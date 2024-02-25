package step_definitions.cart;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import pojo.response.cart.AddedItem;
import pojo.response.cart.Cart;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

import java.util.ArrayList;
import java.util.List;

public class GetACartSteps extends BaseStep {

    List<Integer> actualItemIds = new ArrayList<>();
    String endpoint;
    @When("the user wants to get a cart")
    public void theUserWantsToGetACart() {
        String cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();
        endpoint = CART_ENDPOINT + "/" + cartId;

        response = APIUtils.sendGetRequest(request, endpoint);

        for (int i = 0 ; i < response.jsonPath().getList("items").size() ; i++){
            actualItemIds.add(response.jsonPath().getInt("items[" + i + "].id"));
        }
    }

    @And("the response ids must match with specified item ids")
    public void theResponseIdsMustMatchWithSpecifiedItemIds() {
        for (int i = 0 ; i < actualItemIds.size() ; i++){
           int expectedItemId =  TestDataReader.dataReader("added_products/added_item" + (i + 1) + ".json", AddedItem.class).getItemId();
           Assertions.assertThat(actualItemIds.get(i)).isEqualTo(expectedItemId);
        }
    }

    @When("the user wants to get a cart with wrong endpoint as {string}")
    public void theUserWantsToGetACartWithWrongEndpointAs(String wrongEndpoint) {
        String cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();
        endpoint = wrongEndpoint + cartId;

        response = APIUtils.sendGetRequest(request, endpoint);
    }
}
