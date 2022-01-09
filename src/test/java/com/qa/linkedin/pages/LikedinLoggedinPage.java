package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LikedinLoggedinPage extends BasePageWeb {
	private Logger log = Logger.getLogger(LikedinLoggedinPage.class);

	// create a constructor
	public LikedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[contains(@class,'feed-identity-module__member')]")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//*[contains(@class,'global-nav__me-photo ghost-person ember-view')]")
	private WebElement profileImageIcon;

	@FindBy(xpath="//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;

	@FindBy(xpath="//input[contains([@class,'search-global-typeahead__input']")
	private WebElement searchEditbox;

	private String loggedinPageTitle="Feed | LinkedIn";
	public void verifyLinkedinLoggedinPageTitle() {
		log.debug("Verifying the Linkedin loggedin Page Title" + loggedinPageTitle);
		wait.until(ExpectedConditions.titleContains(loggedinPageTitle));
		Assert.assertTrue(driver.getTitle().contains(loggedinPageTitle));
	}

	public void verifyProfilerailCard() throws InterruptedException {
		log.debug("verify linkedin loggedin page profileRailCard");
		isVisible(profileRailCard);
		Assert.assertTrue(isDisplayedElement(profileRailCard));
	}
  
	public void doLogout() throws InterruptedException {
		log.debug("performing the logout operation.....");
		log.info("wait and click on profile imageicon");
		isVisible(profileImageIcon);
		highlightAndClick(profileImageIcon);
		log.info("wait and click on signout link");
		isVisible(signOutLink);
		highlightAndClick(signOutLink);
	}
	
public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException   {
		log.debug("Perform the people serch after login");
		clearText(searchEditbox);
		log.debug("type the value in search editbox");
		sendKey(searchEditbox,keyword);
		log.debug("press the Enter Key");
		searchEditbox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}
	
	
	
}
