package com.mvn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGDataProvider {

	@DataProvider(name = "tanvi")
	public Object[][] input() {
		return new Object[][] {{"standard_user","secret_sauce"}
		,{"problem_user","secret_sauce"}};
		
	}
	
	
	@Test(dataProvider = "tanvi")	 
 public void f(String username,String Password) throws InterruptedException {
	 WebDriver driver;
	 System.setProperty("webdriver.edge.driver", "H:\\Selenium\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://www.saucedemo.com/");
		Thread.sleep(2000);
		driver.findElement(By.id("user-name"))
		.sendKeys(username);
		Thread.sleep(2000);
		driver.findElement(By.id("password"))
		.sendKeys(Password);
		Thread.sleep(2000);
		driver.findElement(By.name("login-button")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("about_sidebar_link")).click();
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);

		
		driver.close();
	}

}
