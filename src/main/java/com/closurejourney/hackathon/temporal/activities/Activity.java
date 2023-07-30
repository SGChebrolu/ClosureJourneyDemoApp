package com.closurejourney.hackathon.temporal.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface Activity {

	@ActivityMethod
	void placeSellOrder();

	@ActivityMethod
	void checkPTCE();

	@ActivityMethod
	void placeQFSellOrder();

	@ActivityMethod
	void placeUnitradeSellOrder();

	@ActivityMethod
	void releaseFunds();

}
