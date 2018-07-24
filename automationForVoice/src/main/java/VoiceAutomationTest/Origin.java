package VoiceAutomationTest;


public enum Origin 
{
	GET_USER_DYN_URL("GetUserDynUrl","http://lnxs0506.uk.b-and-q.com:8080/dyn/admin/nucleus//atg/commerce/order/OrderRepository/"),
	DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX("DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_TEXTBOX","//*[@name='xmltext']"),
	DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON("DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_ENTER_BUTTON","//*[@value='Enter']"),
	DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT("DYNADMIN_PAGE_COMPONENT_BROWSER_QUERY_RESULT","html/body/pre/code"),
	SENT_TO_OMS("SENT_TO_OMS","Order Just Submitted"),
	CREATED_TO_OMS("CREATED_TO_OMS","Sales Order Notification Sent in ATG"),
	PROCESSING("PROCESSING","Order Shipment processing started"),
	DISPATCHED("DISPATCHED","Order Ready for Shipment"),
	DELIVERED("DELIVERED","Order Shipped"),
	LOGIN_TEXT_FIELD("login id","//*[@id='user']"),
	PASSWORD_FIELD("PASSWORD_FIELD","//*[@id='pwd']"),
	LOGIN_BUTTON("LOGIN_BUTTON","//*[@class='action-bar']/input[1]"),
	AGENT_USERID("AGENT_USERID","service"),
	AGENT_PASSWORD("AGENT_PASSWORD","Service123"),
	CONTACT_CENTER_LOACTION("CONTACT_CENTER_LOACTION","0505"),
	CONTACT_CENTER_LOACTION_FIELD("CONTACT_CENTER_LOACTION_FIELD","//*[@class='error-wrap']/input[1]"),
	CONTACT_CENTER_LOACTION_CONTINUE("CONTACT_CENTER_LOACTION_CONTINUE","//input[@value='Continue']"),
	CONTACT_CENTER_CUSTOMER_SEARCH_FIELD("CONTACT_CENTER_CUSTOMER_SEARCH_FIELD","//*[@id='details']"),
	CONTACT_CENTER_CUSTOMER_SEARCH_LOOKUP("CONTACT_CENTER_CUSTOMER_SEARCH_LOOKUP","//*[@id='start-tab']/section/div[1]/div[1]/div[2]/div[1]/form/div/div/input[2]"),
	CONTACT_CENTER_CUSTOMER_SEARCH_EDIT("CONTACT_CENTER_CUSTOMER_SEARCH_EDIT","//a[@class='js-modal edit-link pull-right chevron-link']"),
	CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE("CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE","//input[@id='mobilenumber']"),
	CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_SAVE("CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_SAVE","//input[@value='Save contact details']"),
	CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_REFLECT("CONTACT_CENTER_CUSTOMER_SEARCH_MOBILE_CHANGE_REFLECT","//div[@class='accord-wrapper js-tpAccountContact']/div[2]/div[2]/dl/dd[2]"),
	CONTACT_CENTER_CONFIRM_LOCATION("CONTACT_CENTER_CONFIRM_LOCATION","//*[@class='error-wrap']/input[2]"),
	CONTACT_CENTER_SCROLL_DOWN("CONTACT_CENTER_SCROLL_DOWN","//a[@class='jspArrow jspArrowDown']"),
	CONTACT_CENTER_POSTCODE("CONTACT_CENTER_POSTCODE","//input[@id='manual-post-code']"),
	CONTACT_CENTER_POSTCODE_CHANGE("CONTACT_CENTER_POSTCODE_CHANGE","//div[@class='js-account-details-response']/div[2]/div[2]/div[1]/dl/dd[2]"),
	
	
	
	
	
	
	
	
	
	
	
	GET_USER_AGENT_URL("GET_USER_AGENT_URL","http://lnxs0506.uk.b-and-q.com:8030/agent-front/"),
	

	
	;
	
	
	private String key;
	private String value;
	
	Origin(String key,String value)
	{
		this.key=key;
		this.value=value;
				
	}
	
	
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
}

