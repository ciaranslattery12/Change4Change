package com.revature.home.SeleniumTest;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class EdgeTest extends DriverSetup{
	@Before
	public void setup(){
		driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER); //option #2:FIREFOX_38
		driver.setJavascriptEnabled(true);
		driver.get("http://13.59.180.148:8080/index.html#/login");
//		URL = driver.getCurrentUrl();
	}
	
	@Test
	public void loginToPage(){
		driver.get("http://13.59.180.148:8080/index.html#/login");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys("guy1");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[1]")).sendKeys("guyguy123");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[2]")).click();
		
//		System.out.println("url = " + driver.getCurrentUrl());
		
		driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-9']/ul[2]/li[1]/a"));
		driver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-9\"]/ul[2]/li[1]/a"));
	}
}
