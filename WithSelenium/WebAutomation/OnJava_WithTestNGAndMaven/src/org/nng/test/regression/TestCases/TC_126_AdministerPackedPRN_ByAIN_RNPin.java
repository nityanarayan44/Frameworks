package org.nng.test.regression.TestCases;

import org.nng.test.Configurations.Constants;
import org.nng.test.pageObjects.HomePage;
import org.nng.test.pageObjects.ProgressNotes;
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

public class TC_126_AdministerPackedPRN_ByAIN_RNPin extends GetBrowserInstance
{

	WebDriver driver;
	Keywords key = new Keywords();
	StreamTest vansah = new StreamTest();
	HomePage homePage = new HomePage();
	ResidentDetailPage residentPage = new ResidentDetailPage();
	ReportsPage reportPage =  new ReportsPage();
	ProgressNotes progressPage = new ProgressNotes();

	String CounterName;
	String monitorCode = Constants.sitemon_Monitor;

	String vansahPackage = Constants.vansahPackage;
	String vansahRequirement = "8";
	String vansahTestCaseID = "126";
	String vansahBuild = Constants.build;
	String vansahRelease = Constants.release;
	String bhs_Environment = Constants.environment;
	String BHSLog_bit = Constants.log_Bit;
	String vansahTestcaseName = "TC_126_AdministerPackedPRN_ByAIN_RNPin";
	String testStep = "";

	//String residentName ="TESTPOINT, Eight";
	String residentName ="";
	String drugName = "";

	@BeforeTest
	public void getBrowserInstance(){
		driver = getDriver();
	}

	@Test(priority = 1)
	public void administerPackedPRN_ByAIN_RNPin() 
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
			//homePage.waitForPageLoader(driver);

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

				Thread.sleep(5000);
				//---- Click on resident --
				testStep = "User click on resident";
				CounterName = "ResidentDetail-Page-Load";
				vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
				//residentPage.clickOn_Resident(driver);
				//key.click(driver, "xpath", "//h3[text()='"+residentName+"']");
				key.click(driver, "xpath", "(//h3[contains(@ng-bind,'ResidentName')])[2]");
				residentPage.presence0f_MedicationsLocated(driver);
				vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

				//homePage.waitForPageLoader(driver);
				Thread.sleep(5000);
				//Save current resident Name
				residentName = residentPage.getResidentName(driver);

				// --- Click on PRN Button
				testStep = "User clicks on PRN Button";
				CounterName = "ResidentDetail-PRN-Page-Load";
				vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
				residentPage.clickOn_PRN(driver);
				residentPage.presence0f_MedicationsLocated(driver);
				vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);


				Thread.sleep(3000);

				testStep = "User verifies the authentication popup";
				if(residentPage.presence0f_PinAuthenticationPopUpLocated(driver)){
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

					//Enter steps for invalid Pin
					testStep = "User enters valid first RN pin and click 'verify' for authorization";
					residentPage.enterPin(driver, Constants.RN_PIN);
					residentPage.clickOn_verifyPin(driver);
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
				}//end of authentication popup Logic
				else{
					ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"fail", "Test Step: " + testStep +" - Failed!", BHSLog_bit);
					vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! at Following TestStep "+testStep+"is the stack trace: ", 2);
					Assert.fail(testStep + "--Failed!!");
				}	
				
				
				//Enter Quantity to administer PRN medicine
				Thread.sleep(3000);
				testStep = "User enters medication Quantity within the limits of Minimum and Maximum";
				residentPage.enterPackedQTY_PRN(driver, "-1"); // Always enter -ve value to check notification message
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
				Thread.sleep(1000);


				//Check to Administer Medicine
				testStep = "User click on Admin to administer the PRN medicine";
				residentPage.clickOn_pckdPRN_FirstMedicine(driver);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

				Thread.sleep(3000);
				//homePage.waitForPageLoader(driver);
				
				//drugName = residentPage.getDrugName(driver);
				//System.out.println("1. drug name -->" + drugName);
				
				testStep = "User clicks On Confirm button";
				residentPage.clickOn_ConfirmBtn(driver);
				vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
						bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

				Thread.sleep(2000);
				testStep = "User verifies confirmation popup";
				if(residentPage.verify_PresenceOfPRN_ConfirmationPopUp(driver))
				{
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

					testStep = "User click confirm popup";
					residentPage.popUp_ConfirmPrn(driver);;
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);
					Thread.sleep(2000);
					//homePage.waitForPageLoader(driver);
					
				}else{
					ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
					vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
							bhs_Environment,"fail", "Test Step: " + testStep +" - Failed!", BHSLog_bit);
					vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! at Following TestStep "+testStep+"is the stack trace: ", 2);
					Assert.fail(testStep + "--Failed!!");					
				}
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
			vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! Following is the stack trace: "+e, 2);
			Assert.fail(testStep + "--Failed!! ", e);
		}

	}// End of method


	
	@Test(dependsOnMethods = "administerPackedPRN_ByAIN_RNPin")
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


			testStep = "User clicks on Administration menu";
			CounterName = "AdministrationReport-Page-Load";
			vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
			reportPage.clickOn_Adminstration(driver);
			vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

			testStep = "User enter Start and End date";
			reportPage.enterStartDate(driver, homePage.getCurrentDate());
			reportPage.enterEndDate(driver, homePage.getCurrentDate());
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

			testStep = "User Select Resident";
			reportPage.selectResident(driver, residentName);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);


			testStep = "User unchecks NIM, Emergency and checks PRN";
			reportPage.clickOn_nurseInitiatedCheckbox(driver);
			reportPage.clickOn_EmergencyCheckbox(driver);
			//reportPage.clickOn_PRNCheckbox(driver);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

			Thread.sleep(2000);
			//omePage.waitForPageLoader(driver);
			testStep = "User clicks on Generate button and observes generated Report";
			CounterName = "Administration-ReportGeneration-Time";
			vansah.Start_SiteMon_Synthetic(CounterName, monitorCode);
			reportPage.clickOn_generateBtn(driver);
			key.checkElementDisplay(driver, "xpath", "//div[contains(@class,'AdministrationReport ')]");
			vansah.Stop_SiteMon_Synthetic(CounterName, monitorCode);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild,
					bhs_Environment,"pass", "Test Step: " + testStep +" - Passed!", BHSLog_bit);

		}catch(Exception e){
			ScreenShotCapture.captureScreen(driver, vansahTestcaseName);
			vansah.sendUpdateLog(vansahPackage, vansahRequirement, vansahTestCaseID, vansahRelease, vansahBuild, bhs_Environment,
					"fail", "Execution on the following Step: " + testStep +" -is Failed!", BHSLog_bit);
			vansah.hostAlert("During Test Case " + vansahTestcaseName + " Failed! Following is the stack trace: "+e, 2);
			Assert.fail(testStep + "--Failed!! ", e);
		}
	}// end of method
	
	
}// end of class