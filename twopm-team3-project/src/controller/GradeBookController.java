package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.GradeBookModel;
import view.GradeBookView;

public class GradeBookController implements ActionListener {
	
	private GradeBookView view;
	private GradeBookModel model;
	

	public GradeBookController(GradeBookModel model, GradeBookView view) {
		this.model = model;
		this.view = view;

		//TODO functions for adding classes to view
		view.addMenuListener(new MenuListener());

		view.LoadData(model.getSemesters()); //load semester to view
	}
	
	class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Open")){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("ser");
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.OPEN_DIALOG) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //TODO open file using open class
				}
			} else if (command.equals("Save")){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("ser");
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.SAVE_DIALOG) {
				    File selectedFile = fileChooser.getSelectedFile();
				    //TODO save file using save class
				}
			}else if( command.equals("Quit")){
				System.exit(0);
			}
		}
	}
	
	class TreeListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			
		}
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 
		
	}
	
	
}
