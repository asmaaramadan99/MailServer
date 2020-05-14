package application; 
import java.awt.Button;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
 
import eg.edu.alexu.cs.datastructures.Interfaces.IMail;
import eg.edu.alexu.cs.datastructures.classes.App;
import eg.edu.alexu.cs.datastructures.classes.Contact;
import eg.edu.alexu.cs.datastructures.classes.Filter;
import eg.edu.alexu.cs.datastructures.classes.Folder;
import eg.edu.alexu.cs.datastructures.classes.Index;
import eg.edu.alexu.cs.datastructures.classes.Mail;
import eg.edu.alexu.cs.datastructures.classes.MailBasicInfo;
import eg.edu.alexu.cs.datastructures.classes.Sort;
import eg.edu.alexu.cs.datastructures.classes.User;
import eg.edu.alexu.csd.datastructure.DoubleLinkedList;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public class MainPageController{
 
 
  App myApp=new App();
  IMail [] mail;
  public App getApp() {
		return myApp;
	}
 
	public void setApp(App myApp) {
		this.myApp = myApp;
		
		initMainPageStuff();
	}
 
 
 
  @FXML  
  private TextField signInEmail;
  @FXML
  private TextField signInPass;
  @FXML
  private TextField signUpEmail;
  @FXML
  private TextField signUpPass;
  @FXML  
  private TextField page;
  @FXML
  private Label signUpStatus;
  @FXML
  private Label signInStatus;
  @FXML
  private TextField signUpName;
  @FXML
  private TextField signUpBirthday;
  @FXML
  private ListView <String> listView;
  @FXML
  private ComboBox<String> comboBoxFilter;
  @FXML
  private ComboBox<String> comboBoxSort;
  @FXML  
  private TextField searchFor;
 
  
  void initMainPageStuff() {
	  listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	  
  }
  @FXML
  public void SignIn(ActionEvent event) throws Exception
  {   
	  if(myApp.signin(signInEmail.getText(),signInPass.getText()))
	  {
		    Stage HomePageStage=new Stage();
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
			Parent root=loader.load();
			MainPageController c=loader.getController();
			c.setApp(myApp);
			Scene scene =new Scene(root,800,600);
			HomePageStage.setResizable(false);
			HomePageStage.setTitle("Mail Server");
			HomePageStage.setScene(scene);
			HomePageStage.show(); 
	  }
	  
	  else 
		  signInStatus.setText("Incorrect email or password");
	  	  
  }
 
 
 
  @FXML
  public void SignUp(ActionEvent event) 
  {     
	  Contact contact=new Contact();
	  contact.setName(signUpName.getText());
	  contact.setEmail(signUpEmail.getText());
	  contact.setPassword(signUpPass.getText());
	  contact.setBirthday(signUpBirthday.getText());
	  contact.setUserPath();
 
 
 
	  if(myApp.signup(contact))
		  signUpStatus.setText("You signed up successfully");
	  
	  else
		  signUpStatus.setText("Sign up failed");  
	  
  }
 
 
 
 
  public void chooseFolder(String folder) throws IOException {
	  listView.getItems().clear();
	  myApp.setVeiwOptions(folder, null, null, null);
	  
	  int p= Integer.parseInt(page.getText());
	  
	  mail=myApp.listEmails(p);
	  System.out.println(mail.length);
 
	  for(int i=0;i<mail.length;i++) {
		  Mail n=new Mail();
		  n= (Mail) mail[i];
		  if(n!=null) 
			  listView.getItems().addAll("Subject: "+n.basicInfo.subject );
		  
		  else
			  break;
	  } 
		  
 
  }
 
 
 
  public void openInbox(ActionEvent event) throws IOException {
	  chooseFolder("Inbox");
  }
  public void openTrash(ActionEvent event) throws IOException {
	  chooseFolder("Trash");
  }
  public void openDrafts(ActionEvent event) throws IOException {
	  chooseFolder("Drafts");
  }
  public void openSent(ActionEvent event) throws IOException {
	  chooseFolder("Sent"); 
  }
 
 
  ObservableList <String> filterList= FXCollections.observableArrayList("date","priority","sender");
  ObservableList <String> sortList= FXCollections.observableArrayList("Default","Priority","Sender","Subject","Body");
 
  @FXML
  public void comboFilter() {
	  comboBoxFilter.setItems(filterList);
	  comboBoxSort.setItems(sortList);
 
  }
  
  
  @FXML
  public void openEmail() throws IOException {
	  ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();

      for(Object o : selectedIndices){
    	  int index = (int)(o.toString()).charAt(0);
    	  index -= 48;
          System.out.println(index);
          System.out.println(index);
          Mail m = (Mail)mail[index];
          viewMailPage(myApp,m);
      }
  }
  
  @FXML 
  public void compose() {
	//  System.out.println(123);
	  try {
		sendEmailPage(myApp);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void sendEmailPage(App app) throws Exception {
		
	Stage sendEmailPage = new Stage();
	FXMLLoader loader= new FXMLLoader(getClass().getResource("SendEmail.fxml"));
	Parent root = loader.load();
	
	SendEmailController k = loader.getController();
	System.out.println(app);
	k.setApp(app);
	
	
	Scene scene =new Scene(root,700,500);
	sendEmailPage.setTitle("Mail Server");
	sendEmailPage.setResizable(false);
	sendEmailPage.setScene(scene);
	sendEmailPage.show();
  }
  

	public void viewMailPage(App app,Mail mail) throws IOException {
		Stage sendEmailPage = new Stage();
		FXMLLoader loader= new FXMLLoader(getClass().getResource("viewEmail.fxml"));
		Parent root = loader.load();

		viewEmailController c = loader.getController();
		c.setApp(app);
		c.setMail( mail );
		
		Scene scene =new Scene(root,600,500);
		sendEmailPage.setTitle("Mail Server");
		sendEmailPage.setResizable(false);
		sendEmailPage.setScene(scene);
		sendEmailPage.show();
	}
  
  public void sort(ActionEvent event) {
	  String type=comboBoxSort.getValue();
	  
		MailBasicInfo[] array=new MailBasicInfo [10];
		Index.IndexFilePath=myApp.currentUser.user.getUserPath()+File.separator+myApp.currentFolder.name+File.separator+"index.txt";
		System.out.println(Index.IndexFilePath);
	 
		DoubleLinkedList list=new DoubleLinkedList();
	    list=Index.getListFromIndexFile();
	    System.out.println(list.size());
	 
		if(type=="Priority")
			Sort.priority(list);
		else
			Sort.iterativeQuickSort(list, type);
	 
		for(int i=0;i<list.size();i++) {
			if(list.get(i)!=null)
			array[i]=(MailBasicInfo) list.get(i);
		}
	 
	 
		  listView.getItems().clear();
	 
		  for(int i=0;i<array.length;i++) {
			  MailBasicInfo n=new MailBasicInfo();
			  n= (MailBasicInfo) array[i];
			  if(n!=null) {
			  listView.getItems().add("Subject: "+n.subject);
			  }
	 
		  }
  }
	

  
  public void filter(ActionEvent event) {
  }

 
}