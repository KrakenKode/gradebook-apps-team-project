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
		tabbedPane = new JTabbedPane();
		
	ImageIcon icon = createImageIcon("images/middle.gif");

		
		JComponent panel1 = makeTextPanel("Panel #1");
		tabbedPane.addTab("Tab 1", icon, panel1,
				"Does nothing");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


		mainpanel.add(tabbedPane, BorderLayout.EAST);

		//root node gets hidden
		root = new DefaultMutableTreeNode("Root");

		tree = new JTree(root);

		tree.setRootVisible(false);
		treeView = new JScrollPane(tree);

		this.add(mainpanel);
		mainpanel.add(treeView, BorderLayout.WEST);

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

	}
	public void addTab(Semester sem, Course course){
		//add tab when course is clicked
		ImageIcon icon = createImageIcon("images/middle.gif");

		
		JComponent panel1 = makeTextPanel("Panel #1");
		tabbedPane.addTab(course.getName(), icon, panel1,
				sem.getName());
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);


	}
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = GradeBook.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}


}