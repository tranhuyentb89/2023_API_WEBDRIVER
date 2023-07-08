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

public class Topic_06_WebBrowser_WebElement2 {
	WebDriver driver;
	By firtNameXpath = By.xpath("//div[@class='account-create']//input[@id='firstname']");
	By middleNameXpath = By.xpath("//div[@class='account-create']//input[@id='middlename']");
	By lastNameXpath = By.xpath("//div[@class='account-create']//input[@id='lastname']");
	By emailXpath = By.xpath("//div[@class='account-create']//input[@id='email_address']");
	By passwordXpath = By.xpath("//div[@class='account-create']//input[@id='password']");
	By cfmpassXpath = By.xpath("//div[@class='account-create']//input[@id='confirmation']");
	By regisBtn = By.xpath("//form[@id='form-validate']//span[contains(text(),'Register')]");

	String firstName = "Tran";
	String middleName = "Thi";
	String lastName = "Huyen";
	String email = "tranhuyentb" + randomNumber() +"@gmail.com";
	String pass = "123456";
	String cfmPass = "123456";

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_LoginWithEmptyEmailPass() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Login')]")).click();

		String emailErr = driver
				.findElement(By.xpath("//form[@id='login-form']//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErr, "This is a required field.");
		String passErr = driver.findElement(By.xpath("//form[@id='login-form']//div[@id='advice-required-entry-pass']"))
				.getText();
		Assert.assertEquals(passErr, "This is a required field.");
	}

	//@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='email']")).sendKeys("123456@12365454");
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Login')]")).click();
		String emailErr = driver
				.findElement(By.xpath("//form[@id='login-form']//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErr, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	//@Test
	public void TC_03_loginWithPassLesserThan6() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Login')]")).click();
		String passErr = driver.findElement(By.xpath("//form[@id='login-form']//div[@id='advice-required-entry-pass']"))
				.getText();
		Assert.assertEquals(passErr, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	//@Test
	public void TC_04_loginWithIncorrectEmailPass() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='pass']")).sendKeys("12345676");
		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Login')]")).click();

		String errLogin = driver
				.findElement(By.xpath("//div[@class='main']//span[contains(text(),'Invalid login or password.')]"))
				.getText();
		Assert.assertEquals(errLogin, "Invalid login or password.");

	}

	@Test
	public void TC_05_CreateNewAccount() throws InterruptedException {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Create an Account')]")).click();
		driver.findElement(firtNameXpath).sendKeys(firstName);
		driver.findElement(middleNameXpath).sendKeys(middleName);
		driver.findElement(lastNameXpath).sendKeys(lastName);
		driver.findElement(emailXpath).sendKeys(email);
		driver.findElement(passwordXpath).sendKeys(pass);
		driver.findElement(cfmpassXpath).sendKeys(cfmPass);
		driver.findElement(regisBtn).click();

		String successMsg = driver.findElement(By.xpath(
				"//div[@class='my-account']//span[contains(text(),'Thank you for registering with Main Website Store.')]"))
				.getText();
		Assert.assertEquals(successMsg, "Thank you for registering with Main Website Store.");

		String msg = driver.findElement(By.xpath("//div[@class='box-content']//a[text()='Change Password']/parent::p")).getText();
		System.out.println(msg);
		Assert.assertEquals(msg, firstName+" " + middleName +" " + lastName +"\n" + email +"\n" + "Change Password");
		
		driver.findElement(By.xpath("//header[@id='header']//span[contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[@title='Log Out']")).click();
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_06_Login() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='pass']")).sendKeys(pass);
		driver.findElement(By.xpath("//form[@id='login-form']//span[contains(text(),'Login')]")).click();

	}

	
	@AfterTest
	public void afterTest() {
		driver.close();
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
}
