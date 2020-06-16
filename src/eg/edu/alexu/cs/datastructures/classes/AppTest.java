package eg.edu.alexu.cs.datastructures.classes;

import static org.junit.Assert.*; 

import java.io.File;
import java.util.Date;

import org.junit.Test;

import eg.edu.alexu.csd.datastructure.Queue;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
 
public class AppTest {
	App app;
	SinglyLinkedList bodyText = new SinglyLinkedList();
	 
	void generateNewContacts() {
		app = new App();
		
		Contact user = new Contact("Mohamed@gmail.com", "pass");
		user.setName("mohamed"); 
		user.setUserPath();
		app.signup(user); 
		
		user = new Contact("ahmed@gmail.com", "pass");
		user.setName("ahmed"); 
		user.setUserPath();
		assertEquals(app.signup(user),true);
		
		
		user = new Contact("moaz@gmail.com", "pass");
		user.setName("moaz"); 
		user.setUserPath();
		app.signup(user);
		
		Contact omar = new Contact("omar@gmail.com", "pass");
		user.setName("Omar"); 
		user.setUserPath();
		app.signup(user);
	}
	 
	void generateEmails(String Sender,String bodyText, int priority, String subject) {
		  
		 app = new App();
		 
		 SinglyLinkedList a = new SinglyLinkedList();
		 String path = "C:\\Users\\Moaz\\Pictures\\Saved Pictures" 
		 + File.separator + "IMG_0558.jpg";
		 if( (new File(path)).exists() )
			 a.add(path);
		  
		 assertEquals(app.signin("moaz@gmail.com", "pass"),true);
			
		  Mail m=new Mail(Sender+"@gmail.com", subject , bodyText,
				  new Date(), priority, a );
		  
		  Queue z = new Queue();
		  z.enqueue("mohamed@gmail.com");
		  z.enqueue("ahmed@gmail.com");
		  m.receivers = z;
		  assertEquals(app.compose(m),true);
		  
		  z.enqueue("k@gmailt.com");
		  assertEquals(app.compose(m),false);
		   
	}
	
	
	@Test
	public void test() {
		app = new App();
		
		// delete root DIR
		FileManager.deleteDir(new File(app.rootPath));
		
		 
		generateNewContacts(); 
		
		for(Integer i=0; i<13; i++) {
			Integer r = 12-i;
			bodyText.add("subject" + r.toString());
			generateEmails("Sender" +((Integer)(i%5)).toString() ,
					"subject" + i.toString(), i%5, "subject" + ((Integer)(i%5)).toString());
		}
	
		test1();
		test2();
		test3();
	}
	
	 
	// check if receiver got emails
	void test1() {
		app = new App();
		
		assertEquals(app.signin("ahmed@gmail.com", "pass"),true);
		Mail[] mails = (Mail[])app.listEmails(1);
		for(int i=0; i<mails.length; i++) {
			if(mails[i] == null)
				break;
			
			String text = (mails[i]).getBodyText();
			assertEquals(text.equals( (String)bodyText.get(i) ), true);
		}
		
		
	}
	
	
	// check if emails were put in send file
	void test2() {
		app = new App();
		
		assertEquals(app.signin("moaz@gmail.com", "pass"),true);
		Folder a = new Folder("Sent");
		app.setVeiwOptions("Sent",null, null, null);;
		Mail[] mails = (Mail[])app.listEmails(1);
		for(int i=0; i<mails.length; i++) {
			
			if(mails[i] == null)
				break;
			
			String text = (mails[i]).getBodyText();
			assertEquals(text.equals( (String)bodyText.get(i) ), true);
		}
	}
	
	void test3() {
		app = new App();
		  
		assertEquals(app.signin("mohamed@gmail.com", "pass"),true);
	 	app.setVeiwOptions("Inbox", null, null, null);
		Mail[] mails = (Mail[])app.listEmails(1);
		for(int i=0; i<mails.length; i++) {
			if(mails[i] == null)
				break;
			
			//String text = (mails[i]).getBodyText();
			System.out.println("P-> "+ mails[i].getSubject());
		}
		
	
	}
	
	
	
	
}
