package com.crm.CRUDOperationWithBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class UpdateProjectTest {

	@Test
	public void UpdateProject() {
	
		//json object
		JSONObject obj=new JSONObject();
		obj.put("createdBy", "Chanchal");
		obj.put("projectName", "ReelsIdea");
		obj.put("status", "created");
		obj.put("teamSize", 12);
		
		given()			//preconditions
			.contentType(ContentType.JSON)
			.body(obj)
		.when()			//request
			.put("http://localhost:8084/projects/TY_PROJ_1003")
		.then()			//response
			.assertThat().contentType(ContentType.JSON)
			.assertThat().statusCode(200)
			.log().all();
	}
}
