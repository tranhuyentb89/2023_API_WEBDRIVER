package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_09_iFrame {
	WebDriver driver;
	JavascriptExecutor jsExecuter;
	WebDriverWait waitExplicit;

	@BeforeTest
	public void beforeTest() {

//        driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void Testcase01() {
		driver.get("https://www.hdfcbank.com/");
		By popup = By.xpath("//div[@id='CCContactlessinactD']//a//img");
		Assert.assertTrue(driver.findElement(popup).isDisplayed());
		driver.findElement(popup).click();
	}

	public void Testcase_01_Popup() throws Exception {
		driver.get("https://ngoaingu24h.vn/");
		WebElement popupLogin = driver.findElement(By.xpath("//div[@class='modal-content']//h4[text()='Đăng nhập']"));
		Assert.assertFalse(popupLogin.isDisplayed());

		WebElement loginBtn = driver.findElement(By.xpath("//button[@class='login_ icon-before']"));
		loginBtn.click();
		Thread.sleep(1500);
		Assert.assertTrue(popupLogin.isDisplayed());

		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='account-input']"))
				.sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@class='modal fade in']//input[@id='password-input']"))
				.sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[text()='Đăng nhập']")).click();
		System.out.println("Text la "
				+ driver.findElement(By.xpath("//div[contains(text(),'Tài khoản không tồn tại!')]")).getText());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[contains(text(),'Tài khoản không tồn tại!')]")).isDisplayed());
	}

	public void TC_02_FixedPopup_InDom() throws Exception {
		driver.get("https://skills.kynaenglish.vn/");

		// Assert.assertFalse(popupLogin.isDisplayed());
		driver.findElement(By.xpath("//nav[@id='navDesktop']//a[text()='Đăng nhập']")).click();
		WebElement popupLogin = driver
				.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//div[@class='right']"));
		Assert.assertTrue(popupLogin.isDisplayed());

		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
		Thread.sleep(1000);
		String errorText = driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText();
		System.out.println("error msg la " + errorText);
		Assert.assertEquals(errorText, "Sai tên đăng nhập hoặc mật khẩu");
	}

	public void TC_03_FixedInDom() throws Exception {
		driver.get("https://tiki.vn/");
		driver.findElement(By.xpath("//span[contains(text(),'Tài khoản')]")).click();
		Thread.sleep(1000);
		WebElement popupLogin = driver.findElement(By.xpath("//button[@class='btn-close']//parent::div"));
		Assert.assertTrue(popupLogin.isDisplayed());
		driver.findElement(By.xpath("//p[contains(text(),'Đăng nhập bằng email')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
		WebElement emailErr = driver.findElement(By.xpath("//span[contains(text(),'Email không được để trống')]"));
		WebElement passErr = driver.findElement(By.xpath("//span[contains(text(),'Mật khẩu không được để trống')]"));
		Assert.assertTrue(emailErr.isDisplayed());
		Assert.assertTrue(passErr.isDisplayed());
		driver.findElement(By.xpath("//button[@class='btn-close']")).click();
		Thread.sleep(1000);
	}

	public void TC_04_FixPopupNotInDOM() throws Exception {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		Thread.sleep(1500);
		WebElement popupRegister = driver.findElement(By.xpath("//div[@class='registration_redesign']"));
		Assert.assertTrue(popupRegister.isDisplayed());

	}

	public void TC_07_PopupNotInDom() throws Exception {
		driver.get("https://dehieu.vn/");
		Thread.sleep(5000);
		List<WebElement> popup = driver.findElements(By.xpath("//div[@class='popup-content']"));
		int popupSize = popup.size();
		if (popupSize > 0) {
			System.out.println(popupSize);
			driver.findElement(By.xpath("//div[@class='popup-content']//button[@class='close']")).click();
			Thread.sleep(1000);
		} else {
			System.out.println("Hello");
		}
	}

	public void TC_08_iFrame() throws Exception {
		driver.get("https://skills.kynaenglish.vn/");
		WebElement faceIframe = driver.findElement(By.xpath("//div[@class='face-content']//iframe"));
		driver.switchTo().frame(faceIframe);
		WebElement verifyText = driver.findElement(By.xpath("//div[text()='165K likes']"));
		Assert.assertTrue(verifyText.isDisplayed());
		driver.switchTo().defaultContent();
		WebElement chatFrame = driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']"));
		Thread.sleep(2000);
		driver.switchTo().frame(chatFrame);
		driver.findElement(By.cssSelector(".border_overlay")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".input_name")).sendKeys("John Wick");
		driver.findElement(By.cssSelector(".input_phone")).sendKeys("0389954259");
		selectItemInCustomDropdown("//select[@id='serviceSelect']", "//select[@id='serviceSelect']//option", "TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("Java Boostrap");
		driver.switchTo().defaultContent();
		WebElement txtSearch = driver.findElement(By.xpath("//input[@id='live-search-bar']"));
		txtSearch.sendKeys("Excel");
		Thread.sleep(2000);
		txtSearch.sendKeys(Keys.ENTER);
		
	}

	@Test
	public void TC_09_Frame() throws Exception {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame(driver.findElement(By.xpath("//frame")));
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("huyentt09");
		driver.findElement(By.cssSelector(".login-btn")).click();
		Thread.sleep(2000);
		WebElement password = driver.findElement(By.xpath("//input[@id='keyboard']"));
		Assert.assertTrue(password.isDisplayed());
	}
	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValue)
			throws Exception {
		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		jsExecuter.executeScript("arguments[0].click();", parentDropdown);

		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		for (WebElement childElement : allItems) {
			if (childElement.getText().contains(expectedValue)) {
				jsExecuter.executeScript("arguments[0].scrollIntoView(true);", childElement);
				Thread.sleep(1500);
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					jsExecuter.executeScript("arguments[0].click();", childElement);
				}
				Thread.sleep(3000);
				break;
			}
		}
	}
	
	

	public boolean isElementDisplayed(WebElement element) {
		if (element.isDisplayed()) {
			System.out.println("Element " + element + " isDisplayed");
			return true;
		} else {
			System.out.println("Element " + element + " isNotDisplayed");
			return false;
		}
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
