package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_01_checkEnviroment {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
	}

	@Test
	public void Testcase01() {
		String homepageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homepageUrl, "http://live.techpanda.org/");
	}
	
	@Test
	public void Testcase_02_CheckTitle()
	{
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Home page");
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
