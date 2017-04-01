package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {	

	private String name;
	private ArrayList<Grade> grades;

	
	public Category(String name) {
		this.name = name;
		grades = new ArrayList<Grade>();	
	}

	public void addGrade(Grade newGrade) {
		grades.add(newGrade);
	}
	
	public void removeGrade(Grade grade) {
		grades.remove(grade);
	}

	@Override
	public String toString() {
		return name;
	}
	
	//returns the % of earned points in the category as a double
	public double catRun(){
		double total = 0;
		int i = 0;
		for(Grade grade: getGrades()){
			total +=  grade.gradeRun();
			i = i + 100;
		}
		total = total/i;
		total = total * 100;
		return total;
	}
	/////////////Getters and Setters///////////////////

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Grade> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Grade> grades) {
		this.grades = grades;
	}
	
	
}
