package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_06_WebBrowser_WebElement {
	WebDriver driver;
	By emailDisplay = By.xpath("//input[@id='mail']");
	By ageDisplay = By.xpath("//input[@id='under_18']");
	By eduDisplay = By.xpath("//textarea[@id='edu']");
	By btnDisable = By.xpath("//button[@id='button-disabled']");
	By developmentCheckbox = By.xpath("//div[@class='container']//input[@id='development']");
	
	By emailMailChip = By.xpath("//form[@id='signup-form']//input[@id='email']");
	String email = "tranhuyentb" + randomNumber();
	

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_VerifyURL() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String urlPage = driver.getCurrentUrl();
		Assert.assertEquals(urlPage, "http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Create an Account')]")).click();
		String urlRegisterPage = driver.getCurrentUrl();
		Assert.assertEquals(urlRegisterPage, "http://live.techpanda.org/index.php/customer/account/create/");
	}

	// @Test
	public void TC_02_verifyTitle() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String titleAccountPage = driver.getTitle();
		Assert.assertEquals(titleAccountPage, "Customer Login");

		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Create an Account')]")).click();
		String titleRegisterPage = driver.getTitle();
		Assert.assertEquals(titleRegisterPage, "Create New Customer Account");
	}

	// @Test
	public void TC_03_navigateBackForward() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Create an Account')]")).click();
		String urlRegisterPage = driver.getCurrentUrl();
		Assert.assertEquals(urlRegisterPage, "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		String urlPage = driver.getCurrentUrl();
		Assert.assertEquals(urlPage, "http://live.techpanda.org/index.php/customer/account/login/");

		driver.navigate().forward();
		String titleRegisterPage = driver.getTitle();
		Assert.assertEquals(titleRegisterPage, "Create New Customer Account");

	}

	// @Test
	public void TC_04_getPageSource() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		boolean pageSource = driver.getPageSource().contains("Login or Create an Account");
		System.out.println(pageSource);
		Assert.assertTrue(pageSource);

		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Create an Account')]")).click();
		boolean pageSource2 = driver.getPageSource().contains("Create an Account");
		System.out.print(pageSource2);
		Assert.assertTrue(pageSource2);
	}

	// @Test
	public void TC_05_isDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (isElementDisplayed(emailDisplay)) {
			driver.findElement(emailDisplay).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(eduDisplay)) {
			driver.findElement(eduDisplay).sendKeys("Automation Testing");
		}
		if (isElementDisplayed(ageDisplay)) {
			driver.findElement(ageDisplay).click();
		}
	}

	//@Test
	public void TC_06_isEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnable(ageDisplay));
		Assert.assertFalse(isElementEnable(btnDisable));
	}

	//@Test
	public void TC_07_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		checkToCheckbox(ageDisplay);
		checkToCheckbox(developmentCheckbox);
		Assert.assertTrue(isElementSelected(ageDisplay));
		Assert.assertTrue(isElementSelected(developmentCheckbox));
		
		uncheckToCheckbox(developmentCheckbox);
		Assert.assertFalse(isElementSelected(developmentCheckbox));
	}
	
	//@Test
	public void TC_08_registerFunctionAtMailChip() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(emailMailChip).sendKeys(email);
	}
	


	@AfterTest
	public void afterTest() {
		driver.close();
	}

	public boolean isElementDisplayed(By byValue) {
		if (driver.findElement(byValue).isDisplayed()) {
			System.out.println("Element " + byValue + " isDisplayed");
			return true;
		} else {
			System.out.println("Element " + byValue + " isNotDisplayed");
			return false;
		}
	}

	public boolean isElementEnable(By byValue) {
		if (driver.findElement(byValue).isEnabled()) {
			System.out.println("Element " + byValue + " isEnable");
			return true;
		} else {
			System.out.println("Element " + byValue + " isDisable ");
			return false;
		}
	}

	
	public boolean isElementSelected(By byValue) {
		if (driver.findElement(byValue).isSelected()) {
			System.out.println("Element " + byValue + " isSelected");
			return true;
		} 
		else {
			System.out.println("Element " + byValue + " is de-Selected ");
			return false;
		}
	}

	public void checkToCheckbox(By byValue) {
		WebElement element = driver.findElement(byValue);
		if (!element.isSelected()) {
			element.click();
		}

	}

	public void uncheckToCheckbox(By byValue) {
		WebElement element = driver.findElement(byValue);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
}
