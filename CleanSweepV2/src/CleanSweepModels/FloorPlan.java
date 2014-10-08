package CleanSweepModels;


import java.util.HashMap;
import java.util.Map;

import CleanSweepModels.Floor.FloorTypes;


public class FloorPlan {
	

	private int _xFloorPlanDim;
	private int _yFloorPlanDim;
	private ChargingStation _chargingStation = new ChargingStation(0,0);
	private Robot _robot = new Robot(0,0,this);
	
	private Map<Point,Object> _data = new HashMap<Point,Object>();
	
	
	
	//public access methods
	public int xFloorPlanDim() {return this._xFloorPlanDim;}
	public int yFloorPlanDim() {return this._yFloorPlanDim;}
	
	public FloorPlan (int xDim, int yDim)
	{
		this._xFloorPlanDim = xDim;
		this._yFloorPlanDim = yDim;
		for(int i = 0; i < xDim;i++)
		{
			for(int j=0;j<yDim;j++)
			{
				this._data.put(new Point(i,j),new Floor(true,FloorTypes.BARE));
			}
		}
	}
	
	public Map<Point,Object> getFloorPlanData()
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
	public FloorPlan MoveRobot(int xCoor,int yCoor)
	{
		if(canMove(xCoor,yCoor))
		{
			this.getRobot().Move(xCoor, yCoor);
		}
		return this;
	}

	public boolean canMove(int xCoor,int yCoor)
	{
		Point robotCoor = this.getRobot().getCoordinates();
		int xRightMax = this.xFloorPlanDim() - robotCoor.getX(); // this should always be > 0
		int xLeftMax = robotCoor.getX() - 0;
		int yUpMax = this.yFloorPlanDim() - robotCoor.getY(); // this should always be > 0
		int yDownMax = robotCoor.getY() - 0;

		boolean canMoveFlag = false;
		
		if(xCoor < 0)
			canMoveFlag = Math.abs(xCoor) <= xLeftMax; 
		else
			canMoveFlag = xCoor <= xRightMax;
		
		if(canMoveFlag == false)
			return canMoveFlag;
		
		if(yCoor < 0 )
			canMoveFlag = Math.abs(yCoor) <= yDownMax;
		else
			canMoveFlag = yCoor <= yUpMax;
		
		return canMoveFlag;
	}
	
}
