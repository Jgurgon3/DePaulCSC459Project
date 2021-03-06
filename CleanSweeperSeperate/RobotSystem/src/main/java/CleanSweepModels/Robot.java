package src.main.java.CleanSweepModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.main.java.CleanSweepModels.RobotLog.LogActivityTypes;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorObstructions;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.Point;

public class Robot {
    private final static Logger logger = Logger.getLogger(Robot.class.getName());
	private Point coordinates;
	private double power = 50;
	private int dirtCollected = 0;
	private int totalDirtCollected = 0;
	private boolean returnToChargerFlag = false;
	private FloorPlan floorPlan;
	private HashMap<Point, FloorCell> memory = new HashMap<Point, FloorCell>();
	private RobotLog log = new RobotLog();
	private double breadcrumbPowerNeeded = 0;
	private final static int maxAllowableDirt = 50;
	private ArrayList<FloorCell> breadCrumb = new ArrayList<FloorCell>();

	public Point getCoordinates() {
		return this.coordinates;
	}

	public void MoveRobot() {
		if (floorPlan.floorPlanIsCleaned()) {
			returnToCharger();
			return;
		}
		List<FloorCell> movePossiblities = this
				.getMovePossiblities(this.floorPlan
                        .getCellByPoint(getCoordinates()));
		floorPlan.setFoundDirtyCell(false);
		for (final FloorCell cell : movePossiblities) {
			if (CanMove(cell)) {
				this.Move(cell, true);
				cell.setVisited();
				while (!cell.alreadyCleaned()) {
					floorPlan.setFoundDirtyCell(true);
					Clean(cell);
					floorPlan.AddCell(cell); // this updates the cells
												// attributes.
				}
				System.out.println(this.toString());
				if (getReturnToChargerFlag()) {
					returnToCharger();
				}
				break;
			} else {
				returnToCharger();
				break;
			}

		}

	}

	public void returnToCharger() {
		for (int i = getBreadCrumb().size() - 1; i >= 0; i--) {
			Move(getBreadCrumb().get(i), false);
			System.out.println(this.toString());
		}
		resetBreadCrumbPowerNeeded();
		ChargeAndEmpty();
	}

	public FloorPlan getFloorPlan() {
		return this.floorPlan;
	}

	private void addBreadCrumb(FloorCell fc) {
		this.breadCrumb.add(fc);
	}

	public ArrayList<FloorCell> getBreadCrumb() {
		return this.breadCrumb;
	}

	public void addToDirtCollected() {
		this.dirtCollected++;
	}

	public int getDirtCollected() {
		return this.dirtCollected;
	}

	public void resetBreadCrumbPowerNeeded() {
		this.breadcrumbPowerNeeded = 0;
	}

	public void addToBreadCrumbPowerNeeded(Point point) {
		this.breadcrumbPowerNeeded += this.calculatePowerToMove(point);
	}

	public void subtractFromBreadCrumbPowerNeeded(Point point) {
		this.breadcrumbPowerNeeded -= this.calculatePowerToMove(point);
	}

	public double getBreadCrumbPowerNeeded() {
		return this.breadcrumbPowerNeeded;
	}

	private void setFloorPlan(FloorPlan fp) {
		this.floorPlan = fp;
	}

	public boolean getReturnToChargerFlag() {
		return this.returnToChargerFlag;
	}

	private void setReturnToChargerFlag(boolean bool) {
		if (bool) {
			log.addLog(LogActivityTypes.RETURNTOCHARGER,
                    "Returning to charger station");
		}
		this.returnToChargerFlag = bool;
	}

	public double getPower() {
		int x;
		if (this.power < 0) {
			x = 10;
		}
		return this.power;
	}

	public void ChargeAndEmpty() {
		this.power = 50.0;
		this.totalDirtCollected += this.dirtCollected;
		this.dirtCollected = 0;
		this.setReturnToChargerFlag(false);
		this.breadCrumb.clear();
	}

	private void addChargerToBreadCrumb() {
		this.addBreadCrumb(this.getFloorPlan().getCellByPoint(new Point(0, 0)));
	}

	public boolean CanClean(Point point) {
		return this.hasEnoughPower(point) && canStoreMoreDirt();
	}

