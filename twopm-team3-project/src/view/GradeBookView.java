package view;

import model.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;



public class GradeBookView extends JFrame{

	private JTree tree;
	private JScrollPane treeView;
	private JPanel mainpanel;
	private JPanel coursePanel;
	private JPanel summaryPanel;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem saveMenu;
	private JMenuItem openMenu;
	private JMenuItem quitMenu;
	private Dimension scrolldim;
	private JLabel courseLbl;
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
		
		
		//title and grade summary 
		courseLbl = new JLabel("Course Name");
		coursePanel = new JPanel(new GridLayout(0, 1, 2, 2));
		courseLbl.setFont(new Font(courseLbl.getFont().getName(), Font.PLAIN, 18));
		courseLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		TitledBorder tlb = new TitledBorder("Course Name");
		JPanel totalPanel = new JPanel();
		totalPanel.setBackground(Color.pink);
		totalPanel.setBorder(tlb);
		
		summaryPanel = new JPanel(new GridLayout(0 , 1, 0, 0));
		summaryPanel.setBackground(Color.RED);
		
		Box summaryBox = Box.createVerticalBox();
		summaryBox.add(courseLbl);
		summaryBox.add(Box.createVerticalStrut(8));
		summaryBox.add(totalPanel);
		summaryPanel.add(summaryBox, BorderLayout.CENTER);
		coursePanel.add(summaryPanel);
			
		//first category selection
		JPanel categoryPanel = new JPanel();
		categoryPanel.setBackground(Color.GREEN);
		
		JLabel categoryLbl = new JLabel("Homework");
		JLabel runningLbl = new JLabel("Running %");
		JLabel totalpLbl = new JLabel ("Total %");
		
		Box categoryBox = Box.createHorizontalBox();
		categoryBox.add(categoryLbl);
		categoryBox.add(Box.createHorizontalStrut(130));
		categoryBox.add(runningLbl);
		categoryBox.add(Box.createHorizontalStrut(150));
		categoryBox.add(totalpLbl);
		
		JPanel results = new JPanel();
		results.setBackground(Color.GREEN);
		
		JLabel hw1 = new JLabel("HW1");
		JLabel run1 = new JLabel("80%");
		JLabel total1 = new JLabel ("100%");
		
		Box categoryBox1 = Box.createHorizontalBox();
		categoryBox1.add(hw1);
		categoryBox1.add(Box.createHorizontalStrut(190));
		categoryBox1.add(run1);
		categoryBox1.add(Box.createHorizontalStrut(190));
		categoryBox1.add(total1);
		
		results.add(categoryBox1);
		categoryPanel.add(categoryBox);
		categoryPanel.add(results);
		coursePanel.add(categoryPanel);
		
		JPanel scorePanel2 = new JPanel();
		scorePanel2.setBackground(Color.blue);
		
		JPanel scorePanel3 = new JPanel();
		scorePanel3.setBackground(Color.orange);
		
		
		coursePanel.add(scorePanel2);
		coursePanel.add(scorePanel3);
		mainpanel.add(coursePanel, BorderLayout.CENTER);
	}
	
	
	public void initializeTreeData(ArrayList<Semester> semdata){
		
		root = new DefaultMutableTreeNode("Root");
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		treeView = new JScrollPane(tree);
		
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
				
		scrolldim = treeView.getPreferredSize();
		scrolldim.width = 150;
		treeView.setPreferredSize(scrolldim);
		this.add(mainpanel);
		mainpanel.add(treeView, BorderLayout.WEST);
		
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
		courseLbl.setText(lbl);
	}


}