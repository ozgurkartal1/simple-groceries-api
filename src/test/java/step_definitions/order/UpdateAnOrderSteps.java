package step_definitions.order;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import pojo.request.order.update_order.UpdateOrder;
import pojo.response.order.OrderRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

public class UpdateAnOrderSteps extends BaseStep {

    String endpoint;
    @When("the user updates an order with new customer name as {string}")
    public void theUserUpdatesAnOrderWithNewCustomerNameAs(String customerName) {
        UpdateOrder body = new UpdateOrder(customerName);
        String orderId = TestDataReader.dataReader("order.json", OrderRes.class).getOrderId();
        endpoint = ORDER_ENDPOINT + "/" + orderId;

        response = APIUtils.sendPatchRequest(request, endpoint, body);
    }

    @And("Verify that the customer name in specified order is updated with name {string}")
    public void verifyThatTheCustomerNameInSpecifiedOrderIsUpdated(String customerName) {
        response = APIUtils.sendGetRequest(request, endpoint);

        Assertions.assertThat(response.jsonPath().getString("customerName")).isEqualTo(customerName);
    }
}
