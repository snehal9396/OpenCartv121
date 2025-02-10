package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTestClass;


public class TC001_AccountRegistrationTest extends BaseTestClass {
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registeration()
	{
		try {
		
		logger.info("**************Start of TC001_AccountRegistrationTest******");
		//logger.debug("This is a debug log message");
		
		//we need to access these methods from another class
		HomePage hp = new HomePage(driver);
		
		hp.clickMyaccount();
		logger.info("Clicked on MyAccount Link.. ");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link.. ");
		
		logger.info("**************Beginning of Account Registration******");
		//we need to access these methods from another class 
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		
		logger.info("Entering Customer Details");
		arp.setfirstname(randomString().toUpperCase());
		arp.setlastname(randomString().toUpperCase());
		arp.setemail(randomString()+ "@email.com");
		arp.setphone(randomNumber());
		
		String password=randomAlphaNumeric();
		arp.setpassword(password);
		arp.setconfirmpassword(password);
		
		arp.clickpolicy();
		arp.clickcontine();
		
		logger.info("Validating expected message..");
		
		String confrmmsg=arp.getconfirmmessage();
		if(confrmmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			
			logger.error("Test Failed");;
			logger.debug("Debug Logs");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e)
			{
				Assert.fail();
			}
		logger.info("**************End of TC001_AccountRegistrationTest******");
	}
	

	
	

}
