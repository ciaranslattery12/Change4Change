package com.revature.home.SeleniumTest;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class ServerTest {

//	   @Test
//	   public void openChange4Change() {
//		   WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_45);
//	       driver.get("http://ec2-13-59-180-148.us-east-2.compute.amazonaws.com");
//		   System.out.println(driver.getCurrentUrl());
//	       WebElement title = driver.findElement(By.xpath("//title"));
//	       System.out.println("********************************************************");
//	       System.out.println("*" + title.getAttribute("text") + "*");
//	       System.out.println("********************************************************");
//	       driver.quit();
//	   }
	   
	   @Test
	   public void openC4C(){
		   WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME,true);
		   driver.navigate().to("http://13.59.180.148:8080/index.html");
		   System.out.println(driver.getCurrentUrl());
		   WebElement title = driver.findElement(By.xpath("//title"));
	       System.out.println("********************************************************");
	       System.out.println("*" + title.getAttribute("text") + "*");
	       System.out.println("********************************************************");
	       driver.quit();
	   }
	   
	   @Test
	   public void loginAsAdmin(){
		   WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
		   driver.get("http://13.59.180.148:8080/index.html#/login");
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	 
		   System.out.println(driver.getCurrentUrl());
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys("pMuldoon20");
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[1]")).sendKeys("password!");
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[2]")).click();
		   
	   }

	
}