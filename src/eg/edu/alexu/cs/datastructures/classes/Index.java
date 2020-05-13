package eg.edu.alexu.cs.datastructures.classes;
 
import java.io.File;
 
import eg.edu.alexu.csd.datastructure.DoubleLinkedList;
 
public class Index {
   
 
    static String IndexFilePath;
   
    public static void writeToIndexFile(MailBasicInfo basicInfo)
    { 
    	
    	File readFrom=new File(IndexFilePath);
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
    	File readFrom=new File(IndexFilePath);
    	if(readFrom.length()==0)
         return new DoubleLinkedList();
        
        DoubleLinkedList currentEmails=new DoubleLinkedList();
         currentEmails=(DoubleLinkedList) FileManager.getFile(IndexFilePath);
        return currentEmails;
       
    }
   
    /*
     * set IndexFilePath & toIndexFilePath
     */ 
    public static void  manipulateIndexInfo(String destination,String ID,String action)
    {
       DoubleLinkedList mailsInfo = getListFromIndexFile();
       for(int i=0;i<mailsInfo.size();i++)
       {
           MailBasicInfo m=(MailBasicInfo) mailsInfo.get(i);
           if(m.ID.equals(ID))
           {
               if(action.equals("remove"))
               {
                   mailsInfo.remove(i);
                   FileManager.writeToFile(mailsInfo,IndexFilePath);
               }
               if(action.equals("move"))
               {   mailsInfo.remove(i);
                   FileManager.writeToFile(mailsInfo,IndexFilePath);
                   String temp = Index.IndexFilePath;
                   Index.IndexFilePath=destination;
                   writeToIndexFile(m);
                   Index.IndexFilePath = temp;
                   
               }
           }
       }
       
    }
 
}