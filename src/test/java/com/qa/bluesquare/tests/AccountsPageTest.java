package com.qa.bluesquare.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.bluesquare.utils.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accountPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountPageTitleTest() {
		String title = accPage.getAccPageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accPageFooterNoteTest() {
		Assert.assertTrue(accPage.getAccFooterNote().contains("naveenopencart © 20221"));
	}

	@Test
	public void accPageLogoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void accPageLogoTest() {
		Assert.assertTrue(accPage.isAccLogoDisplayed());
	}

	@Test
	public void accPageSectionListTest() {
		List<String> secList = accPage.getAccSectionList();
		softAssert.assertEquals(accPage.getAccSectionList().size(), Constants.ACC_PAGE_SECTION_COUNT);
		softAssert.assertEquals(secList, Constants.ACC_PAGE_SECTION_LIST);
		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "iMac" }, { "MacBook Pro" }, { "MacBook Air" }, { "Samsung SyncMaster 941BW" },
				{ "Palm Treo Pro" } };

	}

	@Test(dataProvider = "productData")
	public void searchTest(String productToBeSearch) {
		searchResultPage = accPage.doSearch(productToBeSearch);
		Assert.assertTrue(searchResultPage.getSearchResultCount() > 0);
	}

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { { "MacBook", "MacBook Pro" }, { "MacBook", "MacBook Air" },
				{ "Apple", "Apple Cinema 30\"" }, { "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProduct) {
		searchResultPage = accPage.doSearch(productName);
		searchResultPage.selectProduct(mainProduct);
	}

}
