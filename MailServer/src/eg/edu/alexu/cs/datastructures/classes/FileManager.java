package eg.edu.alexu.cs.datastructures.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {
	public static void writeToFile(Object obj, String filePath) {
		try {
			FileOutputStream fout = new FileOutputStream(filePath);
			ObjectOutputStream Oout = new ObjectOutputStream(fout);
			Oout.writeObject(obj);
			Oout.flush();
			Oout.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Object getFile(String filePath) {
		Object obj = null;
		System.out.println(filePath);
		try {
			FileInputStream fin = new FileInputStream(filePath);
			ObjectInputStream Oin = new ObjectInputStream(fin);
			obj = Oin.readObject();
			Oin.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return obj;
	}
	
	public static void deleteDir(File dir) {
	    File[] files = dir.listFiles();
	    if(files != null) {
	        for (File file : files) {
	            deleteDir(file);
	        }
	    }
	    dir.delete();
	}
}
