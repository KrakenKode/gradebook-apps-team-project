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
			
		view.setVisible(true);
	}

}
