package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_II {
	WebDriver driver;String projectPath = System.getProperty("user.dir");
	By fullNameTextboxBy = By.id("txtFirstname");
	By emailTexboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextBoxBy = By.id("txtPassword");
	By passwordConfirmTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//button[@type='submit']");
	By firstNameErrorMessageBy = By.id("txtFirstname-error");
	By emailErrorMessageBy = By.id("txtEmail-error");
	By confirmEmailErrorMessageBy = By.id("txtCEmail-error");
	By passwordErrorMessageBy = By.id("txtPassword-error");
	By confirmPasswordErrorMessageBy = By.id("txtCPassword-error");
	By phoneErrorMessageBy = By.id("txtPhone-error");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@BeforeMethod
	public void beforMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Regester_Empty_Data() {
		driver.findElement(fullNameTextboxBy).sendKeys("");
		driver.findElement(emailTexboxBy).sendKeys("");
		driver.findElement(confirmEmailTextboxBy).sendKeys("");
		driver.findElement(passwordTextBoxBy).sendKeys("");
		driver.findElement(passwordConfirmTextboxBy).sendKeys("");
		driver.findElement(phoneTextboxBy).sendKeys("");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(firstNameErrorMessageBy).getText(), "Vui l??ng nh???p h??? t??n");
		Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText(), "Vui l??ng nh???p email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessageBy).getText(), "Vui l??ng nh???p l???i ?????a ch??? email");
		Assert.assertEquals(driver.findElement(passwordErrorMessageBy).getText(), "Vui l??ng nh???p m???t kh???u");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMessageBy).getText(), "Vui l??ng nh???p l???i m???t kh???u");
		Assert.assertEquals(driver.findElement(phoneErrorMessageBy).getText(), "Vui l??ng nh???p s??? ??i???n tho???i.");
	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("hoang tinh");
		driver.findElement(emailTexboxBy).sendKeys("tinh@123@123");
		driver.findElement(confirmEmailTextboxBy).sendKeys("tinh@123@123");
		driver.findElement(passwordTextBoxBy).sendKeys("123456");
		driver.findElement(passwordConfirmTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0311111111");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText(), "Vui l??ng nh???p email h???p l???");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessageBy).getText(), "Vui l??ng nh???p email h???p l???");

	}
	
	@Test
	public void TC_03_Register_Incorect_Confirm_Email() {
		driver.findElement(fullNameTextboxBy).sendKeys("hoang tinh");
		driver.findElement(emailTexboxBy).sendKeys("tinh@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("t@gmail.com");
		driver.findElement(passwordTextBoxBy).sendKeys("123456");
		driver.findElement(passwordConfirmTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0311111111");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(confirmEmailErrorMessageBy).getText(), "Email nh???p l???i kh??ng ????ng");
	}
	
	@Test
	public void TC_04_Register_Invalid_Password() {
		driver.findElement(fullNameTextboxBy).sendKeys("hoang tinh");
		driver.findElement(emailTexboxBy).sendKeys("tinh@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("tinh@gmail.com");
		driver.findElement(passwordTextBoxBy).sendKeys("1234");
		driver.findElement(passwordConfirmTextboxBy).sendKeys("1234");
		driver.findElement(phoneTextboxBy).sendKeys("0311111111");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(passwordErrorMessageBy).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMessageBy).getText(), "M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
	}
	
		@Test
		public void TC_04_Register_Incorrect_Confirm_Password() {
			driver.findElement(fullNameTextboxBy).sendKeys("hoang tinh");
			driver.findElement(emailTexboxBy).sendKeys("tinh@gmail.com");
			driver.findElement(confirmEmailTextboxBy).sendKeys("tinh@gmail.com");
			driver.findElement(passwordTextBoxBy).sendKeys("123456");
			driver.findElement(passwordConfirmTextboxBy).sendKeys("111111");
			driver.findElement(phoneTextboxBy).sendKeys("0311111111");
			driver.findElement(registerButtonBy).click();
			
			Assert.assertEquals(driver.findElement(confirmPasswordErrorMessageBy).getText(), "M???t kh???u b???n nh???p kh??ng kh???p");
	}
	
		
		
		
	@Test
	public void TC_05_Regester_Invalid_Phone() {
		driver.findElement(fullNameTextboxBy).sendKeys("hoang tinh");
		driver.findElement(emailTexboxBy).sendKeys("tinh@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("tinh@gmail.com");
		driver.findElement(passwordTextBoxBy).sendKeys("123456");
		driver.findElement(passwordConfirmTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0311111");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(phoneErrorMessageBy).getText(), "S??? ??i???n tho???i ph???i t??? 10-11 s???.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("312222222");
		driver.findElement(registerButtonBy).click();
		
		Assert.assertEquals(driver.findElement(phoneErrorMessageBy).getText(), "S??? ??i???n tho???i b???t ?????u b???ng: 09 - 03 - 012 - 016 - 018 - 019");
		
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}