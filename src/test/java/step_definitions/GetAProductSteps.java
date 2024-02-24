package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import pojo.response.products.Product;
import utils.APIUtils;
import utils.TestDataReader;

public class GetAProductSteps extends BaseStep{

    Product product;

    SoftAssertions softAssertions = new SoftAssertions();
    @When("the user sends request to get a product endpoint with specified product id")
    public void theUserSendsRequestToGetAProductEndpointWithSpecifiedProductId() {
        product = TestDataReader.dataReader("product.json", Product.class);
        String endpoint = PRODUCTS_ENDPOINT + "/" + product.getId();
        response = APIUtils.sendGetRequest(request, endpoint);
    }

    @And("the product should match with is written json file")
    public void theProductShouldMatchWithIsWrittenJsonFile() {
        softAssertions.assertThat(response.jsonPath().getString("category")).isEqualTo(product.getCategory());
        softAssertions.assertThat(response.jsonPath().getString("name")).isEqualTo(product.getName());
        softAssertions.assertThat(response.jsonPath().getBoolean("inStock")).isEqualTo(product.isInStock());

        softAssertions.assertAll();
    }

    @When("the user sends request to get a product endpoint with invalid product id as {string}")
    public void theUserSendsRequestToGetAProductEndpointWithInvalidProductIdAs(String invalidProductId) {
        String endpoint = PRODUCTS_ENDPOINT + "/" + invalidProductId;
        response = APIUtils.sendGetRequest(request, endpoint);
    }
}
