package com.example;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class FizzBuzzStepsDefs {

  FizzBuzz fizzBuzz;
	String fizzBuzzString;
	
	@Given("^Create a FizzBuzz game play$")
	public void create_a_FizzBuzz_game_play() throws Throwable {
	    fizzBuzz = new FizzBuzz();
	}

	@When("^I Play with number (\\d+)$")
	public void i_Play_with_number(int number) throws Throwable {
	    fizzBuzzString = fizzBuzz.play(number);
	}

	@Then("^The result is \"([^\"]*)\"$")
	public void the_result_is(String resultString) throws Throwable {
	    Assertions.assertEquals(resultString, fizzBuzzString);
	}
  
}
