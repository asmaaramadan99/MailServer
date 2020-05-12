package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.SinglyLinkedList;
import  eg.edu.alexu.csd.datastructure.Queue;  
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.time.LocalDate;
import java.util.UUID;

public class Main {

	
	static void moazTest() {
		
		App app = new App();
		Contact moaz = new Contact("Mohamed@gmail.com", "pass");
		moaz.setName("ahmed"); 
		moaz.setUserPath();
		System.out.println(app.signup(moaz));
		System.out.println(app.signin(moaz.getEmail(), moaz.getPassword()));
		System.out.println(moaz.getUserPath());
		
		try {
			System.out.print(app.emailExists("ahmed@gmail.com"));
		} catch(Exception e) {
			
		}
		
	}
	
	static void generateUsers() {
		App app = new App();
		Contact moaz = new Contact("Mohamed@gmail.com", "pass");
		moaz.setName("mohamed"); 
		moaz.setUserPath();
		System.out.println(app.signup(moaz));
		
		 moaz = new Contact("ahmed@gmail.com", "pass");
		moaz.setName("ahmed"); 
		moaz.setUserPath();
		System.out.println(app.signup(moaz));
		
		
		 moaz = new Contact("moaz@gmail.com", "pass");
			moaz.setName("moaz"); 
			moaz.setUserPath();
			System.out.println(app.signup(moaz));
			
	}
	
	static void newEmails() {

		  App app = new App();
		  System.out.println(app.signin("ahmed@gmail.com","pass"));
		  String a = new String ("C:\\Users\\Moaz\\Deskt"
		  		+ "op\\corona.jpg");
		  SinglyLinkedList A= new SinglyLinkedList();
		  A.add(a); 
		  
		 /* Mail m=new Mail("ahmed@gmail.com", "ads", "2mdfasfasfag",
				  null, 2, A );
		  
		  
		  Queue z = new Queue();
		  z.enqueue("mohamed@gmail.com");
		  z.enqueue("moaz@gmail.com");
		  m.receivers = z;
		  System.out.print(app.compose(m));
		  
		  System.out.println(m.getBodyText());*/
		
	}
	
	
	
	static void getMails() {
		App app = new App();
		System.out.println(app.signin("moaz@gmail.com","pass"));
		Mail[] mails = (Mail[])app.listEmails(1);
		for(int i=0; i<mails.length; i++) {
			if(mails[i] == null)
				break;
			
			String text = (mails[i]).getBodyText();
			System.out.println(text);
		}
	}
	
	
	public static void main(String args[]) {
		
		SinglyLinkedList a = new SinglyLinkedList();
		a.add(1);
		a.add(2);
		a.add(3);
		System.out.println(a.size());
		
		//generateUsers();
		//newEmails();
		//getMails();
		
		
		//filterShit();
		
		
    //    User asmaa=new User("asmaa","asmaa99@yahoo.com","asmaa");
        
      /*  		
		User Moaz = new User("Moaz", "moaz@mail.com", "somePassword");
		Moaz.createNewFolder("work");
		UserInnerFolder work = Moaz.getFolder("work");
		Mail mail1 = new Mail("Some Subject", "assa@asd.com", "Some Text#s", 4, null);
		work.createEmail(mail1);
		//work.DeleteEmail(mail1);
		//System.out.println(mail1.path);
		Moaz.writeUserToFile();
		*/
        
        /*myContact c=new myContact("mariam","mariam@gmail.com");
        asmaa.addContact(asmaa.myContacts,c);
        asmaa.saveContacts();
        asmaa.myContacts=asmaa.loadContacts();
        myContact s=(myContact) asmaa.myContacts.get(0);
        System.out.println(s.name);
        System.out.println(s.email);
        asmaa.myContacts=null;
        asmaa.myContacts=asmaa.loadContacts();
        s=(myContact)asmaa.myContacts.get(0);
        System.out.println(s.name);
        System.out.println(s.email);
        myContact k=new myContact("asmaa","asmaa@yahoo.com");
        asmaa.addContact(asmaa.myContacts,k);
        asmaa.saveContacts();
        asmaa.myContacts=null;
        asmaa.myContacts=asmaa.loadContacts();
        System.out.println(asmaa.myContacts.size());
        */
		
		//System.out.print(m.name);
		
		//Moaz.addNewEmail(c,"asmaa",Moaz.myContacts);
		
		
		
		
		
		//System.out.print(l.head.value);
	
	
		//File folder = new File("C:\\Users\\Moaz\\Desktop\\Java DT Porject\\Assignment 8\\MailServer\\root");
		//FileManager.deleteDir(folder);
		

		/*
		User asmaa = new User("Asmaa", "Asmaa@mail.com", "somePassword");
		Contact c=new Contact("asmma@gmail.com","asmaa");
		//System.out.print(app.signup(c));
		//System.out.println(app.signin("asmma@gmail.com", "asmaa"));
		System.out.print(app.signup(c));
		 */
		
		
	}
	
	void foledrsCode() {

		System.out.println("Working Directory = " + File.separator + System.getProperty("user.dir"));
		
		File folder = new File("C:\\Users\\Moaz\\Desktop\\Test\\madeDir\\asmaa");
		folder.mkdirs();
		folder.delete();
		folder.mkdirs();
		
		//get children
		folder = new File("C:\\Users\\Moaz\\Desktop\\Test");
		String[] files = folder.list();
		for(String f: files) {
			System.out.println(folder.getAbsolutePath() + File.separator + f);
			
		}
		
	}
	
	void testingSinglyList() {

		SinglyLinkedList arr = new SinglyLinkedList();
		App app = new App();
		String a = new String("a");

		String b = new String("b");
		Integer z = -531;
		arr.add(-31); arr.add(b); arr.add(z); arr.add(92);
		//System.out.print(arr.getIndex(34));
		arr.remove((arr.getIndex(z)));
		arr.printAll();
		
	}
	
	/*
	//priority queue test
	PriorityQueue q=new PriorityQueue();
		q.insert(5,2);
		q.insert(6,1);
		System.out.println(q.removeMin());
		System.out.println(q.size());
		
	
	
	//sorting test
		SinglyLinkedList mails = new SinglyLinkedList();
		
		Mail mail1=new Mail("c", "mariam@gmail.com",  "l",3 , null);
		Mail mail2=new Mail("a", "zaitoun@gmail.com",  "o",1 , null);
		Mail mail3=new Mail("b", "asmaa@gmail.com",  "a",2, null);

	   
		mail1.date=LocalDate.now().plusDays(2);
		mail2.date=LocalDate.now().plusDays(1);
		mail3.date=LocalDate.now().plusDays(3);



	    mails.add(mail1);
	    mails.add(mail2);
	    mails.add(mail3); 
	    
	    Sort.iterativeQuickSort(mails,"Default");
	    Sort.iterativeQuickSort(mails,"Subject");
	    Sort.iterativeQuickSort(mails,"Sender");
	    Sort.iterativeQuickSort(mails,"Body");

	 
	   for (int i=0;i<mails.size();i++) {
	    	Mail m=(Mail) mails.get(i);
	    	//System.out.println(m.date);
	        //System.out.println(m.subject);
	        //System.out.println(m.subject);
            System.out.println(m.bodyText);

	    }
	  
	}
*/

}
