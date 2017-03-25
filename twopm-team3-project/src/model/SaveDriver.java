package model;

import java.io.*;
import java.util.ArrayList;

public class SaveDriver {
	
	private SaveDriver() {
		//nothing needed
	}
	
	
	public static void saveFile(ArrayList<Semester> semesters) {
				
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savefile.ser"));
			for(Semester curr : semesters) {
				out.writeObject(curr);
			}						
		} catch(Exception e) {
			e.printStackTrace();		
		}
		
	}
	
	
}
