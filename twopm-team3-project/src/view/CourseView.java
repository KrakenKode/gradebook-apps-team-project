package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import model.*;

/**
 * Builds the main course view panel from the
 * information in the selected course then
 * add it to the main panel to be displayed.
 */
public class CourseView {
	private JPanel mainpanel;
	private ActionListener textActionListener;
	private ItemListener desiredBoxListener;
	private JPanel coursePanel;
	private JPanel categoryPanel;
	private JPanel categoryInsidePanel;
	private JComboBox<String> gradeList;
	private JScrollPane courseScroll;
	private Course course;


	public CourseView(JPanel mainpanel){
		this.mainpanel = mainpanel;
	}
	
	//added to repaint panel
	public void refresh() {
		mainpanel.remove(courseScroll);
		if (course != null) {
			course.updatePredicted();
			addCourseView(course);
		}
	}
	
	//added to remove previous text boxes
	public void removeCourseView() throws NullPointerException{
		mainpanel.remove(courseScroll);
	}

	public void addCourseView(Course course) {
		
		setCurrentSelectedCourse(course);
		course.updatePercentage();
	   
		
		//title and grade summary 
		JLabel courseLbl = new JLabel(course.getName());
		coursePanel = new JPanel(new GridLayout(0, 1, 5, 5));
		courseLbl.setForeground(Color.BLUE);
		courseLbl.setFont(new Font("Courier New", Font.PLAIN, 19));
		courseLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel summaryPanel = new JPanel(new GridLayout(0 , 1, 0, 0));
		TitledBorder tlb = new TitledBorder("Grade Summary");
		JPanel totalPanel = new JPanel();
		totalPanel.setBorder(tlb);
		
		Box currentHeaderBox = Box.createHorizontalBox();
		currentHeaderBox.add(new JLabel("Current Total"));
		currentHeaderBox.add(Box.createHorizontalStrut(100));
		currentHeaderBox.add(new JLabel("Current Grade"));
		currentHeaderBox.add(Box.createHorizontalStrut(100));
		
		Box currentUserGrades = Box.createHorizontalBox();
		currentUserGrades.add(new JLabel((new DecimalFormat("#.#").format(course.getPercentage()))+"%"));
		currentUserGrades.add(Box.createHorizontalStrut(155));
		currentUserGrades.add(new JLabel(course.getLetterGrade(course.getPercentage())));
		currentUserGrades.add(Box.createHorizontalStrut(185));
		
		Box predictedHeaderBox = Box.createHorizontalBox();
		predictedHeaderBox.add(new JLabel("Predicted Total"));
		predictedHeaderBox.add(Box.createHorizontalStrut(90));
		predictedHeaderBox.add(new JLabel("Predicted Grade"));
		predictedHeaderBox.add(Box.createHorizontalStrut(165));
		
		Box predictedUserGrades = Box.createHorizontalBox();
		predictedUserGrades.add(new JLabel((new DecimalFormat("#.#").format(course.getPredicted()))+"%"));
		predictedUserGrades.add(Box.createHorizontalStrut(155));
		predictedUserGrades.add(new JLabel(course.getLetterGrade(course.getPredicted())));
		predictedUserGrades.add(Box.createHorizontalStrut(185));
		
		Box totalBox = Box.createVerticalBox();
		totalBox.add(currentHeaderBox);
		totalBox.add(Box.createVerticalStrut(5));
		totalBox.add(currentUserGrades);
		totalBox.add(Box.createVerticalStrut(20));
		totalBox.add(predictedHeaderBox);
		totalBox.add(Box.createVerticalStrut(5));
		totalBox.add(predictedUserGrades);
		

		String[] letterList = { "Desired","A", "B", "C", "D"};

		gradeList = new JComboBox<String>(letterList);
		gradeList.addItemListener(desiredBoxListener);
	    currentHeaderBox.add(gradeList);

		totalPanel.add(totalBox);

		//Grade Summary Box
		Box summaryBox = Box.createVerticalBox();
		summaryBox.add(courseLbl);
		summaryBox.add(Box.createVerticalStrut(8));
		summaryBox.add(totalPanel);
		summaryPanel.add(summaryBox, BorderLayout.CENTER);
		coursePanel.add(summaryPanel);

		//category selection	
		ArrayList<Category> categorydata = course.getCategories();
		for(Category category : categorydata ){
			this.addCategoryView(category.getName());

			ArrayList<Grade> gradedata = category.getGrades();
			for(Grade grade : gradedata ){
				this.addGradeView(grade.getName(), grade.getPoints(), grade.getMaxPoints() ,grade.gradeRun(), grade.getPredictedGrade(),gradedata.indexOf(grade));
			}
		}
		
		courseScroll = new JScrollPane(coursePanel);
		mainpanel.add(courseScroll);
		mainpanel.revalidate();	
	}

