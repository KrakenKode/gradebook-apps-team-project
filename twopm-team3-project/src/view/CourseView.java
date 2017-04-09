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
import java.util.*;
import javax.swing.*;
import model.Category;
import model.Course;
import model.Grade;

public class CourseView {
	private JPanel mainpanel;
	private ActionListener textActionListener;
	private ItemListener desiredBoxListener;
	private JPanel coursePanel;
	private JPanel categoryPanel;
	private JPanel categoryInsidePanel;
	private JComboBox<String> gradeList;
	private JScrollPane courseScroll;


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

		String[] letterList = { "Desired","A", "B", "C", "D", "E" };

		gradeList = new JComboBox<String>(letterList);
		gradeList.addItemListener(desiredBoxListener);
		gradeList.setSelectedIndex(0);
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
		JTextField categoryComTxt = new JTextField(comment);
		categoryNameTxt.setText(gradeName);
		//categoryNameTxt.setEditable(false);
		categoryNameTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		categoryNameTxt.setHorizontalAlignment(JTextField.CENTER);
		
		//set the JTextField background to be same as mainpanel
		categoryNameTxt.setBackground(mainpanel.getBackground());
		categoryNameTxt.addActionListener(textActionListener);
		
		JTextField gradeTxt = new JTextField();
		gradeTxt.setText(Math.round(grade*100.0)/100.0 + "%");
		//gradeTxt.setEditable(false);
		gradeTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		gradeTxt.setHorizontalAlignment(JTextField.CENTER);
		gradeTxt.setBackground(mainpanel.getBackground());
		gradeTxt.addActionListener(textActionListener);
		
		JButton categoryComBut = new JButton();
		categoryComBut.setText("Comment");
		categoryComBut.addActionListener(textActionListener);
		categoryNameTxt.setName("GradeName " + num);
		gradeTxt.setName("Grade " + num);
	
		categoryInsidePanel.add(categoryNameTxt);
		categoryInsidePanel.add(gradeTxt);
		categoryInsidePanel.add(categoryComBut);
		categoryInsidePanel.add(new JLabel("--%", JLabel.CENTER));
		categoryPanel.add(categoryInsidePanel);
		coursePanel.add(categoryPanel);
	}

	public void addCategoryView(String category) {

		categoryPanel = new JPanel(new GridLayout(1,4,2,2));
		JTextField categoryTxt = new JTextField(category);
		//categoryTxt.setEditable(false);
		categoryTxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		categoryTxt.setHorizontalAlignment(JTextField.CENTER);
		categoryTxt.setBackground(mainpanel.getBackground());
		categoryTxt.setFont(new Font(categoryTxt.getFont().getName(), Font.BOLD, 13));
		categoryTxt.addActionListener(textActionListener);
		
		//categoryBox holds - Homework, Grade - Horizontally
		categoryInsidePanel = new JPanel(new GridLayout(0,4,60,20));
		categoryInsidePanel.add(categoryTxt);
		categoryInsidePanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		categoryInsidePanel.add(new JLabel("Grade", JLabel.CENTER));
		categoryInsidePanel.add(new JLabel("Comment", JLabel.CENTER));
		categoryInsidePanel.add(new JLabel("Predict", JLabel.CENTER));

		categoryPanel.setName(category);
		categoryPanel.add(categoryInsidePanel);
		categoryPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		coursePanel.add(categoryPanel);
		mainpanel.revalidate();	
	}
 
	public void addTextActionListener(ActionListener al){
		this.textActionListener = al;
	}
	
	public void addDesiredBoxListener(ItemListener il) {
		this.desiredBoxListener = il;
	}
	
}
