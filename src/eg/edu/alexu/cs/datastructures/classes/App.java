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
	
	private  final long serialVersionUID = 1L;
	protected static String rootPath;
	protected static  String accountsFolderPath;
	public static String attachmentsFolderPath;
	public User currentUser; 
	protected String SystemUsersPath;
	public Folder currentFolder;
	Filter currentFilter;
	String currentSort;
	DoubleLinkedList allMails;
	
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
			c.setUserPath();
			this.currentUser=(User) FileManager.getFile(c.getUserPath()+File.separator+"userInfo.bin");
		}

		return exist&&authenticate.matchPass?true:false;

	}

	

	@Override
	public boolean signup(IContact contact) {

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
		}
		return exist?false:true;

	}
 
	
	DoubleLinkedList clearNullMails(DoubleLinkedList Mails) {
		for(int i=0; i<Mails.size(); i++) {
			if(Mails.get(i) == null)
				Mails.remove(i);		
		}
		return Mails;
	}
	
	void setDefaultViewOptions() {
		currentFolder = new Folder("inbox");
		currentFilter = null;
		currentSort = null;
		
	}

	@Override 
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		
		getAllMails(); // get mails for currentfolder (1st option)
		
		sortMails();
		
		if(currentFilter != null) {
			currentFilter.emails = allMails;
			allMails = currentFilter.getFiltered();
		}		
		
		sortMails();
	} 
	 
	void sortMails() {
		allMails = clearNullMails(allMails);
		if(currentSort == "priority")
			Sort.priority(allMails);
		
		else if(currentSort != null)
			Sort.iterativeQuickSort(allMails, currentSort);
	}
	 
	public void setVeiwOptions(String folderName, String sortType, String 
			filterType, Object filterTarget) {
		if(folderName != null) 
			currentFolder = new Folder(folderName);
		
		if(sortType != null)
			currentSort = sortType;
		
		if(filterType != null && filterTarget != null) {
			getAllMails();
			if(filterType.equals("priority")) {
				currentFilter = new Filter(filterType, 
						Integer.parseInt((String) filterTarget),
						this.allMails);
				
			}
			
			
			else {
				currentFilter = new Filter(filterType, filterTarget,
					this.allMails);
			}
		}
		else 
			currentFilter = null; 
		
		setViewingOptions(currentFolder, currentFilter, null);
	}
		
	void getAllMails() {
		
		allMails = new DoubleLinkedList();
		String folderName = currentFolder.name;
		String folderIndexFile = currentUser.user.getUserPath() +
				File.separator + folderName + 
				File.separator + "index.txt";
		
		Index.IndexFilePath = folderIndexFile;
		DoubleLinkedList basicInfoMails =  Index.getListFromIndexFile();
		
		/// convert basicInfoMails to MailsArray (allMails)
		
		for(int i=basicInfoMails.size()-1; i>=0; i--) {
			MailBasicInfo mba = (MailBasicInfo)basicInfoMails.get(i);
			Mail mail = constructMail(mba);
			allMails.add(mail);
		}
	} 
		
	@Override
	public IMail[] listEmails(int page) {
		
		if(allMails == null)
			getAllMails(); 
			
		
		// pages Are 1,2,3,4
		final Integer numOfEmailsPerPage = 10;
		Mail[] mails = new Mail[numOfEmailsPerPage];
		
		int counter=0;
		int numOfPages = allMails.size() / numOfEmailsPerPage;
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
		Index.IndexFilePath=this.currentUser.user.getUserPath()+File.separator+
				currentFolder.name +File.separator+"index.txt";
        
		for(int i=0; i<mails.size(); i++) {
			Mail a = (Mail)mails.get(i);
			String ID = a.getBasicInfo().ID;
			Index.manipulateIndexInfo(null, ID, "remove");
		}
	} 
 
	@Override 
	public void moveEmails(ILinkedList mails, IFolder des) {
		Index.IndexFilePath=this.currentUser.user.getUserPath()+File.separator+
				currentFolder.name +File.separator+"index.txt";
        
		Folder dest = (Folder)des;
		String destination = this.currentUser.user.getUserPath()+
				File.separator+ dest.name +File.separator+
				"index.txt";
		for(int i=0; i<mails.size(); i++) {
			Mail a = (Mail)mails.get(i);
			String ID = a.getBasicInfo().ID;
			Index.manipulateIndexInfo(destination, ID, "move");
		}
	}

	@Override
	public boolean compose(IMail email) { 
		 
		Mail mail = (Mail) email;
		Queue recievers = mail.receivers;
		String userPath = this.currentUser.user.getUserPath();
		
		if(!validateRecievers(recievers))
			return false;
		
		mail.store(userPath, "Sent");
		
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
			mail.store(receiverPath, "Inbox");
		}
		DoubleLinkedList basicInfoMails =  Index.getListFromIndexFile();
		Index.IndexFilePath = currentUser.user.getUserPath();
		
		return true;
		
	}
	
	public void setAsDraft(Mail email) {
		String userPath = this.currentUser.user.getUserPath();
		email.store(userPath, "Drafts");
	}
	

	public String[] getUserFolderNames() {
		File userFolder = new File(currentUser.user.getUserPath());
		String[] children = userFolder.list();
		String[] folder = new String[children.length-1];
		int minus = 0;
		for(int i=0; i<children.length; i++) {
			if(children[i].equals("userInfo.bin") == true)
			{ minus = 1; continue;}
			folder[i - minus] = children[i];
		//	System.out.println(folder[i]);
		}
		return folder;
	}
	
	public void createFolder(String folderName) {
		try {
			currentUser.createFolder(folderName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteFolder(String folderName) {
		String path = currentUser.user.getUserPath() + 
				File.separator + folderName;
		
		File folder = new File(path);
		if(folder.exists()) {
			FileManager.deleteDir(folder);
			return true;
		}
		
		else
			return false;
	}
	
	public boolean renameFolder(String oldName, String newName) {
		String oldPath = currentUser.user.getUserPath() + 
				File.separator + oldName;
		String newPath = currentUser.user.getUserPath() + 
				File.separator + newName;
		
		File oldFolder = new File(oldPath);
		File newFolder = new File(newPath);
		if(oldFolder.exists() == false || newFolder.exists() == true ||
				oldName.toLowerCase().equals("trash") ||
				oldName.toLowerCase().equals("inbox") ||
				oldName.toLowerCase().equals("sent") ||
				oldName.toLowerCase().equals("drafts"))
			return false;
		return oldFolder.renameTo(newFolder);
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
