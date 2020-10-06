package br.com.file.process.files;

import java.io.File;
import java.io.FilenameFilter;

public class ExtenionFileNameFilter implements FilenameFilter {

	private String extension;
	
	
	
	public ExtenionFileNameFilter(String extension) {
		this.extension = extension;
	}


	@Override
	public boolean accept(File dir, String name) {

		int index = -1;
		
		if((index = name.lastIndexOf('.')) > 0) {
			String str = name.substring(index);
			
			if(str.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		
		return false;
	}

}
