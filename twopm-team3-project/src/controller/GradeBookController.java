package controller;
import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class GradeBookController implements ActionListener {
	
	private GradeBookView view;
	private GradeBookModel model;
	

	public GradeBookController(GradeBookModel model, GradeBookView view) {
		this.model = model;
		this.view = view;
		//TODO functions for adding classes to view
		
		//view.LoadData(model.getSemesters()); load semester to view
	}
	
	public void loadData(){
		
	}
	
	class MenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Open")){
				
			} else if (e.equals("Save")){
				
			}
		}
	}
	
	class TreeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
		}
	}
	
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
