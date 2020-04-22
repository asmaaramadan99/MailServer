package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.io.File;
import java.io.Serializable;

public class App implements IApp, Serializable {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	protected static String rootPath;
	protected static String accountsFolderPath;
	User currentUser;
	IFolder currentFolder;
	IFilter currentFilter;
	ISort currentSort;
	
	
	public App(){
		
		rootPath = System.getProperty("user.dir")+ File.separator + "root"; 
		accountsFolderPath = rootPath + File.separator + "accounts";
		File accountsFolder = new File(accountsFolderPath);
		if(accountsFolder.exists() == false)
			accountsFolder.mkdirs();	
		
		
	}
	
	@Override
	public boolean signin(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean signup(IContact contact) {
		
		return false;
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
