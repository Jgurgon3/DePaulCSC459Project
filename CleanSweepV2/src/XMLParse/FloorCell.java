package XMLParse;

import CleanSweepModels.Point;
import CleanSweepModels.Types.*;

public class FloorCell{
	 private int xCoor;
	 private int yCoor;
	 private int surfaceSensor;
	 private String pathSensor;
	 private int dirtSensor;
	 private int chargingStation;
	 private FloorObstructions _northObstructions;
	 private FloorObstructions _southObstructions;
	 private FloorObstructions _eastObstructions;
	 private FloorObstructions _westObstructions;
	 private boolean cleaned;
	 
	    public Boolean alreadyCleaned() {
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

	    public int getDirtSensor() {
	        return dirtSensor;
	    }
	    public void setDirtSensor( int _dirtSensor) {
	        this.dirtSensor=_dirtSensor;
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
	    

	    
	  @Override
	  public String toString() {
	    return "x:"+ xCoor+"   "+"y:"+yCoor +"   "+"surface:"+surfaceSensor +"   "+"path:"+pathSensor+"   "+"dirt:"+dirtSensor+"   "+"chargingStation:"+chargingStation;
	  }	
	 
		 
}