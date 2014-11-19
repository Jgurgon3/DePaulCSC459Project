package src.main.java.XMLParse;

import src.main.java.XMLParse.Point;
//import src.main.java.CleanSweepModels.Robot;
//import src.main.java.CleanSweepModels.Types.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.lang.StringBuilder;

public class FloorPlan {

	private ChargingStation _chargingStation;

	private boolean _foundDirtyCell = true;
	private ArrayList<ArrayList<FloorCell>> _prevBreadCrumbs = new ArrayList<ArrayList<FloorCell>>();

	private Map<Point, FloorCell> _data = new HashMap<Point, FloorCell>();

	// public access methods
	public int xFloorPlanDim() {
		int xPrevMax = 0;
		for (Map.Entry<Point, FloorCell> row : this._data.entrySet()) {
			if (row.getKey().getX() > xPrevMax) {
				xPrevMax = row.getKey().getX();
			}
		}
		return xPrevMax + 1;
	}

	public int yFloorPlanDim() {
		int yPrevMax = 0;
		for (Map.Entry<Point, FloorCell> row : this._data.entrySet()) {
			if (row.getKey().getY() > yPrevMax) {
				yPrevMax = row.getKey().getY();
			}
		}
		return yPrevMax + 1;
	}

	public FloorPlan() {
	}

	public boolean getFoundDirtyCell() {
		return this._foundDirtyCell;
	}

	public void setFoundDirtyCell(boolean flag) {
		this._foundDirtyCell = flag;
	}

	public void AddCell(FloorCell fc) {
		Point cellCoordinates = new Point(fc.getCoordinates().getX(), fc
				.getCoordinates().getY());

		this._data.put(cellCoordinates, fc);
	}

	public void addPreviousBreadCrumb(ArrayList<FloorCell> lFc) {
		this._prevBreadCrumbs.add(lFc);
	}

	public ArrayList<ArrayList<FloorCell>> getPreviousBreadCrumb() {
		return this._prevBreadCrumbs;
	}

	public Map<Point, FloorCell> getFloorPlanData() {
		return this._data;
	}

	public ChargingStation getChargingStation() {
		return this._chargingStation;
	}

	public void setChargingStation() {
		this._chargingStation = new ChargingStation(0, 0);
	}

	public boolean floorPlanIsCleaned() {
		for (FloorCell cell : this.getFloorPlanData().values()) {
			if (!cell.alreadyCleaned()) {
				return false;
			}
		}
		return true;

	}

	public FloorCell getCellByPoint(Point point) {
		FloorCell fc = this.getFloorPlanData().get(point);
		return fc;
	}

}
