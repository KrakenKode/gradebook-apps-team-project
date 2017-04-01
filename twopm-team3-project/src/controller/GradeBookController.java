package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import model.GradeBookModel;
import model.Semester;
import view.EditOptionView;
import view.GradeBookView;

public class GradeBookController implements ActionListener {
	
	private GradeBookView view;
	private GradeBookModel model;
	

	public GradeBookController(GradeBookModel model, GradeBookView view) {
		this.model = model;
		this.view = view;
		
		//TODO functions for adding classes to view
		view.addMenuListener(new MenuListener());
		
		
		view.initializeTreeData(model.getSemesters()); //load semester to view
		view.addTreeListener(new TreeListener());
	}
	
	class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Open")){
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				FileFilter filter = new FileNameExtensionFilter("SER file", "ser");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    model.setOpenFile(selectedFile.getAbsolutePath());
				    model.openFile();
				    view.updateTreeData(model.getSemesters());
				}
			} else if (command.equals("Save")){
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
			}else if(command.equals("Add Semester")){
				EditOptionView ev = new EditOptionView(view, "Add Semester");
				String sem = ev.addView();
				Semester semester = new Semester(sem);
				model.addSemester(semester);
				System.out.println(sem);
			}else if( command.equals("Quit")){
				System.exit(0);
			}
		}
	}
	
	class TreeListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			Object treeObject = view.getTree().getLastSelectedPathComponent();
			
			// Cast the Object into a DefaultMutableTreeNode
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeObject;
			
			// Returns the object stored in this node and casts it to a string
				
			String treeNode = (String) node.getUserObject();
			if (node.isLeaf()) {
				view.setLable(treeNode);
			}
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
