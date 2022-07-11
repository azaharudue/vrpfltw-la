/**
 * 
 */
package entities;

import java.util.ArrayList;
import java.util.List;

import services.LevellingApproach;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class Tour
{
	private Long id;

	private TimeWindow timeWindow;

	private final List<Order> orders = new ArrayList<Order>();

	private Depot depot;

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders()
	{
		return orders;
	}

	public Depot getDepot()
	{
		return depot;
	}

	public void setDepot(Depot depot)
	{
		this.depot = depot;
	}

	/**
	 * Currently supports single time windows
	 * 
	 * timewindow of depot is set as initial index.
	 * 
	 * @return
	 */
	public long[][] getTimeWindowsMatrix(boolean withLA)
	{
		long[][] timeWindows = new long[orders.size()][2];

		// time window of depot as initial index
		timeWindows[0][0] = this.depot.getTimewindow().getStart().getMinutes();
		timeWindows[0][1] = this.depot.getTimewindow().getEnd().getMinutes();

		for (int i = 1; i < orders.size(); i++)
		{
			if (withLA)
				LevellingApproach.adujustTime(orders.get(i));

			timeWindows[i][0] = orders.get(i).user.getTimewindow().getStart().getMinutes();
			timeWindows[i][1] = orders.get(i).user.getTimewindow().getEnd().getMinutes();
		}

		return timeWindows;
	}

	public TimeWindow getTimeWindow()
	{
		return timeWindow;
	}

	public void setTimeWindow(TimeWindow timeWindow)
	{
		this.timeWindow = timeWindow;
	}

}
