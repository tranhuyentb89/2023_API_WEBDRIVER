package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_11_JSExecute {
	WebDriver driver;
	JavascriptExecutor jsExecuter;


	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Testcase01() {
		String homepageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homepageUrl, "http://live.techpanda.org/");
	}

	@Test
	public void Testcase_02_CheckTitle() {
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Home page");
	}

	public void highlightElement(WebElement element) {
		jsExecuter = (JavascriptExecutor)driver;
		jsExecuter.executeScript("arguments[0].style.border='6px groove red'", element);
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecuter.executeScript(javaScript);
	}
	
	public Object clickToElementByJS(WebElement element) {
		return jsExecuter.executeScript("arguments[0].click();", element);

	}
	
	public Object sendkeyToElementByJS(WebElement element, String value) {
		return jsExecuter.executeScript("arguments[0].setAttribute('value'," + value + "')", element);
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
