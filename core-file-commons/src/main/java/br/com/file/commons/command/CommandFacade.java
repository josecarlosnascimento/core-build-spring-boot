package br.com.file.commons.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.core.file.commons.command.Command;
import org.core.file.commons.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class CommandFacade{

	@Autowired
	private ApplicationContext context;
	
	private Map<String, Class<?>> commandMap;
	
	public CommandFacade() throws InstantiationException, IllegalAccessException {
		
		commandMap = new HashMap<String, Class<?>>();
		
		List<Package> packagesCommand = findAllCommandPackage();
		
		for (Package pack : packagesCommand) {
			List<Class<?>> classPackage = ReflectionUtils.getClassForPackage(pack);

			for (Class<?> class1 : classPackage) {
				if(!class1.isInterface() && Command.class.isAssignableFrom(class1)) {
					Command instace = (Command) class1.newInstance();
					commandMap.put(instace.getCommandName(), class1);
				}
			}
		}
		
	}

	
	public List<Package> findAllCommandPackage(){
		List<Package> packages = new ArrayList<Package>();
		for (Package pack : Package.getPackages()) {
			if(pack.getName().endsWith("command")) {
				packages.add(pack);
			}
		}
		return packages;
	}

	public Command getCommandByName(String name) {
		Class<?> clas = commandMap.get(name);
		if(clas != null) {
			return (Command) context.getBean(clas);
		}
		
		throw new RuntimeException("Erro ao encontrar elemento "+ name);
	}
}
