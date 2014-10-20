package XMLParse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import CleanSweepModels.*;
import CleanSweepModels.Types.*;
import CleanSweepModels.Point;
import Dijkstra.WeightedGraph;
public class SAXHandler extends DefaultHandler {

	List<FloorCell> floorCellList = new ArrayList<>();
	FloorCell floorCell = null;
	String content = null;
	FloorPlan _fp = new FloorPlan(3,3); // this may change
	
	@Override
	//Triggered when the start of tag is found.
	public void startElement(String uri, String localName, 
			String qName, Attributes attributes) 
					throws SAXException {

		switch(qName){

		case "cell":
			floorCell = new FloorCell();
			floorCell.setXCoordinates(Integer.parseInt(attributes.getValue("xs").trim()));
			floorCell.setYCoordinates(Integer.parseInt(attributes.getValue("ys").trim()));
			floorCell.setSurfaceSensor(Integer.parseInt(attributes.getValue("ss").trim()));
			floorCell.setPathSensor((attributes.getValue("ps").trim()));
			floorCell.setDirtUnits(Integer.parseInt(attributes.getValue("ds").trim()));
			if(Integer.parseInt(attributes.getValue("cs").trim()) == 1)
			{
				int xCoor = Integer.parseInt(attributes.getValue("xs").trim());
				int yCoor = Integer.parseInt(attributes.getValue("ys").trim());
				this._fp.setRobot(xCoor, yCoor);
				this._fp.setChargingStation(xCoor, yCoor);
			}
			floorCell.setChargingStation(Integer.parseInt(attributes.getValue("cs").trim()));
			floorCell.setCleaned(false);
			floorCell.setEastObstructions(FloorObstructions.values()[Integer.parseInt(Character.toString(attributes.getValue("ps").charAt(0)))]);
			floorCell.setWestObstructions(FloorObstructions.values()[Integer.parseInt(Character.toString(attributes.getValue("ps").charAt(1)))]);
			floorCell.setNorthObstructions(FloorObstructions.values()[Integer.parseInt(Character.toString(attributes.getValue("ps").charAt(2)))]);
			floorCell.setSouthObstructions(FloorObstructions.values()[Integer.parseInt(Character.toString(attributes.getValue("ps").charAt(3)))]);
			
			this._fp.AddCell(floorCell);
			break;
		}
	}

	
	
	@Override
	public void endElement(String uri, String localName, 
			String qName) throws SAXException {
		switch(qName){
		//Add the employee to list once end tag is found
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