package com.crm.RequestChaining;

import org.testng.annotations.Test;

import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.JavaUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;



public class PostGetPutAndDeleteTest {

	@Test
	public void PostGetPutAndDelete() {
		//create a project
		JavaUtility jLib=new JavaUtility();
		ProjectLibrary pojo=new ProjectLibrary("Adani", "stocks"+jLib.getRandomNumber(), "created", 100);
		Response resp = given()
				.contentType(ContentType.JSON)
				.body(pojo)
				.when()
				.post("http://localhost:8084/addProject");

		//capture the project
		String prId1 = resp.jsonPath().get("projectId");
		System.out.println(prId1);
		resp.then().assertThat().statusCode(201).log().all();

		//get the created project
		 given()
			.contentType(ContentType.JSON)
			.pathParam("pId1", prId1)
		.when()
			.get("http://localhost:8084/projects/{pId1}")
			.then().assertThat().statusCode(200).log().all();


		//update the getting project
		ProjectLibrary pojo1=new ProjectLibrary("Dara Khosrowshahi", "Ubber"+jLib.getRandomNumber(), "created", 100);
		given()
			.contentType(ContentType.JSON)
			.body(pojo1)
			.pathParam("pId2", prId1)
		.when()
			.put("http://localhost:8084/projects/{pId2}")
		.then()
			.assertThat().statusCode(200).log().all();
		
		//delete the updated project
		given()
			.pathParam("pId3", prId1)
			.contentType(ContentType.JSON)
		.when()
			.delete("http://localhost:8084/projects/{pId3}")
		.then()
			.assertThat().statusCode(204);


	}
}
