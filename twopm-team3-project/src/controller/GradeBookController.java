package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.*;
import view.InputOptionView;
import view.GradeBookView;
import view.ReportPopUp;

public class GradeBookController {

	private GradeBookView view;
	private GradeBookModel model;

	public GradeBookController(GradeBookModel model, GradeBookView view) {
		this.model = model;
		this.view = view;

		//set up menu listener
		view.getMenuView().addMenuListener(new MenuListener());
		
		//set up desired box listener
		view.getCourseView().addDesiredBoxListener(new DesiredBoxListener());
		
		//set up the treeView
		view.getTreeView().initializeTreeData(model.getSemesters());
		view.getTreeView().addTreeListener(new GBTreeListener(model, view), new GBTreeListener(model, view));		

	}


	class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Open")) {
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					
				}
				view.getCourseView().refresh();
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				FileFilter filter = new FileNameExtensionFilter("SER file", "ser");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					model.setOpenFile(selectedFile.getAbsolutePath());
					model.openFile();
					view.getTreeView().rebuildTree(model.getSemesters());
				}
			} else if(command.equals("Save")) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				FileFilter filter = new FileNameExtensionFilter("SER file", "ser");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					model.setSaveFile(selectedFile.getAbsolutePath());
					model.saveFile();
				}
			} else if(command.equals("Add Semester")) {
				InputOptionView ev = new InputOptionView(view, "Add Semester");
				String semString = ev.addPopUp();
				if (semString== null) {return;}
				Semester sem = new Semester(semString);
				model.addSemester(sem);
				view.getTreeView().addSemesterNode(sem);
			} else if(command.equals("Semester Report")) {
				ReportPopUp report = new ReportPopUp(model);
				report.SemesterPopUp();
			} else if(command.equals("Course Report")) {
				ReportPopUp report = new ReportPopUp(model);
				report.CoursePopUp();
			} else if( command.equals("Quit")) {
				System.exit(0);
			}
		}
	}
	
	class DesiredBoxListener implements ItemListener {
		
		@Override
		public void itemStateChanged(ItemEvent i) {
			String grade = (String) i.getItem();
			if (i.getStateChange() == i.SELECTED)
				System.out.println(grade);
			
		}
		
	}
	
}