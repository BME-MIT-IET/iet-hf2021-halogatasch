Feature: BDD Tests - Kerekes Áron

  Scenario: Eskimo builds igloo on tent
    Given Eskimo stands on icecell with tent
    When Eskimo builds igloo
    Then Tent doesn't gets replaced with igloo

  Scenario: Eskimo builds tent on igloo
    Given Eskimo stands on icecell with igloo
    Given Eskimo has tent in backpack
    When Eskimo builds tent
    Then Igloo doesn't gets replaced with tent

  Scenario: Snowstorm breaks tent
    Given Icecell with a tent
    When Snowstorm happens
    Then Tent gets removed from icecell

  Scenario: Snowstorm breaks igloo
    Given Icecell with an igloo
    When Snowstorm happens
    Then Igloo gets removed from icecell

  Scenario: Eskimo dies from loosing bodyheat
    Given Eskimo stands on icecell with one bodyheat and no building
    When Snowstorm happens
    Then Eskimo dies