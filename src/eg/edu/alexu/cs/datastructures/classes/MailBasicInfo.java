package eg.edu.alexu.cs.datastructures.classes;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.util.UUID;

import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
public class MailBasicInfo implements Serializable{
	
	public static final long serialVersionUID = 3347324734166375499L;
	public String sender;
	public String subject;
	public Date date;
	public Integer priority;
	public SinglyLinkedList attachements=new SinglyLinkedList();
	public String mailFolderPath;
	
	public String status;
	public String ID;
	
    MailBasicInfo(String sender, 
    		String subject, Date date, Integer priority,
    		SinglyLinkedList attachments) {
		this.sender=sender;
		this.subject=subject;
		this.date= date;
		this.priority=priority;
		this.status="unread";
		this.ID = UUID.randomUUID().toString();
		this.attachements = attachments;
    }

	public MailBasicInfo() {
		// TODO Auto-generated constructor stub
	}

	
}
