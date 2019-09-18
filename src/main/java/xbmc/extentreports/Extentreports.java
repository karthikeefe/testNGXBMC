package xbmc.extentreports;

import java.io.File;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import xbmc.configProperties.ConfigProperties;

public class Extentreports {
	private com.aventstack.extentreports.ExtentReports reports;
	private com.aventstack.extentreports.ExtentTest test;
	private ExtentHtmlReporter htmlreporter;
	private static ConfigProperties configFile;
	String browser;
	
	public Extentreports() {
		configFile = new ConfigProperties();
		htmlreporter = new ExtentHtmlReporter(new File(configFile.getExtentReportsFilepath() + "\\test_reports.html"));
		reports = new com.aventstack.extentreports.ExtentReports();
		reports.attachReporter(htmlreporter);
		test = reports.createTest("regression");
		///reports.setSystemInfo("Browser", browser);
		// reports.setSystemInfo("Browser", browser);
		// configuration items to change the look and feel
		// add content, manage tests etc
		htmlreporter.config().setChartVisibilityOnOpen(true);
		htmlreporter.config().setDocumentTitle("Regression");
		htmlreporter.config().setReportName("Regression Test Report");
		htmlreporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		//test.log(Status.PASS, browser + " browser was opened sucessfully");
		//reports.flush();
	}
	
	public com.aventstack.extentreports.ExtentTest getTest() {
		return test;
	}
	
	public com.aventstack.extentreports.ExtentReports getReports() {
		return reports;
	}
}
