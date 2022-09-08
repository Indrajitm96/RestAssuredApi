package com.crm.PostMultipleDataUsingDataprovider;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.ExcelUtility;

import io.restassured.http.ContentType;

public class AddProjectUsingDataFromExcelTest extends ExcelUtility {

	
	@Test(dataProvider = "bodyData")
	public void create(String createdBy,String projectName,String status,int teamSize) {
		ProjectLibrary pojo=new ProjectLibrary(createdBy, projectName, status, teamSize);

		//File file=new File("/src/test/resources/Datas.xlsx");
		
		given()
			.contentType(ContentType.JSON)
			.body(pojo)
		.when()
			.post("http://localhost:8084/addProject")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(201)
			.log().all();
		
	}
	
	@DataProvider
	public Object[][] bodyData() throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, IOException{
		ExcelUtility elib=new ExcelUtility();
		
		Object[][] objArr=new Object[4][4];
		
		objArr[0][0]=elib.readDataFromExcel("Datas", 1, 0);
		objArr[0][1]=elib.readDataFromExcel("Datas", 1, 1);
		objArr[0][2]=elib.readDataFromExcel("Datas", 1, 2);
		objArr[0][3]=elib.readDataFromExcel("Datas", 1, 3);
		
		objArr[1][0]=elib.readDataFromExcel("Datas", 2, 0);
		objArr[1][1]=elib.readDataFromExcel("Datas", 2, 1);
		objArr[1][2]=elib.readDataFromExcel("Datas", 2, 2);
		objArr[1][3]=elib.readDataFromExcel("Datas", 2, 3);
		
		objArr[2][0]=elib.readDataFromExcel("Datas", 3, 0);
		objArr[2][1]=elib.readDataFromExcel("Datas", 3, 1);
		objArr[2][2]=elib.readDataFromExcel("Datas", 3, 2);
		objArr[2][3]=elib.readDataFromExcel("Datas", 3, 3);
		
		objArr[3][0]=elib.readDataFromExcel("Datas", 4, 0);
		objArr[3][1]=elib.readDataFromExcel("Datas", 4, 1);
		objArr[3][2]=elib.readDataFromExcel("Datas", 4, 2);
		objArr[3][3]=elib.readDataFromExcel("Datas", 4, 3);
		
		return objArr;
	}
}
