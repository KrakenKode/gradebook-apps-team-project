package view;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem saveMenu;
	private JMenuItem openMenu;
	private JMenuItem quitMenu;

	public GradeBookView(){
		super("GradeBook Management System");

		//window opens in middle of screen
		this.setLocationRelativeTo(null);	

		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainpanel = new JPanel(new BorderLayout());

		//root node gets hidden
		root = new DefaultMutableTreeNode("Root");

		addMenu();
		this.setVisible(true);	
	}
	
	public void addMenu(){

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);
	
		
		//menu.addSeparator();	//adds a line in the menu

		openMenu = new JMenuItem("Open", KeyEvent.VK_O);
		menu.add(openMenu);
		
		saveMenu = new JMenuItem("Save");
		menu.add(saveMenu);

		quitMenu = new JMenuItem("Quit", KeyEvent.VK_Q);
		menu.add(quitMenu);
		mainpanel.add(menuBar, BorderLayout.NORTH);	
	}
	
	public void addMenuListener(ActionListener e){
		quitMenu.addActionListener(e);
		saveMenu.addActionListener(e);
		openMenu.addActionListener(e);
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