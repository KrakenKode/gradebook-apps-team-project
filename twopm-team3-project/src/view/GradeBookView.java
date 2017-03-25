package view;
import model.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;


public class GradeBookView extends JFrame{

	private JTree tree;
	private JScrollPane treeView;
	private JPanel panel;
	private DefaultMutableTreeNode root;


	public GradeBookView(){
		super("GradeBook Management System");

		//window opens in middle of screen
		this.setLocationRelativeTo(null);	

		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel(new BorderLayout());



		//root node gets hidden
		root = new DefaultMutableTreeNode("Root");
		

		//build fall2016 semester and add to root
		DefaultMutableTreeNode semester = new DefaultMutableTreeNode("Fall 2016");
		semester.add(new DefaultMutableTreeNode("Discrete Math"));
		semester.add(new DefaultMutableTreeNode("Math Foundations"));
		semester.add(new DefaultMutableTreeNode("Data Structures"));
		semester.add(new DefaultMutableTreeNode("Game Programming"));
		root.add(semester);

		//build fall2017 semester
		semester = new DefaultMutableTreeNode("Spring 2017");
		semester.add(new DefaultMutableTreeNode("Algorithms"));
		semester.add(new DefaultMutableTreeNode("Systems"));
		semester.add(new DefaultMutableTreeNode("CompOrg"));
		semester.add(new DefaultMutableTreeNode("Applications"));
		root.add(semester);


		semester = new DefaultMutableTreeNode("Fall 2017");
		semester.add(new DefaultMutableTreeNode("C"));
		semester.add(new DefaultMutableTreeNode("Java"));
		semester.add(new DefaultMutableTreeNode("Python"));
		semester.add(new DefaultMutableTreeNode("JavaFx"));
		root.add(semester);

		tree = new JTree(root);
		
		tree.setRootVisible(false);
		treeView = new JScrollPane(tree);
		
		this.add(panel);
		panel.add(treeView, BorderLayout.WEST);

		this.setVisible(true);	
	}

	public void LoadData(ArrayList<Semester> semdata){
		//build semester and add to root
		for(Semester sem : semdata){
			System.out.println(sem);
			DefaultMutableTreeNode semester = new DefaultMutableTreeNode(sem.getName());

			ArrayList<Course> coursedata  = sem.getCourses();
			for(Course course : coursedata){
				semester.add(new DefaultMutableTreeNode(course.getName()));
			}
			root.add(semester);
		}

	}


}