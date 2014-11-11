package src.main.java.service;

import java.util.ArrayList;
import java.util.List;




import src.main.java.*;
import src.main.java.XMLParse.FloorCell;



public class RouteService {
	
	private List<FloorCell> floorCellList;
	
	public List<FloorCell> getFloorCellList() {
		return floorCellList;
	}

	public void setFloorCellList(List<FloorCell> floorCellList) {
		this.floorCellList = floorCellList;
	}

	public RouteService (List<FloorCell>  _floorCellList)
	{
		floorCellList=_floorCellList;
	}
	
	public  List<FloorCell> routeToChageStation( FloorCell currentPotion)
	{
		List<FloorCell>  routeList = new  ArrayList<FloorCell>();
		//TODO:
		return routeList;
	}
	/*
	 
	public  List<FloorCell> getShortrouteTo( FloorCell currentPotion, FloorCell toPosition)
	{
	   // getShortrouteTo is in progress
		List<FloorCell>  routeList = new  ArrayList<FloorCell>();
		int currentX =currentPotion.getCoordinates().getX();
		int currentY =currentPotion.getCoordinates().getY();
		FloorObstructions  WestObstructions = currentPotion.getWestObstructions();
		if(currentX>0)
		{
			FloorCell	fc= null;
			if(WestObstructions== FloorObstructions.OPEN)
			fc= FloorCell.FindFloorCell(floorCellList,new Point(currentX-1, currentX));
			else
			{
				// May be we in other room
				Boolean southOpen=false;
				do while (southOpen==true) {
					// West close , go east till you able  to go down
					fc= FloorCell.FindFloorCell(floorCellList,new Point(currentX+1, currentX));
					if(fc.getSouthObstructions()==FloorObstructions.OPEN)
					{
						southOpen=true;
					}
				} 
				
				
			}
		}
		
		return routeList;
	}
	*/
	
}
