package com.closurejourney.hackathon.temporal.workflow;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkFlow {

	public static final String QUEUE_NAME = "ClosureJourney_Order";

	@WorkflowMethod
	void startApprovalWorkflow();

	@SignalMethod
	void signalCheckPTCE();

	@SignalMethod
	void signalQFSellOrder();

	@SignalMethod
	void signalUnitradeSellOrder();
}
