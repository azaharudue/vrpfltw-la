/**
 * 
 */
package entities;

import java.util.Date;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class TimeWindow
{
	private Date start;

	private Date end;

	/**
	 * 
	 */
	public TimeWindow()
	{
	}

	/**
	 * @param start
	 * @param end
	 */
	public TimeWindow(Date start, Date end)
	{
		this.start = start;
		this.end = end;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TimeWindow [start=" + start + ", end=" + end + "]";
	}

	/**
	 * @return the start
	 */
	public Date getStart()
	{
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(Date start)
	{
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Date getEnd()
	{
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(Date end)
	{
		this.end = end;
	}

}
