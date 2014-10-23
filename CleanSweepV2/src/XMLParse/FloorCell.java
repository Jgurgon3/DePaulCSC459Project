package XMLParse;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import CleanSweepModels.Point;
import CleanSweepModels.Types.*;

public class FloorCell{
	 private int xCoor;
	 private int yCoor;
	 private int surfaceSensor;
	 private String pathSensor;
	 private int dirtUnits;
	 private int chargingStation;
	 private FloorObstructions _northObstructions;
	 private FloorObstructions _southObstructions;
	 private FloorObstructions _eastObstructions;
	 private FloorObstructions _westObstructions;
	 private boolean cleaned = false;
	 
	    public Boolean alreadyCleaned() 
	    {
	    	return cleaned;
	    }
		public void setCleaned(Boolean cleaned) {
			this.cleaned = cleaned;
		}
	    public void setXCoordinates( int _xCoor) {
	        this.xCoor=_xCoor;
	    }

	    public void setYCoordinates( int _yCoor) {
	        this.yCoor=_yCoor;
	    }
	    
	    public Point getCoordinates()
	    {
	    	return new Point(this.xCoor,this.yCoor);
	    }
	    
	    public int getSurfaceSensor() {
	        return surfaceSensor;
	    }
	    public void setSurfaceSensor( int _surfaceSensor) {
	        this.surfaceSensor=_surfaceSensor;
	    }

	    public String getPathSensor() {
	        return pathSensor;
	    }
	    public void setPathSensor( String _pathSensor) {
	        this.pathSensor=_pathSensor;
	    }

	    public int getDirtUnits() {
	        return dirtUnits;
	    }
	    public void setDirtUnits( int _dirtUnits) {
	        this.dirtUnits=_dirtUnits;
	    }

	    public int getChargingStation() {
	        return chargingStation;
	    }
	    public void setChargingStation( int _chargingStation) {
	        this.chargingStation=_chargingStation;
	    }
	    public FloorObstructions getNorthObstructions()
	    {
	    	return this._northObstructions;
	    }
	    public FloorObstructions getSouthObstructions()
	    {
	    	return this._southObstructions;
	    }
	    public FloorObstructions getEastObstructions()
	    {
	    	return this._eastObstructions;
	    }
	    public FloorObstructions getWestObstructions()
	    {
	    	return this._westObstructions;
	    }

	    public void setNorthObstructions(FloorObstructions val)
	    {
	    	this._northObstructions = val;
	    }
	    public void setSouthObstructions(FloorObstructions val)
	    {
	    	this._southObstructions = val;
	    }
	    public void setEastObstructions(FloorObstructions val)
	    {
	    	this._eastObstructions = val;
	    }
	    public void setWestObstructions(FloorObstructions val)
	    {
	    	this._westObstructions = val;
	    }
	    public void Clean()
	    {
	    	if(this.getDirtUnits() == 0)
	    		this.setCleaned(true);
	    	else
	    	{
	    		this.setDirtUnits(this.getDirtUnits()-1);
	    		if(this.getDirtUnits() == 0)
		    		this.setCleaned(true);
	    	}	    	
	    	
	    }
	    

	    
	  @Override
	  public String toString() {
	    return "x:"+ xCoor+"   "+"y:"+yCoor +"   "+"surface:"+surfaceSensor +"   "+"path:"+pathSensor+"   "+"dirt:"+dirtUnits+"   "+"chargingStation:"+chargingStation;
	  }	
	 
	  public static Predicate<FloorCell> FindByCordinate(Point searchPoint) {
	        return p -> (p.getCoordinates().getX()==searchPoint.getX() &&   p.getCoordinates().getY()==searchPoint.getY());
	    } 
	  public static List<FloorCell> filterFloorCell (List<FloorCell> floorcells, Predicate<FloorCell> predicate) {
		  List<FloorCell> lst =floorcells.stream().filter( predicate ).collect(Collectors.<FloorCell>toList()); 
	        return lst;
	    }
	  
	  public static FloorCell FindFloorCell (List<FloorCell> floorList,Point p ) {
			List<FloorCell> floorCellFlistresult = (List<FloorCell>) FloorCell.filterFloorCell(floorList,FloorCell.FindByCordinate(p) );
			if(floorCellFlistresult!=null && floorCellFlistresult.size()>0)
			{
				return floorCellFlistresult.get(0);
				}
			else
				return null;
	    }
}