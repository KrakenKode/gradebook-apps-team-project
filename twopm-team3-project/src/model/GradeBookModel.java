package model;

import java.util.ArrayList;

public class GradeBookModel {
	
	private ArrayList<Semester> semesters;
	private String openFile;
	private String saveFile;
	
	public GradeBookModel() {	
		semesters = new ArrayList<Semester>();
		openFile = "defaultTemplate.ser";
		saveFile = "defaultTemplate.ser";
		Grade newGrade = new Grade("HW 1", 75, 100);
		System.out.println(newGrade.gradeRun());
		
//		SaveOpenDriver.readSemesters(semesters, openFile);
//		
//		if(semesters.isEmpty()) {
//			makeTemplateSemesters();
//			SaveOpenDriver.saveSemesters(semesters, openFile);
//		}
	}
	
	public void openFile() {
		semesters = new ArrayList<Semester>();
		
		SaveOpenDriver.readSemesters(semesters, openFile);	
	}
	
	
	public void saveFile() {
		SaveOpenDriver.saveSemesters(semesters, saveFile);
	}

	
	public ArrayList<Semester> getSemesters() {
		return semesters;
	}
	
	public void addSemester(Semester sem) {
		semesters.add(sem);
	}
	
	
	public Object determineTreeObject(String objectName) {
		Object obj = null;
		
		// if the name matches a semester return the semester
		for(Semester sem: semesters) {
			if (sem.getName().equals(objectName)) {
				return sem;
			}
		}
			
		// if the name matches a course return the course
		for(Semester sem: semesters) {
			for(Course curr: sem.getCourses()) {
				if (curr.getName().equals(objectName)) {
					return curr;
				}
			}
		}
			
		return obj;
	}
	
	
	public void makeTemplateSemesters() {
		Semester template;
		Category newCategory;
		Course newCourse;
				
		template = new Semester("Fall 2016");
		
		newCourse = new Course("Systems Programming");
		
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
			
		template = new Semester("Spring 2017");
				
		newCourse = new Course("Computer Organization");
		
		newCategory = new Category("Test");	
		newCategory.addGrade(new Grade("Test 1", 235, 250));
		newCategory.addGrade(new Grade("Test 2", 260, 300));
		newCourse.addCategory(newCategory);
					
		newCategory = new Category("Homework");
		newCategory.addGrade(new Grade("HW 1", 75, 100));
		newCategory.addGrade(new Grade("HW 2", 99, 100));
		newCourse.addCategory(newCategory);
		
		template.addCourse(newCourse);
				
		newCourse = new Course("Application Programming");
		
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
	
	
	// Getters and Setters
	
	public String getOpenFile() {
		return openFile;
	}

	public void setOpenFile(String openFile) {
		this.openFile = openFile;
	}

	public String getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	
}
