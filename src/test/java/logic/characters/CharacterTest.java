package logic.characters;

import logic.Way;
import logic.icecells.IceCell;
import logic.icecells.StableIceCell;
import logic.items.Food;
import logic.items.FragileShovel;
import logic.items.PlayerActions;
import logic.items.Shovel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CharacterTest {
    private Eskimo eskimo;
    private Explorer explorer;
    private IceCell mockOwnCell;
    private IceCell mockNeighbourCell;

    @BeforeEach
    public void init() {
        mockOwnCell = mock(StableIceCell.class);
        mockNeighbourCell = mock(StableIceCell.class);
       /* mockOwnCell = new StableIceCell();
        mockNeighbourCell = new StableIceCell();*/
        this.eskimo = new Eskimo(mockOwnCell);
        this.explorer = new Explorer(mockOwnCell);
    }

    @Test
    public void Explorer_Successful_Ability() {
        int actions = explorer.getActionsLeft();
        when(mockOwnCell.getNeighbour(Way.up)).thenReturn(mockNeighbourCell);
        when(mockNeighbourCell.setCapacityKnown()).thenReturn(true);

        explorer.ability();

        assertEquals(actions - 1, explorer.getActionsLeft());
        verify(mockOwnCell, times(1)).getNeighbour(Way.up);
        verify(mockNeighbourCell, times(1)).setCapacityKnown();
    }

    @Test
    public void Explorer_Failed_Ability() {
        int actions = explorer.getActionsLeft();
        when(mockOwnCell.getNeighbour(Way.up)).thenReturn(mockNeighbourCell);
        when(mockNeighbourCell.setCapacityKnown()).thenReturn(false);

        explorer.ability();

        assertEquals(actions, explorer.getActionsLeft());
        verify(mockOwnCell, times(1)).getNeighbour(Way.up);
        verify(mockNeighbourCell, times(1)).setCapacityKnown();
    }

    @Test
    public void Explorer_Failed_Ability_Null() {
        int actions = explorer.getActionsLeft();
        when(mockOwnCell.getNeighbour(Way.up)).thenReturn(null);
        when(mockNeighbourCell.setCapacityKnown()).thenReturn(false);

        explorer.ability();

        assertEquals(actions, explorer.getActionsLeft());
        verify(mockOwnCell, times(1)).getNeighbour(Way.up);
        verify(mockNeighbourCell, times(0)).setCapacityKnown();
    }

    @Test
    public void Eskimo_Successful_Ability() {
        int actions = eskimo.getActionsLeft();
        when(mockOwnCell.setIgloo(true)).thenReturn(true);

        eskimo.ability();

        assertEquals(actions - 1, eskimo.getActionsLeft());
        verify(mockOwnCell, times(1)).setIgloo(true);
    }

    @Test
    public void Eskimo_Failed_Ability() {
        int actions = eskimo.getActionsLeft();
        when(mockOwnCell.setIgloo(true)).thenReturn(false);

        eskimo.ability();

        assertEquals(actions, eskimo.getActionsLeft());
        verify(mockOwnCell, times(1)).setIgloo(true);
    }

    @Test
    public void Character_Successful_Move() {
        int actions = eskimo.getActionsLeft();
        eskimo.setFacingWay(Way.down);
        when(mockOwnCell.getNeighbour(Way.down)).thenReturn(mockNeighbourCell);

        eskimo.move();

        assertEquals(actions - 1, eskimo.getActionsLeft());
        verify(mockOwnCell, times(1)).removeCharacter(eskimo);
        verify(mockNeighbourCell, times(1)).accept(eskimo);
    }

    @Test
    public void Character_Failed_Move() {
        int actions = eskimo.getActionsLeft();
        when(mockOwnCell.getNeighbour(Way.up)).thenReturn(null);

        eskimo.move();

        assertEquals(actions, eskimo.getActionsLeft());
        verify(mockOwnCell, times(0)).removeCharacter(eskimo);
        verify(mockNeighbourCell, times(0)).accept(eskimo);
    }

    @Test
    public void Character_Backpack_Eating_Failed() {
        int actions = eskimo.getActionsLeft();
        boolean success = eskimo.putItemtoBackPack(new Food(), PlayerActions.eating);
        int bodyHeat = eskimo.getBodyHeat();

        eskimo.useItem(PlayerActions.eating);

        assertEquals(actions, eskimo.getActionsLeft());
        assertEquals(true, success);
        assertEquals(bodyHeat, eskimo.getBodyHeat());
    }

    @Test
    public void Character_Backpack_Eating_Successful() {
        int actions = eskimo.getActionsLeft();
        boolean success = eskimo.putItemtoBackPack(new Food(), PlayerActions.eating);
        eskimo.loseOneHeat();
        int heatBeforeEating = eskimo.getBodyHeat();

        eskimo.useItem(PlayerActions.eating);

        assertEquals(actions - 1, eskimo.getActionsLeft());
        assertEquals(true, success);
        assertEquals(heatBeforeEating + 1, eskimo.getBodyHeat());
    }

    @Test
    public void Character_Eating_NoFood() {
        int actions = eskimo.getActionsLeft();
        eskimo.loseOneHeat();
        int heatBeforeEating = eskimo.getBodyHeat();

        eskimo.useItem(PlayerActions.eating);

        assertEquals(actions, eskimo.getActionsLeft());
        assertEquals(heatBeforeEating, eskimo.getBodyHeat());
    }

    @Test
    public void Character_Shovel_WithSnow() {
        int actions = eskimo.getActionsLeft();
        boolean putShovel = eskimo.putItemtoBackPack(new Shovel(), PlayerActions.shoveling);
        boolean putShovelAgain = eskimo.putItemtoBackPack(new Shovel(), PlayerActions.shoveling);
        when(mockOwnCell.loseSnow(anyBoolean())).thenReturn(true);

        eskimo.useItem(PlayerActions.shoveling);

        assertEquals(actions - 1, eskimo.getActionsLeft());
        assertEquals(true, putShovel);
        assertEquals(false, putShovelAgain);
    }

    @Test
    public void Character_Shovel_WithoutSnow() {
        int actions = eskimo.getActionsLeft();
        boolean putShovel = eskimo.putItemtoBackPack(new FragileShovel(), PlayerActions.fragileshoveling);
        when(mockOwnCell.loseSnow(anyBoolean())).thenReturn(false);

        eskimo.useItem(PlayerActions.fragileshoveling);

        assertEquals(actions, eskimo.getActionsLeft());
        assertEquals(true, putShovel);
    }

    @Test
    public void Character_Put_On_Diving_Suit() {
        boolean firstTry = eskimo.wearDivingSuit();
        boolean secondTry = eskimo.wearDivingSuit();

        assertEquals(true, eskimo.getDivingSuit());
        assertEquals(true, firstTry);
        assertEquals(false, secondTry);
    }
}