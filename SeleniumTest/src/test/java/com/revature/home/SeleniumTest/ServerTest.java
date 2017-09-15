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

	   @Test
	   public void openChange4Change() {
		   WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_45);
	       driver.get("http://localhost:8080/home/index.html");
	       WebElement title = driver.findElement(By.xpath("//title"));
	       System.out.println("********************************************************");
	       System.out.println("*" + title.getAttribute("text") + "*");
	       System.out.println("********************************************************");
	       driver.quit();
	   }
	   
	   @Test
	   public void openC4C(){
		   WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		   driver.get("http://localhost:8080/home/index.html");
	       WebElement title = driver.findElement(By.xpath("//title"));
	       System.out.println("********************************************************");
	       System.out.println("*" + title.getAttribute("text") + "*");
	       System.out.println("********************************************************");
	       driver.quit();
	   }
	   
	   @Test
	   public void loginAsAdmin(){
		   WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
		   driver.get("http://localhost:8080/home/index.html#/login");
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys("pMuldoon20");
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[1]")).sendKeys("password!");
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[2]")).click();
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	 
		   
	   }

	
}