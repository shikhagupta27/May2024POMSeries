package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil elemUtil;
	
	private By searchHeader = By.cssSelector("div#content h2");
	private By searchResults = By.cssSelector("div.product-thumb");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		elemUtil = new ElementUtil(driver);
	}
	
	public String getSearchHeader() {
		String searchHeaderValue = elemUtil.waitForElementVisible(searchHeader, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		return searchHeaderValue;
	}
	
	public int getSearchResultsCount() {
		int resultCount = elemUtil.waitForElementsVisible(searchResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Search Result Count ==>" + resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("The product name is ==>" + productName);
		elemUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	
}
