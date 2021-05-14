package uitest;

import graphics.GameBoard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.Way;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITest extends ApplicationTest {

    @Before
    public void startProject() throws Exception{ ApplicationTest.launch(graphics.Main.class); }
    @Override
    public void start(Stage stage){
        stage.show();
    }

    @Test
    public void testMenuButtons() throws Exception{
        Button explorer = lookup("#explorer").query();
        Button eskimo = lookup("#eskimo").query();
        Button start = lookup("#start").query();
        //Nem látható
        clickOn(explorer);
        clickOn(eskimo);
        assertEquals(false, start.isVisible());
        //Látható
        clickOn(explorer);
        assertEquals(true, start.isVisible());

        clickOn(eskimo);
        clickOn(explorer);
        clickOn(eskimo);
        //6 karakter után nem megnyomható
        assertEquals(false, eskimo.isVisible());
        assertEquals(false, explorer.isVisible());
    }

    @Test
    public void testGameBoardBasicButtons() throws Exception{
        clickOn((Button)lookup("#eskimo").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#explorer").query());
        clickOn((Button)lookup("#start").query());

        //Munka végzés és hp ellenőrzés
        Label work = lookup("#actionLabel").query();
        assertEquals("4", work.getText());
        Button ability = lookup("#ability").query();
        clickOn(ability);
        assertEquals("3", work.getText());
        Label hp = lookup("#bodyHeatLabel").query();
        assertEquals("3", work.getText());

        //Csákány használatának tiltása
        Button pickaxe = lookup("#pickaxe").query();
        clickOn(pickaxe);
        //assertEquals(false, pickaxe.isVisible());

        //assertEquals(true, );
        /*Button wayUp = lookup("up").query();
        Button wayDown = lookup("down").query();
        Button wayLeft = lookup("left").query();
        Button wayRight = lookup("right").query();
        for(Way w : Way.values())
            clickOn(pass);*/

    }

}
