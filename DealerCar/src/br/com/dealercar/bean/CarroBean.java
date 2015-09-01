package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CarroDAO;
import br.com.dealercar.domain.Carro;

@ManagedBean(name="MBCarro")
@ViewScoped
public class CarroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Carro carro = new Carro();
	private List<Carro> listaCarros = new ArrayList<Carro>();
	private int totalCarros;
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}
	
	public int getTotalCarros() {
		return totalCarros;
	}

	public void setTotalCarros(int totalCarros) {
		this.totalCarros = totalCarros;
	}

	public void carregarListagemCarros() {
		CarroDAO carDao = new CarroDAO();
		listaCarros = carDao.listarTodos();
		this.setTotalCarros(listaCarros.size());
		
	}
	
}
