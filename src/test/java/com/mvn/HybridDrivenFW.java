package com.mvn;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HybridDrivenFW {
	@DataProvider(name = "tanvi")
	public Object[][] readData() throws InvalidFormatException, IOException {
		Object[][] data=null;
		
		//1.give file path
		String filepath="H:\\Selenium\\20jun.xlsx";
		//2. to make file
		File file=new File(filepath);
		//3.to open particular file
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		//4.to open particular sheet
		Sheet sheet=workbook.getSheet("Sheet3");
		//5.to select a particular row
		int n_row=sheet.getPhysicalNumberOfRows();
		System.out.println("no of rows: "+n_row);
		//jagged array
		data=new Object[n_row][];
		
		for (int i = 0; i < data.length; i++) {
			Row row =sheet.getRow(i);
			
			//6.to select a particular col
			int ncol=row.getPhysicalNumberOfCells();
			System.out.println("no of col is :"+ncol);
			data[i]=new Object[ncol];
			for (int j = 0; j < data[i].length; j++) {
				
				
				Cell cell=row.getCell(j);
				//7.set all type of data to String
				cell.setCellType(CellType.STRING);
				//8. to get data
				data[i][j]= cell.getStringCellValue();
			}
		}
		
		
		return data;
		
	}
	WebDriver driver;
	@Test(dataProvider = "tanvi")
	public void test(String keyword,String data) throws InterruptedException, InvalidFormatException, IOException {
//		String[][] data=readData();
		
//		for (int i = 0; i < data.length; i++) {
		
		if(keyword.equalsIgnoreCase("open browser"))
		{
			WebDriverManager.chromedriver().setup();
//		 System.setProperty("webdriver.edge.driver", "H:\\Selenium\\msedgedriver.exe");
		 driver = new ChromeDriver();
		}else if (keyword.equalsIgnoreCase("enter url")) {
			driver.get(data);
			Thread.sleep(2000);
		}else if(keyword.equalsIgnoreCase("enter username"))
		{
			driver.findElement(By.id("user-name"))
			.sendKeys(data);
			Thread.sleep(2000);
		}else if(keyword.equalsIgnoreCase("enter password")) {
			driver.findElement(By.id("password"))
			.sendKeys(data);
			Thread.sleep(2000);
			
			
	}
		else if (keyword.equalsIgnoreCase("click login")) {
			driver.findElement(By.name("login-button")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("react-burger-menu-btn")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("about_sidebar_link")).click();
			Thread.sleep(2000);
			driver.navigate().back();
			Thread.sleep(2000);
			File file= ((RemoteWebDriver) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(file, new File("H:\\Selenium\\SS\\defect_20_dec.png"));
			Thread.sleep(2000);
		}else if(keyword.equalsIgnoreCase("close browser")) {
			driver.close();
		}
			
			
			
//	}
	}
}
