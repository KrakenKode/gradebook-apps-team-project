package model;

import java.util.ArrayList;

public class GradeBookModel {
	
	private ArrayList<Semester> semesters;
	
	public GradeBookModel() {	
		semesters = openFile();	//initialize semesters upon program start
	}
	
	public ArrayList<Semester> openFile() {
		OpenDriver open = new OpenDriver();
		
		return open.getSemesters();
	}
	
	public void saveFile() {
		SaveDriver.saveFile(semesters);	
	}

	public ArrayList<Semester> getSemesters() {
		return semesters;
	}
	
	
}
