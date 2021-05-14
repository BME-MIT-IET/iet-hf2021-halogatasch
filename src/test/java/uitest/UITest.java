package uitest;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit5.ApplicationTest;

public class UITest extends ApplicationTest {

    @Before
    public void startProject() throws Exception{
        ApplicationTest.launch(graphics.Main.class);
    }
    @Override
    public void start(Stage stage){
        stage.show();
    }
    @Test
    public void test()throws Exception{
        Button b = lookup("#explorer").query();
        Button b2 = lookup("#explorer").query();
        Button b3 = lookup("#explorer").query();
        clickOn(b);
        clickOn(b2);
        clickOn(b3);
    }

}
