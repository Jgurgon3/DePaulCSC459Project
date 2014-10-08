package edu.du.agile;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParserFloorPlan {
	
	public static void main(String[] args) throws Exception {
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
	    SAXParser parser = parserFactor.newSAXParser();
	    SAXHandler handler = new SAXHandler();
 	   parser.parse(ClassLoader.getSystemResourceAsStream("xml/FloorPlan.xml"),handler);
	    
	    //Printing the list of employees obtained from XML
	    for ( FloorCell fc : handler.floorCellList){
	      System.out.println(fc);
	    }

	  }
}

	


