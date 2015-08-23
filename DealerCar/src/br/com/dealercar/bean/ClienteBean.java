package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
	private List<Cliente> listaClientes;
	private List<Cidade> listaCidades;
	private int totalClientes;
	private Cidade cidade = new Cidade();

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

	public void carregarListagemCidades() {
		listaCidades = cidade.listarTodos();
	}

	// carrega a lista
	public void carregarListagem() {
		listaClientes = cliente.listarTodos();
		setTotalClientes(listaClientes.size());

	}

	public void cadastrar() {

		CidadeBean cidBean = new CidadeBean();
		listaCidades = cidBean.listaCidades;
		cliente.cadastrar(cliente, cidade);

		JSFUtil.adicionarMensagemSucesso("Cliente Cadastrado com Sucesso.");

		cliente = new Cliente();
		cidade = new Cidade();
	}

	public void pesquisarPorID() {
		boolean ehIgual = false;

		for (Cliente cli : listaClientes) {
			if (clienteRetorno.getId() == cli.getId()) {
				ehIgual = true;
				break;
			}
		}

		if (ehIgual == false) {
			clienteRetorno = new Cliente();
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Localizado.");
			return;
		}
		setClienteRetorno(cliente.pesquisarPorID(clienteRetorno));
	}

	public void editar() {
		for (Cidade cid : listaCidades) {
			if (cid.getNome().equals(clienteRetorno.getCidade().getNome())) {
				setCidade(cid);
				clienteRetorno.setCidade(cidade);
				break;
			}

		}
		clienteRetorno.editar(clienteRetorno, cidade);

		JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso.");
	}

	public void excluir() {
		clienteRetorno.excluir(clienteRetorno);
		clienteRetorno = new Cliente();
		JSFUtil.adicionarMensagemNaoLocalizado("Cliente excluido com Sucesso.");
	}
	
	public void limparPesquisa() {
		clienteRetorno = new Cliente();
	}

}
