package testCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

//import Utilities.takeScreenShot;
import pageObject.Login;
import pageObject.PopularModels;
import testBase.BaseClass;

public class TC003_Login extends BaseClass{

	
	@Test(priority=7,groups= {"sanity", "regression"} )
	public void navigateToHomePge() throws IOException, InterruptedException {
		
		logger.info("***** starting TC003_Login *****");
		logger.info("Clicking on Zigwheels logo");
		
		PopularModels model = new PopularModels(driver);
		boolean logo=model.clickOnLogo();
		
		//validate the logo button(zigWheels)
		Assert.assertEquals(logo, true);
		
	}

	@Test(priority=8, groups = { "sanity", "regression" })
	public void loginProcess() throws InterruptedException, IOException{
		
		logger.info("Clicking on Login Button");
		
		Login login = new Login(driver);
		boolean loginBtn=login.clickOnLogin();
		
//		validate the login button 
		Assert.assertEquals(loginBtn, true);
	}
	
	@Test(priority=9,groups = { "regression" })
	public void selectGoogle() throws InterruptedException, IOException {
		
		Thread.sleep(3000);
		logger.info("Selecting Google option");
		Login login = new Login(driver);
		
		boolean gBtn=login.chooseGoogle();
		
//		validate the google button
		Assert.assertEquals(gBtn, true);
		
		login.getWindowIds();
		
	}
	
	@Test(priority=10, groups = { "regression" })
	public void inputEmailId() throws InterruptedException, IOException{
		
		Thread.sleep(3000);
		logger.info("Passing Incorrect email id");
		
		Login login = new Login(driver);
		
		boolean nextBtn = login.submitEmailId();
		
//		validate the next button 
		Assert.assertEquals(nextBtn, true);
		
	}
	
	@Test(priority=11, groups = { "regression" })
	public void validateErrorMessage() throws Exception {
		
		logger.info("Validating error message");
		
		Login login = new Login(driver);
		
		login.validateErrorMsg();
		
		logger.info("## Close TC003_Login  ##");

	}
}
