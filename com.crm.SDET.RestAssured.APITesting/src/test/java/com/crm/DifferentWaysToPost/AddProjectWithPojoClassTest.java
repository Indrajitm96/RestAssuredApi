package com.crm.DifferentWaysToPost;

import org.testng.annotations.Test;

import com.crm.PojoClass.ProjectLibrary;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class AddProjectWithPojoClassTest {

	@Test
	public void addProject() {
		
		ProjectLibrary obj=new ProjectLibrary("sachin", "marriage", "created", 2);
		
		given()
			.body(obj).contentType(ContentType.JSON)
		.when()
			.post("http://localhost:8084/addProject")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(201)
			.log().all();
	}
}
