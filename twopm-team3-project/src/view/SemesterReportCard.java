package view;

import model.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * SemesterReportCard displays a bar chart for the
 * selected semester.
 */
public class SemesterReportCard extends JFrame
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
         true, true, false);
      	double GPA = getGPA(sem);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 750 , 600 ) );
      JPanel panel = new JPanel(new BorderLayout());
      JLabel GPAlabel = new JLabel(" Semester GPA = " + GPA);
      GPAlabel.setFont(new Font(GPAlabel.getName(), Font.PLAIN, 16));
      panel.add(chartPanel);
      panel.add(GPAlabel, BorderLayout.SOUTH);
      panel.setBackground(Color.WHITE);
      setContentPane( panel );
   }
   private CategoryDataset createDataset(Semester sem)
   {
	  ArrayList<Course> courses = sem.getCourses();
	  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	  for (Course course : courses) {
		  course.updatePercentage();
		  dataset.addValue(course.getPercentage(), "Course Grade", course.getName());
		  for (Category cat : course.getCategories()) {
			  dataset.addValue(cat.catRun(), cat.getName() + " Average", course.getName());
		  }
	  }
	 
      return dataset; 
   }
   
   private double getGPA(Semester sem) {
	   double GPA;
	   double total = 0;
	   int cnt = 0;
	   ArrayList<Course> courses = sem.getCourses();
	   for (Course course : courses) {
		   course.updatePercentage();
		   total += (course.getPercentage()/20-1);
		   cnt++;
	   }
	   GPA = total/cnt;
	   return new BigDecimal(GPA).setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
   }
}
