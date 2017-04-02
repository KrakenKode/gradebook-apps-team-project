package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
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
import view.NewGradeInputPopUp;

public class GradeBookController implements ActionListener {
	
	private GradeBookView view;
	private GradeBookModel model;
	private Course currCourse;
	private Semester currSem;
	

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
				Object obj = model.determineTreeObject(treeNode);
				if( obj instanceof Course){
					Course course = (Course) obj;
					view.addCourseView(course);
				}
				
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
				 Object obj = model.determineTreeObject(selPath.getLastPathComponent().toString()); //get the object mouse was closest to
				 ActionListener ra = new JPopupMenuListener();
				 //if the object was a semester object
				 if (obj instanceof Semester) {
					 //Make JPopupMenu for right click context
					 currSem = (Semester) obj;
					 JPopupMenu rc = new JPopupMenu();
					 JMenuItem couradd = new JMenuItem();
					 couradd.setText("Add Course");
					 couradd.addActionListener(ra);
					 rc.add(couradd);
					 rc.show(e.getComponent(), e.getX(), e.getY());
				 //if the object was a course object
				 } else if (obj instanceof Course) {
					 //Make JPopupMenu for right click context
					 currCourse = (Course) obj;
					 JPopupMenu rc = new JPopupMenu();
					 JMenuItem catadd = new JMenuItem();
					 catadd.setText("Add Category");
					 catadd.addActionListener(ra);
					 rc.add(catadd);
					 JMenuItem gradeadd = new JMenuItem();
					 gradeadd.setText("Add Grade");
					 gradeadd.addActionListener(ra);
					 rc.add(gradeadd);
//					 rc.show(e.getComponent(), e.getX(), e.getY());
					 
					 rc.addSeparator();
					 
					 JMenuItem gradeRemove = new JMenuItem();
					 gradeRemove.setText("Remove Grade");
					 gradeRemove.addActionListener(ra);
					 rc.add(gradeRemove);
					 rc.show(e.getComponent(), e.getX(), e.getY());
					
					 
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
	
	
	class JPopupMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("Add Category")){
				EditOptionView ev = new EditOptionView(view, "Add Category");
				String categoryString = ev.addPopUp();
				if (categoryString == null) {return;}
				Category newCategory = new Category(categoryString);
				currCourse.addCategory(newCategory);
				view.updateTreeData(model.getSemesters());			
				ev.showSuccess("Success!");
				//view.addCourseView(currCourse); no longer needed
				view.addCategoryView(newCategory.getName());
			} else if(command.equals("Add Grade")){
				NewGradeInputPopUp ngrade = new NewGradeInputPopUp(currCourse);
				ngrade.newGradePopUp();
				view.addCourseView(currCourse);
				//view.addGradeView(ngrade.getGrade().getName(), ngrade.getGrade().gradeRun()); //doesn't work			
			} else if (command.equals("Add Course")){
				EditOptionView ev = new EditOptionView(view, "Add Course");
				String courseString = ev.addPopUp();
				if (courseString== null) {return;}
				Course newCourse = new Course(courseString);
				currSem.addCourse(newCourse);
				view.updateTreeData(model.getSemesters());			
				ev.showSuccess("Success!");
			}else if (command.equals("Remove Grade")){
				NewGradeInputPopUp ngrade = new NewGradeInputPopUp(currCourse);
				ngrade.deleteGradePopUp();
				view.addCourseView(currCourse);
				System.out.println(command);
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
