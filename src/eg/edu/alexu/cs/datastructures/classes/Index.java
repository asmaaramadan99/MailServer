package eg.edu.alexu.cs.datastructures.classes;

import java.io.File;

import eg.edu.alexu.csd.datastructure.DoubleLinkedList;

public class Index {
	
	protected static String IndexFilePath;
   
	
	public static void writeToIndexFile(MailBasicInfo basicInfo)
	{   File readFrom=new File(IndexFilePath);
	    if(readFrom.length()==0)
	    {
	    	 DoubleLinkedList currentEmails=new DoubleLinkedList();
	    	 currentEmails.add(basicInfo);
	    	 FileManager.writeToFile(currentEmails,IndexFilePath);
	    	
	    }
	    else {
		DoubleLinkedList currentEmails=new DoubleLinkedList();
		currentEmails=getListFromIndexFile();
		currentEmails.add(basicInfo);
		FileManager.writeToFile(currentEmails,IndexFilePath);
	    }
		
	}
	
	public static DoubleLinkedList getListFromIndexFile()
	{
		DoubleLinkedList currentEmails=new DoubleLinkedList();
		 currentEmails=(DoubleLinkedList) FileManager.getFile(IndexFilePath);
		return currentEmails;
		
	}
	

}
