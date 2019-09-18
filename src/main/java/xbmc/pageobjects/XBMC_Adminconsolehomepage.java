package xbmc.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import xbmc.configProperties.ConfigProperties;
import xbmc.extentreports.Detailedlog;
import xbmc.pageobjectslManager.PageobjectsManager;

public class XBMC_Adminconsolehomepage  {

	static WebDriver driver;
	static WebDriverWait wait;
	private static ConfigProperties configFile;
	
	
	 private static final Logger logger = Logger.getLogger(XBMC_Adminconsolehomepage.class);

	public XBMC_Adminconsolehomepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		XBMC_Adminconsolehomepage.driver = driver;
		wait = new WebDriverWait(XBMC_Adminconsolehomepage.driver, 60);
		//configFile = new ConfigProperties();
		
			
	}

	@FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/tbody")
	private List<WebElement> adminconsole_BPtable;

	@FindAll({ @FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/thead/tr[2]/th") })
	private List<WebElement> BPsession_TableHeader;

	@FindBy(xpath = "/html/body/app-root/div/app-dashboard/div/div/p-confirmdialog/div")
	private WebElement Confirm_popup;

	@FindBy(xpath = "/html/body/app-root/div/app-dashboard/div/div/p-confirmdialog/div/div[3]/button[1]/span[2]")
	private WebElement Confirm_and_Start;

	@FindAll({ @FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/tbody/tr") })
	private List<WebElement> adminconsole_BPtable1;

	@FindBy(xpath="//div[@class='navbar-brand']")
	private WebElement waitforlogo;
	
	@FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/thead/tr[2]/th")
	private List<WebElement> BPname_filter;

	@FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/tbody/tr")
	private List<WebElement> BPtablelist;
	
	@FindBy(xpath="//div[@class='container-fluid']/p-toast/div/p-toastitem/div/div']")
	private WebElement stop_alert;
	
	@FindBy(xpath="//div[@class='navbar-brand']/i")
	private WebElement home_icon;
	
	@FindBy(xpath = "//p-paginator[@class='ng-star-inserted']")
	private WebElement paginator_icon;

	public void open_AdminConsoleURL() {
		try {
			logger.info("-- open_adminconsole_URL --");
			configFile = new ConfigProperties();
			driver.get(configFile.getAdminconsoleURL());
			logger.info("Opened admin console URL");
			driver.manage().window().maximize();
			logger.info( "Browser window is maximized");
			wait.until(ExpectedConditions.visibilityOf(waitforlogo));
		} catch (Exception e) {
			logger.info("Error in open the Admin console URL"+e);
			System.out.println("Error in open the Admin console URL"+e);
		}
		
	}
	
	public boolean to_check_icons() {
		logger.info("-- to_check_homeicon_sunteclogo_paginationicon_inadminconsole_UI --");
		if(home_icon.isDisplayed() && waitforlogo.isDisplayed() && paginator_icon.isDisplayed())
		{
			logger.info( "Home icon, logo & paginator are displayed in Admin console.");
			return true;
		}
		else {
			logger.info("Home icon or logo or paginator is not displaying in Admin console.");
			return false;
		}

	}

	public boolean to_check_bpasset_table() {
		logger.info("-- to_check_whether_BPlist_table_is_available_in_UI --");

		try {
			wait.until(ExpectedConditions.visibilityOf(BPname_filter.get(0)));
			logger.info( "BPasset list table is available in admin console.");
			return true;
		} catch (Exception e) {
			logger.error("BPasset list table is not available in admin console :" +e);
			System.out.println("Error in BPasset table load");
			return false;
		}

	}

	// maintest method is commanded so function is not in use //
	/*public String tocheck_Assetname(String bpflowAssetName) {
		String assetName = "no";
		try {
			Map<String, List<WebElement>> BPflowAssetmap = new HashMap<String, List<WebElement>>();
			List<WebElement> trow = new ArrayList<WebElement>();

			for (int i = 0; i < adminconsole_BPtable.size(); i++) {
				trow = adminconsole_BPtable.get(i).findElements(By.tagName("td"));
				BPflowAssetmap.put(trow.get(0).getText(), trow);
			}

			if (!(BPflowAssetmap.isEmpty())) {

				if (BPflowAssetmap.containsKey(bpflowAssetName)) {
					assetName = BPflowAssetmap.get(bpflowAssetName).get(0).getText();
				}

				else {
					assetName = "no";
					System.out
							.println("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
				}
			}

		} catch (Exception e) {
			assetName = "no";
			System.out.println("Error in admin console asset table capture " + e);
		}
		return assetName;
	}*/

	public boolean to_check_BPasset_tablelist() {
		logger.info("-- to_check_whether_BPTable_have_Assetlist --");
		try {

			if (BPtablelist.size() == 1 && BPtablelist.get(0).getText().equals("Record not found")) {
				logger.info( "BP asset list table have no asset. It's Showing 'Record not found'");
				System.out.println("Bp asset table have no asset list");
				return false;
			} else {
				logger.info("To check BP asset list table have asset's");
				return true;
			}
		} catch (Exception e) {
			logger.error( "To check BP asset list table have asset's is failed with exception :"+ e);
			System.out.println("Error in BP asset table list check :" + e);
			return false;
		}

	}

	public String assetnamefilter() {
		logger.info("-- to_check_whether_BPTable_have_Assetlist --");
		String norecords = "no";
		String inputasset = "ZZZZ";
		try {
			BPname_filter.get(0).findElement(By.tagName("input")).sendKeys(inputasset);
			logger.info( "Pass wrong inputs to the BPname filter");
			Thread.sleep(1000);
			//driverr.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

			if (BPtablelist.size() == 1) {
				norecords = BPtablelist.get(0).getText();
				logger.info( "BP name filter returns "+norecords+" in list table.");
			} else {
				norecords = "no";
				logger.info( "BP name filter returns more than one value for the filtered name. Test case marked failed.");
			}
			
			/*for (int i = 0; i < inputasset.length(); i++) {
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
				// driverr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}*/

			return norecords;

		} catch (Exception e) {
			logger.error("Norecordsfound_is_displayed_in_the_BPAsset_table case failed with exception :"+ e);
			System.out.println("No record found while using filter throws exception : " + e);
			return "no";
		}

	}
	
	public String statusFilter() {
		logger.info("-- check_whether_Norecordsfound_is_displayed_in_the_BPAsset_table_when_user_searched_status_is_not_prestent_in_assettable --");
		String norecords = "no";
		String inputstatus = "ZZZZ";
		try {
			BPname_filter.get(0).findElement(By.tagName("input")).click();
			control_delete();
	BPname_filter.get(1).findElement(By.tagName("input")).sendKeys(inputstatus);
			logger.info( "Passed wrong inputs to the BP status filter");
			Thread.sleep(1000);
			//driverr.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

			if (BPtablelist.size() == 1) {
				norecords = BPtablelist.get(0).getText();
				logger.info( "BP status filter returns "+norecords+" in list table.");
			} else {
				norecords = "no";
				logger.info( "BP status filter returns more than one value for the filtered name. Test case marked failed.");
			}
			
			/*for (int i = 0; i < inputstatus.length(); i++) {
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}*/

			return norecords;

		} catch (Exception e) {
			logger.error("Norecordsfound is not displayed in the BPAsset table when user search wrong status in filter, case failed with exception :"+ e);
			System.out.println("Norecordsfound is not displayed in the BPAsset table when user search wrong status in filter, case failed with exception : " + e);
			return "no";
		}
	}
	
	
	

	public String adminconsole_BPcolumnname_filter(String bpflowAssetName) {
		logger.info("-- to_verify_BPname_Filter_by_valuable_assetname --");
		String assetName = "no";
		Map<String, List<WebElement>> BPflowAssetmap = new HashMap<String, List<WebElement>>();
		List<WebElement> trow = new ArrayList<WebElement>();
		try {
			BPname_filter.get(1).findElement(By.tagName("input")).click();
			control_delete();
			BPname_filter.get(0).findElement(By.tagName("input")).sendKeys(bpflowAssetName);
			Thread.sleep(2000);
			logger.info( "Entering valuable asset name to check asset name filter.");
			

			if (adminconsole_BPtable.size() != 0) {
				for (int i = 0; i < adminconsole_BPtable.size(); i++) {
					trow = adminconsole_BPtable.get(i).findElements(By.tagName("td"));
					BPflowAssetmap.put(trow.get(0).getText(), trow);
				}

				if (BPflowAssetmap.containsKey(bpflowAssetName)) {
					assetName = BPflowAssetmap.get(bpflowAssetName).get(0).getText();
					logger.info( "Entered asset is available in the BP list table.");
				}

				else {
					assetName = "no";
					logger.info( "Mentioned bp flow is not available in the admin console. Check the nifi and DB.");
					System.out
							.println("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
				}
			} else {
				assetName = "no";
				logger.info( "Mentioned Bp name is not available in adminconsole. BP table is empty.");
				System.out.println("Mentioned Bp name is not available in adminconsole. BP table is empty");
			}

			/*for (int i = 0; i < bpflowAssetName.length(); i++) {
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}*/
			// BPname_filter.clear();

		} catch (Exception e) {
			assetName = "no";
			logger.error("BPname_Filter_by_valuable_assetname is failed with exception : "+e);
			System.out.println("Error in admin console asset table capture " + e);
		}
		return assetName;
	}

	public String Status_check(String bpflowAssetName, String test_method) {
		logger.info("-- check_whether_Status_is_inactive --"
				+ "check_whether_the_status_is_changed_from_Inactive_to_Active --");
		String assetStatus = "no";
		try {
			
			BPname_filter.get(0).findElement(By.tagName("input")).click();
			control_delete();
			BPname_filter.get(0).findElement(By.tagName("input")).sendKeys(bpflowAssetName);
			Thread.sleep(2000);
			logger.info("Enter valuable asset name to check the status is active or in active.");
			Map<String, List<WebElement>> BPflowAssetmap = new HashMap<String, List<WebElement>>();
			List<WebElement> trow = new ArrayList<WebElement>();

			if (adminconsole_BPtable.size() != 0) {
				logger.info( "BP list table returns more than one row");
				for (int i = 0; i < adminconsole_BPtable.size(); i++) {
					trow = adminconsole_BPtable.get(i).findElements(By.tagName("td"));
					BPflowAssetmap.put(trow.get(0).getText(), trow);
				}

				if (BPflowAssetmap.containsKey(bpflowAssetName)) {
					assetStatus = BPflowAssetmap.get(bpflowAssetName).get(1).getText();
//					if(assetStatus.equals("InActive"))
//					{
//					System.out.println(test_method);
					logger.info( "Entered BPasset is filtered and it's status is "+ assetStatus+". it should active, after the start");
//					}
					}

				else {
					assetStatus = "no";
					logger.info("Mentioned bp flow is not available in the admin console. Check the nifi and DB.");
					System.out
							.println("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
				}
			} else {
				assetStatus = "no";
				logger.info("Mentioned Bp name is not available in adminconsole. BP table is empty.");
				System.out.println("Mentioned Bp name is not available in adminconsole. BP table is empty");
			}
			/*for(int i=0; i < bpflowAssetName.length(); i++)
			{
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}*/
			
		} catch (Exception e) {
			logger.error("Error in capture the asset status " + e);
			System.out.println("Error in capture the asset status :" + e);

		}
		return assetStatus;

	}

	public boolean start_BPasset(WebDriver driver, String bpflowAssetName) {
		logger.info("-- Start_inactive_BPasset_by_click_play_button_in_adminconsole --");
		boolean assetName = false;
		try {
			BPname_filter.get(0).findElement(By.tagName("input")).click();
			control_delete();
			BPname_filter.get(0).findElement(By.tagName("input")).sendKeys(bpflowAssetName);
			Thread.sleep(2000);
			logger.info("Enter valuable BPasset name for start the asset.");
			Map<String, List<WebElement>> BPflowAssetmap = new HashMap<String, List<WebElement>>();
			List<WebElement> trow = new ArrayList<WebElement>();

			for (int i = 0; i < adminconsole_BPtable.size(); i++) {
				trow = adminconsole_BPtable.get(i).findElements(By.tagName("td"));
				BPflowAssetmap.put(trow.get(0).getText(), trow);
			}
			if (!(BPflowAssetmap.isEmpty())) 
			{
				if (BPflowAssetmap.containsKey(bpflowAssetName)) {
					logger.info("Entered asset is available and filtered in the BP table.");
					if (BPflowAssetmap.get(bpflowAssetName).get(2).findElement(By.tagName("img")).getAttribute("src")
							.substring(52, 63).equals("play_en.png") && BPflowAssetmap.get(bpflowAssetName).get(1).getText().equals("InActive")) 
					{
						logger.info("Asset is in inactive status & it's play button is enabled.");
						BPflowAssetmap.get(bpflowAssetName).get(2).findElement(By.tagName("img")).click();
						logger.info( "Start the BP asset by click the play button.");
						if (Confirm_popup.isDisplayed()) 
						{
							Confirm_and_Start.click();
							logger.info("Confirms the start by click start button in confirmation popup.");
							assetName = true;
							driver.navigate().refresh();
						}
					} else {
						assetName = false;
						logger.info("Mentioned bp flow START button is not enabled or status is Active, Some sessions are already running for this flow.");
						System.out.println(
								"Mentioned bp flow START button is not enabled or status is Active, Some sessions are already running for this flow.");
					}
				}

				else {
					assetName = false;
					logger.info("Mentioned bp flow is not available in the admin console. Check the nifi and DB.");
					System.out
							.println("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
				}
			}

			for(int i=0; i < bpflowAssetName.length(); i++)
			{
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}
		} catch (Exception e) {
			assetName = false;
			logger.error("Error in admin console asset start :" + e);
			System.out.println("Error in admin console asset table capture " + e);
		}
		return assetName;
	}

	
	public boolean start_active_BPasset(String bpflowAssetName) {
		logger.info("-- check_whether_user_can_start_ActivestateBPflow_by_clicking_Startbutton --");
		boolean assetName = false;
		try {
			BPname_filter.get(0).findElement(By.tagName("input")).click();
			control_delete();
			BPname_filter.get(0).findElement(By.tagName("input")).sendKeys(bpflowAssetName);
			Thread.sleep(2000);
			logger.info("Enter asset name in BPname fileter to check to start the active asset.");
			Map<String, List<WebElement>> BPflowAssetmap = new HashMap<String, List<WebElement>>();
			List<WebElement> trow = new ArrayList<WebElement>();

			if (adminconsole_BPtable.size() != 0) {
				for (int i = 0; i < adminconsole_BPtable.size(); i++) {
					trow = adminconsole_BPtable.get(i).findElements(By.tagName("td"));
					BPflowAssetmap.put(trow.get(0).getText(), trow);
				}

				if (BPflowAssetmap.containsKey(bpflowAssetName)) {
					
					if (BPflowAssetmap.get(bpflowAssetName).get(2).findElement(By.tagName("img")).getAttribute("src").endsWith("play_dis.png"))
				//substring are may change at any time //.substring(52, 64).equals("play_dis.png")) 
					{
						BPflowAssetmap.get(bpflowAssetName).get(2).findElement(By.tagName("img")).click();
						if ((BPflowAssetmap.get(bpflowAssetName).get(2).findElement(By.tagName("img")).isDisplayed())) 
						{
							logger.info( "Entered asset name is available in the list and it's play button is disabled. When click the play button does't show the popup.");
							assetName = true;
						}
						else {
							assetName = false;
							//driver.navigate().refresh();
							logger.info( "Mentioned BP asset is ready to start dupilicate session, Pop up should not shown but it showing.");
							System.out.println("Mentioned BP asset is ready to start dupilicate session, Pop up should not shown");
						}

					} else {
						assetName = false;
						logger.info("Mentioned bp flow START button is enabled, It should be disabled after BPasset starts.");
						System.out.println("Mentioned bp flow START button is enabled, It should be disabled after BPasset starts.");
					}
				}

				else {
					assetName = false;
					logger.info( "Mentioned bp flow is not available in the admin console. Check the nifi and DB.");
					System.out
							.println("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
				}
			} else {
				assetName = false;
				logger.info("Mentioned Bp name is not available in adminconsole. BP table is empty.");
				System.out.println("Mentioned Bp name is not available in adminconsole. BP table is empty");
			}
			/*for(int i=0; i < bpflowAssetName.length(); i++)
			{
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}*/
			
		} catch (Exception e) {
			logger.error("Error in capture the asset status :" + e);
			System.out.println("Error in capture the asset status :" + e);
		}
		return assetName;
	}
	
	
	public boolean Adminconsole_BPsession_buttonclick(WebDriver driver, String bpflowAssetName)
	{
		logger.info("-- move_to_bpsession_page_by_click_BPsessionbutton --");
		boolean assetName = false;

		try {
			BPname_filter.get(0).findElement(By.tagName("input")).click();
			control_delete();
			BPname_filter.get(0).findElement(By.tagName("input")).sendKeys(bpflowAssetName);
			Thread.sleep(2000);
			logger.info( "Entered asset name to move to BP session page");
			Map<String, List<WebElement>> BPflowAssetmap = new HashMap<String, List<WebElement>>();

			List<WebElement> trow = new ArrayList<WebElement>();

			for (int i = 0; i < adminconsole_BPtable1.size(); i++) {

				trow = adminconsole_BPtable1.get(i).findElements(By.tagName("td"));
				BPflowAssetmap.put(trow.get(0).getText(), trow);

			}
			if (!(BPflowAssetmap.isEmpty())) {
				
				if (BPflowAssetmap.containsKey(bpflowAssetName)) {
					BPflowAssetmap.get(bpflowAssetName).get(4).findElement(By.tagName("img")).click();
					assetName = true;
					logger.info( "Entered asset name is available in the list and it is redirect to BP session page");
				
				}

				else { 
					assetName = false;
					logger.info("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
					System.out
							.println("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
				}
			} else {
				assetName = false;
				logger.info("Mentioned bp flow is not available in the admin console. Check the nifi and DB");
				System.out.println("Mentioned bp flow is not available in the admin console. Check the nifi and DB");

			}
			for(int i=0; i < bpflowAssetName.length(); i++)
			{
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}

		} catch (Exception e) {
			assetName = false;
			logger.error("Error in admin console asset table capture " + e);
			System.out.println("Error in admin console asset table capture " + e);
		}
		return assetName;
	}
	
	private void control_delete() throws AWTException, InterruptedException {
		Robot rr = new Robot();
		rr.keyPress(KeyEvent.VK_CONTROL);
		rr.keyPress(KeyEvent.VK_A);
		
		rr.keyRelease(KeyEvent.VK_CONTROL);
		rr.keyRelease(KeyEvent.VK_A);
		
		rr.keyPress(KeyEvent.VK_BACK_SPACE);
		rr.keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(500);
}
}
	
