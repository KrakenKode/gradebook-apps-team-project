package view;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EditOptionView{
	
	private JFrame mainview;
	private String msg;
	
	public EditOptionView(JFrame mainview, String msg){
		this.mainview = mainview;
		this.msg = msg;
	}
	
	public String addView(){
		String input = JOptionPane.showInputDialog(msg);
		if(input == null)
			showError("Null Input");
		
		showSuccess("Success!");
		return input;
	}
	public String EditView(){
		String input = JOptionPane.showInputDialog(msg);
		
		if(input == null)
			showError("Null Input");
		
		showSuccess("Update Success!");
		return input;
	}
	
	public void showSuccess(String successMsg){
		JOptionPane.showMessageDialog(mainview, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showError(String errMsg){
		JOptionPane.showMessageDialog(mainview, errMsg, "Error", JOptionPane.ERROR);
	}
}