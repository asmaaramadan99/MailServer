package eg.edu.alexu.cs.datastructures.classes;

import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

class Mail implements IMail, Serializable {
	
	
	/**
	 *  Mail Tree:
	 *  MailName or ID:
	 *  	- body.txt
	 *  	- attachment1
	 *  	- attachment2
	 */
	
	
	private static final long serialVersionUID = -1546344480061265891L;
	String subject;
	String bodyText;
	String senderEmail;
	String path;
	String ID;
	Integer priority;
	Boolean read;
	SinglyLinkedList attachmentsPaths;// maybe use a wrapper array;
	
	
	
	Mail(String subject, String senderEmail, String bodyText, int priority, SinglyLinkedList attachmentsPaths){
		this.subject = subject;
		this.senderEmail = senderEmail;
		this.priority = priority;
		this.bodyText = bodyText;
		this.priority = priority;
		if(attachmentsPaths == null)
			this.attachmentsPaths = attachmentsPaths;
		
		else
			this.attachmentsPaths = new SinglyLinkedList();
	}
	
	void createMailFolder(String path) {
	}
	
	void createBodyTextFile() {
	}
	
	void addAttachment(String attachmentOriginalPath) {
	}
	
	void addAllAttachments() {
	}
	
	
	void remove() {
	}
	
	
	
	
}
