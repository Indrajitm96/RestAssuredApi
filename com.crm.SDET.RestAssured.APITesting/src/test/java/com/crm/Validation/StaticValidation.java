package com.crm.Validation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class StaticValidation {

	@Test
	public void GetStaticResponse() {
		//prerequisites
		String expdata="TY_PROJ_005";
		
		//action
		Response resp = when().get("http://localhost:8084/projects");
		
		//validation
		String actData = resp.jsonPath().get("[2].projectId");
		Assert.assertEquals(actData, expdata);
		System.out.println("data verified");
		resp.then().log().all();
		
	}
}
