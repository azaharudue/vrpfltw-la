package services;

import java.util.ArrayList;
import java.util.List;

import com.google.maps.model.LatLng;

import entities.Order;
import entities.Tour;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class GenerateRoutes
{

	public static void main(String[] args)
	{

		// depot & other locations:

		List<Tour> tours = ImportManualTourData.getTours();

		// Without LA
		generateRoutes(tours, false);

		// Applying LA
		generateRoutes(tours, true);

	}

	/**
	 * @param tours
	 */
	public static void generateRoutes(List<Tour> tours, boolean withLA)
	{
		for (Tour tour : tours)
		{
			List<Order> orders = tour.getOrders();

			List<LatLng> destinations = new ArrayList<LatLng>(orders.size());

			for (Order order : orders)
			{
				destinations.add(order.getUser().getLocation());
			}

			// Time matrix:
			long[][] timeMatrix = TimeMatrixRetriever.getMatrix(destinations, true);

			// Time windows:
			long[][] timewindows = tour.getTimeWindowsMatrix(withLA);

			DataModel datamodel = new DataModel(timeMatrix, timewindows);

			GoogleOrSolverSevice.generateRoutes(datamodel);

			if (withLA)
			{
				// Here apply LA, hence with updated Time windows:
				long[][] timewindows2 = tour.getTimeWindowsMatrix(withLA);
				DataModel datamodel2 = new DataModel(timeMatrix, timewindows2);

				GoogleOrSolverSevice.generateRoutes(datamodel2);
			}

		}
	}

}
