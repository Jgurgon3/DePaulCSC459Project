package XMLParse;

import CleanSweepModels.Types.*;

public class FloorCell{
	 private int xSensor;
	 private int ySensor;
	 private int surfaceSensor;
	 private String pathSensor;
	 private int dirtSensor;
	 private int chargingStation;
	 private FloorObstructions _northObstructions;
	 private FloorObstructions _southObstructions;
	 private FloorObstructions _eastObstructions;
	 private FloorObstructions _westObstructions;
	 private Boolean clean;
	 
	    public Boolean getClean() {
		return clean;
	}
	public void setClean(Boolean clean) {
		this.clean = clean;
	}
		public int getXSensor() {
	        return xSensor;
	    }
	    public void setXSensor( int _xSensor) {
	        this.xSensor=_xSensor;
	    }

	    public int getYSensor() {
	        return ySensor;
	    }
	    public void setYSensor( int _ySensor) {
	        this.ySensor=_ySensor;
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
	    return "x:"+ xSensor+"   "+"y:"+ySensor +"   "+"surface:"+surfaceSensor +"   "+"path:"+pathSensor+"   "+"dirt:"+dirtSensor+"   "+"chargingStation:"+chargingStation;
	  }	
	 
		 
}