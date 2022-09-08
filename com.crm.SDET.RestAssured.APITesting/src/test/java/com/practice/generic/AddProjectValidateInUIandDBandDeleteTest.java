package com.practice.generic;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.BaseApiClass;
import com.crm.genericUtilities.EndPointsLibrary;
import com.crm.genericUtilities.IConstants;
import com.crm.genericUtilities.JavaUtility;
import com.crm.genericUtilities.RestAssuredLibrary;
import com.crm.genericUtilities.WebdriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.SQLException;
import java.util.List;

public class AddProjectValidateInUIandDBandDeleteTest {

	@Test
	public void AddProjectValidateInUIandDBandDelete() throws SQLException {
		baseURI="http://localhost";
		port=8084;
		
		WebdriverUtility wLib=new WebdriverUtility();
		JavaUtility jLib=new JavaUtility();
		
		//create a project
		ProjectLibrary pLib=new ProjectLibrary("Naresh", "Xerox"+jLib.getRandomNumber(), "created", 30);
		
		Response response = given()
								.body(pLib)
								.contentType(ContentType.JSON)
							.when()
								.post(EndPointsLibrary.createProject);
		
		response.then().assertThat().statusCode(201).log().all();
		//capture the projectid
		RestAssuredLibrary rLib=new RestAssuredLibrary();
		String proId = rLib.getJsonData(response, "projectId");
		
		//validate in RMGYantra 
		WebDriver driver=null;
			//open browser
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
			//enter url
		driver.get(baseURI+":"+port);
		wLib.waitTillPageGetsLoad(driver);
		wLib.maximizeTheWindow(driver);
		
			//login to rmgyantra
		driver.findElement(By.id("usernmae")).sendKeys(IConstants.appUserName);
		driver.findElement(By.id("inputPassword")).sendKeys(IConstants.appPassword);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
			//click on projects
		driver.findElement(By.xpath("//a[@href='/dashboard/projects']")).click();
		
		List<WebElement> alldatas = driver.findElements(By.xpath("//td[1]"));
		
		boolean flag=false;
		int count = 0;
		for(WebElement projectId:alldatas)
		{
			String allproject = projectId.getText();
			if(allproject.contains(proId))
			{
				flag=true;
				break;
			}
			count++;
		}
		
		if(flag==true)
		{
		Assert.assertTrue(flag);
		System.out.println("data is present in GUI");
		}else
		{
			System.out.println("Data is not prsent in GUI");
			Assert.assertTrue(false);
		}
		
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		driver.quit();
		
		//validation in database
		BaseApiClass bLib=new BaseApiClass();
		bLib.bsConfig();
		String query="select * from project";
		bLib.validate(proId,1, query);
		bLib.asConfig();
		
		//delete the project
		given()
			.pathParam("pId",proId )
		.when()
			.delete(EndPointsLibrary.deleteProject+"{pId}")
		.then()
			.assertThat().statusCode(204).time(Matchers.lessThan(2000L)).log().all();
		
		//validate in rmgyantra
		
		//open browser
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	
		//enter url
	driver.get(baseURI+":"+port);
	wLib.waitTillPageGetsLoad(driver);
	wLib.maximizeTheWindow(driver);
	
		//login to rmgyantra
	driver.findElement(By.id("usernmae")).sendKeys(IConstants.appUserName);
	driver.findElement(By.id("inputPassword")).sendKeys(IConstants.appPassword);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
		//click on projects
	driver.findElement(By.xpath("//a[@href='/dashboard/projects']")).click();
	
	List<WebElement> alldatas1 = driver.findElements(By.xpath("//td[1]"));
	
	boolean flag1=true;
	int count1 = 0;
	for(WebElement webelement:alldatas1)
	{
		String allprojects=webelement.getText();
		if(allprojects.contains(proId))
		{
			flag1=false;
			break;
			
		}
		count1++;
	}
		if(flag1==true)
		{
			Assert.assertTrue(true);
			System.out.println("Data is not present in GUI");
		}
		else {
			System.out.println("Data is present in GUI");
			Assert.assertTrue(false);
			
		}
	
	driver.findElement(By.xpath("//a[.='Logout']")).click();
	driver.quit();
	
	//validation in database
			bLib.bsConfig();
			bLib.validate(proId,1, query);
			bLib.asConfig();
		
	}
}
