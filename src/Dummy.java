import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import comanfunctionLibrary.FunctionLibrary;

public class Dummy {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		
		
		
		ExtentReports reports=new ExtentReports("C:\\ch\\MyProjectsChandu\\StcokAccount_Hybrid\\Reports\\resutls.html");
		
		ExtentTest writer=reports.startTest("Logine Test");
		
		
	
		//launch browser
		WebDriver driver1=FunctionLibrary.startBrowser();
        //launch Application
		FunctionLibrary.openApplication(driver1);
		//login
		writer.log(LogStatus.PASS, "Application Launched Success");
		
		FunctionLibrary.waitForElement(driver1, "id","username" , "10");
		FunctionLibrary.typeAction(driver1, "id", "username", "admin");
		writer.log(LogStatus.PASS, "Enterd  UserName Success");
		
        FunctionLibrary.waitForElement(driver1, "name","password" , "10");
		FunctionLibrary.typeAction(driver1, "name", "password", "master");
		writer.log(LogStatus.FAIL, "Enterd  Password Success");
		
		 FunctionLibrary.waitForElement(driver1, "id","btnsubmit" , "10");
		 FunctionLibrary.clickAction(driver1, "id", "btnsubmit");
		 writer.log(LogStatus.INFO, "Enterd  Click on Submit Button Success");
		 reports.endTest(writer);
		 
		 reports.flush();
		 
		 
		//Supply Creation page Click on link
		 /*FunctionLibrary.waitForElement(driver1, "xpath","//*[@id='mi_a_suppliers']/a" , "10");
		 FunctionLibrary.clickAction(driver1, "xpath", "//*[@id='mi_a_suppliers']/a");
		//Click on Add Button
		 FunctionLibrary.waitForElement(driver1, "xpath","//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a" , "20");
		 FunctionLibrary.clickAction(driver1, "xpath", "//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a");
		//Enter All Details Creation Page
		 FunctionLibrary.waitForElement(driver1, "id","x_Supplier_Name" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x_Supplier_Name","Admin");
		 
		 FunctionLibrary.waitForElement(driver1, "id","x_Address" , "20");
		 FunctionLibrary.typeAction(driver1, "id", "x_Address","master");
		 
		 
		 FunctionLibrary.waitForElement(driver1, "id","x_City" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x_City","master");
		 
		 FunctionLibrary.waitForElement(driver1, "id","x_Country" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x_Country","master");
		 
		 FunctionLibrary.waitForElement(driver1, "id","x_Contact_Person" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x_Contact_Person","master");
		 
		 FunctionLibrary.waitForElement(driver1, "id","x__Email" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x__Email","chahgs@123");
		 
		 FunctionLibrary.waitForElement(driver1, "id","x_Phone_Number" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x_Phone_Number","9585777745");
		 
		 FunctionLibrary.waitForElement(driver1, "id","x_Notes" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x_Notes","hydaerabad");
		 
		 FunctionLibrary.waitForElement(driver1, "id","x_Mobile_Number" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "x_Mobile_Number","9585777745");
		 
		
		 */
		/*
		 FunctionLibrary.waitForElement(driver1, "id","btnAction" , "10");
		 FunctionLibrary.typeAction(driver1, "id", "btnAction","Keys.ENTER");
		 */
		
		/*FunctionLibrary.waitForElement(driver1, "id","logout" , "10")*/;
	/*   FunctionLibrary.clickAction(driver1, "id", "logout");
	   
	   FunctionLibrary.waitForElement(driver1, "xpath","//div[2]/div/div[4]/div[2]/button[1]" , "20");
	   FunctionLibrary.clickAction(driver1, "xpath", "//div[2]/div/div[4]/div[2]/button[1]");
	   Thread.sleep(5000);
	 FunctionLibrary.closeBrowser(driver1);   
*/
	
	}

}
