package view;

import java.awt.GridLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Category;
import model.Course;
import model.Grade;

/**
 * Pop up used give the user options on which
 * grade they wish to delete.
 *
 */
public class DeleteGradePopUp {

	private Course course;

	public DeleteGradePopUp(Course course) {
		this.course = course;
	}
	
	public void deleteGradePopUp(){
		JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));

		DefaultComboBoxModel<Grade> gradeModel = new DefaultComboBoxModel<Grade>();

		for(Category cat : course.getCategories()) {
			for(Grade grade : cat.getGrades()){
				gradeModel.addElement(grade); 
			}
		}

		JComboBox<Grade> comboBox = new JComboBox<Grade>(gradeModel);

		panel.add(new JLabel("Grade to Delete:"));
		panel.add(comboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Remove Grade Information", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			Object obj = comboBox.getSelectedItem();
			if (obj instanceof Grade) {
				Grade delGrade = (Grade) obj;
				for(Category cat : course.getCategories()) {
					if(cat.getGrades().remove(delGrade)) {
						break;
					}
				}

			}
		}		
	}
	
}
