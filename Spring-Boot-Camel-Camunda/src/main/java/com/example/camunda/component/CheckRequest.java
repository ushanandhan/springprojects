package com.example.camunda.component;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Order;

@Component("checkRequest")
public class CheckRequest {

	@Autowired
	RuntimeService runtimeService;
	
	public void checkLoanRequest(Execution execution) {
		System.out.println("Checking loan Request..........."+execution);
		Order order = (Order) runtimeService.getVariable(execution.getProcessInstanceId(), "order");
		System.out.println(order.getOrderId());
	}
}
