#Author: ushanandhan
Feature: FizzBuzz game play

  Scenario: Play FizzBuzz to get Fizz
    Given Create a FizzBuzz game play
    When I play with number 3
    Then The result is "Fizz"
