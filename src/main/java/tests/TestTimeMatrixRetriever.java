
package tests;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.maps.model.LatLng;

import services.TimeMatrixRetriever;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class TestTimeMatrixRetriever
{

	private static List<LatLng> DESTINATIONS = new ArrayList<LatLng>();
	public static long[][] DISTANCE_MATRIX;
	public static long[][] TIME_MATRIX;

	static
	{
		// tour 4386
		LatLng L1 = new LatLng(51.4198511, 6.9604499);
		// LatLng L2 = new LatLng(49.4803094, 8.479098);
		LatLng L3 = new LatLng(51.3515924, 6.6325733);
		LatLng L4 = new LatLng(51.3608802, 6.930263);
		LatLng L5 = new LatLng(51.4198033, 6.7554258);
		LatLng L6 = new LatLng(51.5631544, 6.7125437);

		DESTINATIONS.addAll(Arrays.asList(L1, L3, L4, L5, L6));

	}
	private static final int NUM_LOCATIONS = DESTINATIONS.size();

	public static void main(String[] args)
	{

		PrintWriter out2;
		try
		{
			out2 = new PrintWriter("timeMatrix.txt");
			TIME_MATRIX = TimeMatrixRetriever.getMatrix(DESTINATIONS, true);

			for (int i = 1; i < NUM_LOCATIONS; i++)
			{
				for (int j = 1; j < NUM_LOCATIONS; j++)
				{
					System.out.println(TIME_MATRIX[i][j] + " ");
					out2.print(TIME_MATRIX[i][j] + " ");
				}
				out2.println();
			}

			out2.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
