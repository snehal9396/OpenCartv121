package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseTestClass 
{
	
	public  WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Regression","Master","Sanity"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//loading properties file
		FileReader fr = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(fr);
		
		logger = LogManager.getLogger(this.getClass());
		
		//remote machine execution
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("Windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("MAC"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			//cap.setPlatform(Platform.WIN11);
			//cap.setBrowserName(br)
			
			//brs
			switch(br.toLowerCase())
			{
				case  "chrome": capabilities.setBrowserName("chrome");break;
				case  "edge": 	capabilities.setBrowserName("MicrosoftEdge");break;
				case  "firefox": capabilities.setBrowserName("firefox");break;
				default: System.out.println("Invalid Browser");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		
		//local execution
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase())
			{
				case "chrome": driver= new ChromeDriver();break;
				case "edge": driver= new EdgeDriver();break;
				case "firefox": driver= new FirefoxDriver();break;
				default: System.out.println("Invalid Browser");return;
			}
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appurl2"));  // reading url from config file 
		driver.manage().window().maximize();
		
	}
	
	
	
	//passing run time data to account registration
	public  String randomString()
	{
		String generatedString=RandomStringUtils.secure().nextAlphabetic(5);
		return generatedString;
	}
	
	
	public String randomNumber()
	{
		String generatedString=RandomStringUtils.secure().nextNumeric(10);
		return generatedString;
	}
	
	public  String randomAlphaNumeric()
	{
		//String generatedString=RandomStringUtils.secure().nextAlphanumeric(10);
		//return generatedString;
	
		String str = RandomStringUtils.secure().nextAlphabetic(3);
		String num = RandomStringUtils.secure().nextNumeric(5);
		
		return str + "@" + num;
	
	}
	
	
	//capture screenshot on failure of test case
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
	
	
	//close b/s
		@AfterClass(groups= {"Regression","Master","Sanity"})
		public void tearDown()
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.quit();
		}
}
