package eg.edu.alexu.cs.datastructures.classes;

import java.io.*;

import MyDataStructures.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;

public class User implements IUser {
	
	String name;
	SinglyLinkedList Emails = new SinglyLinkedList();
	String password;
	String userPath;
	// should be changed to contact
	
	// TODO: encrypt stored passwords
	User(String name, String Email, String password){
		
		if(this.name != null) // user made before
			return ; // TODO:
		
		/// TODO: check proper format & length for name and email and password 
		this.name = name;
		this.Emails.add(Email);
		this.password = password;
		
		String[] innerFoldersPath = new String[] {"inbox","draft","trash"};
		
		// create account folder
		userPath = App.accountsFolderPath + File.separator + name;
		File userFolder = new File(userPath);
		
		if(userFolder.exists() == true) {
			return;
		}
		
		userFolder.mkdir();
		
		// inner folder represents inbox, trash .. etc
		for (int i = 0; i < innerFoldersPath.length; i++) {
			String innerFolderPath = innerFoldersPath[i];
			File innerFolder = new File(userPath + 
					File.separator + innerFolderPath);
			
			innerFolder.mkdir();
			
			// make index File
			
			try {
				@SuppressWarnings("resource")
				FileOutputStream fileOutput = 
						new FileOutputStream(innerFolder.getAbsolutePath() + File.separator +
								"indexFile.dat");
			    String fileContent = "123";// TODO: make this part better
			    fileOutput.write(fileContent.getBytes());
			} catch (FileNotFoundException e) {
			    // exception handling
			} catch (IOException e) {
			    // exception handling
			}

		}
		
		
		
		
		
		
	}
	
	
}
