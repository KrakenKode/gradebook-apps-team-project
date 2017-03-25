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
