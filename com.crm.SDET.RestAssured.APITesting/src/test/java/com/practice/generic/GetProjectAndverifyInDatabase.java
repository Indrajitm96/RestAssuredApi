package com.practice.generic;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.when;

import java.sql.SQLException;

import org.testng.annotations.Test;

import com.crm.genericUtilities.BaseApiClass;
import com.crm.genericUtilities.EndPointsLibrary;
import com.crm.genericUtilities.RestAssuredLibrary;

import io.restassured.response.Response;
/**
 * 
 * @author INDRAJIT
 *
 */
public class GetProjectAndverifyInDatabase {

	@Test
	public void getProjectAndVerify() throws SQLException {
		baseURI="http://localhost";
		port=8084;
		
		//request to get a project
		Response resp = when()
							.get(EndPointsLibrary.getSingleProject+"TY_PROJ_1204");
		
		//capture the projectId
		RestAssuredLibrary rLib=new RestAssuredLibrary();
		String proId = rLib.getJsonData(resp, "projectId");
		
		//validate in database
		BaseApiClass bLib=new BaseApiClass();
		bLib.bsConfig();
		String query="select * from project";
		bLib.validate(proId,1, query);
		bLib.asConfig();
		
		
	}
}
