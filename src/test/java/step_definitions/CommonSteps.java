package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;

public class CommonSteps extends BaseStep{

    @Given("the user is in base uri")
    public void theUserIsInBaseUri() {
        request = RestAssured.given();
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int statusCode) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    @And("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String errorMessage) {
        Assertions.assertThat(response.jsonPath().getString("error")).isEqualTo(errorMessage);
    }
}
