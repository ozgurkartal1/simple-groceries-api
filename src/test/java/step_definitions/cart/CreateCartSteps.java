package step_definitions.cart;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import pojo.response.cart.Cart;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataWriter;

public class CreateCartSteps extends BaseStep {

    Cart cart;

    @When("the user sends POST request to create cart endpoint")
    public void theUserSendsPOSTRequestToCreateCartEndpoint() {
        response = APIUtils.sendPostRequest(request, CART_ENDPOINT);

        cart = response.as(Cart.class);

        TestDataWriter.dataWriter("cart.json", cart);

    }

    @And("the response cartId can not be null or empty")
    public void theResponseCartIdCanNotBeNullOrEmpty() {
        Assertions.assertThat(cart.getCartId()).isNotNull();
        Assertions.assertThat(cart.getCartId()).isNotEmpty();
    }

    @And("the response created should be true")
    public void theResponseCreatedShouldBeTrue() {
        Assertions.assertThat(cart.isCreated()).isTrue();
    }

    @When("the user sends POST request to wrong endpoint as {string}")
    public void theUserSendsPOSTRequestToWrongEndpointAs(String endpoint) {
        response = APIUtils.sendPostRequest(request, endpoint);
    }
}
