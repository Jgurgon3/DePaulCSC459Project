package edu.du.agile;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class SAXHandler extends DefaultHandler {

	  List<FloorCell> floorCellList = new ArrayList<>();
	  FloorCell floorCell = null;
	  String content = null;
	  @Override
	  //Triggered when the start of tag is found.
	  public void startElement(String uri, String localName, 
	                           String qName, Attributes attributes) 
	                           throws SAXException {
	      
	    switch(qName){
	      //Create a new Employee object when the start tag is found
	      case "cell":
	    	  floorCell = new FloorCell();
	    	  floorCell.setXSensor(Integer.parseInt(attributes.getValue("xs").trim()));
	    	  floorCell.setYSensor(Integer.parseInt(attributes.getValue("ys").trim()));
	    	  floorCell.setSurfaceSensor(Integer.parseInt(attributes.getValue("ss").trim()));
	    	  floorCell.setPathSensor(Integer.parseInt(attributes.getValue("ps").trim()));
	    	  floorCell.setDirtSensor(Integer.parseInt(attributes.getValue("ds").trim()));
	    	  floorCell.setChargingStation(Integer.parseInt(attributes.getValue("cs").trim()));

	        break;
	    }
	  }

	  @Override
	  public void endElement(String uri, String localName, 
	                         String qName) throws SAXException {
	   switch(qName){
	     //Add the employee to list once end tag is found
	     case "cell":
	    	 floorCellList.add(floorCell);       
	       break;
	    	   }
	  }

	  @Override
	  public void characters(char[] ch, int start, int length) 
	          throws SAXException {
	    content = String.copyValueOf(ch, start, length).trim();
	  }
	    
	}

