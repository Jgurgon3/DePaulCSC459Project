package test.cleansweep.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import cleansweep.service.PowerService;

import XMLParse.FloorCell;


public class PowerServiceTest {

	/*@Test
	public void GenericTest_TO_DELETE() throws ParserConfigurationException, SAXException, IOException {
	List<FloorCell> floorCellFlist=	ParserFloorPlan.FloorCellList("XMLParse/xml3x3.xml") ;
	PowerService powerService =  new PowerService(floorCellFlist, 50.0, null);
	powerService.Process();
	}*/
	@Test
	public void testThreeBareCarpet() throws ParserConfigurationException, SAXException, IOException {
	List<FloorCell> floorCellFlist= populateCell(3);
	floorCellFlist.get(0).setSurfaceSensor(1);
	floorCellFlist.get(1).setSurfaceSensor(1);
	floorCellFlist.get(2).setSurfaceSensor(1);
	PowerService powerService =  new PowerService(floorCellFlist, 50.0, null);
	powerService.Process();
	assertEquals(45.0, powerService.getPower(),0);
	}

	
	@Test
	public void testThreeLowPileCarpet() throws ParserConfigurationException, SAXException, IOException {
	List<FloorCell> floorCellFlist= populateCell(3);
	PowerService powerService =  new PowerService(floorCellFlist, 50.0, null);
	powerService.Process();
	assertEquals(40.0, powerService.getPower(),0);
	}
	@Test
	public void testThreeHighPileCarpet() throws ParserConfigurationException, SAXException, IOException {
	List<FloorCell> floorCellFlist= populateCell(3);
	floorCellFlist.get(0).setSurfaceSensor(4);
	floorCellFlist.get(1).setSurfaceSensor(4);
	floorCellFlist.get(2).setSurfaceSensor(4);
	PowerService powerService =  new PowerService(floorCellFlist, 50.0, null);
	powerService.Process();
	assertEquals(35.0, powerService.getPower(),0);
	}

	@Test
	public void testOneHighPileCarpet() throws ParserConfigurationException, SAXException, IOException {
	List<FloorCell> floorCellFlist= populateCell(1);
	floorCellFlist.get(0).setSurfaceSensor(4);
	PowerService powerService =  new PowerService(floorCellFlist, 50.0, null);
	powerService.Process();
	assertEquals(47.0, powerService.getPower(),0);
	}
	
	
	private List<FloorCell> populateCell( int count)
	{
		List<FloorCell> floorCellFlist=	new ArrayList<FloorCell>();
		for(int i=0; i<count ;i++)
		{
		
		FloorCell fc1 = new FloorCell();
		fc1.setXCoordinates(0);
		fc1.setYCoordinates(0);
		fc1.setSurfaceSensor(2); // Defualt to low pile Carpet
		floorCellFlist.add(fc1);
		
		}
		return floorCellFlist;
		
	}
}
