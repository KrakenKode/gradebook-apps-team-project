package model;

import java.io.*;
import java.util.ArrayList;

public class OpenDriver {
	
	private ArrayList<Semester> semesters;

	public OpenDriver() {
		semesters = new ArrayList<Semester>();
		readFile();
		
		
		//add a default semesters with courses, categories, and grades
		if(semesters.isEmpty()) {
			Semester template = new Semester("Default");
			
			Category newCategory;
			Course newCourse;
			
			newCourse = new Course("Systems");
			
			newCategory = new Category("Test");	
			newCategory.addGrade(new Grade("Test 1", 235, 250));
			newCategory.addGrade(new Grade("Test 2", 260, 300));
			newCourse.addCategory(newCategory);
						
			newCategory = new Category("Homework");
			newCategory.addGrade(new Grade("HW 1", 75, 100));
			newCategory.addGrade(new Grade("HW 2", 99, 100));
			newCourse.addCategory(newCategory);
			
			template.addCourse(newCourse);
					
			newCourse = new Course("Analysis of Algorithms");
			
			newCategory = new Category("Test");	
			newCategory.addGrade(new Grade("Test 1", 235, 250));
			newCategory.addGrade(new Grade("Test 2", 260, 300));
			newCourse.addCategory(newCategory);
			
			
			newCategory = new Category("Homework");
			newCategory.addGrade(new Grade("HW 1", 75, 100));
			newCategory.addGrade(new Grade("HW 2", 99, 100));
			newCourse.addCategory(newCategory);

			template.addCourse(newCourse);	
			
			semesters.add(template);
		}
		
		
		
	}
	
	
	public ArrayList<Semester> getSemesters() {
		return semesters;
	}


	private void readFile() {
		Semester curr;
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream("savefile.ser"));
			
			while((curr = (Semester)in.readObject()) != null) {
				semesters.add(curr);			
			}
						
		} catch(EOFException e) {
			return;
		} catch(Exception e) {
			e.printStackTrace();

		}
		
		
		
	}
	

}
