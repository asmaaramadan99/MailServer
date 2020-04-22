package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.io.File;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.IOException;


public class App implements IApp, Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	protected static String rootPath;
	protected static String accountsFolderPath;
	User currentUser;
	protected static String SystemUsersPath;
	IFolder currentFolder;
	IFilter currentFilter;
	ISort currentSort;
	
	
	public App(){
		
		rootPath = System.getProperty("user.dir")+ File.separator + "root"; 
		accountsFolderPath = rootPath + File.separator + "accounts";
		File accountsFolder = new File(accountsFolderPath);
		if(accountsFolder.exists() == false)
			accountsFolder.mkdirs();	
		
		createSystemUserFile();		
		
	}
	
	public void createSystemUserFile()
	{  
		File SystemUsers=new File(rootPath,"SystemUsersInfo.bin");
		try {
			SystemUsers.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   SystemUsersPath=SystemUsers.getAbsolutePath();
		
	}
	
	@Override
	public boolean signin(String email, String password) {
	
		// TODO: uplaod contact library
		Contact contact=new Contact(email,password);
		boolean exist=false;
		Authentication authenticate=new Authentication(SystemUsersPath,contact);
		
		try {
			exist=authenticate.exist(contact);
		} catch (ClassNotFoundException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return exist&&authenticate.matchPass?true:false;
	 
	  
	}

	@Override
	public boolean signup(IContact contact) {
		  
		  Authentication authenticate=new Authentication(SystemUsersPath,contact);
		  File f=new File(SystemUsersPath);
		  boolean exist = false;
		  if(f.length()==0) {
		  authenticate.addNewUser(true);
		  return true;
		  }
		  else {
	      try {
	  		exist= authenticate.exist((Contact) contact);
	  	} catch (ClassNotFoundException | FileNotFoundException e) {
	  		// TODO Auto-generated catch block
	  		e.printStackTrace();
	  	}
	      if(!exist)
	    	  authenticate.addNewUser(false);
		  
		  }
		  return exist?false:true;
		
		
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		this.currentFolder = folder;
		this.currentFilter = filter;
		this.currentSort = sort;
		
	}

	@Override
	public IMail[] listEmails(int page) {
		
		/// 1- get indexFilePath from currentUser >> user.indexFilePath(Folder.folderName);
		/// 2- read the data 
		/// 3- apply the needed filters and sorts
		/// 4- send the emails
		
		
		return null;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean compose(IMail email) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
