package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.IceField;
import logic.characters.Eskimo;
import logic.icecells.StableIceCell;
import logic.items.PlayerActions;
import logic.items.Tent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class StepsKerekesaron {
    Eskimo eskimo;
    IceField iceField = mock(IceField.class);
    StableIceCell stableIceCell = new StableIceCell(iceField, null, null);

    //region Eskimo builds igloo on tent
    @Given("Eskimo stands on icecell with tent")
    public void EskimoBuildsIglooOnTentGiven() {
        eskimo = new Eskimo(stableIceCell);
        IceField.setMaxPlayer(6);
        stableIceCell.setUpTent();
    }

    @When("Eskimo builds igloo")
    public void EskimoBuildsIglooOnTentWhen() {
        eskimo.ability();
    }

    @Then("Tent doesn't gets replaced with igloo")
    public void EskimoBuildsIglooOnTentThen() {
        assertFalse(stableIceCell.itHasIgloo());
        assertEquals(6,stableIceCell.getTentTurnsLeft());
    }
    //endregion

    //region Eskimo builds tent on igloo
    @Given("Eskimo stands on icecell with igloo")
    public void EskimoBuildsTentOnIglooGiven1() {
        eskimo = new Eskimo(stableIceCell);
        IceField.setMaxPlayer(6);
        stableIceCell.setIgloo(true);
    }

    @Given("Eskimo has tent in backpack")
    public void EskimoBuildsTentOnIglooGiven2() {
        eskimo.putItemtoBackPack(new Tent(), PlayerActions.setUpTent);
    }

    @When("Eskimo builds tent")
    public void EskimoBuildsTentOnIglooWhen() {
        eskimo.useItem(PlayerActions.setUpTent);
    }

    @Then("Igloo doesn't gets replaced with tent")
    public void EskimoBuildsTentOnIglooThen(){
        assertTrue(stableIceCell.itHasIgloo());
        assertEquals(0,stableIceCell.getTentTurnsLeft());
    }
    //endregion

    //The next three scenarios has the same when:
    @When("Snowstorm happens")
    public void SnowstormHappens(){
        stableIceCell.snowing();
    }

    //region Snowstorm breaks tent
    @Given("Icecell with a tent")
    public void SnowstormBreaksTentGiven(){
        stableIceCell.setUpTent();
    }
    @Then("Tent gets removed from icecell")
    public void SnowstormBreaksTentThen(){
        assertEquals(0,stableIceCell.getTentTurnsLeft());
    }
    //endregion

    //region Snowstorm breaks igloo
    @Given("Icecell with an igloo")
    public void SnowstormBreaksIglooGiven(){
        stableIceCell.setIgloo(true);
    }
    @Then("Igloo gets removed from icecell")
    public void SnowstormBreaksIglooThen(){
        assertFalse(stableIceCell.itHasIgloo());
    }
    //endregion

    //region Eskimo dies from loosing bodyheat
    @Given("Eskimo stands on icecell with one bodyheat and no building")
    public void EskimoDiesFromLoosingBodyheatGiven(){
        eskimo = new Eskimo(stableIceCell);
        stableIceCell.addCharacter(eskimo);
        int currentHeat=eskimo.getBodyHeat();
        for (int i=0; i<currentHeat-1; i++){
            eskimo.loseOneHeat();
        }
    }
    @Then("Eskimo dies")
    public void EskimoDiesFromLoosingBodyheatThen(){
        assertEquals(0,eskimo.getBodyHeat());
    }
    //endregion

}
