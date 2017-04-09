package model;

import java.util.ArrayList;
import model.*;

public class Prediction {
	private Course course;
	private int idesiredGrade;

	public Prediction(Course course, int idesiredGrade){
		this.course = course;
		this.idesiredGrade = idesiredGrade;
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
			System.out.printf("Total Grade for %s (Weight-%d) is %f\n"
					, category.getName()
					, category.getWeight()
					, totalGrade);
			// HW total = (HW1 + HW2) * 20/100
			totalCourseGrade += totalGrade * (catWeight/100);

			totalGrade = 0; // new value for next cat
		}
		if(idesiredGrade > totalCourseGrade){
			requiredPoints = idesiredGrade - totalCourseGrade;
			System.out.printf("You still need %f to achieve your desired grade\n", requiredPoints);
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

	public static void main(String[] args){
		Semester sem = new Semester("Spring 2017");
		Course course = new Course("Programming", sem);

		Category cat1 = new Category("Test");
		cat1.setWeight(40);
		Grade test1 = new Grade("Test1", 60, 100, "");
		Grade test2 = new Grade("Test2", 50, 100, "");
		cat1.addGrade(test1);
		cat1.addGrade(test2);

		Category cat2 = new Category("Homework");
		cat2.setWeight(30);
		Grade hw1 = new Grade("HW1", 0, 100,"");
		cat2.addGrade(hw1);

		Category cat3 = new Category("Final");
		cat3.setWeight(30);
		Grade exam = new Grade("Exam", 0, 100,"");
		cat3.addGrade(exam);

		course.addCategory(cat1);
		course.addCategory(cat2);
		course.addCategory(cat3);

		int desiredGrade = course.getGradeRange("A");

		Prediction predict = new Prediction(course, desiredGrade);
		predict.initiatePrediction();
	}
}
