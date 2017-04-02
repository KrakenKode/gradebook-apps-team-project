package view;
import model.*;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class SemesterReportCard extends ApplicationFrame
{
   public SemesterReportCard(Semester sem)
   {
      super(sem.getName() + " Report Card");        
      JFreeChart barChart = ChartFactory.createBarChart(
         sem.getName(),           
         "Courses",            
         "Grades",            
         createDataset(sem),          
         PlotOrientation.VERTICAL,           
         false, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   private CategoryDataset createDataset(Semester sem)
   {
	  ArrayList<Course> courses = sem.getCourses();
	  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	  for (Course course : courses) {
		  System.out.println(course.getPercentage());
		  dataset.addValue(course.getPercentage(), "Grade", course.getName());
	  }
	 
      return dataset; 
   }
}
