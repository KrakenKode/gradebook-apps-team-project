package view;


import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Category;

public class InputOptionView{
	
	private JFrame mainview;
	private String msg;
	
	public InputOptionView(JFrame mainview, String msg){
		this.mainview = mainview;
		this.msg = msg;
	}
	
	public String addPopUp(){
		String input = JOptionPane.showInputDialog(msg);
		return input;
	}
	
	public Category addCategory() {
		JPanel panel = new JPanel(new GridLayout(0, 2, 2, 2));
		JLabel cat = new JLabel("Name of Category");
		JLabel weight = new JLabel("Weight of Category");
		JTextField first = new JTextField(10);
		JTextField second = new JTextField(10);
		panel.add(cat);
		panel.add(first);
		panel.add(weight);
		panel.add(second);	
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Enter Category Information", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION){
			Category newCategory = new Category(first.getText(), Double.parseDouble(second.getText()));
			return newCategory;
		}	
		return null;
	}

}