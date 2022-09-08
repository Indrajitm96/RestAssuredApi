package com.crm.ReqresApiTesting;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ReqresDeleteTest {

	@Test
	public void Delete() {
		//request
		when()
			.delete("https://reqres.in/api/users/2")
		//response validation
		.then()
			.assertThat().statusCode(204)
			.log().all();
	}
}
