package logic.items;

import logic.IceField;
import logic.Way;
import logic.WinChecker;
import logic.characters.Character;
import logic.characters.Eskimo;
import logic.characters.Explorer;
import logic.icecells.IceCell;
import logic.icecells.StableIceCell;
import logic.icecells.WaterCell;
import logic.items.Food;
import logic.items.FragileShovel;
import logic.items.PlayerActions;
import logic.items.Shovel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemsTest {
    private BackPack backpack;
    private StableIceCell stableIceCell;
    private Explorer explorer;
    private IceField iceField;
    private Items item;

    public <T> void init(T item, PlayerActions pa) {
        this.item = (Items) item;

        stableIceCell = mock(StableIceCell.class);
        backpack = mock(BackPack.class);
        iceField = mock(IceField.class);
        explorer = new Explorer(stableIceCell);

        //explorer.putItemtoBackPack(this.item, pa);
    }

    @Test
    public void break_fragile_shovel() {
        init(new FragileShovel(), PlayerActions.fragileshoveling);
        item.equip(explorer);

        when(stableIceCell.loseSnow(true)).thenReturn(true);


        item.use(explorer); item.use(explorer); item.use(explorer);

        assertEquals(null, explorer.getBackPack().getItem(PlayerActions.fragileshoveling));
    }

    @Test
    public void eat_one_food(){
        init(new Food(), PlayerActions.eating);
        item.equip(explorer);

        explorer.loseOneHeat();
        explorer.loseOneHeat();
        item.use(explorer);

        assertEquals(3, explorer.getBodyHeat());
    }

    @Test
    public void set_up_tent(){
        init(new Tent(), PlayerActions.setUpTent);
        item.equip(explorer);

        //A static getMaxPlayert oldottam meg így
        ArrayList<Character> ch = new ArrayList<>();
        ch.add(explorer); ch.add(explorer); ch.add(explorer);
        StableIceCell sic = new StableIceCell(new IceField(ch), null, null);

        explorer.setOwnCell(sic);

        item.use(explorer);

        assertNotEquals(0, sic.getTentTurnsLeft());
    }

    @Test
    public void dig_to_zero_with_shovel(){
        init(new Shovel(), PlayerActions.shoveling);
        item.equip(explorer);

        StableIceCell sic = new StableIceCell(iceField, null, null);
        explorer.setOwnCell(sic);

        item.use(explorer); item.use(explorer); item.use(explorer); //6 ásásra muszáj eltűnnie a hónak, max 5 lehet

        assertEquals(0, sic.getSnow());
    }

    @Test
    public void collect_all_essentialitem(){
        WinChecker wc = new WinChecker();
        init(new EssentialItem(1, wc), PlayerActions.assemblingEssentials);
        item.equip(explorer);
        EssentialItem item2 = new EssentialItem(2, wc);
        EssentialItem item3 = new EssentialItem(3, wc);

        explorer.putItemtoBackPack(item2, PlayerActions.assemblingEssentials);
        explorer.putItemtoBackPack(item3, PlayerActions.assemblingEssentials);

        item.use(explorer);

        assertEquals(true, wc.isAssembled());
        assertEquals(3, item3.getID());
    }
    @Test
    public void wear_divingsuit(){
        init(new Divingsuit(), PlayerActions.wearingSuit);
        item.equip(explorer);

        item.use(explorer);

        assertEquals(true, explorer.getDivingSuit());
    }

    @Test
    public void save_with_rope(){
        init(new Rope(), PlayerActions.savingWithRope);
        item.equip(explorer);

        WaterCell neighbourWater = new WaterCell(iceField);
        Eskimo eskimo = new Eskimo(neighbourWater);
        neighbourWater.addCharacter(eskimo);
        neighbourWater.addNeighbour(Way.down, stableIceCell);

        when(stableIceCell.getNeighbour(any(Way.class))).thenReturn(neighbourWater);
        when(iceField.getChosenToSave()).thenReturn(eskimo);

        item.use(explorer);

        //NEM JÓ
        assertEquals(0, neighbourWater.getPlayersFromCell().size());
    }


}
