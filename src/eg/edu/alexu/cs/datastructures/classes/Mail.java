package eg.edu.alexu.cs.datastructures.classes;
import eg.edu.alexu.csd.datastructure.*;

import eg.edu.alexu.cs.datastructures.Interfaces.*;

import java.beans.Transient;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class Mail  implements  IMail, Serializable {
		
	private static final long serialVersionUID = -1546344480061265891L;
	public Queue receivers=new Queue();
	public String bodyText;
	public MailBasicInfo basicInfo;
	
	public Mail(){} 
	
    public Mail(String sender, 
    		String subject, String bodyText , String date, Integer priority,
    		SinglyLinkedList attachments){
    	
    	this.bodyText = bodyText;
    	if(priority < 1) priority = 1;
    	if(priority > 5) priority = 5;
    	
    	this.basicInfo = new MailBasicInfo(sender, 
    		 subject,  date,  priority,
    		 attachments);
    	
    }
    
    
    public void store(String userPath, String folder) {
    	setMailFolderPath(userPath, folder);
    	addToIndexFile();
    	try{
    		createMailFolder();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
	public void addToIndexFile()
	{
		Index.writeToIndexFile(this.basicInfo);
	}
	public void setMailFolderPath(String userPath,String folder)
	{
		basicInfo.mailFolderPath=userPath+File.separator+folder+File.separator+this.basicInfo.ID;
		Index.IndexFilePath=userPath+File.separator+folder+File.separator+"index.txt";		
	}
	
	public void createMailFolder() throws IOException
	{
		File mailFolder=new File(this.basicInfo.mailFolderPath);
		mailFolder.mkdirs();
		File textFile =new File(mailFolder.getAbsolutePath()+File.separator+"text.txt");
		textFile.createNewFile();
		FileManager.writeToFile(this.bodyText,textFile.getAbsolutePath());

		 
		// store attachments
		if(basicInfo.attachements == null)
			return;
		for(int i=0; i<basicInfo.attachements.size(); i++) {
			String attachment = (String) basicInfo.attachements.get(i);
			attachment = Attachment.store(attachment);
			basicInfo.attachements.set(i, attachment); 
		}
		
	}
	
	/// --------------------------------------------
	
	public String getMailFolderPath()
	{
		return basicInfo.mailFolderPath;
	}
	
	public Queue getReceivers() {
		return this.receivers;
	}

	public String getSubject() {
		return basicInfo.subject;
	}
	

	public String getBodyText() {
		String path = basicInfo.mailFolderPath +
				File.separator + "text.txt";
		
		return (String)FileManager.getFile(path);
	}
	
	public String getSender() {
		return basicInfo.sender;
	}
	
	public Date getDate() {
		return Date.valueOf(basicInfo.date);
	}
	
	public String getDateString() {
		return basicInfo.date;
	}
	
	public String getStatus() {
		return this.basicInfo.status;
	}
	
	public int getPriority() {
		return this.basicInfo.priority;
	}

	public SinglyLinkedList getattachments() {
		return basicInfo.attachements;
	}
	
	public MailBasicInfo getBasicInfo() {
		return basicInfo;
	}

}
