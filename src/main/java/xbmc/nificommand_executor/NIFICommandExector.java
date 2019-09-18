package xbmc.nificommand_executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.testng.Assert;

import xbmc.configProperties.ConfigProperties;

public class NIFICommandExector {
	ConfigProperties configFile;

	public int NIFI_Scriptexecution() throws IOException {
		configFile = new ConfigProperties();
		String plinkCommand = configFile.getplinkCommand();
		String bpName = configFile.getXBMCAsset_AssetName();
		int processexit_Value = -1;

		String command = plinkCommand + " " + bpName;
		// String command =
		// "C:\\\\Users\\\\karthikeyans\\\\Java-code\\\\xelerate\\\\plink\\\\nk.exepli
		// -ssh hcluser@sbstjvmlx816.suntecsbs.com -pw suntec123 cd
		// /home/hcluser;./autotest.sh KarthikAsset1";
		try {

			Process process = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}
			process.waitFor();
			processexit_Value = process.exitValue();
			// Assert.assertEquals(operatorconnector_Value, 0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return processexit_Value;

	}

}
