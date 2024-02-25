package step_definitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigManager;

public abstract class BaseStep {

    protected static RequestSpecification request;

    protected static Response response;

    protected final String REGISTRATION_ENDPOINT;
    protected final String PRODUCTS_ENDPOINT;

    protected final String CART_ENDPOINT;
    protected final String ITEM_ENDPOINT;

    protected BaseStep() {
        RestAssured.baseURI = ConfigManager.getProperty("BaseURI");
        REGISTRATION_ENDPOINT = ConfigManager.getProperty("api.registration.endpoint");
        PRODUCTS_ENDPOINT = ConfigManager.getProperty("api.products.endpoint");
        CART_ENDPOINT = ConfigManager.getProperty("api.cart.endpoint");
        ITEM_ENDPOINT = ConfigManager.getProperty("api.item.endpoint");
    }
}
