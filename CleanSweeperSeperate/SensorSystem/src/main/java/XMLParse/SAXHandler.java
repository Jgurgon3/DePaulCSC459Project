package src.main.java.XMLParse;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	List<FloorCell> floorCellList = new ArrayList<>();
	FloorCell floorCell = null;
	String content = null;
	FloorPlan fp = new FloorPlan(); // this may change

	@Override
	// Triggered when the start of tag is found.
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
		case "cell":
			floorCell = new FloorCell();
			floorCell.setXCoordinates(Integer.parseInt(attributes
					.getValue("xs").trim()));
			floorCell.setYCoordinates(Integer.parseInt(attributes
					.getValue("ys").trim()));
			floorCell.setFloorType(FloorTypes.setValue(attributes
					.getValue("ss").trim()));
			floorCell.setPathSensor((attributes.getValue("ps").trim()));
			floorCell.setDirtUnits(Integer.parseInt(attributes.getValue("ds")
					.trim()));
			floorCell.setChargingStation(Integer.parseInt(attributes.getValue(
					"cs").trim()));
			floorCell.setCleaned(false);
			floorCell.setEastObstructions(FloorObstructions.setValue(Character
					.toString(attributes.getValue("ps").charAt(0))));
			floorCell.setWestObstructions(FloorObstructions.setValue(Character
					.toString(attributes.getValue("ps").charAt(1))));
			floorCell.setNorthObstructions(FloorObstructions.setValue(Character
					.toString(attributes.getValue("ps").charAt(2))));
			floorCell.setSouthObstructions(FloorObstructions.setValue(Character
					.toString(attributes.getValue("ps").charAt(3))));
			this.fp.AddCell(floorCell);
			// moved below so robot can store cell in memory when instantiated
			if (Integer.parseInt(attributes.getValue("cs").trim()) == 1) {
				int xCoor = Integer.parseInt(attributes.getValue("xs").trim());
				int yCoor = Integer.parseInt(attributes.getValue("ys").trim());
				this.fp.setChargingStation();
			}
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		switch (qName) {
		case "cell":
			floorCellList.add(floorCell);
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		content = String.copyValueOf(ch, start, length).trim();
	}
}
