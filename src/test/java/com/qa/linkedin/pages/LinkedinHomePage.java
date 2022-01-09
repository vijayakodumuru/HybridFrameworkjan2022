package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb {
	private Logger log = Logger.getLogger(LinkedinHomePage.class);

	// create a constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a.nav__logo-link")
	private WebElement linkedinLogo;

	@FindBy(xpath = "//*[@class='nav__button-secondary']")
	private WebElement signinLink;
	// assert page title tag

	@FindBy(css = "h1[class*='main-heading']")
	private WebElement linkedinHomePageHeaderText;
	
	String homePageTitle = "LinkedIn: Log In or Sign Up";
	String signinPageTitle = "LinkedIn Login, Sign in | LinkedIn";

	@FindBy(xpath = "//h1[contains(class,'header__content__heading')]")
	private WebElement signInheadertext;

	@FindBy(id = "username")
	private WebElement usernameEditbox;

	@FindBy(id = "password")
	private WebElement passwordEditbox;

	// @FindBy(xpath="//*[@id=\"organic-div\"]/form/div[3]/button")
	@FindBy(css = "button[class*='btn__primary--large from__button--floating']") 
   private WebElement signinButton;

	public void verifyLinkedinHomePageTitle() {
		log.debug("Verifying the Linkedin Home Page Title" + homePageTitle);
		wait.until(ExpectedConditions.titleContains(homePageTitle));
		Assert.assertEquals(driver.getTitle(), homePageTitle);
	}

	public void verifyLinkedinLogo() throws InterruptedException {
		log.debug("verify linkedin Logo is present in HomePage");
		isVisible(linkedinLogo);
		Assert.assertTrue(isDisplayedElement(linkedinLogo));
	}

	public void clickSigninLink() throws InterruptedException {
		log.debug("Click on signin Link in Linked in homepage");
		highlightAndClick(signinLink);
	}

	public void verifyLinkedinHomePageHeaderText() throws InterruptedException {
		log.debug("verify verifyLinkedinHomePageHeaderText is present in HomePage");
		isVisible(linkedinHomePageHeaderText);
		Assert.assertTrue(isDisplayedElement(linkedinHomePageHeaderText));
	}

	public void verifyLinkedinLoginPageTitle() {
		log.debug("Verifying thelinkedin  SignInPageTitle " + signinPageTitle);
		wait.until(ExpectedConditions.titleContains(signinPageTitle));
		Assert.assertEquals(driver.getTitle(), signinPageTitle);
	}

	public void verifySignInHeaderText() throws InterruptedException {
		log.debug("verify the sign in headertext in loginpage");
		isVisible(signInheadertext);
		Assert.assertTrue(isDisplayedElement(signInheadertext));
	}
	public void clickSiginButton() throws InterruptedException {
		log.debug("click on signin button in linkedin in login page");
		highlightAndClick(signinButton);
	}

	public LikedinLoggedinPage doLogIn( String uname, String pwd)throws InterruptedException {
		log.debug("login to linkedin page");
		log.debug("type the username" +uname+"in username editbox");
		sendKey(usernameEditbox, uname);
		log.debug("type the password"+pwd+" in password editbox");
		sendKey(passwordEditbox, pwd);
		log.debug("Click on signin button ");
		clickSiginButton();
		return new LikedinLoggedinPage();
	}
	
}
