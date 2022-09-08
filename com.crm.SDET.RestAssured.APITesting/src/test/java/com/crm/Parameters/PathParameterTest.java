package com.crm.Parameters;

import org.testng.annotations.Test;


import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;


public class PathParameterTest {

	@Test
	public void PathParameter() {
		//prerequisites
		given()
			.pathParam("pId","TY_PROJ_1208" )
		//request
		.when()
			.get("http://localhost:8084/projects/{pId}")
		//response
		.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.log().all();
		
	}
}
