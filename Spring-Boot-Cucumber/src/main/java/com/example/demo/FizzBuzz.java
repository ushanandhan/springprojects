package com.example.demo;

public class FizzBuzz {

	public String play(int number) {
		if(number<=0) throw new ArithmeticException("Number should be greater than 0");
		if(number%3==0 && number%5==0) return "FizzBuzz";
		if(number%3==0) return "Fizz";
		if(number%5==0) return "Buzz";
		return String.valueOf(number);
	}
}
