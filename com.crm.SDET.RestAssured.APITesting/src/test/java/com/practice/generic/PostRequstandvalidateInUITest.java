package com.practice.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.EndPointsLibrary;
import com.crm.genericUtilities.IConstants;
import com.crm.genericUtilities.JavaUtility;
import com.crm.genericUtilities.RestAssuredLibrary;
import com.crm.genericUtilities.WebdriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class PostRequstandvalidateInUITest {

	@Test
	public void postRequest() {
		baseURI="http://localhost";
		port=8084;
		JavaUtility jLib=new JavaUtility();
		
		//create a request
		ProjectLibrary pLib=new ProjectLibrary("swapna", "Furniture"+jLib.getRandomNumber(), "On Going", 13);
		
		Response resp = given()
							.body(pLib)
							.contentType(ContentType.JSON)
						.when()
							.post(EndPointsLibrary.createProject);
		
		//capture the json data
		RestAssuredLibrary rLib=new RestAssuredLibrary();
		String proId = rLib.getJsonData(resp,"projectId");
		
		given()
			.pathParam("pId", proId)
		.when()
			.get(EndPointsLibrary.getSingleProject+"{pId}")
		.then()
			.assertThat().contentType(ContentType.JSON).statusCode(200)
			.log().all();
		
		//for validating user interface
		WebDriver driver=null;
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		driver.get(baseURI+":"+port);
		WebdriverUtility wLib=new WebdriverUtility();
		wLib.maximizeTheWindow(driver);
		wLib.waitTillPageGetsLoad(driver);
		
		driver.findElement(By.id("usernmae")).sendKeys(IConstants.appUserName);
		driver.findElement(By.id("inputPassword")).sendKeys(IConstants.appPassword);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("//a[@href='/dashboard/projects']")).click();
		
		List<WebElement> alldatas = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));
		for(WebElement webelement:alldatas)
		{
			String allprojects=webelement.getText();
			if(allprojects.contains(proId))
			{
				System.out.println("project is present");
			}
			else {
				System.out.println("Project is not present");
				break;
			}
		}
		
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		driver.quit();
	}
}
