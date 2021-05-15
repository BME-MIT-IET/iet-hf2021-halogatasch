Feature: BDD Tests - Markovics Gergely

  Scenario: Game over on Character's next turn if he is still in water without diving suit
    Given Eskimo Falls in Water without diving suit
    When Full turn passes
    Then Game over

  Scenario: Eat Apple adds health
    Given Eskimo with apple
    When Eskimo eats apple
    Then Eskimo's health goes up by one

  Scenario: Explorer uses ability on non-existent cell
    Given Explorer next to non-existent cell
    When Explorer uses ability that way
    Then Ability does not consume work, does nothing

  Scenario: Blocked Work does not consume energy
    Given Eskimo next to non-existent cell
    When Eskimo tries to move that way
    Then Move does not consume work, does nothing


