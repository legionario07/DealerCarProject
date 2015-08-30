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
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBCliente")
@ViewScoped
public class ClienteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente = new Cliente();
	private Cliente clienteRetorno = new Cliente();
	private List<Cliente> listaClientes = new ArrayList<Cliente> ();
	private List<Cidade> listaCidades =  new ArrayList<Cidade>();
	private int totalClientes;
	private Cidade cidade = new Cidade();
	private boolean ehCadastrado = false;
	private boolean jaPesquisei = false;
	

	public ClienteBean() {
	}

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

	public void carregarListagemCidades() {
		CidadeDAO cidDao = new CidadeDAO();
		listaCidades = cidDao.listarTodos();
	}

	// carrega a lista
	public void carregarListagem() {
		ClienteDAO cliDao = new ClienteDAO();
		listaClientes = cliDao.listarTodos();
		setTotalClientes(listaClientes.size());

	}

	public void cadastrar() {
		ClienteDAO cliDao = new ClienteDAO();
		CidadeDAO cidDao = new CidadeDAO();
		
		listaCidades = cidDao.listarTodos();
		cliDao.cadastrar(cliente, cidade);

		JSFUtil.adicionarMensagemSucesso("Cliente Cadastrado com Sucesso.");

		cliente = new Cliente();
		cidade = new Cidade();
	}

	public void pesquisarPorID() {
		this.setEhCadastrado(false);
		ClienteDAO cliDao = new ClienteDAO();
		
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

	public void pesquisarPorCPF() {
		
		ClienteDAO cliDao = new ClienteDAO();
		
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
	
	public void editar() {
		ClienteDAO cliDao = new ClienteDAO();
		
		for (Cidade cid : listaCidades) {
			if (cid.getNome().equals(clienteRetorno.getCidade().getNome())) {
				setCidade(cid);
				clienteRetorno.setCidade(cidade);
				break;
			}

		}
		cliDao.editar(clienteRetorno, cidade);

		JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso.");
	}

	public void excluir() {
		ClienteDAO cliDao = new ClienteDAO();
		
		cliDao.excluir(clienteRetorno);
		JSFUtil.adicionarMensagemSucesso("Cliente excluido com Sucesso.");
		clienteRetorno = new Cliente();
		this.jaPesquisei = false;
		this.ehCadastrado = false;
		
	}
	
	public void limparPesquisa() {
		clienteRetorno = new Cliente();
		this.ehCadastrado = false;
	}

}
