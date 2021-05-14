package logic.characters;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "target/test-classes", glue = {"cucumber.steps"})
public class MyStepTest {
}
