package cleansweep.service;

import java.util.ArrayList;
import java.util.List;

import XMLParse.FloorCell;



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
	
	
	
}
