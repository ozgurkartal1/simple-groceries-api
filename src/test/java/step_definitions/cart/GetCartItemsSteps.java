package step_definitions.cart;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.Assertions;
import pojo.response.cart.AddedItem;
import pojo.response.cart.Cart;
import pojo.response.cart.Item;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetCartItemsSteps extends BaseStep {

    List<Item> actualItems;

    List<Item> expectedItems = new ArrayList<>();

    String endpoint;
    @When("the user gets cart items")
    public void theUserGetsCarItems() {
        String cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();
        endpoint = CART_ENDPOINT + "/" + cartId + ITEM_ENDPOINT;

        response = APIUtils.sendGetRequest(request, endpoint);
        actualItems = response.as(new TypeRef<>() {
        });
    }

    @And("the response information must match with following item details:")
    public void theResponseInformationMustMatchWithFollowingItemDetails(DataTable dataTable) {
        List<Map<String, Integer>> maps = dataTable.asMaps(String.class, Integer.class);

        for(int i = 0 ; i < maps.size(); i++){
            int itemId = TestDataReader.dataReader("added_products/added_item" + (i + 1) + ".json", AddedItem.class).getItemId();
            Item item = new Item(itemId, maps.get(i).get("productId"), maps.get(i).get("quantity"));

            expectedItems.add(item);
        }

        expectedItems.forEach(expectedItem -> {
            Assertions.assertThat(actualItems.get(expectedItems.indexOf(expectedItem)).getId()).isEqualTo(expectedItem.getId());
            Assertions.assertThat(actualItems.get(expectedItems.indexOf(expectedItem)).getProductId()).isEqualTo(expectedItem.getProductId());
            Assertions.assertThat(actualItems.get(expectedItems.indexOf(expectedItem)).getQuantity()).isEqualTo(expectedItem.getQuantity());
        });
    }

    @When("the user get cart items with invalid cart id as {string}")
    public void theUserGetCartItemsWithInvalidCartIdAs(String cartId) {

        endpoint = CART_ENDPOINT + "/" + cartId + ITEM_ENDPOINT;

        response = APIUtils.sendGetRequest(request, endpoint);
    }
}
