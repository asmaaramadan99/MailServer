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
		this.path = path;
		this.ID = new File(this.path).getName(); 
		File mailFolder = new File(this.path);
		mailFolder.mkdir();
		this.createBodyTextFile();
		this.addAllAttachments();
	}
	
	void createBodyTextFile() {
		String bodyPath = this.path + File.separator + "body.txt";
		try{
			BufferedOutputStream bufferedOutput = new BufferedOutputStream(
					new FileOutputStream(bodyPath));
		    bufferedOutput.write(bodyText.getBytes());    
		    bufferedOutput.close();	
		} catch (IOException e) {
		    // Exception handling
			
			// TO DO
		}
		
	}
	
	void addAttachment(String attachmentOriginalPath) {
		File source = new File(attachmentOriginalPath); 
        File dest = new File(this.path + File.separator + source.getName());
        
        try {
			Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void addAllAttachments() {
		if(attachmentsPaths == null)
			return;
		
		SinglyLinkedList.Node node = attachmentsPaths.head;
		while(node != attachmentsPaths.nullNode) {
			addAttachment((String)node.value);
			node = node.next;
		}
	}
	
	
	void remove() {
		File mailFolder = new File(this.path);
		FileManager.deleteDir(mailFolder);
	}
	
	
	
	
}
