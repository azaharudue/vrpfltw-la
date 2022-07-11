package services;

/**
 * @author Azahar Hossain (c) 2022
 *
 */
public class DataModel
{
	long[][] timeMatrix;

	private long[][] timeWindows;

	private int vehicleNumber = 2;

	private int depot = 0;

	/**
	 * Constructors
	 */
	public DataModel()
	{

	}

	/**
	 * @param timeMatrix
	 * @param timeWindows
	 */
	public DataModel(long[][] timeMatrix, long[][] timeWindows)
	{
		this.timeMatrix = timeMatrix;
		this.timeWindows = timeWindows;
	}

	/**
	 * @param timeMatrix
	 * @param timeWindows
	 * @param vehicleNumber
	 * @param depot
	 */
	public DataModel(long[][] timeMatrix, long[][] timeWindows, int vehicleNumber, int depot)
	{
		this.timeMatrix = timeMatrix;
		this.timeWindows = timeWindows;
		this.vehicleNumber = vehicleNumber;
		this.depot = depot;
	}

	/**
	 * @return the timeMatrix
	 */
	public long[][] getTimeMatrix()
	{
		return timeMatrix;
	}

	/**
	 * @param timeMatrix
	 *            the timeMatrix to set
	 */
	public void setTimeMatrix(long[][] timeMatrix)
	{
		this.timeMatrix = timeMatrix;
	}

	/**
	 * @return the timeWindows
	 */
	public long[][] getTimeWindows()
	{
		return timeWindows;
	}

	/**
	 * @param timeWindows
	 *            the timeWindows to set
	 */
	public void setTimeWindows(long[][] timeWindows)
	{
		this.timeWindows = timeWindows;
	}

	/**
	 * @return the vehicleNumber
	 */
	public int getVehicleNumber()
	{
		return vehicleNumber;
	}

	/**
	 * @param vehicleNumber
	 *            the vehicleNumber to set
	 */
	public void setVehicleNumber(int vehicleNumber)
	{
		this.vehicleNumber = vehicleNumber;
	}

	/**
	 * @return the depot
	 */
	public int getDepot()
	{
		return depot;
	}

	/**
	 * @param depot
	 *            the depot to set
	 */
	public void setDepot(int depot)
	{
		this.depot = depot;
	}

}
