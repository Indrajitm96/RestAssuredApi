package com.crm.RequestChaining;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;


import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAndPutTest {

	@Test
	public void GetAndPut() {
		//create a project
		Response resp = given()
							.contentType(ContentType.JSON)
						.when()
							.get("http://localhost:8084/projects");
		
		//capture the project
		String proId=resp.jsonPath().get("[11].projectId");
		System.out.println(proId);
		resp.then().log().all();
		
		//create a request
		JavaUtility jLib=new JavaUtility();
		ProjectLibrary pojo=new ProjectLibrary("Rakesh", "nivea"+jLib.getRandomNumber(), "On Going", 30);
		given()
			.body(pojo)
			.contentType(ContentType.JSON)
			.pathParam("pId", proId)
		.when()
			.put("http://localhost:8084/projects/{pId}")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(200)
			.log().all();
		
		
		
	}
}
