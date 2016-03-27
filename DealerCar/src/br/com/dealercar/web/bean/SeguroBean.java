package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBSeguro")
@ViewScoped
public class SeguroBean extends AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Seguro seguro = new Seguro();
	private List<EntidadeDominio> listaSeguros = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaTiposSeguros = new ArrayList<EntidadeDominio>();
	
	
	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public List<EntidadeDominio> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<EntidadeDominio> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public List<EntidadeDominio> getListaTiposSeguros() {
		return listaTiposSeguros;
	}

	public void setListaTiposSeguros(List<EntidadeDominio> listaTiposSeguros) {
		this.listaTiposSeguros = listaTiposSeguros;
	}

	@Override
	public void carregarListagem() {
		
		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();
		resultado = command.execute(seguro);

		if (resultado != null) {
			listaSeguros = resultado.getEntidades();
		}
		
		resultado = command.execute(seguro.getTipoSeguro());

		if (resultado != null) {
			listaTiposSeguros = resultado.getEntidades();
		}
		
		
	}
	
	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(seguro);
		
		if (resultado != null) {
			seguro = (Seguro) resultado.getEntidades().get(0);
		}

		seguro = new Seguro();

	}
	
	public void limparObjetos(){
		seguro = new Seguro();
	}

}
