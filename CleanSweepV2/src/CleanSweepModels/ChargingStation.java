package CleanSweepModels;

public class ChargingStation {
	private Point _coordinates;
	
	public ChargingStation(int xCoor,int yCoor)
	{
		this._coordinates = new Point(xCoor,yCoor);
	}
	public Point getCoordinates()
	{
		return this._coordinates;
	}

}
