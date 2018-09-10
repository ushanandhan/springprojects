#Author: ushanandhan
Feature: FizzBuzz game play

  Scenario: Play FizzBuzz to get Fizz
    Given Create a FizzBuzz game play
    When I Play with number 3
    Then The result is "Fizz"

  Scenario: Play FizzBuzz to get Buzz
    Given Create a FizzBuzz game play
    When I Play with number 5
    Then The result is "Buzz"

  Scenario: Play FizzBuzz to get FizzBuzz
    Given Create a FizzBuzz game play
    When I Play with number 15
    Then The result is "FizzBuzz"

  Scenario: Play FizzBuzz to get number
    Given Create a FizzBuzz game play
    When I Play with number 1
    Then The result is "1"

  Scenario: Play FizzBuzz to get negative number
    Given Create a FizzBuzz game play
    When I Play with number -1
    Then The result is "Number should be greater than 0"
