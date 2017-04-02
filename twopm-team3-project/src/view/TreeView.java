package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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

	
	public TreeView(JPanel mainpanel) {
		this.mainpanel = mainpanel;
	}

	
	public void initializeTreeData(ArrayList<Semester> semdata) {

		root = new DefaultMutableTreeNode("Root");
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		JScrollPane treeScrollPane = new JScrollPane(tree);

		//build semester and add to root
		for(Semester sem : semdata){
			DefaultMutableTreeNode semester = new DefaultMutableTreeNode(sem.getName());

			ArrayList<Course> coursedata  = sem.getCourses();
			for(Course course : coursedata){
				semester.add(new DefaultMutableTreeNode(course.getName()));
			}
			root.add(semester);
		}

		treeModel.reload(root);
		tree.setEditable(true);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		Dimension scrolldim = treeScrollPane.getPreferredSize();
		scrolldim.width = 200;
		treeScrollPane.setPreferredSize(scrolldim);
		mainpanel.add(treeScrollPane, BorderLayout.WEST);

	}
	
	
	public void updateTreeData(ArrayList<Semester> semdata) {
		root.removeAllChildren();
		
		//build semester and add to root
		for(Semester sem : semdata){
			DefaultMutableTreeNode semester = new DefaultMutableTreeNode(sem.getName());

			ArrayList<Course> coursedata  = sem.getCourses();
			for(Course course : coursedata){
				semester.add(new DefaultMutableTreeNode(course.getName()));
			}
			root.add(semester);
		}
				
		treeModel.reload(root);
	}
	
	
	public void addTreeListener(TreeSelectionListener tsl, MouseListener l) {	
		tree.addTreeSelectionListener(tsl);
		tree.addMouseListener(l);
	}

	
	public JTree getTree() {
		return tree;
	}

}
