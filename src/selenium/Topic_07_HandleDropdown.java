package selenium;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_HandleDropdown {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor jsExecuter;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Thuc hanh\\012023_SELENIUM_WEBDRIVER\\lib\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");

		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void Testcase01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
		Select jobRoleSelect = new Select(jobRole1);

		Assert.assertFalse(jobRoleSelect.isMultiple());
		jobRoleSelect.selectByVisibleText("Functional UI Testing");
		Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Functional UI Testing");
		Thread.sleep(3000);

		jobRoleSelect.selectByValue("desktop");
		Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Desktop Testing");
		Thread.sleep(3000);

		jobRoleSelect.selectByIndex(3);
		Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Mobile Testing");
		Thread.sleep(3000);

		Assert.assertEquals(jobRoleSelect.getOptions().size(), 10);

	}

	public void Testcase_02_selectCustomDropdown() throws Exception {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "18");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='18']"));

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "2");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='2']"));

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']"));

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "16");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='16']"));

	}

	public void TC_03_CustomeAngularDropdown() throws Exception {
		driver.get("https://material.angular.io/components/select/overview");
		selectItemInCustomDropdown("//mat-label[text()='State']", "//mat-option/span", "Arkansas");
		Assert.assertTrue(
				isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='18']"));

	}

	public void TC_04_XuLyHTMLDropdown01() throws Exception {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//div[@class='header-links']//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@value='M']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Huyen");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Tran");
		WebElement dateOfBirthYear = driver
				.findElement(By.xpath("//div[@class='inputs date-of-birth']//select[@name='DateOfBirthYear']"));
		Select year = new Select(dateOfBirthYear);
		Assert.assertEquals(year.getOptions().size(), 112);

		selectItemInCustomDropdown("//div[@class='inputs date-of-birth']//select[@name='DateOfBirthDay']",
				"//div[@class='inputs date-of-birth']//select[@name='DateOfBirthDay']/option", "12");
		selectItemInCustomDropdown("//div[@class='inputs date-of-birth']//select[@name='DateOfBirthMonth']",
				"//div[@class='inputs date-of-birth']//select[@name='DateOfBirthMonth']/option", "April");
		selectItemInCustomDropdown("//div[@class='inputs date-of-birth']//select[@name='DateOfBirthYear']",
				"//div[@class='inputs date-of-birth']//select[@name='DateOfBirthYear']/option", "1989");

		String email = "tranhuyentb" + randomNumber() + "@gmail.com";
		String pass = "123456";
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-body']//div[@class='result']")).getText(),
				"Your registration completed");

		driver.findElement(By.xpath("//div[@class='header-links']//a[@class='ico-login']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();

		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"), "12");
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value"), "4");
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"), "1989");

	}

	public void TC_04_XuLyHTMLDropdown02() {
		driver.get("https://rode.com/en/support/where-to-buy");
		driver.switchTo().alert();
		driver.findElement(By.xpath("//button[contains(text(),'Required Only')]")).click();
	}

	public void TC_05() throws Exception {
		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		selectItemInCustomDropdown("//select[@id='Person_Role__c']", "//select[@id='Person_Role__c']//option",
				"SDET / Test Automation Engineer");
		selectItemInCustomDropdown("//select[@id='Test_Framework__c']", "//select[@id='Test_Framework__c']//option",
				"Webdriver IO");
		selectItemInCustomDropdown("//select[@id='Self_Report_Country__c']",
				"//select[@id='Self_Report_Country__c']//option", "Aland Islands");

		Assert.assertEquals(driver.findElement(By.xpath("//select[@id='Person_Role__c']")).getAttribute("value"),
				"SDET / Test Automation Engineer");
		Assert.assertEquals(driver.findElement(By.xpath("//select[@id='Test_Framework__c']")).getAttribute("value"),
				"Webdriver IO");
		Assert.assertEquals(
				driver.findElement(By.xpath("//select[@id='Self_Report_Country__c']")).getAttribute("value"),
				"Aland Islands");

	}

	@Test
	public void TC_06_VUEJS_DropDown() throws Exception {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//li//a", "Second Option");
		Assert.assertTrue(isElementDisplayed(("//div[@class='btn-group']/li[@class='dropdown-toggle' and contains(text(),'Second Option')]")));
	}
	
//	public boolean checkItemSelected(String[] itemSelectedText) { 
//		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
//		int numberItemSelected = itemSelected.size();
//		String allItemSelectedText = driver.findElement(By.xpath(""))
//	}
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
				if(childElement.isDisplayed()) {
					childElement.click();
				}
				else
				{
					jsExecuter.executeScript("arguments[0].click();", childElement);

				}
				Thread.sleep(3000);
				break;
			}
		}
	}

	public boolean isElementDisplayed(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isDisplayed()) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
