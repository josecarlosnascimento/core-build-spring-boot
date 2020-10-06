package br.com.file.commons.process;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.com.file.commons.concurrent.TaskProcess;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SplitFileTaskProcess extends TaskProcess<Integer>{
	
	private File file;
	private int numSplitLines;
	private boolean headerPresent;
	private String header = "HEADER";
	private List<File> resultFiles = new ArrayList<File>();
	
	public SplitFileTaskProcess(File file, int numSplitLines, boolean headerPresent) {
		super();
		this.file = file;
		this.numSplitLines = numSplitLines;
		this.headerPresent = headerPresent;
	}

	@Override
	public Integer process() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<File> getResultFiles(){
		return null;
	}

}
