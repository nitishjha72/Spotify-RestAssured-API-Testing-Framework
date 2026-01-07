package com.nitish.qa.assertions;

import com.nitish.qa.io.SchemaReader;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public final class ApiAssertions {

    private ApiAssertions() {}

    public static void validateSchema(Response response, String schemaPath) {
        response.then()
                .body(matchesJsonSchema(SchemaReader.read(schemaPath)));
    }
}
