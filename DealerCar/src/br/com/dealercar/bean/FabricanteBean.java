package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.automotivos.FabricanteDAO;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.util.JSFUtil;

/**
 * Controller responsável por gerenciar o View Fabricantes.xhtml
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean implements Serializable, IBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Fabricante fabricante = new Fabricante();
	private List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
	private int totalFabricante;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}


	public int getTotalFabricante() {
		return totalFabricante;
	}

	public void setTotalFabricante(int totalFabricante) {
		this.totalFabricante = totalFabricante;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	@Override
	public void carregarListagem() {

		listaFabricantes = new FabricanteDAO().listarTodos();
		
		totalFabricante = listaFabricantes.size();
		
	}
	
	public void cadastrar(){
		
		new FabricanteDAO().cadastrar(fabricante);
		
		JSFUtil.adicionarMensagemSucesso("Fabricante Cadastrado com Sucesso.");
		
		fabricante = new Fabricante();
		
	}
	
	public void editar(){
		
		new FabricanteDAO().editar(fabricante);
		
		JSFUtil.adicionarMensagemSucesso("Fabricante Editado com Sucesso.");
		
		fabricante = new Fabricante();
	}
	
	public void excluir(){
		
		new FabricanteDAO().excluir(fabricante);
		
		JSFUtil.adicionarMensagemSucesso("Fabricante Excluido com Sucesso.");
		
		fabricante = new Fabricante();
		
	}
	
	public void limparObjetos(){
		fabricante = new Fabricante();
	}
	
}
