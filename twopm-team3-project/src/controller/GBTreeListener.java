package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
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
import view.CommentPopUp;
import view.CourseView;
import view.DeleteCategoryPopUp;
import view.DeleteGradePopUp;
import view.EditGradeRangePopUp;
import view.GradeBookView;
import view.InputOptionView;
import view.NewGradeInputPopUp;


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
				currRSelCourse = (Course) obj;
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

	//View to Model saving functionality
	class JTextFieldListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Get current string in textfield
			String command = e.getActionCommand();
			//get the object selected
			Object obj = e.getSource();
			if (obj instanceof JTextField) {
				JTextField curSelField = (JTextField) obj;
				//Get the name for the selected JTextField to change name or points
				String selName = curSelField.getName();
				String split[] = selName.split(" ");
				String num = split[1];
				int result = Integer.parseInt(num);
				//Get the category from the name of the JPanel categoryPanel
				String selCat = curSelField.getParent().getParent().getName();
				ArrayList<Grade> gradeList = new ArrayList<Grade>();
				//Find category that matches currently selected one
				for (Category cat: lastSelCourse.getCategories()) {
					if (cat.getName() == selCat) {
						gradeList = cat.getGrades();
						break;
					}
				}
				if(selName.contains("GradeName")) {
					//Set the name of the grade
					gradeList.get(result).setName(command);
				} else if (selName.contains("Grade")) {
					//TODO fix input for grade points
					//Points is type int and Command is String
					//Thus "94.0%" needs to be "94" then cast into int
					gradeList.get(result).setPoints(Integer.parseInt((String) command.subSequence(0, 2)));
				}
			}
			
			//TODO add comment popup functionality
			if (command.equals("Comment")) {
				CommentPopUp comEdit = new CommentPopUp();
				
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
				if (newCategory.getName().equals("fail")) {return;}
				try {
					view.getCourseView().removeCourseView();
				} catch (Exception nPointer) {
					System.err.println("CoursePanel does not exist.");
				}
				currRSelCourse.addCategory(newCategory);
				view.getCourseView().addCategoryView(newCategory.getName());

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
				Course newCourse = new Course(courseString);
				currSelSem.addCourse(newCourse);
				view.getTreeView().addCourseNode(currSelSem, newCourse);
				
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
}
