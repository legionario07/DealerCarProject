package br.com.dealercar.core.teste.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class TEST{

	protected WebDriver driver;
	protected static WebDriverWait wait;
	
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
		driver.manage().window().maximize();
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
	
	protected static void clickButton(By element, WebDriver driver) {
		wait = new WebDriverWait(driver,5000); // espera 
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(element).click();
	}
	

}
