package selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_WindowTab {
	WebDriver driver;
	JavascriptExecutor jsExecuter;
	WebDriverWait waitExplicit;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void Testcase01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentPageTitle = driver.getTitle();
		String parentID = driver.getWindowHandle();
		System.out.println(parentID);
		driver.findElement(By.xpath("//a[contains(text(),'GOOGLE')]")).click();
		switchToWindowByID(parentID);
		String google = driver.getTitle();
		Assert.assertEquals(google, "Google");
		System.out.println(google);
		Assert.assertTrue(closeAllWindowWithoutParentWindows(parentID));
		Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");
		switchToWindowByTitle(parentPageTitle);
		System.out.println(parentPageTitle);
		driver.findElement(By.xpath("//a[contains(text(),'FACEBOOK')]")).click();
		switchToWindowByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		closeAllWindowWithoutParentWindows(parentID);
	}

//	@Test
	public void Testcase_02_() throws Exception {
		driver.get("https://kyna.vn/");
		String parentPageTitle = driver.getTitle();
		String parentID = driver.getWindowHandle();

		// Click vao icon Facebook
		clickToElementByJS("//div[@id='k-footer']//img[@alt='facebook']");
		Thread.sleep(3000);
		switchToWindowByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Home | Facebook");
//		closeAllWindowWithoutParentWindows(parentID);

		// Switch ve trang parent
		switchToWindowByTitle(parentPageTitle);
		System.out.println(driver.getTitle());

		// click vao youtube
		clickToElementByJS("//div[@id='k-footer']//img[@alt='youtube']");
		Thread.sleep(2000);
		switchToWindowByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");

		closeAllWindowWithoutParentWindows(parentID);

	}

//	@Test
	public void Testcase_03() {
		driver.get("http://live.techpanda.org/");
		String parentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
		driver.findElement(By.xpath(
				"//a[contains(text(),'Samsung Galaxy')]//parent::h2//following-sibling::div[@class='actions']//a[contains(text(),'Add to Compare')]"))
				.click();
		WebElement successMsg1 = driver.findElement(By.xpath("//span[contains(text(),'The product Samsung Galaxy has been added to compa')]"));
		Assert.assertTrue(successMsg1.isDisplayed());
		
		driver.findElement(By.xpath(
				"//a[contains(text(),'Sony Xperia')]//parent::h2//following-sibling::div[@class='actions']//a[contains(text(),'Add to Compare')]"))
				.click();
		WebElement successMsg2 = driver.findElement(By.xpath("//span[contains(text(),'The product Sony Xperia has been added to compa')]"));
		Assert.assertTrue(successMsg2.isDisplayed());
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToWindowByID(parentID);
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeAllWindowWithoutParentWindows(parentID);
		
		switchToWindowByTitle("Mobile");
		driver.findElement(By.xpath("//a[contains(text(),'Clear All')]")).click();
		driver.switchTo().alert().accept();
		WebElement clearMsg = driver.findElement(By.xpath("//span[contains(text(),'The comparison list was cleared.')]"));
		Assert.assertTrue(clearMsg.isDisplayed());
	}
	
	@Test
	public void Testcase_04() {
		driver.get("https://dictionary.cambridge.org/vi/");
		driver.findElement(By.xpath("//span[@class='tb' and contains(text(),'Đăng nhập')]")).click();
		String parentID = driver.getWindowHandle();
		switchToWindowByID(parentID);
		driver.findElement(By.xpath("//form[@class='gigya-login-form']//input[@value='Log in']")).click();
		String errMsgLoginID = driver.findElement(By.xpath("//span[contains(@class,'gigya-error-msg-active') and @data-bound-to='loginID']")).getText();
		Assert.assertEquals(errMsgLoginID, "This field is required");
		String errMsgPass = driver.findElement(By.xpath("//span[contains(@class,'gigya-error-msg-active') and @data-bound-to='password']")).getText();
		Assert.assertEquals(errMsgPass, "This field is required");
		closeAllWindowWithoutParentWindows(parentID);
		switchToWindowByTitle("Từ điển tiếng Anh Cambridge và Từ điển từ đồng nghĩa miễn phí");
		WebElement txtSearch = driver.findElement(By.xpath("//input[@id='searchword']"));
		String keyInput = "automation";
		txtSearch.sendKeys(keyInput);
		txtSearch.sendKeys(Keys.ENTER);
		String keyWordInDOM = driver.getPageSource();
		Assert.assertTrue(keyWordInDOM.contains(keyInput));
	}

	// Switch if have only 2 window
	public void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
			}
		}
	}

	// Switch if have > 2 window
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(expectedTitle)) {
				break;
			}
		}
	}

	public boolean closeAllWindowWithoutParentWindows(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void clickToElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecuter = (JavascriptExecutor) driver;
		jsExecuter.executeScript("arguments[0].click();", element);
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
