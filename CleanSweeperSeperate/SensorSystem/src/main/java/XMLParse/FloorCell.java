package src.main.java.XMLParse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//import src.main.java.CleanSweepModels.*;





public class FloorCell implements Comparable<FloorCell> {
	 private int xCoor;
	 private int yCoor;
	 private FloorTypes _floorType;
	 private String pathSensor;
	 private int dirtUnits;
	 private int chargingStation;
	 private FloorObstructions _northObstructions;
	 private FloorObstructions _southObstructions;
	 private FloorObstructions _eastObstructions;
	 private FloorObstructions _westObstructions;
	 private boolean cleaned = false;
	 private int _visited = 0;
	 private long _visitedTime;
	 
	    public Boolean alreadyCleaned() 
	    {
	    	return cleaned;
	    }
	    public void setVisited()
	    {
	    	this._visited++;
	    	this._visitedTime = System.nanoTime();
	    }
	    public int getVisited()
	    {
	    	return this._visited;
	    }
	    public long getVisitedTime()
	    {
	    	return this._visitedTime;
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
	    
	    public FloorTypes getFloorType() {
	        return _floorType;
	    }
	    public void setFloorType( FloorTypes _floorType) {
	        this._floorType=_floorType;
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
	    	int x = 10;
	    	if(this.getCoordinates().equals(new Point(1,8)))
	    	{
	    		x++;
	    	}
	    	if(this.getDirtUnits() == 0)
	    		this.setCleaned(true);
	    	else
	    	{
	    		this.setDirtUnits(this.getDirtUnits()-1);
	    		if(this.getDirtUnits() == 0)
		    		this.setCleaned(true);
	    	}	    	
	    	
	    }
	   // @Override
	    public int compareTo(FloorCell  fc)
        {

            if( fc.alreadyCleaned() && !this.alreadyCleaned())
            {
            	return -1;
            }
            else if(!fc.alreadyCleaned() && this.alreadyCleaned())
            {
            	return 1;
            }
            
            else
            {         
            	
            	if(fc.getVisited() > this.getVisited())
            	{
            		return -1;
            	}
            	else if(fc.getVisited() < this.getVisited())
            	{
            		return 1;
            	}
            	else
            	{
            		if(fc.getVisitedTime() > this.getVisitedTime())
            		{
            			return -1;
            		}
            		else if(fc.getVisitedTime() < this.getVisitedTime())
            		{
            			return -1;
            		}
            		else
            			return 0;
            	}
            		
//            	int xCharger = 0;
//            	int yCharger = 0;
//            	
//            	int x1 = fc1.getCoordinates().getX();
//            	int y1 = fc1.getCoordinates().getY();
//            	
//            	int x2 = this.getCoordinates().getX();
//            	int y2 = this.getCoordinates().getY();
//            	
//            	double x1Distance = Math.sqrt((x1-xCharger)*(x1-xCharger) + (y1-yCharger)*(y1-yCharger));
//            	double x2Distance = Math.sqrt((x2-xCharger)*(x2-xCharger) + (y2-yCharger)*(y2-yCharger));
//            	
//            	if(x1Distance > x2Distance)
//            	{
//            		return 1;
//            	}
//            	else if(x1Distance < x2Distance)
//            		return -1;
//            	else
//            	{
//            		int x = new Random(System.currentTimeMillis()).nextInt();
//	            	int y = 1;
//	            	if(x < 0)
//	            	{
//	            		y = -1;
//	            	}
//	            	y = y*x;
//	            	
//	            	return x/(y);
//            		return 0;
//            	}
            	
            }
        }
	    

	    
	  @Override
	  public String toString() {
	    return "x:"+ xCoor+"   "+"y:"+yCoor +"   "+"surface:"+this._floorType.name() +"   "+"path:"+pathSensor+"   "+"dirt:"+dirtUnits+"   "+"chargingStation:"+chargingStation;
	  }	
	 
	  
	  public static Predicate<FloorCell> FindByCordinate(Point searchPoint, List<FloorCell> floorList) {
//		  final List<FloorCell> selection = new ArrayList<FloorCell>();
		  Predicate<FloorCell> cell2 = null;
		  for(FloorCell cell : floorList){
		  if(cell.getCoordinates().getX()==searchPoint.getX() &&   cell.getCoordinates().getY()==searchPoint.getY())
			  cell2 = (Predicate<FloorCell>) cell; 
		  } 
		 
		return cell2;
	  }
	  
	  public static List<FloorCell> filterFloorCell (List<FloorCell> floorcells, Predicate<FloorCell> predicate) {
		  List<FloorCell> lst =floorcells.stream().filter( predicate ).collect(Collectors.<FloorCell>toList()); 
	        return lst;
	    }
	  
	  public static FloorCell FindFloorCell (List<FloorCell> floorList,Point p ) {
			List<FloorCell> floorCellFlistresult = (List<FloorCell>) FloorCell.filterFloorCell(floorList,FloorCell.FindByCordinate(p, floorList) );
			if(floorCellFlistresult!=null && floorCellFlistresult.size()>0)
			{
				return floorCellFlistresult.get(0);
				}
			else
				return null;
	    }
}