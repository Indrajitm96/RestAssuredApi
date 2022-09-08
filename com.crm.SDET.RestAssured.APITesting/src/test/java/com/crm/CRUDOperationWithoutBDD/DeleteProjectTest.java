package com.crm.CRUDOperationWithoutBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


public class DeleteProjectTest {

	@Test
	public void deleteProject() {
		//request
		Response response = RestAssured.delete("http://localhost:8084/projects/TY_PROJ_603");
		ValidatableResponse validate = response.then();
		
		validate.assertThat().statusCode(204);
		validate.log().all();
	}
}
