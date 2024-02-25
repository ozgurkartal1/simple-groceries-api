package step_definitions.registration;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.Assertions;
import step_definitions.BaseStep;
import utils.APIUtils;
import pojo.request.user.User;
import pojo.response.token.AccessToken;
import utils.TestDataWriter;

import java.util.Map;

public class RegisterANewClientSteps extends BaseStep {

    User user;

    @When("the user sends POST request to registeration endpoint with valid {string} and {string}")
    public void theUserSendsPOSTRequestToRegisterationEndpointWithValidAnd(String clientName, String clientEmail) {
        user = new User(clientName, clientEmail);
        response = APIUtils.sendPostRequest(request, REGISTRATION_ENDPOINT, user);
        AccessToken accessToken = response.as(AccessToken.class);

        TestDataWriter.dataWriter("access_token.json", accessToken);
    }


    @And("the access token can not be null or empty")
    public void theAccessTokenCanNotBeNullOrEmpty() {
        Assertions.assertThat(response.jsonPath().getString("accessToken")).isNotEmpty();
        Assertions.assertThat(response.jsonPath().getString("accessToken")).isNotNull();
    }

    @When("the user sends POST request to registeration endpoint with parameters {string} and {string}")
    public void theUserSendsPOSTRequestToRegisterationEndpointWithParametersAnd(String clientName, String clientEmail) {
        user = new User(clientName, clientEmail);
        response = APIUtils.sendPostRequest(request, REGISTRATION_ENDPOINT, user);
    }

    @When("the user sends POST request to a invalid endpoint with followint details:")
    public void theUserSendsPOSTRequestToAInvalidEndpoint(DataTable dataTable) {
        Map<String, String> userMap = dataTable.asMap(String.class, String.class);
        user = new User(userMap.get("clientName"), userMap.get("clientEmail"));
        response = APIUtils.sendPostRequest(request, REGISTRATION_ENDPOINT, user);
    }

}
