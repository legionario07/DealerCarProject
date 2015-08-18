package br.com.dealercar.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.domain.Cliente;

@ManagedBean(name="MBCliente")
@ViewScoped
public class ClienteBean {
	
	private List<Cliente> listaClientes;
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
	
	

	//carrega a lista
	public void carregarListagem() {
		ClienteDAO dao = new ClienteDAO();
		listaClientes = dao.listarTodos();
		setTotalClientes(listaClientes.size());
		
	}


	
	
}
