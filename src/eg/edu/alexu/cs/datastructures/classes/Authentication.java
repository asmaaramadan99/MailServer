package eg.edu.alexu.cs.datastructures.classes;
import java.io.EOFException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import eg.edu.alexu.cs.datastructures.Interfaces.IContact;


public class Authentication {
	
	protected String SystemUsersInfoPath;
	private IContact contact;
	boolean matchPass=false;
	
	//for sign up
	Authentication(String SystemUsersInfoPath,IContact contact )
	{
		this.SystemUsersInfoPath=SystemUsersInfoPath;
		this.contact=contact;
		
	}
	//for sign in
	Authentication(String SystemUsersInfoPath,String email,String password)
	{
		this.SystemUsersInfoPath=SystemUsersInfoPath;
		this.contact= new Contact(email,password);
	}
	 //sign up
	 public Boolean exist(Contact contact) throws ClassNotFoundException, FileNotFoundException
	{   

		try {
			ObjectInputStream read= new ObjectInputStream(new FileInputStream(SystemUsersInfoPath));
			 while(true) {
				Contact current=(Contact) read.readObject();
				if(current.email.equals(contact.email)) {
					if(current.password.equals(contact.password)) matchPass=true;
					read.close();
					return true;
				            }
			   }	
		} catch(EOFException e) 
		{
			return false;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		return false;
		
	    }
	 
	 public boolean isValidEmailFormat()
	 {  
		 Contact user=(Contact) contact;
		 String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     boolean match=pattern.matcher(user.email).matches();
		 return match;
	 }
	 //for sign up
	 public void addNewUser(boolean first)
	 { 
		if(isValidEmailFormat()) {
		
		 try {
			 if(first) { 
			ObjectOutputStream write= new ObjectOutputStream(new FileOutputStream(SystemUsersInfoPath));
			write.writeObject(contact);
			write.close();
			 }
			 else
			 {
				 ObjectOutputStream os2 = new ObjectOutputStream(new FileOutputStream(SystemUsersInfoPath, true)) {
					    protected void writeStreamHeader() throws IOException {
					        reset();
					    }
					};
					
					os2.writeObject(contact);
					os2.close(); 
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}			 
	 
		else System.out.print("invalid email format");
	 }	
     }
