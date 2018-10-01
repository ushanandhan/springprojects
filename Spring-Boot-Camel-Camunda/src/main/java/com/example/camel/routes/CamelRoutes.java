package com.example.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:firstRoute")
			.process("camelProcessor")
			.log("Camel body: ${body}")
			.to("camunda-bpm:start?processDefinitionKey=loanApproval");
		
		from("direct:applyForJobRoute")
			.process("interviewProcessor")
			.log("Camel body:${body}")
			.to("camunda-bpm:start?processDefinitionKey=applyForJobProcess");
	}

}
