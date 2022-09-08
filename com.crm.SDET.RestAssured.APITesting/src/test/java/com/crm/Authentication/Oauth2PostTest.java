package com.crm.Authentication;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Oauth2PostTest {

	@Test
	public void oauth2(){
		 Response resp = given()
				 			.formParam("client_id", "skybag")
				 			.formParam("client_secret", "473e1127492bdbf0593b80565b2a5bc3")
				 			.formParam("grant_type", "client_credentials")
				 			.formParam("redirect_uri", "http://skybag.com")
				 			.formParam("code", 3755)
				 			
				 		.when()
				 			.post("http://coop.apps.symfonycasts.com/token");
			
		String token = resp.jsonPath().get("access_token");
		
		given()
			.auth()
			.oauth2(token)
			.pathParam("USER_ID", 3755)
		.when()
			//.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/barn-unlock")
			//.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/toiletseat-down")
			//.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/chickens-feed")
			//.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/eggs-collect")
			.post("http://coop.apps.symfonycasts.com/api/{USER_ID}/eggs-count")
		
		.then()
			.log().all();
		
	}
}
