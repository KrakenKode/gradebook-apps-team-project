package view;

import java.awt.GridLayout;

import javax.swing.*;

import model.*;

public class NewGradeInputPopUp {


	private Course course;

	public NewGradeInputPopUp(Course course) {
		this.course = course;
	}


	public void newGradePopUp() {
		JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));
		JTextField nameField = new JTextField(5);
		JTextField gradeField = new JTextField(5);
		JTextField maxField = new JTextField(5);
		JTextField comField = new JTextField(5);
		DefaultComboBoxModel<Category> categoryModel = new DefaultComboBoxModel<Category>();

		for(Category cat : course.getCategories()) {
			categoryModel.addElement(cat); 
		}

		JComboBox<Category> comboBox = new JComboBox<Category>(categoryModel);

		panel.add(new JLabel("Category:"));
		panel.add(comboBox);
		panel.add(new JLabel("Grade name:"));
		panel.add(nameField);
		panel.add(new JLabel("Points earned:"));
		panel.add(gradeField);
		panel.add(new JLabel("Max Points:"));
		panel.add(maxField);
		panel.add(new JLabel("Comment:"));
		panel.add(comField);


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