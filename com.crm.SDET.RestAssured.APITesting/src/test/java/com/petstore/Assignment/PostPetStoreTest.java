package com.petstore.Assignment;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostPetStoreTest {

	@Test
	public void postOrder() {
		baseURI="http://petstore.swagger.io/v2";
		
		JSONObject obj=new JSONObject();
		obj.put("id", 101);
		obj.put("petId", "P_ID_101");
		obj.put("quantity", 4);
		obj.put("shipDate","2022-09-06T10:53:52.219Z");
		obj.put("status", "placed");
		obj.put("complete", true);
		
		given()
			.contentType(ContentType.JSON)
			.body(obj)
			
		.when()
			.post("/store/order")
		.then()
			.log().all();
		  
	}
}
