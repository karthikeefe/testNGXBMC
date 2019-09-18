package xbmc.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import xbmc.extentreports.Detailedlog;
import xbmc.pageobjectslManager.PageobjectsManager;

public class XBMC_AdminconsoleBPsessionpage {
	private static WebDriver driver;
	private static String session_id = null;
	static WebDriverWait wait;
	// static ConfigProperties configFile;
	
	private static final Logger logger= Logger.getLogger(XBMC_AdminconsoleBPsessionpage.class);

	public XBMC_AdminconsoleBPsessionpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		XBMC_AdminconsoleBPsessionpage.driver = driver;
		wait = new WebDriverWait(XBMC_Adminconsolehomepage.driver, 60);
	}
	
	@FindAll({ @FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/tbody/tr") })
	private List<WebElement> BPsession_Tablebody;
	
	//@FindAll({@FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/thead/tr[2]/th")})
	//private List<WebElement> BPsession_statusfilter;
	
	private String BPflow_stoppopup = "//div[@class='ui-dialog-titlebar ui-widget-header ui-helper-clearfix ui-corner-top']/span[text()='Stop a Session']";

	@FindBy(xpath = "//div[@class='ui-dialog-footer ui-widget-content ng-tns-c5-3 ng-star-inserted']/button[1]")
	private WebElement BPflow_stop;
	
	@FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/thead/tr[2]/th")
	private List<WebElement> BPsession_headers_filter;
	
	@FindBy(xpath = "//div[@class='ui-table-wrapper ng-star-inserted']/table/thead/tr[2]/th[1]/input")
	private WebElement BPsession_headers_filter1;
	
	private String BPsession_enableresumebutton_classattrivalue = "pointer ng-star-inserted";
	
	private String BPsession_disableresumebutton_classattrivalue = "ng-star-inserted";
	
	@FindBy(xpath="//div[@class='navbar-brand']/i")
	private WebElement home_icon;
	
	@FindBy(xpath="//div[@class='navbar-brand']")
	private WebElement logo;
	
	@FindBy(xpath="//div[@class='navbar-brand']/child::span")
	private WebElement session_detail;
	
	@FindBy(xpath = "//p-paginator[@class='ng-star-inserted']/div")
	private WebElement paginator_icon;
	
	@FindBy(xpath = "//p-paginator[@class='ng-star-inserted']/div/a")
	private List<WebElement> paginator_buttons;
	
	@FindBy(xpath = "//p-paginator[@class='ng-star-inserted']/div/child::span/a")
	private List<WebElement> paginator_pages;
	
	private String paginator_backwardbutton ="ui-paginator-icon pi pi-step-backward";
	
	private String paginator_leftbutton="ui-paginator-icon pi pi-caret-left";
	
	private String paginator_rightbutton="ui-paginator-icon pi pi-caret-right";
	
	private String paginator_forwardbutton="ui-paginator-icon pi pi-step-forward";
	
	@FindBy(xpath = "//div[@class='row'][1]")
	private WebElement dashboard_detailstable;
	
	@FindBy(xpath = "//div[@class='row'][1]/div/h6")
	private List<WebElement> dashboard_details;
	
	@FindBy(xpath = "//div[@class='inline label title']")
	private WebElement traceability_assetname;
	
	@FindBy(xpath = "//div[@class='inline text-left w-50']//child::div//child::div[2]")
	private List<WebElement> traceability_details;
	
	
	
	
	public boolean check_noof_running_status_asset() {
		logger.info("to_check_only_one_flow_is_running_for_asset");
		boolean ret_value=false;
		String status = "InProgress";
		List<WebElement> BPsession = new ArrayList<>();
		try {
			//deleting previous filter input 
			BPsession_headers_filter.get(9).findElement(By.tagName("input")).click();
			control_delete();
			BPsession_headers_filter.get(2).findElement(By.tagName("input")).sendKeys(status);
			Thread.sleep(1000);
			logger.info("Entered Inprogress to check no of running state sessions.");
			if (BPsession_Tablebody.size() == 1) {
				BPsession = BPsession_Tablebody.get(0).findElements(By.tagName("td"));
				Thread.sleep(500);
				if(!(BPsession.get(0).getText().equals("Record not found")))
				{
				if (BPsession.get(5).isEnabled() && BPsession.get(14).findElement(By.tagName("img")).getAttribute("src")
						.substring(52, 71).equals("resume_disabled.png")) 
				{
					ret_value= true;
					logger.info("Confirms only one session is running and it's stop button is enabled and resume button is disabled.");
				} else {
					logger.info("Running assets stop button is disabled or resume buttons is enabled");
					System.out.println("Running assets stop button is disabled or resume buttons is enabled");
					ret_value =false;
				}
				}
				else {
					logger.info("BP session have no inprogress state asset");
					System.out.println("BP session have no inprogress state asset");
					ret_value= false;
				}
			} else {
				logger.info("Bp session have more than 1 active sessions");
				System.out.println("Bp session have more than 1 active sessions");
				ret_value= false;
			}
			for(int i=0; i<status.length(); i++)
			{
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}
			

		} catch (Exception e) {
			logger.error("Error in check no of running asset status :" + e);
			System.out.println("Error in check no of running asset status :" + e);
			ret_value= false;
		}
		return ret_value;
		

	}

	public boolean stoping_BPflow(WebDriver driver, int processed_datacount) {
		logger.info("-- xbmc_stop_bpflow --");
		List<WebElement> tabledata;
		boolean stopasset = false;
		String noofinput = String.valueOf(processed_datacount);

		try {

			tabledata = new ArrayList<>();

			// for(int i=0; i < BPsession_Tablebody.size(); i++)
			// {
			tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));
			System.out.println(Integer.parseInt(tabledata.get(6).getText()));
			// }
			if (tabledata.get(2).getText().equals("In Progress")) {
				session_id = tabledata.get(0).getText();

				do {
					Thread.sleep(5000);
					tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));
					logger.info("before start the flow, waiting for equal count in both processed & success+failure");
					//logger.info(Integer.parseInt(tabledata.get(6).getText()));
				} while (!(tabledata.get(6).getText().equals(noofinput)));

				if (tabledata.get(2).getText().equals("In Progress") && tabledata.get(8).getText().equals(noofinput)) {
					tabledata.get(5).findElement(By.tagName("i")).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BPflow_stoppopup)));
					BPflow_stop.click();
					logger.info("Sessio flow is in inprogress and processed & success+failure, so it's stoped by click stop button.");
					driver.navigate().refresh();
					stopasset = true;
				}

			}
		} catch (Exception e) {
			stopasset = false;
			logger.error("Error in stoping bp flow in admin console page" + e);
			System.out.println("Error in stoping bp flow in admin console page" + e);
		}
		return stopasset;
	}

	public void BPsession_monitoring() throws InterruptedException {
		logger.info("-- xbmc_BPsession_page_validation --");
		int TotalEventsProcessed = 0;
		int SuccessCount = 0;
		int TechnicalFailureCount = 0;
		int BusinessFailureCount =0;
		List<WebElement> tabledata;

		try {
			tabledata = new ArrayList<>();

			// for(int i=0; i < BPsession_Tablebody.size(); i++)
			// {
			tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));

			// }
			if (tabledata.get(2).getText().equals("In Progress")) {
				logger.info("session is inprogress status. so waiting up to it move to completed or shutting down status");
				do {

					Thread.sleep(6000);
					tabledata.clear();
					tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));
					TotalEventsProcessed = Integer.parseInt(tabledata.get(8).getText());
					SuccessCount = Integer.parseInt(tabledata.get(9).getText());
					TechnicalFailureCount = Integer.parseInt(tabledata.get(10).getText());
					BusinessFailureCount = Integer.parseInt(tabledata.get(11).getText());
					System.out.println(TotalEventsProcessed + ":" + SuccessCount + ":" + TechnicalFailureCount+":"+BusinessFailureCount+ tabledata.get(2).getText());
					logger.info(TotalEventsProcessed + ":" + SuccessCount + ":" + TechnicalFailureCount+":"+BusinessFailureCount+ tabledata.get(2).getText());
				} while (tabledata.get(2).getText().equals("In Progress"));

				try {
					tabledata.clear();
					tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));

				} catch (Exception e) {
					System.out.println("Exception :"+e);

				}}
			if (tabledata.get(2).getText().equals("Shutting Down")) {

				while (tabledata.get(2).getText().equals("Shutting Down")) {
					Thread.sleep(500);
					System.out.println("Shutting down : sleep");
					logger.info("Shutting down : sleep");
					tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));
				}

				if (tabledata.get(2).getText().equals("Completed")) {
					// tabledata.clear();
					// tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));
					TotalEventsProcessed = Integer.parseInt(tabledata.get(8).getText());
					SuccessCount = Integer.parseInt(tabledata.get(9).getText());
					TechnicalFailureCount = Integer.parseInt(tabledata.get(10).getText());
					BusinessFailureCount = Integer.parseInt(tabledata.get(11).getText());
					System.out.println("BP Flow is completed : " + TotalEventsProcessed + ":" + SuccessCount + ":" + TechnicalFailureCount+":"+BusinessFailureCount);
				logger.info("Status is changed to completed : " + TotalEventsProcessed + ":" + SuccessCount + ":" + TechnicalFailureCount+":"+BusinessFailureCount);
				}
				// System.out.println("3"+TotalEventsProcessed+":"+SuccessCount+":"+FailureCount);
			} else {
				logger.info("selected BPflow is already completed in the admin console");
				System.out.println("selected BPflow is already completed in the admin console");
			}
		}

		catch (org.openqa.selenium.StaleElementReferenceException ex) {
			System.out.println("Error in session monitoring : "+ex);
			logger.error("Error in session monitoring : "+ex);
		}

	}

	public boolean BPsession_resume(String resume) {
		logger.info("-- To_check_disablestopbutton_and_enabled_resumebutton_and_resume_BPSession_for_reprocess_the_failed_data --");
		List<WebElement> tabledata;
		boolean stopasset = false;
		int FailureCount = 0;
		int count = 1;
		int noofresume = Integer.parseInt(resume);
		while (count < noofresume) {
			int cc = count + 1;
			// String cc = String.valueOf(count+1);
			try {
				BPsession_headers_filter.get(0).clear();
				Thread.sleep(500);
				BPsession_headers_filter.get(0).findElement(By.tagName("input")).sendKeys(session_id);
				Thread.sleep(1000);
				tabledata = new ArrayList<>();
				tabledata = BPsession_Tablebody.get(0).findElements(By.tagName("td"));

				wait.until(ExpectedConditions.textToBePresentInElement(tabledata.get(2), "Completed"));
				FailureCount = Integer.parseInt(tabledata.get(8).getText());
				if (tabledata.get(2).getText().equals("Completed")) {

					if (FailureCount > 0) {
						if (tabledata.get(14).findElement(By.tagName("img")).getAttribute("class")
								.equals(BPsession_enableresumebutton_classattrivalue)) {

							tabledata.get(14).findElement(By.tagName("img")).click();
							logger.info("session status is completed and failure count is more than 0 and check's resume buttons is enabled. All true so resume by click resume button");
							stopasset = true;
						} else {
							System.out.println(
									"Bp session failure data count is more than 0 but resume button is not anabled");
							stopasset = false;
							logger.info("Bp session failure data count is more than 0 but resume button is not anabled");
						}
					}

					if (FailureCount == 0) {
						if (tabledata.get(14).findElement(By.tagName("img")).getAttribute("class")
								.equals(BPsession_disableresumebutton_classattrivalue)) {
							stopasset = true;
							logger.info("No of failure count is 0, so resume button is disabled");
						} else {
							System.out.println("Bp session failure data count is 0 but resume button is anabled");
							System.out.println("Bp session failure data count is 0 but resume button is anabled");
							stopasset = false;
						}

					}
					logger.info("waiting...");
					// wait.until(ExpectedConditions.textToBePresentInElement(element, text)
					wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@class='ui-table-wrapper ng-star-inserted']/table/tbody/tr"), cc));
					logger.info("new session entered : " + cc);

				}
			} catch (Exception e) {
				stopasset = false;
				logger.error("Error in resume BP session failed data : " + e);
				System.out.println("Error in resume BP session failed data : " + e);
			}
			count = count + 1;
		}
		return stopasset;
	}

	public boolean GUI_icons() {
		logger.info("-- To_check_GUI_of_SessionDetail_page --");
		try {
			wait.until(ExpectedConditions.visibilityOf(logo));
			if(logo.isDisplayed() && home_icon.isDisplayed() && session_detail.isDisplayed() && paginator_icon.isDisplayed())
			{
				logger.info("BP session page GUI's logo, session detail text & paginators are checked");
				return true;
			}
			else {
				logger.info("BP session page logo or home button or paginator are not available");
				System.out.println("BP session page logo or home button or paginator are not available");
				return false;
			}
			
		} catch (Exception e) {
			logger.error("Error in GUI logo, home button and paginator check, failed with exception : "+e);
			System.out.println("Error in GUI logo, home button and paginator check, failed with exception : "+e);
			return false;	
		}
	}
	
	public boolean Paginator_arrows_availability_check() {
		logger.info("-- check_paginator_is_available_with_left_and_right_arrow --");
		boolean button=false;
		try {
			if(paginator_buttons.size() > 0)
			{
				logger.info("BP session paginator is available");
			if(paginator_buttons.get(0).findElement(By.tagName("span")).getAttribute("class").equals(paginator_backwardbutton))
			{
				logger.info("BP session paginator, backward button is available");
				button = true;
			}
			else {
				logger.info("Paginator backward button is not available");
				System.out.println("Paginator backward button is not available");
				button = false;
			}
	        if(paginator_buttons.get(1).findElement(By.tagName("span")).getAttribute("class").equals(paginator_leftbutton))
	        {
	        	logger.info("BP session paginator, left button is available");
	        	button = true;
	        }
	        else {
	        	logger.info("Paginator left button is no available");
				System.out.println("Paginator left button is no available");
				button = false;
			}
	        if(paginator_buttons.get(2).findElement(By.tagName("span")).getAttribute("class").equals(paginator_rightbutton))
	        {
	        	logger.info("BP session paginator, right button is available");
	        	button = true;
	        }
	        else {
	        	logger.info("Paginator right button is not available");
				System.out.println("Paginator right button is not available");
				button = false;
			}
	        if(paginator_buttons.get(3).findElement(By.tagName("span")).getAttribute("class").equals(paginator_forwardbutton))
	        {
	        	logger.info("BP session paginator, forward button is available");
	        	button = true;
	        }
	        else {
	        	logger.info("Paginator forward button is not available.");
				System.out.println("Paginator forward button is not available.");
				button = false;
			}
			}
			else
			{
				logger.info("Paginator button list is zero.");
				System.out.println("Paginator button list is zero.");
				button = false;
			}
			
		} catch (Exception e) {
			logger.error("Error in paginator button check, Failed with exception :"+ e);
			System.out.println("Error in paginator button check, Failed with exception :"+ e);
			button = false;
		}
		return button;

	}
	
	public boolean sessionID_Filter_Norecords() {
		logger.info("-- check_NOrecordfound_displayed_while_search_wrong_SessionID --");
		int session_status_header_element = 0;
		int previous_element=0;
		return check_filter_returning_norecordfound(session_status_header_element, previous_element);
	}
	
	public boolean sessionstatus_norecordfound() 
	{
		logger.info("-- check_NOrecordfound_displayed_while_search_wrong_Status --");
		int session_status_header_element = 2;
		int previous_element=0;
		return check_filter_returning_norecordfound(session_status_header_element, previous_element);	
	}
	
	public boolean startdate_filter_norecordfound() {
		logger.info("-- check_NOrecordfound_displayed_while_search_wrong_Startdate --");
		int session_status_header_element = 3;
		int previous_element=2;
		return check_filter_returning_norecordfound(session_status_header_element, previous_element);
	}
	
	public boolean enddate_filter_norecordfound() {
		logger.info("check_NOrecordfound_displayed_while_search_wrong_Enddate");
		int session_status_header_element = 4;
		int previous_element=3;
		return check_filter_returning_norecordfound(session_status_header_element, previous_element);
	}
	
	public boolean success_eventcount_filter_norecordfound() {
		logger.info("check_NOrecordfound_displayed_while_search_wrong_successevent");
		int session_status_header_element = 9;
		int previous_element=4;
		return check_filter_returning_norecordfound(session_status_header_element, previous_element);
	}
	
	
	
	
	
	public boolean check_filter_returning_norecordfound(int filter_element, int previous_element) {

		List<WebElement> SessionIDs = new ArrayList<>();
		String WrongIP = "zzzzz";
		boolean Norecord = false;
		try {
			BPsession_headers_filter.get(previous_element).findElement(By.tagName("input")).click();
			control_delete();
			BPsession_headers_filter.get(filter_element).findElement(By.tagName("input")).sendKeys(WrongIP);
			Thread.sleep(1000);
			logger.info("Entered wrong I/P to check, filter returned 'no record found'");
			if (BPsession_Tablebody.size() == 1) {
				SessionIDs = BPsession_Tablebody.get(0).findElements(By.tagName("td"));
				Thread.sleep(500);
				if(SessionIDs.get(0).getText().equals("Record not found"))
				{
					logger.info("Bp session table returns 'No record found' while search wrong I/P.");
					Norecord= true;
				}
				else {
					logger.info("Bp session table, not returns 'No record found' while search wrong I/P name.");
					Norecord= false;
				}
				}
			else {
				logger.info("Session stauts filter is returning more than one row in the table, While filter the wrong I/P");
				System.out.println("Session stauts filter is returning more than one rowin the table, While filter the wrong I/P");
				Norecord= false;
			}
			
			/*for(int i=0; i<WrongIP.length(); i++)
			{
				Robot rr = new Robot();
				rr.keyPress(KeyEvent.VK_BACK_SPACE);
				rr.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);
			}*/
			
		} catch (Exception e) {
			logger.error("Error in Session Status filter norecord validation: "+ e);
			System.out.println("Error in Session Status filter norecord validation: "+ e);
			Norecord= false;		
		}
		return Norecord;	
	}
	
	public boolean check_SessionID_Filter() {
		logger.info("-- check_able_to_search_Sessions_using_SessionID --");
		int sessionID_filter_element=0;
		int previous_filter_element=9;
		String FilterName = "session ID";
		return this.check_sessiondetail_Filter(sessionID_filter_element, FilterName, previous_filter_element);
	}
	
	public boolean check_SessionStatus_Filter() {
		logger.info("-- check_able_to_search_Sessions_using_Sessionstatus --");
		int sessionstatus_filter_element=2;
		int previous_filter_element=0;
		String FilterName = "Session Status";
		return this.check_sessiondetail_Filter(sessionstatus_filter_element, FilterName,  previous_filter_element);
	}
	
	public boolean check_Startdate_Filter() {
		logger.info("-- check_able_to_search_sessions_by_start_date --");
		int startdate_filter_element=3;
		int previous_filter_element=2;
		String FilterName = "Start Date";
		return this.check_sessiondetail_Filter(startdate_filter_element, FilterName,  previous_filter_element);
	}
	
	public boolean check_Enddate_Filter() {
		logger.info("check_able_to_search_sessions_by_end_date");
		int enddate_filter_element = 4;
		int previous_filter_element=3;
		String FilterName = "End Date";
		return this.check_sessiondetail_Filter(enddate_filter_element, FilterName,  previous_filter_element);
	}
	
	public boolean check_Successevent_Filter() {
		logger.info("check_able_to_search_sessions_by_Success_eventcount");
		int successevent_filter_element = 9;
		int previous_filter_element=4;
		String FilterName = "Success Count";
		return this.check_sessiondetail_Filter(successevent_filter_element, FilterName,  previous_filter_element);
	}
	
	public boolean check_sessiondetail_Filter(int elementindex_inlist, String FilterName, int previous_filter) {
		List<String> SessionIDs = new ArrayList<>();
		List<String> Filteredsessionid = new ArrayList<>();
		String filtersession = null;
		int noofsessions=0;
		boolean validation= false;
		try {
			BPsession_headers_filter.get(previous_filter).findElement(By.tagName("input")).click();
			control_delete();
				//Only check if BPsession first row have more than one td value//
				if(BPsession_Tablebody.get(0).findElements(By.tagName("td")).size() > 1)
				{
					logger.info("Confirms BP session detail table have some records to validate the filters with appropriate value");
					//Itterate all the pages for capture the session ids//
					for(int pages=0; pages<paginator_pages.size(); pages++)
					{
						//capture the table row td1(session ids) values//
						for(int i=0; i<BPsession_Tablebody.size(); i++)
						{
							System.out.println(BPsession_Tablebody.get(i).findElements(By.tagName("td")).get(elementindex_inlist).getText());
							SessionIDs.add(BPsession_Tablebody.get(i).findElements(By.tagName("td")).get(elementindex_inlist).getText());
						}
						//Move to next page by paginator,if it have more than one pages//
						if(paginator_pages.size() > 1 && paginator_pages.size() > pages+1)
						{
							paginator_pages.get(pages+1).click();
							Thread.sleep(500);
						}
						else {
							paginator_pages.get(0).click();
							Thread.sleep(500);
						}
						
					}
					Thread.sleep(3000);
					filtersession = SessionIDs.get(0);
					System.out.println(SessionIDs.get(0));
					BPsession_headers_filter.get(elementindex_inlist).findElement(By.tagName("input")).sendKeys(filtersession);
				Thread.sleep(1000);
				//Check the repeated values in session id column//
				if(BPsession_Tablebody.get(0).findElements(By.tagName("td")).size() > 1)
				{
				for(int i=0; i<SessionIDs.size(); i++)
				{
					if(SessionIDs.get(i).equals(filtersession))
					{
						noofsessions = noofsessions+1;
					}
				}
				System.out.println(noofsessions);
				
				for(int pag=0; pag<paginator_pages.size(); pag++)
				{
				//After filtering capture the filtered value//
				for(int i=0; i<BPsession_Tablebody.size(); i++)
				{
					System.out.println(BPsession_Tablebody.get(i).findElements(By.tagName("td")).get(elementindex_inlist).getText());
					Filteredsessionid.add(BPsession_Tablebody.get(i).findElements(By.tagName("td")).get(elementindex_inlist).getText());
				}
				if(paginator_pages.size() > 1 && paginator_pages.size() > pag+1)
				{
					paginator_pages.get(pag+1).click();
					Thread.sleep(500);
				}
				
				}
				
				
				System.out.println(Filteredsessionid.size()+"::::"+noofsessions);
				//Returning value if only repeted count & filtered table counts are equal and both filter session id & table id should be same//  
				if((Filteredsessionid.size() == noofsessions))
				{
					validation= true;
					System.out.println("truee");
					logger.info("No of " + FilterName + " filtered and no of repeted session id's availble in table is same.");
				}
				else {
					validation= false;
					logger.info("No of " + FilterName + " filtered and no of repeted session id's availble in table is not same.");
				}
				
				}
				else {
					validation = false;
					logger.info(FilterName +" Filtered result is empty, while check with first row data, It is not working properly");
					System.out.println(FilterName +" Filtered result is empty, while check with first row data, It is not working properly");
				}
				}
			else {
				validation = false;
				logger.info("BP session table is empty");
				System.out.println("BP session table is empty");
			}
				/*for(int j=0; j<filtersession.length(); j++)
				{
					Robot rr = new Robot();
					rr.keyPress(KeyEvent.VK_BACK_SPACE);
					rr.keyRelease(KeyEvent.VK_BACK_SPACE);
					Thread.sleep(500);
					
					/*rr.keyPress(KeyEvent.VK_CONTROL);
					rr.keyPress(KeyEvent.VK_A);
					rr.keyRelease(KeyEvent.VK_CONTROL);
					rr.keyRelease(KeyEvent.VK_A);
					
					rr.keyPress(KeyEvent.VK_DELETE);
					rr.keyRelease(KeyEvent.VK_DELETE);
					Thread.sleep(1000);
				}	*/	
				
		} catch (Exception e) {
			validation = false;
			System.out.println("Error in check" + FilterName + "filter : "+e);
			logger.error("Error in check" + FilterName + "filter : "+e);
		}
		return validation;
	}
	
	public boolean paginator_arrows() {
		logger.info("-- check_paginator_arrows_are_enabled_based_on_pages --");
		boolean arrow=false;
		List<WebElement> pageslist = new ArrayList<>();
		try {
			
			if(paginator_pages.size() > 1)
			{
				logger.info("BP session Paginator have more then one page");
				pageslist = paginator_pages;
				for(int page=0; page<paginator_pages.size(); page++)
				{
					if((pageslist.get(page).getAttribute("class").substring(70, 85).equals("ui-state-active")) && (paginator_pages.get(page).getText().equals("1")))
					{
						logger.info("BP session paginator first page is active");
						if((paginator_buttons.get(2).getAttribute("tabindex").equals("0")) && 
								(paginator_buttons.get(3).getAttribute("tabindex").equals("0"))
								&& (paginator_buttons.get(1).getAttribute("tabindex").equals("-1"))
								&& (paginator_buttons.get(0).getAttribute("tabindex").equals("-1")))
						{
							logger.info("BP session first page is active and backward & left buttons are in disabled mode and forward & right buttons are enabled");
							arrow = true;
						}
						else {
							arrow = false;
							logger.info("paginator arrows are not enabled & disabled properely, when it is in first page");
							System.out.println("paginator arrows are not enabled & disabled properely, when it is in first page");
						}
					}
					
					else if((pageslist.get(page).getAttribute("class").substring(87, 102).equals("ui-state-active")) && ((page+1)!=paginator_pages.size()))
					{
						logger.info("BP session paginator after the first page & not an last page is active");
						if((paginator_buttons.get(2).getAttribute("tabindex").equals("0")) && 
								(paginator_buttons.get(3).getAttribute("tabindex").equals("0"))
								&& (paginator_buttons.get(1).getAttribute("tabindex").equals("0"))
								&& (paginator_buttons.get(1).getAttribute("tabindex").equals("0")))
						{
							logger.info("Now paginator is in mid of the page, so all forward & backward buttons are enabled.");
							arrow = true;
						}
						else {
							arrow = false;
							logger.info("paginator arrows are not enabled & disabled properely, when it is in middle page");
							System.out.println("paginator arrows are not enabled & disabled properely, when it is in middle page");
						}
					}
					else if((pageslist.get(page).getAttribute("class").substring(87, 102).equals("ui-state-active")) && ((page+1) == paginator_pages.size()))
					{
						logger.info("BP session paginator last page is active");
						if((paginator_buttons.get(2).getAttribute("tabindex").equals("-1")) && 
								(paginator_buttons.get(3).getAttribute("tabindex").equals("-1"))
								&& (paginator_buttons.get(1).getAttribute("tabindex").equals("0"))
								&& (paginator_buttons.get(1).getAttribute("tabindex").equals("0")))
						{
							logger.info("When the last page is active, buttons backward & left are active.");
							arrow = true;
						}
						else {
							arrow = false;
							logger.info("paginator arrows are not enabled & disabled properely, when it is in last page");
							System.out.println("paginator arrows are not enabled & disabled properely, when it is in last page");
						}
					}
					else {
						arrow = false;
						logger.info("Paginator forward & backward arrows are not enabling properly");
						System.out.println("Paginator forward & backward arrows are not enabling properly");
					}
						
					if(paginator_pages.size() > page+1)
					{
						paginator_pages.get(page+1).click();
						Thread.sleep(1000);
						logger.info("paginator have "+paginator_pages.size()+" pages, so it's moved to "+(page+1)+" page");
						pageslist = paginator_pages;
					}
					if(paginator_pages.size() == page+1)
					{
						paginator_pages.get(paginator_pages.size()-(page+1)).click();
						Thread.sleep(1000);
					}
					}
			}
			else {
				arrow = true;
				logger.info("Paginator have not more than one pages");
				System.out.println("Paginator have not more than one pages");
			}
		//driver.navigate().refresh();	
		} catch (Exception e) {
			arrow = false;
			logger.error("Error in paginator arrows check :"+e);
			System.out.println("Error in paginator arrows check : "+e);
			
		}
		return arrow;
}
	
	public void back_to_adminconsole_homepage() {
		try {
			home_icon.click();
			logger.info("Back to adminconsole home page.");
		} catch (Exception e) {
			logger.error("Error in back to adminconsole home page :"+ e);
			System.out.println("Error in back to adminconsole home page :"+ e);
		}

	}
	
	public boolean dashboard_validation(String assetname) {
		logger.info("-- check_wheather_assetname_is_available_correctly_in_BP_session_dashboard --");
		boolean status=false;
		try {
			
			//check bp session detail table have atleast one session detail. if should not allowed if no record found is displayed 
			if(BPsession_Tablebody.get(0).findElements(By.tagName("td")).size() > 1)
			{
			logger.info("checked bp session detail table have atleast one session detail.");
			String sessionid= BPsession_Tablebody.get(0).findElements(By.tagName("td")).get(0).getText();
			String runnumber = BPsession_Tablebody.get(0).findElements(By.tagName("td")).get(1).getText();
			String BPstatus = BPsession_Tablebody.get(0).findElements(By.tagName("td")).get(2).getText();
			
			BPsession_Tablebody.get(0).findElements(By.tagName("td")).get(15).findElement(By.tagName("img")).click();
			wait.until(ExpectedConditions.visibilityOf(dashboard_detailstable));
			
			
			//check whether session detail & dashboard have same status, assetname, session id & run number
			if(dashboard_details.get(0).getText().equals("Status: "+BPstatus)
					&& dashboard_details.get(1).getText().equals(assetname)
					&& dashboard_details.get(2).getText().equals("Session ID: "+sessionid)
					&& dashboard_details.get(3).getText().equals("Run Number: "+runnumber))
			{
				status=true;
				logger.info("All the BP dashboard details status, assetname, session id & run number are same as session details table.");
			}
			else {
				status = false;
				logger.info("BP dashboard details status, assetname, session id & run number are not     same as session details table.");
			}
			}
			else {
				status = false;
				logger.info("BP session details table body have no session's");
				System.out.println("BP session details table body have no session's");
			}
			Thread.sleep(2000);
			driver.navigate().back();
			Thread.sleep(1000);
		} catch (Exception e) {
			status = false;
			logger.info("Error in BP sesion Dashboard validation");
			System.out.println("Error in BP sesion Dashboard validation"+ e);
		}
		return status;
	}
	
	public boolean traceability_validation(String assetname) {
		logger.info("-- check wheather assetname sessionID is same as in BP session traceability --");
		boolean status= false;
		try {
			if(BPsession_Tablebody.get(0).findElements(By.tagName("td")).size() > 1)
			{
				logger.info("checked bp session detail table have atleast one session detail.");
			String sessionid= BPsession_Tablebody.get(0).findElements(By.tagName("td")).get(0).getText();
			String runnumber = BPsession_Tablebody.get(0).findElements(By.tagName("td")).get(1).getText();
			
			BPsession_Tablebody.get(0).findElements(By.tagName("td")).get(16).findElement(By.tagName("img")).click();
			logger.info("Moved to tracebility page by click the trace column img");
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(traceability_assetname)));
			if(traceability_assetname.getText().equals(assetname) 
					&& traceability_details.get(0).getText().equals(sessionid)
					&& traceability_details.get(1).getText().equals(runnumber))
			{
				status=true;
				logger.info("BP session traceability details asset name & session ID & run number are same as session details.");
			}
			else {
				status=false;
				logger.info("BP session traceability page assetname, sessionID & runnumber are not same as session details");
				System.out.println("BP session traceability page assetname, sessionID & runnumber are not same as session details");
			}
			}
			else {
				status = false;
				logger.info("BP session table have not no session details, session table have 'No Record Found' row");
				System.out.println("BP session table have not no session details, session table have 'No Record Found' row");
				
			}
			Thread.sleep(2000);
			driver.navigate().back();
		} catch (Exception e) {
			status = false;
			logger.info("Error in validation of tracebility page asset name, session id & run number : "+ e);
			System.out.println("Error in validation of tracebility page asset name, session id & run number : "+e);
		}
		return status;

	}
	
	private void control_delete() throws AWTException, InterruptedException {
		Robot rr =new Robot();
		rr.keyPress(KeyEvent.VK_CONTROL);
		rr.keyPress(KeyEvent.VK_A);
		
		
		rr.keyRelease(KeyEvent.VK_CONTROL);
		rr.keyRelease(KeyEvent.VK_A);
		
		rr.keyPress(KeyEvent.VK_BACK_SPACE);
		rr.keyRelease(KeyEvent.VK_BACK_SPACE);
		Thread.sleep(1000);

	}

	}
	
	
