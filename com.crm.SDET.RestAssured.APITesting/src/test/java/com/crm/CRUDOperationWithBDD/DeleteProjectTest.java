package com.crm.CRUDOperationWithBDD;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class DeleteProjectTest {

	@Test
	public void DeleteProject() {
		
		given()			//preconditions
			.contentType(ContentType.JSON)
		.when()			//request
			.delete("http://localhost:8084/projects/TY_PROJ_1002")
		.then()			//response
			.assertThat().contentType(ContentType.JSON)
			.assertThat().statusCode(204)
			.log().all();
	}
}
