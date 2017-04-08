package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Course implements Serializable {

	private String name;
	private ArrayList<Category> categories;
	private HashMap<String, Integer> gradeRange;
	private double percentage; 
	
//	public int predict(String letter){
//		for(Category cat : categories){
//			cat.catRun()
//		}
//		return 0 ;
//	}
	
	public Course(String name) {
		this.name = name;
		categories = new ArrayList<Category>();
		gradeRange = new HashMap<String, Integer>();
		defaultGradeRange();
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
	

	public String getName() {
		return name;
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
}
