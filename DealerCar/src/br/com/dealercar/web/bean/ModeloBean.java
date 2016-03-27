package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.web.command.ICommand;

/**
 * Controller responsável por gerenciar o View Modelos.xhtml
 * 
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBModelo")
@ViewScoped
public class ModeloBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Modelo modelo = new Modelo();
	private List<EntidadeDominio> listaModelos = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaFabricantes = new ArrayList<EntidadeDominio>();
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

	public List<EntidadeDominio> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<EntidadeDominio> listaModelos) {
		this.listaModelos = listaModelos;
	}

	public List<EntidadeDominio> getListaFabricantes() {
		return listaFabricantes;
	}

	public void setListaFabricantes(List<EntidadeDominio> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();
		resultado = command.execute(new Fabricante());

		if (resultado != null) {
			listaFabricantes = resultado.getEntidades();
		}
		
		resultado = command.execute(modelo);
		if (resultado != null) {
			listaModelos = resultado.getEntidades();
		}

		totalModelo = listaModelos.size();

	}

	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(modelo);
		
		if (resultado != null) {
			modelo = (Modelo) resultado.getEntidades().get(0);
		}

		modelo = new Modelo();

	}

	public void limparObjetos() {
		modelo = new Modelo();
	}

}
