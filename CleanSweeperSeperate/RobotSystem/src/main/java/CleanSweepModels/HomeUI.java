package src.main.java.CleanSweepModels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Base64Data;

import src.main.java.*;
import src.main.java.XMLParse.FloorCell;
import src.main.java.XMLParse.FloorPlan;
import src.main.java.XMLParse.FloorTypes;
import src.main.java.XMLParse.ParserFloorPlan;
import src.main.java.XMLParse.Point;
class Base64
{
 
    public static byte[] decode(String data)
    {
        int[] tbl = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
            55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2,
            3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        byte[] bytes = data.getBytes();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length; ) {
            int b = 0;
            if (tbl[bytes[i]] != -1) {
                b = (tbl[bytes[i]] & 0xFF) << 18;
            }
            // skip unknown characters
            else {
                i++;
                continue;
            }
 
            int num = 0;
            if (i + 1 < bytes.length && tbl[bytes[i+1]] != -1) {
                b = b | ((tbl[bytes[i+1]] & 0xFF) << 12);
                num++;
            }
            if (i + 2 < bytes.length && tbl[bytes[i+2]] != -1) {
                b = b | ((tbl[bytes[i+2]] & 0xFF) << 6);
                num++;
            }
            if (i + 3 < bytes.length && tbl[bytes[i+3]] != -1) {
                b = b | (tbl[bytes[i+3]] & 0xFF);
                num++;
            }
 
            while (num > 0) {
                int c = (b & 0xFF0000) >> 16;
                buffer.write((char)c);
                b <<= 8;
                num--;
            }
            i += 4;
        }
        return buffer.toByteArray();
    }
}
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
    	String imgRbt = "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";
    	try
    	{
        	byte[] btDataFile = Base64.decode(imgRbt);
        	image = ImageIO.read(new ByteArrayInputStream(btDataFile));
    	}
    	catch(Exception x)
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
	    	if (args.length > 0) {

				if (isValidXML(args[0]))
					path = args[0];
				else {
					System.out.println("Invalid file path. Path must be to a valid XML file");
					System.exit(0);
				}
			}
	    	
	    	final FloorPlan fp = startRobot(path);
	    	final Robot r  = new Robot(0,0,fp);
	    	
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
		FloorPlan fp = (ParserFloorPlan
				.runParser(path));
		
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