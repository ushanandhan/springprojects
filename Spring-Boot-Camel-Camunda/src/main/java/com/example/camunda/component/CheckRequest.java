package com.example.camunda.component;

import org.springframework.stereotype.Component;

@Component("checkRequest")
public class CheckRequest {

	public void loanRequest() {
		System.out.println("Loan Request is processed......");
	}
}
