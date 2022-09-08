package com.crm.RequestChaining;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetAndDeleteTest {

	@Test
	public void GetAndDelete() {
		 Response resp = given()
				 			.contentType(ContentType.JSON)	
				 		.when()
				 			.get("http://localhost:8084/projects/TY_PROJ_1205");
		 
		String proId = resp.jsonPath().get("projectId");
		resp.then().log().all();
		
		given()
			.pathParam("pId", proId)
		.when()
			.delete("http://localhost:8084/projects/{pId}")
		.then()
			.assertThat().statusCode(204);
		
		
		
	}
}
