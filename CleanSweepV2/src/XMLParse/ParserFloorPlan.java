package XMLParse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import CleanSweepModels.Floor;
import CleanSweepModels.Point;

public class ParserFloorPlan {

	public static void main(String[] args) throws Exception {
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		SAXHandler handler = new SAXHandler();
		parser.parse(ClassLoader.getSystemResourceAsStream("XMLParse/xml3x3.xml"),handler);

		System.out.println(handler.fp.getRobot().toString() + " Should be (0, 0)");

		handler.fp.MoveRobot(2, 1);
		System.out.println(handler.fp.getRobot().toString()+ " Should be (2, 1)");
		handler.fp.MoveRobot(1, -1); // Move the right 1 space and up 1 space
		System.out.println(handler.fp.getRobot().toString()+ " Can't Move Here.  Should be (2, 1)");
		handler.fp.MoveRobot(-2, 0); // Move the right 1 space and down 1 space
		System.out.println(handler.fp.getRobot().toString()+ " Should be (0, 1)");

	}
}




