package eg.edu.alexu.cs.datastructures.classes;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import eg.edu.alexu.csd.datastructure.*;
import eg.edu.alexu.cs.datastructures.Interfaces.*;


/**
 * 
 * @author Moaz
 * - deprecated
 * - replaced by File ready class.
 *	 
 */


class Folder implements IFolder,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	
	Folder(String name){
		this.name = name;
	}
	
}
