package FunkyProject.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import FunkyProject.TestComponents.BaseTest;
import FunkyProject.pageobjects.CartPage;
import FunkyProject.pageobjects.CheckoutPage;
import FunkyProject.pageobjects.ConfirmationPage;
import FunkyProject.pageobjects.ProductPage;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LogginErrorValidation() throws IOException, InterruptedException{
		String productName = "ZARA COAT 3";
		ProductPage productPage = landingPage.loginApplication("anshika@gmail.com", "Iamki000");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}
	
	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException, InterruptedException{
		String productName = "ZARA COAT 3";
		ProductPage productPage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		List<WebElement> products = productPage.getProductList();
		productPage.addProductToCart(productName);

		CartPage cartPage = productPage.goToCart();
		Boolean match =  cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
