package eg.edu.alexu.cs.datastructures.classes;
import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

class Mail  implements  IMail, Serializable {
		
	private static final long serialVersionUID = -1546344480061265891L;
	
	private String sender;
	private Queue receivers;
	private String subject;
	private String bodyText;
	private Date date;
	private Integer priority;
	private SinglyLinkedList attachements;
	private String status;
	
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


	public void setDate(Date date)
	{
		this.date=date;
	}

	public Date getDate()
	{
		return date;

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

	public Queue getReceivers() {
		return receivers;
	}

	public void setReceivers(Queue receivers) {
		this.receivers = receivers;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	
	
	void creatMailFolders(String Path) {
		
		
	}
	
}







	
	
	