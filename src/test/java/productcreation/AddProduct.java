package productcreation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import base.LaunchBrowser;


public class AddProduct extends LaunchBrowser{

	@Test
	public static void AddItems() throws InterruptedException {
		//List of products and details to be created
		String name = "Product";
		String description = "description of ";
		float initalPrice = 10.97f;
		float initalSalePrice = 9.97f;
		int sku = 00;
		int numberOfProductNeed = 5;

		//Login to WordPress Admin page
		WebElement login = driver.findElement(By.id("user_login"));
		login.sendKeys(prop.getProperty("username"));
		WebElement password = driver.findElement(By.id("user_pass"));
		password.sendKeys(prop.getProperty("password"));
		WebElement loginButton = driver.findElement(By.id("wp-submit"));
		loginButton.click(); 

		//WooCommerce Product creation page
		WebElement product = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[9]/a/div[3]"));
		product.click();
		Thread.sleep(1000L);

		//Creating the Product
		for (int i=1;i<=numberOfProductNeed; i++) {
			//Add Product title
			WebElement subMenu = driver.findElement(By.partialLinkText("Add New"));
			subMenu.click();
			WebElement productTitle = driver.findElement(By.id("title"));
			productTitle.sendKeys(name + i);

			//Add Product description 
			driver.switchTo().frame(0);
			WebElement pDescription = driver.findElement(By.cssSelector("body#tinymce"));
			pDescription.sendKeys("This section contains detail " + description + name + i);
			driver.switchTo().defaultContent();

			//Add Regular Price 
			WebElement regularPrice = driver.findElement(By.id("_regular_price"));
			float aSwap = initalPrice;
			regularPrice.sendKeys(String.valueOf(aSwap));
			float dSwap = initalPrice + (initalPrice * 0.1f);
			initalPrice = dSwap;

			//Add Sale Price start with 9.97
			WebElement salePrize = driver.findElement(By.id("_sale_price"));
			float bSwap = initalSalePrice;
			salePrize.sendKeys(String.valueOf(bSwap));
			float cSwap = initalSalePrice + (initalSalePrice * 0.1f);
			initalSalePrice = cSwap;

			//Switch to Inventory Menu and add SKU values
			WebElement inventory = driver.findElement(By.xpath("//span[text()='Inventory']"));
			inventory.click();
			WebElement skuValue = driver.findElement(By.id("_sku"));
			skuValue.sendKeys(String.valueOf(sku+i));

			//Scroll up and publish
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,-950)","");
			Thread.sleep(3000L);
			WebElement publishButton = driver.findElement(By.cssSelector("input#publish"));
			publishButton.click();
		}
	}
}
