package src.main.java.XMLParse;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParserFloorPlan {

	

	public static FloorPlan runParser(String path)
			throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		SAXHandler handler = new SAXHandler();
		// parser.parse(ClassLoader.getSystemResourceAsStream(path),handler);
		parser.parse(path, handler);
		return handler._fp;

	}

}
