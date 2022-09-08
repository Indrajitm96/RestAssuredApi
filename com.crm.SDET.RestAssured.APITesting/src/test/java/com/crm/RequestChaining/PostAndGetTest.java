package com.crm.RequestChaining;

import org.testng.annotations.Test;

import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PostAndGetTest {

	@Test
	public void RqstchngPostNGet() {
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
		
		//create a get request to get the newly created project using path parameter
		given()
			.pathParam("prId", proId)
		.when()
			.get("http://localhost:8084/projects/{prId}")
		.then()
			.assertThat().statusCode(200)
			.log().all();
	}
}
