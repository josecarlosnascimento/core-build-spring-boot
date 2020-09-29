package br.com.file.commons.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class TaskProcess<T> implements Runnable{

	@Autowired
	private ApplicationContext context;
	private String name;
	private Thread currentThread = null;
	private ProcessStatus status = ProcessStatus.READY;
	private boolean finishedProcess = false;
	private Throwable exception;
	private T processReturn;
	
	public abstract T process();
	
	public void processSynchronous() {
		run();
	}
	
	public synchronized void processAssynchronous() {
		this.currentThread = new Thread(this, getThreadName());
		this.status = ProcessStatus.RUNNING;
		this.currentThread.start();
	}
	
	private String getThreadName() {
		return name;
	}

	@Override
	public void run() {
		status = ProcessStatus.RUNNING;
		try {
			processReturn = process();
			status = ProcessStatus.SUCCESS;
		} catch (Exception e) {
			status = ProcessStatus.ERROR;
		}finally {
			finishedProcess = true;
		}
	
	}

	public synchronized boolean isRunning() {
		return !finishedProcess;
	}
	
	public synchronized boolean isFinished() {
		return finishedProcess;
	}
	
	public ProcessStatus getStatus() {
		return status;
	}
}
