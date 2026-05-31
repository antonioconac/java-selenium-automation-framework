package FunkyProject.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FunkyProject.TestComponents.BaseTest;
import FunkyProject.pageobjects.CartPage;
import FunkyProject.pageobjects.CheckoutPage;
import FunkyProject.pageobjects.ConfirmationPage;
import FunkyProject.pageobjects.ProductPage;
import FunkyProject.pageobjects.OrderPage;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		ProductPage productPage = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(input.get("product"));

		CartPage cartPage = productPage.goToCart();
		Boolean match =  cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductPage productPage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage ordersPage = productPage.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//FunkyProject//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	//Extent reports - 
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"anshika@gmail.com", "Iamking@000", "ZARA COAT 3"}, {"anshika@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"} };
//	}
	
//  using HashMaps
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "anshika@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
	

}
