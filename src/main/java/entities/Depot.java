/**
 * 
 */
package entities;

import com.google.maps.model.LatLng;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class Depot
{
	private Long id;

	private LatLng location;

	private TimeWindow timewindow;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Depot [id=" + id + ", location=" + location + ", timewindow=" + timewindow + "]";
	}

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
	 * @return the location
	 */
	public LatLng getLocation()
	{
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(LatLng location)
	{
		this.location = location;
	}

	/**
	 * @return the timewindow
	 */
	public TimeWindow getTimewindow()
	{
		return timewindow;
	}

	/**
	 * @param timewindow
	 *            the timewindow to set
	 */
	public void setTimewindow(TimeWindow timewindow)
	{
		this.timewindow = timewindow;
	}

}
