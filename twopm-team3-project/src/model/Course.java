package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Course implements Serializable {

	private String name;
	private ArrayList<Category> categories;
	private HashMap<String, Integer> gradeRange;
	private double percentage;
	private Semester parentSem;
	private String sdesiredGrade;
	private double predictedGrade;

	public Course(String name, Semester parent) {
		this.name = name;
		this.parentSem = parent;
		categories = new ArrayList<Category>();
		gradeRange = new HashMap<String, Integer>();
		defaultGradeRange();
		
		// assume student wants A for every course
		setDesiredGrade("A"); 
	}

	public void addCategory(Category newCategory) {
		categories.add(newCategory);
	}

	public void removeCategory(Category category) {
		categories.remove(category);
	}
	
	
	public void updatePercentage() {
		double total = 0, max = 0;
		
		for(Category cat : categories) {
			for(Grade grade : cat.getGrades()) {
				total += grade.getPoints();
				max += grade.getMaxPoints();
			}
		}
		
		if(max == 0 || total == 0) {
			percentage = 0;
		}
		
		percentage = (total/max) * 100;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void defaultGradeRange() {
		gradeRange.put("A", 90);
		gradeRange.put("B", 80);
		gradeRange.put("C", 70);
		gradeRange.put("D", 60);
		
	}
	
	/////////////Getters and Setters///////////////////
	
	public double getPredicted(){
		return predictedGrade;
	}
	
	public void setPredicted(double predictedGrade){
		this.predictedGrade = predictedGrade;
	}
	
	public String getName() {
		return name;
	}

	public Semester getParentSem() {
		return parentSem;
	}
	
	
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public int getGradeRange(String key) {
		return gradeRange.get(key);
	}
	
	public void setGradeRange(String key, int grade) {
		gradeRange.put(key, grade);
	}
	
	public String getDesiredGrade(){
		return sdesiredGrade;
	}
	
	public void setDesiredGrade(String desiredGrade){
		this.sdesiredGrade = desiredGrade;
	}
	
	public String getLetterGrade(double value) {
		if (value < gradeRange.get("D"))
			return "F";
		else if (value < gradeRange.get("C"))
			return "D";
		else if (value < gradeRange.get("B"))
			return "C";
		else if (value < gradeRange.get("A"))
			return "B";
		else
			return "A";
		}
}
