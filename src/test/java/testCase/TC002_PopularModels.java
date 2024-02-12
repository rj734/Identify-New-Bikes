package testCase;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.PopularModels;

import testBase.BaseClass;

public class TC002_PopularModels extends BaseClass {

	@Test(priority=5, groups= {"sanity","regression"})
    public void chennaiUsedCars() throws InterruptedException, IOException {
		
		logger.info("***** starting TC002_PopularModels *****");
		logger.info("Selecting used cars in Chennai");
		
		PopularModels model = new PopularModels(driver);
		boolean ch=model.mouseHoverUsedCars();
		
//		validate the chennai button
		Assert.assertEquals(ch,true);
	
	}
	
	@Test(priority=6, groups= {"regression"})
    public void popularModels() throws InterruptedException, IOException {
		
		logger.info("Printing popular models");
		
		PopularModels model = new PopularModels(driver);
		model.displayPopularModels();
		
		logger.info("## Close TC002_PopularModels ##");
	}
	
}
