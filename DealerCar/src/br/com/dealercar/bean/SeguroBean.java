package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;
import br.com.dealercar.strategy.valida.ValidaItemOpcional;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBSeguro")
@ViewScoped
public class SeguroBean implements IBean, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Seguro seguro = new Seguro();
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();
	private List<TipoSeguro> listaTiposSeguros = new ArrayList<TipoSeguro>();
	
	
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}


	public List<TipoSeguro> getListaTiposSeguros() {
		return listaTiposSeguros;
	}

	public void setListaTiposSeguros(List<TipoSeguro> listaTiposSeguros) {
		this.listaTiposSeguros = listaTiposSeguros;
	}

	@Override
	public void carregarListagem() {
		
		listaSeguros = new SeguroDAO().listarTodos();
		listaTiposSeguros = new TipoSeguroDAO().listarTodos();
		
	}
	
	/**
	 * Cadastra um novo seguro
	 */
	public void cadastrar(){
	
		//validando o tipo seguro escolhido
		seguro.setTipoSeguro((TipoSeguro) new ValidaItemOpcional().validar(seguro.getTipoSeguro()));
		
		new SeguroDAO().cadastrar(seguro);
		
		seguro = new Seguro();
		
		JSFUtil.adicionarMensagemSucesso("Seguro cadastrado com Sucesso");
		
	}
	
	/**
	 * Editar um seguro
	 */
	public void editar(){
		
		//validando o tipo seguro escolhido
		seguro.setTipoSeguro((TipoSeguro) new ValidaItemOpcional().validar(seguro.getTipoSeguro()));
		
		new SeguroDAO().editar(seguro);

		seguro = new Seguro();
		
		JSFUtil.adicionarMensagemSucesso("Seguro editado com Sucesso");
		
		
	}
	
	/**
	 * Exclui um seguro
	 */
	public void excluir(){
		
		new SeguroDAO().excluir(seguro);
		
		seguro = new Seguro();
		
		JSFUtil.adicionarMensagemSucesso("Seguro excluido com Sucesso");
	}

}
