package br.com.file.commons.utils;

import java.io.File;

import br.com.file.process.files.ExtenionFileNameFilter;

public class FileUtils {
	
	public static File moveFile(String sourceDirectory, String targetDiretory) {
		
		File movedFile;
		
		File sourceFile = new File(sourceDirectory);
		
		if(!sourceFile.exists() || !sourceFile.isFile()) {
			throw new IllegalArgumentException("Erro: "+sourceDirectory+" não é valido ou não existe.");
		}
		
		File targetFile = new File(targetDiretory);
		
		if(!targetFile.exists()) {
			if(!targetFile.mkdir()) {
				throw new RuntimeException("Erro: Não foi possível criar o diretoriio: "+targetDiretory);
			}
		}
		
		movedFile = new File(targetFile, sourceFile.getName());
		
		if(movedFile.exists()) {
			boolean nOk = true;
			
			int i = 0;
			
			while (nOk) {
				int indexOfDot = movedFile.getName().lastIndexOf(".");
				File file = new File(targetFile, movedFile.getName().substring(0, indexOfDot)+"_"+i+".csv");
				
				if(file.exists()) {
					i++;
					continue;
				}
				
				nOk = false;
				movedFile.renameTo(file);
				
			}
			movedFile = new File(targetFile,sourceFile.getName());
			if(!sourceFile.renameTo(movedFile)) {
				throw new RuntimeException("Erro: Não foi possível mover o arquivo: "+sourceDirectory);
			}
		
		}
		
		
		
		return movedFile;
	}

	public static File[] listFilesByExtension(String directory, String extension, boolean b) {

		File f = new File(directory);
		
		if(!f.isDirectory()) {
			throw new RuntimeException("Erro: "+f+" não eh um diretorio");
		}
		
		File[] file = f.listFiles(new ExtenionFileNameFilter(extension));
		
		if(b && (file == null || file.length == 0)) {
			throw new IllegalArgumentException("Erro: não há arquivos para processar.");
		}
		
		return file;
	}
	
	public static File[] moveAllFilesToDirectory(File[] files, String directory) {
		File[] movedFles = new File[files.length];
		for (int i = 0; i < files.length; i++) {
			movedFles[i] = moveFile(files[i].getPath(), directory);
		}
		return movedFles;
	}
}
