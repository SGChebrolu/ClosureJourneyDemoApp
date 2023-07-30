package com.closurejourney.hackathon.temporal.workflow;

import java.time.Duration;

import com.closurejourney.hackathon.temporal.activities.Activity;
import com.closurejourney.hackathon.temporal.activities.ActivityImpl;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

public class WorkflowImpl implements WorkFlow {

	private final RetryOptions retryoptions = RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1))
			.setMaximumInterval(Duration.ofSeconds(100)).setBackoffCoefficient(2).setMaximumAttempts(50000).build();
	private final ActivityOptions options = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(30))
			.setRetryOptions(retryoptions).build();

	private final Activity activity = Workflow.newActivityStub(Activity.class, options);

	public boolean isOrderConfirmed = false;

	public boolean isOrderPickedUp = false;

	public boolean isOrderDelivered = false;

	@Override
	public void startApprovalWorkflow() {
		
		activity.placeSellOrder();
		
		//System.out.println("***** Waiting for Restaurant to confirm your order");
		Workflow.await(() -> isOrderConfirmed);
		
		//System.out.println("***** Please wait till we assign a delivery executive");
		Workflow.await(() -> isOrderPickedUp);
		
		Workflow.await(() -> isOrderDelivered);

		activity.releaseFunds();
	}

	@Override
	public void signalCheckPTCE() {
		activity.checkPTCE();
		this.isOrderConfirmed = true;
	}

	@Override
	public void signalQFSellOrder() {
		activity.placeQFSellOrder();
		this.isOrderPickedUp = true;
	}

	@Override
	public void signalUnitradeSellOrder() {
		activity.placeUnitradeSellOrder();
		this.isOrderDelivered = true;
	}

}
