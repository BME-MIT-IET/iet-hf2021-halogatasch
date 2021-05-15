Feature: BDDTests - Nagy Gellért

  Scenario: Character in water with diving suit
    Given Character is in water
    Given Character is wearing diving suit
    When An entire turn passes
    Then The character should still be alive

  Scenario: Bear encounters a tent
    Given A tent is on an icecell
    Given The bear is next to it
    When The bear moves to the icecell with the tent
    Then The tent should be demolished

  Scenario: Explorer uses his ability
    Given An explorer stands next to an icecell with unknown capacity
    When The explorer uses his special ability
    Then The capacity of the neighbouring cell should be known

  Scenario: Eskimo uses his ability
    Given An eskimo stands on an icecell with no igloo
    When The eskimo uses his special ability
    Then An igloo should be built on the icecell