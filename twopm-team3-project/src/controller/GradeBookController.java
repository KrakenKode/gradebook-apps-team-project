package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.Popup;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.*;
import javax.swing.JTree;
import model.*;
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
		view.addTreeListener(new TreeListener(), new TreeListener());
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
				String semString = ev.addPopUp();
				if (semString== null) {return;}
				Semester sem = new Semester(semString);
				model.addSemester(sem);
				view.updateTreeData(model.getSemesters());			
				ev.showSuccess("Success!");

			}else if( command.equals("Quit")){
				System.exit(0);
			}
		}
	}
	
	class TreeListener implements TreeSelectionListener, MouseListener{
		JTree tree = view.getTree();
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			
			Object treeObject = view.getTree().getLastSelectedPathComponent();
			
			if (treeObject == null) {return;}
			// Cast the Object into a DefaultMutableTreeNode
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeObject;
			
			// Returns the object stored in this node and casts it to a string
				
			String treeNode = (String) node.getUserObject();
			if (treeNode == null) {return;}
			if (node.isLeaf()) {
//				view.setLable(treeNode);
				
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			 if (SwingUtilities.isRightMouseButton(e)) {
				 int row = tree.getClosestRowForLocation(e.getX(), e.getY());
				 if (row == -1) {return;}	// no node was selected
				 tree.setSelectionRow(row);
				 TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
				 if (selPath == null) {return;}
				 Object obj = model.determineTreeObject(selPath.getLastPathComponent().toString());
				 if (obj instanceof Semester) {
					 Semester sem = (Semester) obj;
					 EditOptionView ev = new EditOptionView(view, "Add Course");
					 String courseString = ev.addPopUp();
					 if (courseString== null) {return;}
					 Course newCourse = new Course(courseString);
					 sem.addCourse(newCourse);
					 view.updateTreeData(model.getSemesters());			
					 ev.showSuccess("Success!");		 
				 } else if (obj instanceof Course) {
					 Course course = (Course) obj;
					 EditOptionView ev = new EditOptionView(view, "Add Category");
					 String categoryString = ev.addPopUp();
					 if (categoryString == null) {return;}
					 Category newCategory = new Category(categoryString);
					 course.addCategory(newCategory);
					 view.updateTreeData(model.getSemesters());			
					 ev.showSuccess("Success!");
				 }
			 }
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
