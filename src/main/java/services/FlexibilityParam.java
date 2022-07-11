package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.maps.model.LatLng;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class FlexibilityParam
{

	public FlexibilityParam()
	{

	}

	public static Map<LatLng, Double> getFlts(Map<LatLng, List<Double>> usersmapDiffs)
	{
		Map<LatLng, Double> usersmapFlts = new HashMap<LatLng, Double>(usersmapDiffs.size());

		for (Entry<LatLng, List<Double>> entry : usersmapDiffs.entrySet())
			usersmapFlts.put(entry.getKey(), getFlt(entry.getValue()));

		return usersmapFlts;

	}

	/**
	 * 
	 * @param diffs
	 * @return
	 */
	public static Double getFlt(List<Double> diffs)
	{
		Level level = LevellingApproach.getLevel(diffs);

		switch (level)
		{
			case VERY_FLEXIBLE:
				return 60.0; // maximum flt
			case FLEXIBLE:
				return 45.0; // flex flt
			case NORMAL:
				return 30.0; // normal flt
			case STRICT:
				return 15.0; // strict flt
			case VERY_STRICT:
				return 0.0; // min flt

		}
		return 0.0;
	}
}
