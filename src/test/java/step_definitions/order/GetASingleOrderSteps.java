package step_definitions.order;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import pojo.response.cart.AddedItem;
import pojo.response.order.OrderRes;
import pojo.response.order.get_a_single_order.GetASingleOrderRes;
import step_definitions.BaseStep;
import utils.APIUtils;
import utils.TestDataReader;

import java.util.List;
import java.util.Map;

public class GetASingleOrderSteps extends BaseStep {

    GetASingleOrderRes actualOrder;
    @When("the user get a single order")
    public void theUserGetASingleOrder() {
        String orderId = TestDataReader.dataReader("order.json", OrderRes.class).getOrderId();
        String endpoint = ORDER_ENDPOINT + "/" + orderId;

        response = APIUtils.sendGetRequest(request, endpoint);

        actualOrder = response.as(GetASingleOrderRes.class);
    }

    @And("the response order must match with following order information:")
    public void theResponseOrderMustMatchWithFollowingOrderInformation(DataTable dataTable) {
        List<Map<String, String>> maps = dataTable.asMaps(String.class, String.class);

        String orderId = TestDataReader.dataReader("order.json", OrderRes.class).getOrderId();
        int itemId = TestDataReader.dataReader("added_products/added_item2.json", AddedItem.class).getItemId();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualOrder.getId()).isEqualTo(orderId);
        softAssertions.assertThat(actualOrder.getItems().get(0).getId()).isEqualTo(itemId);
        softAssertions.assertThat(actualOrder.getItems().get(0).getQuantity()).isEqualTo(Integer.parseInt(maps.get(0).get("quantity")));
        softAssertions.assertThat(actualOrder.getItems().get(0).getProductId()).isEqualTo(Integer.parseInt(maps.get(0).get("productId")));
        softAssertions.assertThat(actualOrder.getCustomerName()).isEqualTo(maps.get(0).get("customerName"));

        softAssertions.assertAll();
    }
}
