package xbmc.configProperties;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import xbmc.pageobjects.XBMC_Contextpage;

public class ConfigProperties {
	
	private Properties properties;
	private final String configPropertyFilepath = "./Config/config.properties";
	
	public  ConfigProperties() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(configPropertyFilepath));
			properties = new Properties();
			
			try {
				properties.load(reader);
				reader.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("config.properties file not found in "+configPropertyFilepath);
		throw new RuntimeException("config.properties file not found in "+configPropertyFilepath);
		}

	}
	
	public String getChromeDriverPath() {
		
			String driver_path = properties.getProperty("ChromeDriverPath");
			if(driver_path != null)
			{
				return driver_path;
			}
			else
			{
				throw new RuntimeException("Chrome Driver path is not specified in the config.properties file");
			}
	}
	
	public String getFirefoxDriverPath() {
		
		String driver_path = properties.getProperty("FirefoxDriverPath");
		if(driver_path != null)
		{
			return driver_path;
		}
		else
		{
			throw new RuntimeException("Chrome Driver path is not specified in the config.properties file");
		}
	}
	
	public String getIEDriverPath() {
		
		String driver_path = properties.getProperty("IEDriverPath");
		if(driver_path != null)
		{
			return driver_path;
		}
		else
		{
			throw new RuntimeException("Chrome Driver path is not specified in the config.properties file");
		}
}
	
	public String getXBMCurl() {
		String XBMCurl = properties.getProperty("XBMCurl");
		if(XBMCurl != null)
		{
			return XBMCurl;
		}
		else {
			throw new RuntimeException("XBMC url path is not mentioned in the config.properties file");
		}

	}
	
	public String getLoginUsername() {
		String loginUsername = properties.getProperty("LoginUsername");
		if(loginUsername != null)
		{
			return loginUsername;
 		}
		else {
			throw new RuntimeException("XBMC login username is not mentioned in the config.properties file");
		}

	}
	
	public String getLoginPassword() {
		String loginPassword = properties.getProperty("LoginPassword");
		if(loginPassword != null)
		{
			return loginPassword;
		}
		else {
			throw new RuntimeException("XBMC login Password is not mentioned in the config.properties file");
		}

	}
	
	public String getXBMCcontext_Department() {
		String XBMCcontect_Department = properties.getProperty("XBMC_Context_Department");
		if(XBMCcontect_Department != null)
		{
			return XBMCcontect_Department;
		}
		else {
			throw new RuntimeException("XBMC department is not mentioned in the config.properties file");
		}

	}
	
	public String getXBMCContext_Module() {
		String XBMCcontext_Module = properties.getProperty("XBMC_Context_Module");
		if(XBMCcontext_Module != null)
		{
			return XBMCcontext_Module;
		}
		else {
			throw new RuntimeException("XBMC context page module is not mentioned in config.properties file");
		}

	}
	
	public String getXBMCContext_ReleaseName() {
		String XBMCContext_ReleaseName = properties.getProperty("XBMC_Context_ReleaseList_ReleaseName");
        if(XBMCContext_ReleaseName != null)
        {
        	return XBMCContext_ReleaseName;
        }
        else {
			throw new RuntimeException("XBMC context page release list name is not mentioned in the config.properties file");
		}
	}
	
	public String getXBMCContext_Role() {
		String XBMCContext_Role = properties.getProperty("XBMC_Context_Role");
		if(XBMCContext_Role != null)
		{
			return XBMCContext_Role;
		}
		else {
			throw new RuntimeException("XBMC Context page role is not mentioned in config.properties file");
		}

	}
	
	public String getXBMCContext_PMSName() {
		String XBMCContext_PMSName = properties.getProperty("XBMC_Context_PMSList_PMSName");
		if (XBMCContext_PMSName != null) 
		{
		return XBMCContext_PMSName;	
		}
		else {
			throw new RuntimeException("XBMC context page PMS name is not mentioned in config.properties file");
		}

	}
	
	
	public String getXBMCAsset_AssetName() {
		String XBMCAsset_AssetName = properties.getProperty("XBMC_Asset_AssetName");
		if (XBMCAsset_AssetName != null) 
		{
			return XBMCAsset_AssetName;
		}
		else {
			throw new RuntimeException("XBMC Asset page asset name is not mentioned in the config.properties file");
		}

	}
	
	public String getScreenshotFolder() {
		String ScreenshotFoledrpath =properties.getProperty("Screenshot_FolderPath");
		if (ScreenshotFoledrpath != null)
		{
			return ScreenshotFoledrpath;
		}
		else {
			throw new RuntimeException("Screenshot folder path is not mentioned in the copnfig file");
		}

	}
	
	public String getCodegenAPI() {
		String codegenAPI = properties.getProperty("CodegenAPIurl");
		if(codegenAPI != null)
		{
			return codegenAPI;
		}
		else {
			throw new RuntimeException("codegen api url is not mentioned in the config file");
		}

	}
	
	public String getplinkCommand() {
		String plinkCommand = properties.getProperty("PlinkCommand");
		if(plinkCommand != null)
		{
			return plinkCommand;
		}
		else {
			throw new RuntimeException("plink command is not mentioned in the cnfig file");
		}

	}
	
	public String getAdminconsoleURL() {
		String adminConsoleURL = properties.getProperty("AdminConsoleURL");
		if(adminConsoleURL != null)
		{
			return adminConsoleURL;
		}
		else {
			throw new RuntimeException("admin console URL is not mentioned in the config properties file");
		}

	}
	
	public String getExtentReportsFilepath() {
		String extreportFilepath= properties.getProperty("Extentreports_filepath");
		if(extreportFilepath != null)
		{
			return extreportFilepath;
		}
		else {
			throw new RuntimeException("Extent report file path not mentioned in the config file");
		}
	}
		
	public String getInputAPIheader1()
	{
		String APIheader1 = properties.getProperty("Content-Type");
		if(APIheader1 != null)
		{
			return APIheader1;
		}
		else {
			throw new RuntimeException("API header1 is not mentioned in the config file");
		}
	}

	public String getInputAPIheader2() 
	{
		String APIheader2 = properties.getProperty("channelId");
		if(APIheader2 != null)
		{
			return APIheader2;
		}
		else {
			throw new RuntimeException("API header2 is not mentioned in the config file");
		}

	}	
	
	public String getInputAPIheader3() 
	{
		String APIheader3 = properties.getProperty("channelName");
		if(APIheader3 != null)
		{
			return APIheader3;
		}
		else {
			throw new RuntimeException("API header3 is not mentioned in the config file");
		}

	}
	
	public String getInputAPIheader4() 
	{
		String APIheader4 = properties.getProperty("beName");
		if(APIheader4 != null)
		{
			return APIheader4;
		}
		else {
			throw new RuntimeException("API header4 is not mentioned in the config file");
		}

	}
	
	public int getNoofinput() 
	{
		int APInoofinput = Integer.parseInt(properties.getProperty("noofinput"));
		if(APInoofinput != 0)
		{
			return APInoofinput;
		}
		else {
			throw new RuntimeException("API header4 is not mentioned in the config file");
		}

	}
	
	public String getAPIinputfilepath() 
	{
		String APIinputfilepath = properties.getProperty("postinputfilepath");
		if(APIinputfilepath != null)
		{
			return APIinputfilepath;
		}
		else {
			throw new RuntimeException("API file path is not mentioned in the config file");
		}

	}
	
	public String getnoofresumes() {
		String noofResume = properties.getProperty("noofresuem");
		if(noofResume != null)
		{
			return noofResume;
		}
		else {
			throw new RuntimeException("No of Resume is not mentioned in the config file");
		}

	}
	
	public String getInputBEAPI() {
		
		String inputBEAPI = properties.getProperty("InputBEAPI");
		if(inputBEAPI != null)
		{
			return inputBEAPI;
		}
		else {
			throw new RuntimeException("Input BE API is not mentioned in the config file");
		}
	}

public String getInputexcel_filepath() {
		
		String inputExcelFilepath = properties.getProperty("ExcelInputFilepath");
		if(inputExcelFilepath != null)
		{
			return inputExcelFilepath;
		}
		else {
			throw new RuntimeException("Input BE API is not mentioned in the config file");
		}
	}
	
}
