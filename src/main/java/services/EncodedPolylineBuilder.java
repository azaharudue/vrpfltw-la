/**
 * 
 */
package services;

import java.util.List;
import java.util.Objects;

import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.LatLng;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class EncodedPolylineBuilder
{

	public static String getEndcodedPolyLine(List<LatLng> coordList)
	{
		Objects.requireNonNull(coordList, "coordList must not be null");
		return PolylineEncoding.encode(coordList);

	}

}
