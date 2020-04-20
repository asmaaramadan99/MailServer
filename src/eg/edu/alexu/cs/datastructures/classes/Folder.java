package eg.edu.alexu.cs.datastructures.classes;

import java.io.;

import MyDataStructures.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;


/**
 * 
 * @author Moaz
 * - deprecated
 * - replaced by File ready class.
 *	 
 */


// TODO tests
public class Folder implements IFolder{

	String name;
	String path;
	String fullPath;
	
	Folder(String name){
		this.name = name;
	}
	
	Folder(String name, String path){
		this.name = name;
		this.path = path;
		this.fullPath = name + File.separator + path;
	}
	
	Folder(String fullPath){
		this.fullPath = fullPath;
		File f = new File("C:\\Hello\\AnotherFolder\\The File Name.PDF");
		this.name = f.getName();
		this.path = f.getParentFile().getName(); 
	}
	
	// TODO .. Getter and Setter (check that the path is valid)
}
