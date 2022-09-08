package com.crm.DifferentWaysToPost;

import static io.restassured.RestAssured.given;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class AddProjectWithHashMapTest {

	@Test
	public void Create() {
		
		
		//prerequisites
		HashMap hsmp=new HashMap();
		hsmp.put("createdBy", "Indrajit");
		hsmp.put("projectName", "Weekend");
		hsmp.put("status", "created");
		hsmp.put("teamSize", 4);
		
		/*
		 * Set keys = hsmp.keySet(); Collection value = hsmp.values();
		 * System.out.println(keys); System.out.println(value);
		 */
		
		given()
			.body(hsmp)
			.contentType(ContentType.JSON)
		
			//request action
		.when()
			.post("http://localhost:8084/addProject")
			
			//validation
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(201)
			.log().all();
			
	}
}
