package br.com.file.command;

import java.util.List;

import org.core.file.commons.command.Command;
import org.springframework.stereotype.Service;

import static org.core.file.commons.command.CommandUtils.println;

@Service
public class LoadFileCommand implements Command{

	@Override
	public int execute(List<String> args) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void showHelp() {
		println("yes");
	}

	@Override
	public String getCommandName() {
		return "--file-command";
	}

}
