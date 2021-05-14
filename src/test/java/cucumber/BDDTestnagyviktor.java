package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.IceField;
import logic.Way;
import logic.WinChecker;
import logic.characters.Eskimo;
import logic.characters.Explorer;
import logic.icecells.StableIceCell;
import logic.icecells.UnstableIceCell;
import logic.icecells.WaterCell;
import logic.items.EssentialItem;
import logic.items.FragileShovel;
import logic.items.PlayerActions;
import logic.items.Rope;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BDDTestnagyviktor {
    Eskimo eskimo;
    Explorer explorer;

    IceField iceField = mock(IceField.class);
    StableIceCell sic = new StableIceCell(iceField, null, null);;
    UnstableIceCell uic;
    WaterCell wc = new WaterCell(iceField);

    WinChecker winCh = new WinChecker();

    @Given("Eskimo with Rope in his backpack")
    public void saveWithRopeGiven() throws Throwable{
        eskimo = new Eskimo(sic);
        eskimo.putItemtoBackPack(new Rope(), PlayerActions.savingWithRope);
    }
    @When("Eskimo in stable, Explorer in watercell")
    public void saveWithRopeWhen() throws Throwable{
        explorer = new Explorer(wc);
        sic.addNeighbour(Way.up, wc);
        wc.addNeighbour(Way.down, sic);
        wc.addCharacter(explorer);
        sic.addCharacter(eskimo);
    }
    @Then("Eskimo saves Explorer with Rope")
    public void saveWithRopeThen() throws Throwable{
        when(iceField.getChosenToSave()).thenReturn(explorer);
        eskimo.setFacingWay(Way.up);
        eskimo.useItem(PlayerActions.savingWithRope);

        assertEquals(2, sic.getPlayersFromCell().size());
    }


    @Given("Eskimo ready to win the game")
    public void assembleEssentialItemsGiven() throws Throwable{
        eskimo = new Eskimo(sic);
    }
    @When("Eskimo has 3 different essential items")
    public void assembleEssentialItemsWhen() throws Throwable{
        eskimo.putItemtoBackPack(new EssentialItem(1, winCh), PlayerActions.assemblingEssentials);
        eskimo.putItemtoBackPack(new EssentialItem(2, winCh), PlayerActions.assemblingEssentials);
        eskimo.putItemtoBackPack(new EssentialItem(3, winCh), PlayerActions.assemblingEssentials);
    }
    @Then("Eskimo assemble it to win the game")
    public void assembleEssentialItemsThen() throws Throwable{
        eskimo.useEssentials();
        assertEquals(true, winCh.isAssembled());
    }


    @Given("Eskimo with a fragileshovel")
    public void breakFragileShovelGiven() throws Throwable{
        eskimo = new Eskimo(sic);
        eskimo.putItemtoBackPack(new FragileShovel(), PlayerActions.fragileshoveling);
    }
    @Then("Eskimo uses it 3 times then break it")
    public void breakFragileShovelThen() throws Throwable{
        for(int i = 0; i < 3; i++)
            eskimo.useItem(PlayerActions.fragileshoveling);
        assertEquals(null, eskimo.getBackPack().getItem(PlayerActions.fragileshoveling));
    }


    @Given("An unstable cell")
    public void breakUnstableCellGiven() throws Throwable{
        uic = new UnstableIceCell(1, iceField);
    }
    @When("More player stands on it than the capacity")
    public void breakUnstableCellWhen() throws Throwable{
        eskimo = new Eskimo(uic);
        explorer = new Explorer(uic);

        uic.addCharacter(eskimo);
        uic.addCharacter(explorer);
    }
    @Then("Unstable cell will be a water cell")
    public void breakUnstableCellThen() throws Throwable{
        assertEquals(0, uic.getPlayersFromCell().size());
    }
}
