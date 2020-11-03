/**
 * 
 */
package com.grootantech.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver ldriver;
	
		public HomePage(WebDriver rdriver)
		{
			ldriver=rdriver;
			PageFactory.initElements(rdriver, this);
		}
		
		@FindBy(xpath="//*[@id='main-nav']/div[1]/ul/li[2]/a[1]")
		@CacheLookup
		WebElement Homebutton;
		
		@FindBy(xpath="//*[@id='main-nav']/div[1]/ul/li[2]/a[2]")
		@CacheLookup
		WebElement Servicesbutton;
		
		@FindBy(xpath="//*[@id='main-nav']/div[1]/ul/li[2]/a[3]")
		@CacheLookup
		WebElement OpenSourcebutton;
		
		@FindBy(xpath="//*[@id='main-nav']/div[1]/ul/li[2]/a[4]")
		@CacheLookup
		WebElement Blogbutton;
		
		@FindBy(xpath="//*[@id='gatsby-focus-wrapper']/div/nav/div/ul/li[2]/a[5]")
		@CacheLookup
		WebElement Teambutton;
		
		@FindBy(xpath="//*[@id='main-nav']/div[1]/ul/li[2]/a[6]")
		@CacheLookup
		WebElement Careersbutton;
		
		@FindBy(xpath="//*[@id='main-nav']/div[1]/ul/li[2]/a[7]")
		@CacheLookup
		WebElement ContactUsbutton;

		public void ClickHome()
		{
			Homebutton.click();
		}
		
		public void ClickServices()
		{
			Servicesbutton.click();;
		}
		
		public void ClickOpenSource()
		{
			OpenSourcebutton.click();;
		}
		
		public void ClickBlog()
		{
			Blogbutton.click();;
		}
		
		public void ClickTeam()
		{
			Teambutton.click();;
		}
		
		public void ClickCareers()
		{
			Careersbutton.click();;
		}
		
		public void ClickContactUs()
		{
			ContactUsbutton.click();;
		}
	}


