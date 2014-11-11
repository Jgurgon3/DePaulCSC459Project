package src.main.java.CleanSweepModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import src.main.java.*;
import src.main.java.CleanSweepModels.RobotLog.LogActivityTypes;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorObstructions;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.Point;



public class Robot {
	
	private Point _coordinates;
	private double _power = 50;
	private int _dirtCollected = 0;
	private int _totalDirtCollected = 0;
	private boolean _returnToChargerFlag = false;
	private FloorPlan _floorPlan;
	private HashMap<Point, FloorCell> _memory = new HashMap<Point, FloorCell>();
	private RobotLog _log = new RobotLog();
	private int _breadcrumbPowerNeeded = 0;
	private final int maxAllowableDirt = 50;
	private ArrayList<FloorCell> _breadCrumb = new ArrayList<FloorCell>();
	
	public Point getCoordinates()
	{
		return this._coordinates;
	}
	public void MoveRobot()
	{
		if(_floorPlan.floorPlanIsCleaned())
		{
			returnToCharger();
			return;
		}
		List<FloorCell> movePossiblities = this.getMovePossiblities(this._floorPlan.getCellByPoint(getCoordinates()));
		_floorPlan.setFoundDirtyCell(false);
		for (final FloorCell cell : movePossiblities) 
		{
			if(CanMove(cell))
			{			
					this.Move(cell,true);
					cell.setVisited();
					while(!cell.alreadyCleaned())
					{
						_floorPlan.setFoundDirtyCell(true);
						Clean(cell);
						_floorPlan.AddCell(cell); // this updates the cells attributes.
					}										
					System.out.println(this.toString());
					if(getReturnToChargerFlag())
					{
						returnToCharger();
					}
					break;
			}
			else
			{
				returnToCharger();
				break;
			}
			
		}

	}
	public void returnToCharger()
	{
		for(int i = getBreadCrumb().size()-1; i>=0;i--)
		{
			Move(getBreadCrumb().get(i),false);
			System.out.println(this.toString());
		}
		resetBreadCrumbPowerNeeded();
		ChargeAndEmpty();
		
		
	}
	private FloorPlan getFloorPlan()
	{
		return this._floorPlan;
	}
	private void addBreadCrumb(FloorCell fc)
	{
		this._breadCrumb.add(fc);
			addToBreadCrumbPowerNeeded(fc.getCoordinates());
	}
	public ArrayList<FloorCell> getBreadCrumb()
	{
		return this._breadCrumb;
	}
	public void addToDirtCollected()
	{
		this._dirtCollected++;
	}
	public int getDirtCollected()
	{
		return this._dirtCollected;
	}
	public void resetBreadCrumbPowerNeeded()
	{
		this._breadcrumbPowerNeeded = 0;
	}
	private void addToBreadCrumbPowerNeeded(Point point)
	{
		this._breadcrumbPowerNeeded += this.calculatePowerToMove(point);
	}
	public void subtractFromBreadCrumbPowerNeeded(Point point)
	{
		this._breadcrumbPowerNeeded -= this.calculatePowerToMove(point);
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
	public double getPower()
	{
		return this._power;
	}
	
	public void ChargeAndEmpty()
	{
		this._power = 50.0;
		this._totalDirtCollected += this._dirtCollected;
		this._dirtCollected = 0;
		this.setReturnToChargerFlag(false);
		this._breadCrumb.clear();
	}

	private void addChargerToBreadCrumb()
	{
		this.addBreadCrumb(this.getFloorPlan().getCellByPoint(new Point(0,0)));
	}
	public boolean CanClean(Point point)
	{
		return this.hasEnoughPower(point) && canStoreMoreDirt();
	}
	public void Clean(FloorCell fc)
	{
		if(this.getCoordinates().equals(fc.getCoordinates())) // only clean cells that we are currently at
		{
			fc.Clean();	
			this.addToDirtCollected();
			_log.addLog(LogActivityTypes.CLEANED, "Cleaned unit of dirt at: " + fc.getCoordinates().toString() + " with floor type " + fc.getFloorType().name());
		}
	}
	public boolean CanMove(FloorCell fc)
	{
		if(!this.getReturnToChargerFlag() && hasEnoughPower(fc.getCoordinates()))
		{
			return true;
		}
		else
			return false;
		
	}
	public void Move(FloorCell fc,boolean AddToBreadCrumb)
	{
		Point currentCoor = this.getCoordinates();
		//Change by Pravangsu on Nov 7, 2014, Check should be make before move
		if (Math.abs(currentCoor.getX() - fc.getCoordinates().getX()) > 1 || Math.abs(currentCoor.getY() - fc.getCoordinates().getY()) > 1)
			throw new IllegalArgumentException("Attempted to move two cells at once");
		if (Math.abs(currentCoor.getX() - fc.getCoordinates().getX()) + Math.abs(currentCoor.getY() - fc.getCoordinates().getY()) > 1)
			throw new IllegalArgumentException("Attempted to move two cells at once (diagonally)");

		
		if(AddToBreadCrumb )//&& fc.alreadyCleaned() == false)
		{	
			// this means we are moving forward
			this.addBreadCrumb(this.getFloorPlan().getCellByPoint(currentCoor));
			this._power -= calculatePowerToMove(fc.getCoordinates());
		}
		else
		{
			
			this._power -= 1;
			if(AddToBreadCrumb == false)
			{
				this.subtractFromBreadCrumbPowerNeeded(fc.getCoordinates());
			}
		}
		currentCoor.setX(fc.getCoordinates().getX());
		currentCoor.setY(fc.getCoordinates().getY());
		
		
		logMove(fc);
		
		
	}
	private void logMove(FloorCell fc)
	{
		_memory.put(fc.getCoordinates(), fc);
		_log.addLog(LogActivityTypes.MOVE, "Robot moved to cell: " + fc.getCoordinates().toString());
	}
	public double calculatePowerToMove(Point point)
	{
		if(this.getFloorPlan().getCellByPoint(this.getCoordinates()).alreadyCleaned())
		{
			return .5;
		}
		return (this.getFloorPlan().getCellByPoint(this.getCoordinates()).getFloorType().getValue() 
				+ this.getFloorPlan().getCellByPoint(point).getFloorType().getValue())/2;
	}
	private ArrayList<FloorCell> getMovePossiblities(FloorCell fc)
	{
		ArrayList<FloorCell> possibleCells = new ArrayList<>();
		
		if(fc != null)
		{
			if(fc.getWestObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = getFloorPlan().getCellByPoint(new Point(getCoordinates().getX()-1,getCoordinates().getY()));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
			if(fc.getEastObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = getFloorPlan().getCellByPoint(new Point(getCoordinates().getX()+1,this.getCoordinates().getY()));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
				
			}
			if(fc.getNorthObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = getFloorPlan().getCellByPoint(new Point(getCoordinates().getX(),getCoordinates().getY()+1));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
			
			if(fc.getSouthObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = getFloorPlan().getCellByPoint(new Point(getCoordinates().getX(),getCoordinates().getY()-1));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
			long seed = System.nanoTime();
			if(!_floorPlan.getFoundDirtyCell())
			{
				Collections.shuffle(possibleCells, new Random(seed));
				Collections.sort(possibleCells);	
			}
			
		}
		return possibleCells;
	}
		
	public Robot(int xCoor,int yCoor,FloorPlan fp)
	{
		
		Point p = new Point(xCoor, yCoor);
		
		this.setFloorPlan(fp);
	
		if  (fp.getCellByPoint(p) == null) {

			throw new IllegalArgumentException("Attempt to instantiate robot at invalid point");
			
		}
		
		this._coordinates = p;
		this.addChargerToBreadCrumb();
		while(this.CanClean(p) && !this.getFloorPlan().getCellByPoint(p).alreadyCleaned())
		{
			this.Clean(this.getFloorPlan().getCellByPoint(p));
			this.getFloorPlan().AddCell(this.getFloorPlan().getCellByPoint(p)); // this updates the cells attributes.
		}	
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
	private boolean hasEnoughPower(Point point)
	{
		double powerToMove = calculatePowerToMove(point);
		double forecastedPowerLeft = this.getPower() - powerToMove;
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
	public boolean canStoreMoreDirt()
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
				+ "\nDirt collected: " + Integer.toString(this._totalDirtCollected));
	}

	public void Move(Point p)
	{
		
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
			int floorTypes =floorCell.getFloorType().getValue();
			
			switch(floorTypes)
			{
			case 1:
				powerUnit=1.0;
				break;
			case 2:
				powerUnit=2.0;
				break;
	
			case 4:
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
			
			int floorTypes =prevCell.getFloorType().getValue();
			switch(floorTypes)
			{
			case 1:
				powerUnitPrev=.5;
				break;
			case 2:
				powerUnitPrev=1.0;
				break;
	
			case 4:
				powerUnitPrev=1.5;
				break;
			}
			int floorTypes2 =floorCell.getFloorType().getValue();;
			switch(floorTypes2)
			{
			case 1:
				powerUnitCurrent=.5;
				break;
			case 2:
				powerUnitCurrent=1.0;
				break;
	
			case 4:
				powerUnitCurrent=1.5;
				break;
		
			}
			
			return powerUnitPrev + powerUnitCurrent;
			}
		}
}
