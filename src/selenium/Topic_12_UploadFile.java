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

public class Topic_12_UploadFile {
	WebDriver driver;
	// Get relative path
	String rootFolder = System.getProperty("user.dir");
	String fileName01 = "image_01.jpg";
	String fileName02 = "image_02.jpg";
	String fileName03 = "image_03.jpg";

	String fileNamePath01 = rootFolder + "\\uploadFile\\" + fileName01;
	String fileNamePath02 = rootFolder + "\\uploadFile\\" + fileName02;
	String fileNamePath03 = rootFolder + "\\uploadFile\\" + fileName03;
	String[] files = { fileNamePath01, fileNamePath02, fileNamePath03 };

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Testcase01() throws InterruptedException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		for (String file : files) {
			WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			uploadFile.sendKeys(file);
		}

		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Start upload']")).click();
		Thread.sleep(3000);
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
