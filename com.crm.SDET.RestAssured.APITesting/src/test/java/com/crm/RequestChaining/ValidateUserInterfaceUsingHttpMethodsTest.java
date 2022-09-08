package com.crm.RequestChaining;

import static io.restassured.RestAssured.given;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.crm.PojoClass.ProjectLibrary;
import com.crm.genericUtilities.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ValidateUserInterfaceUsingHttpMethodsTest {

	@Test
	public void Validatedatabase() throws SQLException {
		//create a project
		JavaUtility jLib=new JavaUtility();
		ProjectLibrary pojo=new ProjectLibrary("Sanjay", "Parachute"+jLib.getRandomNumber(), "created", 100);
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
		ProjectLibrary pojo1=new ProjectLibrary("Andy Jassy", "Amazon"+jLib.getRandomNumber(), "created", 100);
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
		
		//validate UI
		WebDriver driver=null;
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		driver.get("http://localhost:8084");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.findElement(By.xpath("//a[@href='/dashboard/projects']")).click();
		
		List<WebElement> alldatas = driver.findElements(By.xpath("//table/tbody/tr/td[1]"));

		for(WebElement webelement:alldatas)
		{
			String allprojects=webelement.getText();
			if(allprojects.contains(prId1))
			{
				System.out.println("project is not deleted");
			}
			else {
				System.out.println("Project is deleted");
				break;
			}
		}
		
		driver.findElement(By.xpath("//a[.='Logout']")).click();
		driver.quit();
		
}
}
