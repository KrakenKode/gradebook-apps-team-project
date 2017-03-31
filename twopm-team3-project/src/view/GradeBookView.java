package view;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.ByteOrder;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import main.*;


public class GradeBookView extends JFrame{

	private JTree tree;
	private JScrollPane treeView;
	private JPanel mainpanel;
	private JPanel coursePanel;
	private JPanel scorePanel;
	private DefaultMutableTreeNode root;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveMenu;
	private JMenuItem openMenu;
	private JMenuItem quitMenu;
	private Dimension scrolldim;
	private JLabel selected;
	private JLabel totalScore;
	private JLabel pcntScore;
	public GradeBookView(){
		super("GradeBook Management System");

		//window opens in middle of screen
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);	
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//main panel settings
		mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(new EmptyBorder(2, 2, 0, 2));
		
		selected = new JLabel("");
		
		addMenu();
		addCourseView();
		
		//mainpanel.add(selected, BorderLayout.EAST);
		this.setVisible(true);	
	}
	
	public void addMenu(){

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(fileMenu);
	
		
		//menu.addSeparator();	//adds a line in the menu

		openMenu = new JMenuItem("Open", KeyEvent.VK_O);
		fileMenu.add(openMenu);
		
		saveMenu = new JMenuItem("Save", KeyEvent.VK_S);
		fileMenu.add(saveMenu);

		quitMenu = new JMenuItem("Quit", KeyEvent.VK_Q);
		fileMenu.add(quitMenu);
		mainpanel.add(menuBar, BorderLayout.NORTH);	
	}
	
	public void addCourseView(){
		coursePanel = new JPanel(new BorderLayout());
		selected.setFont(new Font(selected.getFont().getName(), Font.PLAIN, 20));
		selected.setHorizontalAlignment(JLabel.CENTER);
		
		coursePanel.add(selected, BorderLayout.NORTH);
		mainpanel.add(coursePanel, BorderLayout.CENTER);
	}
	
	public void addTreeData(ArrayList<Semester> semdata){
		root = new DefaultMutableTreeNode("Root");
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
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		
		treeView = new JScrollPane(tree);
		scrolldim = treeView.getPreferredSize();
		scrolldim.width = 150;
		treeView.setPreferredSize(scrolldim);
		this.add(mainpanel);
		mainpanel.add(treeView, BorderLayout.WEST);
		
	}
	
	public void addTreeListener(TreeSelectionListener tsl ){
		
		tree.addTreeSelectionListener(tsl);
		
	}
	
	public void addMenuListener(ActionListener e){
		
		quitMenu.addActionListener(e);
		saveMenu.addActionListener(e);
		openMenu.addActionListener(e);
		
	}
	
	public JTree getTree(){
		return tree;
	}
	
	public void setLable(String lbl){
		selected.setText(lbl);
	}


}