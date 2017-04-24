package model;

import java.util.ArrayList;

/**
 * Prediction class is used to perform the
 * minimum score calculations for the user's
 * target grade.
 */
public class Prediction {
	private Course course;
	private int idesiredGrade;
	private double requiredGrade = 0;

	public Prediction(Course course, int idesiredGrade){
		this.course = course;
		this.idesiredGrade = idesiredGrade;
	}
	
	/**
	 * The method test if there are grades available to perform 
	 * Method must be called before initiating Prediction
	 * @return
	 */
	public boolean testPredictability(){
		//if there are any zero value grades 
		for(Category category : course.getCategories()){
			for(Grade grade : category.getGrades()){
				if(grade.getNumOfGradeToPredict() > 0){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * The method initiate prediction algorithm 
	 * it check to see if user does not already have the desired grade
	 * returns true if prediction succeed, false otherwise 
	 * @return
	 */
	public boolean initiatePrediction(){
		course.updatePercentage();
		double coursePercent = course.getPercentage();

		if( coursePercent < idesiredGrade){
			requiredGrade = idesiredGrade - coursePercent;
			//return setPredictionGrades(idesiredGrade);
			
			if(setPredictionGrades(requiredGrade) == true){
				return testPredictions();
			}
		}

		return false;
	}

	public boolean setPredictionGrades(double requiredPrecent){
		double predicted = 0;

		for(Category category : course.getCategories()){
			for(Grade grade : category.getGrades()){
				if(grade.getPoints() == -1){

					predicted = (grade.getMaxPoints() * requiredPrecent/100);

					grade.setPredictedGrade(predicted);
				}
			}
		}

		boolean predictState = testPredictions();

		if( predictState == false && requiredPrecent < 100){
			setPredictionGrades(requiredPrecent+1); 
			// if the desired grade is still not met, it would increase
			//the requiredPercent 
		}
		else if(predictState == false && requiredPrecent >= 100){
			return false;
		}

		return true;
	}

	/*
	 * testPredictions() will test to see if predicted grades 
	 * will give the user's desired grade.
	 */
	public boolean testPredictions(){
		double total = 0;
		double totalMax = 0;
		double predictionResult = 0;

		for(Category category: course.getCategories()){
			ArrayList<Grade> gradedata = category.getGrades();
			for(Grade grade : gradedata){
				if(grade.getPoints() > 0){
					total += grade.getPoints();
				}else if(grade.getPoints() == -1){
					total += grade.getPredictedGrade();;
				}
				totalMax += grade.getMaxPoints();
			}
			predictionResult = (total / totalMax) * 100 ;

		}
		course.setPredicted(predictionResult);
		if( idesiredGrade > predictionResult){
			return false; //still doesn't reach the desired grade
		}

		return true;
	}

	public void showPredictions(){
		for(Category category: course.getCategories()){
			ArrayList<Grade> gradedata = category.getGrades();
			System.out.println("Grade\tCurrent Grade\tPredict");
			for(Grade grade : gradedata){
				System.out.println(grade.getName()+"\t\t"
						+ grade.gradeRun() + "\t\t"+grade.getPredictedGrade());
			}
		}
	}

}
