package pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtility;

public class UpcomingBikes extends BasePage {

	public UpcomingBikes(WebDriver driver) {
		super(driver);
	}
	
	//locators
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[3]/a")
	WebElement newBikes;
	
	@FindBy(xpath="//div[@class='col-lg-12 pl-0']/ul/li[3]/ul/li[5]/span")
	WebElement upcomingBikes;
	
	@FindBy(xpath="(//a[@class='fnt-14'])[3]")
	WebElement hondaBikes;
	
	@FindBy(xpath="//li[contains(@class,'modelItem')]")
	List<WebElement> bikePrice;
	
	@FindBy(xpath="//strong[@class='lnk-hvr block of-hid h-height']")
	List<WebElement> bikeModels;
	
	@FindBy(xpath="//div[@class='clr-try fnt-14']")
	List<WebElement> launchDates;
	
	@FindBy(xpath="//span[@class='zw-cmn-loadMore']")
	WebElement viewmore;
	
	//Actions
	
	public boolean clickOnUpcomingBikes() throws InterruptedException {
		
		Actions a = new Actions(driver);
		a.moveToElement(newBikes).perform();
		
		Thread.sleep(2000);
		
//		check if the button is clickable or not return true or false
		Boolean bol = upcomingBikes.isEnabled();
	
//		highlight the element and take screenshot
		try {
			highlightElement(upcomingBikes);
			
			Thread.sleep(2000);
			//take screenshot
			captureScreen("//screenShots//clickOnUpcomingBikes");
			
			//click on upcomingBikes
			upcomingBikes.click();
		}catch(Exception e) {
			
		}
		
		return bol;
	}
	
	public boolean selectBrand() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
//		js.executeScript("arguments[0].scrollIntoView();",hondaBikes);
		
		Boolean hb = hondaBikes.isEnabled();
		
//		highlight the element and take screenshot
		try {
			highlightElement(hondaBikes);
			
			Thread.sleep(2000);
			//take screenshot
			captureScreen("//screenShots//selectHondaBrand");
			
//			click on honda brand
			js.executeScript("arguments[0].click()", hondaBikes);
		}catch(Exception e) {
			
		}
		
		Thread.sleep(2000);
		return hb;
		
	}
	
    public boolean viewMoreBikes() throws InterruptedException, IOException {
    	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1200)");
		
//		js.executeScript("arguments[0].scrollIntoView();",viewmore);
	
		Boolean bol = viewmore.isEnabled();
		
//		highlight the element and take screenshot
		try {
			highlightElement(viewmore);
			
			Thread.sleep(4000);
			//take screenshot
			captureScreen("//screenShots//viewMoreBikeBtn");
			
		}catch(Exception e) {
			
		}
		
//		click on viewmorebikes button
		js.executeScript("arguments[0].click()", viewmore);
		return bol;
		
	}
    
	public void printBikeDetails() throws InterruptedException, IOException {
		
		ExcelUtility et = new ExcelUtility(System.getProperty("user.dir")+"\\TestData\\bikeDetails.xlsx");
		int k=1;
		String models = " ";
		
		for(int i=0;i<bikePrice.size();i++) {
			
		double prices =Double.parseDouble(bikePrice.get(i).getAttribute("data-price"));
		models=bikeModels.get(i).getText();
		String dates=launchDates.get(i).getText();
		
		if(prices < 400000) {
			
			System.out.println("Bike name: "+models);
			System.out.println("Price: "+prices);
			System.out.println(dates);
			
			System.out.println("--------------------");
			
//			update the value of bike model name, price and launch date in excel sheet
			et.setCellData("HondaBike", k, 0, models);
			et.setCellData("HondaBike", k, 1, String.valueOf(prices));
			et.setCellData("HondaBike", k, 2, dates);
		 }
		}
		
	}
}
