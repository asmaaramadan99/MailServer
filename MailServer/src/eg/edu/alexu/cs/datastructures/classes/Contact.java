package eg.edu.alexu.cs.datastructures.classes;

import java.io.Serializable;

import eg.edu.alexu.cs.datastructures.Interfaces.IContact;

public class Contact implements IContact,Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 String email;
	 String password;
	 
	 Contact(String email,String password)
	 {
		 this.email=email;
		 this.password=password;
	 }

	 Contact() {
		// TODO Auto-generated constructor stub
	
	}
	

}
