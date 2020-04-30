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
	public static String attachmentsFolderPath;
	User currentUser;
	protected static String SystemUsersPath;
	IFolder currentFolder;
	IFilter currentFilter;
	ISort currentSort;
	
	
	void setInitialFoldersPaths() {
		rootPath = System.getProperty("user.dir")+ File.separator + "root"; 
		accountsFolderPath = rootPath + File.separator + "accounts";
		attachmentsFolderPath =  rootPath + File.separator + "attachments";
		
	}
	
	void createInitialFolders() {
		File accountsFolder = new File(accountsFolderPath);
		File attachmentsFolder = new File(attachmentsFolderPath);
		accountsFolder.mkdirs();
		attachmentsFolder.mkdirs();
	}
	
	public App(){
		
		setInitialFoldersPaths();
		// no errors will occur if the folders already exist
		createInitialFolders(); 
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
	public boolean signin(final String email, final String password) {

		// TODO: uplaod contact library

		boolean exist=false;
		Authentication authenticate=new Authentication(SystemUsersPath,email,password);


		try {
			exist=authenticate.exist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(exist&&authenticate.matchPass)
		{   Contact c=authenticate.getCurrentUser();
		//System.out.println(c.getUserPath());
		this.currentUser=(User) FileManager.getFile(c.getUserPath()+File.separator+"userInfo.bin");
		}


		return exist&&authenticate.matchPass?true:false;

	}



	@Override
	public boolean signup(final IContact contact) {

		Authentication authenticate=new Authentication(SystemUsersPath,contact);
		if(!authenticate.isValidEmailFormat())
			return false;
		File f=new File(SystemUsersPath);
		boolean exist = false;
		if(f.length()==0) {
			authenticate.addNewUser(true);
			contact.setUserPath();
			try {
				contact.createFolder();
				new User((Contact)contact);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		else {
			try {
				exist= authenticate.exist();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!exist)
				authenticate.addNewUser(false);
			contact.setUserPath();
			try {
				contact.createFolder();
				new User((Contact) contact);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return exist?false:true;


	}	@Override
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
