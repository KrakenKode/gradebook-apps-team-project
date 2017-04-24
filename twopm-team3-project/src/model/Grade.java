package model;

import java.io.Serializable;

/**
 * Grade object contains the name and
 * point information of a particular grade.
 */
public class Grade implements Serializable {

	private String name;
	private int points;
	private int maxPoints;
	private double predictedGrade;
	private int numOfGradeToPredict = 0;

	
	public Grade(String name, int points, int maxPoints) {
		this.name = name;
		this.points = points;	
		this.maxPoints = maxPoints;

		if(this.points == -1){
			numOfGradeToPredict++;
		}
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
	
	public int getNumOfGradeToPredict(){
		return numOfGradeToPredict;
	}
	
	public void setPredictedGrade(double predictedGrade){
		this.predictedGrade = predictedGrade;
	}
	
	public double getPredictedGrade(){
		return predictedGrade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return this.points;
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