	public void addGradeView(String gradeName, int points, int maxPoints, double totalGrade, double predictedPoints, int num) {
	
		JTextField categoryNameTxt = new JTextField(gradeName);
		categoryNameTxt.setText(gradeName);
		categoryNameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		categoryNameTxt.setHorizontalAlignment(JTextField.CENTER);
		categoryNameTxt.setPreferredSize(new Dimension(50, 25));
		categoryNameTxt.setMaximumSize(new Dimension(50, 25));
		categoryNameTxt.setName("catName");
		categoryNameTxt.addActionListener(textActionListener);
		
		Box categoryNameBox = Box.createHorizontalBox();
		categoryNameBox.add(Box.createHorizontalStrut(60));
		categoryNameBox.add(categoryNameTxt);
		//JLabel for the % total grade
		JLabel totalGradeLbl = new JLabel();
		
		if( totalGrade < 0){
			totalGradeLbl.setText("P");
		}else{
			totalGradeLbl.setText(Math.round(totalGrade*100.0)/100.0 + "%");
		}
		
		totalGradeLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		totalGradeLbl.setHorizontalAlignment(JLabel.CENTER);
		totalGradeLbl.setBackground(mainpanel.getBackground());
		
		//Text field for the earned points
		JTextField pointsEarnedTxt = new JTextField();
		//CHANGE VALUE BELOW(CONTROLLER)
		pointsEarnedTxt.setText(points+"");
		pointsEarnedTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		pointsEarnedTxt.setHorizontalAlignment(JTextField.CENTER);
		pointsEarnedTxt.setPreferredSize(new Dimension(50, 20));
		pointsEarnedTxt.setMaximumSize(new Dimension(50, 20));
		pointsEarnedTxt.setName("earnedP " + num);
		pointsEarnedTxt.addActionListener(textActionListener);
		
		//Text field for the max points
		JTextField maxPointsTxt = new JTextField();
		//CHANGE VALUE BELOW(CONTROLLER)
		maxPointsTxt.setText(maxPoints+"");
		maxPointsTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		maxPointsTxt.setHorizontalAlignment(JTextField.CENTER);
		maxPointsTxt.setPreferredSize(new Dimension(50, 20));
		maxPointsTxt.setMaximumSize(new Dimension(50, 20));
		maxPointsTxt.setName("maxP " + num);
		maxPointsTxt.addActionListener(textActionListener);
		
		categoryNameTxt.setName("GradeName " + num);
		totalGradeLbl.setName("Grade " + num);
	
		Box pointsBox = Box.createHorizontalBox();
		pointsBox.add(Box.createHorizontalStrut(20));
		pointsBox.add(pointsEarnedTxt);
		JLabel dividerLbl = new JLabel("/");
		dividerLbl.setFont(new Font("Courier New", Font.PLAIN, 18));
		pointsBox.add(dividerLbl);
		pointsBox.add(maxPointsTxt);

		//adding text fields to the panel in horizontally order
		categoryInsidePanel.add(categoryNameBox);
		categoryInsidePanel.add(pointsBox);
		categoryInsidePanel.add(totalGradeLbl);
		categoryInsidePanel.add(new JLabel(Math.round(predictedPoints)+"", JLabel.CENTER));
		
		categoryPanel.add(categoryInsidePanel);
		coursePanel.add(categoryPanel);
	}

	public void addCategoryView(String category) {

		 
		categoryPanel = new JPanel(new GridLayout(1,4,5,5));
		JTextField categoryTxt = new JTextField(category);
		categoryTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		categoryTxt.setHorizontalAlignment(JTextField.CENTER);
		categoryTxt.setFont(new Font(categoryTxt.getFont().getName(), Font.BOLD, 13));
		categoryTxt.setPreferredSize(new Dimension(90, 30));
		categoryTxt.setMaximumSize(new Dimension(90, 30));
		categoryTxt.setName("category");
		categoryTxt.addActionListener(textActionListener);

		
		Box catBox = Box.createHorizontalBox();
		catBox.add(Box.createHorizontalStrut(40));
		catBox.add(categoryTxt);
		catBox.setName("category");
		
		//categoryBox holds - Homework, Grade - Horizontally
		//changed the second parameter to 4 because of number of columns
		categoryInsidePanel = new JPanel(new GridLayout(0,4,20,20));
		categoryInsidePanel.add(catBox);
		categoryInsidePanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		categoryInsidePanel.add(new JLabel("Points / Max Points", JLabel.CENTER));
		categoryInsidePanel.add(new JLabel("Grade", JLabel.CENTER));
		categoryInsidePanel.add(new JLabel("Predict", JLabel.CENTER));

		categoryPanel.setName(category);
		categoryPanel.add(categoryInsidePanel);
		coursePanel.add(categoryPanel);
	}
 
	public void addTextActionListener(ActionListener al){
		this.textActionListener = al;
	}
	
	public void addDesiredBoxListener(ItemListener il) {
		this.desiredBoxListener = il;
	}
	
	public void setCurrentSelectedCourse(Course course){
		this.course = course;
	}
	
	public Course getCurrentSelectedCourse(){
		return this.course;
	}
	
	public JPanel getMainPanel(){
		return mainpanel;	
	}
	
	public void setCourseScroll(JScrollPane scroll){
		courseScroll = scroll;
	}
}
