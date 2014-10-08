package edu.du.agile;

public class FloorCell{
	 private int xSensor;
	 private int ySensor;
	 private int surfaceSensor;
	 private int pathSensor;
	 private int dirtSensor;
	 private int chargingStation;
	 
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

	    public int getPathSensor() {
	        return pathSensor;
	    }
	    public void setPathSensor( int _pathSensor) {
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

	    
	  @Override
	  public String toString() {
	    return "x:"+ xSensor+"   "+"y:"+ySensor +"   "+"surface:"+surfaceSensor +"   "+"path:"+pathSensor+"   "+"dirt:"+dirtSensor+"   "+"chargingStation:"+chargingStation;
	  }	
	 
		 
}