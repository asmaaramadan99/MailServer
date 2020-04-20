package eg.edu.alexu.cs.datastructures.classes;

import MyDataStructures.*;
import java.io.File;
import eg.edu.alexu.cs.datastructures.Interfaces.*;

public class Main {

	public static void main(String args[]) {
		
		App app = new App();
		User asmaa = new User("Asmaa", "Asmaa@mail.com", "somePassword");
		
	}
	
	void foledrsCode() {

		System.out.println("Working Directory = " + File.separator + System.getProperty("user.dir"));
		
		File folder = new File("C:\\Users\\Moaz\\Desktop\\Test\\madeDir\\asmaa");
		folder.mkdirs();
		folder.delete();
		folder.mkdirs();
		
		//get children
		folder = new File("C:\\Users\\Moaz\\Desktop\\Test");
		String[] files = folder.list();
		for(String f: files) {
			System.out.println(folder.getAbsolutePath() + files.separator + f);
			
		}
		
	}
}
