package CleanSweepModels;

import CleanSweepModels.Types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import XMLParse.FloorCell;

public class FloorPlan {


	private int _xFloorPlanDim;
	private int _yFloorPlanDim;
	private ChargingStation _chargingStation = new ChargingStation(0,0);
	private Robot _robot = new Robot(0,0,this);
	


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
	
	public FloorPlan MoveRobot(ArrayList<Point> _breadCrumb)
	{
		
		List<FloorCell> movePossiblities = this.getMovePossiblities();
						
		if(movePossiblities.isEmpty() == false)
		{
			//ArrayList<Point> _breadCrumb = new ArrayList<Point>();
			for (final FloorCell cell : movePossiblities) 
			{
				if(!_breadCrumb.contains(cell.getCoordinates()))
				{
					_breadCrumb.add(new Point(this.getRobot().getCoordinates().getX(),this.getRobot().getCoordinates().getY()));
					this.getRobot().Move(cell.getCoordinates());
					cell.setCleaned(true);
					this.AddCell(cell); // this updates the cells attributes.
					System.out.println(this.getRobot().toString());
					this.MoveRobot(_breadCrumb);
				}
				else
				{
					break;
				}
				
			}
			for(final Point point : _breadCrumb)
			{
				this.getRobot().Move(point);
			}
			
		}
		
		return this;
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

}
