package VoiceAutomationTest;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Trial {

	public WebDriver initDriver()
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "D:/Chrome/chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
	}

	public Order RetrieveOrderDetail(String OrderNo)
	{
		WebDriver driver=initDriver();
		String queryResult=null;
		String submitDate=null;
		String creationDate=null;
		String lastModifiedDate=null;
		String orderState=null;
		String orderOriginSource=null;
		String totalToPay=null;
		String paymentGroups=null;
		String jurisdiction=null;
		String stateDetail=null;


		driver.get(Origin.GET_USER_DYN_URL.getValue());

		String initialOrderQuery="<query-items item-descriptor="+'"'+"order"+'"'+">sapOrderId="+'"'+OrderNo+'"'+"</query-items>";

		Order od=new Order();


		((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX.getValue())), initialOrderQuery);
		driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON.getValue())).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		queryResult = driver.findElement(By.xpath(Origin.DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT.getValue())).getText();
		submitDate = fetchValue(queryResult,"submittedDate");
		creationDate=fetchValue(queryResult,"creationDate");
		lastModifiedDate=fetchValue(queryResult,"lastModifiedDate");
		orderState=fetchValue(queryResult,"state");
		orderState=Origin.valueOf(orderState.toUpperCase()).getValue();
		orderOriginSource=fetchValue(queryResult,"orderOriginSource");
		totalToPay=fetchValue(queryResult,"totalToPay");
		paymentGroups=fetchValue(queryResult,"paymentGroups");
		jurisdiction=fetchValue(queryResult,"jurisdiction");
		stateDetail=fetchValue(queryResult,"stateDetail");



		od.setSubmitDate(submitDate);
		od.setCreationDate(creationDate);
		od.setLastModifiedDate(lastModifiedDate);
		od.setOrderState(orderState);
		od.setJurisdiction(jurisdiction);
		od.setOrderOriginSource(orderOriginSource);
		od.setPaymentGroups(paymentGroups);
		od.setStateDetail(stateDetail);
		od.setTotalToPay(totalToPay);

		driver.quit();

		return od;

	}

	public String fetchValue(String queryResult,String value) {
		String result = null;
		if (!queryResult.contains("No items returned")) {
			if(queryResult.contains(value))
			{
				result = queryResult.split(value + '"' + "><!" + "\\["
						+ "CDATA" + "\\[")[1].split("]")[0].trim();
			}


		}
		return result;

	}

	public Customer setCustomerDetail(String custId,String mobile,String postCode) 
	{
		String status="fail";
		WebDriver driver=initDriver();

		try {



			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.get(Origin.GET_USER_AGENT_URL.getValue());
			if(returnid(driver,Origin.LOGIN_TEXT_FIELD.getValue()).isDisplayed())
			{
				returnid(driver,Origin.LOGIN_TEXT_FIELD.getValue()).sendKeys(Origin.AGENT_USERID.getValue());
				returnid(driver,Origin.PASSWORD_FIELD.getValue()).sendKeys(Origin.AGENT_PASSWORD.getValue());

				returnid(driver,Origin.LOGIN_BUTTON.getValue()).click();
				if(returnid(driver,Origin.CONTACT_CENTER_LOACTION_FIELD.getValue()).isDisplayed())
				{
					returnid(driver,Origin.CONTACT_CENTER_LOACTION_FIELD.getValue()).sendKeys(Origin.CONTACT_CENTER_LOACTION.getValue());
					returnid(driver,Origin.CONTACT_CENTER_CONFIRM_LOCATION.getValue()).click();

					Thread.sleep(2000);

					returnid(driver,Origin.CONTACT_CENTER_LOACTION_CONTINUE.getValue()).click();

					if(returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_FIELD.getValue()).isDisplayed())
					{
						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_FIELD.getValue()).sendKeys(custId);
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].scrollIntoView(true);", returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_LOOKUP.getValue()));
						((JavascriptExecutor) driver).executeScript(
								"arguments[0].click();", returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_LOOKUP.getValue()));

						Thread.sleep(5000);


						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_EDIT.getValue()).click();

						Thread.sleep(3000);


					}
					System.out.println(mobile.length()+mobile); 
					// final Actions builder = new Actions(driver);
					if(mobile.length()>0) {
						System.out.println("inside if mobile");
						for(int i=1;i<=20;i++)
						{
							if(returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).isDisplayed())
							{
								if(!returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).getAttribute("value").equals(mobile))
								{
									returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).clear();

									returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE.getValue()).sendKeys(mobile);
									status="Updated mobileNo";
								}
								else
								{
									status="Nothing to update mobileNo";
								}

								break;
							}
							else
							{
								returnid(driver,Origin.CONTACT_CENTER_SCROLL_DOWN.getValue()).click();
							}
						}

						Thread.sleep(5000);

						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_SAVE.getValue()).click();

						Thread.sleep(5000);

						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("window.scrollBy(0,-2000)", "");
						String newNumber=returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_REFLECT.getValue()).getText();
						System.out.println("***"+newNumber);
						if(newNumber.equals(mobile))
						{
							status=status+"::thank you";
						}
					}
					if(postCode.length()>0)
					{

						System.out.println("inside if post");
						for(int i=1;i<=20;i++)
						{
							if(returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).isDisplayed())
							{
								if(!returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).getAttribute("value").equals(postCode))
								{
									returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).clear();

									returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).sendKeys(postCode);
									status="Updated postCode";
								}
								else
								{
									status="Nothing to update postCode";
								}

								break;
							}
							else
							{
								returnid(driver,Origin.CONTACT_CENTER_SCROLL_DOWN.getValue()).click();
							}
						}

						Thread.sleep(5000);

						returnid(driver,Origin.CONTACT_CENTER_POSTCODE.getValue()).sendKeys(Keys.TAB);
						returnid(driver,Origin.CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_SAVE.getValue()).click();

						Thread.sleep(5000);

						JavascriptExecutor jse = (JavascriptExecutor)driver;
						jse.executeScript("window.scrollBy(0,-2000)", "");
						String newPostCode=returnid(driver,Origin.CONTACT_CENTER_POSTCODE_CHANGE.getValue()).getText();
						System.out.println("***"+newPostCode);
						if(newPostCode.contains(postCode))
						{
							status=status+"::thank you";
						}

					}
				}

			}
		}
		catch(Exception e)
		{
			e.getMessage();

		}
		Customer cust=new Customer();
		cust.setUpdateMessage(status);
		driver.quit();
		return cust;
	}
	public WebElement returnid(WebDriver driver,String id)
	{
		WebElement pathFix=driver.findElement(By.xpath(id));
		return pathFix;
	}

	/*public static void main(String args[])
{

	Trial tr=new Trial();

	//tr.setCustomerDetail("0065178655","07789562341", null);
	System.out.println(tr.setCustomerDetail("0065178655","07714527890", "").getUpdateMessage());


}*/




}
