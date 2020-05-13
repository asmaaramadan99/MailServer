package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class viewEmailController {
	
  App app;
  Mail mail;
  SinglyLinkedList attachments = new SinglyLinkedList();
  
  
  @FXML
  private Label subject;
   
  @FXML
  private Label sender;
  
  @FXML
  private TextArea bodyText;
  
  @FXML
  private void initialize() {
	System.out.println("controller");
	bodyText.setWrapText(true);		
  }
  
  public void downloadAttachments(ActionEvent event) {
	  
	  if(!Desktop.isDesktopSupported()){
          sender.setText("no support availabe for opennign files");
          return;
      }
	  Desktop desktop = Desktop.getDesktop();
	  
	  System.out.println(attachments.size());
	   
	   
	  for(int i=0; i<attachments.size(); i++) {
		  
		  File file = new File( ((String) attachments.get(i)) );
		  try {
			desktop.open(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
  }
  
  
  public void setApp(App app) {
	   this.app =  app;
  }
  
  public void setMail(Mail mail) {
	  sender.setText("From: " + mail.getSender());
	  subject.setText("Subject: " + mail.getSubject());
	  bodyText.setText(mail.getBodyText());
	  attachments = mail.getattachments();
	  if(attachments == null)
		  attachments = new SinglyLinkedList();
  }
  
  public String modifyPath(String path) {
	  return path.split("#")[0];
  }
  
}


