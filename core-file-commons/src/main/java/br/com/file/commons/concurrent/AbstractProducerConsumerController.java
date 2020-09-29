package br.com.file.commons.concurrent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProducerConsumerController<T> extends TaskProcess<Boolean>{

	private List<TaskProcess> producer = new ArrayList<TaskProcess>();
	private List<TaskProcess> consumer = new ArrayList<TaskProcess>();
	
	public void addProducer(TaskProcess<T> producer) {
		this.producer.add(producer);
	}
	
	public void addConsumer(TaskProcess<T> consumer) {
		this.consumer.add(consumer);
	}
	
	@Override
	public Boolean process() {
		
		for (TaskProcess<T> prod : producer) {
			prod.processAssynchronous();
		}
		
		for (TaskProcess<T> cons : consumer) {
			cons.processAssynchronous();
		}
		
		return null;
	}
	
}
