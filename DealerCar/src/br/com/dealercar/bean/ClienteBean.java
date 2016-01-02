package br.com.dealercar.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.strategy.valida.ValidaCidade;
import br.com.dealercar.strategy.valida.ValidaCliente;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.viewhelper.ViewHelper;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean extends AbstractBean implements Serializable {

	/**
	 * Controlando a evolução dos objetos serialidos.... Ex.: Salva um objeto em
	 * um arquivo, meses depois em que adicionar um método e ou atributo na sua
	 * classe. Quando tentar deserializar o objeto naão é permitido mais.
	 * Mantendo o serialVersionUID este erro não ocorre. Assim é permitido
	 * deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = 1L;

	private Cliente cliente = new Cliente();

	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Cidade> listaCidades = new ArrayList<Cidade>();
	private int totalClientes;

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}


	public int getTotalClientes() {
		return totalClientes;
	}

	public void setTotalClientes(int totalClientes) {
		this.totalClientes = totalClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}


	/**
	 * carrega a listagem de todos os objetos de Clientes e Cidades ao iniciar a tela e
	 * calcula a quantidade existente e coloca na variavel totalClientes
	 */
	@Override
	public void carregarListagem() {

		listaClientes = new ClienteDAO().listarTodos();
		listaCidades = new CidadeDAO().listarTodos();
		setTotalClientes(listaClientes.size());
	}

	/**
	 * Cadastra um novo Objeto de Cliente passando como parametro os dados do
	 * Cliente e da Cidade que o usuário digitou na Tela
	 */
	public void cadastrar() {

		cliente.getEndereco().setCidade((Cidade) new ValidaCidade().validar(cliente.getEndereco().getCidade()));

		// Verifica se o Cliente eh maior de idade
		int i = DataUtil.devolverDataEmAnos(cliente.getDataNasc());
		if (i < 18) {
			JSFUtil.adicionarMensagemErro("O Cliente deve ser maior de 18 anos");
			cliente.setDataNasc(null);
			return;
		}

		if (ViewHelper.validarIdadeMaxima(cliente.getDataNasc()) == -1) {

			JSFUtil.adicionarMensagemErro("A data de Nascimento é inválida.");
			cliente.setDataNasc(null);
			return;
		}

		new ClienteDAO().cadastrar(cliente);
		
		JSFUtil.adicionarMensagemSucesso("Cliente Cadastrado com Sucesso.");
		
		// encaminha para a pagina de cliente se não ocorrer nenhum erro
		FacesContext faces = FacesContext.getCurrentInstance();
		ExternalContext exContext = faces.getExternalContext();

		try {
			exContext.redirect("cliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		cliente = new Cliente();

	}

	

	/**
	 * Edita o Cliente desejado pelo Usuário apos realizado a pesquisa pelo CPF
	 * na tela
	 */
	public void editar() {

		// Verifica se o Cliente eh maior de idade
		int i = DataUtil.devolverDataEmAnos(cliente.getDataNasc());
		if (i < 18) {
			JSFUtil.adicionarMensagemErro("O Cliente deve ser maior de 18 anos");
			return;
		}

		
		if (ViewHelper.validarIdadeMaxima(cliente.getDataNasc()) == -1) {
			
			JSFUtil.adicionarMensagemErro("A data de Nascimento é inválida");
			return;
		}
		
		// Verifica a cidade escolhida para ser adicionado ao Cliente que esta
		// sendo editado
		cliente.getEndereco().setCidade((Cidade) new ValidaCidade().validar(cliente.getEndereco().getCidade()));

		new ClienteDAO().editar(cliente);

		JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso.");
		
		// Se não houve nenhum erro fecha o <p:Dialog>
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dlgClienteEditar').hide();");

	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {


		setEhCadastrado(false);
		setJaPesquisei(true);

		// Validando o cliente
		cliente = ((Cliente) new ValidaCliente().validar(cliente));

		// veficando se o cliente foi encontrado
		if (cliente != null) {
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		}

		if (isEhCadastrado() == false) {
			cliente = new Cliente();
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Cadastrado.");
			return;
		}

	}
	
	/**
	 * Exclui um objeto de Cliente desejado pelo usuario e solicita a
	 * confirmação na tela
	 */
	public void excluir() {

		new ClienteDAO().excluir(cliente);
		JSFUtil.adicionarMensagemSucesso("Cliente excluido com Sucesso.");
		limparPesquisa();

	}

	/**
	 * limpa a tela de pesquisa de Cliente do Usuario Deixando pronto para uma
	 * nova Pesquisa
	 */
	public void limparPesquisa() {
		cliente = new Cliente();
		setEhCadastrado(false);
		setJaPesquisei(false);
	}

}
