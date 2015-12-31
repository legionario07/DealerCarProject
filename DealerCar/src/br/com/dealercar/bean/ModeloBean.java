package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.automotivos.FabricanteDAO;
import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.strategy.valida.ValidaFabricante;
import br.com.dealercar.util.JSFUtil;

/**
 * Controller responsável por gerenciar o View Modelos.xhtml
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBModelo")
@ViewScoped
public class ModeloBean implements Serializable, IBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Modelo modelo = new Modelo();
	private List<Modelo> listaModelos = new ArrayList<Modelo>();
	private List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
	private int totalModelo;

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}


	public int getTotalModelo() {
		return totalModelo;
	}

	public void setTotalModelo(int totalModelo) {
		this.totalModelo = totalModelo;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}

	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	@Override
	public void carregarListagem() {

		listaModelos     = new ModeloDAO().listarTodos();
		listaFabricantes = new FabricanteDAO().listarTodos(); 
		
		totalModelo = listaModelos.size();
		
	}
	
	public void cadastrar(){
		
		
		//recebendo o modelo da view e pesquisando nos modelos cadastrados
		modelo.setFabricante((Fabricante) new ValidaFabricante().validar(modelo.getFabricante()));
		
		new ModeloDAO().cadastrar(modelo);
		
		JSFUtil.adicionarMensagemSucesso("Modelo Cadastrado com Sucesso.");
		
		modelo = new Modelo();
		
	}
	
	public void editar(){
		

		//recebendo o modelo da view e pesquisando nos modelos cadastrados
		modelo.setFabricante((Fabricante) new ValidaFabricante().validar(modelo.getFabricante()));
		
		new ModeloDAO().editar(modelo);
		
		JSFUtil.adicionarMensagemSucesso("Modelo Editado com Sucesso.");
		
		modelo = new Modelo();
	}
	
	public void excluir(){
		
		new ModeloDAO().excluir(modelo);
		
		JSFUtil.adicionarMensagemSucesso("Modelo Excluido com Sucesso.");
		
		modelo = new Modelo();
		
	}
	
}
