package xbmc.configProperties;

import static org.testng.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import okhttp3.Call;

public class ExcelinputData {

	static ConfigProperties config;

	static String excel_filepath;

	private static HSSFSheet ExcelWSheet;
	private static HSSFWorkbook ExcelWBook;
	private static HSSFCell Cell;
	private static HSSFRow Row;

	public ExcelinputData() {
		config = new ConfigProperties();
		excel_filepath = config.getInputexcel_filepath();

	}

	// This method is to set the File path and to open the Excel file, Pass Excel
	// Path and Sheetname as Arguments to this method

	public void setExcelFile(String SheetName) throws Exception {

		try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(excel_filepath);

			// Access the required test data sheet

			ExcelWBook = new HSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			System.out.println(ExcelWSheet.getSheetName());

		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public ArrayList<String> getStartoperator_input_be()  {

		try {
			ArrayList<String> Celldata = new ArrayList<>();
			for(int i=2; i<ExcelWSheet.getLastRowNum(); i++)
			{
			Cell = ExcelWSheet.getRow(i).getCell(1);
			if (Cell != null) {
				
			String CellData = Cell.getStringCellValue();
			Celldata.add(CellData);
			
			}
			}
			return Celldata;
		} catch (Exception e) {
			System.out.println("Error in excel, Startoperator_InputBE"+e);
			return null;
		}
		
	}
			
			

	public ArrayList<String> getStartoperator_properties() throws Exception {

		try {
			ArrayList<String> CellArray = new ArrayList<>();
			System.out.println(ExcelWSheet.getLastRowNum());
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				Cell = ExcelWSheet.getRow(i).getCell(3);
				if (Cell != null)
				{
					if(Cell.getCellType().toString().equals("NUMERIC"))
					{
						Cell.setCellType(CellType.STRING);
						String CellData =  Cell.getStringCellValue();
						CellArray.add(CellData);
					}
					else if (Cell.getCellType().toString().equals("STRING"))
					{
						String CellData1 = Cell.getStringCellValue();
						CellArray.add(CellData1);
					}	
				}
			}
			
			return CellArray;
		} catch (Exception e) {
			System.out.println("Error in excel, Startoperator_yield_duration"+e);
			return null;

		}
	}

	/*public String getStartoperator_concurrent_task() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(3);
	Cell.setCellType(CellType.STRING);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in excel, Startoperator_concurrent_task"+e);
			return "";
		}
	}*/
	
