package xbmc.pageobjects;

import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XBMC_Homepage {

	WebDriverWait wait;

	public XBMC_Homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='logo-wrap']/i[@class='fa fa-2x fa-info-circle']")
	private WebElement profile_info;

	private String icon = "//div[@class='logo-wrap']/i[@class='fa fa-2x fa-info-circle']";

	public void profile_info_mouseover(WebDriver driver1) {
		wait = new WebDriverWait(driver1, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(icon)));
		// Actions action = new Actions(driver1);
		// action.moveToElement(profile_info).click().build().perform();
		profile_info.click();

	}
}
