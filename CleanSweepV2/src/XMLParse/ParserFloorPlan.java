package XMLParse;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import CleanSweepModels.*;


public class ParserFloorPlan {

//	
		
		public static FloorPlan runParser(String fileName) throws ParserConfigurationException, SAXException, IOException {

			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler();
			parser.parse(ClassLoader.getSystemResourceAsStream(fileName),handler);	
						
			return handler._fp;
			

		}
		
//		public static List<FloorCell> FloorCellList(String fileName) throws ParserConfigurationException, SAXException, IOException {
//
//			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
//			SAXParser parser = parserFactor.newSAXParser();
//			SAXHandler handler = new SAXHandler();
//			parser.parse(ClassLoader.getSystemResourceAsStream(fileName),handler);	
//			return handler.floorCellList;
//
//		}

}




