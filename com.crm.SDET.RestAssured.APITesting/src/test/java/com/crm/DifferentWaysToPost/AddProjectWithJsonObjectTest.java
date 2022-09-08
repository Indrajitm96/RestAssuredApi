package com.crm.DifferentWaysToPost;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class AddProjectWithJsonObjectTest {

	@Test
	public void addPeoject() {
		//json Object
		JSONObject obj=new JSONObject();
		obj.put("createdBy", "Kalpana");
		obj.put("projectName", "visit");
		obj.put("status", "On Going");
		obj.put("teamSize", 2);
		
		given()
			.body(obj).contentType(ContentType.JSON)
		.when()
			.post("http://localhost:8084/addProject")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(201)
			.log().all();
	}
	
}
