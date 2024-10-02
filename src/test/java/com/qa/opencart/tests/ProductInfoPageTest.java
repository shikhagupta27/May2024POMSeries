package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getHeaderData() {
		return new Object[][] {
			{"macbook","MacBook Pro","MacBook Pro"},
			{"imac","iMac","iMac"},
			{"samsung","Samsung SyncMaster 941BW","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D","Canon EOS 5D"},
		};
	}
	
	@Test (dataProvider = "getHeaderData")
	public void productHeaderTest(String searchKey, String productName, String headerName) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(productHeader, headerName);
	}
	
	@Test
	public void productInfoTest() {
		resultsPage = accPage.doSearch("macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String,String> actProductDataMap = productInfoPage.getProductData();
		softAssert.assertEquals(actProductDataMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductDataMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductDataMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductDataMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductDataMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actProductDataMap.get("extaxprice"), "$2,000.00");
		softAssert.assertAll();
	}
	
	@DataProvider
	public Object[][] getProductImagesCountData() {
		return new Object[][] {
			{"macbook","MacBook Pro", 4},
			{"imac","iMac", 3},
			{"samsung","Samsung SyncMaster 941BW", 1},
			{"samsung","Samsung Galaxy Tab 10.1", 7},
			{"canon","Canon EOS 5D", 3},
		};
	}
	@Test (dataProvider = "getProductImagesCountData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
	}
	
	
}
