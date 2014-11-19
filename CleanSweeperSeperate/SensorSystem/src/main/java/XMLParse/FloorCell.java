package src.main.java.XMLParse;

import java.util.ArrayList;
import java.util.List;

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

	public Boolean alreadyCleaned() {
		return cleaned;
	}

	public void setVisited() {
		this._visited++;
		this._visitedTime = System.nanoTime();
	}

	public int getVisited() {
		return this._visited;
	}

	public long getVisitedTime() {
		return this._visitedTime;
	}

	public void setCleaned(Boolean cleaned) {
		this.cleaned = cleaned;
	}

	public void setXCoordinates(int _xCoor) {
		this.xCoor = _xCoor;
	}

	public void setYCoordinates(int _yCoor) {
		this.yCoor = _yCoor;
	}

	public Point getCoordinates() {
		return new Point(this.xCoor, this.yCoor);
	}

	public FloorTypes getFloorType() {
		return _floorType;
	}

	public void setFloorType(FloorTypes _floorType) {
		this._floorType = _floorType;
	}

	public String getPathSensor() {
		return pathSensor;
	}

	public void setPathSensor(String _pathSensor) {
		this.pathSensor = _pathSensor;
	}

	public int getDirtUnits() {
		return dirtUnits;
	}

	public void setDirtUnits(int _dirtUnits) {
		this.dirtUnits = _dirtUnits;
	}

	public int getChargingStation() {
		return chargingStation;
	}

	public void setChargingStation(int _chargingStation) {
		this.chargingStation = _chargingStation;
	}

	public FloorObstructions getNorthObstructions() {
		return this._northObstructions;
	}

	public FloorObstructions getSouthObstructions() {
		return this._southObstructions;
	}

	public FloorObstructions getEastObstructions() {
		return this._eastObstructions;
	}

	public FloorObstructions getWestObstructions() {
		return this._westObstructions;
	}

	public void setNorthObstructions(FloorObstructions val) {
		this._northObstructions = val;
	}

	public void setSouthObstructions(FloorObstructions val) {
		this._southObstructions = val;
	}

	public void setEastObstructions(FloorObstructions val) {
		this._eastObstructions = val;
	}

	public void setWestObstructions(FloorObstructions val) {
		this._westObstructions = val;
	}

	public void Clean() {
		int x = 10;
		if (this.getCoordinates().equals(new Point(1, 8))) {
			x++;
		}
		if (this.getDirtUnits() == 0)
			this.setCleaned(true);
		else {
			this.setDirtUnits(this.getDirtUnits() - 1);
			if (this.getDirtUnits() == 0)
				this.setCleaned(true);
		}

	}

	public int compareTo(FloorCell fc) {

		if (fc.alreadyCleaned() && !this.alreadyCleaned()) {
			return -1;
		} else if (!fc.alreadyCleaned() && this.alreadyCleaned()) {
			return 1;
		}

		else {

			if (fc.getVisited() > this.getVisited()) {
				return -1;
			} else if (fc.getVisited() < this.getVisited()) {
				return 1;
			} else {
				if (fc.getVisitedTime() > this.getVisitedTime()) {
					return -1;
				} else if (fc.getVisitedTime() < this.getVisitedTime()) {
					return -1;
				} else
					return 0;
			}

		}
	}

	@Override
	public String toString() {
		return "x:" + xCoor + "   " + "y:" + yCoor + "   " + "surface:"
				+ this._floorType.name() + "   " + "path:" + pathSensor + "   "
				+ "dirt:" + dirtUnits + "   " + "chargingStation:"
				+ chargingStation;
	}

}