package eg.edu.alexu.cs.datastructures.classes;


import java.time.LocalDate;

import  eg.edu.alexu.csd.datastructure.SinglyLinkedList;
//import  eg.edu.alexu.csd.datastructure.SinglyLinkedList.Node;
//import eg.edu.alexu.cs.datastructures.Interfaces.*;

class Main {

	public static void main(String args[]) {
	  	
		//App app = new App();

		/*User Moaz = new User("Moaz", "moaz@mail.com", "somePassword");
		Moaz.createNewFolder("work");
		UserInnerFolder work = Moaz.getFolder("work");
		Mail mail1 = new Mail("Some Subject", "assa@asd.com", "Some Text#s", 4, null);
		work.createEmail(mail1);
		//work.DeleteEmail(mail1);
		System.out.println(mail1.path);
	*/
		/*
		File folder = new File("C:\\Users\\Moaz\\Desktop\\Java DT Porject\\Assignment 8\\MailServer\\root");
		FileManager.deleteDir(folder);
		*/

		
		/*User asmaa = new User("Asmaa", "Asmaa@mail.com", "somePassword");
		Contact c=new Contact("asmma@gmail.com","asmaa");
		System.out.print(app.signup(c));
		System.out.println(app.signin("asmma@gmail.com", "asmaa"));
		System.out.print(app.signup(c));
		 
		
		
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
		arr.printAll();*/
		
		/*PriorityQueue q=new PriorityQueue();
		q.insert(5,2);
		q.insert(6,1);
		System.out.println(q.removeMin());
		System.out.println(q.size());*/
		
		SinglyLinkedList mails = new SinglyLinkedList();
		
		Mail mail1=new Mail("c", "mariam@gmail.com",  "l",3 , null);
		Mail mail2=new Mail("a", "zaitoun@gmail.com",  "o",1 , null);
		Mail mail3=new Mail("b", "asmaa@gmail.com",  "a",2, null);

	   /* Date date1=Date.valueOf(mail1.date);
	    Date date2=Date.valueOf(mail2.date);
	    Date date3=Date.valueOf(mail3.date);
	    date1.setDate(26);
	    date2.setDate(27);
	    date3.setDate(23);*/
	    
	    
		mail1.date=LocalDate.now().plusDays(2);
		mail2.date=LocalDate.now().plusDays(1);
		mail3.date=LocalDate.now().plusDays(3);



	    mails.add(mail1);
	    mails.add(mail2);
	    mails.add(mail3); 
	    
	    //Sort.iterativeQuickSort(mails,"Default");
	    //Sort.iterativeQuickSort(mails,"Subject");
	    //Sort.iterativeQuickSort(mails,"Sender");
	    //Sort.iterativeQuickSort(mails,"Body");

	 
	   for (int i=0;i<mails.size();i++) {
	    	Mail m=(Mail) mails.get(i);
	    	//System.out.println(m.date);
	        //System.out.println(m.subject);
	        //System.out.println(m.subject);
            System.out.println(m.bodyText);

	    }
	  
	}
		
}
