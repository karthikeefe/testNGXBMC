package xbmc.webdrivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import xbmc.configProperties.ConfigProperties;

public class Webdrivermanager {
	
	private WebDriver driver;
	private static String driverPath;

	ConfigProperties config;
	
	private static Webdrivermanager webdrivermanager;
	
	static {
		webdrivermanager = new Webdrivermanager();
	}
	
	private Webdrivermanager() {
		config = new ConfigProperties();
		driverPath = config.getChromeDriverPath();	
	}
	
	public static Webdrivermanager getInstance()
	{
		return webdrivermanager;
	}
	
	public WebDriver getDriver(String browser) {
		if(driver == null)
		{
			driver = createDriver(browser);
		}
		return driver;
	}
	
	private WebDriver createDriver( String browser) {
		switch (browser.toUpperCase()) 
		{
		case "CHROME" :
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver(); 
			break;
			
		case "FIREFOX" :
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
			break;
			
		case "IE" :
			System.setProperty("webdriver.ie.driver", driverPath);
			driver = new InternetExplorerDriver();
			break;
		default:
			break;
		}
		return driver;

	}

}
