package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Course;

public class EditGradeRangePopUp {
	
	private Course course;
	
	public EditGradeRangePopUp(Course course) {
		this.course = course;
	}
	
	public void gradeRangePopUp() {
		JPanel panel = new JPanel(new GridLayout(0, 2, 20, 2));
		//Set up new labels
		JLabel Alabel = new JLabel("A");
		JLabel Blabel = new JLabel("B");
		JLabel Clabel = new JLabel("C");
		JLabel Dlabel = new JLabel("D");
		//Sets text field for grades
		JTextField Afield = new JTextField(3);
		JTextField Bfield = new JTextField(3);
		JTextField Cfield = new JTextField(3);
		JTextField Dfield = new JTextField(3);
		//Set fields to current defualt grade ranges
		Afield.setText(Integer.toString(course.getGradeRange("A")));
		Bfield.setText(Integer.toString(course.getGradeRange("B")));
		Cfield.setText(Integer.toString(course.getGradeRange("C")));
		Dfield.setText(Integer.toString(course.getGradeRange("D")));
		
		panel.add(Alabel);
		panel.add(Afield);
		panel.add(Blabel);
		panel.add(Bfield);
		panel.add(Clabel);
		panel.add(Cfield);
		panel.add(Dlabel);
		panel.add(Dfield);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Enter Grade Range Information", JOptionPane.OK_CANCEL_OPTION);
		
		if (result == JOptionPane.OK_OPTION) {
			course.setGradeRange("A", Integer.parseInt(Afield.getText()));
			course.setGradeRange("B", Integer.parseInt(Bfield.getText()));
			course.setGradeRange("C", Integer.parseInt(Cfield.getText()));
			course.setGradeRange("D", Integer.parseInt(Dfield.getText()));
		}
	}

}
