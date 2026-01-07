package com.nitish.qa.http;

import com.nitish.qa.auth.OAuthTokenProvider;
import com.nitish.qa.config.ConfigFactory;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static com.nitish.qa.constants.Endpoints.BASE_PATH;

public final class RequestSpecFactory {

    private RequestSpecFactory() {}

    public static RequestSpecification apiSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigFactory.get().getBaseUrl())
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer " + OAuthTokenProvider.getToken())
                .setConfig(RestAssured.config().logConfig(LogConfig.logConfig().blacklistDefaultSensitiveHeaders()))
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification accountSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigFactory.get().getAccountUrl())
                .setContentType(ContentType.URLENC)
                .setConfig(RestAssured.config().logConfig(LogConfig.logConfig().blacklistDefaultSensitiveHeaders()))
                .build();
    }

    public static RequestSpecification apiSpecWithToken(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigFactory.get().getBaseUrl())
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token)
                .setConfig(RestAssured.config().logConfig(LogConfig.logConfig().blacklistDefaultSensitiveHeaders()))
                .build();
    }


}
