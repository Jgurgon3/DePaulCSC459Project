package XMLParse;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.ws.handler.HandlerResolver;

import org.xml.sax.SAXException;

import CleanSweepModels.Floor;
import CleanSweepModels.FloorPlan;
import CleanSweepModels.Point;
import Dijkstra.Dijkstra;
import Dijkstra.WeightedGraph;

public class ParserFloorPlan {

//	public static void main(String[] args) throws Exception {
		
		public static FloorPlan runParser(String fileName) throws ParserConfigurationException, SAXException, IOException {

			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler();
//			parser.parse(ClassLoader.getSystemResourceAsStream("XMLParse/xml3x3.xml"),handler);
			parser.parse(ClassLoader.getSystemResourceAsStream(fileName),handler);	
		
			return handler.fp;

		}
		
		public static List<FloorCell> FloorCelList(String fileName) throws ParserConfigurationException, SAXException, IOException {

			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler();
//			parser.parse(ClassLoader.getSystemResourceAsStream("XMLParse/xml3x3.xml"),handler);
			parser.parse(ClassLoader.getSystemResourceAsStream(fileName),handler);	
			return handler.floorCellList;

		}

		
	
		
		
		
//		handler.fp.MoveRobot(2, 1);
//		System.out.println(handler.fp.getRobot().toString()+ " Should be (2, 1)");
//		handler.fp.MoveRobot(1, -1); // Move the right 1 space and up 1 space
//		System.out.println(handler.fp.getRobot().toString()+ " Can't Move Here.  Should be (2, 1)");
//		handler.fp.MoveRobot(-2, 0); // Move the right 1 space and down 1 space
//		System.out.println(handler.fp.getRobot().toString()+ " Should be (0, 1)");
//		Floor floor = (Floor)handler.fp.getFloorPlanData().get(new Point(1,2));
//		System.out.println(floor.toString());

	}
//}




