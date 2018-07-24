package com.tcs.automationForVoice.controller;

import org.openqa.selenium.WebDriver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/")
public class Controller {
	
	@RequestMapping(value = "order/{number}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
   
	@ResponseBody
	public String order(@PathVariable("number") String OrderNo) {
		   
		
        Trial tr=new Trial();
        Order od=tr.RetrieveOrderDetail(OrderNo);
        Gson g=new Gson();
        return g.toJson(od);
        
       
    }
	
	@RequestMapping(value = "orderStatus/{number}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public String orderStatus(@PathVariable("number") String OrderNo) 
	{
		Trial tr=new Trial();
        Order od=tr.RetrieveOrderDetail(OrderNo);
        Gson g=new Gson();
        return g.toJson(od.getOrderState());
		
	}
	
	@RequestMapping(value = "customerPhone", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public String customerPhone(@RequestBody Customer customer) 
	{
		System.out.println("customer phone"+customer.getCustomerMobile());
		Trial tr=new Trial();
		
        Customer cust=tr.setCustomerDetail(customer.getCustId(), customer.getCustomerMobile(), "");
        Gson g=new Gson();
        return g.toJson(cust.getUpdateMessage());
		
	}
	@RequestMapping(value = "customerPostcode", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	   
	@ResponseBody
	public String customerPostcode(@RequestBody Customer customer) 
	{
		System.out.println("customer postcode"+customer.getCustPostCode());
		Trial tr=new Trial();
		
        Customer cust=tr.setCustomerDetail(customer.getCustId(),"",customer.getCustPostCode());
        Gson g=new Gson();
        return g.toJson(cust.getUpdateMessage());
		
	}
		

}
