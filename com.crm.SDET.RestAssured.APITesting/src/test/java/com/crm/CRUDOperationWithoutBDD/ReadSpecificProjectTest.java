package com.crm.CRUDOperationWithoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReadSpecificProjectTest {

	@Test
	public void getSpecificProject() {
		
		//request
		RequestSpecification reqspec = RestAssured.given();
		reqspec.contentType(ContentType.JSON);
		
		//action
		Response response = reqspec.get("http://localhost:8084/projects/TY_PROJ_1403");
		//ValidatableResponse validate = response.then();
//		validate.assertThat().contentType(ContentType.JSON);
//		validate.assertThat().statusCode(200);
		//validate.log().all();
		
		
		response.prettyPrint();
		
	}
}
