package CleanSweepModels;

public class Robot {
	
	private Point _coordinates;
	private int _power = 50;
	private boolean _returnToChargerFlag = false;
	private FloorPlan _floorPlan;

	public Point getCoordinates()
	{
		return this._coordinates;
	}
	private FloorPlan getFloorPlan()
	{
		return this._floorPlan;
	}
	private void setFloorPlan(FloorPlan fp)
	{
		this._floorPlan = fp;
	}
	public boolean getReturnToChargerFlag()
	{
		return this._returnToChargerFlag;
	}
	private void setReturnToChargerFlag(boolean bool)
	{
		this._returnToChargerFlag = bool;
	}
	public int getPower()
	{
		return this._power;
	}
	public Robot Move(Point point)
	{
		Point currentCoor = this.getCoordinates();
		
		if (!_floorPlan.getFloorPlanData().containsKey(point))
			throw new IllegalArgumentException("Attempted move to point outside floorplan");
		
		if(canMove(point.getX()))
		{
			currentCoor.setX(point.getX());
			this._power -= Math.abs(point.getX());
		}

		if(canMove(point.getY()))
		{
			currentCoor.setY(point.getY());
			this._power -= Math.abs(point.getY());
		}
					
		return this;
	}
	public Robot(int xCoor,int yCoor,FloorPlan fp)
	{
		this.setFloorPlan(fp);
		this._coordinates = new Point(xCoor,yCoor);
	}
	private boolean canMove(int distance)
	{
		if(this.getReturnToChargerFlag())
		{
			return false;
		}
		else
		{
			int forecastedPowerLeft = this.getPower()-Math.abs(distance);
			if(forecastedPowerLeft >= this.getChargingStationDistance()){
				this.setReturnToChargerFlag(false);
				return true;
			}
			else
			{
				this.setReturnToChargerFlag(true);
				return false;
			}
		}
	}
	private int getChargingStationDistance()
	{
		ChargingStation charger = this.getFloorPlan().getChargingStation();
		Point chargerCoor = charger.getCoordinates();
		int xChargerCoor = chargerCoor.getX();
		int yChargerCoor = chargerCoor.getY();
		
		int xRobotCoor = this.getCoordinates().getX();
		int yRobotCoor = this.getCoordinates().getY();
		
		int necessaryCharginUnitsToReturnToCharger = Math.abs(xRobotCoor - xChargerCoor) + Math.abs(yRobotCoor - yChargerCoor);
		return necessaryCharginUnitsToReturnToCharger;
		
	}
	public String toString()
	{
		return ("X coordinate of Robot: " + Integer.toString(this.getCoordinates().getX()) 
				+ " --- " + "Y Coordinate of Robot: " + Integer.toString(this.getCoordinates().getY()) + " " + this.getReturnToChargerFlag());
	}


}
