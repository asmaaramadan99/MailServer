package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;

import java.io.*;
import java.util.UUID;

public class Mail implements IMail {
	
	
	/**
	 *  Mail Tree:
	 *  MailName or ID:
	 *  	- body.txt
	 *  	- attachment1
	 *  	- attachment2
	 */
	
	
	
	static Integer numOfEmails = 0;
	String subject;
	String bodyText;
	String senderEmail;
	String folderPath;
	String ID;
	Integer priority;
	Boolean read;
	String[] attachments;// maybe use a wrapper array;
	
	
	
	Mail(String subject, String senderEmail, String bodyText,
			String folderPath, int priority){
		numOfEmails++;
		this.subject = subject;
		this.senderEmail = senderEmail;
		this.folderPath = folderPath;
		this.priority = priority;
		this.bodyText = bodyText;
		this.ID = "#" + numOfEmails.toString();
		read = false;
		
		createMailFile();
	}
	
	void createMailFile() {
		File mailFolder = new File(this.folderPath + File.pathSeparator + this.ID);
		mailFolder.mkdir();
		String bodyPath = mailFolder.getAbsolutePath() + File.separator + "body.txt";
		
		try{
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(bodyPath));
		    bufferedWriter.write(this.bodyText);
		    bufferedWriter.close();
		} catch (IOException e) {
		    // Exception handling
			
			// TO DO
		}
		
		
	}
	
	
	
	
	
}
