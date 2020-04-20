package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;
import java.io.File;

public class App implements IApp {
	
	protected static String rootPath;
	protected static String accountsFolderPath;
	IFolder currentFolder;
	IFilter currentFilter;
	ISort currentSort;
	
	
	App(){
		
		// generate file if it doesn't exist
		if(rootPath == null) {
			rootPath = System.getProperty("user.dir")+ File.separator + "root"; 
			accountsFolderPath = rootPath + File.separator + "accounts";
			File rootFolder = new File(accountsFolderPath);
			rootFolder.mkdirs();
			
		}
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
