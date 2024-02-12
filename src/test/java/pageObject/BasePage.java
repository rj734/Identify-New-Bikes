package pageObject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;

public class BasePage extends BaseClass  {
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void highlightElement(WebElement element) {
		
		//highlight the selected webelement
		try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 5px solid red;');", element);
		}
		
		catch(Exception e) {}
	}

	
	// Method to take screenshot

		public void captureScreen(String tname) throws IOException {

			TakesScreenshot ts = (TakesScreenshot) driver;

			File src = ts.getScreenshotAs(OutputType.FILE);

			File trg = new File("C:\\eclipse\\hackathonProject 1.zip_expanded\\hackathonproject\\" + tname + ".png");

			FileUtils.copyFile(src, trg);

		}
}

