package application;

import java.io.File;
import java.util.Date;

import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SendEmailController {
	
  App app;
  SinglyLinkedList attachments = new SinglyLinkedList();
  
  ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "1",
		        "2",
		        "3",
		        "4",
		        "5"
		    );
  @FXML
  private TextField recievers;
  @FXML
  private TextField subject;
  @FXML
  private TextField signUpPass;
  @FXML
  private TextArea messageBody;
  @FXML
  private Label SendBtn;
  @FXML
  private Label warning;
  @FXML
  private ComboBox priorityBox;
  
  @FXML
  private void initialize() {
	  System.out.println("started");
	  priorityBox.setItems(options);
	  priorityBox.setValue("1");
  }
  
  public void addAttachments(ActionEvent event) throws Exception
  {   
	  FileChooser fileChooser = new FileChooser();
	  File selectedFile = fileChooser.showOpenDialog(new Stage());
	  attachments.add(selectedFile.getAbsoluteFile().toString());
  }
  
  public void Send(ActionEvent event)
  { 
	  String msgBody = messageBody.getText();
	  String msgSubject =  subject.getText();
	  String msgRecievers = recievers.getText();
	  String date = (new Date()).toString();
	  int priority = (int)((priorityBox.getValue().toString()).charAt(0)) - 48;  
	  
	  Mail mail = new Mail( app.currentUser.user.getEmail()
			  , msgSubject, msgBody,
		date, priority, attachments ); 
	  
	  mail.receivers = emailsSeparator(msgRecievers);
	  
	  if(app.compose(mail)) {
		  warning.setText("Email Sent");
	  }
	  
	  else
	  {
		  warning.setText("Something is Wrong. Try again!");
	  }
  }
 
  Queue emailsSeparator( String mails ) {
	  mails = mails.trim().replaceAll(" +", " ");
	  Queue receivers = new Queue();
	  String[] arr = mails.split(" ");  
	  for(int i=0; i<arr.length; i++) {
		  System.out.println(arr[i]);
		  if(arr[i].isEmpty() == false)
		   receivers.enqueue(arr[i]);
	  }
	  System.out.println(receivers.size());
	  
	  return receivers; 
  }
  
  static boolean isValid(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
  
   
  public void setApp(App app) {
	   this.app = app;
  }

}


