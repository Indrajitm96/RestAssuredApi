package com.practice.generic;

import org.testng.annotations.Test;

import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.EndPointsLibrary;
import com.crm.genericUtilities.JavaUtility;
import com.crm.genericUtilities.RestAssuredLibrary;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UpateProjectTest {

	@Test
	public void update() {
		baseURI="http://localhost";
		port=8084;
		
		JavaUtility jLib=new JavaUtility();
		
		//get the project
		Response resp = when()
			.get(EndPointsLibrary.getSingleProject+"TY_PROJ_1208");
		resp.then().assertThat().statusCode(200).log().all();
		
		//capture the project id
		RestAssuredLibrary rLib=new RestAssuredLibrary();
		String pId = rLib.getJsonData(resp, "projectId");
		
		//update the project
		ProjectLibrary pojo=new ProjectLibrary("Priya", "Twitter"+jLib.getRandomNumber(), "created", 80);
		
		given()
			.body(pojo)
			.contentType(ContentType.JSON)
			.pathParam("proId",pId )
		.when()
			.put(EndPointsLibrary.updateProject+"{proId}")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(200)
			.log().all();
		
		
	}
}
