package CleanSweepModels;

import CleanSweepModels.Types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.StringBuilder;

import XMLParse.FloorCell;

public class FloorPlan {


	private int _xFloorPlanDim;
	private int _yFloorPlanDim;
	private ChargingStation _chargingStation;
	private Robot _robot;
	


	private Map<Point, FloorCell> _data = new HashMap<Point, FloorCell>();



	//public access methods
	public int xFloorPlanDim() {return this._xFloorPlanDim;}
	public int yFloorPlanDim() {return this._yFloorPlanDim;}

	public FloorPlan (int xDim, int yDim)
	{

		// This sets the initial size
		this._xFloorPlanDim = xDim;
		this._yFloorPlanDim = yDim;		

	}
	
	public void AddCell(FloorCell fc)
	{
		Point cellCoordinates = new Point(fc.getCoordinates().getX(), fc.getCoordinates().getY());
		
		this._data.put(cellCoordinates,fc);
	
	}

	public Map<Point, FloorCell> getFloorPlanData()
	{
		return this._data;
	}

	public void setRobot(int xCoor, int yCoor)
	{
		this._robot = new Robot(xCoor,yCoor,this);
	}

	public ChargingStation getChargingStation()
	{
		return this._chargingStation;
	}
	public void setChargingStation(int xCoor, int yCoor)
	{
		this._chargingStation = new ChargingStation(xCoor,yCoor);
	}
	
	public Robot getRobot()
	{
		return this._robot;
	}
	private boolean floorPlanIsCleaned()
	{
		for (FloorCell cell : this.getFloorPlanData().values()) {
			if(!cell.alreadyCleaned())
			{
				return false;
			}
		}
		return true;

	}
	public FloorPlan MoveRobot(ArrayList<Point> _breadCrumb)
	{
		
		List<FloorCell> movePossiblities = this.getMovePossiblities();
						
		if(movePossiblities.isEmpty() == false)
		{
			for (final FloorCell cell : movePossiblities) 
			{
				if(!_breadCrumb.contains(cell.getCoordinates()))
				{
					if(this.floorPlanIsCleaned() == false) // continue moving and cleaning
					{
						Point currentRobotCoor = new Point(this.getRobot().getCoordinates().getX(),this.getRobot().getCoordinates().getY());
						if(this.getRobot().Move(cell.getCoordinates()))
						{
							_breadCrumb.add(currentRobotCoor);
							while(this.getRobot().CanClean() && !cell.alreadyCleaned())
							{
								 this.getRobot().Clean(cell);
							}
							this.AddCell(cell); // this updates the cells attributes.
							System.out.println(this.toString());
							this.MoveRobot(_breadCrumb);
						}
						else
						{
							// Return to charger, robot needs power
							System.out.println(this.toString());
							this.returnToCharger(_breadCrumb);
							break;
						}
					}
					else
					{
						this.returnToCharger(_breadCrumb);
					}
				}
				
			}
			this.returnToCharger(_breadCrumb);

			
		}
		
		return this;
	}
	private void returnToCharger(ArrayList<Point> _breadCrumb)
	{
		for(int i = _breadCrumb.size() -1; i > 0; i--)
		{
			Point point = _breadCrumb.get(i);
			this.getRobot().Move(point); // send the robot back on the path it came on
		}
		_breadCrumb.clear();
	}
	private FloorCell getCellByPoint(Point point)
	{
		FloorCell fc = this._data.get(point);
		return fc;
	}

	private List<FloorCell> getMovePossiblities()
	{
		List<FloorCell> possibleCells = new ArrayList<>();
		
		FloorCell fc = this.getCellByPoint(this.getRobot().getCoordinates());
		if(fc != null)
		{
			if(fc.getEastObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX()+1,this.getRobot().getCoordinates().getY()));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
				
			}
			if(fc.getNorthObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX(),this.getRobot().getCoordinates().getY()+1));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
			if(fc.getWestObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX()-1,this.getRobot().getCoordinates().getY()));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
			if(fc.getSouthObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX(),this.getRobot().getCoordinates().getY()-1));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
		}
		
		return possibleCells;
		
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int x=0;x<this._xFloorPlanDim;x++)
		{
			for(int y=0;y<this._yFloorPlanDim;y++)
			{
				FloorCell _tmpFC = this.getCellByPoint(new Point(x,y));
				if(_tmpFC.getCoordinates().equals(this.getChargingStation().getCoordinates()))
				{
					sb.append("+");
				}
				if(this.getRobot().getCoordinates().equals(_tmpFC.getCoordinates()))
				{
					sb.append("Robot   ");
				}
				else
				{
					if(_tmpFC.getDirtUnits() == 0)
					{
						sb.append("Clean   ");
					}
					else
					{
						sb.append("Dirty   ");
					}
				}
				
			}
			sb.append("\n");
		}
		
		
		return sb.toString();
	}

}
