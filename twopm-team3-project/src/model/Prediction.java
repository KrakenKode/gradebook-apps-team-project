package model;

import java.util.ArrayList;

public class Prediction {
	private Course course;
	private int DesiredGrade;
	
	public Prediction(Course course, int DesiredGrade){
		this.course = course;
		this.DesiredGrade = DesiredGrade;
	}
	
	public void initiatePrediction(){
		double catWeight;
		double totalGrade =0;
		double totalCourseGrade = 0;
		double requiredPoints = 0;
		
		ArrayList<Category> categorydata = course.getCategories();
		for(Category category : categorydata ){
			catWeight  = category.getWeight();
			
			ArrayList<Grade> gradedata = category.getGrades();
			for(Grade grade : gradedata ){
				if(grade.getPoints() != 0){
					totalGrade +=grade.gradeRun();
				}
			}
			// HW total = (HW1 + HW2) * 20/100
			totalCourseGrade += totalGrade * (catWeight/100);
		}
		
		if(DesiredGrade > totalCourseGrade){
			//requiredPoints 
		}
	}
	
	private void setPredictedGrade(int predicted){
		ArrayList<Category> categorydata = course.getCategories();
		for(Category category : categorydata ){
			
			
			ArrayList<Grade> gradedata = category.getGrades();
			for(Grade grade : gradedata ){
				if(grade.getPoints() == 0){
					
				}
			}
			
		}
	}
}
