package com.crm.Authentication;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class BearerTokenTest {

	@Test
	public void BearerToken() {
		baseURI="https://api.github.com/";
		
		
		/*
		 * JSONObject obj=new JSONObject(); obj.put("name", "Ty_repository");
		 * 
		 * given() .auth() .oauth2("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")
		 * .body(obj) .when() .post("/user/repos") .then() .log().all();
		 */
		 
		
		given()
			.auth()
			.oauth2("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")
		.when()
			.get("/user/repos")
		.then()
			.log().all();
		
	}
}
