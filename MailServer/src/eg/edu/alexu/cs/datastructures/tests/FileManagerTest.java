package eg.edu.alexu.cs.datastructures.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import eg.edu.alexu.cs.datastructures.classes.*;

public class FileManagerTest {

	@Test
	public void test() {
		int[] arr = new int[]{1,2,3,4,5};
		String path =  System.getProperty("user.dir") + File.separator + "testText.txt";
		FileManager.writeToFile(arr, path);
		assertArrayEquals(arr, (int[])FileManager.getFile(path) );
		
	}

}
