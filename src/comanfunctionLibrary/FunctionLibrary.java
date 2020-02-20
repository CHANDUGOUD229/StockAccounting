package comanfunctionLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilites.PropertyFileUtils;

public class FunctionLibrary 
{
	 static  WebDriver driver;
	
	public static WebDriver startBrowser() throws IOException
	{
		
	if(PropertyFileUtils.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\Drivers\\chromedriver.exe");	
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		}
	else if(PropertyFileUtils.getValueForKey("Browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\Drivers\\geckodriver.exe");	
	    driver=new FirefoxDriver();
	}
	else
	{
		System.setProperty("webdriver.ie.driver", "C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\Drivers\\IEDriverServer.exe");	
	    driver=new InternetExplorerDriver();
		
	}
	
		return driver;
		
	}
	
	public static void openApplication(WebDriver driver) throws IOException
	{
		driver.get(PropertyFileUtils.getValueForKey("Url"));
		
		driver.manage().window().maximize();
	}
	
	public static void waitForElement(WebDriver driver,String locatortype,

			String locatorvalue,String waittitme){
			
			WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittitme));
			
			if(locatortype.equalsIgnoreCase("id")){
				
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
			}
            if(locatortype.equalsIgnoreCase("xpath"))
            {
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
			}
			if(locatortype.equalsIgnoreCase("name"))
			{
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
			}			
		}
	
    public static void typeAction(WebDriver driver,String locatortype,
			String locatervalue,String testdata)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatervalue)).clear();
			driver.findElement(By.id(locatervalue)).sendKeys(testdata);
			
		}
		if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatervalue)).clear();
			driver.findElement(By.name(locatervalue)).sendKeys(testdata);
			
		}
		if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatervalue)).clear();
			driver.findElement(By.xpath(locatervalue)).sendKeys(testdata);
		}
	}
	
	public static void clickAction(WebDriver driver,String locatortype,String locatervalue)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatervalue)).sendKeys(Keys.ENTER);
			
		}else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatervalue)).click();
			
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatervalue)).sendKeys(Keys.ENTER);;
		}
		
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	public static String genaretDate()
	{
		Date d=new  Date();
	     
	  SimpleDateFormat de=new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss");
	  String requireddate=de.format(d);
	  return requireddate;
	  
	}
	
	public static void CaptureData(WebDriver driver,String locatortype,String locatervalue) throws IOException
	{
		
     String supplierdata="";
		
		if(locatortype.equalsIgnoreCase("id"))
		{
			supplierdata=	driver.findElement(By.id(locatervalue)).getAttribute("value");
			
		}else if(locatortype.equalsIgnoreCase("name"))
		{
			supplierdata=driver.findElement(By.name(locatervalue)).getAttribute("value");
			
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			supplierdata=driver.findElement(By.xpath(locatervalue)).getAttribute("value");
		}
		FileWriter fw=new FileWriter("C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\CaptureData\\DataStoring.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(supplierdata);
		bw.flush();
		bw.close();
		
	}
	
	public static void tableValidation(WebDriver driver,String column) throws IOException, InterruptedException
	{
		FileReader fr=new FileReader("./CaptureData/DataStoring.txt");
		BufferedReader br=new BufferedReader(fr);
		
        String 	Exp_Data=br.readLine();
        if(driver.findElement(By.id(PropertyFileUtils.getValueForKey("SearchTextBox"))).isDisplayed())
        {
        	Thread.sleep(5000);
        	driver.findElement(By.id(PropertyFileUtils.getValueForKey("SearchTextBox"))).sendKeys(Exp_Data);
        	driver.findElement(By.id(PropertyFileUtils.getValueForKey("SearchButton"))).click();
        	
        }else
        {   driver.findElement(By.xpath(PropertyFileUtils.getValueForKey("SpanButton"))).click();
        	Thread.sleep(5000);
        	driver.findElement(By.id(PropertyFileUtils.getValueForKey("SearchTextBox"))).sendKeys(Exp_Data);
        	driver.findElement(By.id(PropertyFileUtils.getValueForKey("SearchButton"))).click();
        }
        
        
        WebElement table=driver.findElement(By.id(PropertyFileUtils.getValueForKey("suppliertable")));
		
		List<WebElement>rows=table.findElements(By.tagName("tr"));
		
		for(int i=1;i<rows.size();i++)
		{
		       String act_data= driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+column+"]/div/span")).getText();	
		       Assert.assertEquals(Exp_Data, act_data); 
		       System.out.println(act_data+"   "+Exp_Data);
		       break;
		}
		
		
		
		
		
	}
	
}
