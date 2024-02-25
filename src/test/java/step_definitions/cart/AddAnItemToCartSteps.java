package step_definitions.cart;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import pojo.request.product.Product;
import pojo.response.cart.AddedItem;
import pojo.response.cart.Cart;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;
import utils.TestDataWriter;

import java.util.ArrayList;
import java.util.List;

public class AddAnItemToCartSteps extends BaseStep {

    List<Response> responses = new ArrayList<>();
    @When("the user added two product with ids as {int} and {int}")
    public void theUserAddedTwoProductWithIdsAsAnd(int item1, int item2) {
        List<Integer> productIds = new ArrayList<>();
        productIds.add(item1);
        productIds.add(item2);

        for(int i = 0 ; i < productIds.size() ; i++){
            String cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();
            String endpoint = CART_ENDPOINT + "/" + cartId + ITEM_ENDPOINT;

            Product product = new Product(productIds.get(i));
            response = APIUtils.sendPostRequest(request, endpoint, product);

            responses.add(response);

            AddedItem addedItem = response.as(AddedItem.class);
            TestDataWriter.dataWriter("added_products/added_item" + (i + 1) + ".json", addedItem);
        }
    }

    @And("the response item id can not be null or empty")
    public void theResponseItemIdCanNotBeNullOrEmpty() {
        for (Response response1:responses){
            Assertions.assertThat(response1.jsonPath().getString("itemId")).isNotEmpty();
            Assertions.assertThat(response1.jsonPath().getString("itemId")).isNotNull();
        }
    }

    @And("the response created must be true")
    public void theResponseCreatedMustBeTrue() {
        for (Response response1:responses){
            Assertions.assertThat(response1.jsonPath().getBoolean("created")).isTrue();
        }
    }
}
