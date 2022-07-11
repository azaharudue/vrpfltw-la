package tests;

import java.util.ArrayList;
import java.util.List;

import services.FlexibilityParam;
import services.LevellingApproach;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class TestLevellingApproach
{

	public static void main(String[] args)
	{

		List<Double> diffs = new ArrayList<>();
		diffs.add(45.0);
		diffs.add(5.0);
		diffs.add(50.0);
		diffs.add(-129.0);
		diffs.add(-151.0);
		diffs.add(-75.0);
		diffs.add(102.0);
		diffs.add(-267.0);
		diffs.add(-50.0);
		diffs.add(-1144.0);
		diffs.add(-51.0);
		diffs.add(-1324.0);
		diffs.add(-1167.0);
		diffs.add(-78.0);
		diffs.add(8.0);
		diffs.add(86.0);
		diffs.add(21.0);
		diffs.add(-110.0);
		diffs.add(48.0);
		diffs.add(142.0);
		diffs.add(-70.0);
		diffs.add(-86.0);
		diffs.add(-127.0);
		diffs.add(-68.0);
		diffs.add(-125.0);
		diffs.add(16.0);
		diffs.add(57.0);
		diffs.add(24.0);
		diffs.add(-55.0);
		diffs.add(-3.0);
		diffs.add(79.0);

		// average punctuality score
		Double avgScore = LevellingApproach.getAvgPS(diffs);
		Double strictness = LevellingApproach.getStrictness(avgScore);
		double flt = FlexibilityParam.getFlt(diffs);
		System.out.println("Strictness(%): " + strictness + "\nFlt(mins): " + flt);

	}

}
