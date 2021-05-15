package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.IceField;
import logic.characters.Character;
import logic.characters.Eskimo;
import logic.icecells.IceCell;
import logic.icecells.StableIceCell;
import logic.icecells.WaterCell;
import logic.items.PlayerActions;
import logic.items.Rope;
import org.opentest4j.AssertionFailedError;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class BDDTestsergyangergo {
    Eskimo eskimo;
    IceField iceField = mock(IceField.class);
    Rope rope = new Rope();
    StableIceCell stableIceCell = new StableIceCell(iceField, rope, null);
    WaterCell waterCell;
    Eskimo e1;
    Eskimo e2;
    Eskimo e3;
    ArrayList<Character> chList;
    IceField field;

    //region wrong action is not done
    @Given("Eskimo stands on snowy icecell")
    public void EskimoDoesWrongActionGiven() {
        eskimo = new Eskimo(stableIceCell);
        IceField.setMaxPlayer(6);
        stableIceCell.gainOneSnow();
    }

    @When("Eskimo try to mine item")
    public void EskimoDoesWrongActionWhen() {
        try {
            eskimo.mine();
        }
        catch (AssertionFailedError e){}
    }

    @Then("Item remains in icecell")
    public void EskimoBuildsIglooOnTentThen() {
        assertFalse(eskimo.getBackPack().hasItem(PlayerActions.savingWithRope));

    }
    //endregion


    //region players dont start in water
    @Given("List created with 3 eskimo")
    public void IceFieldGiven() {
        e1 = new Eskimo(stableIceCell);
        e2 = new Eskimo(stableIceCell);
        e3 = new Eskimo(stableIceCell);
        chList = new ArrayList<Character>();
        chList.add(e1);
        chList.add(e2);
        chList.add(e3);
    }

    @When("IceField created")
    public void IceFieldWhen() {
        field = new IceField(chList);
    }
    @Then("Cell capacity is not 0")
    public void IceFieldThen() {
        assertNotEquals(0, e1.getOwnCell().getCapacity());
    }
    //endregion


    //region players dont start with bear
    @Given("List created with 3 eskimo_")
    public void IceField2Given() {
        e1 = new Eskimo(stableIceCell);
        e2 = new Eskimo(stableIceCell);
        e3 = new Eskimo(stableIceCell);
        chList = new ArrayList<Character>();
        chList.add(e1);
        chList.add(e2);
        chList.add(e3);
    }

    @When("IceField created_")
    public void IceField2When() {
        field = new IceField(chList);
    }
    @Then("Cell has no bear")
    public void IceField2Then() {
        assertFalse(e1.getOwnCell().hasBear());
    }
    //endregion

    //region snow dont fall on water
    @Given("Cell is water")
    public void SnowOnWaterGiven() {
        waterCell = new WaterCell(iceField);
        waterCell.setBroken();
    }
    @When("Snow falling to water")
    public void SnowOnWaterWhen() {
        waterCell.snowing();
    }
    @Then("Water has no snow on it")
    public void SnowOnWaterThen() {
        assertEquals(0, waterCell.getSnow());
    }
    //endregion
}
