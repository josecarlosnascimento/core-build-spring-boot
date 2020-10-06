package process;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.file.commons.concurrent.TaskProcess;
import br.com.file.commons.process.SplitFileTaskProcess;
import br.com.file.commons.utils.FileUtils;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LoadFileProcess extends TaskProcess<Integer>{

	@Autowired
	private ApplicationContext contex;
	
	@Value("#{br.com.file.command.folder}")
	private String folderWork;

	@Value("#{br.com.file.command.linesPerFile:50000000}")
	private int linesPerFile;

	@Override
	public Integer process() {


		File[] workFiles = null;
		
		File[] inputFiles = FileUtils.listFilesByExtension("","",true);
		
		workFiles = FileUtils.moveAllFilesToDirectory(inputFiles, folderWork);
		
		SplitFileTaskProcess split = contex.getBean(SplitFileTaskProcess.class, workFiles, linesPerFile, true, "HEADER");
		
		split.processSynchronous();
		
		if(split.getProcessReturn() != 0) {
			throw new IllegalStateException("Erro ao dividir arquivos");
		}
		
		workFiles = split.getResultFiles().toArray(new File[0]);
		
		return null;
	}

}
