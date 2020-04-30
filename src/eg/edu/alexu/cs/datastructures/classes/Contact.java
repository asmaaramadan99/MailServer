package eg.edu.alexu.cs.datastructures.classes;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.stream.Stream;
import eg.edu.alexu.cs.datastructures.Interfaces.IContact;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;

public class Contact implements IContact,Serializable{

	
	private static final long serialVersionUID = -5689051864886253844L;
	enum Folders{Inbox,Sent,Drafts,Trash}
    private String name;
    private String email;
    private String password;
    private String birthday;
    private String userPath;

    

   public String getEmail()
    {
    	return email;
    }
 
   public void setEmail(final String email)
   {
	   this.email=email;
   }
   
   public String getPassword()
   {
	   return password;
   }
   
   public void setPassword(final String password)
   {
	   this.password=password;
   }
   
   public void setName(final String name)
   {
	   this.name=name;
   }
   
   public String getName()
   {
	   return name;
   }
    
   public void setBirthday(String birthday)
   {
	   this.birthday=birthday;
   }
   
   public String getBirthday()
   {
	   return birthday;
   }
   
  
   
   public void setUserPath()
   {
	   this.userPath=App.accountsFolderPath+File.separator+this.name;
   }
   public String getUserPath()
   {
	   return userPath;
   }
	

 
  public void createFolder() throws IOException {
	File folder = new File(this.getUserPath());
	folder.mkdir();
	File userInfo=new File(this.getUserPath()+File.separator+"userInfo.bin");
	userInfo.createNewFile();
	    for (Folders f : Folders.values()) {   
	    	File file=new File(this.getUserPath()+File.separator+f.toString());
	    	file.mkdir();
	    	File index=new File(file.getAbsoluteFile()+File.separator+"index.txt");
	    	index.createNewFile();
	    	
	    }		
    }
}
