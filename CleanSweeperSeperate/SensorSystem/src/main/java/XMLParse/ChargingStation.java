package src.main.java.XMLParse;

public class ChargingStation {
	private Point _coordinates;

	public ChargingStation(int xCoor, int yCoor) {
		this._coordinates = new Point(xCoor, yCoor);
	}

	public Point getCoordinates() {
		return this._coordinates;
	}

	public String toString() {
		return ("Coordinates of Charging Station: ("
				+ Integer.toString(this.getCoordinates().getX()) + ","
				+ Integer.toString(this.getCoordinates().getY()) + ")");
	}

}
