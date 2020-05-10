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

class Mail  implements  IMail, Serializable {
		
	private static final long serialVersionUID = -1546344480061265891L;
	private String sender;
    private  PriorityQueue receivers=new PriorityQueue();
	private String subject;
	private String bodyText;
	private LocalDate date;
	private Integer priority;
	transient private SinglyLinkedList attachements=new SinglyLinkedList();
	private String status;
	private String mailFolderPath;
    private String ID;
    private MailBasicInfo basicInfo;
	
	
	Mail() 
	{   
		setNewID();
	
	}
	
	
	public void setBasicInfo()
	{
		this.basicInfo=new MailBasicInfo(sender,receivers,subject,Date.valueOf(date),priority,attachements.size(),status,mailFolderPath);
		Index.writeToIndexFile(this.basicInfo);
		
	}
	public void setMailFolderPath(String userPath,String folder)
	{
		mailFolderPath=userPath+File.separator+folder+File.separator+this.getID();
		Index.IndexFilePath=userPath+File.separator+folder+File.separator+"index.txt";
			
	}
	public String getMailFolderPath()
	{
		return mailFolderPath;
	}
	
	public void setNewID() {
		this.ID =UUID.randomUUID().toString(); 
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	public void setSender(String sender)
	{
		this.sender=sender;
	}

	public String getSender()
	{
		return this.sender;
	}

	public void setSubject(String subject)
	{
		this.subject=subject;
	}

	public String getSubject()
	{
		return this.subject;
	}


	@SuppressWarnings("static-access")
	public void setDate()
	{
		this.date=date.now();
	}

	public Date getDate()
	{
		return Date.valueOf(date);

	}

	public Integer getPriority() {
		return priority;
	}

	public boolean setPriority(Integer priority) {
		if(priority <=5 && priority >=1 )
			this.priority = priority;
		
		else 
			return false;
		
		return true;
	}

	public SinglyLinkedList getAttachements() {
		return attachements;
	}

	public void setAttachements(SinglyLinkedList attachements) {
		this.attachements = attachements;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PriorityQueue getReceivers() {
		return receivers;
	}

	public void setReceivers(PriorityQueue receivers) {
		this.receivers = receivers;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	
	public void createMailFolder() throws IOException
	{
		File mailFolder=new File(this.mailFolderPath);
		mailFolder.mkdirs();
		File textFile =new File(mailFolder.getAbsolutePath()+File.separator+"text.txt");
		textFile.createNewFile();
		FileManager.writeToFile(this.bodyText,textFile.getAbsolutePath());

		
		
	}

	public MailBasicInfo getBasicInfo() {
		return basicInfo;
	}


	

	
}
