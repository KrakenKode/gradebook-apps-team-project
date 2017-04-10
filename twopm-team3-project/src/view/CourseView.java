package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.*;
import model.*;


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
		mainpanel.repaint();
	}
	
	//added to remove previous text boxes
	public void removeCourseView() throws NullPointerException{
		mainpanel.remove(courseScroll);
	}

	public void addCourseView(Course course) {
		
		setCurrentSelectedCourse(course);
		
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
		
		Box totalBox = Box.createHorizontalBox();
		totalBox.add(new JLabel("Total"));
		totalBox.add(Box.createHorizontalStrut(100));
		totalBox.add(new JLabel("Current Grade"));
		totalBox.add(Box.createHorizontalStrut(100));

		String[] letterList = { "Desired","A", "B", "C", "D"};

		gradeList = new JComboBox<String>(letterList);
		gradeList.addItemListener(desiredBoxListener);
		//gradeList.setSelectedItem(course.getDesiredGrade());;
		totalBox.add(gradeList);

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
				this.addGradeView(grade.getName(), grade.gradeRun(), grade.getComment(), gradedata.indexOf(grade));
				System.out.println(gradedata.indexOf(grade));
			}
		}
		
		courseScroll = new JScrollPane(coursePanel);
		mainpanel.add(courseScroll);
		mainpanel.revalidate();	
	}

	public void addGradeView(String gradeName, Double grade, String comment, int num) {
		//TODO change JTextField to button to add comment button for popup

		JTextField categoryNameTxt = new JTextField(gradeName);
//		JTextField categoryComTxt = new JTextField(comment);
		categoryNameTxt.setText(gradeName);
		categoryNameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		categoryNameTxt.setHorizontalAlignment(JTextField.CENTER);
		
		//set the JTextField background to be same as mainpanel
		categoryNameTxt.setBackground(mainpanel.getBackground());
		categoryNameTxt.addActionListener(textActionListener);
		
		//Text field for the %grade
		JTextField gradeTxt = new JTextField();
		gradeTxt.setText(Math.round(grade*100.0)/100.0 + "%");
		gradeTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		gradeTxt.setHorizontalAlignment(JTextField.CENTER);
		gradeTxt.setBackground(mainpanel.getBackground());
		gradeTxt.addActionListener(textActionListener);
		//why isnt this committ working
		//Text field for the earned points
		JTextField earned = new JTextField();
		//CHANGE VALUE BELOW(CONTROLLER)
		earned.setText("earned");
		earned.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		earned.setHorizontalAlignment(JTextField.CENTER);
		earned.setBackground(mainpanel.getBackground());
		earned.addActionListener(textActionListener);
		
		//Text field for the max points
		JTextField max = new JTextField();
		//CHANGE VALUE BELOW(CONTROLLER)
		max.setText("max!");
		max.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		max.setHorizontalAlignment(JTextField.CENTER);
		max.setBackground(mainpanel.getBackground());
		max.addActionListener(textActionListener);
		

		categoryNameTxt.setName("GradeName " + num);
		gradeTxt.setName("Grade " + num);


	

		//adding text fields to the panel in order
		categoryInsidePanel.add(categoryNameTxt);
		categoryInsidePanel.add(earned);
		categoryInsidePanel.add(max);
		categoryInsidePanel.add(gradeTxt);
		categoryInsidePanel.add(new JLabel("%", JLabel.CENTER));
		

//This down here is giving me an error so i just commented it out for now
	//	categoryInsidePanel.add(new JLabel(Math.round(predicted*100.0)/100.0 + "%", JLabel.CENTER));


		categoryPanel.add(categoryInsidePanel);
		coursePanel.add(categoryPanel);
	}

	public void addCategoryView(String category) {

		 
		categoryPanel = new JPanel(new GridLayout(1,4,2,2));
		JTextField categoryTxt = new JTextField(category);
		categoryTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		categoryTxt.setHorizontalAlignment(JTextField.CENTER);
		categoryTxt.setBackground(mainpanel.getBackground());
		categoryTxt.setFont(new Font(categoryTxt.getFont().getName(), Font.BOLD, 13));
		categoryTxt.addActionListener(textActionListener);
		
		//categoryBox holds - Homework, Grade - Horizontally
		//changed the second parameter to 5 because of number of columns
		categoryInsidePanel = new JPanel(new GridLayout(0,5,60,20));
		categoryInsidePanel.add(categoryTxt);
		categoryInsidePanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		categoryInsidePanel.add(new JLabel("Earned", JLabel.CENTER));
		categoryInsidePanel.add(new JLabel("MaxPts",JLabel.CENTER));
		categoryInsidePanel.add(new JLabel("Grade", JLabel.CENTER));
		categoryInsidePanel.add(new JLabel("Predict", JLabel.CENTER));

		categoryPanel.setName(category);
		categoryPanel.add(categoryInsidePanel);
		categoryPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
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
}
