package com.closurejourney.hackathon.temporal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.closurejourney.hackathon.temporal.activities.ActivityImpl;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;

@Component
@Configuration
public class TemporalConfig {

	/*
	TemporalServer booted up using Docker in localhost
	 */
	private String temporalServiceAddress = "127.0.0.1:7233";

	private String temporalNamespace = "default";

	@Bean
	public WorkflowClient workflowClient() {
		WorkflowServiceStubsOptions workflowServiceStubsOptions = WorkflowServiceStubsOptions.newBuilder()
				.setTarget(temporalServiceAddress)
				.build();
		return WorkflowClient.newInstance(WorkflowServiceStubs.newServiceStubs(workflowServiceStubsOptions));
	}

	@Bean
	public WorkerFactory workerFactory(WorkflowClient workflowClient) {
		return WorkerFactory.newInstance(workflowClient);
	}

	@Bean
	public ActivityImpl SignUpActivity() {
		return new ActivityImpl();
	}

}
