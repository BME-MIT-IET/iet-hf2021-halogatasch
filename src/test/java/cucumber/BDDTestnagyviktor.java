package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class BDDTestnagyviktor {
    @Given("Save person with Ro")
    public void saveWithRopeGiven() throws Throwable{
        assertEquals(true, true);
    }

    @When("Save person with R")
    public void saveWithRopeWhen() throws Throwable{
        assertEquals(true, true);
    }

    /*@Then("Save person with ")
    public void saveWithRopeThen() throws Throwable{
        assertEquals(true, true);
    }*/
}
