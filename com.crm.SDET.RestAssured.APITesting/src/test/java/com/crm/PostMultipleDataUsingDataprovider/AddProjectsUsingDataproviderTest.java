package com.crm.PostMultipleDataUsingDataprovider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.PojoClass.ProjectLibrary;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class AddProjectsUsingDataproviderTest {

	@Test(dataProvider = "bodyData")
	public void create(String createdBy,String projectName,String status,int teamSize) {
		ProjectLibrary pojo=new ProjectLibrary(createdBy, projectName, status, teamSize);
		
		given()
			.body(pojo)
			.contentType(ContentType.JSON)
		.when()
			.post("http://localhost:8084/addProject")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(201)
			.log().all();
		
	}
	
	@DataProvider
	public Object[][] bodyData() {
		Object[][] objArray=new Object[4][4];
		objArray[0][0]="Amit";
		objArray[0][1]="Bag";
		objArray[0][2]="created";
		objArray[0][3]=20;
		
		objArray[1][0]="Kamal";
		objArray[1][1]="Hat";
		objArray[1][2]="created";
		objArray[1][3]=10;
		
		objArray[2][0]="Shibani";
		objArray[2][1]="Dress";
		objArray[2][2]="On Going";
		objArray[2][3]=50;
		
		objArray[3][0]="Mousami";
		objArray[3][1]="Jwellery";
		objArray[3][2]="Completed";
		objArray[3][3]=100;
		
		return objArray;
		
		
	}
}
