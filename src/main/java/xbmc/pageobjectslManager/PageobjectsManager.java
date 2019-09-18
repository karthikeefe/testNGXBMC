package xbmc.pageobjectslManager;

import org.openqa.selenium.WebDriver;

import xbmc.codegenAPItest.XBMC_AssetAPIinput;
import xbmc.codegenAPItest.XBMC_Codegenapi;
import xbmc.extentreports.Detailedlog;
import xbmc.pageobjects.XBMC_AdminconsoleBPsessionpage;
import xbmc.pageobjects.XBMC_Adminconsolehomepage;
import xbmc.pageobjects.XBMC_Adminconsolehomepage;
import xbmc.pageobjects.XBMC_Assetspage;
import xbmc.pageobjects.XBMC_BPDesignerpage;
import xbmc.pageobjects.XBMC_Contextpage;
import xbmc.pageobjects.XBMC_Homepage;
import xbmc.pageobjects.XBMC_Loginpage;

public class PageobjectsManager {
	
	private WebDriver driver;
	private XBMC_Homepage HomePage;
	private XBMC_Loginpage LoginPage;
	private XBMC_Contextpage ContextPage;
	private XBMC_Assetspage AssetPage;
	private XBMC_BPDesignerpage BPDesignerPage;
	private XBMC_Adminconsolehomepage AdminConsolehomePage;
	private XBMC_AdminconsoleBPsessionpage AdminConsoleBPsessionPage;
	private XBMC_Codegenapi Codegenapi;
	private XBMC_AssetAPIinput Assetinputapi;
	private Detailedlog detaillog;

	public PageobjectsManager(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	public XBMC_Loginpage getLoginPage() 
	{
		return (LoginPage == null) ? LoginPage = new XBMC_Loginpage(driver) : LoginPage;
	}
	
	public XBMC_Homepage getHomePage() 
	{
		return (HomePage == null) ? HomePage = new XBMC_Homepage(driver) : HomePage;
	}
	
	public XBMC_Contextpage getContextPage() {
		return (ContextPage == null) ? ContextPage = new XBMC_Contextpage(driver) : ContextPage;
	}
	
	public XBMC_Assetspage getAssetPage() {
		return (AssetPage == null) ? AssetPage = new XBMC_Assetspage(driver) : AssetPage;
	}
	
	public XBMC_BPDesignerpage getBPDesignerPage() {
		return (BPDesignerPage == null) ? BPDesignerPage = new XBMC_BPDesignerpage(driver) : BPDesignerPage;
	}
	
	public XBMC_Adminconsolehomepage getAdminConsolehomepage() {
		return (AdminConsolehomePage == null) ? AdminConsolehomePage = new XBMC_Adminconsolehomepage(driver) : AdminConsolehomePage;
	}

	public XBMC_Codegenapi getCodegenAPI() {
		return (Codegenapi == null) ? Codegenapi = new XBMC_Codegenapi() : Codegenapi;
	}
	
	public XBMC_AssetAPIinput getInputAPI() {
		return (Assetinputapi == null) ? Assetinputapi = new XBMC_AssetAPIinput() : Assetinputapi;
	}
	
	public XBMC_AdminconsoleBPsessionpage getAdminConsoleBPsesionpage() {
		return (AdminConsoleBPsessionPage == null) ? AdminConsoleBPsessionPage = new XBMC_AdminconsoleBPsessionpage(driver) : AdminConsoleBPsessionPage;
	}
	
	public Detailedlog getDetailedlog() {
		return Detailedlog.getDetailedLogInstance();/*(detaillog == null) ? detaillog = new getDetailedLogInstance() : detaillog;*/
	}
	
}
