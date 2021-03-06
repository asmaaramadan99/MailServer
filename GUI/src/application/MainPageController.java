package application; 
import javafx.scene.control.Button;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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
  @FXML
  private VBox buttonsWrapper;	
  @FXML  
  private TextField folderToAddName;
  @FXML  
  private TextField folderToDelName;
  @FXML  
  private TextField folderToMoveName;
  @FXML  
  private TextField oldName;
  @FXML  
  private TextField newName;
  @FXML
  private Label name;
  
  ObservableList <String> filterList= FXCollections.observableArrayList("subject","date","priority","sender");
  ObservableList <String> sortList= FXCollections.observableArrayList("Default","Priority","Sender","Subject","Body");
 
  
  void initMainPageStuff() {
	  listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	  comboBoxFilter.setItems(filterList);
	  comboBoxSort.setItems(sortList);  
	  addFolderButtons();
	  
	  searchFor.setOnAction(new EventHandler<ActionEvent>() {
		  public void handle(ActionEvent event) {
			  filter(null);
		  }
	   });
	  
	  name.setText(myApp.currentUser.user.getName()  + " - "
			  + myApp.currentUser.user.getEmail()  );
	  
	  try {
		chooseFolder("Inbox");
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
  }
  
  void addFolderButtons() {
	  buttonsWrapper.getChildren().removeAll(buttonsWrapper.getChildren());
	  buttonsWrapper.setAlignment(Pos.CENTER);
	  String folders[] = myApp.getUserFolderNames();
	  for(int i=0; i<folders.length; i++) {
		  Button button = new Button(folders[i]);
		  buttonsWrapper.getChildren().add(button);
		  button.setMaxWidth(Double.MAX_VALUE);
		  button.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e)  {
			    	try {
						chooseFolder(button.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			});
		  buttonsWrapper.setMargin(button,new Insets(5,5,5,0));
		  
	  }
  }

  public void reload(ActionEvent event) throws Exception
  {   
	  System.out.println("reloading");
	  chooseFolder(myApp.currentFolder.name);
  }
  
  @FXML
  public void SignIn(ActionEvent event) throws Exception
  {   
	  myApp.signin("moaz@gmail.com", "pass");
	  
	  if(myApp.signin(signInEmail.getText(),signInPass.getText()))
	  {
		    Stage HomePageStage=new Stage();
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
			Parent root=loader.load();
			MainPageController c=loader.getController();
			c.setApp(myApp);
			Scene scene =new Scene(root,1070,650);
			HomePageStage.setResizable(false);
			HomePageStage.setTitle("Mail Server");
			HomePageStage.setScene(scene); 
			//HomePageStage.setMaximized(true);
			HomePageStage.show(); 
			//chooseFolder("inbox");
			
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
  
  public void deleteFolder(ActionEvent event) throws IOException {
	  System.out.println("deleting a folder");
	  String folderName=folderToDelName.getText();
	  if(folderName != "Inbox" && folderName != "Drafts" &&
			  folderName != "Trash" && folderName != "Sent") {
		myApp.deleteFolder(folderToDelName.getText());
	  	addFolderButtons();
	  }
	  
  } 
  
 
  public void makeNewFolder(ActionEvent event) throws IOException {
	  myApp.createFolder(folderToAddName.getText());
	  addFolderButtons();
  }
  
  public void moveMails(ActionEvent event) throws IOException {
	  ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
	  SinglyLinkedList mails = new SinglyLinkedList();
	  int i=0; 
	  
      for(Object o : selectedIndices){
    	  int index = (int)(o.toString()).charAt(0);
    	  index -= 48;
          System.out.println(index);
          System.out.println(index);
          Mail m = (Mail)mail[index];
          mails.add(m);
      }
      
      myApp.moveEmails(mails, new Folder(folderToMoveName.getText()));
      chooseFolder(myApp.currentFolder.name);
  }
  
  public void deleteMails(ActionEvent event) throws IOException {
	  ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
	  SinglyLinkedList mails = new SinglyLinkedList();
	  int i=0;
	  
      for(Object o : selectedIndices){
    	  int index = (int)(o.toString()).charAt(0);
    	  index -= 48;
          System.out.println(index);
          System.out.println(index);
          Mail m = (Mail)mail[index];
          mails.add(m);
      }
      
      myApp.deleteEmails(mails);
      chooseFolder(myApp.currentFolder.name);
      
  }
  
  public void renameFolder(ActionEvent event) {
	  String old = oldName.getText();
	  String ne = newName.getText();
	  myApp.renameFolder(old, ne);
	  addFolderButtons();
  }
  
  
  @FXML
  public void comboFilter() {
	  
	  
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
	
	Scene scene =new Scene(root,750,550);
	sendEmailPage.setTitle("Mail Server");
	sendEmailPage.setResizable(false);
	sendEmailPage.setScene(scene);
	sendEmailPage.show();
  }
  

	public void viewMailPage(App app,Mail mail) throws IOException {
		
		if(myApp.currentFolder.name.equals("Drafts")== true) {
			Stage sendEmailPage = new Stage();
			FXMLLoader loader= new FXMLLoader(getClass().getResource("SendEmail.fxml"));
			Parent root = loader.load();
			 
			SendEmailController k = loader.getController();
			System.out.println(app);
			k.setApp(app);
			k.setInfo(mail);
			
			Scene scene =new Scene(root,700,500);
			sendEmailPage.setTitle("Mail Server");
			sendEmailPage.setResizable(false);
			sendEmailPage.setScene(scene);
			sendEmailPage.show();
			
			return;
		}
		
		
		Stage viewEmailPage = new Stage();
		FXMLLoader loader= new FXMLLoader(getClass().getResource("viewEmail.fxml"));
		Parent root = loader.load();

		viewEmailController c = loader.getController();
		c.setApp(app);
		c.setMail( mail );
		
		Scene scene =new Scene(root,600,500);
		viewEmailPage.setTitle("Mail Server");
		viewEmailPage.setResizable(false);
		viewEmailPage.setScene(scene);
		viewEmailPage.show();
	}
  
  public void sort(ActionEvent event) throws IOException {
	  
	  String type=comboBoxSort.getValue().toLowerCase();
	  listView.getItems().clear();
	  if(type.equals("priority"))
		  myApp.setVeiwOptions(null, "priority", null, null);
	  	
	  else
		  myApp.setVeiwOptions(null, type, null, null);
		
	  chooseFolder(myApp.currentFolder.name);
  }
	

  
  public void filter(ActionEvent event) {
	  if(comboBoxFilter.getValue() == null)
		  return;
	  
	  listView.getItems().clear();
	  
	  String type=comboBoxFilter.getValue().toLowerCase();
	  int p= Integer.parseInt(page.getText());
	  myApp.setVeiwOptions(myApp.currentFolder.name, 
			  null, type,
			  searchFor.getText() );
	  mail=myApp.listEmails(p);	
	  
	  for(int i=0;i<mail.length;i++) {
		  Mail n=new Mail();
		  n= (Mail) mail[i];
		  if(n!=null) 
			  listView.getItems().addAll("Subject: "+n.basicInfo.subject );
		  
		  else
			  break;
	  } 
  }
  
  

 
}