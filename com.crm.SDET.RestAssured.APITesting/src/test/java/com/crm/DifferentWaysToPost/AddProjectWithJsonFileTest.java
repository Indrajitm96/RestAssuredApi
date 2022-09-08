package com.crm.DifferentWaysToPost;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class AddProjectWithJsonFileTest {

	@Test
	public void Create() {
		//prerequisites
		File file=new File("./src/test/resources/RestAssuredJsonFile.json");
		
		given()
			.body(file)
			.contentType(ContentType.JSON)
			
			//request action
		.when()
			.post("http://localhost:8084/addProject")
			
			//validate
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(201)
			.log().all();
	}
}