	public void Clean(FloorCell fc) {
		if (this.getCoordinates().equals(fc.getCoordinates())) {// only clean
																// cells that we
																// are currently
																// at {
			fc.Clean();
			this.addToDirtCollected();
			log.addLog(LogActivityTypes.CLEANED, "Cleaned unit of dirt at: "
                    + fc.getCoordinates().toString() + " with floor type "
                    + fc.getFloorType().name());
		}
	}

	public boolean CanMove(FloorCell fc) {
		if (!this.getReturnToChargerFlag()
				&& hasEnoughPower(fc.getCoordinates())) {
			return true;
		} else {
            return false;
        }
	}

	public void Move(FloorCell fc, boolean AddToBreadCrumb) {
		Point currentCoor = this.getCoordinates();
		// Change by Pravangsu on Nov 7, 2014, Check should be make before move
		if (Math.abs(currentCoor.getX() - fc.getCoordinates().getX()) > 1
				|| Math.abs(currentCoor.getY() - fc.getCoordinates().getY()) > 1) {
			throw new IllegalArgumentException(
					"Attempted to move two cells at once");
		}
		if (Math.abs(currentCoor.getX() - fc.getCoordinates().getX())
				+ Math.abs(currentCoor.getY() - fc.getCoordinates().getY()) > 1) {
			throw new IllegalArgumentException(
					"Attempted to move two cells at once (diagonally)");
		}
		this.power -= calculatePowerToMove(fc.getCoordinates());

		if (AddToBreadCrumb) {// && fc.alreadyCleaned() == false)

			// this means we are moving forward
			this.addBreadCrumb(this.getFloorPlan().getCellByPoint(currentCoor));
			this.addToBreadCrumbPowerNeeded(fc.getCoordinates());
		} else {
			this.subtractFromBreadCrumbPowerNeeded(fc.getCoordinates());
		}

		currentCoor.setX(fc.getCoordinates().getX());
		currentCoor.setY(fc.getCoordinates().getY());

		logMove(fc);

	}

	private void logMove(FloorCell fc) {
		memory.put(fc.getCoordinates(), fc);
		log.addLog(LogActivityTypes.MOVE, "Robot moved to cell: "
                + fc.getCoordinates().toString());
	}

	public double calculatePowerToMove(Point point) {

		if (this.getFloorPlan().getCellByPoint(point).alreadyCleaned()) {
			return .5;
		}
		return (this.getFloorPlan().getCellByPoint(this.getCoordinates())
				.getFloorType().getValue() + this.getFloorPlan()
				.getCellByPoint(point).getFloorType().getValue()) / 2;
	}

	private ArrayList<FloorCell> getMovePossiblities(FloorCell fc) {

		ArrayList<FloorCell> possibleCells = new ArrayList<>();

		if (fc != null) {

			if (fc.getWestObstructions() == FloorObstructions.OPEN) {

				FloorCell possibleCell = getFloorPlan().getCellByPoint(
						new Point(getCoordinates().getX() - 1, getCoordinates()
								.getY()));
				if (fc != null) {
					possibleCells.add(possibleCell);
				}
			}
			if (fc.getEastObstructions() == FloorObstructions.OPEN) {

				FloorCell possibleCell = getFloorPlan().getCellByPoint(
						new Point(getCoordinates().getX() + 1, this
								.getCoordinates().getY()));
				if (fc != null) {

					possibleCells.add(possibleCell);
				}

			}
			if (fc.getNorthObstructions() == FloorObstructions.OPEN) {

				FloorCell possibleCell = getFloorPlan().getCellByPoint(
						new Point(getCoordinates().getX(), getCoordinates()
								.getY() + 1));
				if (fc != null) {
					possibleCells.add(possibleCell);
				}
			}

			if (fc.getSouthObstructions() == FloorObstructions.OPEN) {

				FloorCell possibleCell = getFloorPlan().getCellByPoint(
						new Point(getCoordinates().getX(), getCoordinates()
								.getY() - 1));
				if (fc != null) {
					possibleCells.add(possibleCell);
				}
			}
			long seed = System.nanoTime();
			if (!floorPlan.getFoundDirtyCell()) {
				Collections.shuffle(possibleCells, new Random(seed));
				Collections.sort(possibleCells);
			}

		}
		return possibleCells;
	}

