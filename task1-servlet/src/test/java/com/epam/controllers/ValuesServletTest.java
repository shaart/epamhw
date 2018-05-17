package com.epam.controllers;

import com.epam.model.Value;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValuesServletTest {

  @Before
  public void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    RestAssured.defaultParser = Parser.JSON;
  }

  @Test
  public void postThenGetThenPutThenGetThenDeleteThenGet() {
    final String VALUES_URL = "/values";
    final String ID_FIELD = "id";
    final String NAME_FIELD = "name";

    final String TEST_NAME_VALUE = "test";

    Response response;

    // POST - CREATE NEW
    response = RestAssured.given()
        .body(Value.builder().name(TEST_NAME_VALUE).build())
        .when()
        .post(VALUES_URL)
        .then()
        .assertThat()
        .body(ID_FIELD, Matchers.notNullValue())
        .body(NAME_FIELD, Matchers.equalTo(TEST_NAME_VALUE))
        .statusCode(200)
        .extract().response();

    Value createdValue = response.jsonPath().getObject("", Value.class);
    Assert.assertNotNull(createdValue);

    Gson gson = new Gson();

    // GET WITH CREATED
    RestAssured.given()
        .when()
        .get(VALUES_URL)
        .then()
        .assertThat()
        .body(Matchers.containsString(gson.toJson(createdValue)))
        .statusCode(200);

    // PUT TO CREATED
    response = RestAssured.given()
        .body(
            Value.builder()
                .id(createdValue.getId())
                .name(createdValue.getName() + "upd")
                .build()
        )
        .when()
        .put(VALUES_URL + "/" + createdValue.getId())
        .then()
        .assertThat()
        .body(ID_FIELD, Matchers.equalTo(createdValue.getId()))
        .body(NAME_FIELD, Matchers.equalTo(createdValue.getName() + "upd"))
        .statusCode(200)
        .extract().response();

    Value updatedValue = response.jsonPath().getObject("", Value.class);
    Assert.assertNotNull(updatedValue);

    // GET WITH UPDATED
    RestAssured.given()
        .when()
        .get(VALUES_URL)
        .then()
        .assertThat()
        .body(Matchers.containsString(gson.toJson(updatedValue)))
        .statusCode(200);

    // DELETE UPDATED
    response = RestAssured.given()
        .when()
        .delete(VALUES_URL + "/" + updatedValue.getId())
        .then()
        .assertThat()
        .body(ID_FIELD, Matchers.equalTo(createdValue.getId()))
        .body(NAME_FIELD, Matchers.equalTo(createdValue.getName() + "upd"))
        .statusCode(200)
        .extract().response();

    Value deletedValue = response.jsonPath().getObject("", Value.class);
    Assert.assertNotNull(deletedValue);

    // GET WITHOUT DELETED
    RestAssured.given()
        .when()
        .get(VALUES_URL)
        .then()
        .assertThat()
        .body(Matchers.not(Matchers.containsString(gson.toJson(deletedValue))))
        .statusCode(200);
  }
}
