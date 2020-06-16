package eg.edu.alexu.cs.datastructures.classes;
 
 
import java.io.File;
import java.util.Date;

import eg.edu.alexu.csd.datastructure.DoubleLinkedList;
 
public class IndexTest {
   
    /*delete root folder before testing
     * Index path before move =current folder
     * after move= the destination folder
     */
    public static void main(String[] args) {
       
        App myapp=new App();
        Contact m = new Contact("a@gmail.com", "pass");
        m.setName("asmaa");
        m.setUserPath();
        System.out.println((myapp.signup(m)));
        System.out.println(myapp.signin("a@gmail.com","pass"));
       
        String id=getId(myapp.currentUser.user.getUserPath());
       
        Index.IndexFilePath=myapp.currentUser.user.getUserPath()+File.separator+"Inbox"+File.separator+"index.txt";
        DoubleLinkedList l=Index.getListFromIndexFile();
        //inbox path
        System.out.println(Index.IndexFilePath);
        System.out.println(l.size());
        
        Index.manipulateIndexInfo(myapp.currentUser.user.getUserPath()+File.separator+"Drafts"+File.separator+"index.txt",id,"move");
        //draft folder
        System.out.println(Index.IndexFilePath);
        l=Index.getListFromIndexFile();
        //became 1
        System.out.println(l.size());
        //back to inbox
        Index.IndexFilePath=myapp.currentUser.user.getUserPath()+File.separator+"Inbox"+File.separator+"index.txt";
        l=Index.getListFromIndexFile();
        //became zero
        System.out.println(l.size());
    } 
   
    public static String getId(String path)
    {
        Mail t=new Mail("a@gmail.com","s","sd",new Date(),1,null);
        t.store(path,"Inbox"); 
        return t.getBasicInfo().ID;
    }
 
}