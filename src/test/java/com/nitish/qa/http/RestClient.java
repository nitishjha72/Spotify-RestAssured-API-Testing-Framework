package com.nitish.qa.http;


import com.nitish.qa.pojo.Token;
import io.restassured.response.Response;

import java.util.Map;

import static com.nitish.qa.constants.Endpoints.API;
import static com.nitish.qa.constants.Endpoints.TOKEN;
import static io.restassured.RestAssured.given;

public final class RestClient {

    private RestClient() {}

    public static Response post(String path, Object body) {
        return given()
                .spec(RequestSpecFactory.apiSpec())
                .body(body)
                .post(path);
    }

    public static Response get(String path) {
        return given()
                .spec(RequestSpecFactory.apiSpec())
                .get(path);
    }

    public static Response put(String path, Object body) {
        return given()
                .spec(RequestSpecFactory.apiSpec())
                .body(body)
                .put(path);
    }

    public static Token fetchToken(Map<String, String> formParams) {
        return given()
                .spec(RequestSpecFactory.accountSpec())
                .formParams(formParams)
                .post(API + TOKEN)
                .as(Token.class);
    }

    public static Response postWithToken(String path, String token, Object body) {

        return given()
                .spec(RequestSpecFactory.apiSpecWithToken(token))
                .body(body)
                .post(path);
    }

}

