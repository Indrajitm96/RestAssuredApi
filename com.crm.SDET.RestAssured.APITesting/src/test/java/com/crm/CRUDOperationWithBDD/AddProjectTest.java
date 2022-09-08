package com.crm.CRUDOperationWithBDD;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;



public class AddProjectTest {
@Test
public void Create() {
	
	//json Object
	JSONObject obj=new JSONObject();
	obj.put("createdBy", "Ganesha");
	obj.put("projectName", "festivals");
	obj.put("status", "On Going");
	obj.put("teamSize", 50);
	
	
	given()   //preconditions
		.contentType(ContentType.JSON)
		.body(obj)
	.when()   //request
		.post("http://localhost:8084/addProject")
	.then()    //response
		.assertThat().contentType(ContentType.JSON)
		.assertThat().statusCode(201)
		.log().all();
		
	
}
}
