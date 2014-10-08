package floorPlan;
import java.awt.Point;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class xmlParse {

	public static void main(String[] args){
    	Floor f = new Floor();
    	f.set_surfaceSensor(2);
    	f.set_pathSensor(1212);
    	f.set_chargingStation();
    	f.set_dirtSensor(1);
    	f.add(f.get_chargingStation());
    	
    	System.out.println((f.points).toString());
    	
    	
    	
        f.set_point(0, 1);
        
        f.set_point(0, 2);
        
        f.set_point(1, 0);
        
        f.set_point(1, 1);
        
        f.set_point(1, 2);

        f.set_point(2, 0);
        
        f.set_point(2, 1);
        
        f.set_point(2, 2);

        
        
        
    	
    	System.out.println("the Surface Sensor is " + f.get_surfaceSensor());
    	System.out.println("the Path is " + f.get_pathSensor());
    	System.out.println("the Charging Station is at " + (f.get_chargingStation().toString()));
    	System.out.println("the Dirt Sensor is " + f.get_dirtSensor());
    	
    	
    	Robot r = new Robot();
		r.set_startPosition(f.get_chargingStation());
		r.set_position(0, 0);
		System.out.println("Start position is " + r.startPosition.toString());
		System.out.println("Current position is " + (r.position.toString()));
		
		r.move(0, 1); 
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(0, 2);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(1, 2);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(1, 1);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(2, 2);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(2, 1);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);

		r.move(0, 1);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(2, 0);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(1, 0);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		
		r.move(1, 1);
		System.out.println((f.points).size());
		System.out.println("Current position is " + r.position);
		


		
		
		
		System.out.println("Current position is " + r.position);
		System.out.println("Start position is " + r.startPosition);
		System.out.println("Charging Station position is " + f.get_chargingStation());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	//		public static void main(String[] args) throws IOException{
//			File file = new File("C:/Users/Jim/Desktop/DePaul/SE459/Project/xmlSmallTest.txt");
//			Scanner input = new Scanner(file);
//			Floor f = new Floor();
//			while(input.hasNext()) {
//			    String nextLine = input.next();
//			    if(nextLine.contains("xs=")){
//			    	if (nextLine.contains("0")){
//			    		f.set_xAxis(0);
//			    	}
//			    	else if (nextLine.contains("1")){
//			    		f.set_xAxis(1);
//			    	}
//			    	else if (nextLine.contains("-1")){
//			    		f.set_xAxis(-1);
//			    	}
//			    	System.out.println(f.get_xAxis());
//			    }
//			    if(nextLine.contains("ys=")){
//			    	if (nextLine.contains("0")){
//			    		f.set_yAxis(0);
//			    	}
//			    	else if (nextLine.contains("1")){
//			    		f.set_yAxis(1);
//			    	}
//			    	else if (nextLine.contains("-1")){
//			    		f.set_yAxis(-1);
//			    	}
//			    	System.out.println(f.get_yAxis());
//			    }
//			    
//			}
//			
//		}
