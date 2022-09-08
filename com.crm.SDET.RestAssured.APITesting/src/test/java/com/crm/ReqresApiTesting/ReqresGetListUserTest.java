package com.crm.ReqresApiTesting;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ReqresGetListUserTest {

	@Test
	public void ReadAll() {
		//prerequisites
		given()
			.contentType(ContentType.JSON)
		//request
		.when()
			.get("https://reqres.in/api/users?page=2")
		//response validation
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(200)
			.log().all();
	}
}
