package logic.icecells;

import logic.IceField;
import logic.Way;
import logic.characters.Bear;
import logic.characters.Eskimo;
import logic.items.PlayerActions;
import logic.items.Shovel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IceCellTest {
    private Shovel item;
    private IceField iceField;
    private StableIceCell stableIceCell;
    private UnstableIceCell unstableIceCell;
    private WaterCell waterCell;
    private Bear bear;
    private Eskimo eskimo;

    private int unstableSize = 2;

    @BeforeEach
    public void init() {
        item = mock(Shovel.class);
        iceField = mock(IceField.class);
        bear = mock(Bear.class);
        eskimo = mock(Eskimo.class);

        stableIceCell = new StableIceCell(iceField, item, PlayerActions.shoveling);
        unstableIceCell = new UnstableIceCell(unstableSize, iceField);
        waterCell = new WaterCell(iceField);
    }

    @Test
    public void StableIceCell_AcceptCharacter_Success(){
        //Arrange

        // Act
        stableIceCell.accept(eskimo);

        // Assert
        assertEquals(1, stableIceCell.standingPlayers.size());
        verify(eskimo, times(1)).setOwnCell(stableIceCell);
        verifyNoInteractions(iceField);
    }

    @Test
    public void StableIceCell_AcceptBearWithoutCharacters_Success(){
        //Arrange

        // Act
        stableIceCell.acceptBear(bear);

        // Assert
        assertTrue(stableIceCell.hasBear());
        verify(bear, times(1)).setOwnCell(stableIceCell);
        verifyNoInteractions(iceField);
    }

    @Test
    public void StableIceCell_AcceptCharacterWithBear_GameOver(){
        //Arrange
        stableIceCell.acceptBear(bear);
         
        // Act
        stableIceCell.accept(eskimo);

        // Assert
        assertEquals(1, stableIceCell.standingPlayers.size());
        verify(eskimo, times(1)).setOwnCell(stableIceCell);
        verify(iceField, times(1)).gameLost("Megtámadott a medve!");
    }

    @Test
    public void StableIceCell_AcceptBearWithCharacter_GameOver(){
        //Arrange
        stableIceCell.accept(eskimo);
         
        // Act
        stableIceCell.acceptBear(bear);

        // Assert
        assertEquals(1, stableIceCell.standingPlayers.size());
        verify(bear, times(1)).setOwnCell(stableIceCell);
        verify(iceField, times(1)).gameLost("Megtámadott a medve!");
    }

    @Test
    public void StableIceCell_Mine_Success(){
        //Arrange
        stableIceCell.snow = 0;
        when(item.equip(eskimo)).thenReturn(true);

        // Act
        stableIceCell.mine(eskimo);

        // Assert
        assertFalse(stableIceCell.hasItem(PlayerActions.shoveling));
        verify(eskimo, times(1)).loseOneAction();
        verify(item, times(1)).equip(eskimo);
    }

    @Test
    public void StableIceCell_SnowingWithoutCover_LoseHeat(){
        //Arrange
        stableIceCell.snow = 0;
        stableIceCell.accept(eskimo);
        
        // Act
        stableIceCell.snowing();

        // Assert
        assertEquals(1, stableIceCell.snow);
        verify(eskimo, times(1)).loseOneHeat();
    }

    @Test
    public void StableIceCell_SetIgloo_Success(){
        //Arrange
        
        // Act
        boolean result = stableIceCell.setIgloo(true);

        // Assert
        assertTrue(result);
        assertTrue(stableIceCell.itHasIgloo());
    }

    @Test
    public void StableIceCell_SetIglooTwoTimes_Fail(){
        //Arrange
        
        // Act
        boolean result_first = stableIceCell.setIgloo(true);
        boolean result_second = stableIceCell.setIgloo(true);

        // Assert
        assertTrue(result_first);
        assertFalse(result_second);
        assertTrue(stableIceCell.itHasIgloo());
    }

    @Test
    public void StableIceCell_SetUpTent_Success(){
        //Arrange
        IceField.setMaxPlayer(6);
        int maxPlayer = IceField.getMaxPlayer();

        // Act
        boolean result = stableIceCell.setUpTent();

        // Assert
        assertTrue(result);
        assertEquals(maxPlayer, stableIceCell.getTentTurnsLeft());
    }

    @Test
    public void StableIceCell_SetUpTentTwoTimes_Fail(){
        //Arrange
        IceField.setMaxPlayer(6);
        int maxPlayer = IceField.getMaxPlayer();

        // Act
        boolean result_first = stableIceCell.setUpTent();
        boolean result_second = stableIceCell.setUpTent();

        // Assert
        assertTrue(result_first);
        assertFalse(result_second);
        assertEquals(maxPlayer, stableIceCell.getTentTurnsLeft());
    }

    @Test
    public void UnstableIceCell_AcceptCharacterBelowCapacity_Success(){
        //Arrange

        // Act
        unstableIceCell.accept(eskimo);

        // Assert
        assertEquals(1, unstableIceCell.standingPlayers.size());
        verify(eskimo, times(1)).setOwnCell(unstableIceCell);
    }

    @Test
    public void UnstableIceCell_AcceptCharacterAboveCapacity_Sink(){
        //Arrange
        StableIceCell neighbour = mock(StableIceCell.class);
        unstableIceCell.addNeighbour(Way.left, neighbour);
        neighbour.addNeighbour(Way.left.opposite(), unstableIceCell);
        
        // Act
        unstableIceCell.accept(eskimo);
        unstableIceCell.accept(eskimo);
        unstableIceCell.accept(eskimo);

        // Assert
        verify(neighbour, times(1)).addNeighbour(Way.left.opposite(), unstableIceCell);

        assertEquals(3, unstableIceCell.standingPlayers.size());

        verify(eskimo, times(3)).setOwnCell(unstableIceCell);
        
        verify(neighbour, times(1)).addNeighbour(any(), any(WaterCell.class));
        verify(eskimo, times(3)).setOwnCell(any(WaterCell.class));
        verify(eskimo, times(3)).addOneTurnInWater();

        verify(iceField, times(1)).addIceCell(any(WaterCell.class), any(UnstableIceCell.class));
    }

    @Test
    public void WaterCell_MovePlayerOut_Success(){
        //Arrange
        waterCell.accept(eskimo);
        when(iceField.getChosenToSave()).thenReturn(eskimo);
        
        // Act
        boolean result = waterCell.movePlayerOut(Way.left);

        // Assert
        assertTrue(result);
        verify(iceField, times(1)).getChosenToSave();
        verify(eskimo, times(1)).setFacingWay(Way.left);
        verify(eskimo, times(1)).move();
        verify(eskimo, times(1)).resetTurnsInWater();
    }

    @Test
    public void WaterCell_MovePlayerOutNotThere_Fail(){
        //Arrange
        when(iceField.getChosenToSave()).thenReturn(eskimo);
        
        // Act
        boolean result = waterCell.movePlayerOut(Way.left);

        // Assert
        assertFalse(result);
        verify(iceField, times(1)).getChosenToSave();
        verifyNoInteractions(eskimo);
    }
}
