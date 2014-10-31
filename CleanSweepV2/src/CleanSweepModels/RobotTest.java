package CleanSweepModels;


import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


import junit.framework.TestCase;

public class RobotTest extends TestCase {
	public void testConstructorAndAttributes()
			throws ParserConfigurationException, SAXException, IOException {

		FloorPlan fp = (XMLParse.ParserFloorPlan
				.runParser("src/XMLParse/xml3x3.xml"));

		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);

		assertFalse(fp.getRobot().getReturnToChargerFlag());

		assertEquals(fp.getRobot().getPower(), 50.0);

	}

	public void testMovement() throws ParserConfigurationException,
			SAXException, IOException {
		FloorPlan fp = (XMLParse.ParserFloorPlan
				.runParser("src/XMLParse/xml3x3.xml"));

		// test east
		fp.getRobot().Move(new Point(1, 0));

		assertSame(fp.getRobot().getCoordinates().getX(), 1);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);

		// test west
		fp.getRobot().Move(new Point(0, 0));

		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);

		// test north
		fp.getRobot().Move(new Point(0, 1));
		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 1);

		// test south
		fp.getRobot().Move(new Point(0, 0));
		assertSame(fp.getRobot().getCoordinates().getX(), 0);
		assertSame(fp.getRobot().getCoordinates().getY(), 0);

		// test invalid
		try {
			fp.getRobot().Move(new Point(4, 4));
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			fp.getRobot().Move(new Point(3, 3));
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			fp.getRobot().Move(new Point(-1, -1));
			fail();
		} catch (IllegalArgumentException e) {
		}

		// test northeast
		try {
			fp.getRobot().Move(new Point(1,1));
		    fail( "Expected invalid movement exception" );
		} catch (IllegalArgumentException e) {
		}
		 assertSame(fp.getRobot().getCoordinates().getX(), 0);
		 assertSame(fp.getRobot().getCoordinates().getY(), 0);

		 fp.getRobot().Move(new Point(1,0));
		 fp.getRobot().Move(new Point(1,1));
		 assertSame(fp.getRobot().getCoordinates().getX(), 1);
		 assertSame(fp.getRobot().getCoordinates().getX(), 1);

		// test southwest
		 try {
			 	fp.getRobot().Move(new Point(0,0));
			    fail( "Expected invalid movement exception" );
			} catch (IllegalArgumentException e) {
			}
		 assertSame(fp.getRobot().getCoordinates().getX(), 1);
		 assertSame(fp.getRobot().getCoordinates().getY(), 1);

	} 

	public void testMemory() throws ParserConfigurationException, SAXException,
			IOException {
		FloorPlan fp = (XMLParse.ParserFloorPlan
				.runParser("src/XMLParse/xml3x3.xml"));

		assertSame(fp.getRobot().getMemory().size(), 1);

		fp.getRobot().Move(new Point(1, 0));
		assertSame(fp.getRobot().getMemory().get(new Point(1, 0)),
				fp.getCellByPoint(new Point(1, 0)));

		fp.getCellByPoint(new Point(0, 0));

		assertSame(fp.getRobot().getMemory().size(), 2);

		fp.getRobot().Move(new Point(1, 1));

		assertSame(fp.getRobot().getMemory().size(), 3);


		assertSame(fp.getRobot().getMemory().get(new Point(0, 0)),
				fp.getCellByPoint(new Point(0, 0)));
		assertSame(fp.getRobot().getMemory().get(new Point(1, 1)),
				fp.getCellByPoint(new Point(1, 1)));
		assertSame(fp.getRobot().getMemory().get(new Point(1, 0)),
				fp.getCellByPoint(new Point(1, 0)));

	}

}
