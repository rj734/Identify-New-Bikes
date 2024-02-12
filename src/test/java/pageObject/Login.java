package pageObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import utilities.ExcelUtility;

public class Login extends BasePage {

	public Login(WebDriver driver) {
		super(driver);
	}

	// locators

	@FindBy(xpath = "//div[@id='forum_login_title_lg']")
	WebElement loginBtn;

	@FindBy(xpath = "//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']")
	WebElement googleBtn;

	@FindBy(xpath = "//input[@id='identifierId']")
	WebElement emailBox;

	@FindBy(xpath = "//button[contains(@class, 'qIypjc TrZEUc lw1w4b')]/span")
	WebElement nextBtn;

	@FindBy(xpath = "//div[@class='o6cuMc Jj6Lae']")
	WebElement errorMsg;

	// Actions

	public boolean clickOnLogin() throws InterruptedException {

		
		Boolean bol = loginBtn.isEnabled();
		
		//highlight the element and take screenshot
		try {
			highlightElement(loginBtn);
			
			Thread.sleep(2000);
			//take screenshot
			captureScreen("//screenShots//clickOnLogin");
			
		}catch(Exception e) {
			
		}

//		click on login button
		loginBtn.click();
		Thread.sleep(2000);
		
		return bol;
		
	}

	public boolean chooseGoogle() {

		Boolean bol = googleBtn.isEnabled();
		
//		highlight the element and take screenshot
		try {
			highlightElement(googleBtn);
			
			Thread.sleep(2000);
			//take screenshot
			captureScreen("//screenShots//selectGoogle");

		}catch(Exception e) {
			
		}
		
		//click on google
		googleBtn.click();
		
		return bol;
	}

	public void getWindowIds() throws InterruptedException, IOException {

		Set<String> windowIds = driver.getWindowHandles();

		List<String> wid = new ArrayList<String>(windowIds);

		driver.switchTo().window(wid.get(1));
		
		//pass the incorrect mail id 
		emailBox.sendKeys("abcffflkkj@gmail.com");
		
		Thread.sleep(2000);
		//take screenshot
		captureScreen("//screenShots//inputMailId");

	}

	public boolean submitEmailId() throws IOException, InterruptedException{
		Thread.sleep(2000);
		
		Boolean bol = nextBtn.isEnabled();
		
		//click on next button
		nextBtn.click();
		
		Thread.sleep(2000);
		//take screenshot
		captureScreen("//screenShots//clickNext");
			
		return bol;
	}
	
	public void validateErrorMsg() throws IOException {
		ExcelUtility et = new ExcelUtility(System.getProperty("user.dir") + "\\TestData\\bikeDetails.xlsx");

//		get the error message after submitting the mail id
		String act_text = errorMsg.getText();

//		update the error message in excel sheet
		et.setCellData("GoogleLogin", 1, 0, act_text);
		System.out.println("The error message displayed is : Couldn’t find your Google Account");
		
	}
}
