package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.IceField;
import logic.Way;
import logic.characters.Character;
import logic.characters.Eskimo;
import logic.characters.Explorer;
import logic.icecells.StableIceCell;
import logic.items.Food;
import logic.items.PlayerActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BDDTestmarkovicsgergely {
    Eskimo eskimo;
    Explorer explorer;
    
    StableIceCell mockStable = mock(StableIceCell.class);
    Eskimo mockEskimo = mock(Eskimo.class);
    Explorer mockExplorer = mock(Explorer.class);
    IceField iceField;

    @Given("Eskimo Falls in Water without diving suit")
    public void gameOverinWaterGiven() throws Throwable {
        ArrayList<Character> characters = new ArrayList<Character>(Arrays.asList(mockEskimo, mockExplorer,mockExplorer));
        iceField = new IceField(characters);
    }
    @When("Full turn passes")
    public void gameOverinWaterWhen() throws Throwable {
        when(mockEskimo.getTurnsInWater()).thenReturn(3);
        when(mockExplorer.getTurnsInWater()).thenReturn(0);
        iceField.nextPlayer();
    }
    @Then("Game over")
    public void gameOverinWaterThen() throws Throwable {
        assertTrue(iceField.gameLost);
    }

    @Given("Eskimo with apple")
    public void eatAppleGiven() throws Throwable {
        eskimo = new Eskimo(mockStable);
        eskimo.putItemtoBackPack(new Food(), PlayerActions.eating);
        eskimo.loseOneHeat();
        eskimo.loseOneHeat();
    }
    @When("Eskimo eats apple")
    public void eatAppleWhen() throws Throwable {
        eskimo.useItem(PlayerActions.eating);
    }
    @Then("Eskimo's health goes up by one")
    public void eatAppleThen() throws Throwable {
        assertEquals(4, eskimo.getBodyHeat());
        assertNull(eskimo.getBackPack().getItem(PlayerActions.eating));
    }

    @Given("Explorer next to non-existent cell")
    public void ExplorerAbilityOnNothingGiven() throws Throwable {
        explorer = new Explorer(mockStable);
    }

    @When("Explorer uses ability that way")
    public void ExplorerAbilityOnNothingWhen() throws Throwable {
        explorer.setFacingWay(Way.up);
        when(mockStable.getNeighbour(Way.up)).thenReturn(null);
        
        explorer.ability();
    }
    @Then("Ability does not consume work, does nothing")
    public void ExplorerAbilityOnNothingThen() throws Throwable {
        int remainingWork = explorer.getActionsLeft();
        explorer.resetActionsLeft();
        int maxWork = explorer.getActionsLeft();
        assertEquals(maxWork, remainingWork);
    }

    @Given("Eskimo next to non-existent cell")
    public void MoveToNothingGiven() throws Throwable {
        explorer = new Explorer(mockStable);
    }
    @When("Eskimo tries to move that way")
    public void MoveToNothingWhen() throws Throwable {
        when(mockStable.getNeighbour(Way.up)).thenReturn(null);
        explorer.setFacingWay(Way.up);
        explorer.move();
    }
    @Then("Move does not consume work, does nothing")
    public void MoveToNothingThen() throws Throwable {
        int remainingWork = explorer.getActionsLeft();
        explorer.resetActionsLeft();
        int maxWork = explorer.getActionsLeft();
        assertEquals(maxWork, remainingWork);
    }
}
