package br.com.file.standalone;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import br.com.file.commons.command.Command;
import br.com.file.commons.command.CommandFacade;
import br.com.file.commons.utils.StatusReturn;

@SpringBootApplication
@ComponentScan("br.com.file")
public class Main {
	
	public static void main(String[] args) {
		System.exit(run(args));
	}

	public static int run(String[] args) {
//		SpringApplication.run(Main.class, args);
		
		ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
		
		CommandFacade commandFacade = applicationContext.getBean(CommandFacade.class);
		
		if(args.length >= 1) {
			Command command = commandFacade.getCommandByName(args[0]);
			
			if(command == null) {
				commandFacade.showHelp();
				return StatusReturn.ERROR.ordinal();
			}
			
			List<String> listArgs = Arrays.asList(args);
			
			command.execute(listArgs);
		}else {
			commandFacade.showHelp();
		}
		return StatusReturn.OK.ordinal();
	}

}
