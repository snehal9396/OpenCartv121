package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends Basepage{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	 
	//web elements 
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelphone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtPasswordConfirm;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgconfirmation;
	
	//actions on web elements 
	
	public void setfirstname(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setlastname(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setemail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setphone(String telph)
	{
		txtTelphone.sendKeys(telph);
	}
	
	public void setpassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void setconfirmpassword(String conpasswd)
	{
		txtPasswordConfirm.sendKeys(conpasswd);
	}
	
	public void clickpolicy( )
	{
		checkPolicy.click();
	}
	
	public void clickcontine()
	{
		btnContinue.click();
	}
	
	public String getconfirmmessage()
	{
		try
		{
		return (msgconfirmation.getText());
		} catch (Exception e) {
		return (e.getMessage());
		}
	}
}
