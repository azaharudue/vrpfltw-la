
package entities;

import java.util.Date;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class Order
{
	User user;

	long salevolume;

	long quantity;

	private Date deliveryTime;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Order [user=" + user.toString() + ", salevolume=" + salevolume + ", " + "\nquantity=" + quantity + ", deliveryTime=" + deliveryTime + "]";
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * @return the salevolume
	 */
	public long getSalevolume()
	{
		return salevolume;
	}

	/**
	 * @param salevolume
	 *            the salevolume to set
	 */
	public void setSalevolume(long salevolume)
	{
		this.salevolume = salevolume;
	}

	/**
	 * @return the quantity
	 */
	public long getQuantity()
	{
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(long quantity)
	{
		this.quantity = quantity;
	}

	public Date getDeliveryTime()
	{
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime)
	{
		this.deliveryTime = deliveryTime;
	}

}
