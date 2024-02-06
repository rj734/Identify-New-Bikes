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

	public boolean clickOnLogin() {

		
		Boolean bol = loginBtn.isEnabled();
		
		loginBtn.click();
		
		return bol;
	}

	public boolean chooseGoogle() {

		Boolean bol = googleBtn.isEnabled();

		googleBtn.click();
		
		return bol;
	}

	public void getWindowIds() {

		Set<String> windowIds = driver.getWindowHandles();

		List<String> wid = new ArrayList<String>(windowIds);

		driver.switchTo().window(wid.get(1));
		// System.out.println(wid.size());

	}

	public boolean submitEmailId() throws InterruptedException {

		emailBox.sendKeys("abcffflkkj@gmail.com");
		Boolean bol = nextBtn.isEnabled();
		
		nextBtn.click();
		return bol;
	}
	
	public String validateErrorMsg() throws IOException {
		ExcelUtility et = new ExcelUtility(System.getProperty("user.dir") + "\\TestData\\bikeDetails.xlsx");

		String act_text = errorMsg.getText();

		System.out.println("The error message displayed is : Couldn’t find your Google Account");

		et.setCellData("GoogleLogin", 1, 0, act_text);
		return act_text;
	}
}
