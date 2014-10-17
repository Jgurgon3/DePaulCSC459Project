package test.cleansweep.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import cleansweep.service.PowerService;
import CleanSweepModels.Point;
import CleanSweepModels.Robot;
import XMLParse.FloorCell;
import XMLParse.ParserFloorPlan;

public class FloorCellTest {

	@Test
	public void FindFirst() throws ParserConfigurationException, SAXException, IOException {
		
	List<FloorCell>floorCellList= populateCell();
	Point p = new Point(0, 0); 
	@SuppressWarnings("unchecked")
	List<FloorCell> floorCellFlistresult = (List<FloorCell>) FloorCell.filterFloorCell(floorCellList,FloorCell.FindByCordinate(p) );
	assertEquals(0, floorCellFlistresult.get(0).getCoordinates().getX());
	assertEquals(0, floorCellFlistresult.get(0).getCoordinates().getY());

	}
	@Test
	public void FindSecond() throws ParserConfigurationException, SAXException, IOException {
		
	List<FloorCell>floorCellList= populateCell();
	Point p = new Point(1, 0); 
	@SuppressWarnings("unchecked")
	List<FloorCell> floorCellFlistresult = (List<FloorCell>) FloorCell.filterFloorCell(floorCellList,FloorCell.FindByCordinate(p) );
	assertEquals(1, floorCellFlistresult.get(0).getCoordinates().getX());
	assertEquals(0, floorCellFlistresult.get(0).getCoordinates().getY());

	}
	
	private List<FloorCell> populateCell()
	{
		List<FloorCell> floorCellFlist=	new ArrayList<FloorCell>();
		FloorCell fc1 = new FloorCell();
		fc1.setXCoordinates(0);
		fc1.setYCoordinates(0);
		
		
		FloorCell fc2 = new FloorCell();
		fc2.setXCoordinates(1);
		fc2.setYCoordinates(0);
		
		
		FloorCell fc3 = new FloorCell();
		fc3.setXCoordinates(2);
		fc3.setYCoordinates(0);
		
		
		floorCellFlist.add(fc1);
		floorCellFlist.add(fc2);
		floorCellFlist.add(fc3);
		
		return floorCellFlist;
		
	}
}
