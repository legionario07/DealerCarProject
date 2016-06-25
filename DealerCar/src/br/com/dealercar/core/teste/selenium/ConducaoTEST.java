package br.com.dealercar.core.teste.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Classe que realiza um teste na view de uma analiza, gerando um gráfico de
 * linhas
 * 
 * @author Paulinho
 *
 */
public class ConducaoTEST extends TEST {

	private static WebDriver driver = null;
	private static WebDriverWait wait = null;

	@Test
	public void executarConducao() {

		
		// efetuando login
		driver = LoginTEST.efetuarLogin();
		
		
		wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);

		// realiza um refesh e abra a pagina de retiradas
		driver.navigate().refresh();
		driver.get("http://localhost:8080/DealerCar/faces/pages/retirada.xhtml");

		driver.findElement(By.id("tabViewConsultar:frmConsultaRetirada:inpCPF")).clear();
		driver.findElement(By.id("tabViewConsultar:frmConsultaRetirada:inpCPF")).sendKeys("294.530.858-47");
		driver.findElement(By.id("tabViewConsultar:frmConsultaRetirada:btnPesquisar")).click();
		driver.findElement(By.id("tabViewConsultar:frmConsultaRetirada:btnEfetuarRetirada")).click();
		driver.switchTo().activeElement();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		By element = By.id("frmEfetuarRetirada:modelo_label");
		clickButton(element, driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		element = By.id("frmEfetuarRetirada:modelo_1");
		clickButton(element, driver);

		wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

		driver.findElement(By.id("frmEfetuarRetirada:txtQuilometragem")).sendKeys("123423");

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.findElement(By.id("frmEfetuarRetirada:data_input")).clear();
		driver.findElement(By.id("frmEfetuarRetirada:data_input")).sendKeys("29/06/2016");
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		element = By.id("frmEfetuarRetirada:Seguro_label");
		clickButton(element, driver);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		element = By.id("frmEfetuarRetirada:Seguro_1");
		clickButton(element, driver);

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		element = By.id("frmEfetuarRetirada:placa_label");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		clickButton(element, driver);
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		element = By.id("frmEfetuarRetirada:placa_1");
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		clickButton(element, driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		element = By.id("frmEfetuarRetirada:TipoSeguro_label");
		clickButton(element, driver);

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		element = By.id("frmEfetuarRetirada:TipoSeguro_1");
		clickButton(element, driver);
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

		driver.findElement(By.id("frmEfetuarRetirada:btnConfirmarRetirada")).click();
		
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);
		
		
		//Aqui inicia a devolucao
		driver.navigate().refresh();
		driver.get("http://localhost:8080/DealerCar/faces/pages/pesqdevcpf.xhtml");
		
		driver.findElement(By.id("tabViewConsultar:frmConsultaDevolucao:inpCPF")).clear();
		driver.findElement(By.id("tabViewConsultar:frmConsultaDevolucao:inpCPF")).sendKeys("294.530.858-47");
		driver.findElement(By.id("tabViewConsultar:frmConsultaDevolucao:btnPesquisar")).click();
		efetuarDevolucao();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		
		efetuarDevolucao();
		

	}

	public void efetuarDevolucao() {
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);
		driver.findElement(By.id("novaDevolucao:frmDevolucaoEfetuar:btnEfetuarDevolucao")).click();
		
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);
		
		driver.switchTo().activeElement();
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.id("fmrDevolucao:txtQuilometragem")), "value"));
		
		driver.findElement(By.id("fmrDevolucao:txtQuilometragem")).clear();
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);
		driver.findElement(By.id("fmrDevolucao:txtQuilometragem")).sendKeys("234123");
		driver.findElement(By.id("fmrDevolucao:btnSalvar")).click();
		driver.manage().timeouts().implicitlyWait(50000, TimeUnit.SECONDS);
	}


}
