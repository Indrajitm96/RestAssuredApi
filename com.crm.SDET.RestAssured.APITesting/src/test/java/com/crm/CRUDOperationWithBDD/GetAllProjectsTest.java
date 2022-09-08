package com.crm.CRUDOperationWithBDD;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class GetAllProjectsTest {

	@Test
	public void ReadAll() {
		given()   //preconditions
			.contentType(ContentType.JSON)
		.when()    //request
			.get("http://localhost:8084/projects")
		.then()    //response
			.assertThat().contentType(ContentType.JSON)
			.assertThat().statusCode(200)
			.log().all();
	}
}
