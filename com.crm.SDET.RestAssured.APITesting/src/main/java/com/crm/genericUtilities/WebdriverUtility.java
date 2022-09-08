package com.crm.genericUtilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author INDRAJIT
 *
 */
public class WebdriverUtility {

	/**
	 * This method is used to maximize the window
	 * 
	 * @param driver
	 */
	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to minimize the browser
	 * 
	 * @param driver
	 *//*
		 * public void minimizeTheBrowser(WebDriver driver) {
		 * driver.manage().window().minimize(); }
		 */
	/**
	 * This method is used to refresh the webpage
	 * 
	 * @param driver
	 */
	public void refreshTheBrowser(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * This method is used to get back to the previous page
	 * 
	 * @param driver
	 */
	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * This method is used to get forward to next page
	 * 
	 * @param driver
	 */
	public void forwardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}

	/**
	 * This method will wait till the page get loaded
	 * 
	 * @param driver
	 */
	public void waitTillPageGetsLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * This method will wait element to click
	 * 
	 * @param driver
	 * @param element
	 */
	
	/*
	 * public void waitTillElementToClick(WebDriver driver,WebElement element) {
	 * WebDriverWait wait=new
	 * WebDriverWait(driver,Duration.ofSeconds(IConstants.explicitWaitDuration));
	 * wait.until(ExpectedConditions.elementToBeClickable(element)); }
	 */
	  
	 /**
		 * This method will wait till element is visible
		 * 
		 * @param driver
		 * @param element
		 */
	/*
	 * public void waiTillElementToVisible(WebDriver driver,WebElement element) {
	 * WebDriverWait wait=new WebDriverWait(driver,
	 * Duration.ofSeconds(IConstants.explicitWaitDuration));
	 * wait.until(ExpectedConditions.visibilityOf(element)); }
	 * 
	 *//**
		 * This method will wait for the title of the page
		 * 
		 * @param driver
		 * @param title
		 *//*
			 * public void waitTillPageLoadTitleDriver(WebDriver driver,String title) {
			 * WebDriverWait wait=new WebDriverWait(driver,
			 * Duration.ofSeconds(IConstants.explicitWaitDuration));
			 * wait.until(ExpectedConditions.titleContains(title)); }
			 */

	/**
	 * This method will wait until the page url is loaded
	 * 
	 * @param driver
	 * @param URL
	 */
	/*
	 * public void waitTillPageLoadURL(WebDriver driver, String URL) { WebDriverWait
	 * wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(IConstants.explicitWaitDuration));
	 * wait.until(ExpectedConditions.urlContains(URL)); }
	 * 
	 *//**
		 * This method will ignore the noSuchElementException for the particular
		 * webElement
		 * 
		 * @param driver
		 */
	/*
	 * public void ignoreNoSuchElementException(WebDriver driver) { WebDriverWait
	 * wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(IConstants.explicitWaitDuration));
	 * wait.ignoring(NoSuchElementException.class); }
	 * 
	 *//**
		 * This method will wait till the alert message to be visible
		 * 
		 * @param driver
		 *//*
			 * public void waitForAlertMsg(WebDriver driver) { WebDriverWait wait = new
			 * WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
			 * wait.until(ExpectedConditions.alertIsPresent()); }
			 */

	/**
	 * This method is used to switch to frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void swithToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to switch to frame using id
	 * 
	 * @param driver
	 * @param id
	 */
	public void switchToFrame(WebDriver driver, String id) {
		driver.switchTo().frame(id);
	}

	/**
	 * This method is used to switch to frame using element
	 * 
	 * @param driver
	 * @param element
	 */
	public void switchtoframe(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to switch to main frame
	 * 
	 * @param driver
	 */
	public void switchToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to select a element in a dropdown using index
	 * 
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to select a element in a dropdown using value
	 * 
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
		System.out.println(select.getOptions());

	}

