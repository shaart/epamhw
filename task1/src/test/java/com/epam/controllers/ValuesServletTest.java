package com.epam.controllers;

import com.epam.services.impl.ValuesServiceTestMock;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class ValuesServletTest {

  @Before
  public void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8080;
    ValuesServlet.setValuesService(ValuesServiceTestMock.getInstance());
  }

  @Test
  public void doDelete() {
  }

  @Test
  public void doGet() {
    RestAssured.given().when().get("/values")
        .then()
        .assertThat()
        .body("id", Matchers.equalTo(ValuesServiceTestMock.EXPECT_ID))
        .body("name", Matchers.hasItem(ValuesServiceTestMock.EXPECT_NAME))
        .statusCode(200);
  }

  @Test
  public void doPost() {
  }

  @Test
  public void doPut() {
  }
}
