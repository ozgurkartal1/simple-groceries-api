package step_definitions.cart;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.SoftAssertions;
import pojo.response.cart.AddedItem;
import pojo.response.cart.Cart;
import pojo.response.cart.Item;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;
import java.util.List;

public class DeleteAnItemInTheCartSteps extends BaseStep {

    String endpoint;

    String cartId;

    int itemId;
    @When("the user deletes an specific item")
    public void theUserDeletesAnSpecificItem() {
        itemId = TestDataReader.dataReader("added_products/added_item1.json", AddedItem.class).getItemId();
        cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();

        endpoint = CART_ENDPOINT + "/" + cartId + ITEM_ENDPOINT + "/" + itemId;

        response = APIUtils.sendDeleteRequest(request, endpoint);
    }

    @And("Verify that the specified item is deleted")
    public void verifyThatTheSpecifiedItemIsDeleted() {
        endpoint = CART_ENDPOINT + "/" + cartId + ITEM_ENDPOINT;
        response = APIUtils.sendGetRequest(request, endpoint);
        List<Item> items = response.as(new TypeRef<>() {
        });

        SoftAssertions softAssertions = new SoftAssertions();

        for (Item item : items){
            softAssertions.assertThat(item.getId()).isNotEqualTo(itemId);
        }

        softAssertions.assertAll();

    }
}
