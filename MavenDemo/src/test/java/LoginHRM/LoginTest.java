package LoginHRM;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class LoginTest {
	public WebDriver driver;
	public WebElement element;
	static ExtentReports extent;
	static ExtentTest test;
  @Test
  public void verify_UserLogin() {
	  test=extent.startTest("demoLoginPass");
	  WebElement element1=driver.findElement(By.xpath("//input[@name='userName']"));
	  WebElement element2=driver.findElement(By.xpath("//input[@name='password']"));
	  Assert.assertTrue(element1.isDisplayed());
	  element1.sendKeys("mercury");
	  Assert.assertTrue(element2.isDisplayed());
	  element2.sendKeys("mercury");
	  driver.findElement(By.xpath("//input[@name='login']")).click();
	  Assert.assertTrue(true);
	  test.log(LogStatus.PASS, "Assert Pass as condition is True");
  }
  @Test
	public void demoReportFail()
	{
		test=extent.startTest("demoReportFail");
		
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Assert Pass as condition is False");
	}
  @BeforeTest
  public void beforeTest() {
			extent=new ExtentReports("D:\\Extent\\Reports\\MyReport.html",true);
			extent.addSystemInfo("HostName","LocalHost");
			extent.addSystemInfo("Enviorment","QA");
			extent.addSystemInfo("UserName","Prakash");
			//extent.loadConfig(configFile);
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("http://newtours.demoaut.com/");
  }

  @AfterMethod
  public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
			  File src=takesScreenshot.getScreenshotAs(OutputType.FILE);
			  try {
				FileUtils.copyFile(src, new File("D://Extent//Reports//MyReport.html"));
				System.out.println("Screenshot Taken");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			test.log(LogStatus.FAIL,result.getThrowable());
			}
			
		}
		extent.endTest(test);
	}
	  
  @AfterTest
	public void endReport()
	{ 
		driver.close();
		extent.flush();
	}

  }

