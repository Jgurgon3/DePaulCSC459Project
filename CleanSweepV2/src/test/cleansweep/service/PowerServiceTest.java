package test.cleansweep.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import cleansweep.service.PowerService;
import CleanSweepModels.Robot;
import XMLParse.FloorCell;
import XMLParse.ParserFloorPlan;

public class PowerServiceTest {

	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException {
	List<FloorCell> floorCellFlist=	ParserFloorPlan.FloorCelList("XMLParse/xml3x3.xml") ;
	PowerService powerService =  new PowerService(floorCellFlist, 50.0, null);
	powerService.Process();
	}
}
