package com.practice.generic;

import org.testng.annotations.Test;

import com.crm.genericUtilities.EndPointsLibrary;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class GetAllProjectsTest {

	@Test
	public void getAllProjects() {
		baseURI="http://localhost";
		port=8084;
	
		when()
			.get(EndPointsLibrary.getAllProjects)
		.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.log().all();
	}
}
