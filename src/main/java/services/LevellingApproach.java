/**
 * 
 */
package services;

import java.util.ArrayList;
import java.util.List;

import entities.Order;
import entities.User;

/**
 * @author Azahar Hossain (c) 2022
 * 
 */
public class LevellingApproach
{

	/**
	 * 
	 * @return average punctuality score for given differences
	 * @see LevellingApproach#getScorePerDiff(Double)
	 */
	private static Double getAvgPuntualityScore(List<Double> differences)
	{
		List<Double> scores = new ArrayList<Double>();
		double sum = 0.0;
		for (Double diff : differences)
		{
			double indivScore = getScorePerDiff(diff);
			scores.add(indivScore);
			sum += indivScore;
		}
		return sum / scores.size();

	}

	public static Double getAvgPS(List<Double> differences)
	{
		return getAvgPuntualityScore(differences);
	}

	public static boolean inRange(double x, double d, double e)
	{
		return ((x - d) * (x - e) <= 0);
	}

	/**
	 * 
	 * 0-1 mins = ~100% 3-5 mins = ~95% 5-10mins = ~90% then i=10, j=20, k = 80
	 * c=10
	 * 
	 * diff in_range(i=i+c,j=j+c) => k=k-c;
	 * 
	 * 
	 * @param diff
	 * @return
	 */
	private static Double getScorePerDiff(Double diff)
	{
		// TODO: optimize to make it shorter

		diff = Math.abs(diff);
		if (inRange(diff, 0.0, 1))
			return 1.0;
		else if (inRange(diff, 3.0, 5.0))
			return 0.95;
		else if (inRange(diff, 5.0, 10.0))
			return 0.90;
		else if (inRange(diff, 10.0, 20.0))
			return 0.80;
		else if (inRange(diff, 20.0, 30.0))
			return 0.70;
		else if (inRange(diff, 30.0, 40.0))
			return 0.60;
		else if (inRange(diff, 40.0, 50.0))
			return 0.50;
		else if (inRange(diff, 50.0, 60.0))
			return 0.40;
		else if (inRange(diff, 60.0, 70.0))
			return 0.30;
		else if (inRange(diff, 70.0, 80.0))
			return 0.20;
		else if (diff > 80.0 && diff < 120)
			return 0.1;
		else
			return 0.0;
	}

	/**
	 * 
	 * 0-0.2 Very strict 0.2-0.4 Strict 0.4-0.6 normal 0.6-0.8 flexible .8-1.0
	 * very flexible
	 * 
	 * @param strictness
	 * @return
	 */
	public static Level getLevel(double strictness)
	{

		if (inRange(strictness, 0.8, 1.0))
			return Level.VERY_STRICT;
		else if (inRange(strictness, 0.6, 0.8))
			return Level.STRICT;
		else if (inRange(strictness, 0.4, 0.6))
			return Level.NORMAL;
		else if (inRange(strictness, 0.2, 0.4))
			return Level.FLEXIBLE;
		else
			return Level.VERY_FLEXIBLE;
	}

	public static double getStrictness(double avgPunctScore)
	{
		double strictness = 0.0;
		if (inRange(avgPunctScore, 0.0, 0.1))
			strictness = 1.0;
		else if (inRange(avgPunctScore, 0.1, 0.99))
			strictness = 1 - avgPunctScore;
		return strictness;
	}

	/**
	 * 0.0 -> 1.0 0.1 ->0.9 0.2 ->0.8 .. 0.9 ->0.1 1.0 ->0.0
	 * 
	 * @param avgPunctScore
	 * @return
	 *
	 * 
	 * 		public static double getStrictness(Level level) { switch (level)
	 *         { case VERY_FLEXIBLE: return 0.0; case FLEXIBLE: return .25; case
	 *         NORMAL: return .5; case STRICT: return .75; case VERY_STRICT:
	 *         return 1.0; } return 0; }
	 */
	/**
	 * @param differences
	 * @return
	 */
	public static double getStrictness(List<Double> differences)
	{
		return getStrictness(getAvgPuntualityScore(differences));
	}

	/**
	 * 
	 * @param latlng
	 * @param differences
	 * @return
	 */
	public static Level getLevel(List<Double> differences)
	{
		return getLevel(getStrictness(differences));

	}

	/**
	 * @param order
	 */
	public static void adujustTime(Order order)
	{
		// based on current : order
		long quantity = order.getQuantity();
		long saleVolume = order.getSalevolume();

		// based on user history
		User user = order.getUser();
		Level level = user.getLevel();
		int numOfOrdersHistory = user.getNumOfOrderHistory;
		double amountOfOrdersHistory = user.getAmountOrderHistory;

		// define a rank according to avg of number of orders and amount.

		// get historical values: >5 orders, >10 orders in last 6 months.

	}

}
