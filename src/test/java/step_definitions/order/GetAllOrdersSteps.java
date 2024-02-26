package step_definitions.order;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import pojo.response.order.allorders.AllOrders;
import pojo.response.order.get_a_single_order.Item;
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

    List<AllOrders> actualOrders;
    @When("the user gets all orders")
    public void theUserGetsAllOrders() {
        response = APIUtils.sendGetRequest(request, ORDER_ENDPOINT);
        actualOrders = response.as(new TypeRef<>() {
        });
    }

    @And("the response orders must be a array of order")
    public void theResponseOrdersMustBeAArrayOfOrder() {
        Assertions.assertThat(actualOrders.size()).isGreaterThan(0);
    }
}