	public Robot(int xCoor, int yCoor, FloorPlan fp) {
		Point p = new Point(xCoor, yCoor);

		this.setFloorPlan(fp);

		if (fp.getCellByPoint(p) == null) {

			throw new IllegalArgumentException(
					"Attempt to instantiate robot at invalid point");

		}

		this.coordinates = p;
		this.addChargerToBreadCrumb();
		while (this.CanClean(p)
				&& !this.getFloorPlan().getCellByPoint(p).alreadyCleaned()) {
			this.Clean(this.getFloorPlan().getCellByPoint(p));
			this.getFloorPlan().AddCell(this.getFloorPlan().getCellByPoint(p)); // this
																				// updates
																				// the
																				// cells
																				// attributes.
		}
		memory.put(new Point(xCoor, yCoor), fp.getCellByPoint(p));
		log.addLog(LogActivityTypes.WAKEUP,
                "Robot started at cell: " + p.toString());
	}

	public Map<Point, FloorCell> getMemory() {
		return memory;
	}

	public void dumpMemory() {

		Iterator<Point> iterator = memory.keySet().iterator();

		if (!iterator.hasNext()) {
            logger.log(Level.INFO, "Clean sweep memory is empty");

		}

		while (iterator.hasNext()) {
			Point key = iterator.next();
			FloorCell value = memory.get(key);

			String s = value.toString();
			System.out.println(s);
		}
	}

	public void dumpLog() {
		log.dumpLog();
	}

	private boolean hasEnoughPower(Point point) {
		double powerToMove = calculatePowerToMove(point);
		double forecastedPowerLeft = this.getPower() - powerToMove;
		if (forecastedPowerLeft > this.getBreadCrumbPowerNeeded()) {
			this.setReturnToChargerFlag(false);
			return true;
		} else {
			this.setReturnToChargerFlag(true);
			return false;
		}
	}

	public boolean canStoreMoreDirt() {
		if (this.getDirtCollected() < this.maxAllowableDirt) {
			return true;
		} else {
			this.setReturnToChargerFlag(true);
			return false;
		}
	}

	public String toString() {
		return ("Coordinates of Robot: ("
				+ Integer.toString(this.getCoordinates().getX()) + ","
				+ Integer.toString(this.getCoordinates().getY())
				+ ")\nRobot power: " + this.getPower() + "\nDirt collected: " + Integer
					.toString(this.totalDirtCollected));
	}

	public void Move(Point p) {

	}

	public Double getPowerForCellClean(FloorCell floorCell) {
		// 1 unit battery for cleaning bare floor
		// 2 unit battery for cleaning low pile carpet floor
		// 3 unit battery for cleaning high pile carpet floor

		// 1 The cell is bare floor.
		// 2 The cell is covered in low-pile carpet.
		// 4 The cell is covered in high-pile carpet.

		if (floorCell.alreadyCleaned() == true) {
			return 0.0; // No power required to clean, just move to next cell.
						// May be way back to cleaning after charge
		}
		Double powerUnit = 0.0;
		int floorTypes = floorCell.getFloorType().getValue();

		switch (floorTypes) {
		case 1:
			powerUnit = 1.0;
			break;
		case 2:
			powerUnit = 2.0;
			break;

		case 4:
			powerUnit = 3.0;
			break;

		}
		return powerUnit;
	}

	public Double getPowerForCellMoving(FloorCell floorCell, FloorCell prevCell) {
		// 1 unit of power for moving between bare floors to bare floor
		// 1.5 unit of power for moving between bare floor to low pile carpet
		// floor
		// 2 unit of power for moving between bare floor to high pile carpet
		// floor
		// 2.5 unit of power for) moving between low pile carpet floor to high
		// pile carpet floor
		// 3 unit of power for moving between high pile carpet floor to high
		// pile carpet floor
		if (prevCell == null) {
			return 0.0; // When we are at cell zero we need not move , just
						// celan it
		} else {
			Double powerUnitPrev = 0.0;
			Double powerUnitCurrent = 0.0;

			int floorTypes = prevCell.getFloorType().getValue();
			switch (floorTypes) {

			case 1:
				powerUnitPrev = .5;
				break;
			case 2:
				powerUnitPrev = 1.0;
				break;

			case 4:
				powerUnitPrev = 1.5;
				break;
			}
			int floorTypes2 = floorCell.getFloorType().getValue();
			switch (floorTypes2) {

			case 1:
				powerUnitCurrent = .5;
				break;
			case 2:
				powerUnitCurrent = 1.0;
				break;

			case 4:
				powerUnitCurrent = 1.5;
				break;

			}

			return powerUnitPrev + powerUnitCurrent;
		}
	}
}
