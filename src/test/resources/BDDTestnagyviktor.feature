Feature: BDD Tests - Nagy Viktor

  Scenario: Break fragileshovel after 3 uses
    Given Eskimo stands on icecell
    When Eskimo with a fragileshovel
    Then Eskimo uses it 3 times then break it

  Scenario: Save person with Rope
    Given Eskimo with Rope in his backpack
    When Eskimo in stable, Explorer in watercell
    Then Eskimo saves Explorer with Rope

  Scenario: Assemble essential items
    Given Eskimo ready to win the game
    When Eskimo has 3 different essential items
    Then Eskimo assemble it to win the game

  Scenario: Break unstable cell
    Given An unstable cell
    When More player stands on it than the capacity
    Then Unstable cell will be a water cell


