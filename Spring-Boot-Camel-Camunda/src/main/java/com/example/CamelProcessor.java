package com.example;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.example.model.Order;

@Component("camelProcessor")
public class CamelProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Order order = new Order();
		order.setOrderId("ORD1234");
		order.setName("Internet Connection");
		order.setPrice(1400);
		Map<String, Object> processVariables = new HashMap<>();
		processVariables.put("order", order);
		exchange.getIn().setBody(processVariables);
	}

}
