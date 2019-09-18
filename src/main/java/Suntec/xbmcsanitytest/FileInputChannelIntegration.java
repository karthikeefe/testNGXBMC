package Suntec.xbmcsanitytest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import xbmc.codegenAPItest.XBMC_AssetAPIinput;
import xbmc.configProperties.ConfigProperties;
import xbmc.configProperties.ExcelinputData;
import xbmc.extentreports.Detailedlog;
import xbmc.extentreports.Extentreports;
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

public class FileInputChannelIntegration {
	
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
	

	@Test(priority = 30, groups = {"ui"})
	public void open_XBMCURL() {
		try {
			xbmc_BPdesignerpage = new XBMC_BPDesignerpage(driver);
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

}
