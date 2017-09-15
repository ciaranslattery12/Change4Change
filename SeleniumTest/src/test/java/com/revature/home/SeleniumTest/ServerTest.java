package com.revature.home.SeleniumTest;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class ServerTest {

	   @Test
	   public void openBlazeMeterTest() {
		   WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_45);
	       driver.get("localhost:8080/home/index.html");
	       WebElement title = driver.findElement(By.xpath("//title"));
	       System.out.println("********************************************************");
	       System.out.println("*" + title.getAttribute("text") + "*");
	       System.out.println("********************************************************");
	       driver.quit();
	   }
	   
//	   @Test
//		public void loginAsAdmin(){
//		   WebDriver driver = new FirefoxDriver();
//			driver.get("http://13.59.180.148:8080/index.html#/login");
//			//sign in user with admin privaleges
//			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys("pMuldoon20");
//			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[1]")).sendKeys("password!");
//			driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[2]")).click();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//			//find elements that should be on home page after login
//			//driver.findElement(By.cssSelector("body > div:nth-child(2) > div > div > div > div:nth-child(1) > h1"));
//			//driver.findElement(By.cssSelector("#myCarousel > div"));
//			//driver.findElement(By.xpath("//*[@id='bs-example-navbar-collapse-9']/ul[2]/li[1]/a")).click();
//		}
	
}