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
import br.com.dealercar.web.command.ICommand;

/**
 * Controller responsável por gerenciar o View Fabricantes.xhtml
 * 
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Fabricante fabricante = new Fabricante();
	private List<EntidadeDominio> listaFabricantes = new ArrayList<EntidadeDominio>();
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

		resultado = command.execute(fabricante);

		if (resultado != null) {
			listaFabricantes = resultado.getEntidades();
		}

		totalFabricante = listaFabricantes.size();

	}

	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(fabricante);

		if (resultado != null) {
			fabricante = (Fabricante) resultado.getEntidades().get(0);
		}

		fabricante = new Fabricante();

	}

	public void limparObjetos() {
		fabricante = new Fabricante();
	}

}