	public String getInvokeBSoperator_BussinessService() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(0);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_BussinessService"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_BussinessService_api() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(1);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_BussinessService_api"+e);
			return "";
		}
	}
	
	public ArrayList<ArrayList<String>> getInvokeBSoperator_ipmapping() {
		try {
			ArrayList<ArrayList<String>> selectPV = new ArrayList<>();
			System.out.println(ExcelWSheet.getLastRowNum());
			
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				ArrayList<String> cellvalue = new ArrayList<>();
				for(int j=3; j <= 6; j++)
				{
				Cell = ExcelWSheet.getRow(i).getCell(j);
				if (Cell != null)
				{
					
						Cell.setCellType(CellType.STRING);
						String CellData =  Cell.getStringCellValue();
						cellvalue.add(CellData);
					System.out.println(CellData);
				}
				}
				selectPV.add(cellvalue);
			}
			
			return selectPV;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_ipmapping select PV : "+e);
			return null;
		}
	}
	
	
	/*public ArrayList<String> getInvokeBSoperator_ipmapping_enterPV() {
		try {
			ArrayList<String> enterPV = new ArrayList<>();
			System.out.println(ExcelWSheet.getLastRowNum());
			
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				Cell = ExcelWSheet.getRow(i).getCell(6);
				if (Cell != null)
				{
					if(Cell.getCellType().toString().equals("NUMERIC"))
					{
						Cell.setCellType(CellType.STRING);
						String CellData =  Cell.getStringCellValue();
						enterPV.add(CellData);
					}
					else if (Cell.getCellType().toString().equals("STRING"))
					{
						String CellData1 = Cell.getStringCellValue();
						enterPV.add(CellData1);
					}	
				}
			}
			
			return enterPV;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_ipmapping enter PV : "+e);
			return null;
		}

	}*/
	
	/*public String getInvokeBSoperator_ipmapping_name1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(2);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_BussinessService_api"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_name2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(2);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_ipmapping_name2"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_name3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(2);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_ipmapping_name3"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_selectPV1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in excel, InvokeBSoperator_ipmapping_selectPV1"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_selectPV2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_ipmapping_selectPV2"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_selectPV3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_ipmapping_selectPV3"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_enterPV1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_ipmapping_enterPV1"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_enterPV2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_ipmapping_enterPV2"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_ipmapping_enterPV3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_ipmapping_enterPV3"+e);
			return "";
		}
	}*/
	
	public ArrayList<ArrayList<String>> getInvokeBSoperator_opmapping_paramaters() {
		try {
			ArrayList<ArrayList<String>> OPparamater = new ArrayList<>();
		//	System.out.println("opM Size"+ExcelWSheet.getLastRowNum());
			
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				ArrayList<String> cellValue = new ArrayList<>();
				for(int j=8; j <= 11; j++)
				{
					Cell = ExcelWSheet.getRow(i).getCell(j);
					if(Cell != null)
					{
						Cell.setCellType(CellType.STRING);
						String CellData = Cell.getStringCellValue();
						cellValue.add(CellData);
						//System.out.println("OPMapping: "+CellData);
					}
					
				}
				OPparamater.add(cellValue);		
			}
			
			return OPparamater;
			
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBS output mapping parameter : "+e);
			return null;
		}

	}
	
	
	/*public String getInvokeBSoperator_opmapping_processvariable1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(8);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_processvariable1"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_processvariable2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(8);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, vokeBSoperator_opmapping_processvariable2"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_processvariable3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(8);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_processvariable3"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_processvariable4() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(6).getCell(8);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_processvariable3"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_selectCV1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(10);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_processvariable3"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_selectCV2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(10);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_selectCV2"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_selectCV3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(10);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_selectCV3"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_selectCV4() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(6).getCell(10);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_selectCV4"+e);
			return "";
		}
	}
	
	public int getInvokeBSoperator_opmapping_enterCV1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(11);
			int CellData = (int) (Cell.getNumericCellValue());
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_enterCV1"+e);
			return 0;
		}
	}
	
	public String getInvokeBSoperator_opmapping_enterCV2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(11);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_enterCV2"+e);
			return "";
		}
	}
	
	public String getInvokeBSoperator_opmapping_enterCV4() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(6).getCell(11);
			String CellData =  Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_opmapping_enterCV4"+e);
			return "";
		}
		
	}*/
	
	public String getInvokeBSoperator_properties_yieldduration() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(14);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_properties_yieldduration"+e);
			return "";
		}
	}
	
	public int getInvokeBSoperator_properties_concurrenttask() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(14);
			System.out.println("::::::::::::::::::"+Cell.getCellType().toString());
			int CellData = (int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, InvokeBSoperator_properties_concurrenttask"+e);
			return 0;
		}
	}
	
	public String getDMExclusiveoperator_Bussinessetting_ipBE() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(0);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_ipBE"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_Bussinessetting_conditionName() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(6).getCell(0);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_conditionName"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_Bussinessetting_rulebuilder_Value1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(7).getCell(1);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_rulebuilder_Value1"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_Bussinessetting_rulebuilder_Value2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(7).getCell(2);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_rulebuilder_Value2"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_Bussinessetting_rulebuilder_Value3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(7).getCell(4);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_rulebuilder_Value3"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_Bussinessetting_rulebuilder_Value4() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(8).getCell(1);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_rulebuilder_Value4"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_Bussinessetting_rulebuilder_Value5() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(8).getCell(2);
			String CellData = Cell.getStringCellValue();
			System.out.println(CellData);
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_rulebuilder_Value5"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_processVariable_default_pv1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(6);
			String CellData = Cell.getStringCellValue();
			System.out.println(CellData);
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_Bussinessetting_rulebuilder_Value5"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_processVariable_decision_pv1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(8).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_processVariable_decision_pv1"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_processVariable_decision_pv2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(9).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_processVariable_decision_pv2"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_processVariable_decision_pv3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(10).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_processVariable_decision_pv3"+e);
			return "";
		}
	}
	
	public String getDMExclusiveoperator_processVariable_decision_pv4() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(11).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_processVariable_decision_pv4"+e);
			return "";
		}
	}
	
	
	public String getDMExclusiveoperator_properties_yieldduration() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(12);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_properties_yieldduration"+e);
			return "";
		}
	}
	
	public int getDMExclusiveoperator_properties_concurrentTask() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(12);
			int CellData =(int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_properties_concurrentTask"+e);
			return 0;
		}
	}
	
	public String getJoinoperator_bussinesssetting_ipBE() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(2).getCell(1);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Joinoperator_bussinesssetting_ipBE"+e);
			return "";
		}
	}
	
	public String getJoin_properties_yieldduration() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(3);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_properties_yieldduration"+e);
			return "";
		}
	}
	
	public int getJoin_properties_concurrentTask() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(3);
			int CellData =(int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, DMExclusiveoperator_properties_concurrentTask"+e);
			return 0;
		}
	}

	public String getEnd1operator_bussinesssetting_ipBE() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(2).getCell(1);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, End1operator_bussinesssetting_ipBE"+e);
			return "";
		}
	}
	
	public String getEnd1operator_properties_yieldduration() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(3);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, End1operator_bussinesssetting_ipBE"+e);
			return "";
		}
	}
	
	public int getEnd1operator_properties_concurrenttask() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(3);
			int CellData =(int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, End1operator_bussinesssetting_ipBE"+e);
			return 0;
		}
	}

	public String getEnd2operator_bussinesssetting_ipBE() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(2).getCell(1);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, End2operator_bussinesssetting_ipBE"+e);
			return "";
		}
	}
	
	public String getEnd2operator_properties_yieldduration() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(3);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, End2operator_bussinesssetting_ipBE"+e);
			return "";
		}
	}
	
	public int getEnd2operator_properties_concurrenttask() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(3);
			int CellData =(int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, End2operator_bussinesssetting_ipBE"+e);
			return 0;
		}
	}
	
	
	public ArrayList<Object> getSmartconnector_mappings_updatesource() {
		try {
			ArrayList<Object> Smartconnector_mappings_updatesource = new ArrayList<Object>();
			
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				Cell = ExcelWSheet.getRow(i).getCell(5);
				if( Cell != null )
				{	
                		System.out.println("i'm in");
						Cell.setCellType(CellType.STRING);
						String CellData =  Cell.getStringCellValue();
						Smartconnector_mappings_updatesource.add(CellData);	

				}
			}
			return Smartconnector_mappings_updatesource;
			
		} catch (Exception e) {
			System.out.println("Error in getsmartconnector mapping : "+e);
			return null;
		}	
	}
	
	///Return below function values as arraylist so functions are commanded
	/*public String getSmartconnector_mappings_updatesource1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_updatesource4"+e);
			return "";
		}
	}
	
	public String getSmartconnector_mappings_updatesource2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_updatesource4"+e);
			return "";
		}
	}
	
	public String getSmartconnector_mappings_updatesource3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_updatesource4"+e);
			return "";
		}
	}
	
	public String getSmartconnector_mappings_updatesource4() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(6).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_updatesource4"+e);
			return "";
		}
	}
	
	public String getSmartconnector_mappings_updatesource5() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(7).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_updatesource5"+e);
			return "";
		}
	}
	
	public String getSmartconnector_mappings_updatesource6() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(8).getCell(5);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_updatesource6"+e);
			return "";
		}
	}*/
	
	public ArrayList<String> getSmartconnector_mappings_enterPV() {
		try {
			ArrayList<String> mapping_EnterPV =new  ArrayList<String>();
			System.out.println(ExcelWSheet.getLastRowNum());
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				Cell = ExcelWSheet.getRow(i).getCell(6);
				
				if(Cell != null)
				{
					
						System.out.println("i'm in");
						Cell.setCellType(CellType.STRING);
						String CellData =  Cell.getStringCellValue();
						System.out.println(CellData);
						mapping_EnterPV.add(CellData);	
				}
			}
			return mapping_EnterPV;
			
		} catch (Exception e) {
			System.out.println("Error in Smartconnector_mappings_enterPV : "+e);
			return null;
		}

	}
	
	
	public String getSmartconnector_mappings_enterPV1() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(3).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_enterPV1"+e);
			return "";
		}
	}
	
	public String getSmartconnector_mappings_enterPV2() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_enterPV2"+e);
			return "";
		}
	}
	
	public String getSmartconnector_mappings_enterPV3() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_enterPV3"+e);
			return "";
		}
	}
	
	
	
	public int getSmartconnector_mappings_enterPV4() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(6).getCell(6);
			int CellData = (int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_enterPV4"+e);
			return 0;
		}
	}
	
	public int getSmartconnector_mappings_enterPV5() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(7).getCell(6);
			int CellData = (int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_enterPV5"+e);
			return 0;
		}
	}
	
	public String getSmartconnector_mappings_enterPV6() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(8).getCell(6);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_mapping_enterPV6"+e);
			return "";
		}
	}
	
	public String getSmartconnector_properties_yieldduration() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(4).getCell(9);
			String CellData = Cell.getStringCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel,Smartconnector_properties_yieldduration"+e);
			return "";
		}
	}
	
	public int getSmartconnector_properties_concurrenttask() throws Exception {

		try {
			Cell = ExcelWSheet.getRow(5).getCell(9);
			int CellData =(int) Cell.getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			System.out.println("Error in Excel, Smartconnector_properties_concurrenttask"+e);
			return 0;
		}
	}
	
	public ArrayList<ArrayList<String>> getConfigurationBP_wrong_Processvariable() {
		try {
			ArrayList<ArrayList<String>> proVar = new ArrayList<>();
			
			
			
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				ArrayList<String> cellValue = new ArrayList<>();
				for(int j=0; j <= 6; j++)
				{
					Cell = ExcelWSheet.getRow(i).getCell(j);
					if(Cell != null)
					{
						Cell.setCellType(CellType.STRING);
						String CellData = Cell.getStringCellValue();
						cellValue.add(CellData);
					}
					
				}
				proVar.add(cellValue);	
				
			}
			return proVar;
		} catch (Exception e) {
			System.out.println("Error in Excel, Configration BP processvariable "+e);
			return null;
		}

	}
	
	
	public ArrayList<ArrayList<String>> getConfigurationBP_processvariable() {
		try {
			ArrayList<ArrayList<String>> proVar = new ArrayList<>();
			
			
			
			for(int i=3; i <= ExcelWSheet.getLastRowNum(); i++)
			{
				ArrayList<String> cellValue = new ArrayList<>();
				for(int j=0; j <= 6; j++)
				{
					Cell = ExcelWSheet.getRow(i).getCell(j);
					if(Cell != null)
					{
						Cell.setCellType(CellType.STRING);
						String CellData = Cell.getStringCellValue();
						cellValue.add(CellData);
						//System.out.println("ecl : "+cellValue.get(j));
					}
					
				}
				proVar.add(cellValue);	
				
			}
			return proVar;
		} catch (Exception e) {
			System.out.println("Error in Excel, Configration BP processvariable "+e);
			return null;
		}

	}
	
}
