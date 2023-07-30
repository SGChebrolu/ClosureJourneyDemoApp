package com.closurejourney.hackathon;

import com.closurejourney.hackathon.temporal.activities.Activity;
import com.closurejourney.hackathon.temporal.workflow.WorkFlow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.closurejourney.hackathon.temporal.workflow.WorkflowImpl;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

@SpringBootApplication
public class ClosureJourneyApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext appContext = SpringApplication.run(ClosureJourneyApplication.class, args);
		WorkerFactory factory = appContext.getBean(WorkerFactory.class);
		Activity defaultActivity = appContext.getBean(Activity.class);
		Worker worker = factory.newWorker(WorkFlow.QUEUE_NAME);
		worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
		worker.registerActivitiesImplementations(defaultActivity);
		factory.start();
	}

}
