package CleanSweepModels;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import CleanSweepModels.RobotLog.LogActivityTypes;
import CleanSweepModels.Types.FloorTypes;
import XMLParse.FloorCell;

public class Robot {
	
	private Point _coordinates;
	private int _power = 50;
	private int _dirtCollected = 0;
	private boolean _returnToChargerFlag = false;
	private FloorPlan _floorPlan;
	private HashMap<Point, FloorCell> _memory = new HashMap<Point, FloorCell>();
	private RobotLog _log = new RobotLog();
	private int _breadcrumbPowerNeeded = 0;
	private final int maxAllowableDirt = 50;
	
	public Point getCoordinates()
	{
		return this._coordinates;
	}
	private FloorPlan getFloorPlan()
	{
		return this._floorPlan;
	}
	public void addToDirtCollected()
	{
		this._dirtCollected++;
	}
	public int getDirtCollected()
	{
		return this._dirtCollected;
	}
	public void addToBreadCrumbPowerNeeded()
	{
		this._breadcrumbPowerNeeded++;
	}
	public void subtractFromBreadCrumbPowerNeeded()
	{
		this._breadcrumbPowerNeeded--;
	}
	public int getBreadCrumbPowerNeeded()
	{
		return this._breadcrumbPowerNeeded;
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
			_log.addLog(LogActivityTypes.RETURNTOCHARGER, "Returning to charger station");
		this._returnToChargerFlag = bool;
	}
	public int getPower()
	{
		return this._power;
	}

	public boolean CanClean()
	{
		return this.hasEnoughPower() && canStoreMoreDirt();
	}
	public void Clean(FloorCell fc)
	{
		if(this.getCoordinates().equals(fc.getCoordinates())) // only clean cells that we are currently at
		{
			fc.Clean();
			this._power -= (1 * fc.getFloorType().getValue());	
			this.addToDirtCollected();
			_log.addLog(LogActivityTypes.CLEANED, "Cleaned unit of dirt at: " + fc.getCoordinates().toString() + " with floor type " + fc.getFloorType().name());
		}
	}
	public boolean CanMove()
	{
		if(hasEnoughPower() ||  this.getReturnToChargerFlag())
		{
			return true;
		}
		else
			return false;
		
	}
	public void Move(Point point)
	{
		Point currentCoor = this.getCoordinates();
		
	//	if (Math.abs(currentCoor.getX() - point.getX()) > 1 || Math.abs(currentCoor.getY() - point.getY()) > 1)
	//		throw new IllegalArgumentException("Attempted to move two cells at once");
	//	if (Math.abs(currentCoor.getX() - point.getX()) + Math.abs(currentCoor.getY() - point.getY()) > 1)
	//		throw new IllegalArgumentException("Attempted to move two cells at once (diagonally)");
		
		currentCoor.setX(point.getX());
		currentCoor.setY(point.getY());
		logMove(point);
		this._power -= 1;
	}
	private void logMove(Point point)
	{
		_memory.put(point, _floorPlan.getCellByPoint(point));
		_log.addLog(LogActivityTypes.MOVE, "Robot moved to cell: " + point.toString());
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
		_log.addLog(LogActivityTypes.WAKEUP, "Robot started at cell: " + p.toString());
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
			if(forecastedPowerLeft > this.getBreadCrumbPowerNeeded()){
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
	private boolean canStoreMoreDirt()
	{
		if(this.getDirtCollected() < this.maxAllowableDirt)
		{
			return true;
		}
		else
		{
			this.setReturnToChargerFlag(true);
			return false;
		}
	}
	
	public String toString()
	{
		return ("Coordinates of Robot: (" + Integer.toString(this.getCoordinates().getX()) + "," 
				+ Integer.toString(this.getCoordinates().getY()) + ")\nRobot power: " + this.getPower()
				+ "\nDirt collected: " + Integer.toString(this._dirtCollected));
	}

	public Double getPowerForCellClean(FloorCell floorCell)
		{
			//1 unit battery for cleaning bare floor 
			//2 unit battery for cleaning low pile carpet floor 
			//3 unit battery for cleaning high pile carpet floor 
			
			//1 The cell is bare floor.
			//2 The cell is covered in low-pile carpet.
			//4 The cell is covered in high-pile carpet.
	
			if(floorCell.alreadyCleaned() == true)
				return 0.0;  //No power required to clean, just move to next cell. May be way back to cleaning after charge
			
			Double powerUnit =0.0;
			FloorTypes floorTypes =floorCell.getFloorType();
			switch(floorTypes)
			{
			case BARE:
				powerUnit=1.0;
				break;
			case LOW:
				powerUnit=2.0;
				break;
	
			case HIGH:
				powerUnit=3.0;
				break;
		
			}
			return powerUnit;
		}
		
		public Double getPowerForCellMoving(FloorCell floorCell ,FloorCell prevCell )
		{
			// 1 unit of power for moving between bare floors to bare floor 
			//1.5 unit of power for moving between bare floor to low pile carpet floor
			//2 unit of power for moving between bare floor to high pile carpet floor
			//2.5 unit of power for) moving between low pile carpet floor to high pile carpet floor 
			//3 unit of power for moving between high pile carpet floor to high pile carpet floor
			if(prevCell==null)
				return 0.0; // When we are at cell zero we need not move , just celan it
			else
			{
			Double powerUnitPrev =0.0;
			Double powerUnitCurrent =0.0;
			
			FloorTypes floorTypes =prevCell.getFloorType();
			switch(floorTypes)
			{
			case BARE:
				powerUnitPrev=.5;
				break;
			case LOW:
				powerUnitPrev=1.0;
				break;
	
			case HIGH:
				powerUnitPrev=1.5;
				break;
			}
			FloorTypes floorTypes2 =floorCell.getFloorType();
			switch(floorTypes2)
			{
			case BARE:
				powerUnitCurrent=.5;
				break;
			case LOW:
				powerUnitCurrent=1.0;
				break;
	
			case HIGH:
				powerUnitCurrent=1.5;
				break;
		
			}
			
			return powerUnitPrev + powerUnitCurrent;
			}
		}
}
