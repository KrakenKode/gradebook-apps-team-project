package controller;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import model.Category;
import model.Course;
import model.GradeBookModel;
import model.Semester;
import view.*;


class GBTreeListener implements TreeSelectionListener, MouseListener {

	private GradeBookView view;
	private GradeBookModel model;
	private JTree tree;
	private Course currSelCourse;
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
				//setting up courseView

				CourseView courseView = view.getCourseView();
				courseView.addTextActionListener(new JTextFieldListener());
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
				JMenuItem couradd = new JMenuItem();
				couradd.setText("Add Course");
				couradd.addActionListener(ClickAction);
				RClickMenu.add(couradd);
				RClickMenu.show(e.getComponent(), e.getX(), e.getY());
				//if the object was a course object
			} else if (obj instanceof Course) {
				//Make JPopupMenu for right click context
				currSelCourse = (Course) obj;
				JPopupMenu RClickMenu = new JPopupMenu();
				JMenuItem catadd = new JMenuItem();
				catadd.setText("Add Category");
				catadd.addActionListener(ClickAction);
				RClickMenu.add(catadd);
				JMenuItem gradeadd = new JMenuItem();
				gradeadd.setText("Add Grade");
				gradeadd.addActionListener(ClickAction);
				RClickMenu.add(gradeadd);
				//rc.show(e.getComponent(), e.getX(), e.getY());
				RClickMenu.addSeparator();
				//add grade remove option to JPopupMenu
				JMenuItem gradeRemove = new JMenuItem();
				gradeRemove.setText("Remove Grade");
				gradeRemove.addActionListener(ClickAction);
				RClickMenu.add(gradeRemove);
				//add category remove option to JPopupMenu
				JMenuItem categoryRemove = new JMenuItem();
				categoryRemove.setText("Remove Category");
				categoryRemove.addActionListener(ClickAction);
				RClickMenu.add(categoryRemove);
				RClickMenu.addSeparator();
				JMenuItem editGradeRange = new JMenuItem();
				editGradeRange.setText("Edit Grade Range");
				editGradeRange.addActionListener(ClickAction);
				RClickMenu.add(editGradeRange);
				
				RClickMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	class JTextFieldListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			int nice = e.hashCode();
			System.out.println(command);
			System.out.println(nice);

		}

	}


	class JPopupMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("Add Category")) {
				InputOptionView ev = new InputOptionView(view, "Add Category");
				Category newCategory = ev.addCategory();
				if (newCategory.getName().equals("fail")) {return;}
				currSelCourse.addCategory(newCategory);
				view.getCourseView().addCategoryView(newCategory.getName());

			} else if(command.equals("Add Grade")) {
				NewGradeInputPopUp ngrade = new NewGradeInputPopUp(currSelCourse);
				ngrade.newGradePopUp();
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				view.getCourseView().addCourseView(currSelCourse);
				
			} else if(command.equals("Add Course")) {
				InputOptionView ev = new InputOptionView(view, "Add Course");
				String courseString = ev.addPopUp();
				if (courseString== null) {return;}
				Course newCourse = new Course(courseString);
				currSelSem.addCourse(newCourse);
				view.getTreeView().addCourseNode(currSelSem, newCourse);
				
			} else if(command.equals("Remove Grade")) {
				DeleteGradePopUp ngrade = new DeleteGradePopUp(currSelCourse);
				ngrade.deleteGradePopUp();
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				view.getCourseView().addCourseView(currSelCourse);
			} else if(command.equals("Remove Category")) {
				DeleteCategoryPopUp rmCat = new DeleteCategoryPopUp(currSelCourse);
				rmCat.deleteCategoryPopUp();
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				view.getCourseView().addCourseView(currSelCourse);
			} else if (command.equals("Edit Grade Range")) {
				//Creates new edit grade range pop up
				EditGradeRangePopUp gradeR = new EditGradeRangePopUp(currSelCourse);
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
}
