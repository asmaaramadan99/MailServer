package eg.edu.alexu.cs.datastructures.classes;

import static org.junit.Assert.*;

import java.io.File;

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
		System.out.println(app.signup(user));
		
		user = new Contact("ahmed@gmail.com", "pass");
		user.setName("ahmed"); 
		user.setUserPath();
		System.out.println(app.signup(user));
		
		
		user = new Contact("moaz@gmail.com", "pass");
		user.setName("moaz"); 
		user.setUserPath();
		System.out.println(app.signup(user));
		
		Contact omar = new Contact("omar@gmail.com", "pass");
		user.setName("Omar"); 
		user.setUserPath();
		System.out.println(app.signup(user));
	}
	
	void generateEmails(String subject) {
		  
		 app = new App();
		 
		 assertEquals(app.signin("moaz@gmail.com", "pass"),true);
			
		  Mail m=new Mail("moaz@gmail.com", "ads", subject,
				  null, 2, null );
		  
		  Queue z = new Queue();
		  z.enqueue("mohamed@gmail.com");
		  z.enqueue("ahmed@gmail.com");
		  m.receivers = z;
		  System.out.print(app.compose(m));
		  
		  System.out.println(m.getBodyText());
	}
	
	
	@Test
	public void test() {
		app = new App();
		FileManager.deleteDir(new File(app.rootPath));
		generateNewContacts(); 
		
		for(Integer i=0; i<6; i++) {
			bodyText.add("subject" + i.toString());
			generateEmails("subject" + i.toString());
		}
	
	//	test1();
		
		//test2();
	}
	
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
	
	void test2() {
		app = new App();

		
		
		assertEquals(app.signin("moaz@gmail.com", "pass"),true);
		Folder a = new Folder("Sent");
		app.setViewingOptions(a, null, null);
		Mail[] mails = (Mail[])app.listEmails(1);
		for(int i=0; i<mails.length; i++) {
			
			if(mails[i] == null)
				break;
			
			String text = (mails[i]).getBodyText();
			System.out.println(text);
			//assertEquals(text.equals( (String)bodyText.get(i) ), true);
		}
	}
	
	
}
