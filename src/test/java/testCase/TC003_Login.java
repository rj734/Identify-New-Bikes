package testCase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

//import Utilities.takeScreenShot;
import pageObject.Login;
import testBase.BaseClass;

public class TC003_Login extends BaseClass{

	@Test(priority=8 , groups = {"sanity", "regression"})
	public void loginProcess() throws IOException {
		logger.info("***** starting TC003_Login *****");
		logger.info("Clicking on Login Button");
		Login login = new Login(driver);
		boolean loginBtn=login.clickOnLogin();
		Assert.assertEquals(loginBtn, true);
	
	}
	
	@Test(priority=9 , groups = {"regression"})
	public void selectGoogle() throws IOException {
		logger.info("Selecting Google option");
		Login login = new Login(driver);
		boolean gBtn=login.chooseGoogle();
		Assert.assertEquals(gBtn, true);	
		login.getWindowIds();


	}
	
	@Test(priority=10 , groups = {"regression"})
	public void inputEmailId() throws InterruptedException, IOException {
		logger.info("Passing Incorrect email id");
		Login login = new Login(driver);
		boolean nextBtn=login.submitEmailId();
		Assert.assertEquals(nextBtn, true);

	}
	
	@Test(priority=11 , groups = {"regression"})
	public void validateErrorMessage() throws Exception {
		logger.info("Validating error message");
		Login login = new Login(driver);
		login.validateErrorMsg();
		
		logger.info("## Close TC003_Login##");
		
	}
}
