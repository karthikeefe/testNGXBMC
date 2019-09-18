package xbmc.codegenAPItest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import xbmc.configProperties.ConfigProperties;

public class XBMC_Codegenapi {
	ConfigProperties configfile;
	String codegenurl = null;
	
	
	public int xbmc_getCodegenAPI() throws InterruptedException {
		int statusCode = 0;
		try {
			configfile = new ConfigProperties();
			codegenurl = configfile.getCodegenAPI();
			System.out.println("Codegen url : " + codegenurl+configfile.getXBMCAsset_AssetName());
			HttpUriRequest reqest = new HttpGet(codegenurl+configfile.getXBMCAsset_AssetName());
			HttpResponse response = HttpClientBuilder.create().build().execute(reqest);
			String jsonstring = EntityUtils.toString(response.getEntity());
			System.out.println(jsonstring);
			statusCode = response.getStatusLine().getStatusCode();
			Thread.sleep(5000);
			
		} catch (IOException e) {
			System.out.println("Error to trigger the code gen api with given input parameters"+ e);
			
		}
		return statusCode;	
		}
	}


