package step_definitions.cart;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import pojo.response.cart.AddedItem;
import pojo.response.cart.Cart;
import pojo.response.cart.GetACart;
import pojo.response.cart.Item;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GetACartSteps extends BaseStep {

    List<Item> items = new ArrayList<>();
    GetACart actualResult;
    String endpoint;
    SoftAssertions softAssertions = new SoftAssertions();

    @When("the user wants to get a cart")
    public void theUserWantsToGetACart() {
        String cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();
        endpoint = CART_ENDPOINT + "/" + cartId;

        response = APIUtils.sendGetRequest(request, endpoint);
        actualResult = response.as(new TypeRef<>() {
        });
    }

    @And("the response information must match with following cart details:")
    public void theResponseIdsMustMatchWithSpecifiedItemIds(DataTable dataTable) {
        List<Map<String, Integer>> maps = dataTable.asMaps(String.class, Integer.class);

        for (int i = 0; i < maps.size(); i++) {
            int itemId = TestDataReader.dataReader("added_products/added_item" + (i + 1) + ".json", AddedItem.class).getItemId();
            Item item = new Item(itemId, maps.get(i).get("productId"), maps.get(i).get("quantity"));

            items.add(item);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date);

        GetACart expectedResult = new GetACart(items, today);

        for (int i = 0; i < expectedResult.getItems().size(); i++) {
            softAssertions.assertThat(actualResult.getItems().get(i).getId()).isEqualTo(expectedResult.getItems().get(i).getId());
            softAssertions.assertThat(actualResult.getItems().get(i).getProductId()).isEqualTo(expectedResult.getItems().get(i).getProductId());
            softAssertions.assertThat(actualResult.getItems().get(i).getQuantity()).isEqualTo(expectedResult.getItems().get(i).getQuantity());
        }

        softAssertions.assertAll();
        Assertions.assertThat(actualResult.getCreated().contains(expectedResult.getCreated())).isTrue();

    }

    @When("the user wants to get a cart with wrong endpoint as {string}")
    public void theUserWantsToGetACartWithWrongEndpointAs(String wrongEndpoint) {
        String cartId = TestDataReader.dataReader("cart.json", Cart.class).getCartId();
        endpoint = wrongEndpoint + cartId;

        response = APIUtils.sendGetRequest(request, endpoint);
    }
}
