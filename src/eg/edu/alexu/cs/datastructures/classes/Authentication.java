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
	private Contact currentUser;
	private String email;
	private String password;
	
	
	//for sign up
	Authentication(String SystemUsersInfoPath,IContact contact )
	{
		this.SystemUsersInfoPath=SystemUsersInfoPath;
		this.email=contact.getEmail();
		this.password=contact.getPassword();
		this.contact=contact;
		
	}
	//for sign in
	Authentication(String SystemUsersInfoPath,String email,String password)
	{
		this.SystemUsersInfoPath=SystemUsersInfoPath;
		this.email=email;
		this.password=password;
		
		
	}
	/*
	 * @return the user that successfully singed in
	 */
	
	public Contact getCurrentUser()
	{
		return currentUser;
	}
	 /*checks whether the contact already exists or not
	  * @return true if exists
	  * false otherwise
	  * @throws class not found & file not found exception
	  * 
	  */
	 public Boolean exist() throws ClassNotFoundException, FileNotFoundException
	{   

		try {
			ObjectInputStream read= new ObjectInputStream(new FileInputStream(SystemUsersInfoPath));
			 while(true) {
				Contact current=(Contact) read.readObject();
				if(current.getEmail().equals(email)) {
					if(current.getPassword().equals(password)) {matchPass=true; this.currentUser=current;}
					read.close();
					return true;
				            }
			   }	
		} catch(EOFException e) 
		{   
		  return false;
		}
		catch (IOException e) {e.printStackTrace();}
		
		return false;
		
	    }
	 
	 public boolean isValidEmailFormat()
	 {  
		 Contact user=(Contact) contact;
		 String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     boolean match=pattern.matcher(user.getEmail()).matches();
		 return match;
	 }
	 /*
	  * add new user to system when he signs up successfully
	  * @throws IO exception 
	  *
	  */
	 public void addNewUser(boolean first)
	 { 
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
	 }	

	 
     
