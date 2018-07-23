package VoiceAutomationTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



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






}
