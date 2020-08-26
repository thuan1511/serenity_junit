package com.jpetstore.tests;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.jpetstore.steps.PetStoreSteps;
import com.jpetstore.utils.PetCategories;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class Sample {

	@Managed
	WebDriver driver;
	
	@Steps
	PetStoreSteps shopper;
	
	
	@Ignore
	@Test
	@Title("Testing methods")
	public void navigateToSignOnPage() throws InterruptedException {
		
		shopper.navigateToLoginPage();
		shopper.doLogin("test", "test");
		shopper.navigateToProductCategory(PetCategories.DOGS);
		shopper.selectPetByName(PetCategories.DOGS, "Dalmation");
		shopper.addToCartSpecificProduct("Spotted Adult Female Dalmation");
		shopper.clickOnProceedToCheckout();
		
	}
	
}
