/**
 * 
 */
package services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.maps.model.LatLng;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class TimeMatrixRetriever
{

	private static final String API_KEY = System.getenv("GOOGLE_DISTANCE_API_KEY");

	public static long[][] DISTANCE_MATRIX;

	public static long[][] TIME_MATRIX;

	private static OkHttpClient client = new OkHttpClient();

	/**
	 * 
	 * @param source
	 * @param destination
	 * @return
	 * @throws IOException
	 */
	private static String getResponseBody(LatLng source, LatLng destination)
	{
		String result = "";

		String encOrigins = EncodedPolylineBuilder.getEndcodedPolyLine(Arrays.asList(source));
		String encDestinations = EncodedPolylineBuilder.getEndcodedPolyLine(Arrays.asList(destination));
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + "enc:" + encOrigins + ":" + "&destinations=" + "enc:"
			+ encDestinations + ":" + "&key=" + API_KEY;
		Request request = new Request.Builder().url(url).build();

		okhttp3.Response response;
		try
		{
			response = client.newCall(request).execute();
			result = response.body().string();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param sources
	 * @param destinations
	 * @return
	 *
	 */
	public static void processMatrix(LatLng source, LatLng destination, int i, int j, boolean timeMatrix)
	{
		String responseString = getResponseBody(source, destination);
		long duration = parseResponse(responseString, i, j, timeMatrix);

		System.out.println("Duration(" + source + "->" + destination + "): " + duration + "ms");
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	private static long parseResponse(String response, int i, int j, boolean timeMatrix)
	{

		try
		{

			JsonObject jsonObject = (JsonObject) JsonParser.parseString(response);

			JsonArray jsonArray = (JsonArray) jsonObject.get("rows");

			jsonArray = (JsonArray) ((JsonObject) jsonArray.get(0)).get("elements");

			JsonObject elementObject = (JsonObject) jsonArray.get(0);

			// duration
			long duration = elementObject.get("duration").getAsJsonObject().get("value").getAsLong();

			TIME_MATRIX[i][j] = duration;

			if (!timeMatrix)
			{
				// distance
				long distance = elementObject.get("distance").getAsJsonObject().get("value").getAsLong();

				DISTANCE_MATRIX[i][j] = distance;
			}
			return duration;

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return 0;
	}

	public static long[][] getMatrix(List<LatLng> locations, boolean timeMatrix)
	{
		int n = locations.size();
		TIME_MATRIX = new long[n][n];
		if (!timeMatrix)
			DISTANCE_MATRIX = new long[n][n];
		Objects.requireNonNull(locations, "locations must not be null");

		for (int i = 0; i < locations.size(); i++)
		{
			for (int j = 0; j < locations.size(); j++)
			{
				if (i != j)
				{
					TimeMatrixRetriever.processMatrix(locations.get(i), locations.get(j), i, j, timeMatrix);
				}

				else
				{
					TIME_MATRIX[i][j] = 0;
					if (!timeMatrix)
						DISTANCE_MATRIX[i][j] = 0;
				}
			}
		}
		return timeMatrix ? TIME_MATRIX : DISTANCE_MATRIX;
	}
}
