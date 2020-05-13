package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SendEmailController {
	

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
  
  public void addAttachments(ActionEvent event) throws Exception
  {   
	 
  }
  
  public void Send(ActionEvent event)
  { 
	  String msgBody = messageBody.getText();
	  String msgSubject =  subject.getText();
	  String msgRecievers = recievers.getText();
	  
	  System.out.println(msgSubject + msgRecievers +msgBody );
  }
 
  boolean emailsSeprartor( String mails ) {
	 return true; 
  }
 
 

}
