package br.com.dealercar.core.teste.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTEST extends TEST{

	public static WebDriver efetuarLogin() {
		WebDriver driver = null;
		WebDriverWait wait = null;
		
		driver = TEST.retornarDriverSeleniumChrome();
		driver.get("http://localhost:8080/DealerCar/faces/pages/cliente.xhtml");
		
		wait = new WebDriverWait(driver, 5000);
		wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		// Capturando os elementos de email e senha pelo atributo name
		WebElement txtUsuario = driver.findElement(By.id("frmLogin:inpUsuario"));
		WebElement txtSenha = driver.findElement(By.id("frmLogin:inpSenha"));

		// Setando valores nos atributos de email e senha
		// Setando valores nos atributos de email e senha
		txtUsuario.clear();
		txtSenha.clear();
		txtUsuario.sendKeys("paulinho");
		txtSenha.sendKeys("legionario");

		// Efetuando um click no botão
		txtSenha.sendKeys(Keys.ENTER);
		
		return driver;
	}

}
