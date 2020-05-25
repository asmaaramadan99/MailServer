package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import eg.edu.alexu.cs.datastructures.classes.*;
import eg.edu.alexu.csd.datastructure.*;

public class Main extends Application {
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage MainPage) throws Exception {
		// TODO Auto-generated method stub
		
		FXMLLoader loader= new FXMLLoader(getClass().getResource("main.fxml"));
		Parent root = loader.load();
		
		/*SendEmailController k = loader.getController();
		System.out.println(app); 
		k.setApp(app); 
		*/
		
		Scene scene =new Scene(root,700,500);
		MainPage.setTitle("Mail Server");
		MainPage.setResizable(false);
		MainPage.setScene(scene);
		MainPage.show();
		//sendEmailPage(app);
        //viewMailPage(app, a);
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
	
	
}

