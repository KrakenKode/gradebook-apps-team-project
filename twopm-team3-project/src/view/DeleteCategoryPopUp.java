package view;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Category;
import model.Course;
import model.Grade;

public class DeleteCategoryPopUp {
	private Course course;
	
	//Retrieve the course from the caller
	public DeleteCategoryPopUp(Course course) {
		this.course = course;
	}
	
	public void deleteCategoryPopUp() {
		//Add new PopUp Panel
		JPanel rmCatPanel = new JPanel(new GridLayout(0, 2, 20, 20));
		//Set elements for the JComboBox
		DefaultComboBoxModel<Category> categoryModel = new DefaultComboBoxModel<Category>();
		//Get all categories under the course selected
		for (Category cat: course.getCategories()) {
			categoryModel.addElement(cat);
		}
		//Make a new ComboBox
		JComboBox<Category> categoryBox = new JComboBox<Category>(categoryModel);
		//Add each new component to the PopUp Panel
		rmCatPanel.add(new JLabel("Category to Delete"));
		rmCatPanel.add(categoryBox);
		//Popup the remove category panel
		int result = JOptionPane.showConfirmDialog(null, rmCatPanel, 
				"Remove Grade Information", JOptionPane.OK_CANCEL_OPTION);
		//If the user confirms iterate through course and delete selected category
		
		if (result == JOptionPane.OK_OPTION) {
			Object selObj = categoryBox.getSelectedItem();
			if (selObj instanceof Category) {
				Category rmCategory = (Category) selObj;
				for (Category cat : course.getCategories()) {
					if (rmCategory == cat) {
						course.removeCategory(rmCategory);
					}
				}
			}
		}
	}
}
