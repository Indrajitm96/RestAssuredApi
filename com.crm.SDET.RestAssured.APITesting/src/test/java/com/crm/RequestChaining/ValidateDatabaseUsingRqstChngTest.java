package com.crm.RequestChaining;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;
import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.JavaUtility;
import com.mysql.jdbc.Driver;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidateDatabaseUsingRqstChngTest {
	@Test
	public void Validatedatabase() throws SQLException {
		//create a project
		JavaUtility jLib=new JavaUtility();
		ProjectLibrary pojo=new ProjectLibrary("Kabir", "Lipton"+jLib.getRandomNumber(), "created", 100);
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
		ProjectLibrary pojo1=new ProjectLibrary("Howard Schultz", "Starbucks"+jLib.getRandomNumber(), "created", 100);
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

		//validate database
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = connection.createStatement();
		String query = "select * from project";
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			if(prId1.equals(result.getString(1)))
			{
				System.out.println("Project not deleted");
			}
			else {
				System.out.println("Project deleted");
				break;
			}
		}
		connection.close();
	}

}
