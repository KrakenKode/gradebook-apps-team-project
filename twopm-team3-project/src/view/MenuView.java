package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class MenuView {

	private JMenuItem saveMenu;
	private JMenuItem openMenu;
	private JMenuItem quitMenu;
	private JMenuItem addSemester;
	private JMenuItem semesterReport;
	private JMenuItem courseReport;


	public MenuView(JPanel mainpanel) {

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

		//Build the report menu
		JMenu reportMenu = new JMenu("Report");
		menuBar.add(reportMenu);

		semesterReport = new JMenuItem("Semester Report");
		courseReport = new JMenuItem("Course Report");
		reportMenu.add(semesterReport);
		reportMenu.add(courseReport);

		mainpanel.add(menuBar, BorderLayout.NORTH);
	}


	public void addMenuListener(ActionListener e) {		
		quitMenu.addActionListener(e);
		saveMenu.addActionListener(e);
		openMenu.addActionListener(e);	
		addSemester.addActionListener(e);
		semesterReport.addActionListener(e);
		courseReport.addActionListener(e);
	}
}
