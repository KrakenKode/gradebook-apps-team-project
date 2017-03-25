package controller;
import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;
import view.*;

public class GradeBookController implements ActionListener {

	private GradeBookModel model;
	
	
	private GradeBookView view;
	
	//view.LoadData(model.getSemesters());
	
	public GradeBookController(GradeBookModel model, GradeBookView view) {
		this.model = model;
		this.view = view;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
