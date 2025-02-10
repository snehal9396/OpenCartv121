package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException 
	{
		String path = ".\\testData\\Opencart_LoginData.xlsx";      // getting xl file from path 
		ExcelUtility xutil = new ExcelUtility(path);
		
		int totalrows = xutil.getRowCount("Sheet1");
		int totalcolumns = xutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcolumns];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcolumns;j++) 
			{
				logindata[i-1][j] = xutil.getCellData("Sheet1", i, j);   //first 1 value storing in 0th location in array as excel starts from 0 rows is a header
			}
		}
		
		return logindata;     // return data entered in the sheet 
	}
	

	//dp2
	
	//dp3
	
}
