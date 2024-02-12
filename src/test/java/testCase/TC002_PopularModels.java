package testCase;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.PopularModels;

import testBase.BaseClass;

public class TC002_PopularModels extends BaseClass {

	@Test(priority=5 , groups = {"sanity", "regression"})
    public void chennaiUsedCars() throws InterruptedException, IOException {
		logger.info("***** starting TC002_PopularModels *****");
		logger.info("Selecting used cars in Chennai");
		PopularModels model = new PopularModels(driver);
		boolean ch=model.mouseHoverUsedCars();
		Assert.assertEquals(ch, true);
		captureScreen("//screenShots//chennaiUsedCars");

	}
	
	@Test(priority=6 , groups = {"regression"})
    public void popularModels() throws InterruptedException, IOException {
		
		logger.info("Printing popular models");
		PopularModels model = new PopularModels(driver);
		String modelName=model.displayPopularModels();
		Assert.assertEquals( modelName, "Mahindra XUV500");

		
	}
	
	@Test(priority=7 , groups = {"regression"})
	public void clickOnZigWheels() throws IOException {
		logger.info("Clicking on Zigwheels logo");
		PopularModels model = new PopularModels(driver);
		boolean logo=model.clickOnLogo();
		Assert.assertEquals(logo, true);

	}
}
