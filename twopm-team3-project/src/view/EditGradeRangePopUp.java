package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
		JPanel panel = new JPanel(new GridBagLayout());
		//set up constraints
		GridBagConstraints jLabelC = new GridBagConstraints();
		GridBagConstraints tFieldC = new GridBagConstraints();
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
		//Set fields to current default grade ranges
		Afield.setText(Integer.toString(course.getGradeRange("A")));
		Bfield.setText(Integer.toString(course.getGradeRange("B")));
		Cfield.setText(Integer.toString(course.getGradeRange("C")));
		Dfield.setText(Integer.toString(course.getGradeRange("D")));
		
		jLabelC.insets = new Insets(0, 0, 0, 20);
		tFieldC.insets = new Insets(0, 10, 0, 0);
		//Edit constraints and add JLabels and JTextFields
		jLabelC.gridx = 0;
		jLabelC.gridy = 0;
		panel.add(Alabel, jLabelC);
		tFieldC.gridx = 1;
		tFieldC.gridy = 0; 
		panel.add(Afield, tFieldC);
		jLabelC.gridx = 0;
		jLabelC.gridy = 1;
		panel.add(Blabel, jLabelC);
		tFieldC.gridx = 1;
		tFieldC.gridy = 1; 
		panel.add(Bfield, tFieldC);
		jLabelC.gridx = 0;
		jLabelC.gridy = 2;
		panel.add(Clabel, jLabelC);
		tFieldC.gridx = 1;
		tFieldC.gridy = 2; 
		panel.add(Cfield, tFieldC);
		jLabelC.gridx = 0;
		jLabelC.gridy = 3;
		panel.add(Dlabel, jLabelC);
		tFieldC.gridx = 1;
		tFieldC.gridy = 3; 
		panel.add(Dfield, tFieldC);
		//Pop up dialog box
		int result = JOptionPane.showConfirmDialog(null, panel, "Enter Grade Range Information", JOptionPane.OK_CANCEL_OPTION);
		//sets the grade ranges based off of what the user has input
		if (result == JOptionPane.OK_OPTION) {
			course.setGradeRange("A", Integer.parseInt(Afield.getText()));
			course.setGradeRange("B", Integer.parseInt(Bfield.getText()));
			course.setGradeRange("C", Integer.parseInt(Cfield.getText()));
			course.setGradeRange("D", Integer.parseInt(Dfield.getText()));
		}
	}

}
