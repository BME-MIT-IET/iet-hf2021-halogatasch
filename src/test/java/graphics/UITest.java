import graphics.Main;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class UITest extends ApplicationTest {

    @Before
    public void setUpClass() throws Exception{
        ApplicationTest.launch(graphics.Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        /*Main app = new Main();
        app.start(stage);
        stage.setScene(new Scene(new graphics.Main(stage)));*/

        stage.show();
    }
    @After
    public void tearDown() throws Exception{
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void cant_start_less_than_four_player(){
        /*Button explorerBtn = lookup("#explorer").query();
        FxRobot robot = new FxRobot();
        robot.clickOn(explorerBtn);*/
        verifyThat("#explorer", hasText("lol"));

        //System.out.println(explorerBtn.layoutXProperty());
        //explorerBtn.fire(); explorerBtn.fire(); explorerBtn.fire(); explorerBtn.fire();
        //Assert.assertEquals(true, explorerBtn.isDefaultButton());
    }

    /*@Test
    public void cant_add_more_than_six_player(){

    }*/
}
