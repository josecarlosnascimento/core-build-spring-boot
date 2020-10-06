package br.com.file.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.file.commons.command.Command;
import process.LoadFileProcess;

@Service
public class LoadFileCommand implements Command{

	@Autowired
	private ApplicationContext contex;
	
	@Override
	public int execute(List<String> args) {

		long init = System.currentTimeMillis();
		
		LoadFileProcess process = contex.getBean(LoadFileProcess.class);
		process.processSynchronous();
		
		int status = process.getProcessReturn();
		long end = System.currentTimeMillis();
		
		return 0;
	}

	@Override
	public void showHelp() {
	}

	@Override
	public String getCommandName() {
		return "--file-command";
	}

}
