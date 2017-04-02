package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Semester implements Serializable {
	
	private String name;
	private ArrayList<Course> courses;
	
	public Semester(String name) {
		this.name = name;
		courses = new ArrayList<Course>();		
	}
	
	public void addCourse(Course newCourse) {
		courses.add(newCourse);
	}

	public void removeCourse(Course course) {
		courses.remove(course);
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

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	

}
