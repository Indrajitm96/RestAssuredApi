package com.crm.ReqresApiTesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ReqresPutTest {

	@Test
	public void Update() {
		//json onject
		JSONObject obj=new JSONObject();
		obj.put("name", "morpheus");
		obj.put("job", "zion resident");
		
		//prerequisites
		given()
			.contentType(ContentType.JSON)
			.body(obj)
		//request
		.when()
			.put("https://reqres.in/api/users/2")
		//response validation
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(200)
			.log().all();
	}
}
