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
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints jLabels = new GridBagConstraints();
		GridBagConstraints tFields = new GridBagConstraints();
		GridBagConstraints aFields = new GridBagConstraints();
		
		JTextField nameField = new JTextField(5);
		JFormattedTextField gradeField = new JFormattedTextField(0);
		JFormattedTextField maxField = new JFormattedTextField(100);
		
		nameField.setPreferredSize(new Dimension(110, 20));
		gradeField.setPreferredSize(new Dimension(50, 20));
		maxField.setPreferredSize(new Dimension(50, 20));

		
		JTextArea comField = new JTextArea("Add a comment", 5, 0);
		comField.setBorder(BorderFactory.createLineBorder(Color.black));
		comField.setPreferredSize(new Dimension(100, 100));
		comField.setColumns(10);
		comField.setEditable(true);
		comField.setLineWrap(true);
		comField.setWrapStyleWord(true);
		
		DefaultComboBoxModel<Category> categoryModel = new DefaultComboBoxModel<Category>();

		for(Category cat : course.getCategories()) {
			categoryModel.addElement(cat); 
		}

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
		jLabels.gridx = 0;
		jLabels.gridy = 4;
		panel.add(new JLabel("Comment:"), jLabels);
		aFields.gridx = 1;
		aFields.gridy = 4;
		panel.add(comField, aFields);


		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Enter Grade Information", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			System.out.println("Category: " + comboBox.getSelectedItem());
			System.out.println("Grade name: " + nameField.getText());
			System.out.println("Grade: " + gradeField.getText());
			System.out.println("Max Grade: " + maxField.getText());
			System.out.println("Comment: " + comField.getText());

			Grade grade = new Grade(nameField.getText(), 
					Integer.parseInt(gradeField.getText()), 
					Integer.parseInt(maxField.getText()),
					comField.getText());
			
			Object obj = comboBox.getSelectedItem();
			if (obj instanceof Category) {
				((Category) obj).addGrade(grade);		// add the grade to category
			}
		}
	}

}