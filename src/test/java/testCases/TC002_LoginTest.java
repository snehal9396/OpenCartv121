package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;

public class TC002_LoginTest extends BaseTestClass 
{

	@Test(groups= {"Sanity","Master"})
	public void verify_login ()
	{
		try 
		{
		logger.info("********************Start of TC002_LoginTest***************");
		
		// we need to login and for that we need to go to home page
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		
		logger.info("********************Enter customer details to login ***************");
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setemail(p.getProperty("userid"));
		lp.setpassword(p.getProperty("password"));
		lp.clicklogin();
		
		logger.info("********************Logging into the account ***************");
		//MyAccountpage
		MyAccountPage maacc = new MyAccountPage(driver);
		boolean var=maacc.MyAccountExists();
		
		Assert.assertEquals(var, true,"Login Failed");
		logger.info("********************Finished of TC002_LoginTest***************");
		maacc.logout();
		
		}catch(Exception e )
			{
				Assert.fail();
			}
		
		
	}
}