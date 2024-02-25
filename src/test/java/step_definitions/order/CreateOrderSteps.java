package step_definitions.order;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import pojo.request.order.create_order.OrderReq;
import pojo.response.cart.Cart;
import pojo.response.order.OrderRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;
import utils.TestDataWriter;


public class CreateOrderSteps extends BaseStep {

    OrderRes orderRes;

    @When("the user creates a order with customer name as {string}")
    public void theUserCreatesAOrder(String customerName) {
        String cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();
        OrderReq order = new OrderReq(cartId, customerName);
        response = APIUtils.sendPostRequest(request, ORDER_ENDPOINT, order);

        orderRes = response.as(OrderRes.class);

        TestDataWriter.dataWriter("order.json", orderRes);
    }

    @And("the response order id can not be empty or null")
    public void theResponseOrderIdCanNotBeEmptyOrNull() {
        Assertions.assertThat(orderRes.getOrderId()).isNotEmpty();
        Assertions.assertThat(orderRes.getOrderId()).isNotNull();
    }

    @And("the response order created must be true")
    public void theResponseOrderCreatedMustBeTrue() {
        Assertions.assertThat(orderRes.isCreated()).isTrue();
    }
}
