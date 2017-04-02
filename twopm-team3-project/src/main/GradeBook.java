package main;

import controller.*;
import model.*;
import view.*;


public class GradeBook {

	public static void main(String[] args) {

		GradeBookModel model = new GradeBookModel();
		GradeBookView view = new GradeBookView();
		GradeBookController controller = new GradeBookController(model, view);
			
		view.setVisible(true);
	}

}
