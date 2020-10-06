package br.com.file.commons.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.file.commons.concurrent.TaskProcess;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SplitFileProcess extends TaskProcess<Integer> {

	@Autowired
	private ApplicationContext contex;
	private int linesPerFile;
	private boolean headerPresent;
	private String header;
	private File[] workFiles;
	private List<File> resultFiles = new ArrayList<File>();
	
	
	@Override
	public Integer process() {

		try {
			splitFilesInParallel();
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}

	}

	private void splitFilesInParallel() {
		List<SplitFileTaskProcess> splits = new ArrayList<SplitFileTaskProcess>();

		for (File file : workFiles) {
			SplitFileTaskProcess pr = contex.getBean(SplitFileTaskProcess.class, file, linesPerFile, headerPresent, header);
			splits.add(pr);
			pr.processAssynchronous();
		}

		while (!isDone(splits)) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		int ret = 0;
		
		for (SplitFileTaskProcess splitFileTaskProcess : splits) {
			if(!splitFileTaskProcess.getProcessReturn().equals(0)) {
				ret = 1;
			}
		}
		
		if(ret == 0) {
			for (SplitFileTaskProcess splitFileTaskProcess : splits) {
				resultFiles.addAll(splitFileTaskProcess.getResultFiles());
			}
		}
		
	}

	private static boolean isDone(List<? extends TaskProcess> list) {
		for (TaskProcess taskProcess : list) {
			if (!taskProcess.isFinished()) {
				return false;
			}
		}
		return true;
	}

}
