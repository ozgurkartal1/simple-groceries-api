package step_definitions.order;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.SoftAssertions;
import pojo.request.order.allorders.AllOrders;
import pojo.request.order.allorders.Item;
import pojo.response.cart.AddedItem;
import pojo.response.order.OrderRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class GetAllOrdersSteps extends BaseStep {

    AllOrders actualOrder;

    AllOrders expectedOrder;

    List<Item> items = new ArrayList<>();

    SoftAssertions softAssertions = new SoftAssertions();

    @When("the user gets all orders")
    public void theUserGetsAllOrders() {
        response = APIUtils.sendGetRequest(request, ORDER_ENDPOINT);
        List<AllOrders> orders = response.as(new TypeRef<List<AllOrders>>() {
        });

        actualOrder = orders.get(orders.size() - 1);
    }

    @And("the response order which is last must match with following details:")
    public void theResponseOrderMustMatchWithFollowingDetails(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> map : maps) {
            int itemId = TestDataReader.dataReader("added_products/added_item2.json", AddedItem.class).getItemId();
            int productId = Integer.parseInt(map.get("productId"));
            int quantity = Integer.parseInt(map.get("quantity"));
            Item item = new Item(itemId, productId, quantity);

            items.add(item);

            String orderId = TestDataReader.dataReader("order.json", OrderRes.class).getOrderId();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String today = dateFormat.format(date);

            expectedOrder = new AllOrders(orderId, items, map.get("customerName"), today, "");
        }

        softAssertions.assertThat(actualOrder.getId()).isEqualTo(expectedOrder.getId());
        softAssertions.assertThat(actualOrder.getCustomerName()).isEqualTo(expectedOrder.getCustomerName());
        softAssertions.assertThat(actualOrder.getCreated().contains(expectedOrder.getCreated())).isTrue();
        softAssertions.assertThat(actualOrder.getComment()).isEqualTo(expectedOrder.getComment());

        for (int i = 0; i < expectedOrder.getItems().size(); i++) {
            softAssertions.assertThat(actualOrder.getItems().get(i).getId()).isEqualTo(expectedOrder.getItems().get(i).getId());
            softAssertions.assertThat(actualOrder.getItems().get(i).getProductId()).isEqualTo(expectedOrder.getItems().get(i).getProductId());
            softAssertions.assertThat(actualOrder.getItems().get(i).getQuantity()).isEqualTo(expectedOrder.getItems().get(i).getQuantity());
        }


        softAssertions.assertAll();

    }
}
