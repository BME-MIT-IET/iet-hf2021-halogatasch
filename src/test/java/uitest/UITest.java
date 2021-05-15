package uitest;

import graphics.GameBoard;
import io.cucumber.java.bs.A;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.IceField;
import logic.Way;
import org.jacoco.report.internal.html.page.BundlePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class UITest extends ApplicationTest {
    private Stage stage;
    private ResourceBundle bundle;

    @Before
    public void startProject() throws Exception{
        //ApplicationTest.launch(graphics.Main.class);
        Stage s = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(graphics.Main.class);
        stage = s;
    }
    @After
    public void atferEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }
    @Override
    public void start(Stage stage) throws Exception{
        stage.show();
    }

    @Test
    public void testBaseOfProgram() throws Exception{
        assertEquals("Ice Field", stage.getTitle());
        assertEquals(900, stage.getHeight());
        assertEquals(1600, stage.getWidth());
        assertNotNull(stage.getIcons().get(0));
        assertEquals(false, stage.isResizable());
    }

    @Test
    public void testField() throws Exception{
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#start").query());

        assertNotNull(stage.getScene().getRoot().lookup("#backpack"));

        GridPane gp = (GridPane) lookup("#field").query();
        gp.getChildren().get(0);
        assertTrue(gp.isVisible());

        assertNotEquals(-1, GridPane.getRowIndex(lookup("#cellBear").query()));
        assertNotEquals(-1, GridPane.getColumnIndex(lookup("#cellBear").query()));
    }
    @Test
    public void testEskimoAbilityUsed() throws Exception{
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#start").query());

        clickOn((Button)lookup("#ability").query());
        int x = GridPane.getRowIndex(lookup("#cellLid").query());
        int y = GridPane.getColumnIndex(lookup("#cellLid").query());
        assertEquals(GridPane.getRowIndex(lookup("#cellCharacter").query()), x);
        assertEquals(GridPane.getColumnIndex(lookup("#cellCharacter").query()), y);
    }

    @Test
    public void testExplorerAbilityUsed() throws Exception{
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#start").query());

        clickOn((Button)lookup("#ability").query());
        clickOn((Button)lookup("#up").query());
        clickOn((Button)lookup("#ability").query());
        clickOn((Button)lookup("#down").query());
        clickOn((Button)lookup("#ability").query());
        clickOn((Button)lookup("#left").query());
        clickOn((Button)lookup("#ability").query());
        clickOn((Button)lookup("#right").query());

        int x = GridPane.getRowIndex(lookup("#cellCapacity").query());
        int y = GridPane.getColumnIndex(lookup("#cellCapacity").query());
        assertNotEquals(-1, x);
        assertNotEquals(-1, y);
    }

    @Test
    public void testMenuButtons() throws Exception{
        Button explorer = lookup("#explorer").query();
        Button eskimo = lookup("#eskimo").query();
        Button start = lookup("#start").query();
        //Nem látható
        clickOn(eskimo);
        clickOn(eskimo);
        assertEquals(false, start.isVisible());
        //Látható
        clickOn(eskimo);
        assertEquals(true, start.isVisible());

        clickOn(explorer);
        clickOn(explorer);
        clickOn(explorer);
        //6 karakter után nem megnyomható
        assertEquals(false, eskimo.isVisible());
        assertEquals(false, explorer.isVisible());
    }

    @Test
    public void testActionText() throws Exception{
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#start").query());

        //Munka végzés ellenőrzése
        Label work = lookup("#actionLabel").query();
        assertEquals("4", work.getText());
        Button ability = lookup("#ability").query();
        clickOn(ability);
        assertEquals("3", work.getText());
    }

    @Test
    public void testHealthText() throws Exception{
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#start").query());

        //Munka végzés és hp ellenőrzés
        Label hp = lookup("#bodyHeatLabel").query();
        assertEquals("5", hp.getText());
    }

    @Test
    public void testMovement() throws Exception{
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#start").query());

        clickOn((Button)lookup("#up").query());
        clickOn((Button)lookup("#right").query());
        clickOn((Button)lookup("#pass").query());
        clickOn((Button)lookup("#down").query());
        clickOn((Button)lookup("#left").query());

        int differentIdx = 0;
        ArrayList<String> before = new ArrayList<>();
        for(Node n : lookup("#cellCharacter").queryAll()){
            Integer x = GridPane.getColumnIndex(n);
            Integer y = GridPane.getRowIndex(n);
            String s = x.toString() + y.toString();
            if(!before.contains(s)){
                differentIdx++;
                before.add(s);
            }
        }

        assertNotEquals(1, differentIdx);
    }

    @Test
    public void testCantDoAction() throws Exception{
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#start").query());

        Button shovel = lookup("#shovel").query();
        clickOn(shovel); clickOn(shovel); clickOn(shovel); clickOn(shovel); clickOn(shovel);
        clickOn((Button)lookup("#pass").query());
        Label work = lookup("#actionLabel").query();
        assertEquals("4", work.getText());
        clickOn(work);
        assertEquals("4", work.getText());
    }
}
