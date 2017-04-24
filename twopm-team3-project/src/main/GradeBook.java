package main;

import controller.*;
import model.*;
import view.*;

/**
 * Main class that gets the the entire project 
 * up and running.
 */
public class GradeBook {

	public static void main(String[] args) {

		GradeBookModel model = new GradeBookModel();
		GradeBookView view = new GradeBookView();
		GradeBookController controller = new GradeBookController(model, view);
			
		view.setVisible(true);
	}

}
