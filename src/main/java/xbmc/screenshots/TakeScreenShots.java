package xbmc.screenshots;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Suntec.xbmcsanitytest.Maintest;
import xbmc.configProperties.ConfigProperties;

public class TakeScreenShots extends Maintest {
	
	private static ConfigProperties configFile;
	public static  String screenshotFolder = null;
	public String destiDirectory = null;
	
	public TakeScreenShots() {
		
			configFile = new ConfigProperties();
			screenshotFolder = configFile.getScreenshotFolder();
			
			DateFormat dateformat = new SimpleDateFormat("yyyyMMdd_hhmmss");
			java.util.Date date = new java.util.Date();
			
			File screenshotDest_Folder = new File(screenshotFolder+"/"+dateformat.format(date));
			if(screenshotDest_Folder.mkdir())
			{
				destiDirectory = screenshotFolder+"/"+dateformat.format(date);
			}	
	}
	
	public void getScreenshot(String screenshot_name) throws IOException {
		try {
			TakesScreenshot screenshot = (TakesScreenshot)driver;
			File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(srcfile, new File(destiDirectory+"/"+screenshot_name));
		} 
		catch (FileNotFoundException e) {
			
		System.out.println(e+"Screen shot desination file is not available in the respected directory");
		
		}
		
	

	}
	

}
