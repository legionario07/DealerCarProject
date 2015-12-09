package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Funcionario;

@javax.faces.bean.ManagedBean(name = "MBFuncionario")
@ViewScoped
public class FuncionarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
	private List<Cidade> listaCidades = new ArrayList<Cidade>();
	private int totalFuncionario;
	
	private boolean ehCadastrado = false;
	private boolean jaPesquisei = false;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}
	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}
	public List<Cidade> getListaCidades() {
		return listaCidades;
	}
	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}
	public int getTotalFuncionario() {
		return totalFuncionario;
	}
	public void setTotalFuncionario(int totalFuncionario) {
		this.totalFuncionario = totalFuncionario;
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
	
	public void carregarListagem(){
		
		listaFuncionario = new FuncionarioDAO().listarTodos();
		listaCidades     = new CidadeDAO().listarTodos();
		totalFuncionario = listaFuncionario.size();
		
	}
	
}
