package xbmc.codegenAPItest;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import xbmc.configProperties.ConfigProperties;

public class XBMC_AssetAPIinput {
	
	private static Logger logger = Logger.getLogger(XBMC_AssetAPIinput.class);
	ConfigProperties configfile;
	
	public int postAPI(int noofinputs,String filepath,String headervalue1, String headervalue2, String headervalue3, String headervalue4) 
	{
		logger.info("-- xbmc_pass_inputdata_through_assetAPI --");
		configfile = new ConfigProperties();
		int statuscode=0;
		HttpClient http = HttpClientBuilder.create().build();
		try 
		{
			
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(filepath)));
		for(int i=1; i<=noofinputs; ++i)
		{
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost responce = new HttpPost(configfile.getInputBEAPI());
			//StringEntity entity = new StringEntity("[{\"pkId\": \"123456\",\"professorName\": \"Kannan\",\"qualification\": \"PGDM.I.T.\",\"smeArea\": \"PM\",\"enggDiscipline\": \"B.E.s\",\"salary\": \"55000.12\",\"employmentDate\": \"2005-11-12T00:00:00.000+0000\",\"empGrade\": \"First\",\"honourDate\": \"2019-01-01T12:20:45.786+0530\"}]");
			StringEntity entity = new StringEntity(data);
			responce.setEntity(entity);
			responce.setHeader("Content-Type", headervalue1);
			responce.setHeader("channelId", headervalue2);
			responce.setHeader("channelName", headervalue3);
			responce.setHeader("beName", headervalue4);
			CloseableHttpResponse res = client.execute(responce);
			statuscode=res.getStatusLine().getStatusCode();
			System.out.println("INput"+i+"= Status code:"+statuscode);
			client.close();
		}
		logger.info("Inputs api triggered "+noofinputs+"time's.");
		} 
		catch (Exception e) 
		{
			logger.info("Error in API input post call :"+ e);
			statuscode = 0;
			System.out.println("Error in API input post call :"+ e);
		}
		return statuscode;

	}
	

}
