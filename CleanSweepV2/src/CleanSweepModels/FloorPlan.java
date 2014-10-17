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
	
	public FloorPlan MoveRobot()
	{
		
		List<FloorCell> xMovePossiblities = this.getXMovePossiblities();
						
		if(xMovePossiblities.isEmpty() == false)
		{
			for (final FloorCell cell : xMovePossiblities) 
			{
				this.getRobot().Move(cell.getCoordinates());
				System.out.println(this.getRobot().toString());
				break;
	
			}
			this.MoveRobot();
		}

		
//		List<FloorCell> yMovePossiblities = this.getYMovePossiblities();
//		
//		if(yMovePossiblities.isEmpty() == false)
//		{
//			for (final FloorCell cell : yMovePossiblities) 
//			{
//				this.getRobot().Move(cell.getCoordinates());
//				break;
//	
//			}
//		}

		
		
		return this;
	}
	private FloorCell getCellByPoint(Point point)
	{
		FloorCell fc = this._data.get(point);
		if(fc == null)
		{
			// this means that it is out of bounds
		}
		return fc;
	}
//	private boolean isMoveWithinBounds(Point point)
//	{
//		Point robotCoor = this.getRobot().getCoordinates();
//		int xRightMax = this.xFloorPlanDim() - robotCoor.getX(); // this should always be > 0
//		int xLeftMax = robotCoor.getX() - 0;
//		int yUpMax = this.yFloorPlanDim() - (int)robotCoor.getY(); // this should always be > 0
//		int yDownMax = robotCoor.getY() - 0;
//
//		boolean canMoveFlag = false;
//
//		if(point.getX() < 0)
//			canMoveFlag = Math.abs(point.getX()) <= xLeftMax; 
//		else
//			canMoveFlag = point.getX() <= xRightMax;
//
//		if(canMoveFlag == false)
//			return canMoveFlag;
//
//		if(point.getY() < 0 )
//			canMoveFlag = Math.abs(point.getY()) <= yDownMax;
//		else
//			canMoveFlag = point.getY() <= yUpMax;
//
//		return canMoveFlag;
//	}
	private List<FloorCell> getXMovePossiblities()
	{
		List<FloorCell> possibleCells = new ArrayList<>();
		
		FloorCell fc = this.getCellByPoint(this.getRobot().getCoordinates());
		if(fc != null)
		{
			if(fc.getEastObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX()+1,0));
				possibleCells.add(possibleCell);
			}
			else if(fc.getWestObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX()-1,0));
				possibleCells.add(possibleCell);
			}
		}
		
		return possibleCells;
		
	}
	private List<FloorCell> getYMovePossiblities()
	{
		List<FloorCell> possibleCells = new ArrayList<>();
		
		FloorCell fc = this.getCellByPoint(this.getRobot().getCoordinates());
		
		if(fc.getNorthObstructions() == FloorObstructions.OPEN)
		{
			FloorCell possibleCell = this.getCellByPoint(new Point(0,this.getRobot().getCoordinates().getY()+1));
			possibleCells.add(possibleCell);
		}
		else if(fc.getSouthObstructions() == FloorObstructions.OPEN)
		{
			FloorCell possibleCell = this.getCellByPoint(new Point(0,this.getRobot().getCoordinates().getY()-1));
			possibleCells.add(possibleCell);
		}
		
		return possibleCells;
		
	}

}
