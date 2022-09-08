package com.crm.Authentication;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class BasicAuthTest {

	@Test
	public void basicAuth() {
		baseURI="http://localhost";
		port=8084;
		
		given()
			.auth().basic("rmgyantra", "rmgy@9999")
		.when()
			.get("/login")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(202)
			.log().all();
	}
}
