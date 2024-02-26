package step_definitions.order;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import pojo.response.order.OrderRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

public class DeleteAnOrderSteps extends BaseStep {
    String endpoint;
    String orderId;
    @When("the user deletes a specific order")
    public void theUserDeletesASpecificOrder() {
        orderId = TestDataReader.dataReader("order.json", OrderRes.class).getOrderId();
        endpoint = ORDER_ENDPOINT + "/" + orderId;

        response = APIUtils.sendDeleteRequest(request, endpoint);
    }

    @And("Verify that the specified order is deleted")
    public void verifyThatTheSpecifiedOrderIsDeleted() {
        response = APIUtils.sendGetRequest(request, endpoint);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(response.getStatusCode()).isEqualTo(404);
        softAssertions.assertThat(response.jsonPath().getString("error")).isEqualTo("No order with id " + orderId);
    }
}
