package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage MainPage) throws Exception {
		// TODO Auto-generated method stub
		Parent root=FXMLLoader.load(getClass().getResource("main.fxml"));
		Scene scene =new Scene(root,700,400);
		MainPage.setTitle("Mail Server");
		MainPage.setResizable(false);
        MainPage.setScene(scene);
        MainPage.show();
        
		
	}

	
	
}
