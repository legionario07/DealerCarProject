package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;
import br.com.dealercar.web.command.ICommand;

/**
 * Controller responsável por gerenciar o View TaxasAdicionaiss.xhtml
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBTaxasAdicionais")
@ViewScoped
public class TaxaAdicionalBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaxasAdicionais taxasAdicionais = new TaxasAdicionais();
	private List<EntidadeDominio> listaTaxasAdicionais = new ArrayList<EntidadeDominio>();
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


	public List<EntidadeDominio> getListaTaxasAdicionais() {
		return listaTaxasAdicionais;
	}

	public void setListaTaxasAdicionais(List<EntidadeDominio> listaTaxasAdicionais) {
		this.listaTaxasAdicionais = listaTaxasAdicionais;
	}

	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();
		resultado = command.execute(taxasAdicionais);

		if (resultado != null) {
			listaTaxasAdicionais = resultado.getEntidades();
		}
		
		totalTaxasAdicionais = listaTaxasAdicionais.size();
		
	}
	
	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(taxasAdicionais);
		
		if (resultado != null) {
			taxasAdicionais = (TaxasAdicionais) resultado.getEntidades().get(0);
		}

		taxasAdicionais = new TaxasAdicionais();

	}

	
	public void limparObjetos(){
		taxasAdicionais = new TaxasAdicionais();
	}
	
}
