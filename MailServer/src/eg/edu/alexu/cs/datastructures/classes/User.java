package eg.edu.alexu.cs.datastructures.classes;



import java.io.File;
import java.io.Serializable;

import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;



class User implements IUser, Serializable {
	
	
	/*
	 * u should use writeUserToFile method after any updates to the user
	 */
	
	private static final long serialVersionUID = 1L;
	String name;
	String password;
	String userFolderPath;
	File folder;
	SinglyLinkedList emails = new SinglyLinkedList();
	SinglyLinkedList innerFolders = new SinglyLinkedList();
	SinglyLinkedList userFoldersPaths = new SinglyLinkedList();
	// should be changed to contact
	
	// TODO: encrypt stored passwords
	User(String name, String email, String password) {
		
		// TODO: check proper format
		this.name = name;
		this.emails.add(email);
		this.password = password;
		
		store();
	}
	
	void store(){
		makeInitialFiles();
		writeUserToFile();
	}
	
	void makeInitialFiles() {
		
		// create account folder
		userFolderPath = App.accountsFolderPath + File.separator + this.name;
		folder = new File(userFolderPath);
		folder.mkdir();
		
		
		// create inner folder which represents inbox, trash .. etc
		String[] innerFoldersName = new String[] {"inbox","draft","trash"};
		for (int i = 0; i < innerFoldersName.length; i++) {
			createNewFolder(innerFoldersName[i]);
		}
		
	}
	
	void createNewFolder(String folderName) {
		UserInnerFolder tmp = new UserInnerFolder(this, folderName);
		this.innerFolders.add( tmp );
	}
	
	
	void writeUserToFile() {
		String userInfoFilePath = this.userFolderPath + File.separator + "userInfo.bin";
		FileManager.writeToFile(this, userInfoFilePath);
	}
	
	void addAnEmailToUser(String email) {
		if(!isValidEmail(email)) // authentication class
			return ; //TODO
	
		this.emails.add(email);
		writeUserToFile();
	}

	 boolean isValidEmail(String email){
		String emailRegex = "^[\\\\w!#$%&�*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
	    return email.matches(emailRegex);
	 }
	 
	 UserInnerFolder getFolder(String folderName)  {
		return (UserInnerFolder)(FileManager.getFile(
				getFolderIndexFilePath(folderName)) );
	 }
	 
	 String getFolderIndexFilePath(String folderName) {
		 return userFolderPath + File.separator + folderName + File.separator + UserInnerFolder.indexFileName;
	 }
	 
	 boolean folderExists(UserInnerFolder innerFolder) {
		 return innerFolders.contains(innerFolder);
 }
	
	
}