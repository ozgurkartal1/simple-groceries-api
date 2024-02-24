package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.assertj.core.api.Assertions;
import pojo.response.products.Product;
import utils.APIUtils;
import utils.TestDataWriter;

import java.util.List;

public class GetAllProductsSteps extends BaseStep{

    List<Product> products;
    @When("the user sends GET request to get all products endpoint")
    public void theUserSendsGETRequestToGetAllProductsEndpoint() {
        response = APIUtils.sendGetRequest(request, PRODUCTS_ENDPOINT);

        products = response.as(new TypeRef<>() {
        });

        TestDataWriter.dataWriter("product.json", products.get(0));
    }

    @And("the array of products should be displayed")
    public void theArrayOfProductsShouldBeDisplayed() {
        Assertions.assertThat(products.size()).isGreaterThan(0);
    }

    @When("the user sends GET request to get all products endpoint with {string} key and {string}")
    public void theUserSendsGETRequestToGetAllProductsEndpointWith(String key, String value) {
        response = APIUtils.sendGetRequest(request, PRODUCTS_ENDPOINT, key, value);

        products = response.as(new TypeRef<>() {
        });
    }

    @And("the response array of products should includes specified {string} and {string}")
    public void theArrayOfProductsShouldIncludesSpecified(String key, String value) {
        products = response.as(new TypeRef<>() {
        });

        if(key.equals("available")){
            products.forEach(product -> {
                Assertions.assertThat(product.isInStock()).isEqualTo(Boolean.parseBoolean(value));
            });
        }

        if(key.equals("category")){
            products.forEach(product -> {
                Assertions.assertThat(product.getCategory()).isEqualTo(value);
            });
        }
    }
}
