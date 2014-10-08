package floorPlan;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Floor{
    private Point point;
    private int ss;
    private int ps;
    private Point cs;
    private int ds;
	static ArrayList<Point> points = new ArrayList<Point>();

	
	
	public static void add(Point p) {
		points.add(p);                                                       
		
	}

	public static void remove(Point position) {
		points.remove(position);
	}

	public String printToString() {
		return points.toString();
	}
    
    public Point get_point(){
    	return this.point;
    }
    
    public void set_point(int x, int y){
    	point = new Point(x, y);
    	points.add(point);
    }
    
    public int get_surfaceSensor(){
    	return this.ss;
    }
    
    public void set_surfaceSensor(int s){
    	ss = s;
    }
    
    public int get_pathSensor(){
    	return this.ps;
    }
    
    public void set_pathSensor(int p){
    	ps = p;
    }
    
    public Point get_chargingStation(){
    	return this.cs;
    }
    
    public void set_chargingStation(){
    	cs = new Point (0,0);
    }
    
    public int get_dirtSensor(){
    	return this.ds;
    }
    
    public void set_dirtSensor(int d){
    	ds = d;
    }
    
    public void traverse(Point x, int path){
    	
    }
    
//	public static void main(String[] args){
//    	Scanner in = new Scanner (System.in);
//    	Floor f = new Floor();
//    	f.set_xAxis(0);
//    	f.set_yAxis(1);
//    	f.set_point();
//    	f.set_surfaceSensor(2);
//    	f.set_pathSensor(1212);
//    	f.set_chargingStation();
//    	f.set_dirtSensor(1);
//    	System.out.println("the X axis is " + f.get_xAxis());
//    	System.out.println("the Y axis is " + f.get_yAxis());
//    	System.out.println("the Point  is " + (f.get_point().toString()));
//    	System.out.println("the Surface Sensor is " + f.get_surfaceSensor());
//    	System.out.println("the Path is " + f.get_pathSensor());
//    	System.out.println("the Charging Station is at " + (f.get_chargingStation().toString()));
//    	System.out.println("the Dirt Sensor is " + f.get_dirtSensor());
//    	
//    	
//    	
//    }

}
