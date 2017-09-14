package com.revature.home.SeleniumTest;



import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

//set testing site using a chrome browser
public class ChromeTest extends DriverSetup{
	
	@Before
	public void setup(){
		driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		driver.setJavascriptEnabled(true);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://13.59.180.148:8080/index.html");
		URL = driver.getCurrentUrl();
	}
	
	@Test
	public void startBrowser(){
		driver.get("http://13.59.180.148:8080/index.html");
		String domain = (String) driver.executeScript("return document.domain");
		System.out.println("Domain = " + domain);
		
	}
	
	@Test
	public void loginAsAdmin(){
		driver.get("http://13.59.180.148:8080/index.html#/login");
		//sign in user with admin privaleges
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys("pMuldoon20");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[1]")).sendKeys("password!");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[2]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//find elements that should be on home page after login
		driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div > div > div:nth-child(1) > h1"));
		driver.findElement(By.cssSelector("#myCarousel > div"));
		driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-9\"]/ul[1]/li[2]/a"));
	}
	
	@Test
	public void goToCalendar(){
		driver.get(URL + "#/home");
		driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-9\"]/ul[1]/li[2]/a")).click();
	}
	

	
}