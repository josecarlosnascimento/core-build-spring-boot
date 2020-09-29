package br.com.file.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import br.com.file.commons.command.Command;

@Service
public class LoadFileCommand implements Command{

	@Autowired
	private ApplicationContext contex;
	
	@Override
	public int execute(List<String> args) {
		// TODO Auto-generated method stub
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
