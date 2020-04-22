package eg.edu.alexu.cs.datastructures.classes;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;


/**
 * 
 * @author Moaz
 * - deprecated
 * - replaced by File ready class.
 *	 
 */


class Folder implements IFolder,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String path;
	public String name;
	
	Folder(String path){
		this.path = path;
		File folder = new File(path);
		folder.mkdir();
		this.name = folder.getName();
	}
	
}

// TODO tests
class UserInnerFolder extends Folder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	final static String indexFileName = "index.txt"; 
	String indexFilePath;
	User parentUser;
	SinglyLinkedList mails;
	
	
	UserInnerFolder(User parentUser, String name) {
		super(parentUser.userFolderPath + File.separator + name);
		this.parentUser = parentUser;
		indexFilePath = path + File.separator + indexFileName;
		mails = new SinglyLinkedList();
		
		createIndexFile();
		writeToIndexFile();
	}
	
	
	
	void createIndexFile() {
		try {
			File indexFile = new File( indexFilePath );
			indexFile.createNewFile();
		} catch(IOException e) {
			// TODO:
		}
	}
	
	
	void addEmailToIndexFile(Mail mail) {
		mails.add(mail);
		writeToIndexFile();
	}
	
	void removeMailFromIndexFile(Mail mail) {
		SinglyLinkedList folderMails = this.mails;
		folderMails.remove( folderMails.getIndex(mail) );
		writeToIndexFile();
	}
	
	
	void writeToIndexFile() {
		FileManager.writeToFile(this, indexFilePath);
		return ;
	}
	
	void createEmail(Mail mail) {
		addEmailToIndexFile(mail);
		mail.createMailFolder(path + File.separator +
				newID());
	}
	
	String newID() {
		return  UUID.randomUUID().toString();
	}
	
	void DeleteEmail(Mail mail) {
		removeMailFromIndexFile(mail);
		mail.remove();
	}
	
	int getNumberOfMails() {
		return mails.size();
	}
	
}
