package com.closurejourney.hackathon.temporal.activities;

public class ActivityImpl implements Activity {

	@Override
	public void placeSellOrder() {
		System.out.println("***** Sell Order has been placed");
	}

	@Override
	public void checkPTCE() {
		System.out.println("***** PTCE check has been passed");
	}

	@Override
	public void placeQFSellOrder() {
		System.out.println("***** QF sell order placed");
	}

	@Override
	public void placeUnitradeSellOrder() {
		System.out.println("***** unitrade sell order placed");
	}

	@Override
	public void releaseFunds() {
		System.out.println("***** Funds released back to Customer");
	}

}
