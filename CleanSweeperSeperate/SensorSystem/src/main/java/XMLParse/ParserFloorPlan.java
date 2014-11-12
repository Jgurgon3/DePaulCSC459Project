package src.main.java.XMLParse;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class ParserFloorPlan {

//	
		
		public static FloorPlan runParser(InputStream in) throws ParserConfigurationException, SAXException, IOException {
			System.out.println("In run parser");
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			SAXHandler handler = new SAXHandler();
			//parser.parse(fileName,handler);	
			parser.parse(in,handler);				
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




