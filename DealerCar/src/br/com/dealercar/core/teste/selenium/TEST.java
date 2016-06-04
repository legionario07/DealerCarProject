package br.com.dealercar.core.teste.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public abstract class TEST{

	protected WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param args
	 */
	public static WebDriver retornarDriverSeleniumChrome(){
		
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		return driver;
		

	}
	
	/**
	 * @param args
	 */
	public static WebDriver retornarDriverSeleniumIE(){
		
		System.setProperty("webdriver.ie.driver", "C:/IEDriverServer_Win32_2.53.1/IEDriverServer.exe");
		WebDriver driver = new InternetExplorerDriver();
		
		return driver;
		

	}
	
	

}
