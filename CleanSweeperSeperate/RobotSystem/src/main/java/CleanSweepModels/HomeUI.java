package src.main.java.CleanSweepModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import src.main.java.*;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.FloorTypes;
import src.main.java.XMLParse.ParserFloorPlan;
import src.main.java.XMLParse.Point;
class DrawPanel extends JPanel 
{

	private FloorPlan _fp;
	private Robot _r;
	public DrawPanel(FloorPlan fp, Robot r )
	{
		this._fp = fp;
       	this._r = r;
	}
	public void runAnimation()
	{
		while(this._fp.floorPlanIsCleaned() == false)
		{
	   		setPreferredSize(new Dimension(600, 600));
	   		
			_r.MoveRobot();
			paintImmediately(0, 0, getWidth(), getHeight());
			try 
			{ 
	            Thread.sleep(50);
	        }
	        catch (Exception e) 
			{
	        	
	        }
			
		}
		_r.returnToCharger();

	}
	@Override    
    public void paint(Graphics g)
    {
    	Graphics2D g2d = (Graphics2D) g;
        g.clearRect(0, 0, getWidth(), getHeight() );

        int squareWidth = 50;
        int intialPosition = 10;
        Image image = null;
        try
        {
        	image = ImageIO.read(getClass().getResource("Robot.png"));
        }
        catch(IOException e)
        {
        	
        }
        g2d.setColor(new Color(212, 212, 212));
        
        for(int y=this._fp.yFloorPlanDim()-1;y>=0;y--)
		{
			for(int x=0;x<=this._fp.xFloorPlanDim()-1;x++)
			{

				FloorCell _tmpFC = this._fp.getCellByPoint(new Point(x,y));
				if(_tmpFC != null)
				{
					int alpha;					
					if(_tmpFC.getFloorType() == FloorTypes.BARE)
						alpha = 50;
					else if(_tmpFC.getFloorType() == FloorTypes.LOW)
						alpha = 100;
					else
						alpha = 200;
					if(_tmpFC.getCoordinates().equals(this._fp.getChargingStation().getCoordinates()))
					{
						//charger
		        		g2d.setColor(new Color(21,0,255));
		        		g2d.fillRect(intialPosition + (x * squareWidth), intialPosition + (y * squareWidth), squareWidth, squareWidth);

		        		
					}
					if(this._r.getCoordinates().equals(_tmpFC.getCoordinates()))
					{
						
						//robot
						g2d.drawImage(image, intialPosition + (x * squareWidth), intialPosition + (y * squareWidth), this);
					}
					else
					{
						if(_tmpFC.getDirtUnits() == 0)
						{
							//clean cell
							g2d.setColor(new Color(0,255,0,alpha));
							g2d.fillRect(intialPosition + (x * squareWidth), intialPosition + (y * squareWidth), squareWidth, squareWidth);
						}
						else
						{
							//dirty
		
							g2d.setColor(new Color(255,0,0,alpha));
							g2d.fillRect(intialPosition + (x * squareWidth), intialPosition + (y * squareWidth), squareWidth, squareWidth);
						}
					}
				}
				else
				{
					// closet
					g2d.setColor(new Color(217,217,217));
					g2d.fillRect(intialPosition + (x * squareWidth), intialPosition + (y * squareWidth), squareWidth+1, squareWidth+1);

				}
			}
		}
    	
    }
}

public class HomeUI {

    public HomeUI(FloorPlan fp, Robot r) 
    { 
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		mainFrame.setSize(600, 600);
		mainFrame.setTitle("Clean Sweep");
		DrawPanel dp = new DrawPanel(fp,r);
		mainFrame.add(dp);
		mainFrame.setVisible(true);
		dp.runAnimation();
    }


    public static void main(String[] args) {

    	String path = "BIGxml.xml";
  
    	try
    	{
	   // 	if (args.length > 0) {
	   // 		System.out.println("wdf");
	//			if (isValidXML(args[0]))
		//			path = args[0];
		//	}
	    	
	    	FloorPlan fp = startRobot(path);
	    	Robot r  = new Robot(0,0,fp);
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	HomeUI ex = new HomeUI(fp,r);
	            }
	        });
	        
    	}
    	catch(Exception exp)
    	{
    		
    		System.out.println(exp.getMessage());
    	}

		
    }
    public static FloorPlan startRobot(String path) throws ParserConfigurationException, SAXException, IOException {
    	InputStream in = HomeUI.class.getResourceAsStream(path);
		FloorPlan fp = (ParserFloorPlan
				.runParser(in));
		
//		System.out.println(fp.getRobot().toString());
//		System.out.println(fp.toString());

		return fp;
		//System.out.println(fp.toString());
		//System.out.println("Floor is clean");
		//fp.getRobot().dumpLog();
	}

	// returns false if not a existing XML file
	public static boolean isValidXML(String path) throws IOException {

		File f = new File(path);
		//String workingDir = System.getProperty("user.dir");
		//File f2 = new File(workingDir + "/" + path);
		       
		if (f.exists() && !f.isDirectory()) {
			// check if XML
			String extension = "";

			int i = path.lastIndexOf('.');
			if (i > 0) {
				extension = path.substring(i + 1);
				if (extension.equalsIgnoreCase("XML"))
					return true;
			}
			return false;
		} else
			return false;
	}
}