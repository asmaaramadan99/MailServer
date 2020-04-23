package eg.edu.alexu.cs.datastructures.classes;

import java.io.Serializable;

import eg.edu.alexu.csd.datastructure.SinglyLinkedList;

public class myContact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8668198521143042383L;
	protected String name;
	String email;
	SinglyLinkedList emails=new SinglyLinkedList();

	myContact(String name,String email){
		this.name=name;
		this.email=email;
		this.emails.add(this.email);
		
	}
	
	public myContact() {
		// TODO Auto-generated constructor stub
	}
    //allows user to add a contact to his list
	void addContact(SinglyLinkedList myContacts,myContact contact)
	{   if(!myContacts.contains(contact))
		myContacts.add(contact);
	}
	
	//add an email for a contact that exits in the list
	//if it doesn't exist add it as a new contact
	void addNewEmail(myContact contact,String email,SinglyLinkedList myContacts)
	{     
	      int index=  myContacts.getIndex(contact);
	      if(index==-1)
	    	  addContact(myContacts,contact);
	      else {
	      contact=(myContact) myContacts.get(index);
	      contact.emails.add(email);
	      }
	   
	      
	}
	
	void editName(myContact contact,String name,SinglyLinkedList myContacts)
	{
		int index=myContacts.getIndex(contact);
	     if(index==-1)
		 throw new RuntimeException("Contact not found");
		 contact=(myContact) myContacts.get(index);		 
	     contact.name=name;
	     
	}
	
	void deleteContact(myContact contact,SinglyLinkedList myContacts)
	{
		int index=myContacts.getIndex(contact);
		if(index==-1)
			throw new RuntimeException("Contact not found");
		myContacts.remove(index);
	}
	
	
	

}
