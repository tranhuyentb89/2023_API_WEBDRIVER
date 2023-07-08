package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_Handle_TextboxTextArea {
	WebDriver driver;
	String email = "huyentt" + randomNumber() + "@gmail.com";
	String username, password;

	String customerName, gender, DOB, address, city, state, pin, mobile, emailCus, passwordCus;
	String addressEdit, cityEdit, stateEdit, pinEdit, mobileEdit;
	String customerID;

	// locatoer element
	By customerNameLocator = By.xpath("//input[@name='name']");
	By genderLocator = By.xpath("//input[@value='f']");
	By DOBLocator = By.xpath("//input[@id='dob']");
	By addressLocator = By.xpath("//textarea[@name='addr']");
	By cityLocator = By.xpath("//input[@name='city']");
	By stateLocator = By.xpath("//input[@name='state']");
	By pinLocator = By.xpath("//input[@name='pinno']");
	By mobileLocator = By.xpath("//input[@name='telephoneno']");
	By emailCusLocator = By.xpath("//input[@name='emailid']");
	By passwordCusLocator = By.xpath("//input[@name='password']");
	By submitBtnLocator = By.xpath("//input[@value='Submit']");

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		customerName = "Selenium Online";
		gender = "female";
		DOB = "2000-10-10";
		address = "Thanh Xuan";
		city = "Ha Noi";
		state = "Thu Duc";
		pin = "600000";
		mobile = "0987654341";
		emailCus = "huyentt" + randomNumber() + "@gmail.com";
		passwordCus = "123456";
		
		addressEdit ="Nguyen Trai";
		cityEdit ="Ha Nam";
		stateEdit ="Thu Kho";
		mobileEdit ="0987453214";
	}

	//@Test
	public void TC_01_register() {
		driver.get("https://demo.guru99.com/v4/index.php");
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//form[@name='frmLogin']//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//form[@name='frmLogin']//input[@name='btnLogin']")).click();
		username = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
	}

	//@Test
	public void TC_02_Login() {
		driver.get("https://demo.guru99.com/v4/index.php");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	}

	//@Test
	public void TC_03_createNewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//p[text()='Add New Customer']")).getText(),
				"Add New Customer");

		driver.findElement(customerNameLocator).sendKeys(customerName);
		driver.findElement(genderLocator).click();
		driver.findElement(DOBLocator).sendKeys(DOB);
		driver.findElement(addressLocator).sendKeys(address);
		driver.findElement(cityLocator).sendKeys(city);
		driver.findElement(stateLocator).sendKeys(state);
		driver.findElement(pinLocator).sendKeys(pin);
		driver.findElement(mobileLocator).sendKeys(mobile);
		driver.findElement(emailCusLocator).sendKeys(emailCus);
		driver.findElement(passwordCusLocator).sendKeys(passwordCus);
		driver.findElement(submitBtnLocator).click();
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), DOB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), emailCus);

		//Nhan edit customer
		
//		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
//		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	}

	//@Test
	public void TC_04_DeleteCustomer() {
		driver.findElement(By.xpath("//a[text()='Delete Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void TC_02_TextboxAreaBox() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
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
