package CleanSweepModels;

import CleanSweepModels.Types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.lang.StringBuilder;

import XMLParse.FloorCell;

public class FloorPlan {

	private ChargingStation _chargingStation;
	private Robot _robot;
	private boolean _foundDirtyCell = true;
	private ArrayList<ArrayList<FloorCell>> _prevBreadCrumbs = new ArrayList<ArrayList<FloorCell>>();

	private Map<Point, FloorCell> _data = new HashMap<Point, FloorCell>();

	//public access methods
	public int xFloorPlanDim() 
	{
		int xPrevMax = 0;
		for(Map.Entry<Point, FloorCell> row : this._data.entrySet())
		{
			if(row.getKey().getX() > xPrevMax)
			{
				xPrevMax = row.getKey().getX();
			}
		}
		return xPrevMax+1;
	}
	public int yFloorPlanDim() 
	{
		int yPrevMax = 0;
		for(Map.Entry<Point, FloorCell> row : this._data.entrySet())
		{
			if(row.getKey().getY() > yPrevMax)
			{
				yPrevMax = row.getKey().getY();
			}
		}
		return yPrevMax+1;
	}

	public FloorPlan ()
	{
	}
	public boolean getFoundDirtyCell()
	{
		return this._foundDirtyCell;
	}
	public void setFoundDirtyCell(boolean flag)
	{
		this._foundDirtyCell = flag;
	}
	public void AddCell(FloorCell fc)
	{
		Point cellCoordinates = new Point(fc.getCoordinates().getX(), fc.getCoordinates().getY());
		
		this._data.put(cellCoordinates,fc);
	}

	public void addPreviousBreadCrumb(ArrayList<FloorCell> lFc)
	{
		this._prevBreadCrumbs.add(lFc);
	}
	public ArrayList<ArrayList<FloorCell>> getPreviousBreadCrumb()
	{
		return this._prevBreadCrumbs;
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
	public void setChargingStation()
	{
		this._chargingStation = new ChargingStation(0,0);
	}
	
	public Robot getRobot()
	{
		return this._robot;
	}
	public boolean floorPlanIsCleaned()
	{
		for (FloorCell cell : this.getFloorPlanData().values()) {
			if(!cell.alreadyCleaned())
			{
				return false;
			}
		}
		return true;

	}
	public void MoveRobot()
	{
		if(this.floorPlanIsCleaned())
		{
			this.returnToCharger();
			return;
		}
		List<FloorCell> movePossiblities = this.getMovePossiblities(this.getCellByPoint(this.getRobot().getCoordinates()));
		this.setFoundDirtyCell(false);
		for (final FloorCell cell : movePossiblities) 
		{
			if(this.getRobot().CanMove(cell))
			{			
					this.getRobot().Move(cell,true);
					cell.setVisited();
					while(!cell.alreadyCleaned())
					{
						this.setFoundDirtyCell(true);
						this.getRobot().Clean(cell);
						this.AddCell(cell); // this updates the cells attributes.
					}										
					System.out.println(this.toString());
					if(this.getRobot().getReturnToChargerFlag())
					{
						this.returnToCharger();
					}
					break;
			}
			else
			{
				this.returnToCharger();
				break;
			}
			
		}

	}

	public void returnToCharger()
	{
		for(int i = this.getRobot().getBreadCrumb().size()-1; i>=0;i--)
		{
			this.getRobot().Move(this.getRobot().getBreadCrumb().get(i),false);
			System.out.println(this.toString());
		}
		this.getRobot().resetBreadCrumbPowerNeeded();
		this.getRobot().ChargeAndEmpty();
		
		
	}
	public FloorCell getCellByPoint(Point point)
	{
		FloorCell fc = this.getFloorPlanData().get(point);
		return fc;
	}

	private ArrayList<FloorCell> getMovePossiblities(FloorCell fc)
	{
		ArrayList<FloorCell> possibleCells = new ArrayList<>();
		
		if(fc != null)
		{
			if(fc.getWestObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX()-1,this.getRobot().getCoordinates().getY()));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
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
			
			if(fc.getSouthObstructions() == FloorObstructions.OPEN)
			{
				FloorCell possibleCell = this.getCellByPoint(new Point(this.getRobot().getCoordinates().getX(),this.getRobot().getCoordinates().getY()-1));
				if(fc != null)
				{
					possibleCells.add(possibleCell);
				}
			}
		}
		
		long seed = System.nanoTime();
		if(!_foundDirtyCell)
		{
			Collections.shuffle(possibleCells, new Random(seed));
			Collections.sort(possibleCells);	
		}
		return possibleCells;
		
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for(int y=this.yFloorPlanDim()-1;y>=0;y--)
		{
			for(int x=0;x<=this.xFloorPlanDim()-1;x++)
			{
				FloorCell _tmpFC = this.getCellByPoint(new Point(x,y));
				if(_tmpFC != null)
				{
					if(_tmpFC.getCoordinates().equals(this.getChargingStation().getCoordinates()))
					{
						sb.append("+");
					}
					if(this.getRobot().getCoordinates().equals(_tmpFC.getCoordinates()))
					{
						sb.append("Robot (" + _tmpFC.getCoordinates().getX()+"," + _tmpFC.getCoordinates().getY()+")  ");
					}
					else
					{
						if(_tmpFC.getDirtUnits() == 0)
						{
							sb.append("Clean (" + x +"," + y +")  ");
						}
						else
						{
							sb.append("Dirty (" + x +"," + y +")  ");
						}
					}
				}
				else
				{
					sb.append("Close (" + x +"," + y +")  ");
				}
				
			}
				
			sb.append("\n");
		}
		
		
		return sb.toString();
	}

    

}
