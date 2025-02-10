package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTestClass;
import utilities.DataProviders;

 
 
 
public class TC003_LoginDDT extends BaseTestClass 
{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= "DataDriven") // getting dp from diff same, if in same class then not required
	public void verify_login(String email, String pwd, String exp) 
	{
	
		logger.info("**************Start of TC003_LoginDDT******");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			hp.clickLogin();
	
	
		logger.info("********************Enter customer details to login ***************");
		//LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setemail(email);
			lp.setpassword(pwd);
			lp.clicklogin();
		
		logger.info("********************Logging into the account ***************");
		//MyAccountpage
			MyAccountPage maacc = new MyAccountPage(driver);
			boolean var=maacc.MyAccountExists();
		
		
		//Data Valid - login succ - test pass - logout 
		//Data Valid - login unsucc - test fail 

		//Data Invalid - login succ - test fail - logout
		//Data Invalid - login unsucc-test pass
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(var==true)
				{
					maacc.logout();
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			else
			{
				if(var==true)
				{
					maacc.logout();
					Assert.assertTrue(false);
				
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e)
			{
				Assert.fail("An exception Occured" +e.getMessage());
			}
			logger.info("**************Finished  TC003_LoginDDT******");
		}

}
