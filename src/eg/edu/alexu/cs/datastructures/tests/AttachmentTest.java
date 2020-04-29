package eg.edu.alexu.cs.datastructures.tests;

import eg.edu.alexu.cs.datastructures.classes.*;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class AttachmentTest {

	String currentFolder = System.getProperty("user.dir") + "\\src\\eg\\edu\\alexu\\cs\\datastructures\\tests";
	String dummyFilepath =  currentFolder + File.separator + "testText.txt";
	File dummyFile = new File(dummyFilepath);
	int[] arr = new int[]{1,2,3,4,5};
	
	@Test
	public void getName() {
		String path ="C:\\whatevere\\asdass\\ok#123124141";
		assertEquals("ok",Attachment.getName(path));
	}
	
	@Test
	public void store() {
		App a = new App();
		FileManager.writeToFile(arr, dummyFilepath);
		String dest = Attachment.store(dummyFilepath);
		assertArrayEquals(arr, (int[])FileManager.getFile(dest) );
		FileManager.deleteDir(new File(dest));
		
	}

}
