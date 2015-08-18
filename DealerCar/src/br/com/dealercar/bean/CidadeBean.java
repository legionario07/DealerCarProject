package br.com.dealercar.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.domain.Cidade;

@ManagedBean(name="MBcidade")
@ViewScoped
public class CidadeBean {

	List<Cidade> listaCidades;

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}
	
	public void carregarCidades() {
		CidadeDAO cDao = new CidadeDAO();
		listaCidades = cDao.listarTodos();
	}
	
}
