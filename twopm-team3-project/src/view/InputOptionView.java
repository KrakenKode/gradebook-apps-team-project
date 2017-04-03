package view;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	
	public void showSuccess(String successMsg){
		JOptionPane.showMessageDialog(mainview, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showError(String errMsg){
		JOptionPane.showMessageDialog(mainview, errMsg, "Error", JOptionPane.ERROR);
	}
}