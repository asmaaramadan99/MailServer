package eg.edu.alexu.cs.datastructures.classes;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
import eg.edu.alexu.cs.datastructures.Interfaces.*;
import eg.edu.alexu.csd.datastructure.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.Queue;

import java.io.File;
import java.io.Serializable;

import MyDataStructures.ILinkedList;

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
	Folder currentFolder;
	Filter currentFilter;
	Sort currentSort;
	SinglyLinkedList allMails;
	
	
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
		createInitialFolders(); 
		createSystemUserFile();		
		setDefaultViewOptions();
		
		
		allMails = new SinglyLinkedList();
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
			new User((Contact)contact);
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
			new User((Contact) contact);
			
			// if he has to sign in agian
			// this.crrent user = new User((Contact) contact);

		}
		return exist?false:true;

	}

	
	void setDefaultViewOptions() {
		currentFolder = new Folder("inbox");
		currentFilter = null;
		currentSort = null;
	}

		@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		this.currentFolder = (Folder) folder;
		this.currentFilter = (Filter) filter;
		this.currentSort = (Sort) sort;
	}

	@Override
	public IMail[] listEmails(int page) {
		// pages Are 1,2,3,4
		
		// could be bigger but 1000 is enough here
		
		final Integer numOfEmailsPerPage = 10;
		SinglyLinkedList allMails = new SinglyLinkedList();
		Mail[] mails = new Mail[numOfEmailsPerPage];
		
		String folderName = currentFolder.name;
		String folderIndexFile = currentUser.user.getUserPath() +
				File.separator + folderName + 
				File.separator + "index.txt";
		
		Index.IndexFilePath = folderIndexFile;
		DoubleLinkedList basicInfoMails =  Index.getListFromIndexFile();
		
		if(basicInfoMails.size() == 0)
			return mails;
		
		
		/// convert basicInfoMails to MailsArray (allMails)
		
		for(int i=0; i<basicInfoMails.size(); i++) {
			MailBasicInfo mba = (MailBasicInfo)basicInfoMails.get(i);
			Mail mail = constructMail(mba);
			allMails.add(mail);
		}
		System.out.println( "num of mails " + allMails.size());	
		// get page from allMails
		int counter=0;
		int numOfPages = basicInfoMails.size() / numOfEmailsPerPage;
		int start = (page-1) * (numOfEmailsPerPage);
		for(int i=start; i<start + numOfEmailsPerPage; i++) {
			
			if(i >= allMails.size())
				break;
			// ------------------------------------
			
			mails[counter] = (Mail)allMails.get(i);
			counter++;
		}
		return mails;
	
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
			
	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		
	}

	@Override
	public boolean compose(IMail email) {
		
		
		Mail mail = (Mail) email;
		
		Queue recievers = mail.receivers;
		if(!validateRecievers(recievers))
			return false;
		
		String userPath = this.currentUser.user.getUserPath();
		mail.store(userPath, "sent");
		
		/// put emails in recievers' files and add them to index Files
		while(recievers.isEmpty() == false) {
			String receiverEmail = (String)recievers.dequeue();	

			String receiverPath = App.accountsFolderPath + 
					File.separator + receiverEmail;
			String receiverInboxPath = receiverPath + File.separator
					+ "inbox";
			String indexFilePath = receiverInboxPath + File.separator
					+  "index.txt";

			Index.IndexFilePath = indexFilePath;
			mail.store(receiverPath, "inbox");
		}
		DoubleLinkedList basicInfoMails =  Index.getListFromIndexFile();
		Index.IndexFilePath = currentUser.user.getUserPath();
		
		return true;
		
	}
	
	boolean validateRecievers(Queue receivers) {
		Queue a = new Queue();
		while(receivers.isEmpty() == false) {
			String receiverEmail = (String)receivers.dequeue();
			
			if(emailExists(receiverEmail) == false)
				return false;
			
			a.enqueue(receiverEmail.toLowerCase());
		}
		
		while(a.isEmpty() == false) {
			String receiverEmail = (String)a.dequeue();
			receivers.enqueue(receiverEmail);
		}
		
		return true;
	}
	
	
	
	
	boolean emailExists(String email) {
		
		Contact receiverContact = new Contact(email);
		Authentication authenticate=new Authentication(SystemUsersPath,
				receiverContact);
		
		if(!authenticate.isValidEmailFormat())
			return false;
		
		boolean ans = false;
		try {
			ans = authenticate.exist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return ans;
	}
	
	User getUser(String userEmail) {
		String userPath = App.accountsFolderPath + File.separator +
				userEmail + File.separator + "userInfo.bin";
		
		return (User)FileManager.getFile(userPath);
	}
	
	Mail constructMail(MailBasicInfo a) {
		Mail b = new Mail();
		b.basicInfo = a;
		return b;
	}
	

}
