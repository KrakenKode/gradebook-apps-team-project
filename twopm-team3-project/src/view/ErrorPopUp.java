package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorPopUp {
	private JFrame mainview;
	private String msg;
	
	public ErrorPopUp(JFrame mainview, String msg){
		this.mainview = mainview;
		this.msg = msg;
	}
	
	public void showSuccess(String successMsg){
		JOptionPane.showMessageDialog(mainview, successMsg, msg, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showError(String errMsg){
		JOptionPane.showMessageDialog(mainview, errMsg, msg, JOptionPane.WARNING_MESSAGE);
	}
}
