package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Category;
import model.Course;
import model.Grade;

public class NewGradeInputPopUp {


	private Course course;

	public NewGradeInputPopUp(Course course) {
		this.course = course;
	}


	public void newGradePopUp() {
		//make new panel and constraint objects
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints jLabels = new GridBagConstraints();
		GridBagConstraints tFields = new GridBagConstraints();
		GridBagConstraints aFields = new GridBagConstraints();
		
		//Create text fields for user to enter their new grade data
		JTextField nameField = new JTextField(5);
		JFormattedTextField gradeField = new JFormattedTextField(0);
		JFormattedTextField maxField = new JFormattedTextField(100);
		//Set the sizes for the text fields
		nameField.setPreferredSize(new Dimension(110, 20));
		gradeField.setPreferredSize(new Dimension(50, 20));
		maxField.setPreferredSize(new Dimension(50, 20));
		//Make a DesiredBox for user to select category for new grade
		DefaultComboBoxModel<Category> categoryModel = new DefaultComboBoxModel<Category>();
		//Populate DesiredBox
		for(Category cat : course.getCategories()) {
			categoryModel.addElement(cat); 
		}
		//Add Desired box and edit every Label and Field to correctly
		//display on the NewGradePopUp window
		JComboBox<Category> comboBox = new JComboBox<Category>(categoryModel);
		jLabels.gridx = 0;
		jLabels.gridy = 0;
		jLabels.insets = new Insets(10, 0, 10, 0);
		tFields.insets = new Insets(10, 0, 10, 0);
		panel.add(new JLabel("Category:"), jLabels);
		jLabels.gridx = 1;
		jLabels.gridy = 0;
		panel.add(comboBox, jLabels);
		jLabels.gridx = 0;
		jLabels.gridy = 1;
		panel.add(new JLabel("Grade name:"), jLabels);
		tFields.gridx = 1;
		tFields.gridy = 1;
		panel.add(nameField, tFields);
		jLabels.gridx = 0;
		jLabels.gridy = 2;
		panel.add(new JLabel("Points earned:"), jLabels);
		tFields.gridx = 1;
		tFields.gridy = 2;
		panel.add(gradeField, tFields);
		jLabels.gridx = 0;
		jLabels.gridy = 3;
		panel.add(new JLabel("Max Points:"), jLabels);
		tFields.gridx = 1;
		tFields.gridy = 3;
		panel.add(maxField, tFields);

		//Pop up the window for the user
		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Enter Grade Information", JOptionPane.OK_CANCEL_OPTION);
		
		//If OK is clicked get the user data and create the grade
		if (result == JOptionPane.OK_OPTION) {
			System.out.println("Category: " + comboBox.getSelectedItem());
			System.out.println("Grade name: " + nameField.getText());
			System.out.println("Grade: " + gradeField.getText());
			System.out.println("Max Grade: " + maxField.getText());

			Grade grade = new Grade(nameField.getText(), 
					Integer.parseInt(gradeField.getText()), 
					Integer.parseInt(maxField.getText()));
					
			Object obj = comboBox.getSelectedItem();
			if (obj instanceof Category) {
				((Category) obj).addGrade(grade);		// add the grade to category
			}
		}
	}

}