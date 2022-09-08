package com.crm.ReqresApiTesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class ReqresPostTest {

	@Test
	public void Create() {
		
		//json object
		JSONObject obj=new JSONObject();
		obj.put("name","morpheus");
		obj.put("job","leader");
		
		//prerequisites
		given()
			.contentType(ContentType.JSON)
			.body(obj)
		//request
		.when()
			.post("https://reqres.in/api/users")
		//response validation
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(201)
			.log().all();
	}
}
