package view;

import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;



public class GradeBookView extends JFrame{

	private JTree tree;
	private JPanel mainpanel;
	private JPanel coursePanel;
	private JPanel summaryPanel;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private JMenuItem saveMenu;
	private JMenuItem openMenu;
	private JMenuItem quitMenu;
	private JMenuItem addSemester;
	private JLabel courseLbl;
	private TitledBorder tlb;
	private JPanel totalPanel;
	private JPanel categoryPanel;
	private Box categoryBox;
	private JScrollPane coursepane;
	private JLabel categoryLbl;
	private JLabel categoryName;
	private JLabel run1;
	private Box gradeBox;
	private Box predictBox;
	private Box totalBox;
	
	
	
	public GradeBookView() {
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
		
		//mainpanel.add(selected, BorderLayout.EAST);
		this.setVisible(true);	

	}
	
	
	public void addMenu() {

		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		//Build the file menu.
		JMenu fileMenu = new JMenu("File");
		
		menuBar.add(fileMenu);
				
		openMenu = new JMenuItem("Open", KeyEvent.VK_O);
		openMenu.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		fileMenu.add(openMenu);
				
		saveMenu = new JMenuItem("Save", KeyEvent.VK_S);
		saveMenu.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		fileMenu.add(saveMenu);

		fileMenu.addSeparator();	//adds a line in the menu
	
		quitMenu = new JMenuItem("Quit", KeyEvent.VK_Q);
		quitMenu.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		fileMenu.add(quitMenu);
		
		//Build the edit menu.
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		addSemester = new JMenuItem("Add Semester");
		editMenu.add(addSemester);	
		
		mainpanel.add(menuBar, BorderLayout.NORTH);			
	}
	
	
	public void addCourseView(Course course) {
		
		//title and grade summary 
		courseLbl = new JLabel(course.getName());
		coursePanel = new JPanel(new GridLayout(0, 1, 2, 2));
		courseLbl.setFont(new Font(courseLbl.getFont().getName(), Font.PLAIN, 18));
		courseLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

		summaryPanel = new JPanel(new GridLayout(0 , 1, 0, 0));
		summaryPanel.setBackground(Color.RED);
		tlb = new TitledBorder("Grade Summary");
		totalPanel = new JPanel();
		totalPanel.setBackground(Color.pink);
		totalPanel.setBorder(tlb);
		totalBox = Box.createHorizontalBox();
		totalBox.add(new JLabel("Total"));
		totalBox.add(Box.createHorizontalStrut(100));
		totalBox.add(new JLabel("Current Grade"));
		totalBox.add(Box.createHorizontalStrut(100));
		
		String[] letterList = { "Desired","A", "B", "C", "D", "E" };

		JComboBox gradeList = new JComboBox(letterList);
		gradeList.setSelectedIndex(0);
		totalBox.add(gradeList);
		
		totalPanel.add(totalBox);
		
		//Grade Summary Box
		Box summaryBox = Box.createVerticalBox();
		summaryBox.add(courseLbl);
		summaryBox.add(Box.createVerticalStrut(8));
		summaryBox.add(totalPanel);
		summaryPanel.add(summaryBox, BorderLayout.CENTER);
		coursePanel.add(summaryPanel);
		
		//category selection	
		ArrayList<Category> categorydata = course.getCategories();
		for(Category category : categorydata ){
			this.addCategoryView(category.getName());

			ArrayList<Grade> gradedata = category.getGrades();
			for(Grade grade : gradedata ){
				this.addGradeView(grade.getName(), grade.gradeRun());
			}
			
		}

		coursepane = new JScrollPane(coursePanel);
//		coursepane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
//		coursepane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
		mainpanel.add(coursepane);
		this.revalidate();
	}
	public void addGradeView1(String gradeName, Double grade){
		
	}
	
	
	public void addGradeView(String gradeName, Double grade) {
		categoryName = new JLabel(gradeName);

		run1 = new JLabel(Math.round(grade*100.0)/100.0 + "%");

		categoryBox.add(categoryName);
		categoryBox.add(Box.createVerticalStrut(25));
		
		gradeBox.add(run1);
		gradeBox.add(Box.createVerticalStrut(25));
		
		predictBox.add(new JLabel("--%"));
		predictBox.add(Box.createVerticalStrut(25));
		
		categoryPanel.add(categoryBox);
		categoryPanel.add(gradeBox);
		categoryPanel.add(predictBox);
		
		categoryPanel.setBorder(new EmptyBorder(20, 70, 20, 0));
		coursePanel.add(categoryPanel);
		this.revalidate();
	}
	

	
	public void addCategoryView(String category){
		categoryPanel = new JPanel(new GridLayout(1,3,2,2));
		categoryPanel.setBackground(Color.lightGray);
		
		categoryLbl = new JLabel(category, JLabel.LEFT);
		
		//categoryBox holds - Homework, HW1, HW2 - vertically
		categoryBox = Box.createVerticalBox();
		categoryBox.add(categoryLbl);
		categoryBox.add(Box.createVerticalStrut(25));
		
		gradeBox = Box.createVerticalBox();
		gradeBox.add(new JLabel("Grade", JLabel.CENTER));
		gradeBox.add(Box.createVerticalStrut(25));
		
		predictBox = Box.createVerticalBox();
		predictBox.add(new JLabel("Prediction"));
		predictBox.add(Box.createVerticalStrut(25));
		
		categoryPanel.add(categoryBox);
		categoryPanel.add(gradeBox);
		categoryPanel.add(predictBox);
		
		categoryPanel.setBorder(new EmptyBorder(20, 70, 20, 0));
		coursePanel.add(categoryPanel);
		this.revalidate();
	}
	
	
	public void initializeTreeData(ArrayList<Semester> semdata) {
		
		root = new DefaultMutableTreeNode("Root");
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		JScrollPane treeView = new JScrollPane(tree);
		
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
				
		Dimension scrolldim = treeView.getPreferredSize();
		scrolldim.width = 200;
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
	
	
	public void addTreeListener(TreeSelectionListener tsl, MouseListener l) {	
		tree.addTreeSelectionListener(tsl);
		tree.addMouseListener(l);
	}
	
	
	public void addMenuListener(ActionListener e) {		
		quitMenu.addActionListener(e);
		saveMenu.addActionListener(e);
		openMenu.addActionListener(e);	
		addSemester.addActionListener(e);
	}
	
	
	public JTree getTree() {
		return tree;
	}
	
	
	public void setLabel(String lbl) {
		courseLbl.setText(lbl);
	}


}