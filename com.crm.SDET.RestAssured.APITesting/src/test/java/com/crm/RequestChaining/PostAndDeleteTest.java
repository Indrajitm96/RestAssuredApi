package com.crm.RequestChaining;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;


import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostAndDeleteTest {

	@Test
	public void PostAndDelete() {
		//create a project using pojo
		JavaUtility jLib=new JavaUtility();
		ProjectLibrary pojoLib=new ProjectLibrary("Kabita", "Lenovo"+jLib.getRandomNumber(), "created", 40);

		Response resp = given()
				.body(pojoLib)
				.contentType(ContentType.JSON)
				.when()
				.post("http://localhost:8084/addProject");

		//capture the projectId
		String proId = resp.jsonPath().get("projectId");
		System.out.println(proId);
		resp.then().log().all();
	}
}
