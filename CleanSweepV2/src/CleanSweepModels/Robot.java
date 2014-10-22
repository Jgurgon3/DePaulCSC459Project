package CleanSweepModels;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import CleanSweepModels.RobotLog.LogTypes;
import XMLParse.FloorCell;

public class Robot {
	
	private Point _coordinates;
	private int _power = 50;
	private boolean _returnToChargerFlag = false;
	private FloorPlan _floorPlan;
	private HashMap<Point, FloorCell> _memory = new HashMap<Point, FloorCell>();
	private RobotLog _log = new RobotLog();
	
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
		if (bool)
			_log.addLog(LogTypes.RETURNTOCHARGER, "Returning to charger station");
		this._returnToChargerFlag = bool;
	}
	public int getPower()
	{
		return this._power;
	}

	public boolean CanClean()
	{
		return this.hasEnoughPower();
	}
	public void Clean(FloorCell fc)
	{
		if(this.getCoordinates().equals(fc.getCoordinates())) // only clean cells that we are currently at
		{
			fc.Clean();
			this._power -= 1;	
			_log.addLog(LogTypes.CLEANED, "Cleaned unit of dirt at: " + fc.getCoordinates().toString());
		}
	}
	public boolean Move(Point point)
	{
		Point currentCoor = this.getCoordinates();
		
		if (!_floorPlan.getFloorPlanData().containsKey(point))
			throw new IllegalArgumentException("Attempted move to point outside floorplan");
		
		boolean moved = false;
		if(hasEnoughPower())
		{
			currentCoor.setX(point.getX());
			moved = true;
		}

		if(hasEnoughPower())
		{
			currentCoor.setY(point.getY());
			moved = true;
		}
		
		this._power -= 1;
		
		if (moved)  {
			_memory.put(point, _floorPlan.getCellByPoint(point));
			_log.addLog(LogTypes.MOVE, "Robot moved to cell cell: " + point.toString());
		}
		else
			_log.addLog(LogTypes.NOTENOUGHPOWER, "Can't move to: " + point.toString() + " and get back to charging station.");
		
		return moved;
	}
	public Robot(int xCoor,int yCoor,FloorPlan fp)
	{
		
		Point p = new Point(xCoor, yCoor);
		
		this.setFloorPlan(fp);
	
		if  (fp.getCellByPoint(p) == null) {

			throw new IllegalArgumentException("Attempt to instantiate robot at invalid point");
			
		}
		
		this._coordinates = p;
		_memory.put(new Point(xCoor, yCoor), fp.getCellByPoint(p));
		_log.addLog(LogTypes.WAKEUP, "Robot started at cell: " + p.toString());
	}
	public Map<Point, FloorCell> getMemory() {
		return _memory;
	}
	public void dumpMemory() {

	    Iterator<Point> iterator = _memory.keySet().iterator();  
	    
	    if (!iterator.hasNext())
	    	System.out.println("Clean sweep memory is empty");
	    
	    while (iterator.hasNext()) {  
	       Point key = iterator.next();  
	       FloorCell value = _memory.get(key);  
	       
	       String s= value.toString();  
	       System.out.println(s);
	    }  
	}
	
	public void dumpLog() {
		_log.dumpLog();
	}

	private boolean hasEnoughPower()
	{
		if(this.getReturnToChargerFlag())
		{
			return false;
		}
		else
		{
			int forecastedPowerLeft = this.getPower()-1;
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
		return ("Coordinates of Robot: (" + Integer.toString(this.getCoordinates().getX()) + "," + Integer.toString(this.getCoordinates().getY()) + ")\nRobot power: " + this.getPower());
	}


}
