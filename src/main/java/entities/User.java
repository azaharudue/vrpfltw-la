/**
 * 
 */
package entities;

import java.util.Date;

import com.google.maps.model.LatLng;

import services.Level;
import services.LevellingApproach;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class User
{
	public static double getAmountOrderHistory = 0;

	public static int getNumOfOrderHistory = 0;

	private Long id;

	private LatLng location;

	private TimeWindow timewindow;

	private Level level;

	/**
	 * @param id
	 * @param location
	 * @param timewindow
	 */
	public User(Long id, LatLng location, TimeWindow timewindow)
	{
		super();
		this.id = id;
		this.location = location;
		this.timewindow = timewindow;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "User [id=" + id + ", location=" + location + ", timewindow=" + timewindow + ", level=" + level + "]";
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
	 *            to set
	 */
	public void setTimewindow(TimeWindow timewindow)
	{
		this.timewindow = timewindow;
	}

	/**
	 * updates timewindow according to level V.Flex: +45
	 * 
	 * 
	 * Strict: -30
	 * 
	 */
	public void updateTimeWindow()
	{
		switch (this.level)
		{
			case VERY_STRICT:
				setTimewindow(-30, -30);
				break;
			case STRICT:
				setTimewindow(-15, -15);
				break;
			case NORMAL:
				setTimewindow(15, 15);
				break;
			case FLEXIBLE:
				setTimewindow(30, 30);
				break;
			case VERY_FLEXIBLE:
				setTimewindow(45, 45);
				break;
		}
	}

	/**
	 * 
	 * @param offsetStart
	 * @param offsetEnd
	 */
	public void setTimewindow(int offsetStart, int offsetEnd)
	{
		Date start = this.timewindow.getStart();
		Date end = this.timewindow.getEnd();

		start.setMinutes(start.getMinutes() + offsetStart);
		end.setMinutes(end.getMinutes() + offsetEnd);
	}

	/**
	 * @return the level
	 */
	public Level getLevel()
	{
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Level level)
	{
		this.level = level;
		this.updateTimeWindow();
	}

	/**
	 * Sets a level for the user according to the given strictness value.
	 * 
	 * @param strictness
	 */
	public void setLevel(double strictness)
	{
		if (LevellingApproach.inRange(strictness, 0.8, 1.0))
			this.level = Level.VERY_STRICT;
		else if (LevellingApproach.inRange(strictness, 0.6, 0.8))
			this.level = Level.STRICT;
		else if (LevellingApproach.inRange(strictness, 0.4, 0.6))
			this.level = Level.NORMAL;
		else if (LevellingApproach.inRange(strictness, 0.2, 0.4))
			this.level = Level.FLEXIBLE;
		else
			this.level = Level.VERY_FLEXIBLE;
	}

}
