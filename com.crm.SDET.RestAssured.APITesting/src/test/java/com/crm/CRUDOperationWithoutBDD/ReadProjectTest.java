package com.crm.CRUDOperationWithoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ReadProjectTest {

	@Test
	public void getProjects() {
		
		//request
		RequestSpecification reqspec = RestAssured.given();
		reqspec.contentType(ContentType.JSON);
		
		//action
		Response response = reqspec.get("http://localhost:8084/projects");

		
		System.out.println(response.getContentType());
		System.out.println(response.statusCode());
		System.out.println(response.statusLine());
		System.out.println(response.getTime());
		
		
		ValidatableResponse validate = response.then();
		validate.assertThat().contentType(ContentType.JSON);
		validate.assertThat().statusCode(200);
		validate.log().all();
	}
}
