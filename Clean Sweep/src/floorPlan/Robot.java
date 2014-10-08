package floorPlan;
import java.awt.Point;
import java.util.Scanner;

import floorPlan.Floor; 


public class Robot {
	Point position;
	Point startPosition;
	
	
	
	public Point get_position(){
		return this.position;
	}
	
	public void set_position(int x, int y){
		position = new Point(x, y);
	}
	
	public Point get_startPosition(){
		return this.startPosition;
	}
	
	public void set_startPosition (Point a){
		startPosition = new Point(a);
	}
	
	public void move(int x, int y){
		
		if(((y == position.getY()) && x == (position.getX() + 1) || (x == (position.getX() - 1)) && (Floor.points).contains(position))){
			Floor.remove(position);
			position = new Point(x, y);
			
		}
		else if((x == position.getX()) && (y == (position.getY() + 1) || (y == (position.getY() - 1))&& (Floor.points).contains(position))){
			Floor.remove(position);
			position = new Point(x, y);
		}
		else
			System.out.println("Can't move there. " + "Current position is " + position);
	}

	
	public static void main(String[] args){
		Robot r = new Robot();
		r.set_startPosition(new Point (0, 0));
		r.set_position(0, 0);
		System.out.println("Start position is " + r.startPosition.toString());
		System.out.println("Current position is " + r.position);
		r.move(1, 0);
		System.out.println("Current position is " + r.position);
		r.move(-1, 1);
		System.out.println("Current position is " + r.position);
		r.move(1, 1);
		System.out.println("Current position is " + r.position);
		r.move(0, 1);
		System.out.println("Current position is " + r.position);
		r.move(-1, 1);
		System.out.println("Current position is " + r.position);
		r.move(-1, 0);
		System.out.println("Current position is " + r.position);
		r.move(-1, -1);
		System.out.println("Current position is " + r.position);
		r.move(0,  -1);
		System.out.println("Current position is " + r.position);
		r.move(1, 1);
		System.out.println("Current position is " + r.position);
		r.move(1, -1);
		System.out.println("Current position is " + r.position);
		r.move(-1, 1);
		System.out.println("Current position is " + r.position);
		System.out.println("Start position is " + r.startPosition);
		
	}
	
	
}

