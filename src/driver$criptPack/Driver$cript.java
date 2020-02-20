package driver$criptPack;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import comanfunctionLibrary.FunctionLibrary;
import utilites.ExecelFileUtils;
public class Driver$cript 
{ 
	static WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;
    
	public static void main(String[] args) throws Exception
	{ 
		ExecelFileUtils ex=new ExecelFileUtils();
		
		
		for(int i=1;i<=ex.rowCount("MasterTestCases");i++)
		{
			   String ModuleStatus="";
			   if(ex.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			   {
				      String TcModule=ex.getData("MasterTestCases", i, 1);
				       System.out.println(TcModule);
				       report=new ExtentReports("C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\Reports\\"+TcModule+FunctionLibrary.genaretDate()+".html");
				         test= report.startTest(TcModule);
				      for(int j=1;j<=ex.rowCount(TcModule);j++)
				      {
					     String Description=ex.getData(TcModule, j, 0);
					     String Function_Name=ex.getData(TcModule, j, 1);
					     String Locator_Type=ex.getData(TcModule, j, 2);
					     String Locator_Value =ex.getData(TcModule, j, 3);
					     String Test_Data=ex.getData(TcModule, j, 4);
					     try{
		                if(Function_Name.equalsIgnoreCase("startBrowser"))
					     {
					    	 driver=FunctionLibrary.startBrowser();
					    	 test.log(LogStatus.INFO, "startBrowser success");
					     }else if(Function_Name.equalsIgnoreCase("openApplication"))
					     {
					    	 FunctionLibrary.openApplication(driver);
					    	 test.log(LogStatus.INFO, Description);
					     }else if(Function_Name.equalsIgnoreCase("waitForElement"))
					     {
					    	 FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
					    	 test.log(LogStatus.INFO, Description);
					     }else if(Function_Name.equalsIgnoreCase("typeAction"))
					     {
					    	 FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
					    	 test.log(LogStatus.INFO, Description);
					     }else if(Function_Name.equalsIgnoreCase("clickAction"))
					     {
					    	 FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
					    	 test.log(LogStatus.INFO, Description);
					     }else if(Function_Name.equalsIgnoreCase("CaptureData"))
					     {
					    	 FunctionLibrary.CaptureData(driver, Locator_Type, Locator_Value);
					    	 test.log(LogStatus.INFO, Description);
					     }else if(Function_Name.equalsIgnoreCase("tableValidation"))
					     {
					    	 FunctionLibrary.tableValidation(driver, Test_Data);
					    	 test.log(LogStatus.INFO, Description);
					     }
					      else if(Function_Name.equalsIgnoreCase("closeBrowser"))
					     {
					    	  FunctionLibrary.closeBrowser(driver);
					    	  test.log(LogStatus.INFO, Description);
					     }
					      ex.setData(TcModule, j, 5, "PASS");
					       ModuleStatus="PASS";
					     }catch(Exception e)
					     {
					    	 e.printStackTrace();
					    	 ModuleStatus="FAIL";
					    	 ex.setData(TcModule, j, 5, "FAIL");
					    	 String reqDate= FunctionLibrary.genaretDate();
					    	 
					    	File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					    	File destFile=new File("C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\ScreenShots\\"+Description+"-Time- "+reqDate+".png");
					    	FileUtils.copyFile(srcFile, destFile);
					    	 test.log(LogStatus.INFO,Description );
					    	 test.log(LogStatus.INFO, test.addScreenCapture("C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\ScreenShots\\"+Description+"-Time- "+reqDate+".png"));
					    	 break;
					     }
				   }
				   
				   if(ModuleStatus.equalsIgnoreCase("PASS"))
				   {
					   ex.setData("MasterTestCases", i, 3, "PASS");
				   }else
				   {
					ex.setData("MasterTestCases", i, 3, "FIAL");   
				   }
				  
				   report.endTest(test);
				   report.flush();
				 
	             
					
				   
				   
			   }else
			   {
				   ex.setData("MasterTestCases", i, 3, "Not Executed");
				   ModuleStatus="FAIL";
				
			   }
			
			
			
			
			
		}
	
		
		
	}

}
