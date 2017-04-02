package view;

import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import model.Category;
import model.Course;
import model.Grade;
import model.GradeBookModel;
import model.Semester;

public class ReportPopUp {
	private GradeBookModel model;

	public ReportPopUp(GradeBookModel model) {
		this.model = model;
	}

	public void SemesterPopUp(){
		JPanel panel = new JPanel(new GridLayout(0, 2, 20, 20));

		DefaultComboBoxModel<Semester> semModel = new DefaultComboBoxModel<Semester>();

		for(Semester sem : model.getSemesters()) {
			semModel.addElement(sem); 
		}

		JComboBox<Semester> comboBox = new JComboBox<Semester>(semModel);

		panel.add(new JLabel("Select Semester"));
		panel.add(comboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Select a Semester", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			Object obj = comboBox.getSelectedItem();
			if (obj instanceof Semester) {
				Semester semCard = (Semester) obj;
				SemesterReportCard chart = new SemesterReportCard(semCard);
			    chart.pack( );        
			    RefineryUtilities.centerFrameOnScreen( chart );        
			    chart.setVisible( true );
			}

		}
	}
	public void CoursePopUp(){
		JPanel panel = new JPanel(new GridLayout(0, 2, 10, 5));

		DefaultComboBoxModel<Course> courseModel = new DefaultComboBoxModel<Course>();

		for(Semester sem : model.getSemesters()) {
			for(Course course : sem.getCourses()){
				courseModel.addElement(course); 
			}
		}

		JComboBox<Course> comboBox = new JComboBox<Course>(courseModel);

		panel.add(new JLabel("Course"));
		panel.add(comboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, 
				"Course Report Card", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			Object obj = comboBox.getSelectedItem();
			if (obj instanceof Course) {
				Course courseCard = (Course) obj;
				final CourseReportCard corCard = new CourseReportCard(courseCard);
				corCard.pack();
		        RefineryUtilities.centerFrameOnScreen(corCard);
		        corCard.setVisible(true);
			}

		}
	}
}

