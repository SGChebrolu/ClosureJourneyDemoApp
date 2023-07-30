package com.closurejourney.hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.closurejourney.hackathon.service.OrderService;

@RestController
public class MainController {

	@Autowired
	OrderService orderService;

	@PostMapping("/startWorkflow")
	public String createOrder(@RequestParam("id") String id) {
		orderService.placeOrder(id);
		return "ClosureJourney Started for : "+id;
	}

	@PostMapping("/ptcePassed")
	public String orderAccepted(@RequestParam("id") String id) {
		orderService.makeOrderAccepted(id);
		return "OrderFlow moved away from PTCE";
	}

	@PostMapping("/qfSellAccepted")
	public String orderPickedUp(@RequestParam("id") String id) {
		orderService.makeOrderPickedUp(id);
		return "OrderFlow moved away from QF";
	}

	@PostMapping("/uniTradeSellAccepted")
	public String orderDelivered(@RequestParam("id") String id) {
		orderService.makeOrderDelivered(id);
		return "OrderFlow moved away from Unitrade";
	}
}
