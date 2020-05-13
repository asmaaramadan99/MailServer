package eg.edu.alexu.cs.datastructures.classes;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;

public class User implements IUser, Serializable {

	enum Folders {
		Inbox, Sent, Drafts, Trash
	}

	private static final long serialVersionUID = 1L;
	public Contact user;
	SinglyLinkedList emails = new SinglyLinkedList();
	SinglyLinkedList myContacts = new SinglyLinkedList();

	// TODO: encrypt stored passwords
	public User(Contact user) {
		this.user = user;
		this.emails.add(user.getEmail());
		try {
			this.createFolder();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		writeUserToFile();
	}

	public void createFolder() throws IOException {
		File folder = new File(this.user.getUserPath());
		folder.mkdir();
		File userInfo = new File(this.user.getUserPath() + File.separator + "userInfo.bin");
		userInfo.createNewFile();
		for (Folders f : Folders.values()) {
			File file = new File(this.user.getUserPath() + File.separator + f.toString());
			file.mkdir();
			File index = new File(file.getAbsoluteFile() + File.separator + "index.txt");
			index.createNewFile();

		}
	}


	void writeUserToFile() {
		String userInfoFilePath = this.user.getUserPath() + File.separator + "userInfo.bin";
		FileManager.writeToFile(this, userInfoFilePath);
	}


	boolean folderExists(String folderName) {
		File tmpDir = new File(user.getUserPath() + File.separator + folderName);
		return tmpDir.exists();
	}
	
	

}
