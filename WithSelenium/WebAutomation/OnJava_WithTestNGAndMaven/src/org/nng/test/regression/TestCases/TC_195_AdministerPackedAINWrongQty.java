package org.nng.test.regression.TestCases;

import org.nng.test.Configurations.Constants;
import org.nng.test.pageObjects.HomePage;
import org.nng.test.pageObjects.ReportsPage;
import org.nng.test.pageObjects.ResidentDetailPage;
import org.nng.test.utills.GetBrowserInstance;
import org.nng.test.utills.Keywords;
import org.nng.test.utills.ScreenShotCapture;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testpoint.StreamTest;

public class TC_195_AdministerPackedAINWrongQty extends GetBrowserInstance 
{
	WebDriver driver;
	Keywords key = new Keywords();
	StreamTest vansah = new StreamTest();
	HomePage homePage = new HomePage();
	ResidentDetailPage residentPage = new ResidentDetailPage();
	ReportsPage reportPage =  new ReportsPage();

	String CounterName;
	String monitorCode = Constants.sitemon_Monitor;

	String vansahPackage = Constants.vansahPackage;
	String vansahRequirement = "8";
	String vansahTestCaseID = "195";
	String vansahBuild = Constants.build;
	String vansahRelease = Constants.release;
	String bhs_Environment = Constants.environment;
	String BHSLog_bit = Constants.log_Bit;
	String vansahTestcaseName = "TC_195_AdministerPackedAINWrongQty";
	String testStep = "";
	
	String residentName ="";
	
	@BeforeTest
	public void getBrowserInstance(){
		driver = getDriver();
	}

	@Test(priority = 1)
	public void verifyPackedAdminAINWrongQTY() 
	{
		vansah.setProperty("sAgentName", Constants.vansahAgentName);
		
		try
		{
			//Login into Application
			testStep = "User Perform Login as AIN";
			homePage.performLogin(driver, Constants.USER_NAME_AIN, Constants.PASSWORD_AIN);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

			//Select Section
			String SectionName = Constants.sectionName2;
			testStep = "User Select Section: "+SectionName+" ";
			homePage.selectSection(driver, SectionName);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

			Thread.sleep(3000);
			//ClickOnMedicationRound
			testStep = "User Clicks on Medication Round";
			CounterName = "MedicationRound-Page-Load";
			vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
			homePage.clickOn_MedicationRound(driver);
			vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
			
			//Check if there is openDose round available and then proceed
			if(homePage.verify_presenceOfOpenDoseRound(driver))
			{
				
				//--- Click on Open Dose Round ---
				testStep = "User Clicks on openDoseRound";
				CounterName = "MainDoseRound-Page-Load";
				vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
				homePage.clickOn_OpenDoseRound(driver);
				vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
				
				
				//-- click on Packed Tab;
				testStep = "User Clicks on Packed Tab";
				homePage.clickOn_PackedTab(driver);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
				
				//---- Click on resident --
				testStep = "User click on resident";
				CounterName = "ResidentDetail-Page-Load";
				vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
				key.click(driver, "xpath", "(//h3[contains(@ng-bind,'ResidentName')])[last()]");
				residentPage.presence0f_MedicationsLocated(driver);
				vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
				
				//Save current resident Name
				residentName = residentPage.getResidentName(driver);
				Thread.sleep(5000);
				
				testStep = "User verifies Confirm, No. of Given,, Registered Nurse Reason for packed Medicine";
				if(residentPage.presence0f_ConfirmButtonLocated(driver)
						&& residentPage.presence0f_NumberGivenBoxLocated(driver)
						&& residentPage.presence0f_SelectNumberGivenReasonLocated(driver)){
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
					
					
					testStep = "User enters wrong No. of Given value for packed Medicine";
					key.EnterText(driver, "xpath", "//label[text()='Number Given']/following-sibling::input", "50");
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
					
					Thread.sleep(1000);
					
					testStep = "User Clicks On Confirm button";
					residentPage.clickOn_ConfirmBtn(driver);
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
							
					testStep = "User verifies incorrect count message in confirmation popup";
					if(residentPage.verify_PresenceOfConfirmationPopUp(driver)
							&& key.checkElementDisplay(driver, "xpath", "//span[contains(text(),'INCORRECT ADMINISTRATION COUNT')]"))
					{
						vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
								bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
						
						testStep = "User click confirm popup";
						residentPage.confirm_POPUP(driver);
						vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
								bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
						Thread.sleep(2000);
						
						
						testStep = "Search the resident in the list";
						homePage.searchResident(driver, residentName);
						vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
								bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
						
						testStep = "Verify presence of missed Signature in resident's pane";
						if(homePage.verify_PresenceOfMissedSignatureLocated(driver)){
							vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
									bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
						}else{
							ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
							vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
									bhs_Environment,"fail", "Test Step: " + testStep +" - Failed!", BHSLog_bit);
							vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! at Following TestStep "+testStep+"is the stack trace: ", 2);
							Assert.fail(testStep + "--Failed!!");
						}
					}else{
						ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
						vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
								bhs_Environment,"fail", "Test Step: " + testStep +" - Failed!", BHSLog_bit);
						vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! at Following TestStep "+testStep+"is the stack trace: ", 2);
						Assert.fail(testStep + "--Failed!!");					
					}	
				}//No. given found condition
			}//end of open dose round logic
			else{
				ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"fail", "There is no Open Dose round available for the test :( Unable to procceed. ", BHSLog_bit);
				vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! at Following TestStep "+testStep+"is the stack trace: ", 2);
				Assert.fail("There is no open Doase Round Avaiable");
			}		
		}// end of try
		catch(Exception e){
			ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild, bhs_Environment,
					"fail", "Execution on the following Step: " + testStep +" -is Failed!", BHSLog_bit);
			vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! at Following TestStep "+testStep+"is the stack trace: ", 2);
			Assert.fail(testStep + "--Failed!! ", e);
		}
		
	}// end of method
	
	
	@Test(dependsOnMethods = "verifyPackedAdminAINWrongQTY")
	public void verifyAdminsteredReport()
	{
		
		try
		{
			testStep = "User Navigates to homePage";
			homePage.clickOn_HomeBtn(driver);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
			
			testStep = "User clicks on Reports";
			CounterName = "Reports-Page-Load";
			vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
			reportPage.clickOn_Reports(driver);
			vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
			
			
			testStep = "User clicks on Missed Administration menu";
			CounterName = "MissedAdministration-Report-Page-Load";
			vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
			reportPage.clickOn_missedAdminstration(driver);
			vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
			
			testStep = "User enter Start and End date";
			reportPage.enterStartDate(driver, homePage.getCurrentDate());
			reportPage.enterEndDate(driver, homePage.getCurrentDate());
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
			
			
			Thread.sleep(2000);
			testStep = "User clicks on Generate button and observes generated Report";
			CounterName = "MissedAdministration-ReportGeneration-Time";
			vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
			reportPage.clickOn_generateBtn(driver);
			key.checkElementDisplay(driver, "xpath", "//div[contains(@class,'AdministrationReport ')]");
			vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);	
			
		}// end of try
		catch(Exception e){
			ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild, bhs_Environment,
					"fail", "Execution on the following Step: " + testStep +" -is Failed!", BHSLog_bit);
			vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! at Following TestStep "+testStep+"is the stack trace: ", 2);
			Assert.fail(testStep + "--Failed!! ", e);
		}
	}
	
	
}// end of class