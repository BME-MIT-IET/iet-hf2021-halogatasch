package logic.items;

import logic.WinChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BackPackTest {
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
        EssentialItem item2 = new EssentialItem(1, new WinChecker());
        EssentialItem item3 = new EssentialItem(2, new WinChecker());

        //Act
        backPack.addItem(item, playerActions);
        int item1Number = backPack.getEssentialItemNumber();
        backPack.addItem(item2, playerActions);
        int item2Number = backPack.getEssentialItemNumber();
        backPack.addItem(item3, playerActions);
        int item3Number = backPack.getEssentialItemNumber();

        //Assert
        assertEquals(1, item1Number);
        assertEquals(2, item2Number);
        assertEquals(3, item3Number);
    }

    @Test
    public void HasEssentialItemIDTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);
        EssentialItem item2 = new EssentialItem(1, new WinChecker());
        EssentialItem item3 = new EssentialItem(2, new WinChecker());

        //Act
        backPack.addItem(item, playerActions);
        boolean hasEssetialItemID = backPack.hasEssentialItemID(0);
        backPack.addItem(item2, playerActions);
        boolean hasEssetialItemID2 = backPack.hasEssentialItemID(1);
        backPack.addItem(item3, playerActions);
        boolean hasEssetialItemID3 = backPack.hasEssentialItemID(2);

        //Assert
        assertTrue(hasEssetialItemID);
        assertTrue(hasEssetialItemID2);
        assertTrue(hasEssetialItemID3);
    }


    @Test
    public void AddEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);

        //Assert
        boolean backPackHasItem = backPack.hasItem(playerActions);
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);
        EssentialItem item2 = new EssentialItem(1, new WinChecker());
        backPack.addItem(item, playerActions);

        //Act
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackSecondAdd);
    }

    @Test
    public void RemoveEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);
        backPack.addItem(item, playerActions);

        //Act
        backPack.removeItem(playerActions);

        //Assert
        Items itemAfterRemove = backPack.getItem(playerActions);
        assertNull(itemAfterRemove);
    }

    @Test
    public void GetEssentialItemTest() {
        //Arange
        init(new EssentialItem(0, new WinChecker()), PlayerActions.assemblingEssentials);
        backPack.addItem(item, playerActions);

        //Act
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertEquals(item, getItem);
    }
    //endregion

    //region FoodTest
    @Test
    void UseFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);
        backPack.addItem(item, playerActions);

        //Act
        Food useFoodItem = backPack.useFood();

        //Assert
        boolean hasItem = backPack.hasItem(playerActions);
        assertEquals(item, useFoodItem);
        assertFalse(hasItem);
    }

    @Test
    void UseFoodTestEmpty() {
        //Arrange
        init(new Food(), PlayerActions.eating);

        //Act
        Food useFoodItem = backPack.useFood();

        //Assert
        boolean hasItem = backPack.hasItem(playerActions);
        assertNull(useFoodItem);
        assertFalse(hasItem);
    }

    @Test
    void UseFoodTestFoodRemains() {
        //Arrange
        init(new Food(), PlayerActions.eating);
        Food item2 = new Food();
        backPack.addItem(item, playerActions);
        backPack.addItem(item2, playerActions);

        //Act
        Food useFoodItem = backPack.useFood();

        //Assert
        boolean hasItem = backPack.hasItem(playerActions);
        assertEquals(item2, useFoodItem);
        assertTrue(hasItem);
    }

    @Test
    public void AddFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);

        //Act
        boolean backPackAdd = backPack.addItem(item, playerActions);

        //Assert
        boolean backPackHasItem = backPack.hasItem(playerActions);
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);
        Food item2 = new Food();
        backPack.addItem(item, playerActions);

        //Act
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertTrue(backPackSecondAdd);
    }

    @Test
    public void RemoveFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);
        backPack.addItem(item, playerActions);

        //Act
        backPack.removeItem(playerActions);

        //Assert
        Items itemAfterRemove = backPack.getItem(playerActions);
        assertNull(itemAfterRemove);
    }

    @Test
    public void GetFoodTest() {
        //Arrange
        init(new Food(), PlayerActions.eating);
        backPack.addItem(item, playerActions);

        //Act
        Items getItem = backPack.getItem(playerActions);

        //Assert
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

        //Assert
        boolean backPackHasItem = backPack.hasItem(playerActions);
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondDivingsuitTest() {
        //Arrange
        init(new Divingsuit(), PlayerActions.wearingSuit);
        Divingsuit item2 = new Divingsuit();
        backPack.addItem(item, playerActions);

        //Act
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveDivingsuitTest() {
        //Arrange
        init(new Divingsuit(), PlayerActions.wearingSuit);
        backPack.addItem(item, playerActions);

        //Act
        backPack.removeItem(playerActions);

        //Assert
        Items itemAfterRemove = backPack.getItem(playerActions);
        assertNull(itemAfterRemove);
    }

    @Test
    public void GetDivingsuitTest() {
        //Arrange
        init(new Divingsuit(), PlayerActions.wearingSuit);
        backPack.addItem(item, playerActions);

        //Act
        Items getItem = backPack.getItem(playerActions);

        //Assert
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

        //Assert
        boolean backPackHasItem = backPack.hasItem(playerActions);
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondFragileShovelTest() {
        //Arrange
        init(new FragileShovel(), PlayerActions.fragileshoveling);
        FragileShovel item2 = new FragileShovel();
        backPack.addItem(item, playerActions);

        //Act
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveFragileShovelTest() {
        //Arrange
        init(new FragileShovel(), PlayerActions.fragileshoveling);
        backPack.addItem(item, playerActions);

        //Act
        backPack.removeItem(playerActions);

        //Assert
        Items itemAfterRemove = backPack.getItem(playerActions);
        assertNull(itemAfterRemove);
    }

    @Test
    public void GetFragileShovelTest() {
        //Arrange
        init(new FragileShovel(), PlayerActions.fragileshoveling);
        backPack.addItem(item, playerActions);

        //Act
        Items getItem = backPack.getItem(playerActions);

        //Assert
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

        //Assert
        boolean backPackHasItem = backPack.hasItem(playerActions);
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondRopeTest() {
        //Arrange
        init(new Rope(), PlayerActions.savingWithRope);
        Rope item2 = new Rope();
        backPack.addItem(item, playerActions);

        //Act
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveRopeTest() {
        //Arrange
        init(new Rope(), PlayerActions.savingWithRope);
        backPack.addItem(item, playerActions);

        //Act
        backPack.removeItem(playerActions);

        //Assert
        Items itemAfterRemove = backPack.getItem(playerActions);
        assertNull(itemAfterRemove);
    }

    @Test
    public void GetRopeTest() {
        //Arrange
        init(new Rope(), PlayerActions.savingWithRope);
        backPack.addItem(item, playerActions);

        //Act
        Items getItem = backPack.getItem(playerActions);

        //Assert
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

        //Assert
        boolean backPackHasItem = backPack.hasItem(playerActions);
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondShovelTest() {
        //Arrange
        init(new Shovel(), PlayerActions.shoveling);
        Shovel item2 = new Shovel();
        backPack.addItem(item, playerActions);

        //Act
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveShovelTest() {
        //Arrange
        init(new Shovel(), PlayerActions.shoveling);
        backPack.addItem(item, playerActions);

        //Act
        backPack.removeItem(playerActions);

        //Assert
        Items itemAfterRemove = backPack.getItem(playerActions);
        assertNull(itemAfterRemove);
    }

    @Test
    public void GetShovelTest() {
        //Arrange
        init(new Shovel(), PlayerActions.shoveling);
        backPack.addItem(item, playerActions);

        //Act
        Items getItem = backPack.getItem(playerActions);

        //Assert
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

        //Assert
        boolean backPackHasItem = backPack.hasItem(playerActions);
        assertTrue(backPackAdd);
        assertTrue(backPackHasItem);
    }

    @Test
    public void AddSecondTentTest() {
        //Arrange
        init(new Tent(), PlayerActions.setUpTent);
        Tent item2 = new Tent();
        backPack.addItem(item, playerActions);

        //Act
        boolean backPackSecondAdd = backPack.addItem(item2, playerActions);

        //Assert
        assertFalse(backPackSecondAdd);
    }

    @Test
    public void RemoveTentTest() {
        //Arrange
        init(new Tent(), PlayerActions.setUpTent);
        backPack.addItem(item, playerActions);

        //Act
        backPack.removeItem(playerActions);

        //Assert
        Items itemAfterRemove = backPack.getItem(playerActions);
        assertNull(itemAfterRemove);
    }

    @Test
    public void GetTentTest() {
        //Arrange
        init(new Tent(), PlayerActions.setUpTent);
        backPack.addItem(item, playerActions);

        //Act
        Items getItem = backPack.getItem(playerActions);

        //Assert
        assertEquals(item, getItem);
    }
    //endregion
}
