package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class APIUtils {

    public static Response sendPostRequest(RequestSpecification request, String endpoint, Object payload){
        return request
                .contentType(ContentType.JSON)
                .body(payload)
                .when().log().all()
                .post(endpoint);
    }

    public static Response sendGetRequest(RequestSpecification request, String endpoint) {
        return request
                .when()
                .log().all()
                .get(endpoint);
    }

    public static Response sendGetRequest(RequestSpecification request, String endpoint, String key, String value){
        return request
                .when()
                .log().all()
                .queryParam(key, value)
                .get(endpoint);
    }
}
