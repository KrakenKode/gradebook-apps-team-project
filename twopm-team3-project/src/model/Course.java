package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

	private String name;
	private ArrayList<Category> categories;
	
	
	
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
	
	/////////////Getters and Setters///////////////////
	
	public String getName() {
		return name;
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
