package com.crm.CRUDOperationWithoutBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UpdateprojectTest {

	@Test
	public void updateProject() {
		
		//json body
		JSONObject obj=new JSONObject();
		obj.put("createdBy", "Amar");
		obj.put("projectName", "Twitter");
		obj.put("status", "On Going");
		obj.put("teamSize", 50);
		
		//request
		RequestSpecification reqspc = RestAssured.given();
		reqspc.body(obj);
		reqspc.contentType(ContentType.JSON);
		
		//action
		Response response = reqspc.put("http://localhost:8084/projects/TY_PROJ_1404");
		ValidatableResponse validate = response.then();
		validate.assertThat().contentType(ContentType.JSON);
		validate.assertThat().statusCode(200);
		validate.log().all();
		
	}
}
