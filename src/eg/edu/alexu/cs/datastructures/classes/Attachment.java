package eg.edu.alexu.cs.datastructures.classes;

import java.io.File;
import java.util.UUID;

public class Attachment {
	public static String store(String originalPath) {
		File attachment = new File(originalPath);
		String name = attachment.getName();
		String newName = name + "#" + UUID.randomUUID().toString();
		String newPath = App.attachmentsFolderPath 
				+ File.separator
				+ newName;
				
		FileManager.copyFile(originalPath, newPath);
		return newPath;
	}
	
	
	public static String getName(String path) {
		File attachment = new File(path);
		String fileName = attachment.getName();
		String name = new String("");
		for(int i=0; i<fileName.length(); i++) {
			char c = fileName.charAt(i);
			if(c == '#') break;
			name += c;
		}
		return name;
	}

}
