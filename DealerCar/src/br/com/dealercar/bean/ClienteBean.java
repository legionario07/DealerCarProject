package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;

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
		cliente = new Cliente();
		cidade = new Cidade();
	}

	public void pesquisarPorID() {
		cliente.setId(2);
		setClienteRetorno(cliente.pesquisarPorID(cliente));
		
	}

}
