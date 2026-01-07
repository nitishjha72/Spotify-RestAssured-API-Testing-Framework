package com.nitish.qa.http;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public final class ResponseSpecFactory {

    private ResponseSpecFactory() {}

    public static ResponseSpecification success() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
