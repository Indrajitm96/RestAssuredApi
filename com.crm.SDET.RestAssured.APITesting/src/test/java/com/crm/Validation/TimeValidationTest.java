package com.crm.Validation;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class TimeValidationTest {

	@Test
	public void Time() {
		//prerequisites
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:8084/projects")
		.then()
			.assertThat().time(Matchers.lessThan(3000L),TimeUnit.MILLISECONDS)
			.log().all();
	}
}
