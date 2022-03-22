package AddToCart;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.LaunchBrowser;

public class LoadCart extends LaunchBrowser{
	@Test
	public void AddToCart() throws InterruptedException {
		// apply wait to find Elements visibility
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		//product to be added to Cart made as list
		String itemsNeed[] = {"Product1", "Product2","Product3"};
		List<String> clickableItem = Arrays.asList(itemsNeed);
		int items = clickableItem.size();
		int j =0;
		
		//Adding the product to Cart
		List<WebElement> products = driver.findElements(By.cssSelector("h2.woocommerce-loop-product__title"));
		for (int i=0;i<products.size();i++) {
			String name = products.get(i).getText();
			if(clickableItem.contains(name)){
				WebElement addto = driver.findElements(By.linkText("Add to cart")).get(i);
				addto.click();
				Thread.sleep(2000L);
				j++;
				if(j==items) {
					break;
				}
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.added_to_cart.wc-forward")));
		driver.findElement(By.cssSelector("a.added_to_cart.wc-forward")).click();
		Thread.sleep(2000L);
		
		//Finding total value of product in the Cart
		WebElement amount = driver.findElement(By.cssSelector("tr.cart-subtotal"));
		String split = amount.getText().split(" ")[1].trim();
		String split1 = split.substring(1);
		double value = Double.parseDouble(split1);
		
		//Increasing the quantity of product as it does'nt not reach 100 or more
		while (value<100.0){
			//increase the number of product
			List<WebElement> quantity = driver.findElements(By.cssSelector("input.input-text.qty.text"));
			for (WebElement eachProduct : quantity) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.input-text.qty.text")));
				eachProduct.clear();
				eachProduct.sendKeys(String.valueOf(4));
				Thread.sleep(4000L);
				value++;
			}
			//Scrolling down to find 'update cart' button
			Thread.sleep(4000L);
			JavascriptExecutor jse =(JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,400)","");
			WebElement updateCart = driver.findElement(By.name("update_cart"));
			updateCart.click();
			Thread.sleep(4000L);
			
			//Finding the total amount again to checkout
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr.cart-subtotal")));
			WebElement currentAmount = driver.findElement(By.cssSelector("tr.cart-subtotal"));
			String splt2 = currentAmount.getText().split(" ")[1].trim();
			String splt3 = splt2.substring(1);
			Thread.sleep(4000L);
			double value1 = Double.parseDouble(splt3);
			
			//checkout once condition is meat
			if (value1 > 100.00) {
				driver.findElement(By.cssSelector("a.checkout-button.button.alt.wc-forward")).click();
			}
			
			//Scrolling down at checkout page and filling the billing details
			JavascriptExecutor jser =(JavascriptExecutor)driver;
			jser.executeScript("window.scrollBy(0,400)","");
			driver.findElement(By.id("billing_first_name")).sendKeys("Dharshini");
			driver.findElement(By.id("billing_last_name")).sendKeys("C");
			
			//finding country 
			List<WebElement> country = driver.findElements(By.cssSelector("li.select2-results__option"));
			String cnty= "India";
			for (int q=0;q<country.size();q++) {
				String name1 = country.get(q).getText();
				if(cnty.contains(name1)){
					WebElement add = driver.findElements(By.linkText("India")).get(q);
					add.click();
				}
			}
			driver.findElement(By.id("billing_address_1")).sendKeys("F.NO.401, Sai Pride, Bhuvanswari Nagar");
			driver.findElement(By.id("billing_address_2")).sendKeys("Dasarhalli ,Hebbal");
			driver.findElement(By.id("billing_city")).sendKeys("Bangalore");
			List <WebElement> state = driver.findElements(By.cssSelector("li.select2-results__option"));
			String stat= "Karnataka";
			for (int r=0;r<state.size();r++) {
				String name2 = state.get(r).getText();
				if(stat.contains(name2)){
					WebElement add1 = driver.findElements(By.linkText("Karnataka")).get(r);
					add1.click();
				}
			}

			driver.findElement(By.id("billing_postcode")).sendKeys("560024");
			driver.findElement(By.id("billing_phone")).sendKeys("9876543210");
			driver.findElement(By.id("billing_email")).sendKeys("abc@gmail.com");
			JavascriptExecutor jsee =(JavascriptExecutor)driver;
			jsee.executeScript("window.scrollBy(0,-100)","");
			Thread.sleep(3000L);
			WebElement cash = driver.findElement(By.cssSelector("li.payment_method_cod"));
			cash.click();
			Thread.sleep(3000L);
			
			driver.findElement(By.id("place_order")).click();
		} 
	}
}