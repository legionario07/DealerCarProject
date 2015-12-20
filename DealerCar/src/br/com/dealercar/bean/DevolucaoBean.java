package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.DevolucaoDAO;
import br.com.dealercar.domain.Devolucao;

/**
 * Classe Controller responsavel pela View devolucao.xhtml
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBDevolucao")
@ViewScoped
public class DevolucaoBean extends AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Devolucao devolucao;
	private List<Devolucao> listaDevolucao = new ArrayList<Devolucao>();
	
	private int totalDevolucoes;
	
	public Devolucao getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

	public List<Devolucao> getListaDevolucao() {
		return listaDevolucao;
	}

	public void setListaDevolucao(List<Devolucao> listaDevolucao) {
		this.listaDevolucao = listaDevolucao;
	}

	public int getTotalDevolucoes() {
		return totalDevolucoes;
	}

	public void setTotalDevolucoes(int totalDevolucoes) {
		this.totalDevolucoes = totalDevolucoes;
	}

	@Override
	public void carregarListagem() {
		listaDevolucao = new DevolucaoDAO().listarTodos();
		
		this.setTotalDevolucoes(listaDevolucao.size());
		
	}

}
