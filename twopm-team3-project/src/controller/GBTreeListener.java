package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import model.Category;
import model.Course;
import model.Grade;
import model.GradeBookModel;
import model.Semester;
import view.CourseView;
import view.DeleteCategoryPopUp;
import view.DeleteGradePopUp;
import view.EditGradeRangePopUp;
import view.GradeBookView;
import view.InputOptionView;
import view.NewGradeInputPopUp;

/**
 * GBTreeListener contains all the controller
 * functionality pertaining to the jTree. It allows the
 * tree to add and delete nodes, as well as a right click
 * pop up menu to perform actions.
 */
class GBTreeListener implements TreeSelectionListener, MouseListener {

	private GradeBookView view;
	private GradeBookModel model;
	private JTree tree;
	private Course currRSelCourse;
	private Course lastSelCourse;
	private Semester currSelSem;

	public GBTreeListener(GradeBookModel model, GradeBookView view) {
		this.model = model;
		this.view = view;
		tree = view.getTreeView().getTree();
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		Object treeObject = tree.getLastSelectedPathComponent();

		if (treeObject == null) {
			JPanel welcomeView = new JPanel();
			JLabel welcome = new JLabel();
			return;
		}

		// Cast the Object into a DefaultMutableTreeNode		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeObject;

		// Returns the object stored in this node and casts it to a string			
		String treeNode = (String) node.getUserObject();
		if (treeNode == null) {return;}
		if (node.isLeaf()) {
			Object obj = model.determineTreeObject(treeNode);
			if(obj instanceof Course) {
				Course course = (Course) obj;
				lastSelCourse = (Course) obj;
				//setting up courseView

				CourseView courseView = view.getCourseView();
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				courseView.addCourseView(course);			
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
			//get the object mouse was closest to
			Object obj = model.determineTreeObject(selPath.getLastPathComponent().toString()); 
			ActionListener ClickAction = new JPopupMenuListener();
			//if the object was a semester object
			if (obj instanceof Semester) {
				//Make JPopupMenu for right click context
				currSelSem = (Semester) obj;
				JPopupMenu RClickMenu = new JPopupMenu();
				JMenuItem newMenuItem = new JMenuItem();
				
				newMenuItem.setText("Add Course");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
				
				newMenuItem = new JMenuItem();
				newMenuItem.setText("Delete Semester");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
							
				RClickMenu.show(e.getComponent(), e.getX(), e.getY());
				//if the object was a course object
			} else if (obj instanceof Course) {
				//Make JPopupMenu for right click context on course
				currRSelCourse = (Course) obj;
				JPopupMenu RClickMenu = new JPopupMenu();						
				JMenuItem newMenuItem = new JMenuItem();
				
				newMenuItem.setText("Add Category");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
				
				newMenuItem = new JMenuItem();
				newMenuItem.setText("Add Grade");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
				
				RClickMenu.addSeparator();
				
				//add grade remove option to JPopupMenu
				newMenuItem = new JMenuItem();
				newMenuItem.setText("Remove Grade");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
				
				//add category remove option to JPopupMenu
				newMenuItem = new JMenuItem();
				newMenuItem.setText("Remove Category");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
				
				//add remove course option...
				newMenuItem = new JMenuItem();
				newMenuItem.setText("Delete Course");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
				
				RClickMenu.addSeparator();
				
				newMenuItem = new JMenuItem();
				newMenuItem.setText("Edit Grade Range");
				newMenuItem.addActionListener(ClickAction);
				RClickMenu.add(newMenuItem);
				
				RClickMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	class JPopupMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("Add Category")) {
				InputOptionView nCat = new InputOptionView(view, "Add Category");
				Category newCategory = nCat.addCategory();
				if (newCategory == null) {return;}
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				currRSelCourse.addCategory(newCategory);
				view.getCourseView().addCourseView(currRSelCourse);

			} else if(command.equals("Add Grade")) {
				NewGradeInputPopUp ngrade = new NewGradeInputPopUp(currRSelCourse);
				ngrade.newGradePopUp();
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				view.getCourseView().addCourseView(currRSelCourse);
				
			} else if(command.equals("Add Course")) {
				InputOptionView nCourse = new InputOptionView(view, "Add Course");
				String courseString = nCourse.addPopUp();
				if (courseString== null) {return;}
				Course newCourse = new Course(courseString, currSelSem);
				currSelSem.addCourse(newCourse);
				view.getTreeView().addCourseNode(currSelSem, newCourse);
				
			} else if(command.equals("Delete Semester")) {
				//gives the user a confirmation pop up before deleting
				int n = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to delete " + currSelSem.getName() + "?", 
						"Confirmation needed", JOptionPane.YES_NO_OPTION);
				//delete semester
				if(n == JOptionPane.YES_OPTION) {
					view.getTreeView().removeSemesterNode(currSelSem);
					model.removeSemester(currSelSem);
				}
				
			} else if(command.equals("Remove Grade")) {
				DeleteGradePopUp ngrade = new DeleteGradePopUp(currRSelCourse);
				ngrade.deleteGradePopUp();
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				view.getCourseView().addCourseView(currRSelCourse);
			} else if(command.equals("Remove Category")) {
				//Call to add a new removal window
				DeleteCategoryPopUp rmCat = new DeleteCategoryPopUp(currRSelCourse);
				rmCat.deleteCategoryPopUp();
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				view.getCourseView().addCourseView(currRSelCourse);
			} else if (command.equals("Delete Course")) {			
				//gives the user a confirmation pop up before deleting
				int n = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to delete " + currRSelCourse.getName() + "?", 
						"Confirmation needed", JOptionPane.YES_NO_OPTION);
				
				if(n == JOptionPane.YES_OPTION) {
					//get parent semester for the course
					Semester parentSem = currRSelCourse.getParentSem();
					
					//remove the course from the treeview
					view.getTreeView().removeCourseNode(parentSem, currRSelCourse);
									
					//remove the course from the parent Semester in model	
					parentSem.removeCourse(currRSelCourse);					
				}
							
			} else if (command.equals("Edit Grade Range")) {
				//Creates new edit grade range pop up
				EditGradeRangePopUp gradeR = new EditGradeRangePopUp(currRSelCourse);
				gradeR.gradeRangePopUp();
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
	
	public Course getLastSelCourse() {
		return lastSelCourse;
		
	}
}
