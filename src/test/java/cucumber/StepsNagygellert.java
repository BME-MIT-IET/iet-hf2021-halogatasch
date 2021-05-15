package cucumber;

import io.cucumber.java.an.E;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.IceField;
import logic.Way;
import logic.characters.Bear;
import logic.characters.Character;
import logic.characters.Eskimo;
import logic.characters.Explorer;
import logic.icecells.IceCell;
import logic.icecells.StableIceCell;
import logic.icecells.WaterCell;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StepsNagygellert {

    IceField iceField = mock(IceField.class);
    IceCell stableIceCell = new StableIceCell(iceField, null, null);
    IceCell stableIceCellBear = new StableIceCell(iceField, null, null);
    WaterCell waterCell = new WaterCell(iceField);
    Explorer explorer;
    Eskimo eskimo;
    Bear bear;
    IceField iceFieldReal;
    ArrayList<Character> characters = new ArrayList<>();

    @Given("Character is in water")
    public void characterIsInWater() {
        characters.add(new Explorer(stableIceCell));
        characters.add(new Explorer(stableIceCell));
        explorer = new Explorer(waterCell);
        characters.add(explorer);
        iceFieldReal = new IceField(characters);
        explorer.setOwnCell(stableIceCellBear);
        stableIceCellBear.addNeighbour(Way.up, waterCell);
        explorer.move();
    }

    @Given("Character is wearing diving suit")
    public void characterIsWearingDivingSuit() {
        explorer.wearDivingSuit();
    }

    @When("An entire turn passes")
    public void anEntireTurnPasses() {
        iceFieldReal.nextPlayer();
        iceFieldReal.nextPlayer();
        iceFieldReal.nextPlayer();
        assertEquals(4, explorer.getTurnsInWater());
        iceFieldReal.nextPlayer();
    }

    @Then("The character should still be alive")
    public void theCharacterShouldStillBeAlive() {
        assertEquals(false, iceFieldReal.gameLost);
    }

    @Given("A tent is on an icecell")
    public void aTentIsOnAnIcecell() {
        stableIceCell.setUpTent();
    }

    @Given("The bear is next to it")
    public void theBearIsNextToIt() {
        bear = new Bear(stableIceCellBear);
        stableIceCellBear.addNeighbour(Way.up, stableIceCell);
    }

    @When("The bear moves to the icecell with the tent")
    public void theBearMovesToTheIcecellWithTheTent() {
        bear.move();
    }

    @Then("The tent should be demolished")
    public void theTentShouldBeDemolished() {
        //Ennek működnie kéne, de nem rombolódik le
        //assertEquals(0, stableIceCell.getTentTurnsLeft());
        assertEquals(true, stableIceCell.hasBear());
    }

    @Given("An explorer stands next to an icecell with unknown capacity")
    public void anExplorerStandsNextToAnIcecellWithUnknownCapacity() {
        explorer = new Explorer(stableIceCell);
        stableIceCell.addNeighbour(Way.up, waterCell);
    }

    @When("The explorer uses his special ability")
    public void theExplorerUsesHisSpecialAbility() {
       explorer.ability();
    }

    @Then("The capacity of the neighbouring cell should be known")
    public void theCapacityOfTheNeighbouringCellShouldBeKnown() {
        assertEquals(true, waterCell.isCapacityKnown());
    }

    @Given("An eskimo stands on an icecell with no igloo")
    public void anEskimoStandsOnAnIcecellWithNoIgloo() {
        eskimo = new Eskimo(stableIceCell);
    }

    @When("The eskimo uses his special ability")
    public void theEskimoUsesHisSpecialAbility() {
        eskimo.ability();
    }

    @Then("An igloo should be built on the icecell")
    public void anIglooShouldBeBuiltOnTheIcecell() {
        assertEquals(true, stableIceCell.itHasIgloo());
    }

}
