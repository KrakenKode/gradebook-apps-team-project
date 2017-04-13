package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class GradeBookView extends JFrame{

	private JPanel mainpanel;
	private TreeView treeView;
	private MenuView menuView;
	private CourseView courseView;

	public GradeBookView() {
		super("GradeBook Management System");

		//window opens in middle of screen
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);	
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//main panel settings
		mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(new EmptyBorder(2, 2, 0, 2));
		this.add(mainpanel);

		treeView = new TreeView(mainpanel);
		menuView = new MenuView(mainpanel);
		courseView = new CourseView(mainpanel);
		this.setVisible(true);	

	}


	public TreeView getTreeView() {
		return treeView;
	}


	public MenuView getMenuView() {
		return menuView;
	}
	
	public CourseView getCourseView(){
		return courseView;
	}

}