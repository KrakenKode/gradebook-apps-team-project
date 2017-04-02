package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
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
import view.DeleteGradePopUp;
import view.EditOptionView;
import view.GradeBookView;
import view.NewGradeInputPopUp;

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

		if (treeObject == null) {return;}

		// Cast the Object into a DefaultMutableTreeNode		
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeObject;

		// Returns the object stored in this node and casts it to a string			
		String treeNode = (String) node.getUserObject();
		if (treeNode == null) {return;}
		if (node.isLeaf()) {
			Object obj = model.determineTreeObject(treeNode);
			if(obj instanceof Course) {
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
			//get the object mouse was closest to
			Object obj = model.determineTreeObject(selPath.getLastPathComponent().toString()); 
			ActionListener ra = new JPopupMenuListener();
			//if the object was a semester object
			if (obj instanceof Semester) {
				//Make JPopupMenu for right click context
				currSelSem = (Semester) obj;
				JPopupMenu rc = new JPopupMenu();
				JMenuItem couradd = new JMenuItem();
				couradd.setText("Add Course");
				couradd.addActionListener(ra);
				rc.add(couradd);
				rc.show(e.getComponent(), e.getX(), e.getY());
				//if the object was a course object
			} else if (obj instanceof Course) {
				//Make JPopupMenu for right click context
				currSelCourse = (Course) obj;
				JPopupMenu rc = new JPopupMenu();
				JMenuItem catadd = new JMenuItem();
				catadd.setText("Add Category");
				catadd.addActionListener(ra);
				rc.add(catadd);
				JMenuItem gradeadd = new JMenuItem();
				gradeadd.setText("Add Grade");
				gradeadd.addActionListener(ra);
				rc.add(gradeadd);
				//rc.show(e.getComponent(), e.getX(), e.getY());
				rc.addSeparator();

				JMenuItem gradeRemove = new JMenuItem();
				gradeRemove.setText("Remove Grade");
				gradeRemove.addActionListener(ra);
				rc.add(gradeRemove);
				rc.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}


	class JPopupMenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("Add Category")) {
				EditOptionView ev = new EditOptionView(view, "Add Category");
				String categoryString = ev.addPopUp();
				if (categoryString == null) {return;}
				Category newCategory = new Category(categoryString);
				currSelCourse.addCategory(newCategory);
				view.addCategoryView(newCategory.getName());
			} else if(command.equals("Add Grade")) {
				NewGradeInputPopUp ngrade = new NewGradeInputPopUp(currSelCourse);
				ngrade.newGradePopUp();
				view.addCourseView(currSelCourse);
				//view.addGradeView(ngrade.getGrade().getName(), ngrade.getGrade().gradeRun()); //doesn't work			
			} else if(command.equals("Add Course")) {
				EditOptionView ev = new EditOptionView(view, "Add Course");
				String courseString = ev.addPopUp();
				if (courseString== null) {return;}
				Course newCourse = new Course(courseString);
				currSelSem.addCourse(newCourse);
				view.getTreeView().addCourseNode(currSelSem, newCourse);
			} else if(command.equals("Remove Grade")) {
				DeleteGradePopUp ngrade = new DeleteGradePopUp(currSelCourse);
				ngrade.deleteGradePopUp();
				view.addCourseView(currSelCourse);
				System.out.println(command);
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
