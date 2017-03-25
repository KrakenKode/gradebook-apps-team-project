package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

	private String name;
	private ArrayList<Category> categories;
	private double percentage; 
	
	public Course(String name) {
		this.name = name;
		categories = new ArrayList<Category>();	
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

	
}
