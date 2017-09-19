package com.revature.home.SeleniumTest;

import static org.junit.Assert.*;

import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.FindBy;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.javascript.host.Element;

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
	   public void openC4C() throws InterruptedException{
		   WebDriver driver = new PhantomJSDriver();
		   driver.navigate().to("http://13.59.180.148:8080/index.html");
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   System.out.println(driver.getCurrentUrl());
		   WebElement title = driver.findElement(By.xpath("//title"));
	       System.out.println("********************************************************");
	       System.out.println("*" + title.getAttribute("text") + "*");
	       System.out.println("********************************************************");
//	       driver.findElement(By.xpath("/html/body/div/ui-view/nav/div/ul[2]/li[2]/a")).click();
//	       System.out.println(driver.getCurrentUrl());
//	       driver.findElement(By.xpath("//*[@id='manage']/div[1]/div/div/ul/li[3]/a"));
//	       assertEquals("http://localhost:8080/caliber#/vp/manage", driver.getCurrentUrl());
	       driver.quit();
	   }

	   @Test
	   public void loginAsAdmin(){
		   WebDriver driver = new PhantomJSDriver();
		   driver.get("http://13.59.180.148:8080/index.html#/login");
		   System.out.println(driver.getCurrentUrl());
		   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[1]/input")).sendKeys("pMuldoon20");
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[1]")).sendKeys("password!");
		   driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/input[2]")).click();
		   System.out.println("Success");
	   }


}
