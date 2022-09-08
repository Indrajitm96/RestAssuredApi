package com.crm.RequestChaining;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostAndPutTest {

	@Test
	public void RsqtChngPostNPut() {
		//create a project using pojo
		JavaUtility jLib=new JavaUtility();
		ProjectLibrary pojoLib=new ProjectLibrary("Karan", "Viron"+jLib.getRandomNumber(), "created", 100);

		Response resp = given()
							.body(pojoLib)
							.contentType(ContentType.JSON)
						.when()
							.post("http://localhost:8084/addProject");

		//capture the projectId
		String proId = resp.jsonPath().get("projectId");
		System.out.println(proId);
		resp.then().log().all();
		
		//update the newly created project
		ProjectLibrary pLib=new ProjectLibrary("Parampara", "Cars"+jLib.getRandomNumber(), "On going", 70);
		given()
			.contentType(ContentType.JSON)
			.body(pLib)
			.pathParam("prId", proId)
		.when()
			.put("http://localhost:8084/projects/{prId}")
		.then()
			.assertThat().statusCode(200)
			.log().all();
	}
}
