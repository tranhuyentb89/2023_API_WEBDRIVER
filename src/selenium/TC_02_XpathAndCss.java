package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_02_XpathAndCss {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_registerWithEmptyData() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button")).click();
		String nameTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtFirstname-error']"))
				.getText();
		Assert.assertEquals(nameTxt, "Vui lòng nhập họ tên");

		String emailTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtEmail-error']")).getText();
		Assert.assertEquals(emailTxt, "Vui lòng nhập email");

		String emailRepeatTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtCEmail-error']"))
				.getText();
		Assert.assertEquals(emailRepeatTxt, "Vui lòng nhập lại địa chỉ email");

		String passTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtPassword-error']"))
				.getText();
		Assert.assertEquals(passTxt, "Vui lòng nhập mật khẩu");

		String passRepeatTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtCPassword-error']"))
				.getText();
		Assert.assertEquals(passRepeatTxt, "Vui lòng nhập lại mật khẩu");

		String phoneTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtPhone-error']")).getText();
		Assert.assertEquals(phoneTxt, "Vui lòng nhập số điện thoại.");

		driver.close();

	}

	//@Test
	public void TC_02_registerWithInvalidEmail() throws InterruptedException {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		WebElement nameTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtFirstname']"));
		nameTxt.sendKeys("Huyen");

		WebElement emailTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtEmail']"));
		emailTxt.sendKeys("huyentt@huyentt@.vn");

		WebElement emailCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCEmail']"));
		emailCTxt.sendKeys("huyentt@huyentt@.vn");

		WebElement passTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPassword']"));
		passTxt.sendKeys("123456");

		WebElement passCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCPassword']"));
		passCTxt.sendKeys("123456");

		WebElement phoneTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPhone']"));
		phoneTxt.sendKeys("0389954259");

		WebElement registerBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button"));
		registerBtn.click();

		String emailError = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtEmail-error']"))
				.getText();
		String cfmEmailError = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtCEmail-error']"))
				.getText();
		Assert.assertEquals(emailError, "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(cfmEmailError, "Email nhập lại không đúng");

	}
	
	//@Test
	public void TC_03_registerWithIncorrectConfirmEmail() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		WebElement nameTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtFirstname']"));
		nameTxt.sendKeys("Huyen");

		WebElement emailTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtEmail']"));
		emailTxt.sendKeys("huyentt@mpos.vn");

		WebElement emailCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCEmail']"));
		emailCTxt.sendKeys("huyentt@yopmail.com");

		WebElement passTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPassword']"));
		passTxt.sendKeys("123456");

		WebElement passCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCPassword']"));
		passCTxt.sendKeys("123456");

		WebElement phoneTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPhone']"));
		phoneTxt.sendKeys("0389954259");

		WebElement registerBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button"));
		registerBtn.click();
		
		String cfmEmailError = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtCEmail-error']"))
				.getText();
		Assert.assertEquals(cfmEmailError, "Email nhập lại không đúng");
		
	}
	
	//@Test
	public void TC_04_registerWithCharactorLessThan6() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		WebElement nameTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtFirstname']"));
		nameTxt.sendKeys("Huyen");

		WebElement emailTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtEmail']"));
		emailTxt.sendKeys("huyentt@yopmail.com");

		WebElement emailCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCEmail']"));
		emailCTxt.sendKeys("huyentt@yopmail.com");

		WebElement passTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPassword']"));
		passTxt.sendKeys("123");

		WebElement passCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCPassword']"));
		passCTxt.sendKeys("123");

		WebElement phoneTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPhone']"));
		phoneTxt.sendKeys("0389954259");

		WebElement registerBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button"));
		registerBtn.click();
		
		String passErr = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtPassword-error']"))
				.getText();
		Assert.assertEquals(passErr, "Mật khẩu phải có ít nhất 6 ký tự");
		String passCErr = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtCPassword-error']"))
				.getText();
		Assert.assertEquals(passCErr, "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	//@Test
	public void TC_05_registerWithIncorrectConfirmPw() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		WebElement nameTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtFirstname']"));
		nameTxt.sendKeys("Huyen");

		WebElement emailTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtEmail']"));
		emailTxt.sendKeys("huyentt@yopmail.com");

		WebElement emailCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCEmail']"));
		emailCTxt.sendKeys("huyentt@yopmail.com");

		WebElement passTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPassword']"));
		passTxt.sendKeys("123456");

		WebElement passCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCPassword']"));
		passCTxt.sendKeys("12344444");

		WebElement phoneTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPhone']"));
		phoneTxt.sendKeys("0389954259");

		WebElement registerBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button"));
		registerBtn.click();
		
//		String passErr = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtPassword-error']"))
//				.getText();
//		Assert.assertEquals(passErr, "Mật khẩu phải có ít nhất 6 ký tự");
		String passCErr = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtCPassword-error']"))
				.getText();
		Assert.assertEquals(passCErr, "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_registerWithInvalidPhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		WebElement nameTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtFirstname']"));
		nameTxt.sendKeys("Huyen");

		WebElement emailTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtEmail']"));
		emailTxt.sendKeys("huyentt@yopmail.com");

		WebElement emailCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCEmail']"));
		emailCTxt.sendKeys("huyentt@yopmail.com");

		WebElement passTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPassword']"));
		passTxt.sendKeys("123456");

		WebElement passCTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtCPassword']"));
		passCTxt.sendKeys("12344444");

		WebElement phoneTxt = driver.findElement(By.xpath("//form[@id='frmLogin']//input[@id='txtPhone']"));
		phoneTxt.sendKeys("098756232123");

		WebElement registerBtn = driver.findElement(By.xpath("//form[@id='frmLogin']//button"));
		registerBtn.click();
		
//		String passErr = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtPassword-error']"))
//				.getText();
//		Assert.assertEquals(passErr, "Mật khẩu phải có ít nhất 6 ký tự");
		String phoneErr = driver.findElement(By.xpath("//form[@id='frmLogin']//label[@id='txtPhone-error']"))
				.getText();
		Assert.assertEquals(phoneErr, "Số điện thoại phải từ 10-11 số.");

	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
