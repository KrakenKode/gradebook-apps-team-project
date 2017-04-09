package model;

import java.io.Serializable;

public class Grade implements Serializable {

	private String name;
	private int points;
	private int maxPoints;
	private String comment;
	private double predicted;
	
	public Grade(String name, int points, int maxPoints, String comment) {
		this.name = name;
		this.points = points;	
		this.maxPoints = maxPoints;
		this.comment = comment;
		this.predicted = 0;
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
	
	public double getPredicted(){
		return predicted;
	}
	
	public void setPredicted(int predicted){
		this.predicted = predicted;
	}
	
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
	
	public String getComment() {
		return comment;
	}

	
}
