package com.crm.genericUtilities;

import java.sql.SQLException;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
/**
 * 
 * @author INDRAJIT
 *
 */
public class BaseApiClass {

	DatabaseUtility dLib=new DatabaseUtility();
	
	
	@BeforeSuite
	public void bsConfig() {
		dLib.connectToDB("projects");
		
	}
	
	@BeforeClass
	public void validate(String projectId,int colNum,String query) throws SQLException {
		dLib.executeQuery(query, colNum, projectId);
	}
	
	@AfterClass
	public void update(String query) throws SQLException {
		dLib.executeUpdate(query);
	}
	
	@AfterSuite
	public void asConfig() {
		dLib.closeDB();
	}

}
