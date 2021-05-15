Feature: BDD Tests - Sergyán Gergő

  Scenario: wrong action is not done
    Given Eskimo stands on snowy icecell
    When Eskimo try to mine item
    Then Item remains in icecell
  Scenario: players dont start in water
    Given List created with 3 eskimo
    When IceField created
    Then Cell capacity is not 0
  Scenario: players dont start with bear
    Given List created with 3 eskimo_
    When IceField created_
    Then Cell has no bear
  Scenario: snow dont fall on water
    Given Cell is water
    When Snow falling to water
    Then Water has no snow on it