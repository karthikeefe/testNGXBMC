package xbmc.pageobjects;

import static org.testng.Assert.expectThrows;

import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;



public class XBMC_Contextpage {

	WebDriverWait wait;

	public XBMC_Contextpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);

	}

	private String waitforrole = "(//div[@class='c-btn'])[3]";

	@FindBy(xpath = "(//div[@class='c-btn'])[1]")
	private WebElement department_initialtext;

	public WebElement getDepartment_initialtext() {
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@class='col-lg-4 col-md-12 col-form-label']")));
		return department_initialtext;
	}

	@FindBy(xpath = "(//ul[@style='max-height: 180px;'])[position()=1]/child::li")
	private java.util.List<WebElement> department_selection;

	public Actions department_select(WebDriver driver, String dropdowntext) {
		// Select department_select = new Select(department_selection);
		// department_select.selectByVisibleText(dropdowntext);
		Actions department_mouseover = new Actions(driver);

		for (int i = 0; i < department_selection.size(); i++) {
			if (department_selection.get(i).getText().equals(dropdowntext)) {
				department_mouseover.moveToElement(department_selection.get(i));
				break;
			}
		}
		return department_mouseover;
	}

	@FindBy(xpath = "(//div[@class='c-btn'])[2]")
	private WebElement module_initialtext;

	public WebElement getModule_initialtext() {
		return module_initialtext;
	}

	@FindBy(xpath = "(//ul[@style='max-height: 180px;'])[position()=2]/child::li")
	private java.util.List<WebElement> module_selection;

	public Actions module_select(WebDriver driver, String moduletext) {
		Actions module_mouseover = new Actions(driver);
		try {
			for (int i = 0; i < module_selection.size(); i++) {
				if (module_selection.get(i).getText().equals(moduletext)) {
					module_mouseover.moveToElement(module_selection.get(i));
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Given module option is not available in the drop down" + " " + e);
		}
		return module_mouseover;
	}

	@FindBy(xpath = "(//button[@class='btn btn-secondary'])[1]")
	private WebElement fetch_release_button;

	public WebElement getFetch_release_button() {
		return fetch_release_button;
	}

	@FindBy(xpath = "(//div[@class='c-btn'])[3]")
	private WebElement role_select;

	public WebElement getrole_select() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitforrole)));
		return role_select;
	}

	@FindBy(xpath = "(//ul[@style='max-height: 180px;'])[position()=3]/child::li")
	private java.util.List<WebElement> role_selection;

	public Actions rolect_selection(WebDriver driver, String roletext) {
		Actions role_mouseover = new Actions(driver);

		for (int i = 0; i < role_selection.size(); i++) {
			if (role_selection.get(i).getText().equals(roletext)) {
				role_mouseover.moveToElement(role_selection.get(i));
				break;
			}
		}
		return role_mouseover;

	}

	@FindBy(xpath = "(//div[@class='input-group'])[1]/child::input")
	private WebElement release_Filter;

	@FindBy(xpath = "(//div[@class='table-cstm-scroll remove-rightpin'])[1]/table/tbody/tr[1]/td[1]/div/span")
	private WebElement release_Table;

	private String waitforReleaselist = "(//div[@class='card-header active'])[2]";

	public void release_List_Select(WebDriver driver, String releaseString) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(waitforReleaselist)));
		// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//div[@class='col-12
		// table-cstm-scroll-wrap'])[1]/div/table")));
		// JavascriptExecutor filter_Mousehover = (JavascriptExecutor) driver;
		// filter_Mousehover.executeScript("arguments[0].scrollIntoView(true);",
		// release_Filter);

		release_Filter.sendKeys(releaseString);

		release_Table.click();
	}

	/*
	 * public WebElement release_name_select(String releaseName) { WebElement
	 * release_name = null; for(WebElement rel : release_table) {
	 * if(rel.getText().equals(releaseName)) { release_name =
	 * rel.findElement(By.xpath("//span[text()='PR2.0']")); break; } } return
	 * release_name; }
	 */

	@FindBy(xpath = "(//div[@class = 'input-group'])[2]/child::input")
	private WebElement pms_List_Filter;

	@FindBy(xpath = "(//div[@class = 'table-cstm-scroll remove-rightpin'])[2]/table/tbody/tr[1]/td[1]/div/span")
	private WebElement pms_List_Table;

	// @FindBy(xpath = "(//div[@class='card-header active'])[3]")
	// private WebElement pms_visible;

	public void pms_List_Select(WebDriver driver, String pms_Name) {

		// JavascriptExecutor filter_Mousehover = (JavascriptExecutor) driver;
		// filter_Mousehover.executeScript("arguments[0].scrollIntoView(true);",
		// pms_List_Filter);
		pms_List_Filter.sendKeys(pms_Name);
		pms_List_Table.click();

	}

	/*
	 * public WebElement pms_List_Select(String pms_Name) { WebElement pms_list =
	 * null;
	 * 
	 * for (WebElement pms : pms_List_table) { if(pms.getText().equals(pms_Name)) {
	 * pms_list = pms.findElement(By.xpath("//span[text()='PMS_PR_2.0']")); } }
	 * return pms_list; }
	 */
	@FindBy(xpath = "(//button[@class='btn btn-secondary'])[3]")
	private WebElement set_Context_btn;

	public WebElement set_Context_button(WebDriver driver) {
		 JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
		 mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",set_Context_btn);
		return set_Context_btn;
	}

	@FindBy(xpath = "//div[@class='toast-close-button']")
	private WebElement alert;

	public void close_Alert() {
		alert.click();
	}

}
