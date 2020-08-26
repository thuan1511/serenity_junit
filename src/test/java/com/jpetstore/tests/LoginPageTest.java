package com.jpetstore.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.jpetstore.steps.PetStoreSteps;

import io.restassured.filter.session.SessionFilter;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class LoginPageTest {
	
	@Managed
	WebDriver driver;
	
	@Steps
	PetStoreSteps shopper;
	
	private static SessionFilter filter = new SessionFilter();


/**
 * This method will create a test user in the DB
 * username:test
 * password:test
 * 
 */
	@BeforeClass
	public static void createTestUser(){

	SerenityRest.given()
	.filter(filter)
	.get("http://localhost:8080/jpetstore/actions/Catalog.action");

	SerenityRest.given()
	.log()
	.all()
	.sessionId(filter.getSessionId())
	.formParam("account.address1", "WDrive")
	.formParam("account.address2", "10")
	.formParam("account.bannerOption", true)
	.formParam("account.city", "Kzoo")
	.formParam("account.country", "USA")
	.formParam("account.email", "th@sf.com")
	.formParam("account.favouriteCategoryId", "FISH")
	.formParam("account.firstName", "John")
	.formParam("account.lastName", "Doe")
	.formParam("account.languagePreference", "english")
	.formParam("account.zip", 01721)
	.formParam("account.phone", "3333333333")
	.formParam("account.state", "MI")
	.formParam("username", "test")
	.formParam("password", "test")
	.formParam("repeatedPassword", "test")
	.formParam("newAccount", "Save+Account+Information")
	.when()
	.post("http://localhost:8080/jpetstore/actions/Account.action")
	.then()
	.log()
	.all();
	}
	
	
	@Test
	@Title("Verify if a user can login succesfully to the store with valid credentials")
	public void verifyIfLoginIsSuccessful() {
		
		shopper.navigateToLoginPage();
		
		shopper.doLogin("test", "test");
		
		String greetingMessage = shopper.getGreetingMessage();
		
		assertEquals("Welcome John!", greetingMessage);
	}
	
	@Test
	@Title("Verify if the user can signout successfully")
	public void verifyIfUserCanLogoutSuccesfully() {
		
		shopper.navigateToLoginPage();
		
		shopper.doLogin("test", "test");
		
		shopper.signOut();
		
	}
	
	@Test
	@Title("Verify if message <b><i> 'Invalid username or password. Signon failed'.</i></b> is displayed for "
			+ " invalid credentials")
	public void verifyIfMessageIsDisplayedFOrInValidLogin() {
		
		shopper.navigateToLoginPage();
		
		shopper.doLogin("test", "testjsjdjd");
		
		String message = shopper.getMessageOnInvalidLogin();
		
		assertEquals("Invalid username or password. Signon failed.", message);
	}
}
