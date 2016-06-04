package br.com.dealercar.core.teste.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AnaliseLinhaTEST extends TEST{

	private static WebDriver driver = null;
	
	public static void main(String[] args) {
		
		// efetuando login
		driver = LoginTEST.efetuarLogin();
		
		driver.navigate().refresh();
		driver.get("http://localhost:8080/DealerCar/faces/pages/graficosretiradas.xhtml");
		
		pegarElementos();
		
	}
	
	private static void pegarElementos(){
		WebElement checkBox = driver.findElement(By.id("tabViewGrafico:j_idt89:selectEscolha"));
		System.out.println(checkBox.getAttribute("id"));
		List<WebElement> elementos = new ArrayList<WebElement>();
		elementos = checkBox.findElements(By.xpath("//*"));
		for(WebElement w : elementos){
			System.out.println(w.getAttribute("id"));
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
		
		WebElement escolha = checkBox.findElement(By.id("tabViewGrafico:j_idt89:selectEscolha:1"));
		escolha.click();

		
		
	}


	
}
