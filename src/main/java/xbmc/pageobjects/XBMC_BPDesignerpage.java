package xbmc.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import javax.swing.text.TabExpander;
import javax.xml.xpath.XPath;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Suntec.xbmcsanitytest.Maintest;
import cucumber.runtime.Timeout;

public class XBMC_BPDesignerpage extends Maintest {

	public WebDriver driver;
	WebDriverWait wait;

	public XBMC_BPDesignerpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
	}

	@FindBy(xpath = "//div[@class='diagramDiv']/child::canvas")
	private WebElement waitforcanvas;

	@FindBy(xpath = "(//div[@class = 'paletteDiv'])/child::canvas")
	private WebElement operator_Palette;

	@FindBy(xpath = "(//div[@class = 'diagramDiv'])/child::canvas")
	private WebElement design_Palette;

	private String designPalette = "(//div[@class = 'diagramDiv'])/child::canvas";

	// Start operator elements//
	@FindBy(xpath = "//select[@id='inputBeType']")
	private WebElement business_Setting;

	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li[2]/a")
	private WebElement Propertie;
	
	@FindBy(xpath = "//div[@class = 'table-responsive scroll-table']/table/tbody/tr")
	private List<WebElement> Settingsproperties_table;

	//////// properties locateors are taken as list ///////
	//@FindBy(xpath = "//div[@class = 'table-responsive scroll-table']/table/tbody/tr[1]/td[2]/div/input")
	//private WebElement penalty_Duration;

	@FindBy(xpath = "//div[@class = 'table-responsive scroll-table']/table/tbody/tr[2]/td[2]/div/input")
	private WebElement yield_Duration;

	@FindBy(xpath = "//div[@class = 'table-responsive scroll-table']/table/tbody/tr[3]/td[2]/div/input")
	private WebElement concurrent_Task;

	@FindBy(xpath = "(//button[@class='btn btn-primary '])[2]")
	private WebElement save_Button;

	// invokebs operator elements//
	@FindBy(xpath = "//select[@id='businessServiceName']")
	private WebElement business_services;

	private String businessservices = "//select[@id='businessServiceName']";

	@FindBy(xpath = "//select[@id='api']")
	private WebElement API_name;
	
	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li")
	private List<WebElement> operator_settings_menu;
	
	@FindBy(xpath = "//div[@class='tab-content']/div[2]/div[1]/div/table/tbody/tr")
	private List<WebElement> IPmapping_table;

	//@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/child::li[2]/a")
	//private WebElement IP_Mapping;

	/*@FindBy(xpath = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[1]/td[3]/select")
	private WebElement IP_Mapping_PVValue_ipAccNO;

	private String IPAccNo_PV = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[1]/td[4]/div/div/input";

	@FindBy(xpath = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[1]/td[4]/div/div/input")
	private WebElement IPAccNo_PV_Enteralue;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[2]/td[3]/select")
	private WebElement IP_Mapping_PVValue_ipAccID;

	private String IPAccID_PV = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[2]/td[4]/div/div/input";

	@FindBy(xpath = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[2]/td[4]/div/div/input")
	private WebElement IPAccID_PV_Enteralue;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[3]/td[3]/select")
	private WebElement IP_Mapping_PVValue_ipDate;

	private String IPDate_PV = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[3]/td[4]/div/div/input";

	@FindBy(xpath = "//div[@class='table-responsive scroll-table1 ']/table/tbody/tr[3]/td[4]/div/div/input")
	private WebElement IPDate_PV_Enteralue;*/

	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li[3]/a")
	private WebElement OP_Mapping;

	@FindBy(xpath = "//div[@class='tab-pane active']/div/button/i")
	private WebElement ADD_Button;
	
	@FindBy(xpath = "(//div[@class='table-responsive scroll-table'])[2]/table/tbody/tr")
	private List<WebElement> OPparameter_table;

	private String ADDButton = "//div[@class='tab-pane active']/div/button/i";

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr")
	private List<WebElement> PV_Tablerow_Visible;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[1]/td[1]/select")
	private WebElement opMapping_ProcessVariable1;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[1]/td[3]/select")
	private WebElement opMapping_Select_Value1;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[1]/td[4]/div/div/input")
	private WebElement opMapping_Enter_Value1;

	private String PV_Tablerow2_Visible = "//div[@class='table-responsive scroll-table']/table/tbody/tr[2]";

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[2]/td[1]/select")
	private WebElement opMapping_ProcessVariable2;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[2]/td[3]/select")
	private WebElement opMapping_Select_Value2;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[2]/td[4]/div/div/input")
	private WebElement opMapping_Enter_Value2;

	private String PV_Tablerow3_Visible = "//div[@class='table-responsive scroll-table']/table/tbody/tr[3]";

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[3]/td[1]/select")
	private WebElement opMapping_ProcessVariable3;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[3]/td[3]/select")
	private WebElement opMapping_Select_Value3;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[3]/td[4]/div/div/input")
	private WebElement opMapping_Enter_Value3;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody")
	private WebElement opMapping_Table;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[4]/td[1]/select")
	private WebElement opMapping_ProcessVariable4;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[4]/td[3]/select")
	private WebElement opMapping_Select_Value4;

	@FindBy(xpath = "//div[@class='table-responsive scroll-table']/table/tbody/tr[4]/td[4]/div/div/input")
	private WebElement opMapping_Enter_Value4;

	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li[4]/a")
	private WebElement invokebs_Properties_Menu;

	@FindBy(xpath = "(//div[@class='table-responsive scroll-table'])[2]/table/tbody/tr[1]/td/div/input")
	private WebElement invokebs_Properties_PenaltyDur;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped table-layout table-freeze']/tbody/tr[2]/td[2]/div/input")
	private WebElement invokebs_Properties_YieldDur;

	@FindBy(xpath = "//table[@class='table table-bordered table-striped table-layout table-freeze']/tbody/tr[3]/td[2]/div/input")
	private WebElement invokebs_Properties_ConcurrentTask;

	@FindBy(xpath = "(//div[@class='col-md-12 form_btn_bottom text-right ng-star-inserted'])[3]/button")
	private WebElement invokebs_Operator_Save;

	// End operator element//
	@FindBy(xpath = "//select[@name='outputBeType']")
	private WebElement endOperator_BE_Select;

	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li[2]/a")
	private WebElement endOperator_Properties;

	@FindBy(xpath = "(//div[@class='col-md-12 form_btn_bottom text-right ng-star-inserted'])[2]/button")
	private WebElement end_Operator_SaveButton;

	// @FindBy(xpath = "//nav[@id='nav-menu-container']/ul/li[1]/a[text()='Flow']")
	@FindBy(xpath = "//*[@id='nav-menu-container']/ul/li[1]/a")
	private WebElement Menu_Flow;

	// @FindBy(xpath = "//*[@id='nav-menu-container']/ul/li[1]/a")
	private String Menu_Flow1 = "//*[@id='nav-menu-container']/ul/li[1]/a";

	@FindBy(xpath = "//nav[@id='nav-menu-container']/ul/li[1]/ul/li[2]/button")
	private WebElement MenuFlow_Save;
	
	@FindBy(xpath = "//nav[@id='nav-menu-container']/ul/li/ul/li[1]/button[text()='Configuration BP']")
	private WebElement MenuFlow_configurationBP;
	
	@FindBy(xpath = "//div[@class='table-responsive proc_var_tb']/table/tbody")
	private List<WebElement> configureBP_processvariable_table;
	
	private String confBP_profilableAT_solution = "//following::table/tbody/tr/td[1]/div/label/input";
	
	private String confBP_profilableAT_operation = "//following::table/tbody/tr/td[2]/div/label/input";
	
	
	
	private String confBP_processvariable_deleteButton = "//div[@class='ng-star-inserted']/button/span";
	
	@FindBy(xpath = "//div[@class='row justify-content-between   process_var_row']/div[2]/button/i")
	private WebElement confBP_processvariable_addButton;
	
	private String confBP_processvariable_defaultValue_number = "//div[@class='row']/div/div/input";
	
	private String confBP_processvariable_defaultValue_number_errorMSG="//div[@class='row']/div/span";
	
	private String confBP_processvariable_defaultValue_string = "//div[@class='row']/div/input";
	
	private String confBP_processvariable_mandatory_checkbox = "//div[@class='form-check mr-sm-4 ng-star-inserted']/label/input";
	
	@FindBy(xpath = "//div[@class = 'modal-footer']/button")
	private WebElement confBP_save;
	
	@FindBy(xpath = "//div[@class='business-row']/div[2]/div/div")
	private List<WebElement> businessProcess_setup;
	
	

	@FindBy(xpath = "//diagram-control[@class='diagramControlDiv']/div[2][@id='toast']")
	private WebElement Saved_Successmsg_show;
	
	@FindBy(xpath = "//diagram-control[@class='diagramControlDiv']/div[2][@id='toast']/div[2]")
	private WebElement Saved_Successmsg;

	private String Saved_successmsg = "//div[@class='show']/div/div[@id='desc']']";
	// *[@id="desc"]

	@FindBy(xpath = "//*[@id='topHeader']/div/div/div[1]/ul/li[2]/a")
	private WebElement logo;

	@FindBy(xpath = "//nav[@id='nav-menu-container']/ul/li[1]/ul/li[4]/button")
	private WebElement bpflowValidateion;

	private String bpflowValidationpopupbox = "//ngb-modal-window[@class='modal fade show d-block']/div/div/form/div/h6";

	@FindBy(xpath = "//div[@class='tab-pane active ng-star-inserted']/div[2]/div")
	private WebElement validation_errorlessmessage;

	@FindBy(xpath = "//div[@class='modal-body modal-body-bp  tab-content_BP']/div/table/tbody/tr[1]")
	private WebElement validation_errormessage;

	// @FindBy(xpath = "//ngb-modal-window[@class='modal fade show
	// d-block']/div/div/form/child::div[@class='modal-footer']/button")
	// private WebElement validation_popupClose;

	@FindBy(xpath = "/html/body/ngb-modal-window/div/div/form/div[2]/button")
	private WebElement validation_popupClose;
	
	@FindBy(xpath= "//div[@class='col-sm-6 pad_0']/ul/li[2]/a")
	private WebElement assetname_in_bpdesigner;
	
	@FindBy(xpath = "(//table[@class='table thead-border table-freeze'])[1]/tbody/tr[1]/td[4]/select")
	public WebElement invokebs_inputmapping_ipAccountNo_pv;
	
	@FindBy(xpath = "(//table[@class='table thead-border table-freeze'])[1]/tbody/tr[2]/td[4]/select")
	public WebElement invokebs_inputmapping_ipAccountId_pv;
	
	@FindBy(xpath = "(//table[@class='table thead-border table-freeze'])[1]/tbody/tr[3]/td[4]/select")	
	private WebElement invokebs_inputmapping_ipDate_pv;
	
	@FindBy(xpath = "//div[@class='form_properties']/select")
	private WebElement DMExclusive_bs_ipBE;
	
	@FindBy(xpath = "//div[@class='row mg_0 ng-star-inserted']/div/button/i")
	private WebElement DMExclusive_bs_condition_addbutton;
	
	@FindBy(xpath = "(//div[@class='col-md-12 mg_10'])[2]/div/table/tbody/tr[2]/td[1]/input")
	private WebElement DMExclusive_bs_condition_conditionName;
	
	
	@FindBy(xpath = "(//div[@class='col-md-12 mg_10'])[2]/div/table/tbody/tr[2]/td[2]")
	private WebElement DMExclusive_bs_condition_conditionexpression;
	
	@FindBy(xpath = "//div[@class='diagramInfoDiv']/h4[text()  =  'Rule Builder ']")
	private WebElement DMExclusive_bs_condition_rulebuilder_text;
	
	@FindBy(xpath = "//div[@class='q-tree-container']/ul/li[1]/div[2]/div[1]/div/select")
	private WebElement DMExclusive_bs_condition_rulebuilder_value1;
	
	@FindBy(xpath = "//div[@class='q-tree-container']/ul/li[1]/div[2]/div[2]/select")
	private WebElement DMExclusive_bs_condition_rulebuilder_value2;
	
	@FindBy(xpath = "//div[@class='q-tree-container']/ul/li[1]/div[2]/div[3]/select")
	private WebElement DMExclusive_bs_condition_rulebuilder_value3;
	
	@FindBy(xpath = "//div[@class='q-tree-container']/ul/li[1]/div[3]/div[1]/select")
	private WebElement DMExclusive_bs_condition_rulebuilder_value4;
	
	@FindBy(xpath = "//div[@class='q-tree-container']/ul/li[1]/div[3]/div[2]/div/select")
	private WebElement DMExclusive_bs_condition_rulebuilder_value5;
	
	
	@FindBy(xpath = "//div[@class='q-tree-container']/ul/li[2]/div/button")
	private WebElement DMExclusive_bs_condition_rulebuilder_exClose2;
	

	@FindBy(xpath = "//div[@class='modal-footer']/button[text() = 'Save']")
	private WebElement DMExclusive_bs_condition_rulebuilder_saveButton;
	
	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li[2]")
	private WebElement DMExclusive_ProcessVariable_click;
	
	@FindBy(xpath = "//div[@class='col-md-12 ng-star-inserted']/process-variable/button")
	private WebElement DMExclusive_ProcessVariable_defaultpage_addbutton;
	
	@FindBy(xpath = "//div[@class='col-md-12 ng-star-inserted']/process-variable/div/table/tbody/tr/td[1]/select")
	private WebElement DMExclusive_ProcessVariable_defaultpage_pv1Select;
	
	@FindBy(xpath = "//div[@class='col-md-12 ng-star-inserted']/ngb-pagination/ul/li[4]")
	private WebElement DMExclusive_ProcessVariable_movetoDesisionPage;
	
	@FindBy(xpath = "//table[@class='table table-striped table-layout table-freeze thead-border pvariable_table']/tbody/tr[1]/td/select")
	private WebElement DMExclusive_ProcessVariable_decisionpage_pv1;
	
	@FindBy(xpath = "//table[@class='table table-striped table-layout table-freeze thead-border pvariable_table']/tbody/tr[2]/td/select")
	private WebElement DMExclusive_ProcessVariable_decisionpage_pv2;
	
	@FindBy(xpath = "//table[@class='table table-striped table-layout table-freeze thead-border pvariable_table']/tbody/tr[3]/td/select")
	private WebElement DMExclusive_ProcessVariable_decisionpage_pv3;
	
	@FindBy(xpath = "//table[@class='table table-striped table-layout table-freeze thead-border pvariable_table']/tbody/tr[4]/td/select")
	private WebElement DMExclusive_ProcessVariable_decisionpage_pv4;
	
	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li[3]")
	private WebElement DMExclusive_Properties_click;
	
	@FindBy(xpath = "(//div[@class='col-md-12 form_btn_bottom text-right ng-star-inserted'])[3]/button")
	private WebElement DMExclusive_Properties_save;
	
	@FindBy(xpath = "//h4[text() = 'Settings-Join Operator ']")
	private WebElement join_header;
	
	@FindBy(xpath = "//div[@class = 'col-md-12 mg_10 ng-star-inserted']/div/select")
	private WebElement join_inputBE_select;
	
	@FindBy(xpath = "//ul[@class='nav nav-pills justify-content-start']/li[2]")
	private WebElement Join_Properties_click;
	
	@FindBy(xpath = "(//div[@class='col-md-12 form_btn_bottom text-right ng-star-inserted'])[2]/button")
	private WebElement join_opsettigs_save;
	
	@FindBy(xpath = "//div[@class = 'table-responsive scroll-table1 ']/table/tbody/tr")
	private List<WebElement> smart_connector_mapping;
	
	
	
	
	public boolean checkAssetname_in_bpdesignerpage(String assetName) {
		try {
			System.out.println(assetname_in_bpdesigner.getText());
			if(assetname_in_bpdesigner.getText().equals(assetName))
			{
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error in checkAssetname_in_bpdesignerpage, searched asset name & BP designer names are different");
		return false;
		}
		

	}



	public void Design_palette_cleanp() {
		try {
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(designPalette)));
			design_Palette.click();
			Robot designpalette_robot = new Robot();
			designpalette_robot.keyPress(KeyEvent.VK_CONTROL);
			designpalette_robot.keyPress(KeyEvent.VK_A);
			designpalette_robot.keyRelease(KeyEvent.VK_CONTROL);
			designpalette_robot.keyRelease(KeyEvent.VK_A);
			designpalette_robot.keyPress(KeyEvent.VK_DELETE);
			designpalette_robot.keyRelease(KeyEvent.VK_DELETE);
			
			//Actions menuFlow_Mousehover = new Actions(driver);

			//menuFlow_Mousehover.moveToElement(Menu_Flow).build().perform();

			// Menu_Flow.click();
			//menuFlow_Mousehover.moveToElement(MenuFlow_Save).click().build().perform();

		} catch (Exception e) {
			System.out.println("Error in BP designer pallet cleanup");

		}

	}
	
	public boolean configurationBP_BPsetUP_setting() {
		try {
		Actions menuFlow_Mousehover = new Actions(driver);
		menuFlow_Mousehover.moveToElement(Menu_Flow).build().perform();
		menuFlow_Mousehover.moveToElement(MenuFlow_configurationBP).click().build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElements(businessProcess_setup));
		
		/*for(int i=0; i < businessProcess_setup.size(); i++)
		{
			WebElement selector_button = businessProcess_setup.get(i).findElement(By.tagName("label")).findElement(By.tagName("input"));
			selector_button.click();
		}*/
		return true;
		}
		catch(Exception e)
		{
			System.out.println("Error in Configuration BP , Is profilable check box setting");
			return false;
		}

	}
	
	public boolean configurationBP_delete_processVariable_setting() {
		try {
			
			
			if (configureBP_processvariable_table.size() >= 3)
			{
				configureBP_processvariable_table.get(1).click();
				
				while(configureBP_processvariable_table.size() > 3)
				{
					int tbody=3;
					//JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
					//mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",configureBP_processvariable_table.get(i));
			
					WebElement delete_oldconfigprop = configureBP_processvariable_table.get(tbody).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(7).findElement(By.xpath(confBP_processvariable_deleteButton));				
					
					//mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",delete_oldconfigprop);
					
					delete_oldconfigprop.click();
				}
			}
			else {
				System.out.println("Configuration BP process variable have only default value.");
				return true;
			}
			return true;
		} catch (Exception e) {
			System.out.println("Error in configutation BP old process variable delete : "+e);
			return false;
		}

	}
	
	public boolean configurationBP_wrong_processVariable_setting_forNumber(ArrayList<ArrayList<String>> proVariable) {
		try {
			
				confBP_processvariable_addButton.click();
				
				JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
				mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",configureBP_processvariable_table.get(3).findElement(By.tagName("tr")));
				
				
						WebElement PV_td0 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));
						PV_td0.sendKeys(proVariable.get(0).get(0));
				
						WebElement PV_td1 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("textarea"));
						PV_td1.sendKeys(proVariable.get(0).get(1));
					
						WebElement PV_td2 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type_category = new Select(PV_td2);
						type_category.selectByVisibleText(proVariable.get(0).get(2));
				
						WebElement PV_td3 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type = new Select(PV_td3);
						type.selectByVisibleText(proVariable.get(0).get(3));
				
						if(proVariable.get(0).get(4-1).equals("Number"))
						{
							WebElement PV_td3_precision = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(0);
							PV_td3_precision.sendKeys("5");
							
							WebElement PV_td3_scale = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(1);
							PV_td3_scale.sendKeys("2");
							
							WebElement PV_td4 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.xpath(confBP_processvariable_defaultValue_number));
							PV_td4.sendKeys(proVariable.get(0).get(4));
						}
						else if(proVariable.get(0).get(4-1).equals("String"))
						{
							WebElement PV_td4_string = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
						PV_td4_string.sendKeys(proVariable.get(0).get(4));
						}
						else if(proVariable.get(0).get(4-1).equals("Boolean"))
						{
							WebElement PV_td4_boolean = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							if(proVariable.get(0).get(4).equals("TRUE"))
							{
								PV_td4_boolean.click();
							}
						}
						else if(proVariable.get(0).get(4-1).equals("DateTime"))
						{
							WebElement PV_td4_dattime = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							PV_td4_dattime.sendKeys(proVariable.get(0).get(4));
						}
			
				
				
				WebElement PV_td5_mandatory = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(5).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_mandatory.click();
				
				WebElement PV_td5_profilableAT_solution = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_profilableAT_solution.click();
				
				WebElement pv_td5_profilableAT_operation = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				pv_td5_profilableAT_operation.click();
				
				
			
				confBP_save.click();
				
				WebElement error_text = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("span"));
				System.out.println("Error : "+error_text.getText().toString());
				
				
				if((error_text.getText().equals("Entered value's Scale is wrong")))
				{
					configurationBP_delete_processVariable_setting();
					return true;
				}
				else {
					return false;
				}
			
		} catch (Exception e) {
			System.out.println("Error in configurationBP add processVariable setting : "+e);
			return false;
		}

	}
	
	public boolean configurationBP_empty_processVariable_setting_forNumber(ArrayList<ArrayList<String>> proVariable) {
		try {
			
			
			
			//configurationBP_delete_processVariable_setting();
			
				confBP_processvariable_addButton.click();
				
				JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
				mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",configureBP_processvariable_table.get(3).findElement(By.tagName("tr")));
				
				
						WebElement PV_td0 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));
						PV_td0.sendKeys(proVariable.get(0).get(0));
				
						WebElement PV_td1 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("textarea"));
						PV_td1.sendKeys(proVariable.get(0).get(1));
					
						WebElement PV_td2 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type_category = new Select(PV_td2);
						type_category.selectByVisibleText(proVariable.get(0).get(2));
				
						WebElement PV_td3 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type = new Select(PV_td3);
						type.selectByVisibleText(proVariable.get(0).get(3));
				
						if(proVariable.get(0).get(4-1).equals("Number"))
						{
							WebElement PV_td3_precision = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(0);
							PV_td3_precision.sendKeys("5");
							
							WebElement PV_td3_scale = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(1);
							PV_td3_scale.sendKeys("2");
							
							WebElement  PV_td4 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.xpath(confBP_processvariable_defaultValue_number));
							PV_td4.sendKeys("");
							//PV_td4.clear();
						}
						else if(proVariable.get(0).get(4-1).equals("String"))
						{
							WebElement PV_td4_string = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
						PV_td4_string.sendKeys(proVariable.get(0).get(4));
						}
						else if(proVariable.get(0).get(4-1).equals("Boolean"))
						{
							WebElement PV_td4_boolean = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							if(proVariable.get(0).get(4).equals("TRUE"))
							{
								PV_td4_boolean.click();
							}
						}
						else if(proVariable.get(0).get(4-1).equals("DateTime"))
						{
							WebElement PV_td4_dattime = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							PV_td4_dattime.sendKeys(proVariable.get(0).get(4));
						}
			
				
				
				WebElement PV_td5_mandatory = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(5).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_mandatory.click();
				
				WebElement PV_td5_profilableAT_solution = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_profilableAT_solution.click();
				
				WebElement pv_td5_profilableAT_operation = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				pv_td5_profilableAT_operation.click();
				
				
			
				confBP_save.click();
				
				WebElement error_text = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("span"));
				System.out.println("Error : "+error_text.getText().toString());
				
				if(error_text.getText().equals("Precision/Value cannot be Zero/Empty"))
				{
					configurationBP_delete_processVariable_setting();
					return true;
				}
				else {
					return false;
				}
			
		} catch (Exception e) {
			System.out.println("Error in configurationBP add processVariable setting : "+e);
			return false;
		}
	}
	
	public boolean configurationBP_check_empty_processvariable_name(ArrayList<ArrayList<String>> proVariable) 
	{
		try {
			//configurationBP_delete_processVariable_setting();
			
				confBP_processvariable_addButton.click();
				
				JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
				mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",configureBP_processvariable_table.get(3).findElement(By.tagName("tr")));
				
				
						WebElement PV_td0 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));
						PV_td0.sendKeys("");
				
						WebElement PV_td1 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("textarea"));
						PV_td1.sendKeys(proVariable.get(0).get(1));
					
						WebElement PV_td2 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type_category = new Select(PV_td2);
						type_category.selectByVisibleText(proVariable.get(0).get(2));
				
						WebElement PV_td3 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type = new Select(PV_td3);
						type.selectByVisibleText(proVariable.get(0).get(3));
				
						if(proVariable.get(0).get(4-1).equals("Number"))
						{
							WebElement PV_td3_precision = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(0);
							PV_td3_precision.sendKeys("5");
							
							WebElement PV_td3_scale = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(1);
							PV_td3_scale.sendKeys("2");
							
							WebElement  PV_td4 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.xpath(confBP_processvariable_defaultValue_number));
							PV_td4.sendKeys("");
							//PV_td4.clear();
						}
						else if(proVariable.get(0).get(4-1).equals("String"))
						{
							WebElement PV_td4_string = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
						PV_td4_string.sendKeys(proVariable.get(0).get(4));
						}
						else if(proVariable.get(0).get(4-1).equals("Boolean"))
						{
							WebElement PV_td4_boolean = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							if(proVariable.get(0).get(4).equals("TRUE"))
							{
								PV_td4_boolean.click();
							}
						}
						else if(proVariable.get(0).get(4-1).equals("DateTime"))
						{
							WebElement PV_td4_dattime = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							PV_td4_dattime.sendKeys(proVariable.get(0).get(4));
						}
			
				
				
				WebElement PV_td5_mandatory = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(5).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_mandatory.click();
				
				WebElement PV_td5_profilableAT_solution = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_profilableAT_solution.click();
				
				WebElement pv_td5_profilableAT_operation = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				pv_td5_profilableAT_operation.click();
				
				
			
				confBP_save.click();
				
				WebElement error_text = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElements(By.tagName("span")).get(0);
				System.out.println("Error : "+error_text.getText().toString());
				
				if(error_text.getText().equals("*Process Variable Name is mandatory."))
				{
					configurationBP_delete_processVariable_setting();
					return true;
				}
				else {
					return false;
				}
			
		} catch (Exception e) {
			System.out.println("Error in configurationBP add processVariable setting : "+e);
			return false;
		}

	}
	
	public boolean configurationBP_check_wrong_processvariable_name(ArrayList<ArrayList<String>> proVariable) 
	{
		try {
			//configurationBP_delete_processVariable_setting();
			
				confBP_processvariable_addButton.click();
				
				JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
				mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",configureBP_processvariable_table.get(3).findElement(By.tagName("tr")));
				
				
						WebElement PV_td0 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));
						PV_td0.sendKeys("  ");
				
						WebElement PV_td1 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("textarea"));
						PV_td1.sendKeys(proVariable.get(0).get(1));
					
						WebElement PV_td2 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type_category = new Select(PV_td2);
						type_category.selectByVisibleText(proVariable.get(0).get(2));
				
						WebElement PV_td3 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type = new Select(PV_td3);
						type.selectByVisibleText(proVariable.get(0).get(3));
				
						if(proVariable.get(0).get(4-1).equals("Number"))
						{
							WebElement PV_td3_precision = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(0);
							PV_td3_precision.sendKeys("5");
							
							WebElement PV_td3_scale = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(1);
							PV_td3_scale.sendKeys("2");
							
							WebElement  PV_td4 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.xpath(confBP_processvariable_defaultValue_number));
							PV_td4.sendKeys("");
							//PV_td4.clear();
						}
						else if(proVariable.get(0).get(4-1).equals("String"))
						{
							WebElement PV_td4_string = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
						PV_td4_string.sendKeys(proVariable.get(0).get(4));
						}
						else if(proVariable.get(0).get(4-1).equals("Boolean"))
						{
							WebElement PV_td4_boolean = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							if(proVariable.get(0).get(4).equals("TRUE"))
							{
								PV_td4_boolean.click();
							}
						}
						else if(proVariable.get(0).get(4-1).equals("DateTime"))
						{
							WebElement PV_td4_dattime = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							PV_td4_dattime.sendKeys(proVariable.get(0).get(4));
						}
			
				
				
				WebElement PV_td5_mandatory = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(5).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_mandatory.click();
				
				WebElement PV_td5_profilableAT_solution = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_profilableAT_solution.click();
				
				WebElement pv_td5_profilableAT_operation = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				pv_td5_profilableAT_operation.click();
				
				
			
				confBP_save.click();
				
				WebElement error_text = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElements(By.tagName("span")).get(0);
				
				
				if(error_text.getText().equals("*Name you entered contains invalid name or character or space."))
				{
					configurationBP_delete_processVariable_setting();
					System.out.println("Error : "+error_text.getText().toString());
					return true;
				}
				else {
					return false;
				}
			
		} catch (Exception e) {
			System.out.println("Error in configurationBP add processVariable setting : "+e);
			return false;
		}

	}
	
	
	public boolean configurationBP_check_empty_processvariable_description(ArrayList<ArrayList<String>> proVariable) 
	{
		try {
		//	configurationBP_delete_processVariable_setting();
			
				confBP_processvariable_addButton.click();
				
				JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
				mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",configureBP_processvariable_table.get(3).findElement(By.tagName("tr")));
				
				
						WebElement PV_td0 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));
						PV_td0.sendKeys(proVariable.get(0).get(0));
				
						WebElement PV_td1 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("textarea"));
						PV_td1.sendKeys("");
					
						WebElement PV_td2 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type_category = new Select(PV_td2);
						type_category.selectByVisibleText(proVariable.get(0).get(2));
				
						WebElement PV_td3 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type = new Select(PV_td3);
						type.selectByVisibleText(proVariable.get(0).get(3));
				
						if(proVariable.get(0).get(4-1).equals("Number"))
						{
							WebElement PV_td3_precision = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(0);
							PV_td3_precision.sendKeys("5");
							
							WebElement PV_td3_scale = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(1);
							PV_td3_scale.sendKeys("2");
							
							WebElement  PV_td4 = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.xpath(confBP_processvariable_defaultValue_number));
							PV_td4.sendKeys("");
							//PV_td4.clear();
						}
						else if(proVariable.get(0).get(4-1).equals("String"))
						{
							WebElement PV_td4_string = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
						PV_td4_string.sendKeys(proVariable.get(0).get(4));
						}
						else if(proVariable.get(0).get(4-1).equals("Boolean"))
						{
							WebElement PV_td4_boolean = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							if(proVariable.get(0).get(4).equals("TRUE"))
							{
								PV_td4_boolean.click();
							}
						}
						else if(proVariable.get(0).get(4-1).equals("DateTime"))
						{
							WebElement PV_td4_dattime = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							PV_td4_dattime.sendKeys(proVariable.get(0).get(4));
						}
			
				
				
				WebElement PV_td5_mandatory = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(5).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_mandatory.click();
				
				WebElement PV_td5_profilableAT_solution = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_profilableAT_solution.click();
				
				WebElement pv_td5_profilableAT_operation = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				pv_td5_profilableAT_operation.click();
				
				
			
				confBP_save.click();
				
				WebElement error_text = configureBP_processvariable_table.get(3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElements(By.tagName("span")).get(0);
				System.out.println("Error : "+error_text.getText().toString());
				
				if(error_text.getText().equals("*Process Variable Description is mandatory."))
				{
					configurationBP_delete_processVariable_setting();
					return true;
				}
				else {
					return false;
				}
			
		} catch (Exception e) {
			System.out.println("Error in configurationBP add processVariable setting : "+e);
			return false;
		}

	}
	
	
	public boolean configurationBP_add_processVariable_setting(ArrayList<ArrayList<String>> proVariable) {
		try {
			for(int i=0;  i < proVariable.size(); i++)
			{
				confBP_processvariable_addButton.click();
				
				JavascriptExecutor mouseover_setcontext = (JavascriptExecutor) driver;
				mouseover_setcontext.executeScript("arguments[0].scrollIntoView(true);",configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")));
				
				for(int td=0; td < proVariable.get(i).size();  td++)
				{
					//System.out.println("FUn : "+proVariable.get(i).get(td));
					switch (td)
					{
					case 0:
						WebElement PV_td0 = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("input"));
						PV_td0.sendKeys(proVariable.get(i).get(td));
						break;
					case 1:
						WebElement PV_td1 = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("textarea"));
						PV_td1.sendKeys(proVariable.get(i).get(td));
						break;
					case 2:
						WebElement PV_td2 = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type_category = new Select(PV_td2);
						type_category.selectByVisibleText(proVariable.get(i).get(td));
						break;
					case 3:
						WebElement PV_td3 = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("select"));
						Select type = new Select(PV_td3);
						type.selectByVisibleText(proVariable.get(i).get(td));
						break;
					case 4:
						if(proVariable.get(i).get(td-1).equals("Number"))
						{
							WebElement PV_td3_precision = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(0);
							PV_td3_precision.sendKeys("5");
							
							WebElement PV_td3_scale = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElements(By.tagName("input")).get(1);
							PV_td3_scale.sendKeys("2");
							
							WebElement PV_td4 = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.xpath(confBP_processvariable_defaultValue_number));
							PV_td4.sendKeys(proVariable.get(i).get(td));
						}
						else if(proVariable.get(i).get(td-1).equals("String"))
						{
							WebElement PV_td4_string = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							PV_td4_string.sendKeys(proVariable.get(i).get(td));
						}
						else if(proVariable.get(i).get(td-1).equals("Boolean"))
						{
							WebElement PV_td4_boolean = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							if(proVariable.get(i).get(td).equals("TRUE"))
							{
								PV_td4_boolean.click();
							}
						}
						else if(proVariable.get(i).get(td-1).equals("DateTime"))
						{
							WebElement PV_td4_dattime = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
							PV_td4_dattime.sendKeys(proVariable.get(i).get(td));
						}
						break;
					

					default:
						break;
					}
				}
				
				
				WebElement PV_td5_mandatory = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(5).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_mandatory.click();
				
				WebElement PV_td5_profilableAT_solution = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(0).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				PV_td5_profilableAT_solution.click();
				
				WebElement pv_td5_profilableAT_operation = configureBP_processvariable_table.get(i+3).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(6).findElement(By.tagName("table")).findElement(By.tagName("tbody")).findElement(By.tagName("tr")).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("label")).findElement(By.tagName("input"));
				pv_td5_profilableAT_operation.click();
				
				
			}
			confBP_save.click();
			return true;
			
		} catch (Exception e) {
			System.out.println("Error in configurationBP add processVariable setting : "+e);
			return false;
		}

	}
	

	
	
	public boolean Start_op_bussinessSetting_InputtypeBE(ArrayList<String> input_BE_Type) {
		try {
			Select input_BE = new Select(business_Setting);
			input_BE.selectByVisibleText(input_BE_Type.get(0));
			return true;
		} catch (Exception e) {
			System.out.println("Error in start operator, Input BE selection in bussiness setting : "+e);
			return false;
		}
	}
	
	public boolean start_op_properties_settings(ArrayList<String> properties) {
		try {

			Propertie.click();
			wait.until(ExpectedConditions.visibilityOfAllElements(Settingsproperties_table));
			
			for(int i=1; i < Settingsproperties_table.size(); i++)
			{
				WebElement prop = Settingsproperties_table.get(i).findElements(By.tagName("td")).get(1).findElement(By.tagName("div")).findElement(By.tagName("input"));
				prop.clear();
				prop.sendKeys(properties.get(i));
			}
			//Once properties are entered save by click save button
			save_Button.click();
			return true;

		} catch (Exception e) {
			System.out.println("Error in start operator properties setting : "+e);
			return false;
		}
	}
	
	public boolean invokebs_op_business_settings( String businessServiceName, String APIName) {
		

		// invokebs operator business setting//
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(businessservices)));
			
			Select business_Services_Select = new Select(business_services);
			business_Services_Select.selectByVisibleText(businessServiceName);

			Select API_Name_select = new Select(API_name);
			API_Name_select.selectByVisibleText(APIName);

			return true;
		} catch (Exception e) {
			System.out.println("error in Invokebs operator business setting" + e);
			return false;
		}
	}
	
	

	public boolean invokebs_op_inputmapping_settings(ArrayList<ArrayList<String>> IPPV_value) {
		// invokebs I/P mapping setting//
			try {
						//IP_Mapping.click();
				WebElement IPmapping = operator_settings_menu.get(1);
				IPmapping.click();
				
				//IPmapping process variables
				for(int i=0; i < IPmapping_table.size(); i++)
				{
					//System.out.println("I'm in "+IPmapping_table.size());
					//System.out.println("Text : "+IPmapping_table.get(i).findElements(By.tagName("td")).get(1).getText());
						
							WebElement SelectPV = IPmapping_table.get(i).findElements(By.tagName("td")).get(2).findElement(By.tagName("select"));
							
							Select PV = new Select(SelectPV);
							PV.selectByVisibleText(IPPV_value.get(i).get(2));
							
							wait.until(ExpectedConditions.visibilityOf(IPmapping_table.get(i).findElements(By.tagName("td")).get(2)));
							
							if(IPPV_value.get(i).get(2).equals("Enter Value"))
							{
								if((IPmapping_table.get(i).findElements(By.tagName("td")).get(1).getText().equals("  String ")) ||
										(IPmapping_table.get(i).findElements(By.tagName("td")).get(1).getText().equals("  Number ")) ||
										(IPmapping_table.get(i).findElements(By.tagName("td")).get(1).getText().equals("  DateTime ")) )
								{
									System.out.println("inside logic if string"+IPPV_value.get(i).get(3));
									WebElement EnterPV = IPmapping_table.get(i).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
									EnterPV.sendKeys(IPPV_value.get(i).get(3));
								}
								else if((IPmapping_table.get(i).findElements(By.tagName("td")).get(1).getText().equals("  Boolean")))
								{
									WebElement EnterPV = IPmapping_table.get(i).findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
									if(IPPV_value.get(i).get(3).toUpperCase().equals("TRUE"))
									{
										EnterPV.click();
									}
								}
							}
							else if (IPPV_value.get(i).get(2).equals("Process Variable")) 
							{
								WebElement EnterPV_select = IPmapping_table.get(i).findElements(By.tagName("td")).get(3).findElement(By.tagName("select"));
								Select Enterpv_select = new Select(EnterPV_select);
								Enterpv_select.selectByVisibleText(IPPV_value.get(i).get(3));
							}
						
							
				}
						return true;
					} catch (Exception e) {
						System.out.println("Error in invokebs I/P mapping settings" + e);
						return false;
					}
	}
	
	public boolean invokebs_op_outputmapping_settings(ArrayList<ArrayList<String>> OPparameter) {
		// invokebs o/p mapping setting//
					try {
						OP_Mapping.click();

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ADDButton)));
						
						//System.out.println("op size"+OPparameter.size());
						
						for(int i=0; i < OPparameter.size(); i++)
						{
							ADD_Button.click();
							//wait.until(ExpectedConditions.visibilityOf(PV_Tablerow_Visible.get(i)));
							
							for(int td=0; td < OPparameter.get(i).size(); td++)
							{
								switch (td) {
								case 0:
									WebElement opparameter0 = OPparameter_table.get(i).findElements(By.tagName("td")).get(td).findElement(By.tagName("select"));
									Select op = new Select(opparameter0);
									op.selectByVisibleText(OPparameter.get(i).get(td));
									break;
									
								//case 1:
								//	break;
								
								case 2:
									WebElement opparameter2 = OPparameter_table.get(i).findElements(By.tagName("td")).get(td).findElement(By.tagName("select"));
									Select op2 = new Select(opparameter2);
									op2.selectByVisibleText(OPparameter.get(i).get(td));
									break;
									
								case 3:
									if(OPparameter.get(i).get(td-1).equals("Enter Value"))
									{
										if(OPparameter_table.get(i).findElements(By.tagName("td")).get(td-2).getText().equals("Boolean "))
										{
											if(OPparameter.get(i).get(td).toUpperCase().equals("TRUE"))
											{
											WebElement opparameter3 = OPparameter_table.get(i).findElements(By.tagName("td")).get(td).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
											opparameter3.click();
											}
										}
										else
										{
										WebElement opparameter3 = OPparameter_table.get(i).findElements(By.tagName("td")).get(td).findElement(By.tagName("div")).findElement(By.tagName("div")).findElement(By.tagName("input"));
										opparameter3.sendKeys(OPparameter.get(i).get(td));
										}
									}
									else if(OPparameter.get(i).get(td-1).equals("Context Variable"))
									{
										WebElement opparameter3 = OPparameter_table.get(i).findElements(By.tagName("td")).get(td).findElement(By.tagName("select"));
										Select op3 = new Select(opparameter3);
										op3.selectByVisibleText(OPparameter.get(i).get(td));
									}

								default:
									break;
								}
							}
							
						}
						

						/*String op_enter_cv = Integer.toString(op_enterCV1);
						
						ADD_Button.click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PV_Tablerow1_Visible)));
						Select opMapping_PV_Select1 = new Select(opMapping_ProcessVariable1);
						opMapping_PV_Select1.selectByVisibleText(op_processvariable1);
						Select opMapping_SelectValue1 = new Select(opMapping_Select_Value1);
						opMapping_SelectValue1.selectByVisibleText(op_selectCV1);
						opMapping_Enter_Value1.sendKeys(op_enter_cv);

						ADD_Button.click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PV_Tablerow2_Visible)));
						Select opMapping_PV_Select2 = new Select(opMapping_ProcessVariable2);
						opMapping_PV_Select2.selectByVisibleText(op_processvariable2);
						Select opMapping_SelectValue2 = new Select(opMapping_Select_Value2);
						opMapping_SelectValue2.selectByVisibleText(op_selectCV2);
						opMapping_Enter_Value2.sendKeys(op_enterCV2);

						ADD_Button.click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PV_Tablerow3_Visible)));
						Select opMapping_PV_Select3 = new Select(opMapping_ProcessVariable3);
						opMapping_PV_Select3.selectByVisibleText(op_processvariable3);
						Select opMapping_SelectValue3 = new Select(opMapping_Select_Value3);
						opMapping_SelectValue3.selectByVisibleText(op_selectCV3);
						opMapping_Enter_Value3.click();

						ADD_Button.click();
						Actions opmapping_table = new Actions(driver);
						opmapping_table.moveToElement(opMapping_Table);

						JavascriptExecutor opmapping_hover = (JavascriptExecutor) driver;
						opmapping_hover.executeScript("arguments[0].moveTOe", opMapping_ProcessVariable4);

						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PV_Tablerow2_Visible)));
						Select opMapping_PV_Select4 = new Select(opMapping_ProcessVariable4);
						opMapping_PV_Select4.selectByVisibleText(op_processvariable4);
						Select opMapping_SelectValue4 = new Select(opMapping_Select_Value4);
						opMapping_SelectValue4.selectByVisibleText(op_selectcv4);

						
						
						opMapping_Enter_Value4.sendKeys(op_enterCV4);
						Thread.sleep(2000);*/

						return true;
					} catch (Exception e) {
						System.out.println("Error in InvokeBS O/P mapping Setting : "+e);
						return false;
					}

	}
	

	public boolean invokebs_Operator_properties_Setting(String yield_Duration_Input, int concurrent_Task_Input) 
	{
		try {
			// Invokebs properties setting
		
		//		String concurrent_Task =Integer.toString(concurrent_Task_Input);
				invokebs_Properties_Menu.click();
				// invokebs_Properties_PenaltyDur.sendKeys(penalty_Duration_Input);
		//		invokebs_Properties_YieldDur.sendKeys(yield_Duration_Input);
		//		invokebs_Properties_ConcurrentTask.sendKeys(concurrent_Task);
				invokebs_Operator_Save.click();
			
				return true;
		} catch (Exception e) {
			System.out.println("Error in invokebs operator properties setting" + e);
			return false;
		}

	}
	
	public boolean dmExclusive_Operator_Setting(String input_be, String condition_name, String rulebuilderValue1, String rulebuilderValue2,
			String rulebuilderValue3, String rulebuilderValue4, String rulebuilderValue5,
			String pv1,
			String decision_pv1, String decision_pv2, String decision_pv3, String decision_pv4,
			String yeildDuration, int concurrentTask) {
		try {
			wait.until(ExpectedConditions.visibilityOf(DMExclusive_bs_ipBE));
			
			//Bussiness setting ip BE selection
			Select DME_ipBE = new Select(DMExclusive_bs_ipBE);
			DME_ipBE.selectByVisibleText(input_be);
			
			DMExclusive_bs_condition_addbutton.click();
			
			//Bussiness setting condition setup
			DMExclusive_bs_condition_conditionName.sendKeys(condition_name);
			
			DMExclusive_bs_condition_conditionexpression.click();
			wait.until(ExpectedConditions.visibilityOf(DMExclusive_bs_condition_rulebuilder_text));
			
			Select ruleBuilder_value1 = new Select(DMExclusive_bs_condition_rulebuilder_value1);
			ruleBuilder_value1.selectByVisibleText(rulebuilderValue1);
			
			Select ruleBuilder_value2 =  new Select(DMExclusive_bs_condition_rulebuilder_value2);
			ruleBuilder_value2.selectByVisibleText(rulebuilderValue2);
			
			Select ruleBuilder_value3 =new Select(DMExclusive_bs_condition_rulebuilder_value3);
			ruleBuilder_value3.selectByVisibleText(rulebuilderValue3);
			
			Select ruleBuilder_value4 =new Select(DMExclusive_bs_condition_rulebuilder_value4);
			ruleBuilder_value4.selectByVisibleText(rulebuilderValue4);
			
			Select ruleBuilder_value5 =new Select(DMExclusive_bs_condition_rulebuilder_value5);
			ruleBuilder_value5.selectByVisibleText(rulebuilderValue5);
			
			DMExclusive_bs_condition_rulebuilder_exClose2.click();
			DMExclusive_bs_condition_rulebuilder_exClose2.click();
			DMExclusive_bs_condition_rulebuilder_exClose2.click();
			
			DMExclusive_bs_condition_rulebuilder_saveButton.click();
			
			
			//Process Variable setup
			DMExclusive_ProcessVariable_click.click();
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			
			Select defaultPage_pv1 = new Select(DMExclusive_ProcessVariable_defaultpage_pv1Select);
			defaultPage_pv1.selectByVisibleText(pv1);
			
			//Move to page2 Decision page
			DMExclusive_ProcessVariable_movetoDesisionPage.click();
			
			//Add all 4 process variable for decision condition
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV1 = new Select(DMExclusive_ProcessVariable_decisionpage_pv1);
			decisionPV1.selectByVisibleText(decision_pv1);
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV2 = new Select(DMExclusive_ProcessVariable_decisionpage_pv2);
			decisionPV2.selectByVisibleText(decision_pv2);
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV3 = new Select(DMExclusive_ProcessVariable_decisionpage_pv3);
			decisionPV3.selectByVisibleText(decision_pv3);
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV4 = new Select(DMExclusive_ProcessVariable_decisionpage_pv4);
			decisionPV4.selectByVisibleText(decision_pv4);
			
			String concurrent_task = Integer.toString(concurrentTask);
			//DME properties setup
			DMExclusive_Properties_click.click();
			invokebs_Properties_YieldDur.sendKeys(yeildDuration);
			invokebs_Properties_ConcurrentTask.sendKeys(concurrent_task);
			
			DMExclusive_Properties_save.click();
			
			return true;
		} catch (Exception e) {
			System.out.println("Error in DM exclusive operator settings : "+ e);
			return false;
		}

	}
	
	public boolean joinOperator_settings(String ip_be, String yieldDuration, int concurrentTask) {
		try {
			//wait.until(ExpectedConditions.visibilityOf(join_header));
			
			//select join operator input BE
			wait.until(ExpectedConditions.visibilityOf(join_inputBE_select));
			Select join_inputBE = new Select(join_inputBE_select);
			join_inputBE.selectByVisibleText(ip_be);
			
			Join_Properties_click.click();
			
			invokebs_Properties_YieldDur.sendKeys(yieldDuration);
			String concurrent_Task = Integer.toString(concurrentTask);
			invokebs_Properties_ConcurrentTask.sendKeys(concurrent_Task);
			
			
			join_opsettigs_save.click();
			
			return true;
			
		} catch (Exception e) {
			System.out.println("Error in Join operator settings : "+ e);
			return false;
		}
	}
	
	
	public boolean dmIclusive_Operator_Setting(String input_be, String condition_name, String rulebuilderValue1, String rulebuilderValue2,
			String rulebuilderValue3, String rulebuilderValue4, String rulebuilderValue5,
			String pv1,
			String decision_pv1, String decision_pv2, String decision_pv3, String decision_pv4,
			String yeildDuration, int concurrentTask) {
		try {
			wait.until(ExpectedConditions.visibilityOf(DMExclusive_bs_ipBE));
			
			//Bussiness setting ip BE selection
			Select DME_ipBE = new Select(DMExclusive_bs_ipBE);
			DME_ipBE.selectByVisibleText(input_be);
			
			DMExclusive_bs_condition_addbutton.click();
			
			//Bussiness setting condition setup
			DMExclusive_bs_condition_conditionName.sendKeys(condition_name);
			
			DMExclusive_bs_condition_conditionexpression.click();
			wait.until(ExpectedConditions.visibilityOf(DMExclusive_bs_condition_rulebuilder_text));
			
			Select ruleBuilder_value1 = new Select(DMExclusive_bs_condition_rulebuilder_value1);
			ruleBuilder_value1.selectByVisibleText(rulebuilderValue1);
			
			Select ruleBuilder_value2 =  new Select(DMExclusive_bs_condition_rulebuilder_value2);
			ruleBuilder_value2.selectByVisibleText(rulebuilderValue2);
			
			Select ruleBuilder_value3 =new Select(DMExclusive_bs_condition_rulebuilder_value3);
			ruleBuilder_value3.selectByVisibleText(rulebuilderValue3);
			
			Select ruleBuilder_value4 =new Select(DMExclusive_bs_condition_rulebuilder_value4);
			ruleBuilder_value4.selectByVisibleText(rulebuilderValue4);
			
			Select ruleBuilder_value5 =new Select(DMExclusive_bs_condition_rulebuilder_value5);
			ruleBuilder_value5.selectByVisibleText(rulebuilderValue5);
			
			DMExclusive_bs_condition_rulebuilder_exClose2.click();
			DMExclusive_bs_condition_rulebuilder_exClose2.click();
			DMExclusive_bs_condition_rulebuilder_exClose2.click();
			
			DMExclusive_bs_condition_rulebuilder_saveButton.click();
			
			
			//Process Variable setup
			DMExclusive_ProcessVariable_click.click();
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			
			Select defaultPage_pv1 = new Select(DMExclusive_ProcessVariable_defaultpage_pv1Select);
			defaultPage_pv1.selectByVisibleText(pv1);
			
			//Move to page2 Decision page
			DMExclusive_ProcessVariable_movetoDesisionPage.click();
			
			//Add all 4 process variable for decision condition
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV1 = new Select(DMExclusive_ProcessVariable_decisionpage_pv1);
			decisionPV1.selectByVisibleText(decision_pv1);
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV2 = new Select(DMExclusive_ProcessVariable_decisionpage_pv2);
			decisionPV2.selectByVisibleText(decision_pv2);
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV3 = new Select(DMExclusive_ProcessVariable_decisionpage_pv3);
			decisionPV3.selectByVisibleText(decision_pv3);
			
			DMExclusive_ProcessVariable_defaultpage_addbutton.click();
			Select decisionPV4 = new Select(DMExclusive_ProcessVariable_decisionpage_pv4);
			decisionPV4.selectByVisibleText(decision_pv4);
			
			String concurrent_task = Integer.toString(concurrentTask);
			//DME properties setup
			DMExclusive_Properties_click.click();
			invokebs_Properties_YieldDur.sendKeys(yeildDuration);
			invokebs_Properties_ConcurrentTask.sendKeys(concurrent_task);
			
			DMExclusive_Properties_save.click();
			
			return true;
		} catch (Exception e) {
			System.out.println("Error in DM exclusive operator settings : "+ e);
			return false;
		}

	}
	
	
	public boolean smartConnector_setting(ArrayList<Object> updateSource,
			ArrayList<String> enterPV,
			String yieldduration, int concurrenttask) {
		try {
			

			
			//ArrayList<Object> updateSource_value = new ArrayList<Object>();
			//updateSource_value.addAll(updateSource);
			
			//ArrayList<Object> enterPV_value =  new ArrayList<>();
			//enterPV_value.add(enterPV1);
			//enterPV_value.add(enterPV2);
			//enterPV_value.add(enterPV3);
			//enterPV_value.add(enterPV4);
			//enterPV_value.add(enterPV5);
			//enterPV_value.add(enterPV6);
			
			
			wait.until(ExpectedConditions.visibilityOf(DMExclusive_ProcessVariable_click));
			//move to mapping for seetings reused locator instead of mapping
			DMExclusive_ProcessVariable_click.click();
			
			for(int i=0; i < smart_connector_mapping.size(); i++)
			{
				//smart connector mapping , update source selection
				WebElement table_td3 = smart_connector_mapping.get(i).findElements(By.tagName("td")).get(2).findElement(By.tagName("select"));
				Select update_source = new Select(table_td3);
				update_source.selectByVisibleText(updateSource.get(i).toString());
				
				if(updateSource.get(i).equals("Enter Value"))
				{
					WebElement table_td4_entervalue = smart_connector_mapping.get(i).findElements(By.tagName("td")).get(3).findElement(By.tagName("input"));	
					System.out.println(enterPV.get(i)+"ans");
					table_td4_entervalue.sendKeys( enterPV.get(i));
				}
				else if (updateSource.get(i).equals("Process Variable"))
				{
				//smart connector mapping, Enter value/PV
				WebElement table_td4 = smart_connector_mapping.get(i).findElements(By.tagName("td")).get(3).findElement(By.tagName("select"));
				Select enter_PV = new Select(table_td4);
				enter_PV.selectByVisibleText(enterPV.get(i));
				}			
			}
			
			
			//Smart connector properties setting (locators are reused
			
			DMExclusive_Properties_click.click();
			
			String concurrent_task = Integer.toString(concurrenttask);
			
			invokebs_Properties_YieldDur.sendKeys(yieldduration);
			invokebs_Properties_ConcurrentTask.sendKeys(concurrent_task);
			
			//save the smart connector settings(locators are reused)
			DMExclusive_Properties_save.click();
			
			return true;
		} catch (Exception e) {
			System.out.println("Error in smart connector settings : "+e);
			return false;
		}

	}
	

	public boolean endOperator_Setting(String beType, String yield_Dur, int concurrenttask) {
		try {

			// WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(endOperator_BE_Select));

			Select endoperator_BE_Select = new Select(endOperator_BE_Select);
			endoperator_BE_Select.selectByVisibleText(beType);

			String concurrent_task = Integer.toString(concurrenttask);
			endOperator_Properties.click();
			// penalty_Duration.sendKeys(penalty_Dur);
			yield_Duration.sendKeys(yield_Dur);
			 concurrent_Task.sendKeys(concurrent_task);

			end_Operator_SaveButton.click();
			
			
			return true;

		} catch (Exception e) {
			System.out.println("Error in end operator setting");
			return false;
		}

	}

	public String BPflow_Save() throws AWTException 
	{
		try {
			String saved_msg = null;
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Menu_Flow1)));

			Actions menuFlow_Mousehover = new Actions(driver);

			menuFlow_Mousehover.moveToElement(Menu_Flow).build().perform();

			// Menu_Flow.click();
			menuFlow_Mousehover.moveToElement(MenuFlow_Save).click().build().perform();

			 wait.until(ExpectedConditions.visibilityOf(Saved_Successmsg_show));
			 
			if (Saved_Successmsg_show.getAttribute("class").equals("show")) {
				saved_msg = Saved_Successmsg.getText();
			}
			else {
				saved_msg = null;
			}

			return saved_msg;

			
		} catch (Exception e) {
			System.out.println("Error in flow save : "+e);
			return null;
		}
		
	}
	

	
	public int autoitEXE_Execution(String command) throws IOException {
		int joinOperator_Value = -1;
		try {
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			joinOperator_Value = process.exitValue();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return joinOperator_Value;
	}
	
	


	public boolean palette_Availablity() {
		try {
			wait.until(ExpectedConditions.visibilityOf(waitforcanvas));
			return design_Palette.isDisplayed();
		} catch (Exception e) {
			System.out.println("Error in pallet availability :"+ e);
			return false;
		}

		
	}

	public String bpdesignValidation() {
		String validation_error_msg = null;
		try {
		
		Actions menuFlow_Mousehover = new Actions(driver);

		menuFlow_Mousehover.moveToElement(Menu_Flow).build().perform();
		
		menuFlow_Mousehover.moveToElement(bpflowValidateion).click().perform();
		

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(bpflowValidationpopupbox)));

		if (validation_errorlessmessage.getAttribute("class").equals("ng-star-inserted"))
		{
			validation_error_msg = "Error not Found";
		} 
		else if (validation_errorlessmessage.getAttribute("class").equals("table-responsive scroll-table ng-star-inserted"))
		{
			validation_error_msg = "BP Flow Process validation is failed and check the UI for valied error message";
		}

		
		menuFlow_Mousehover.moveToElement(validation_popupClose).click().build().perform();
		return validation_error_msg;

		 } 
		catch (Exception e) {
		 System.out.println("error in bp designer page BP flow validation"+ e);
		 return validation_error_msg;
		 }
	 

	}

}
