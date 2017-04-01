package view;

import model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private JMenu editMenu;
	private JMenuItem addSemester;
	private Dimension scrolldim;
	private JLabel courseLbl;
	private JLabel totalScore;
	private JLabel pcntScore;
	private TitledBorder tlb;
	private JPanel totalPanel;
	private JPanel categoryPanel;
	private Box categoryBox;
	
	
	
	public GradeBookView(){
		super("GradeBook Management System");

		//window opens in middle of screen
		this.setSize(1000, 800);
		this.setLocationRelativeTo(null);	
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//main panel settings
		mainpanel = new JPanel(new BorderLayout());
		mainpanel.setBorder(new EmptyBorder(2, 2, 0, 2));
			
		addMenu();
		//addCourseView();
		
		//mainpanel.add(selected, BorderLayout.EAST);
		this.setVisible(true);	

	}
	
	
	public void addMenu(){

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the file menu.
		fileMenu = new JMenu("File");
		
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
		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		addSemester = new JMenuItem("Add Semester");
		editMenu.add(addSemester);	
		
		mainpanel.add(menuBar, BorderLayout.NORTH);			
	}
	
	
	public void addCourseView(Course course){
		
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
			categoryPanel = new JPanel();
			categoryPanel.setBackground(Color.GREEN);
			categoryBox = Box.createHorizontalBox();
			JLabel categoryLbl = new JLabel(category.getName());

			Box box1 = Box.createVerticalBox();
			box1.add(categoryLbl);
			Box box2 = Box.createVerticalBox();
			JLabel runningLbl = new JLabel("Running %");
			box2.add(runningLbl);

			ArrayList<Grade> gradedata = category.getGrades();
			for(Grade grade : gradedata ){
				JLabel categoryName = new JLabel(grade.getName());
				box1.add(categoryName);
				box1.add(Box.createVerticalStrut(20));
				JLabel run1 = new JLabel("80%");
				box2.add(run1);
				box2.add(Box.createVerticalStrut(20));
				categoryBox.add(box1);
				categoryBox.add(Box.createHorizontalStrut(200));
				categoryBox.add(box2);
			}
				
				categoryPanel.add(categoryBox);
				coursePanel.add(categoryPanel);
			}

		JScrollPane coursepane = new JScrollPane(coursePanel);
		mainpanel.add(coursepane, BorderLayout.CENTER);
		this.revalidate();
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
	
	
	public void addTreeListener(TreeSelectionListener tsl, MouseListener l){
		
		tree.addTreeSelectionListener(tsl);
		tree.addMouseListener(l);;
	}
	public void addMenuListener(ActionListener e){		
	
		quitMenu.addActionListener(e);
		saveMenu.addActionListener(e);
		openMenu.addActionListener(e);	
		addSemester.addActionListener(e);
	}
	
	
	public JTree getTree(){
		return tree;
	}
	
	
	public void setLable(String lbl){
		courseLbl.setText(lbl);
	}


}