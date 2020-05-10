package eg.edu.alexu.cs.datastructures.classes;

import  eg.edu.alexu.csd.datastructure.*;
import java.io.File;
import java.io.IOException;

import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.time.LocalDate;
import java.util.UUID;

public class Main {

	public static void main(String args[]) throws IOException {
	  	
	
		App app = new App();
		
	  System.out.println(app.signin("asmaa99@yahoo.com","hi"));
	  
	  Mail m=new Mail();
	  m.setDate();
	  m.setSender("asmaa");
	  m.setSubject("hello");
	  m.setPriority(1);
	  

	  m.setBodyText("hi"); 
	  m.setStatus("h"); 
	  
	  m.setMailFolderPath(app.currentUser.user.getUserPath(),"Sent");
	  m.setBasicInfo();	
	  System.out.println(m.getMailFolderPath());
	  System.out.println(Index.IndexFilePath); 
	  m.createMailFolder();
      
	  DoubleLinkedList l=Index.getListFromIndexFile();
	  System.out.println(l.size());
		
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
