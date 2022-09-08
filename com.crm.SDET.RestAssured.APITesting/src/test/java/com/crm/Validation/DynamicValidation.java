package com.crm.Validation;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class DynamicValidation {

	@Test
	public void GetDynamicresponse() {
		//prerequisites
		String expData="TY_PROJ_1004";
		
		//action
		Response resp = when().get("http://localhost:8084/projects");
		
		//Validation
		boolean flag=false;
		List<String> pIds = resp.jsonPath().get("projectId");
		
		for(String projectId:pIds)
		{
			if(projectId.equalsIgnoreCase(expData))
			{
				flag=true;
			}
		}
		
		Assert.assertTrue(flag);
		System.out.println("data Verified");
		
		resp.then().log().all();
		
	}
}
