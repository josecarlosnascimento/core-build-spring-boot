package br.com.file.standalone;

import org.core.file.commons.command.Command;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import br.com.file.commons.command.CommandFacade;

@SpringBootApplication
@ComponentScan("br.com.file")
public class Main {

	public static void main(String[] args) {
//		SpringApplication.run(Main.class, args);
		
		ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
		
		CommandFacade commandFacade = applicationContext.getBean(CommandFacade.class);
		
		Command c = commandFacade.getCommandByName("--file-command");
		
		c.showHelp();
	}

}
