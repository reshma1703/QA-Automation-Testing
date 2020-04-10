import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationTask {

	public static String baseUrl = "http://automationpractice.com/index.php";
	
	public static WebDriver driver;
	
	public static void scrollDown(String xPath) {
		
		WebElement Element = driver.findElement(By.id(xPath));
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("argument[0].scrollIntoView()", Element);
		
	}
	
	@BeforeTest
	public static void setBaseURL() {
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		driver = new ChromeDriver();
		// Open Website
		driver.get(baseUrl);
		// Maximize browser
		driver.manage().window().maximize();
		// Pageload timeout
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
    
	@Test
	public static void verifyHomepageTitle()
	{
		String expectedtitle = "My Store";
		String actualtitle = driver.getTitle();
		Assert.assertEquals(expectedtitle, actualtitle);
	}
	
	@Test
	public static void accountCreation()
	{
		//click on login button
		driver.findElement(By.xpath("//a[@class='login']")).click();
		//verify login page title
		String expectedtitle = "Login - My Store";
		String actualtitle = driver.getTitle();
		Assert.assertEquals(expectedtitle, actualtitle);
		//fill email address
		driver.findElement(By.id("email_create")).sendKeys("dijkstra@gmail.com");
		//click on create button
		driver.findElement(By.cssSelector("#SubmitCreate > span")).click();
		//select gender
		driver.findElement(By.id("id_gender2")).click();
		//fill first name
		driver.findElement(By.id("customer_firstname")).sendKeys("reshma");
		//fill last name
		driver.findElement(By.id("customer_lastname")).sendKeys("raghuvanshi");
		//fill password
		driver.findElement(By.id("passwd")).sendKeys("user1234");
		//select days in DOB
		Select birthDay = new Select(driver.findElement(By.id("days")));
		birthDay.selectByValue("17");
		//select date in DOB
		Select birthMonth = new Select(driver.findElement(By.id("months")));
		birthMonth.selectByValue("2");
		//select year in DOB
		Select birthYear = new Select(driver.findElement(By.id("years")));
		birthYear.selectByValue("1993");
		//select company
		AutomationTask.scrollDown("company");
		driver.findElement(By.id("company")).sendKeys("company");
		//select address
		AutomationTask.scrollDown("address1");
		driver.findElement(By.id("address1")).sendKeys("address1");
		//select city
		AutomationTask.scrollDown("city");
		driver.findElement(By.id("city")).sendKeys("Los Angeles");
		//select state
		AutomationTask.scrollDown("id_state");
		Select state = new Select(driver.findElement(By.id("id_state")));
		state.selectByValue("5");
		//select postal code
		AutomationTask.scrollDown("postcode");
		driver.findElement(By.id("postcode")).sendKeys("90002");
		//select country
		AutomationTask.scrollDown("id_country");
		Select country = new Select(driver.findElement(By.id("id_country")));
		country.selectByValue("21");
		//Mobile number
		AutomationTask.scrollDown("phone_mobile");
		driver.findElement(By.id("phone_mobile")).sendKeys("8372827837");
		//signout from the page
		driver.findElement(By.xpath("//a[@class='logout']"));
		
	}
	
	@Test
	public static void login()
	{
		//click on login
		driver.findElement(By.xpath("//a[@class='login']")).click();
		//enter email id
		driver.findElement(By.id("email")).sendKeys("dijkstra@gmail.com");
		//enter password
		driver.findElement(By.id("passwd")).sendKeys("user1234");
		//click on sign in
		driver.findElement(By.cssSelector("@SubmitLogin > span")).click();
		
		//verify login details
		String expectedUrl = driver.getCurrentUrl();
		String actualUrl = "http://automationpractice.com/index.php?controller=my-account";
		Assert.assertEquals(expectedUrl, actualUrl);
		WebElement loginName = driver.findElement(By.xpath("//a[@class='account']"));
		String actualname =loginName.getText();
		Assert.assertEquals(loginName, actualname);
			
	}
	
	@Test
	public static void selectProduct()
	{
		try {
		//click on women
		driver.findElement(By.linkText("Women")).click();
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//img[@class='replace-2x img-responsive']){2}"));
		action.moveToElement(element).perform();
		Thread.sleep(2000);
		//click on quick view
		driver.findElement(By.xpath("//a[@class='quick-view']")).click();
		//fill the quantity
		driver.findElement(By.id("quantity-wanted")).click();
		driver.findElement(By.id("quanted-wanted")).sendKeys("2");
		//click on add to cart
		driver.findElement(By.id("add_to_cart")).click();
		//click on proceed to checkout
		WebElement checkout = driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
		checkout.click();
		//agree terms and conditions
		if ( driver.findElement(By.id("cgv")).isSelected() );
		{
			driver.findElement(By.id("cgv")).click();
			System.out.println("Checkbox is selected");
		}
		
		{
		
			System.out.println("Checkbox is not selected");
		}
		  
		//checkout from shopping page
		driver.findElement(By.name("processCarrier")).click();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		@AfterTest
		public static void teardown()
		{
			driver.close();
		}
		

}