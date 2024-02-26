package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", glue = "step_definitions", tags = "@registration or @product or @cart or @order"
)
public class CukesRunner {
}
