package services;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.google.maps.model.LatLng;

import entities.Order;
import entities.TimeWindow;
import entities.Tour;
import entities.User;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class ImportManualTourData
{

	public enum Headers
	{
		// Tour
		T_ID,
		T_START,
		T_END,

		// User
		USER_Lat,
		USER_Lng,
		USER_Start,
		USER_End,
		USER_TYPE,

		// Historical data per user(6 months):
		NUM_OF_ORDERS,
		AMOUNT_OF_ORDERS,

		// Time
		DELIVERY_TIME,
		DIFF_EARLY,
		DIFF_LATE,
		DIFF_TO_RANGE_START,
		DIFF_TO_RANGE_END,

	}

	private static List<Tour> TOURS = new ArrayList<Tour>();

	private static List<User> USERS = new ArrayList<User>();

	private static List<Order> orders = new ArrayList<Order>();

	public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

	public static void main(String[] args)
	{
		String path = "H:\\Thesis_data\\Punctuality\\late_deliveries_2021-11-2022-6.csv";
		try
		{
			List<Tour> tours = readTourDataFromExcel(path);

			System.out.println(tours.size());

			for (Tour tour : tours)
			{
				// System.out.println("Tour: " + tour.getId() + "\tstart:" +
				// tour.getStart() + "\tend:" + tour.getEnd());

				// tour.getOrders().forEach(o ->
				// System.out.print(o.toString()));

			}
		}
		catch (IOException e)
		{

			e.printStackTrace();
		}
	}

	private static List<Tour> readTourDataFromExcel(String path) throws IOException
	{

		Reader in = new FileReader(path);

		CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withDelimiter(',').withFirstRecordAsHeader());

		List<CSVRecord> records = parser.getRecords();

		List<Tour> tours = new ArrayList<>();

		for (CSVRecord record : records)
		{

			// Tour
			Tour tour = new Tour();

			// id
			String t_id = record.get(Headers.T_ID);
			tour.setId(Long.parseLong(t_id));

			// start
			String t_start = record.get(Headers.T_START);
			// end
			String t_end = record.get(Headers.T_END);

			try
			{
				// tour start->end
				tour.setTimeWindow(new TimeWindow(formatter.parse(t_start), formatter.parse(t_end)));

				Order order = new Order();
				// user location
				double lat = Double.parseDouble(record.get(Headers.USER_Lat));
				double lng = Double.parseDouble(record.get(Headers.USER_Lng));

				LatLng latLng = new LatLng(lat, lng);

				Date start = formatter.parse(record.get(Headers.USER_Start));

				Date end = formatter.parse(record.get(Headers.USER_End));

				User user = new User((long) lat, latLng, new TimeWindow(start, end));
				order.setUser(user);
				order.setDeliveryTime(formatter.parse(record.get(Headers.DELIVERY_TIME)));
				order.setDeliveryTime(formatter.parse(record.get(Headers.DELIVERY_TIME)));

				tour.getOrders().add(order);

				tours.add(tour);

			}
			catch (ParseException | NumberFormatException e)
			{
				e.printStackTrace();
			}
		}
		parser.close();

		return tours;

	}

	/**
	 * @return
	 */
	public static List<Tour> getTours()
	{
		return null;
	}

}
