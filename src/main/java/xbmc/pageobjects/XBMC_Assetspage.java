package xbmc.pageobjects;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class XBMC_Assetspage {

	TreeMap<String, WebElement> asset_map = new TreeMap<String, WebElement>();
	WebDriverWait wait;

	public XBMC_Assetspage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);

	}

	@FindBy(xpath = "//div[@class = 'input-group']/child::input")
	private WebElement asset_Filter;

	private String waitforFilter = "//div[@class = 'input-group']/child::input";

	private String waitfortable = "//div[@class = 'col-12 table-cstm-scroll-wrap']/div/table";

	public void asset_Filter_Name(String asset_Name) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitforFilter)));
		asset_Filter.sendKeys(asset_Name);
	}

	@FindBy(xpath = "//table[@class='table table-striped table-bordered dataTable container-fluid table-fixed-scroll']/tbody/tr[1]/td[4]/div/span")
	private WebElement asset_table1;

	@FindBy(xpath = "//table[@class='table table-striped table-bordered dataTable container-fluid table-fixed-scroll']/tbody/tr[1]/td[1]")
	private WebElement asset_table;

	@FindBy(xpath = "//div[@class='toast-close-button']")
	private WebElement alert;
	
	@FindBy(xpath = "//button[@class='btn btn-secondary marright20px']")
	private WebElement design_button;

	public WebElement close_Alert() {
		return alert;

	}

	public void asset_Table(WebDriver driver) throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitfortable)));
		asset_table.click();

		wait.until(ExpectedConditions.visibilityOf(design_button));
		design_button.click();
		Thread.sleep(1000);
	}
}
