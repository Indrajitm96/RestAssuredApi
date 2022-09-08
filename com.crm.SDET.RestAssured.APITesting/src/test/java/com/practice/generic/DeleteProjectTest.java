package com.practice.generic;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;

import org.testng.annotations.Test;

import com.crm.genericUtilities.EndPointsLibrary;
import com.crm.genericUtilities.RestAssuredLibrary;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class DeleteProjectTest {

	@Test
	public void delete() {
		baseURI="http://localhost";
		port=8084;
		
		//get the project
		Response resp = when()
			.get(EndPointsLibrary.getSingleProject+"TY_PROJ_1206");
		resp.then().assertThat().statusCode(200).log().all();
		
		//capture the project id
		RestAssuredLibrary rLib=new RestAssuredLibrary();
		String pId = rLib.getJsonData(resp, "projectId");
		
		//delete the project
		given()
			.pathParam("proId", pId)
		.when()
			.delete(EndPointsLibrary.deleteProject+"{proId}")
		.then()
			.assertThat().statusCode(204)
			.log().all();
	}
}
