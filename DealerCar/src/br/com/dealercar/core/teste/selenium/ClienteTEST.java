package br.com.dealercar.core.teste.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ClienteTEST extends TEST {

	private static WebDriver driver = null;

	public static void main(String[] args) {
		
		// efetuando login
		driver = LoginTEST.efetuarLogin();
		
		driver.navigate().refresh();
		driver.get("http://localhost:8080/DealerCar/faces/pages/cliente.xhtml");
		
		pesquisarEAbrirTelaNovoCliente();
		inserirDados();
		
		
	}
	
	
	public static void pesquisarEAbrirTelaNovoCliente() {
		WebElement txtPesquisar = driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:inpCPF"));
		txtPesquisar.clear();
		txtPesquisar.sendKeys("141.767.779-15");
		
		WebElement btnPesquisar = driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:btnPesquisar"));
		btnPesquisar.click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
		
		WebElement btnNovo = driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:btnNovo"));
		btnNovo.click();
		
	}
	
	public static void inserirDados(){
		WebElement dialogCadastrar = driver.findElement(By.id("dlgCadastrar"));
		System.out.println("Tamanho Dialogo CADASTRAR");
		System.out.println(dialogCadastrar.getSize());
		
		
		driver.switchTo().activeElement();
		
		WebElement txtNome = driver.findElement(By.id("frmClienteCadastrar:txtNome"));
		txtNome.sendKeys("");
		txtNome.sendKeys("Jose do Espirito Santo");
		
		WebElement txtDtNascimento = driver.findElement(By.id("frmClienteCadastrar:data_input"));
		txtDtNascimento.sendKeys("");
		txtDtNascimento.sendKeys("01/10/1980");
		
		WebElement txtNomeMae = driver.findElement(By.id("frmClienteCadastrar:txtNomeMae"));
		txtNomeMae.sendKeys("");
		txtNomeMae.sendKeys("Margarida do Espirito Santo");
		
		WebElement txtRg = driver.findElement(By.id("frmClienteCadastrar:txtRG"));
		txtRg.sendKeys("");
		txtRg.sendKeys("44521234");
		
		WebElement txtTelefone = driver.findElement(By.id("frmClienteCadastrar:txtTelefone"));
		txtTelefone.sendKeys("");
		txtTelefone.sendKeys("(11)4283-8293");
		
		WebElement txtCelular = driver.findElement(By.id("frmClienteCadastrar:txtCelular"));
		txtCelular.sendKeys("");
		txtCelular.sendKeys("(11)98283-8123");
		
		WebElement txtEmail = driver.findElement(By.id("frmClienteCadastrar:txtEmail"));
		txtEmail.sendKeys("");
		txtEmail.sendKeys("jose_do_espirito@hotmail.com");
		
		WebElement txtRua = driver.findElement(By.id("frmClienteCadastrar:txtRua"));
		txtRua.sendKeys("");
		txtRua.sendKeys("Rua do Lampião");
		
		WebElement txtNumero = driver.findElement(By.id("frmClienteCadastrar:txtNumero"));
		txtNumero.sendKeys("");
		txtNumero.sendKeys("18");
		
		WebElement txtComplemento = driver.findElement(By.id("frmClienteCadastrar:txtComplemento"));
		txtComplemento.sendKeys("");
		txtComplemento.sendKeys("Casa 2");
		
		WebElement txtBairro = driver.findElement(By.id("frmClienteCadastrar:txtBairro"));
		txtBairro.sendKeys("");
		txtBairro.sendKeys("Centro");
		
		WebElement selectUF = driver.findElement(By.id("frmClienteCadastrar:uf"));
		selectUF.click();
		
		WebElement estado = driver.findElement(By.id("frmClienteCadastrar:uf_filter"));
		estado.sendKeys("SP");
		estado.sendKeys(Keys.ENTER);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  
		
		WebElement cidade = driver.findElement(By.id("frmClienteCadastrar:cidade_input"));
		//WebElement selectCidade = driver.findElement(By.id("frmClienteCadastrar:cidade"));
		
		cidade.click();
//		
//		Select selects = new Select(cidade);
//		selects.selectByIndex(5);

		
		WebElement element = driver.findElement(By.id("frmClienteCadastrar"));
		List<WebElement> lista = element.findElements(By.xpath("//select[*]"));
		for(WebElement w : lista){
			System.out.println(w.getAttribute("id"));
			System.out.println(w.getText());
		}
		
//		WebElement cidade = driver.findElement(By.id("frmClienteCadastrar:cidade_filter"));
//		cidade.sendKeys("MOGI DAS CRUZES");
//		cidade.sendKeys(Keys.ENTER);
		
		//selectCidade.click();
		
		//Select select = new Select(driver.findElement(By.id("frmClienteCadastrar:uf_label")));
		//select.selectByValue("SP");
		//System.out.println(select.toString());

		
	}


	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		ClienteTEST.driver = driver;
	}

}
