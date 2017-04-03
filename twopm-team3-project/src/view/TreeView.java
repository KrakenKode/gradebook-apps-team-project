package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import model.Course;
import model.Semester;

public class TreeView {

	private JPanel mainpanel;
	private JTree tree;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private HashMap<String, DefaultMutableTreeNode> semesterMap;


	public TreeView(JPanel mainpanel) {
		this.mainpanel = mainpanel;
	}


	public void initializeTreeData(ArrayList<Semester> semdata) {

		root = new DefaultMutableTreeNode("Root");
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		JScrollPane treeScrollPane = new JScrollPane(tree);
		semesterMap = new HashMap<String, DefaultMutableTreeNode>();

		//build semester and add to root
		for(Semester sem : semdata){
			DefaultMutableTreeNode semester = new DefaultMutableTreeNode(sem.getName());

			ArrayList<Course> coursedata  = sem.getCourses();
			for(Course course : coursedata){
				semester.add(new DefaultMutableTreeNode(course.getName()));
			}
			semesterMap.put(sem.getName(), semester);
			root.add(semester);
		}

		treeModel.reload(root);
		tree.setEditable(false);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		Dimension scrolldim = treeScrollPane.getPreferredSize();
		scrolldim.width = 200;
		treeScrollPane.setPreferredSize(scrolldim);
		mainpanel.add(treeScrollPane, BorderLayout.WEST);

	}


	public void rebuildTree(ArrayList<Semester> semdata) {
		tree.setEditable(true);
		root.removeAllChildren();
		semesterMap.clear();

		//build semester and add to root
		for(Semester sem : semdata){
			DefaultMutableTreeNode semester = new DefaultMutableTreeNode(sem.getName());

			ArrayList<Course> coursedata  = sem.getCourses();
			for(Course course : coursedata){
				semester.add(new DefaultMutableTreeNode(course.getName()));
			}
			semesterMap.put(sem.getName(), semester);
			root.add(semester);
		}

		treeModel.reload(root);
		tree.setEditable(false);
	}


	public void addSemesterNode(Semester sem) {
		tree.setEditable(true);
		DefaultMutableTreeNode semester = new DefaultMutableTreeNode(sem.getName());
		semesterMap.put(sem.getName(), semester);
		root.add(semester);
		treeModel.reload(root);
		tree.setEditable(false);
	}


	public void addCourseNode(Semester sem, Course course) {
		//get the semester node from the hashmap
		tree.setEditable(true);
		DefaultMutableTreeNode semester = semesterMap.get(sem.getName());	
		semester.add(new DefaultMutableTreeNode(course.getName()));
		treeModel.reload(semester);
		tree.setEditable(false);
	}


	public void addTreeListener(TreeSelectionListener tsl, MouseListener l) {	
		tree.addTreeSelectionListener(tsl);
		tree.addMouseListener(l);
	}


	public JTree getTree() {
		return tree;
	}

}
