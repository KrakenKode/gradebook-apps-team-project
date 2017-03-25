package view;
import model.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

import main.*;


public class GradeBookView extends JFrame{

	private JTree tree;
	private JScrollPane treeView;
	private JPanel mainpanel;
	private DefaultMutableTreeNode root;
	private JTabbedPane tabbedPane;


	public GradeBookView(){
		super("GradeBook Management System");

		//window opens in middle of screen
		this.setLocationRelativeTo(null);	

		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainpanel = new JPanel(new BorderLayout());

		//root node gets hidden
		root = new DefaultMutableTreeNode("Root");

		
		this.setVisible(true);	
	}

	public void LoadData(ArrayList<Semester> semdata){
		//build semester and add to root
		for(Semester sem : semdata){
			DefaultMutableTreeNode semester = new DefaultMutableTreeNode(sem.getName());

			ArrayList<Course> coursedata  = sem.getCourses();
			for(Course course : coursedata){
				semester.add(new DefaultMutableTreeNode(course.getName()));
			}
			root.add(semester);
		}
		tree = new JTree(root);
		tree.setRootVisible(false);
		treeView = new JScrollPane(tree);
		this.add(mainpanel);
		mainpanel.add(treeView, BorderLayout.WEST);

	}


}