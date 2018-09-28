package com.example;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

public class FizzBuzzTest {

	private FizzBuzz fizzBuzz;
	
//	@BeforeEach
//	public void beforeEach() {
//		fizzBuzz = new FizzBuzz();
//	}
	
	@DisplayName("Play FizzBuzz with number = 1")
	@Test
	public void testNumber() {
		fizzBuzz = new FizzBuzz();
		String fizzBuzzString = fizzBuzz.play(1);
		Assertions.assertEquals(fizzBuzzString, "1");
	}
	
	@DisplayName("Play FizzBuzz with number = 3")
	@Test
	public void testFizz() {
		fizzBuzz = new FizzBuzz();
		String fizzBuzzString = fizzBuzz.play(3);
		Assertions.assertEquals(fizzBuzzString, "Fizz");
	}
	
	@DisplayName("Play FizzBuzz with number = 5")
	@Test
	public void testBuzz() {
		fizzBuzz = new FizzBuzz();
		String fizzBuzzString = new FizzBuzz().play(5);
		Assertions.assertEquals(fizzBuzzString, "Buzz");
	}
	
	@DisplayName("Play FizzBuzz with number = 15")
	@Test
	public void testFizzBuzz() {
		fizzBuzz = new FizzBuzz();
		String fizzBuzzString = new FizzBuzz().play(15);
		Assertions.assertEquals(fizzBuzzString, "FizzBuzz");
	}
	
	
}
