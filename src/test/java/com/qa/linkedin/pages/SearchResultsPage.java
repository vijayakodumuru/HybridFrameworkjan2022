package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage  extends BasePageWeb {
	private Logger log = Logger.getLogger(SearchResultsPage.class);

	// create a constructor
	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='app-aware-link'][contains(.,'See all people results')]")
	private WebElement seeAllPeopleResultsLink;
	
	@FindBy(xpath = "//div[contains(@class,'pb2 t-black--light t-14')]")
	private WebElement seachResutlText;

	@FindBy(xpath="//ul[contains(@class,'global-nav__primary-item')]/li/a")
	private WebElement homeTab;
   
	String searchResultPageTitle="Search | LinkedIn";
	
	public void verifySearchResultPageTitle() {
		log.debug("wait for the search result page Title");
		wait.until(ExpectedConditions.titleContains(searchResultPageTitle));
		Assert.assertTrue(driver.getTitle().contains(searchResultPageTitle));
	}
 public long getResultCount() throws InterruptedException {
	 verifySearchResultPageTitle();
	 log.debug("wait for the seeAllPeople result link");
	 if(isElementPresent(By.xpath("//a[@class='app-aware-link'][contains(.,'See all people results')]")));
		 {
	
	 wait.until(ExpectedConditions.visibilityOf(seeAllPeopleResultsLink));
	 log.debug(" click on see all people resukt link");
	 highlightAndClick(seeAllPeopleResultsLink);
	 return getResult();
     }/**else {
	  return getResult();
  }**/
  }


private long getResult() throws InterruptedException {
	wait.until(ExpectedConditions.visibilityOf(seachResutlText));
	log.debug("fetch the results text");
	String txt=getText(seachResutlText);
	log.debug("Serch resukt text is :"+txt);
	String[] str=txt.split("//s");
	log.debug("results count text in string format is:"+str[1]);
	//convert string object into long primitive format
	long count=Long.parseLong(str[1].replace(",", ""));
	return count;
}
public void clickHomeTab() throws InterruptedException {
	log.debug("click on Hometab");
	highlightAndClick(homeTab);
	//ClickUsingExecutor(homeTab);
}
}
