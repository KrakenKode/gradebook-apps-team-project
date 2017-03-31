package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public final class SaveOpenDriver {

	private SaveOpenDriver() {}
	
	
	public static void saveSemesters(ArrayList<Semester> semesters, String file) {
		ObjectOutputStream out = null;	
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			for(Semester curr : semesters) {
				out.writeObject(curr);
			}						
		} catch(Exception e) {
			e.printStackTrace();		
		}
		
		// close the file
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void readSemesters(ArrayList<Semester> semesters, String file) {
		Object curr;
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			
			while((curr = in.readObject()) != null) {
				
				if(curr instanceof Semester){
					semesters.add((Semester) curr);
				}
							
			}
						
		} catch(EOFException e) {
			return;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// close the file
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	
	
	
	
}
