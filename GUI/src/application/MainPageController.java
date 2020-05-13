package application; 
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
 
public class MainPageController{
 
 
  App myApp=new App();
 
  public App getApp() {
		return myApp;
	}
 
	public void setApp(App myApp) {
		this.myApp = myApp;
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
	  {
		  signUpStatus.setText("You signed up successfully");
	  }
	  else
	  {
		  signUpStatus.setText("Sign up failed");  
	  }
  }
 
 
 
 
  public void chooseFolder(String folder) throws IOException {
 
	  myApp.setVeiwOptions(folder, null, null, null);
	  
	  
	  int p= Integer.parseInt(page.getText());
	  IMail [] mail=new IMail[10];
	  mail=myApp.listEmails(p);
	  System.out.println(mail.length);
 
	  for(int i=0;i<mail.length;i++) {
		  Mail n=new Mail();
		  n= (Mail) mail[i];
		  if(n!=null) 
			  listView.getItems().add("Subject: "+n.basicInfo.subject );
		  
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
 
  public void sort(ActionEvent event) {
	  
  }
	

  public void filter(ActionEvent event) {
  }

 
}