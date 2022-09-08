package com.crm.CRUDOperationWithoutBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CreateProjectTest {

	@Test
	public void Create() {
		
	//json body
	JSONObject obj=new JSONObject();
	obj.put("createdBy", "Indra");
	obj.put("projectName", "Hp Laptop");
	obj.put("status", "Created");
	obj.put("teamSize", 8);
	
	//preconditions(body and content type) or request
	RequestSpecification reqspc = RestAssured.given();
	reqspc.body(obj);
	reqspc.contentType(ContentType.JSON);
	
	//action
	Response response = reqspc.post("http://localhost:8084/addProject");
	
	//validate
	ValidatableResponse validate = response.then();
	validate.assertThat().contentType(ContentType.JSON);
	validate.assertThat().statusCode(201);
	validate.log().all();
	
	}
}
