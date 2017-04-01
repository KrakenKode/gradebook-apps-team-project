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
