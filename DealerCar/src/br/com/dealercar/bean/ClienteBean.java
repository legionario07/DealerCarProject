package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Endereco;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean implements Serializable {

	/**
	 * Controlando a evolução dos objetos serialidos.... 
	 * Ex.: Salva um objeto em um arquivo, meses depois em que adicionar um método e ou atributo na sua classe. 
	 * Quando tentar deserializar o objeto naão é permitido mais. 
	 * Mantendo o serialVersionUID este erro não ocorre.
	 * Assim é permitido deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente = new Cliente();
	private Cliente clienteRetorno = new Cliente();
	private Cidade cidade = new Cidade();
	private Endereco endereco = new Endereco();
	
	private ClienteDAO cliDao = new ClienteDAO();
	private CidadeDAO cidDao = new CidadeDAO();
	
	private List<Cliente> listaClientes = new ArrayList<Cliente> ();
	private List<Cidade> listaCidades =  new ArrayList<Cidade>();
	private int totalClientes;
	
	private boolean ehCadastrado = false;
	private boolean jaPesquisei = false;
	

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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Cliente getClienteRetorno() {
		return clienteRetorno;
	}

	public void setClienteRetorno(Cliente clienteRetorno) {
		this.clienteRetorno = clienteRetorno;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public boolean isEhCadastrado() {
		return ehCadastrado;
	}

	public void setEhCadastrado(boolean ehCadastrado) {
		this.ehCadastrado = ehCadastrado;
	}

	public boolean isJaPesquisei() {
		return jaPesquisei;
	}

	public void setJaPesquisei(boolean jaPesquisei) {
		this.jaPesquisei = jaPesquisei;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * Carrega a listagem de cidades assim que inicia a pagina XHTML
	 */
	public void carregarListagemCidades() {
		listaCidades = cidDao.listarTodos();
	}

	/**
	 * carrega a listagem de todos os objetos de Clientes ao iniciar a tela
	 * e calcula a quantidade existente e coloca na variavel totalClientes
	 */
	
	public void carregarListagem() {
		
		listaClientes = cliDao.listarTodos();
		setTotalClientes(listaClientes.size());

	}

	/**
	 * Cadastra um novo Objeto de Cliente passando como parametro os dados
	 * do Cliente e da Cidade que o usuário digitou na Tela
	 */
	public void cadastrar() {

		cliente.setEndereco(endereco);
		
		listaCidades = cidDao.listarTodos();
		cliDao.cadastrar(cliente);

		JSFUtil.adicionarMensagemSucesso("Cliente Cadastrado com Sucesso.");

		cliente = new Cliente();
		cidade = new Cidade();
	}

	/**
	 * Pesquisa um cliente no BD de acordo com o ID digitado pelo 
	 * Usuário na Tela
	 */
	public void pesquisarPorID() {
		this.setEhCadastrado(false);
		
		for (Cliente cli : listaClientes) {
			if (clienteRetorno.getId() == cli.getId()) {
				this.setEhCadastrado(true);
				break;
			}
		}

		if (this.ehCadastrado == false) {
			clienteRetorno = new Cliente();
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Cadastrado.");
			return;
		}
		clienteRetorno = cliDao.pesquisarPorID(clienteRetorno);
	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo
	 * Usuário na TEla
	 */
	public void pesquisarPorCPF() {
		
		this.ehCadastrado = false;
		this.jaPesquisei = true;

		for (Cliente cli : listaClientes) {
			if (clienteRetorno.getCPF().toString().equals(cli.getCPF().toString())) {
				this.ehCadastrado = true ; 
				this.jaPesquisei = false;
				clienteRetorno=cliDao.pesquisarPorCPF(clienteRetorno);
				return;
			}
		}

		if (this.ehCadastrado == false) {
			clienteRetorno = new Cliente();
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Cadastrado.");
			return;
		}
		
	}
	
	/**
	 * Edita o Cliente desejado pelo Usuário apos realizado a pesquisa pelo CPF na tela
	 */
	public void editar() {
		
		
		//Verifica a cidade escolhida para ser adicionado ao Cliente que esta sendo editado
		
		for (Cidade cid : listaCidades) {
			if (cid.getNome().equals(clienteRetorno.getCidade().getNome())) {
				setCidade(cid);
				clienteRetorno.setCidade(cidade);
				break;
			}

		} 
	
		
		cliDao.editar(clienteRetorno);

		JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso.");
	}

	/**
	 * Exclui um objeto de CLiente desejado pelo usuario
	 * e solicita a confirmação na tela
	 */
	public void excluir() {
		
		cliDao.excluir(clienteRetorno);
		JSFUtil.adicionarMensagemSucesso("Cliente excluido com Sucesso.");
		clienteRetorno = new Cliente();
		this.jaPesquisei = false;
		this.ehCadastrado = false;
		
	}
	
	/**
	 * limpa a tela de pesquisa de Cliente do Usuario
	 * Deixando pronto para uma nova Pesquisa
	 */
	public void limparPesquisa() {
		clienteRetorno = new Cliente();
		this.ehCadastrado = false;
	}

}
