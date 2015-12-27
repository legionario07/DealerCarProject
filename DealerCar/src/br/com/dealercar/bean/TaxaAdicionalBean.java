package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.automotivos.TaxasAdicionaisDAO;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;
import br.com.dealercar.util.JSFUtil;

/**
 * Controller responsável por gerenciar o View TaxasAdicionaiss.xhtml
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBTaxasAdicionais")
@ViewScoped
public class TaxaAdicionalBean implements Serializable, IBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaxasAdicionais taxasAdicionais = new TaxasAdicionais();
	private List<TaxasAdicionais> listaTaxasAdicionais = new ArrayList<TaxasAdicionais>();
	private int totalTaxasAdicionais;

	public TaxasAdicionais getTaxasAdicionais() {
		return taxasAdicionais;
	}

	public void setTaxasAdicionais(TaxasAdicionais taxasAdicionais) {
		this.taxasAdicionais = taxasAdicionais;
	}


	public int getTotalTaxasAdicionais() {
		return totalTaxasAdicionais;
	}

	public void setTotalTaxasAdicionais(int totalTaxasAdicionais) {
		this.totalTaxasAdicionais = totalTaxasAdicionais;
	}


	public List<TaxasAdicionais> getListaTaxasAdicionais() {
		return listaTaxasAdicionais;
	}

	public void setListaTaxasAdicionais(List<TaxasAdicionais> listaTaxasAdicionais) {
		this.listaTaxasAdicionais = listaTaxasAdicionais;
	}

	@Override
	public void carregarListagem() {

		listaTaxasAdicionais = new TaxasAdicionaisDAO().listarTodos();
		
		totalTaxasAdicionais = listaTaxasAdicionais.size();
		
	}
	
	public void cadastrar(){
		
		new TaxasAdicionaisDAO().cadastrar(taxasAdicionais);
		
		JSFUtil.adicionarMensagemSucesso("Taxa Adicional Cadastrada com Sucesso.");
		
		taxasAdicionais = new TaxasAdicionais();
		
	}
	
	public void editar(){
		
		new TaxasAdicionaisDAO().editar(taxasAdicionais);
		
		JSFUtil.adicionarMensagemSucesso("Taxa Adicional Editada com Sucesso.");
		
		taxasAdicionais = new TaxasAdicionais();
	}
	
	public void excluir(){
		
		new TaxasAdicionaisDAO().excluir(taxasAdicionais);
		
		JSFUtil.adicionarMensagemSucesso("Taxa Adicional Excluida com Sucesso.");
		
		taxasAdicionais = new TaxasAdicionais();
		
	}
	
}
