package br.com.dealercar.core.teste.selenium;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Classe que realiza um teste na view de uma analiza, gerando um gráfico de linhas
 * @author Paulinho
 *
 */
public class AnaliseLinhaTEST extends TEST {

	private static WebDriver driver = null;
	private static WebDriverWait wait = null;

	@Test
	public void executarAnalise() {

		// efetuando login
		driver = LoginTEST.efetuarLogin();

		wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
		
		// realiza um refesh e abra a pagina de graficos
		driver.navigate().refresh();
		driver.get("http://localhost:8080/DealerCar/faces/pages/graficosretiradas.xhtml");

		driver.findElement(By.xpath("//table[@id='tabViewGrafico:j_idt89:selectEscolha']/tbody/tr[2]/td/div/div[2]/span")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//clica para abir menu de anos
		driver.findElement(By.id("tabViewGrafico:j_idt89:selectEscolhaAno_label")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//seleciona o ano
		driver.findElement(By.id("tabViewGrafico:j_idt89:selectEscolhaAno_2")).click();
		
		//clica em gerar Grafico
		driver.findElement(By.id("tabViewGrafico:j_idt89:btnGerar")).click();

	}

}
