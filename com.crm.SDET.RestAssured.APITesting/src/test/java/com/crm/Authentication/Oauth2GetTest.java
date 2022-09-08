package com.crm.Authentication;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Oauth2GetTest {

	@Test
	public void oauth2Get() {
		Response resp = given()
							.formParam("client_id", "skybag")
							.formParam("client_secret", "473e1127492bdbf0593b80565b2a5bc3")
							.formParam("response_type", 3755)
							.formParam("redirect_uri", "http://skybag.com")
							.formParam("scope", "barn-unlock")
						.when()
							.get("http://coop.apps.symfonycasts.com/authorize");
		
		String token=resp.jsonPath().get("access_token");
		System.out.println(token);
		
		given()
			.auth()
			.oauth2(token)
			.pathParam("USER_ID", 3755)
		.when()
			.get("http://coop.apps.symfonycasts.com/api/{USER_ID}")
		.then()
			.log().all();
	}
}
