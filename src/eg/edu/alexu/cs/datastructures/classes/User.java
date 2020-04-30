package eg.edu.alexu.cs.datastructures.classes;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;

public class User implements IUser, Serializable {

	/*
	 * u should use writeUserToFile method after any updates to the user
	 */

	private static final long serialVersionUID = 1L;
	Contact user;
	SinglyLinkedList emails = new SinglyLinkedList();
	SinglyLinkedList myContacts = new SinglyLinkedList();

	// TODO: encrypt stored passwords
	public User(Contact user) {
		this.user = user;
		this.emails.add(user.getEmail());
		store();
	}

	public void store() {
		writeUserToFile();
	}

	void writeUserToFile() {
		String userInfoFilePath = this.user.getUserPath() + File.separator + "userInfo.bin";
		FileManager.writeToFile(this, userInfoFilePath);
	}

	void addAnEmailToUser(String email) {
		if (!isValidEmail(email))
			return;

		this.emails.add(email);
		writeUserToFile();
	}

	boolean isValidEmail(String email) {
		String emailRegex = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
		return email.matches(emailRegex);
	}

	boolean folderExists(String folderName) {

		File tmpDir = new File(user.getUserPath() + File.separator + folderName);

		return tmpDir.exists();
	}

}