	/**
	 * This method is used to select a element in a dropdown using text
	 * 
	 * @param text
	 * @param element
	 */
	public void select(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method is used to fetch all the options from dropdown
	 * 
	 * @param element
	 */
	public String getAllOptionsFromDropDown(WebElement element) {
		Select select = new Select(element);
		List<WebElement> option = select.getOptions();
		for (WebElement webElement1 : option) {
			String text = webElement1.getText();
			System.out.println(text);
		}
		return null;
	}

	/**
	 * This method is used to mouseover on element using action class
	 * 
	 * @param driver
	 * @param element
	 */
	public void mouseOverOnElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method is used to right click on the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * This method is used to double click on the element
	 * 
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to click on enter button
	 * 
	 * @param driver
	 */
	public void clickOnEnterKey(WebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}

	/**
	 * This method is used to take screenshot
	 * 
	 * @param driver
	 * @param screenShotName
	 */
	public static String takeScreenShot(WebDriver driver, String screenShotName) {
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File src = takescreenshot.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshots/" + screenShotName + ".PNG");
		try {
			FileUtils.copyFile(src, dst);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return screenShotName;
	}

	/**
	 * This method is used to customized the wait
	 * 
	 * @param driver
	 */
	public void waitAndClickUsingCustomWait(WebDriver driver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofSeconds(10));
		wait.ignoring(NoSuchElementException.class);
		try {
			wait.wait(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * this method is used for custom wait
	 * 
	 * @param element
	 * @param duration
	 * @param pollingTime
	 */
	public void waitAndClick(WebElement element, int duration, long pollingTime) {
		int count = 0;
		while (count < duration) {
			try {
				element.click();
				break;
			} catch (Exception e) {

				try {
					Thread.sleep(pollingTime);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				count++;
			}
		}
	}

	/**
	 * This method is used to switch to window using title
	 * 
	 * @param driver
	 * @param actualTitle
	 */
	public void switchToWindow(WebDriver driver, String actualTitle) {
		Set<String> set = driver.getWindowHandles();
		for (String string : set) {
			driver.switchTo().window(string);
			String title = driver.getTitle();
			if (title.contains(actualTitle)) {
				break;
			}

		}
	}

	/**
	 * This method is used to switch to window using url
	 * 
	 * @param actualURL
	 * @param driver
	 */
	public void switchToWindowUrl(String actualURL, WebDriver driver) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String wid = it.next();
			driver.switchTo().window(wid);
			String url = driver.getCurrentUrl();
			if (url.contains(actualURL)) {
				break;
			}
		}
	}

	/**
	 * This method is used to switch alert popup and accept the popup
	 * 
	 * @param driver
	 * @param expectedMsg
	 */
	public void switchToAlertPopupAndAccept(WebDriver driver, String expectedMsg) {
		Alert alert = driver.switchTo().alert();
		if (alert.getText().trim().equalsIgnoreCase(expectedMsg.trim())) {
			System.out.println("alert msg is verified");
		} else {
			System.out.println("alert msg is not verified");
		}
		alert.accept();
	}

	/**
	 * This method is used to switch alert popup and dismiss the popup
	 * 
	 * @param driver ----` `param expectedMsg
	 */
	public void switchToAlertPopupAndDismiss(WebDriver driver, String expectedMsg) {
		Alert alert = driver.switchTo().alert();
		if (alert.getText().trim().equalsIgnoreCase(expectedMsg.trim())) {
			System.out.println("alert msg is verified");
		} else {
			System.out.println("alert msg is not verified");
		}
		alert.dismiss();
	}

	/**
	 * This method is used to click on enter by keyboard
	 * 
	 * @throws AWTException
	 */
	public void keyBoardOperationsEnter() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * This method is used to click on Tab by keyboard
	 * 
	 * @throws AWTException
	 */
	public void keyBoardOperationsTab() throws AWTException {
		Robot p = new Robot();
		p.keyPress(KeyEvent.VK_TAB);
		p.keyRelease(KeyEvent.VK_TAB);
	}

	/**
	 * This method is used to paste by keyboard
	 * 
	 * @throws AWTException
	 */
	public void keyBoardOperationsPaste() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}
}
