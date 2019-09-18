package Suntec.xbmcsanitytest;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.gargoylesoftware.htmlunit.BrowserVersion;

import io.github.bonigarcia.wdm.WebDriverManager;
import xbmc.codegenAPItest.XBMC_AssetAPIinput;
import xbmc.codegenAPItest.XBMC_Codegenapi;
import xbmc.configProperties.ConfigProperties;
import xbmc.configProperties.ExcelinputData;
import xbmc.extentreports.Detailedlog;
import xbmc.extentreports.Extentreports;
import xbmc.nificommand_executor.NIFICommandExector;
import xbmc.pageobjects.XBMC_AdminconsoleBPsessionpage;
import xbmc.pageobjects.XBMC_Adminconsolehomepage;
import xbmc.pageobjects.XBMC_Assetspage;
import xbmc.pageobjects.XBMC_BPDesignerpage;
import xbmc.pageobjects.XBMC_Contextpage;
import xbmc.pageobjects.XBMC_Homepage;
import xbmc.pageobjects.XBMC_Loginpage;
import xbmc.pageobjectslManager.PageobjectsManager;
import xbmc.screenshots.TakeScreenShots;
import xbmc.webdrivermanager.Webdrivermanager;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Maintest {
	public static WebDriver driver;
	public static ConfigProperties configFile;
	public static TakeScreenShots takescreenshoot;
	private WebDriverWait wait;
	private PageobjectsManager pageobjectManager;
	Extentreports reports;
	Detailedlog logger;
	ExcelinputData excel;
	Webdrivermanager webdrivermanager;

	XBMC_Loginpage xbmc_loginpage;
	XBMC_Homepage xbmc_Homepage;
	XBMC_Contextpage xbmc_Contextpage;
	XBMC_Assetspage xbmc_Assetpage;
	XBMC_BPDesignerpage xbmc_BPdesignerpage;
	XBMC_Adminconsolehomepage xbmc_Adminconsolehomepage;
	XBMC_AdminconsoleBPsessionpage xbmc_AdminconsoleBPsessionpage;
	XBMC_AssetAPIinput xbmc_Assetinputapi;
	
	
	@Parameters("browser")
	@BeforeClass(groups = {"a", "ui", "flowtest"})
	public void openBrowser(String browser) {
		configFile = new ConfigProperties();
		//if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", configFile.getChromeDriverPath());
			//ChromeOptions options = new ChromeOptions();  
			//options.addArguments("--headless", "--disable-gpu");
			//options.setHeadless(true);
			//driver = new HtmlUnitDriver(true);
			//WebDriverManager.chromedriver().clearPreferences();
			//WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver();
			
		//} else if (browser.equals("Firefox")) {
		//	System.setProperty("webdriver.gecko.driver", configFile.getFirefoxDriverPath());
		//	driver = new FirefoxDriver();
		//} else if (browser.equals("IE")) {
		//	System.setProperty("webdriver.ie.driver", configFile.getIEDriverPath());
		//	driver = new InternetExplorerDriver();
		//}
			webdrivermanager = Webdrivermanager.getInstance();
			driver = webdrivermanager.getDriver(browser);
			
		takescreenshoot = new TakeScreenShots();
		pageobjectManager = new PageobjectsManager(driver);
		wait = new WebDriverWait(driver, 60);
		reports = new Extentreports();
		excel = new ExcelinputData();
		pageobjectManager = new PageobjectsManager(driver);
		logger = pageobjectManager.getDetailedlog();
		logger.log().error("-- Logger info messages --");
	}

	@Test(priority = 1, groups = {"ui"})
	public void open_XBMCURL() {
		try {
			driver.get(configFile.getXBMCurl());
			driver.manage().window().maximize();
			if (driver.getTitle().equals("Xelerate 3")) {
				reports.getTest().log(Status.PASS, "XBMC Webpage opened sucessfully");
			} else {
				reports.getTest().log(Status.FAIL, "Open XBMC URL");
				Assert.fail();
			}

		} catch (Exception e) {
			Assert.fail("Error to open XBMC URL :" + e);
		}

	}

	@Test(priority = 2, groups = {"ui"})
	public void xbmc_enter_Login_Credentials_and_click_login_button() throws Exception {
		try {
			xbmc_loginpage = pageobjectManager.getLoginPage();
			xbmc_loginpage.enterLogin_Username(configFile.getLoginUsername());
			xbmc_loginpage.enterLogin_Password(configFile.getLoginPassword());
			xbmc_loginpage.getLogin_button().click();
			xbmc_loginpage.waitingforNextPage();
			reports.getTest().log(Status.PASS, "Enter Login credentials and click login button");

		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "Enter login credentials and click login button : /n" + e);
			Assert.fail();
		}
	}

	/*@Test(priority = 3,groups = {"ui"}, dependsOnMethods = "xbmc_enter_Login_Credentials_and_click_login_button")
	public void xbmc_homepage_Infoicon_click() {
		try {
			xbmc_Homepage = pageobjectManager.getHomePage();
			xbmc_Homepage.profile_info_mouseover(driver);
			reports.getTest().log(Status.PASS, "Mousehover to home page info icon and click info icon");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL,"Mousehover to home page info icon and click info icon failed with exception : " + e);
			Assert.fail("Homepage info mousehover test is failed with exception : " + e);
		}

	}*/

	@Test(priority = 3,groups = {"ui"}, dependsOnMethods = {"xbmc_enter_Login_Credentials_and_click_login_button" })
	public void xbmc_ContextPageSetup_Department_selection() {
		try {
			xbmc_Contextpage = pageobjectManager.getContextPage();
			if (!(xbmc_Contextpage.getDepartment_initialtext().getText()
					.equals(configFile.getXBMCcontext_Department()))) {
				xbmc_Contextpage.getDepartment_initialtext().click();
				xbmc_Contextpage.department_select(driver, configFile.getXBMCcontext_Department()).click().perform();
			}
			reports.getTest().log(Status.PASS,"XBMC Context page, Department is selected as " + configFile.getXBMCcontext_Department() + ".");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc context page setup Department_selection");
			Assert.fail("Context page department selection test is failed with exception : " + e);
		}

	}

	@Test(priority = 4, groups = {"ui"}, dependsOnMethods = { "xbmc_ContextPageSetup_Department_selection" })
	public void xbmc_ContextPageSetup_Module_Selection() {
		try {
			if (!(xbmc_Contextpage.getModule_initialtext().getText().equals(configFile.getXBMCContext_Module()))) {
				xbmc_Contextpage.getModule_initialtext().click();
				xbmc_Contextpage.module_select(driver, configFile.getXBMCContext_Module()).click().perform();
			}
			reports.getTest().log(Status.PASS,"XBMC Context page, module is selected as " + configFile.getXBMCContext_Module() + ".");
			// reports.flush();
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc context page Setup_Module_Selection");
			Assert.fail("Module selection test is failed with exception : " + e);
		}

	}

	@Test(priority = 5,groups= {"ui"}, dependsOnMethods = { "xbmc_ContextPageSetup_Module_Selection" })
	public void xbmc_ContextPageSetup_Fetch_Release_button_click() {
		try {
			xbmc_Contextpage.getFetch_release_button().click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			reports.getTest().log(Status.PASS, "Context page setup_Fetch release button click");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL,"Context page setup_Fetch release button click failed with exception : " + e);
			Assert.fail("Context page fetch release button click test is failed with exception  : " + e);
		}

	}

	/*@Test(priority = 7,groups= {"ui"}, dependsOnMethods = { "xbmc_ContextPageSetup_Fetch_Release_button_click" })
	public void xbmc_ContextPageSetup_Role_Selection() {
		try {
			xbmc_Contextpage.getrole_select().click();
			xbmc_Contextpage.rolect_selection(driver, configFile.getXBMCContext_Role()).click().perform();
			reports.getTest().log(Status.PASS,
					"XBMC Context page, Role is selected as : " + configFile.getXBMCContext_Role() + ".");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "XBMC Context page, Role is selection failed with exception : " + e);
			Assert.fail("Context page role selection test is failed with exception : " + e);
		}

	}*/

	@Test(priority = 6,groups= {"ui"}, dependsOnMethods = { "xbmc_ContextPageSetup_Fetch_Release_button_click" })
	public void xbmc_ContextPageSetup_Releaselist_Selection() {
		try {
			xbmc_Contextpage.release_List_Select(driver, configFile.getXBMCContext_ReleaseName());
			reports.getTest().log(Status.PASS, "XBMC Context page, Release list is selected as : "+ configFile.getXBMCContext_ReleaseName() + ".");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL,"XBMC Context page, Release list is selection failed with exception : " + e);
			Assert.fail("Context page relese list selection test is failed with excepyion : " + e);
		}

	}

	@Test(priority = 7,groups= {"ui"}, dependsOnMethods = { "xbmc_ContextPageSetup_Releaselist_Selection" })
	public void xbmc_ContextPageSetup_PMSlist_Selection() throws IOException {
		try {
			xbmc_Contextpage.pms_List_Select(driver, configFile.getXBMCContext_PMSName());
			reports.getTest().log(Status.PASS,"XBMC Context page, PMS list is selected as : " + configFile.getXBMCContext_PMSName() + ".");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "XBMC Context page, PMS list selection failed with exception : " + e);
			Assert.fail("Context page pms list selection test is failed with exception : " + e);
		}

	}

	@Test(priority = 8,groups= {"ui"}, dependsOnMethods = { "xbmc_ContextPageSetup_PMSlist_Selection" })
	public void xbmc_ContextPageSetup_SetContext_button_click() {
		try {
			xbmc_Contextpage.set_Context_button(driver).click();
			reports.getTest().log(Status.PASS, "XBMC Context page, Finally click Set context button ");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL,"XBMC Context page,  click Set context button failed with exception : " + e);
			Assert.fail("Context page set context test is failed with exception : " + e);
		}

	}

	@Test(priority = 9,groups= {"ui"}, dependsOnMethods = { "xbmc_ContextPageSetup_SetContext_button_click" })
	public void xbmc_assetpage_Assetsearch() throws IOException {
		try {
			xbmc_Assetpage = pageobjectManager.getAssetPage();
			xbmc_Assetpage.asset_Filter_Name(configFile.getXBMCAsset_AssetName());
			xbmc_Assetpage.asset_Table(driver);
			Set<String> windows = driver.getWindowHandles();
			
			for (String win : windows) {
				System.out.println(win);
				if (!(win.equals(driver.getWindowHandle()))) {
					driver.switchTo().window(win);
				}
			}
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getTitle());
			if (!(driver.getTitle().equals("Xelerate"))) {
				Assert.assertEquals(false, true);
				
				System.out.println("Error in BP designer page, title");
			}
			reports.getTest().log(Status.PASS,"Moved to asset page and enter asset name in fileter as :" + configFile.getXBMCAsset_AssetName()+ "and open BP designer page by click 'create' in column action.");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_asset page,Asset " + configFile.getXBMCAsset_AssetName()+ "search is failed with exception : " + e);
			Assert.fail("Asset page asset file & asset selection test is failed with exception  : " + e);
		}
	}
	
	@Test(priority = 10,groups= {"ui"}, dependsOnMethods = { "xbmc_assetpage_Assetsearch" })
	public void xbmc_bpdesignerpage_assetname_check() {
		try {
			xbmc_BPdesignerpage = pageobjectManager.getBPDesignerPage();
			if(xbmc_BPdesignerpage.checkAssetname_in_bpdesignerpage(configFile.getXBMCAsset_AssetName()))
			{
				reports.getTest().log(Status.PASS,"xbmc_bpdesignerpage_assetname_check : both serche asset name & bp designer asset names are equal");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_assetname_check : Not both serche asset name & bp designer asset names are equal ");
				Assert.fail("xbmc_bpdesignerpage_assetname_check : Not both serche asset name & bp designer asset names are equal");
			}
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_assetname_check : Not both serche asset name & bp designer asset names are equal ");
			Assert.fail("xbmc_bpdesignerpage_assetname_check : Not both serche asset name & bp designer asset names are equal");	
		}
	}
	
	@Test(priority = 11, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_assetname_check" }) 
	  public void xbmc_bpdesignerpage_designPallet_cleanup() throws IOException, AWTException 
	  {
		  try {
			//  System.out.println("Bp designer "+ driver.getWindowHandle());
			  if (xbmc_BPdesignerpage.palette_Availablity()) {
				  xbmc_BPdesignerpage.Design_palette_cleanp();

			  }
			  else {
				  reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_designPallet_cleanup,  designer pallete is not available");
					Assert.fail("xbmc_bpdesignerpage_designPallet_cleanup, designer pallete is not available ");	
			  }
		  }
		  catch(Exception e) {
			  reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_designPallet_cleanup : "+ e);
				Assert.fail("xbmc_bpdesignerpage_designPallet_cleanup : "+ e);	
		  }
	  }
	
	
	@Test(priority = 12, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_designPallet_cleanup" }) 
	  public void xbmc_bpdesignerpage_open_configurationBP_clickISprofilablecheckbox_setting() throws IOException, AWTException 
	  {
			if(xbmc_BPdesignerpage.configurationBP_BPsetUP_setting())
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_setting ,check box are checked");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_setting  ");
				Assert.fail("xbmc_bpdesignerpage_configurationBP_setting ");
			}
			
	  }
	
	@Test(priority = 13, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_designPallet_cleanup" }) 
	  public void xbmc_bpdesignerpage_configurationBP_delete_oldprocessVariable_setting() 
	  {
		try {
			if(xbmc_BPdesignerpage.configurationBP_delete_processVariable_setting())
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_setting ,old process variables are deleted");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_processVariable_setting  ");
				Assert.fail("xbmc_bpdesignerpage_configurationBP_processVariable_setting ");
			}
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_processVariable_setting  ");
			Assert.fail("xbmc_bpdesignerpage_configurationBP_processVariable_setting ");
		}
	  }
	
	@Test(priority = 14, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_configurationBP_delete_oldprocessVariable_setting" }) 
	  public void xbmc_bpdesignerpage_configurationBP_check_wrong_defaultNumber_processVariable() 
	  {
		try {
			excel.setExcelFile("Wrong_ConfigurationBP");
			if(xbmc_BPdesignerpage.configurationBP_wrong_processVariable_setting_forNumber(excel.getConfigurationBP_wrong_Processvariable()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_wrong_processVariable_setting_for_number ,when entered wrong numbers, returns error message as 'Entered value scale is wrong'");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_wrong_processVariable_setting_for_number  ");
				Assert.fail("xbmc_bpdesignerpage_configurationBP_wrong_processVariable_setting_for_number ");
			}
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_wrong_processVariable_setting_for_number : "+e);
			Assert.fail("xbmc_bpdesignerpage_configurationBP_wrong_processVariable_setting_for_number : "+e);
		}
	  }
	
	@Test(priority = 15, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_configurationBP_delete_oldprocessVariable_setting" }) 
	  public void xbmc_bpdesignerpage_configurationBP_check_empty_defaultNumber_processvariable() 
	  {
		try {
			excel.setExcelFile("Wrong_ConfigurationBP");
			if(xbmc_BPdesignerpage.configurationBP_empty_processVariable_setting_forNumber(excel.getConfigurationBP_processvariable()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_empty_processVariable_setting_for_number ,When the number input is empty, returns error message'Precision/Value cannot be Zero/Empty':");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_empty_processVariable_setting_for_number  ");
				Assert.fail("xbmc_bpdesignerpage_configurationBP_empty_processVariable_setting_for_number ");
			}
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_empty_processVariable_setting_for_number : "+e);
			Assert.fail("xbmc_bpdesignerpage_configurationBP_empty_processVariable_setting_for_number : "+e);
		}
	  }
	
	@Test(priority = 16, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_configurationBP_delete_oldprocessVariable_setting" }) 
	  public void xbmc_bpdesignerpage_configurationBP_check_empty_name_as_processVariable() 
	  {
		if(xbmc_BPdesignerpage.configurationBP_check_empty_processvariable_name(excel.getConfigurationBP_processvariable()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_check_empty_name_processVariable ,when procces variable name is empty, returns error message '*Process Variable Name is mandatory.");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_check_empty_name_processVariable  ");
			Assert.fail("xbmc_bpdesignerpage_configurationBP_check_empty_name_processVariable ");
		}
	  }
	
	
	@Test(priority = 17, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_configurationBP_delete_oldprocessVariable_setting" }) 
	  public void xbmc_bpdesignerpage_configurationBP_check_wrong_name_as_processVariable() 
	  {
		if(xbmc_BPdesignerpage.configurationBP_check_wrong_processvariable_name(excel.getConfigurationBP_processvariable()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_check_empty_name_processVariable ,when enter wrong process variable name, returns error message '*Name you entered contains invalid name or character or space.'");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_check_empty_name_processVariable  ");
			Assert.fail("xbmc_bpdesignerpage_configurationBP_check_empty_name_processVariable ");
		}
	  }
	
	@Test(priority = 18, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_configurationBP_delete_oldprocessVariable_setting" }) 
	  public void xbmc_bpdesignerpage_configurationBP_check_empty_description_processVariable() 
	  {
		if(xbmc_BPdesignerpage.configurationBP_check_empty_processvariable_description(excel.getConfigurationBP_processvariable()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_check_empty_description_processVariable ,'when enter process description as empty, return error message '*Process Variable Description is mandatory.'");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_check_empty_description_processVariable  ");
			Assert.fail("xbmc_bpdesignerpage_configurationBP_check_empty_description_processVariable ");
		}
	  }
	
	@Test(priority = 19, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_configurationBP_delete_oldprocessVariable_setting" }) 
	  public void xbmc_bpdesignerpage_configurationBP_add_processVariable_setting() 
	  {
		try {
			excel.setExcelFile("ConfigurationBP");
		if(xbmc_BPdesignerpage.configurationBP_add_processVariable_setting(excel.getConfigurationBP_processvariable()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_configurationBP_add_processVariable_setting ,Valid configuration process variables are entered and saved");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_add_processVariable_setting : ");
			Assert.fail("xbmc_bpdesignerpage_configurationBP_add_processVariable_setting : ");
		}
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_configurationBP_add_processVariable_setting : "+e);
			Assert.fail("xbmc_bpdesignerpage_configurationBP_add_processVariable_setting : "+e);
		}
	  }
	
	
	@Test(priority = 20, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_designPallet_cleanup" })
	public void xbmc_bpdesignerpage_startoperator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution( "./Autoit_script/startoperator_drag&drop.exe")> -1) 
			  { 
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_startoperator_draganddrop , Start operator is placed in BP designer");
			  }
			else {
				System.out.println("AutoIT start operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_startoperator_draganddrop , xbmc_bpdesignerpage_startoperator_draganddrop");
				Assert.fail("xbmc_bpdesignerpage_startoperator_draganddrop , xbmc_bpdesignerpage_startoperator_draganddrop");	
			}
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_startoperator_draganddrop , xbmc_bpdesignerpage_startoperator_draganddrop : "+e);
			Assert.fail("xbmc_bpdesignerpage_startoperator_draganddrop , xbmc_bpdesignerpage_startoperator_draganddrop : "+e);	
		
		}
	}
	
	@Test(priority = 21, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_startoperator_draganddrop" })
	public void xbmc_bpdesignerpage_startoperator_bussinessSettings() {
		try {
			excel.setExcelFile("Start_operator");
			if(xbmc_BPdesignerpage.Start_op_bussinessSetting_InputtypeBE( excel.getStartoperator_input_be()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_startoperator_bussinessSettings , Start operator input BE is select from drop down");
			}
			else
			{
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_startoperator_bussinessSettings ");
				Assert.fail("xbmc_bpdesignerpage_startoperator_bussinessSettings");	
			}
			
		} catch (Exception e) {
			System.out.println("Error in start operator : "+ e);
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_startoperator_bussinessSettings ");
			Assert.fail("xbmc_bpdesignerpage_startoperator_bussinessSettings");	
		}
	
	}
	
	@Test(priority = 22, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_startoperator_draganddrop" })
	public void xbmc_bpdesignerpage_startoperator_properties_Settings_and_Save() {
		try {
			
		if(xbmc_BPdesignerpage.start_op_properties_settings(excel.getStartoperator_properties()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_startoperator_properties_Settings , Start operator properties are entered");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_startoperator_properties_Settings ");
			Assert.fail("xbmc_bpdesignerpage_startoperator_properties_Settings");	
		}
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_startoperator_properties_Settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_startoperator_properties_Settings : "+e);
		}	
	
	}
	
/*	/////////////sample///////////////
	@Test(priority = 15, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_startoperator_properties_Settings_and_Save" })
	public void endoperator_dragdrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/Endsample.exe") > -1) 
			{
				excel.setExcelFile("End2_operator");
				xbmc_BPdesignerpage.endOperator_Setting(excel.getEnd2operator_bussinesssetting_ipBE(),excel.getEnd2operator_properties_yieldduration(),
						excel.getEnd2operator_properties_concurrenttask());
			}
			else {
				System.out.println("AutoIT End2 operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_End2operator_draganddrop ");
				Assert.fail("xbmc_bpdesignerpage_End2operator_draganddrop");	
			}
			
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_End2operator_draganddrop : "+e);
			Assert.fail("xbmc_bpdesignerpage_End2operator_draganddrop : "+e);
			
		}
	}

	
	
	//////////////////////
	 * */
	 
	
	
	@Test(priority = 23, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_startoperator_draganddrop" })
	public void xbmc_bpdesignerpage_invokeBSoperator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution( "./Autoit_script/invokebsoperator_drag&drop.exe") > -1) 
			  { 
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_invokeBSoperator_draganddrop, InvokeBS operator placed in BP designer");
				}
			else {
				System.out.println("AutoIT start operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_draganddrop , xbmc_bpdesignerpage_invokeBSoperator_draganddrop");
				Assert.fail("xbmc_bpdesignerpage_InvokeBSoperator_draganddrop , xbmc_bpdesignerpage_invokeBSoperator_draganddrop");	
			}
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_draganddrop , xbmc_bpdesignerpage_invokeBSoperator_draganddrop : "+e);
			Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_draganddrop , xbmc_bpdesignerpage_invokeBSoperator_draganddrop : "+e);	
		}
	}
	
	@Test(priority = 24, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_invokeBSoperator_draganddrop" })
	public void xbmc_bpdesignerpage_invokeBSoperator_business_settings() {
		try {
			excel.setExcelFile("InvokeBS_operator");
		if(xbmc_BPdesignerpage.invokebs_op_business_settings(excel.getInvokeBSoperator_BussinessService(),excel.getInvokeBSoperator_BussinessService_api()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_invokeBSoperator_business_settings, InvokeBS operator,business settings BS & API are selected");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_business_settings : ");
			Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_business_settings : ");	
		}	
		}
		catch(Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_business_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_business_settings : "+e);	
		}
	}
	
	@Test(priority = 25, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_invokeBSoperator_business_settings" })
	public void xbmc_bpdesignerpage_invokeBSoperator_IPmapping_settings() {
		try {
			if(xbmc_BPdesignerpage.invokebs_op_inputmapping_settings( excel.getInvokeBSoperator_ipmapping() ))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_invokeBSoperator_IPmapping_settings, InvokeBS operator,I/P mappings are selected");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_IPmapping_settings : ");
				Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_IPmapping_settings : ");
			}
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_IPmapping_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_IPmapping_settings : "+e);
		}
	}
	
	
	@Test(priority = 26, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_invokeBSoperator_business_settings" })
	public void xbmc_bpdesignerpage_invokeBSoperator_OPmapping_settings() {
		try {
			excel.setExcelFile("InvokeBS_operator");
			if(xbmc_BPdesignerpage.invokebs_op_outputmapping_settings(excel.getInvokeBSoperator_opmapping_paramaters()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_invokeBSoperator_OPmapping_settings, InvokeBS operator,O/P mappings are selected");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_OPmapping_settings : ");
				Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_OPmapping_settings : ");
			}
			
		}
		catch (Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_OPmapping_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_OPmapping_settings : "+e);
		}
		}
	
	
	@Test(priority = 27, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_invokeBSoperator_OPmapping_settings" })
	public void xbmc_bpdesignerpage_invokeBSoperator_properties_settings() {
		try {
			if( xbmc_BPdesignerpage.invokebs_Operator_properties_Setting(excel.getInvokeBSoperator_properties_yieldduration(), excel.getInvokeBSoperator_properties_concurrenttask()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_invokeBSoperator_properties_settings, InvokeBS operator,properties are selected and saved");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_properties_settings : ");
				Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_properties_settings : ");
			}
		}
		catch(Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBSoperator_properties_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_properties_settings : "+e);
			}
		}

		
	
	@Test(priority = 28, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_invokeBSoperator_draganddrop" })
	public void xbmc_bpdesignerpage_DMExclusiveoperator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution( "./Autoit_script/DMExclusiveoperator_drag&drop.exe") > -1) 
			{
				
			}	
			else {
				System.out.println("Error in DMExclusive");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DM exclusive_draganddrop");
				Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_draganddrop");	
				}
			
		} catch (Exception e) {
			System.out.println("AutoIT DM Exclusive operator drag&Drop is not executed sucessfully");
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DM exclusive_draganddrop : "+ e);
			Assert.fail("xbmc_bpdesignerpage_invokeBSoperator_draganddrop : "+e);	
		}
	}
	
	@Test(priority = 29, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_DMExclusiveoperator_draganddrop" })
	public void xbmc_bpdesignerpage_DMExclusiveoperator_setting() {
		try {
			excel.setExcelFile("DMExclusive_operator");
			if(xbmc_BPdesignerpage.dmExclusive_Operator_Setting(excel.getDMExclusiveoperator_Bussinessetting_ipBE(), excel.getDMExclusiveoperator_Bussinessetting_conditionName(),
			excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value1(), 	excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value2(), excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value3(),
			excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value4(), excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value5(),
			excel.getDMExclusiveoperator_processVariable_default_pv1(),
			excel.getDMExclusiveoperator_processVariable_decision_pv1(), excel.getDMExclusiveoperator_processVariable_decision_pv2(), excel.getDMExclusiveoperator_processVariable_decision_pv3(), excel.getDMExclusiveoperator_processVariable_decision_pv4(),
			excel.getDMExclusiveoperator_properties_yieldduration(), excel.getDMExclusiveoperator_properties_concurrentTask()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_DMExclusiveoperator_setting");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMExclusiveoperator_setting");
				Assert.fail("xbmc_bpdesignerpage_DMExclusiveoperator_setting");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in DMExclusive op settings");
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMExclusiveoperator_setting");
			Assert.fail("xbmc_bpdesignerpage_DMExclusiveoperator_setting");
			}
		}
	
	
	
	
	@Test(priority = 30, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_DMExclusiveoperator_setting" })
	public void xbmc_bpdesignerpage_joinoperator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution( "./Autoit_script/Join1operator_drag&drop.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_joinoperator_draganddrop");
				}
			else {
				System.out.println("AutoIT join operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_Joinoperator_draganddrop ");
				Assert.fail("xbmc_bpdesignerpage_Joinoperator_draganddrop");	
			}
			
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_Joinoperator_draganddrop : "+e);
			Assert.fail("xbmc_bpdesignerpage_Joinoperator_draganddrop : "+e);
			
		}
	}
	
	@Test(priority = 31, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_DMExclusiveoperator_setting" })
	public void xbmc_bpdesignerpage_joinoperator_settings() {
		try {
			excel.setExcelFile("Join_operator");
		if(	xbmc_BPdesignerpage.joinOperator_settings(excel.getJoinoperator_bussinesssetting_ipBE(),excel.getJoin_properties_yieldduration(),excel.getJoin_properties_concurrentTask()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_joinoperator_draganddrop");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_joinoperator_settings ");
			Assert.fail("xbmc_bpdesignerpage_joinoperator_settings");	
		}
		
		}
		catch(Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_joinoperator_settings ");
			Assert.fail("xbmc_bpdesignerpage_joinoperator_settings");
			}
		}
	
	
	@Test(priority = 32, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_joinoperator_draganddrop" })
	public void xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/DMInclusiveoperator_drag&drop.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop");
			}
			else {
				System.out.println("AutoIT DMInclusive operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop ");
				Assert.fail("xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop");	
			}
			
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop : "+ e);
			Assert.fail("xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop : " +e);
			
		}
	}
	
	@Test(priority = 33, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop" })
	public void xbmc_bpdesignerpage_DMInclusiveoperator_settings() {
		try {
			excel.setExcelFile("DMInclusive_operator");
			if(xbmc_BPdesignerpage.dmIclusive_Operator_Setting(excel.getDMExclusiveoperator_Bussinessetting_ipBE(), excel.getDMExclusiveoperator_Bussinessetting_conditionName(),
					excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value1(), 	excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value2(), excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value3(),
					excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value4(), excel.getDMExclusiveoperator_Bussinessetting_rulebuilder_Value5(),
					excel.getDMExclusiveoperator_processVariable_default_pv1(),
					excel.getDMExclusiveoperator_processVariable_decision_pv1(), excel.getDMExclusiveoperator_processVariable_decision_pv2(), excel.getDMExclusiveoperator_processVariable_decision_pv3(), excel.getDMExclusiveoperator_processVariable_decision_pv4(),
					excel.getDMExclusiveoperator_properties_yieldduration(), excel.getDMExclusiveoperator_properties_concurrentTask()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_DMInclusiveoperator_settings");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMInclusiveoperator_settings ");
				Assert.fail("xbmc_bpdesignerpage_DMInclusiveoperator_settings");
			}
		
		}
		catch(Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMInclusiveoperator_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_DMInclusiveoperator_settings : "+e);
			}
		}
	
	
	@Test(priority = 34, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_DMInclusiveoperator_draganddrop" })
	public void xbmc_bpdesignerpage_join2operator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/Join2operator_drag&drop.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_join2operator_draganddrop");
				}
			else {
				System.out.println("AutoIT join2 operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_Join2operator_draganddrop ");
				Assert.fail("xbmc_bpdesignerpage_Join2operator_draganddrop");	
			}
			
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_Join2operator_draganddrop : "+e);
			Assert.fail("xbmc_bpdesignerpage_Join2operator_draganddrop : "+e);
			
		}
	}
	
	@Test(priority = 35, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_join2operator_draganddrop" })
	public void xbmc_bpdesignerpage_join2operator_settings() {
		try {
			excel.setExcelFile("Join2_operator");
			if(xbmc_BPdesignerpage.joinOperator_settings(excel.getJoinoperator_bussinesssetting_ipBE(),excel.getJoin_properties_yieldduration(),excel.getJoin_properties_concurrentTask()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_join2operator_settings");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_join2operator_settings ");
				Assert.fail("xbmc_bpdesignerpage_join2operator_settings");
			}
		
		}
		catch(Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_join2operator_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_join2operator_settings : "+e);	
			}
		}
	
	
	@Test(priority = 36, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_join2operator_draganddrop" })
	public void xbmc_bpdesignerpage_end1operator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/End1operator_drag&drop.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_end1operator_draganddrop");
			}
			else {
				System.out.println("AutoIT End1 operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_End1operator_draganddrop ");
				Assert.fail("xbmc_bpdesignerpage_End1operator_draganddrop");	
			}
			
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_End1operator_draganddrop : "+e);
			Assert.fail("xbmc_bpdesignerpage_End1operator_draganddrop : "+e);
			
		}
	}
	
	@Test(priority = 37, groups = {"ui"},  dependsOnMethods = { "xbmc_bpdesignerpage_end1operator_draganddrop" })
	public void xbmc_bpdesignerpage_end1operator_settings() {
		try {
			excel.setExcelFile("End1_operator");
			if(xbmc_BPdesignerpage.endOperator_Setting(excel.getEnd1operator_bussinesssetting_ipBE(),excel.getEnd1operator_properties_yieldduration(),
					excel.getEnd1operator_properties_concurrenttask()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_end1operator_settings");
			}
			else
			{
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_end1operator_settings ");
				Assert.fail("xbmc_bpdesignerpage_end1operator_settings");
			}
		}
		catch(Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_end1operator_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_end1operator_settings : "+e);
			}
		}
	
	@Test(priority = 38, groups = {""},  dependsOnMethods = { "xbmc_bpdesignerpage_end1operator_draganddrop" })
	public void xbmc_bpdesignerpage_end2operator_draganddrop() {
		try {
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/End2operator_drag&drop.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_end2operator_draganddrop");
			}
			else {
				System.out.println("AutoIT End2 operator drag&Drop is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_End2operator_draganddrop ");
				Assert.fail("xbmc_bpdesignerpage_End2operator_draganddrop");	
			}
			
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_End2operator_draganddrop : "+e);
			Assert.fail("xbmc_bpdesignerpage_End2operator_draganddrop : "+e);
			
		}
	}
	
	@Test(priority = 39, groups = {""},  dependsOnMethods = { "xbmc_bpdesignerpage_end2operator_draganddrop" })
	public void xbmc_bpdesignerpage_end2operator_settings() {
		try {
			excel.setExcelFile("End2_operator");
		if(	xbmc_BPdesignerpage.endOperator_Setting(excel.getEnd2operator_bussinesssetting_ipBE(),excel.getEnd2operator_properties_yieldduration(),
					excel.getEnd2operator_properties_concurrenttask()))
		{
			reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_end2operator_settings");
		}
		else {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_end2operator_settings : ");
			Assert.fail("xbmc_bpdesignerpage_end2operator_settings : ");
		}
		}
		catch(Exception e)
		{
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_end2operator_settings : "+e);
			Assert.fail("xbmc_bpdesignerpage_end2operator_settings : "+e);
			}
		}
	
	@Test(priority = 40, groups = {"u"},  dependsOnMethods = { "xbmc_bpdesignerpage_end1operator_draganddrop" })
	public void xbmc_bpdesignerpage_start_and_invokeBSnormalConnector() throws IOException, Exception {
		
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/Start&Invokebs_normal_connector.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_start_and_invokeBSnormalConnector");
			}
			else {
				System.out.println("AutoIT normal connector b/w start & invokebs is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_start_and_invokeBSnormalConnector ");
				Assert.fail("xbmc_bpdesignerpage_start_and_invokeBSnormalConnector");	
			}
	}
	
	
	@Test(priority = 41, groups = {"u"},  dependsOnMethods = { "xbmc_bpdesignerpage_start_and_invokeBSnormalConnector" })
	public void xbmc_bpdesignerpage_invokeBS_and_DMExclusive_normalConnector() throws IOException, Exception {
		
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/Invokebs&DMExclusive_normal_connector.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_invokeBS_and_DMExclusive_normalConnector");
			}
			else {
				System.out.println("AutoIT normal connector b/w Invokebs & DMExclusive is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_invokeBS_and_DMExclusive_normalConnector ");
				Assert.fail("xbmc_bpdesignerpage_invokeBS_and_DMExclusive_normalConnector");	
			}
	}
	
	@Test(priority = 42, groups = {"u"},  dependsOnMethods = { "xbmc_bpdesignerpage_invokeBS_and_DMExclusive_normalConnector" })
	public void xbmc_bpdesignerpage_DMExclusive_and_Join_2normalConnector() throws IOException, Exception {
		
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/DMExclusive&Join_2normal_connector.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_DMExclusive_and_Join_2normalConnector");
			}
			else {
				System.out.println("AutoIT normal connector b/w DMExclusive & Join is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMExclusive_and_Join_2normalConnector ");
				Assert.fail("xbmc_bpdesignerpage_DMExclusive_and_Join_2normalConnector");	
			}
	}
	
	@Test(priority = 43, groups = {"u"},  dependsOnMethods = { "xbmc_bpdesignerpage_DMExclusive_and_Join_2normalConnector" })
	public void xbmc_bpdesignerpage_Join_and_DMInclusive_normalConnector() throws IOException, Exception {
		
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/Join&DMInclusive_normal_connector.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_Join_and_DMInclusive_normalConnector");
			}
			else {
				System.out.println("AutoIT normal connector b/w Join & DMInclusive is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_Join_and_DMInclusive_normalConnector ");
				Assert.fail("xbmc_bpdesignerpage_Join_and_DMInclusive_normalConnector");	
			}
	}
	
	@Test(priority = 44, groups = {"u"},  dependsOnMethods = { "xbmc_bpdesignerpage_Join_and_DMInclusive_normalConnector" })
	public void xbmc_bpdesignerpage_DMInclusive_and_Join_2normalConnector() throws IOException, Exception {
		
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/DMInclusive&Join_2normal_connector.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_Join_and_DMInclusive_normalConnector");
			}
			else {
				System.out.println("AutoIT normal connector b/w DMInclusive & Join is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_DMInclusive_and_Join_2normalConnector ");
				Assert.fail("xbmc_bpdesignerpage_DMInclusive_and_Join_2normalConnector");	
			}
	}
	
	@Test(priority = 45, groups = {"u"},  dependsOnMethods = { "xbmc_bpdesignerpage_DMInclusive_and_Join_2normalConnector" })
	public void xbmc_bpdesignerpage_Join_and_End1_smartConnector() throws IOException, Exception {
		
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/Join&End1_smart_connector.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_Join_and_End1_smartConnector");
			}
			else {
				System.out.println("AutoIT normal connector b/w DMInclusive & Join is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_Join_and_End1_smartConnector ");
				Assert.fail("xbmc_bpdesignerpage_Join_and_End1_smartConnector");	
			}
	}
	
	
	@Test(priority = 46, groups = {"u"},  dependsOnMethods = { "xbmc_bpdesignerpage_Join_and_End1_smartConnector" })
	public void xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings() throws IOException, Exception {
		try {
			excel.setExcelFile("Smart_connector");
			if(xbmc_BPdesignerpage.smartConnector_setting(excel.getSmartconnector_mappings_updatesource(),
					excel.getSmartconnector_mappings_enterPV(),
					excel.getSmartconnector_properties_yieldduration(),excel.getSmartconnector_properties_concurrenttask()))
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings");
			}
			else {
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings ");
				Assert.fail("xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings");	
			}
			
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings " +e);
			Assert.fail("xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings : "+e);
		}
	}
	
	
	@Test(priority = 47, groups = {""},  dependsOnMethods = { "xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings" })
	public void xbmc_bpdesignerpage_Join_and_End2_normalConnector() throws IOException, Exception {
		
			if(xbmc_BPdesignerpage.autoitEXE_Execution("./Autoit_script/Join&End2_normal_connector.exe") > -1) 
			{
				reports.getTest().log(Status.PASS, "xbmc_bpdesignerpage_Join_and_End2_normalConnector");
			}
			else {
				System.out.println("AutoIT normal connector b/w DMInclusive & Join is not executed sucessfully");
				reports.getTest().log(Status.FAIL, "xbmc_bpdesignerpage_Join_and_End2_normalConnector ");
				Assert.fail("xbmc_bpdesignerpage_Join_and_End2_normalConnector :");	
			}
	}
	
	  
	  
	  @Test(priority = 48, groups = {"u"}, dependsOnMethods = {"xbmc_bpdesignerpage_JoinEnd1_smartConnector_settings"}) 
	  public void xbmc_BPdesignerpage_BPflowsave() throws Exception 
	  { 
		  try 
		  { 
	           String bpSavemsg = xbmc_BPdesignerpage.BPflow_Save(); 
	          Assert.assertEquals(bpSavemsg,"Saved status - SUCCESS");
	          reports.getTest().log(Status.PASS, "xbmc_BPdesignerpage_BPflowsave");
	          
		  }
		  catch (Exception e) 
		  {
			  reports.getTest().log(Status.FAIL, "BP flow save test is failed with exception : "+e);
	          Assert.fail("BP flow save test is failed with exception : "+ e);
	      }
	  
	  }
	 
	  
	  @Test(priority = 49, groups = {"u"}, dependsOnMethods = {"xbmc_BPdesignerpage_BPflowsave"}) 
	  public void xbmc_BPdesignerpage_BPflow_validation_after_save() 
	  { 

	  if(xbmc_BPdesignerpage.bpdesignValidation().equals("Error Not Found"))
	  {
		  reports.getTest().log(Status.PASS, "xbmc_BPdesignerpage_BPflow_validation_after_save");
	  }
	  else {
		  reports.getTest().log(Status.FAIL, "xbmc_BPdesignerpage_BPflow_validation_after_save ");
          Assert.fail("xbmc_BPdesignerpage_BPflow_validation_after_save ");
	}
	  }
	  
	  
	/*@Test(priority = 14, groups = {"nifi"})
	public void xbmc_codegenapi_getCodegenresponse_for_give_input_parameter() {
		try {
			XBMC_Codegenapi xbmcCodegen = new XBMC_Codegenapi();
			Assert.assertEquals(xbmcCodegen.xbmc_getCodegenAPI(), 200);
		} catch (Exception e) {
			Assert.fail("Trigger codegen api test is failed with exception : " + e);
		}

	}

	@Test(priority = 15, groups = { "nifi" })
	public void xbmc_uploadtempleteXML_updateNIFIProperties() throws IOException {
		try {
			NIFICommandExector nifiscriptexc = new NIFICommandExector();
			int exit_value = nifiscriptexc.NIFI_Scriptexecution();
			Assert.assertEquals(exit_value, 0);
		} catch (Exception e) {
			Assert.fail("nifi upload & update test is failed " + e);
		}

	}
	/*
	 * @Test(priority = 16) public void Wait_for_nifi_load() throws
	 * InterruptedException {
	 * System.out.println("System is waiting for nifi restart");
	 * Thread.sleep(40000);
	 * 
	 * }
	 */

	@Test(priority = 17, groups = { "a", "flowtest" })
	public void open_adminconsole_URL() {
		try {
			xbmc_Adminconsolehomepage = pageobjectManager.getAdminConsolehomepage();
			xbmc_Adminconsolehomepage.open_AdminConsoleURL();
			reports.getTest().log(Status.PASS,"Admin console URL was oppened in browser & verify SUNTEC logo is available in homescreen");
		} catch (Exception e) 
		{
			reports.getTest().log(Status.FAIL, "open admin console test failed with exception" + e);
			Assert.fail("open admin console url test is failed with exception : " + e);
		}
	}

	@Test(priority = 18, groups = { "a" }, dependsOnMethods = {"open_adminconsole_URL" })
	public void to_check_homeicon_sunteclogo_paginationicon_inadminconsole_UI() 
	{
		try {
			xbmc_Adminconsolehomepage.to_check_icons();
			reports.getTest().log(Status.PASS, "to check homeicon suntec logo pagination icon in adminconsole UI");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "to check homeicon suntec logo pagination icon in adminconsole UI" + e);
			Assert.fail("to check homeicon suntec logo pagination icon in adminconsole UI is failed with exception : " + e);
		}

	}

	@Test(priority = 19, groups = { "a","flowtest" }, dependsOnMethods = {"open_adminconsole_URL" })
	public void to_check_whether_BPlist_table_is_available_in_UI() 
	{
		if (xbmc_Adminconsolehomepage.to_check_bpasset_table()) {
			reports.getTest().log(Status.PASS, "To check whether BPlist table is available in UI");
		} else {
			reports.getTest().log(Status.FAIL, "To check whether BPlist table is available in UI is failed");
			Assert.fail("BPlist table is not available in Admin console UI");
		}
	}

	@Test(priority = 20, groups = { "a","flowtest" }, dependsOnMethods = {"to_check_whether_BPlist_table_is_available_in_UI" })
	public void to_check_whether_BPTable_have_Assetlist() 
	{
		if (xbmc_Adminconsolehomepage.to_check_BPasset_tablelist()) {
			reports.getTest().log(Status.PASS, "To check whether BP Table have Assetlist");
		} else {
			reports.getTest().log(Status.FAIL, "To check whether BP Table have Assetlist is failed");
			Assert.fail("To check whether BP Table have Assetlist");
		}

	}

	@Test(priority = 21, groups = { "a", "adminconsole" }, dependsOnMethods = {"to_check_whether_BPTable_have_Assetlist" })
	public void check_whether_Norecordsfound_is_displayed_in_the_BPAsset_table_when_user_searched_bpasset_that_is_not_prestent_in_assettable() {
		if (xbmc_Adminconsolehomepage.assetnamefilter().equals("Record not found")) {
			reports.getTest().log(Status.PASS, "check whether Norecordsfound is displayed in the BPAsset table");
		} else {
			reports.getTest().log(Status.FAIL,"check whether Norecordsfound is displayed in the BPAsset table is failed");
			Assert.fail("check whether Norecordsfound is notdisplayed in the BPAsset table");
		}
	}
	
	
	@Test(priority = 22, groups = {"a","adminconsole"}, dependsOnMethods = {"to_check_whether_BPTable_have_Assetlist"})
	public void check_whether_Norecordsfound_is_displayed_in_the_BPAsset_table_when_user_searched_status_is_not_prestent_in_assettable() {
		if(xbmc_Adminconsolehomepage.statusFilter().equals("Record not found"))
		{
			reports.getTest().log(Status.PASS, "check whether Norecordsfound is displayed in the BPAsset table, when user search wrong status in the filter");
		}
		else {
			reports.getTest().log(Status.FAIL,"check whether Norecordsfound is displayed in the BPAsset table is failed, when user search wrong status in the filter");
			Assert.fail("check whether Norecordsfound is not displayed in the BPAsset table,when user search wrong status in the filter");
		}

	}
	

	// test casse name need to be change
	@Test(priority = 23, groups = { "a","flowtest" }, dependsOnMethods = { "to_check_whether_BPTable_have_Assetlist" })
	public void to_verify_BPname_Filter_by_valuable_assetname() 
	{
		String bpflowassetname = configFile.getXBMCAsset_AssetName();
		if (xbmc_Adminconsolehomepage.adminconsole_BPcolumnname_filter(bpflowassetname).equals(bpflowassetname)) {
			reports.getTest().log(Status.PASS, "BP name" + bpflowassetname + " available in the adminconsole bp list");
		} else {
			reports.getTest().log(Status.FAIL,
					"BP name " + bpflowassetname + " is not available in the adminconsole bp list");
			Assert.fail("TO verify BP name in filter test is failed  : ");
		}

	}

	/*
	 * @Test(priority = 19, groups = { "no" }, dependsOnMethods = {
	 * "xbmc_open_adminconsole_URL" }) public void
	 * xbmc_tocheck_Asset_name_is_available_in_adminconsolepage() { String
	 * bpflowassetname = configFile.getXBMCAsset_AssetName(); if
	 * (xbmc_Adminconsolepage.Tocheck_assetname(bpflowassetname).equals(
	 * bpflowassetname)) { test.log(Status.PASS, "TO check asset name" +
	 * bpflowassetname + " availability in adminconsolepage"); reports.flush(); }
	 * else { Assert.fail("Asset name not available in admin console page");
	 * reports.flush(); test.log(Status.FAIL, "TO check asset name" +
	 * bpflowassetname + " is not available in adminconsolepage"); } }
	 */

	@Test(priority = 24, groups = { "a","flowtest" }, dependsOnMethods = {"to_verify_BPname_Filter_by_valuable_assetname" })
	public void check_whether_Status_is_inactive() 
	{
		String test_method="Before start checking the status";
		if (xbmc_Adminconsolehomepage.Status_check(configFile.getXBMCAsset_AssetName(), test_method).equals("InActive")) {
			reports.getTest().log(Status.PASS, "check_whether_Status_is_inactive");
		} else {
			reports.getTest().log(Status.FAIL, "check_whether_Status_is_inactive is failed");
			Assert.fail("check_whether_Status_is_inactive is failed");
		}

	}

	@Test(priority = 25, groups = { "a","flowtest" }, dependsOnMethods = { "check_whether_Status_is_inactive" })
	public void Start_inactive_BPasset_by_click_play_button_in_adminconsole() 
	{
		if (xbmc_Adminconsolehomepage.start_BPasset(driver, configFile.getXBMCAsset_AssetName())) {
			reports.getTest().log(Status.PASS, "Start the flow by click play button in admin console UI");
		} else {
			reports.getTest().log(Status.FAIL, "Start BPflow by click play button test was failed");
			Assert.fail("StartBPflow by click play button in adminconsole is failed with exception : ");
		}
	}

	@Test(priority = 26, groups = { "a","flowtest" }, dependsOnMethods = {"Start_inactive_BPasset_by_click_play_button_in_adminconsole" })
	public void check_whether_the_status_is_changed_from_Inactive_to_Active() 
	{
		String test_method="Status should change from Inactive to Active, after session start by start button. but it is still Inactive status";
		if (xbmc_Adminconsolehomepage.Status_check(configFile.getXBMCAsset_AssetName(), test_method).equals("Active")) {
			reports.getTest().log(Status.PASS, "check_whether_the_status_is_changed_from_Inactive_to_Active");
		} else {
			reports.getTest().log(Status.FAIL, "check whether the status is changed from Inactive to Active is failed");
			Assert.fail("check whether the status is changed from Inactive to Active is failed");
		}
	}

	@Test(priority = 27, groups = {"a", "flowtest" }, dependsOnMethods = {"to_verify_BPname_Filter_by_valuable_assetname" })
	public void check_whether_user_can_start_ActivestateBPflow_by_clicking_Startbutton() 
	{
		if (xbmc_Adminconsolehomepage.start_active_BPasset(configFile.getXBMCAsset_AssetName())) {
			reports.getTest().log(Status.PASS,
					"check_whether_user_can_start_ActivestateBPflow_by_clicking_Startbutton");
		}
		else {
			reports.getTest().log(Status.FAIL,
					"check_whether_user_can_start_ActivestateBPflow_by_clicking_Startbutton");
			Assert.fail("check_whether_user_can_start_ActivestateBPflow_by_clicking_Startbutton");
		}
	}

	@Test(priority = 28, groups = { "a","flowtest" }, dependsOnMethods = {"to_verify_BPname_Filter_by_valuable_assetname" })
	public void move_to_bpsession_page_by_click_BPsessionbutton() {
		if (xbmc_Adminconsolehomepage.Adminconsole_BPsession_buttonclick(driver, configFile.getXBMCAsset_AssetName())) {
			reports.getTest().log(Status.PASS, "Move to bpsession page by click BPsession button");
		} else {
			reports.getTest().log(Status.FAIL, "xbmc_move_to_bpsession_page test Failed with exception : ");
			Assert.fail("Move to bp session test is failed with exception");
		}
	}
	
	@Test(priority = 29, groups = {"a","flowtest"},dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void To_check_GUI_of_SessionDetail_page() {
		xbmc_AdminconsoleBPsessionpage = pageobjectManager.getAdminConsoleBPsesionpage();
		if(xbmc_AdminconsoleBPsessionpage.GUI_icons())
		{
			reports.getTest().log(Status.PASS, "GUI of SessionDetail page have home button, logo & paginator");
		}
		else {
			reports.getTest().log(Status.FAIL, "GUI of SessionDetail page don't have home button, logo & paginator");
			Assert.fail("GUI of SessionDetail page don't have home button, logo & paginator");
		}
	}
	
	@Test(priority =30, groups = {"a"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_paginator_is_available_with_left_and_right_arrow() {
		if(xbmc_AdminconsoleBPsessionpage.Paginator_arrows_availability_check())
		{
			reports.getTest().log(Status.PASS, "check paginator is available with left and right arrow");
		}
		else {
			reports.getTest().log(Status.FAIL, "check paginator is available with left and right arrow");
			Assert.fail("check paginator is available with left and right arrow");
		}
	}
	
	@Test(priority = 31, groups= {"a"}, dependsOnMethods = {"check_paginator_is_available_with_left_and_right_arrow"})
	public void check_paginator_arrows_are_enabled_based_on_pages() {	
		if(xbmc_AdminconsoleBPsessionpage.paginator_arrows())
		{
			reports.getTest().log(Status.PASS, "check paginator arrows forward & backward are enabled based on pages");
		}
		else {
			reports.getTest().log(Status.FAIL, "check paginator arrows forward & backward are enabled based on pages");
			Assert.fail("check paginator arrows are enabled based on pages");
		}
	}
	
	@Test(priority=32, groups = {"a","adminconsole"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_NOrecordfound_displayed_while_search_wrong_SessionID() {
		if(xbmc_AdminconsoleBPsessionpage.sessionID_Filter_Norecords())
		{
			reports.getTest().log(Status.PASS, "Check whether ”No records found ” is displayed in the table when user searched a Session ID which is not displayed in the below table.");
		}
		else {
			reports.getTest().log(Status.FAIL, "Check NOrecordfound displayed while search wrong SessionID");
			Assert.fail("Check NOrecordfound displayed while search wrong SessionID");
		}
	}
	
	@Test(priority = 33, groups = {"a","adminconsole"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_NOrecordfound_displayed_while_search_wrong_Status() {
		if(xbmc_AdminconsoleBPsessionpage.sessionstatus_norecordfound())
		{
			reports.getTest().log(Status.PASS, "Check whether ”No records found ” is displayed in the table when user searched a Session Status which is not displayed in the session table.");
		}
		else
		{
			reports.getTest().log(Status.FAIL, "Check NOrecordfound displayed while search wrong Session Status");
			Assert.fail("Check NOrecordfound displayed while search wrong Session Status");
		}
	}
	
	@Test(priority = 34, groups = {"a","adminconsole"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_NOrecordfound_displayed_while_search_wrong_Startdate() {
		if(xbmc_AdminconsoleBPsessionpage.startdate_filter_norecordfound())
		{
			reports.getTest().log(Status.PASS, "Check whether ”No records found ” is displayed in the table when user searched a Session start date, which is not displayed in the session table.");
		}
		else
		{
			reports.getTest().log(Status.FAIL, "Check NOrecordfound displayed while search wrong Session start date");
			Assert.fail("Check NOrecordfound displayed while search wrong Session start date");
		}
	}
	
	@Test(priority = 35, groups = {"a","adminconsole"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_NOrecordfound_displayed_while_search_wrong_Enddate() {
		if(xbmc_AdminconsoleBPsessionpage.enddate_filter_norecordfound())
		{
			reports.getTest().log(Status.PASS, "Check whether ”No records found ” is displayed in the table when user searched a Session end date, which is not displayed in the session table.");
		}
		else
		{
			reports.getTest().log(Status.FAIL, "Check NOrecordfound displayed while search wrong Session end date");
			Assert.fail("Check NOrecordfound displayed while search wrong Session end date");
		}
	}
	
	@Test(priority = 36, groups = {"a","adminconsole"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_NOrecordfound_displayed_while_search_wrong_successevent() {
		if(xbmc_AdminconsoleBPsessionpage.success_eventcount_filter_norecordfound())
		{
			reports.getTest().log(Status.PASS, "Check whether ”No records found ” is displayed in the table when user searched a Session success event count, which is not displayed in the session table.");
		}
		else
		{
			reports.getTest().log(Status.FAIL, "Check NOrecordfound displayed while search wrong Session success event count");
			Assert.fail("Check NOrecordfound displayed while search wrong Session success event count");
		}

	}
	
	@Test(priority=42, groups= {"adminconsole"})
	public void back_to_adminconsole_home_page() {
		xbmc_AdminconsoleBPsessionpage.back_to_adminconsole_homepage();

	}
	
	
	@Test(priority=43, groups= {"a","flowtest"}, dependsOnMethods= {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_able_to_search_Sessions_using_SessionID() {	
		if(xbmc_AdminconsoleBPsessionpage.check_SessionID_Filter())
		{
			reports.getTest().log(Status.PASS, "Check whether user is able to search the Sessions using the Session ID.");
		}
		else {
			reports.getTest().log(Status.FAIL, "Check whether user is able to search the Sessions using the Session ID.");
			Assert.fail("Check whether user is able to search the Sessions using the Session ID.");
		}
	}
	
	@Test(priority=44, groups= {"a","flowtest"}, dependsOnMethods= {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_able_to_search_Sessions_using_Sessionstatus() {	
		if(xbmc_AdminconsoleBPsessionpage.check_SessionStatus_Filter())
		{
			reports.getTest().log(Status.PASS, "Check whether user is able to search the Sessions using the Session Status.");
		}
		else {
			reports.getTest().log(Status.FAIL, "Check whether user is able to search the Sessions using the Session Status.");
			Assert.fail("Check whether user is able to search the Sessions using the Session Status.");
		}
	}
	
	@Test(priority=45, groups= {"a","flowtest"}, dependsOnMethods= {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_able_to_search_sessions_by_start_date() {
		if(xbmc_AdminconsoleBPsessionpage.check_Startdate_Filter())
		{
			reports.getTest().log(Status.PASS, "Check whether user is able to search the Sessions using the startdate.");
		}
		else {
			reports.getTest().log(Status.FAIL, "Check whether user is able to search the Sessions using the start date.");
			Assert.fail("Check whether user is able to search the Sessions using the Start date.");
		}
	}
	
	@Test(priority=46, groups= {"a","flowtest"}, dependsOnMethods= {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_able_to_search_sessions_by_end_date() {
		if(xbmc_AdminconsoleBPsessionpage.check_Enddate_Filter())
		{
			reports.getTest().log(Status.PASS, "Check whether user is able to search the Sessions using the End date.");
		}
		else {
			reports.getTest().log(Status.FAIL, "Check whether user is able to search the Sessions using the End date.");
			Assert.fail("Check whether user is able to search the Sessions using the End date.");
		}
	}
	
	@Test(priority=47, groups= {"a","flowtest"}, dependsOnMethods= {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_able_to_search_sessions_by_Success_eventcount() {
		if(xbmc_AdminconsoleBPsessionpage.check_Successevent_Filter())
		{
			reports.getTest().log(Status.PASS, "Check whether user is able to search the Sessions using the Success event count.");
		}
		else {
			reports.getTest().log(Status.FAIL, "Check whether user is able to search the Sessions using the Success event count.");
			Assert.fail("Check whether user is able to search the Sessions using the Success event count.");
		}
	}
	

	@Test(priority = 48, groups = { "a","no" }, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton" })
	public void to_check_only_one_flow_is_running_for_asset() 
	{
		if (xbmc_AdminconsoleBPsessionpage.check_noof_running_status_asset()) {
			reports.getTest().log(Status.PASS, "to_check_only_one_flow_is_running_for_asset");
		} else {
			reports.getTest().log(Status.FAIL,
					"To check only one flow is running for asset : More then one asset is in active");
			Assert.fail("to check only one flow is running for asset");
		}

	}

	@Test(priority = 49, groups = { "a","no" }, dependsOnMethods = {"to_check_only_one_flow_is_running_for_asset" })
	public void xbmc_pass_inputdata_through_assetAPI() 
	{
		xbmc_Assetinputapi = pageobjectManager.getInputAPI();
		int statuscode = xbmc_Assetinputapi.postAPI(configFile.getNoofinput(), configFile.getAPIinputfilepath(),
				configFile.getInputAPIheader1(), configFile.getInputAPIheader2(), configFile.getInputAPIheader3(),
				configFile.getInputAPIheader4());
		if (statuscode == 200) {
			Assert.assertEquals(true, true);
		} else {
			Assert.fail("XBMC bp flow API input test is failed");
		}
	}

	@Test(priority = 50, groups = { "a","no" }, dependsOnMethods = {"to_check_only_one_flow_is_running_for_asset" })
	public void xbmc_stop_bpflow() 
	{
		if (xbmc_AdminconsoleBPsessionpage.stoping_BPflow(driver, configFile.getNoofinput())) {
			reports.getTest().log(Status.PASS, "Stoped the bp flow in admin console");
		} else {
			Assert.fail("XBMC stop bp flow test is failed");
			reports.getTest().log(Status.FAIL, "Stop the bp flow test is failed with exception");
		}
	}

	@Test(priority = 51, groups = { "a","no" }, dependsOnMethods = { "xbmc_stop_bpflow" })
	public void xbmc_BPsession_page_validation() throws InterruptedException {
		try {
			xbmc_AdminconsoleBPsessionpage.BPsession_monitoring();
			reports.getTest().log(Status.PASS, "BPsession page data & status validation");
		} catch (Exception e) {
			reports.getTest().log(Status.FAIL, "BP session validation test is failed with exception :" + e);
			Assert.fail("bp session monitoring test is failed with exception :" + e);
		}

	}

	@Test(priority = 52, groups = { "no" }, dependsOnMethods = { "xbmc_BPsession_page_validation" })
	public void To_check_disablestopbutton_and_enabled_resumebutton_and_resume_BPSession_for_reprocess_the_failed_data() {
		if (xbmc_AdminconsoleBPsessionpage.BPsession_resume(configFile.getnoofresumes())) {
			reports.getTest().log(Status.PASS, "resume BPSession for reprocess the failed_data");
		} else {
			reports.getTest().log(Status.FAIL, "resume BPSession for reprocess the failed data is failed");
			Assert.fail("resume BPSession for reprocess the failed data is failed");
		}
	}
	
	@Test(priority = 53, groups = {"a"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_wheather_assetname_sessionid_staus_is_available_correctly_in_BP_session_dashboard() {
		if(xbmc_AdminconsoleBPsessionpage.dashboard_validation(configFile.getXBMCAsset_AssetName()))
		{
			reports.getTest().log(Status.PASS, "check wheather assetname sessionid staus is available correctly in BP session dashboard");
		}
		else {
			reports.getTest().log(Status.FAIL, "check wheather assetname sessionid staus is available correctly in BP session dashboard");
			Assert.fail("check wheather assetname sessionid staus is available correctly in BP session dashboard");
		}
	}
	
	
	
	@Test(priority = 54, groups = {"a"}, dependsOnMethods = {"move_to_bpsession_page_by_click_BPsessionbutton"})
	public void check_wheather_assetname_sessionID_is_same_as_in_BP_session_traceability() {
		if(xbmc_AdminconsoleBPsessionpage.traceability_validation(configFile.getXBMCAsset_AssetName()))
		{
			reports.getTest().log(Status.PASS, "check wheather assetname sessionID is same as in BP session traceability");
		}
		else {
			reports.getTest().log(Status.FAIL, "check wheather assetname sessionID is same as in BP session traceability");
			Assert.fail("check wheather assetname sessionID is same as in BP session traceability");
		}
	}
	
	@AfterClass(groups = { "ui" })
	public void close_Browser() {
		reports.getReports().flush();
		//driver.quit();

	}
}
