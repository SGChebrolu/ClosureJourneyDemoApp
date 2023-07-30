package com.closurejourney.hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.closurejourney.hackathon.temporal.workflow.WorkFlow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;

@Service
public class OrderService {

	/*@Autowired
	WorkflowServiceStubs workflowServiceStubs;*/

	@Autowired
	WorkflowClient workflowClient;

	public void placeOrder(String workflowId) {
		WorkFlow workflow = createWorkFlowConnection(workflowId);
		WorkflowClient.start(workflow::startApprovalWorkflow);
	}

	public void makeOrderAccepted(String workflowId) {
		WorkFlow workflow = workflowClient.newWorkflowStub(WorkFlow.class, "Order_" + workflowId);
		workflow.signalCheckPTCE();
	}

	public void makeOrderPickedUp(String workflowId) {
		WorkFlow workflow = workflowClient.newWorkflowStub(WorkFlow.class, "Order_" + workflowId);
		workflow.signalQFSellOrder();
	}

	public void makeOrderDelivered(String workflowId) {
		WorkFlow workflow = workflowClient.newWorkflowStub(WorkFlow.class, "Order_" + workflowId);
		workflow.signalUnitradeSellOrder();
	}

	public WorkFlow createWorkFlowConnection(String id) {
		WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(WorkFlow.QUEUE_NAME)
				.setWorkflowId("Order_" + id).build();
		return workflowClient.newWorkflowStub(WorkFlow.class, options);
	}

}
