package logic.items;

import logic.WinChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BakcPackTest {
    private BackPack backPack;
    private Items item;
    private PlayerActions playerActions;

    private void init(Items _item, PlayerActions _playerActions) {
        backPack = new BackPack();
        item = _item;
        playerActions = _playerActions;
    }

    //region EssentialItemTest
    @Test
    public void GetEssentialItemNumberTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);
        int item1Number = 0;
        EssentialItem item2 = new EssentialItem(1, new WinChecker());
        int item2Number = 0;
        EssentialItem item3 = new EssentialItem(2, new WinChecker());
        int item3Number = 0;

        //Act
        backPack.addItem(item, playerActions);
        item1Number = backPack.getEssentialItemNumber();
        backPack.addItem(item2, playerActions);
        item2Number = backPack.getEssentialItemNumber();
        backPack.addItem(item3, playerActions);
        item3Number = backPack.getEssentialItemNumber();

        //Assert
        assertEquals(1, item1Number);
        assertEquals(2, item2Number);
        assertEquals(3, item3Number);
    }

    @Test
    public void HasEssentialItemIDTest() {
        //Arange
        init(null, PlayerActions.assemblingEssentials);

        //Act
        for (int i = 0; i < 3; i++) {
            EssentialItem item = new EssentialItem(i, new WinChecker());
            boolean backPackAdd = backPack.addItem(item, playerActions);
            boolean hasEssetialItemID = backPack.hasEssentialItemID(i);

            //Assert
            assertTrue(backPackAdd);
            assertTrue(hasEssetialItemID);
        }
    }

    @Test
    public void AddEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);
        EssentialItem item2 = new EssentialItem(1, new WinChecker());

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertTrue(backPackSecondAdd);
    }

    @Test
    public void RemoveEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        backPack.removeItem(playerActions);
        boolean backPackHasItemAfterRemove = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackHasItemAfterRemove);
    }

    @Test
    public void GetEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, getItem);
    }
    //endregion

    //region FoodTest
    @Test
    void UseFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Food useFoodItem = backPack.useFood();
        boolean hasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, useFoodItem);
        assertFalse(hasItem);
    }

    @Test
    public void AddFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);
        Food item2 = new Food();

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertTrue(backPackSecondAdd);
    }

    @Test
    public void RemoveFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        backPack.removeItem(playerActions);
        boolean backPackHasItemAfterRemove = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackHasItemAfterRemove);
    }

    @Test
    public void GetFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, getItem);
    }
    //endregion


    //region DivingsuitTest
    @Test
    public void AddDivingsuitTest() {
        //Arrange
        init(new Divingsuit(), PlayerActions.wearingSuit);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondDivingsuitTest() {
        //Arrange
        init(new Divingsuit(), PlayerActions.wearingSuit);
        Divingsuit item2 = new Divingsuit();

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveDivingsuitTest() {
        //Arrange
        init(new Divingsuit(), PlayerActions.wearingSuit);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        backPack.removeItem(playerActions);
        boolean backPackHasItemAfterRemove = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackHasItemAfterRemove);
    }

    @Test
    public void GetDivingsuitTest() {
        //Arrange
        init(new Divingsuit(), PlayerActions.wearingSuit);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, getItem);
    }
    //endregion

    //region FragileShovelTest
    @Test
    public void AddFragileShovelTest() {
        //Arrange
        init(new FragileShovel(), PlayerActions.fragileshoveling);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondFragileShovelTest() {
        //Arrange
        init(new FragileShovel(), PlayerActions.fragileshoveling);
        FragileShovel item2 = new FragileShovel();

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveFragileShovelTest() {
        //Arrange
        init(new FragileShovel(), PlayerActions.fragileshoveling);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        backPack.removeItem(playerActions);
        boolean backPackHasItemAfterRemove = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackHasItemAfterRemove);
    }

    @Test
    public void GetFragileShovelTest() {
        //Arrange
        init(new FragileShovel(), PlayerActions.fragileshoveling);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, getItem);
    }
    //endregion

    //region RopeTest
    @Test
    public void AddRopeTest() {
        //Arrange
        init(new Rope(), PlayerActions.savingWithRope);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondRopeTest() {
        //Arrange
        init(new Rope(), PlayerActions.savingWithRope);
        Rope item2 = new Rope();

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveRopeTest() {
        //Arrange
        init(new Rope(), PlayerActions.savingWithRope);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        backPack.removeItem(playerActions);
        boolean backPackHasItemAfterRemove = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackHasItemAfterRemove);
    }

    @Test
    public void GetRopeTest() {
        //Arrange
        init(new Rope(), PlayerActions.savingWithRope);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, getItem);
    }
    //endregion

    //region ShovelTest
    @Test
    public void AditemShovelTest() {
        //Arrange
        init(new Shovel(), PlayerActions.shoveling);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondShovelTest() {
        //Arrange
        init(new Shovel(), PlayerActions.shoveling);
        Shovel item2 = new Shovel();

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveShovelTest() {
        //Arrange
        init(new Shovel(), PlayerActions.shoveling);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        backPack.removeItem(playerActions);
        boolean backPackHasItemAfterRemove = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackHasItemAfterRemove);
    }

    @Test
    public void GetShovelTest() {
        //Arrange
        init(new Shovel(), PlayerActions.shoveling);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, getItem);
    }
    //endregion

    //region TentTest
    @Test
    public void AddTentTest() {
        //Arrange
        init(new Tent(), PlayerActions.setUpTent);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondTentTest() {
        //Arrange
        init(new Tent(), PlayerActions.setUpTent);
        Tent item2 = new Tent();

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveTentTest() {
        //Arrange
        init(new Tent(), PlayerActions.setUpTent);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        boolean backPackHasItem = backPack.hasItem(playerActions);
        backPack.removeItem(playerActions);
        boolean backPackHasItemAfterRemove = backPack.hasItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
        assertFalse(backPackHasItemAfterRemove);
    }

    @Test
    public void GetTentTest() {
        //Arrange
        init(new Tent(), PlayerActions.setUpTent);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertTrue(backPackAdd);
        assertEquals(item, getItem);
    }
    //endregion
}
