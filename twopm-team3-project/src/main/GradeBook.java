package main;

import javax.swing.JFrame;

import controller.*;
import model.*;
import view.*;


public class GradeBook {

	public static void main(String[] args) {

		GradeBookModel model = new GradeBookModel();
		GradeBookView view = new GradeBookView();
		GradeBookController controller = new GradeBookController(model, view);
		
		// view.registerListener(controller);
		
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(800, 600);
		view.setVisible(true);
		view.setLocationRelativeTo(null); //center JFrame
	}

}
