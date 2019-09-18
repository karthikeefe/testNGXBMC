package xbmc.pageobjects;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XBMC_Loginpage {

	private WebDriverWait wait;

	public XBMC_Loginpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	@FindBy(xpath = "//input[@id='username']")
	private WebElement login_username;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement login_password;

	@FindBy(xpath = "//input[@id='kc-login']")
	private WebElement login_button;

	private String title = "//h1[@id='kc-page-title']";

	private String icon = "//img[@alt='Smiley face']";

	public void enterLogin_Username(String username) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(title)));
		login_username.sendKeys(username);
	}

	public void enterLogin_Password(String password) {
		login_password.sendKeys(password);
	}

	public WebElement getLogin_button() {
		return login_button;
	}

	public void waitingforNextPage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(icon)));

	}

}
