package com.crm.genericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

/**
 * 
 * @author INDRAJIT
 *
 */
public class DatabaseUtility {
	static Driver driverRef;
	static Connection connection;
	static ResultSet result;
	
	/**
	 * This method is used to connect to Database
	 * @param DBname
	 */
	public void connectToDB(String DBname)
	{
		try {
			driverRef=new Driver();
			DriverManager.registerDriver(driverRef);
			connection=DriverManager.getConnection(IConstants.DbUrl+DBname,IConstants.DBUsername,IConstants.DBPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to close the database connection
	 */
	public void closeDB()
	{
		try {
			connection.close();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * This method is used to execute query
	 * @param query
	 * @param columnNum
	 * @param expectedData
	 * @return
	 * @throws SQLException
	 */
	public boolean executeQuery(String query,int columnNum,String expectedData) throws SQLException
	{
		result=connection.createStatement().executeQuery(query);
		boolean flag=false;
		while(result.next())
		{
			if(result.getString(columnNum).equalsIgnoreCase(expectedData))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("data is present in Database");
			return flag;
		}else {
			System.out.println("data is not present in Database");
			return flag;
		}
	}
	
	/**
	 * This method is used to perform execute update
	 * @param query
	 * @throws SQLException
	 */
	public void executeUpdate(String query) throws SQLException
	{
		int res = connection.createStatement().executeUpdate(query);
		if(res==1)
		{
			System.out.println("data is updated");
		}else {
			System.out.println("data is not updated");
		}
	}
}
