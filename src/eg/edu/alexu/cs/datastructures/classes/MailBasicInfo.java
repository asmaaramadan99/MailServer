package eg.edu.alexu.cs.datastructures.classes;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
public class MailBasicInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3347324734166375499L;
	/**
	 * 
	 */
	
	private String sender;
	transient private PriorityQueue receivers;
	private String subject;
	private Date date;
	private Integer priority;
	private int  noOfAttachements;
	private String status;
	private String mailFolderPath;
  
     
    
    MailBasicInfo(String sender, PriorityQueue receivers, String subject, Date date, Integer priority, int noOfAttachements,String status,String mailFolderPath) {
	this.sender=sender;
	this.receivers=receivers;
	this.subject=subject;
	this.date=date;
	this.priority=priority;
	this.noOfAttachements=noOfAttachements;
	this.status=status;
	this.mailFolderPath=mailFolderPath; 
    	
    }


	public String getSender() {
		return sender;
	}



	public PriorityQueue getReceivers() {
		return receivers;
	}



	public String getSubject() {
		return subject;
	}


	


	public Date getDate() {
		return date;
	}





	public Integer getPriority() {
		return priority;
	}




	public int getNoOfAttachements() {
		return noOfAttachements;
	}


	

	public String getStatus() {
		return status;
	}



	public String getMailFolderPath() {
		return mailFolderPath;
	}




}
