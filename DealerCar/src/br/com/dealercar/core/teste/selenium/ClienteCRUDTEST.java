package br.com.dealercar.core.teste.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Classe que realiza o teste de CRUD de um Cliente utilizando o Selenium
 * 
 * @author Paulinho
 *
 */
public class ClienteCRUDTEST extends TEST {

	private static WebDriver driver = null;
	public static WebDriverWait wait = null;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		ClienteCRUDTEST.driver = driver;
	}

	@Test
	public void executar() {

		// efetuando login
		driver = LoginTEST.efetuarLogin();

		driver.navigate().refresh();
		driver.get("http://localhost:8080/DealerCar/faces/pages/cliente.xhtml");

		// cadastro de Cliente
		pesquisarEAbrirTelaNovoCliente();
		inserirDados();

		driver.navigate().refresh();
		
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		
		// pesquisa Cliente Cadastrado
		pesquisarCliente();

		// clicando no botao excluir
		driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:btnExcluir")).click();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

		// confirmando a exclusao
		driver.findElement(By.xpath("(//button[@id='tabViewConsultar:frmConsultaCliente:confirmaExclusao'])[2]")).click();

	}

	public static void pesquisarEAbrirTelaNovoCliente() {
		
		wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
		
		WebElement txtPesquisar = driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:inpCPF"));
		txtPesquisar.clear();
		txtPesquisar.sendKeys("141.767.779-15");

		driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:btnPesquisar")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:btnNovo")).click();

	}

	public static void pesquisarCliente() {
		
		WebElement txtPesquisar = driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:inpCPF"));
		txtPesquisar.clear();
		txtPesquisar.sendKeys("141.767.779-15");

		driver.findElement(By.id("tabViewConsultar:frmConsultaCliente:btnPesquisar")).click();

	}


	public static void inserirDados() {
		WebElement dialogCadastrar = driver.findElement(By.id("dlgCadastrar"));
		System.out.println("Tamanho Dialogo CADASTRAR");
		System.out.println(dialogCadastrar.getSize());

		driver.switchTo().activeElement();
		
		wait = new WebDriverWait(driver, 30);
		wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);

		WebElement txtDtNascimento = driver.findElement(By.id("frmClienteCadastrar:data_input"));
		txtDtNascimento.sendKeys("");
		txtDtNascimento.sendKeys("01/10/1980");
		txtDtNascimento.click();

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

		driver.findElement(By.id("frmClienteCadastrar:uf_label")).click();
		driver.findElement(By.id("frmClienteCadastrar:uf_filter")).clear();
		driver.findElement(By.id("frmClienteCadastrar:uf_5")).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		WebElement txtNome = driver.findElement(By.id("frmClienteCadastrar:txtNome"));
		txtNome.sendKeys("");
		txtNome.sendKeys("Jose do Espirito Santo");

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.switchTo().activeElement();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//div[@id='frmClienteCadastrar:cidade']/div[3]")).click();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		By element = By.id("frmClienteCadastrar:cidade_label");
		clickButton(element);

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

		By element2 = By.id("frmClienteCadastrar:cidade_5");
		clickButton(element2);

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.findElement(By.id("frmClienteCadastrar:btnSALVAR")).click();

	}

	public static void clickButton(By element) {
		wait = new WebDriverWait(driver,30); // espera por dez sengundos
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		driver.findElement(element).click();
	}
	
//	public static void editarCliente() {
//
//		WebElement dialogCadastrar = driver.findElement(By.id("dlgEditar"));
//		System.out.println("Tamanho Dialogo EDITAR");
//		System.out.println(dialogCadastrar.getSize());
//
//		driver.switchTo().activeElement();
//		
//		WebElement txtCelular = driver.findElement(By.id("frmClienteEditar:txtCelular"));
//		wait = new WebDriverWait(driver,10); // espera por 5 sengundos
//		
//		txtCelular.sendKeys("");
//		txtCelular.sendKeys("(11)99999-9999");
//		
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		
//		driver.findElement(By.id("frmClienteEditar:btnEditarSalvar")).click();
//	}
	

}
