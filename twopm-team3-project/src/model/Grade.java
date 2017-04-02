package model;

import java.io.Serializable;

public class Grade implements Serializable {

	private String name;
	private int points;
	private int maxPoints;
	
	public Grade(String name, int points, int maxPoints) {
		this.name = name;
		this.points = points;	
		this.maxPoints = maxPoints;
		}
	
	//Returns grade as a double in %
	public double gradeRun(){
		double total = 0;
		total = (double) getPoints() / (double) getMaxPoints();
		total = total*100;
		return total;
	}
	
	@Override
	public String toString() {
		return name;
	}
	/////////////Getters and Setters///////////////////
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	
}
