package src.test.java;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.junit.Test;
import org.xml.sax.SAXException;


import src.main.java.CleanSweepModels.*;
import src.main.java.XMLParse.*;




public class FloorCellTest extends TestCase{

	@Test
	public void testFindFirst() throws ParserConfigurationException, SAXException, IOException {
		
	List<FloorCell>floorCellList= populateCell();
	Point p = new Point(0, 0); 
	
	List<FloorCell> floorCellFlistresult = (List<FloorCell>) FloorCell.filterFloorCell(floorCellList,FloorCell.FindByCordinate(p) );
	assertEquals(0, floorCellFlistresult.get(0).getCoordinates().getX());
	assertEquals(0, floorCellFlistresult.get(0).getCoordinates().getY());

	}
	@Test
	public void testFindSecond() throws ParserConfigurationException, SAXException, IOException {
		
	List<FloorCell>floorCellList= populateCell();
	Point p = new Point(1, 0); 
	
	List<FloorCell> floorCellFlistresult = (List<FloorCell>) FloorCell.filterFloorCell(floorCellList,FloorCell.FindByCordinate(p) );
	assertEquals(1, floorCellFlistresult.get(0).getCoordinates().getX());
	assertEquals(0, floorCellFlistresult.get(0).getCoordinates().getY());

	}
	
	@Test
	public void testFindFloorCellByPoint() throws ParserConfigurationException, SAXException, IOException {
		
	List<FloorCell>floorCellList= populateCell();
	Point p = new Point(1, 0); 
	
	@SuppressWarnings("unchecked")
	FloorCell result = (FloorCell) FloorCell.FindFloorCell(floorCellList,p );
	assertEquals(1, result.getCoordinates().getX());
	assertEquals(0, result.getCoordinates().getY());

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
	@Test
	public void testCleanDirt() {
		FloorCell fc = createFloorCell(0,0);
		fc.setDirtUnits(2);
		
		assertFalse(fc.alreadyCleaned());
		assertEquals(fc.getDirtUnits(), 2);
		
		fc.Clean();
		
		assertFalse(fc.alreadyCleaned());
		
	}
	private FloorCell createFloorCell(int x, int y) {
		FloorCell fc = new FloorCell();
		fc.setXCoordinates(x);
		fc.setYCoordinates(y);
		return fc;
	}
}





