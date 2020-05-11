package application;

import eg.edu.alexu.cs.datastructures.classes.App;
import eg.edu.alexu.cs.datastructures.classes.Contact;
import eg.edu.alexu.csd.datastructure.SinglyLinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainPageController {
	

	  App myApp=new App();
  @FXML  
  private TextField signInEmail;
  @FXML
  private TextField signInPass;
  @FXML
  private TextField signUpEmail;
  @FXML
  private TextField signUpPass;
  @FXML
  private Label signUpStatus;
  @FXML
  private Label signInStatus;
  @FXML
  private TextField signUpName;
  @FXML
  private TextField signUpBirthday;
  
  @FXML
  public void SignIn(ActionEvent event) throws Exception
  {   
	  if(myApp.signin(signInEmail.getText(),signInPass.getText()))
			  {
		    Stage HomePageStage=new Stage();
			Parent root=FXMLLoader.load(getClass().getResource("homePage.fxml"));
			Scene scene =new Scene(root,700,400);
			HomePageStage.setTitle("Mail Server");
			HomePageStage.setResizable(false);
			HomePageStage.setScene(scene);
			HomePageStage.show();
	        
			  }
	  else
	  { 
		  signInStatus.setText("Incorrect email or password");
	  }
		  
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
 
  
  
 
 

}
