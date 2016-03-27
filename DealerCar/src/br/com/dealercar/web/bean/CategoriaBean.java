package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBCategoria")
@ViewScoped
public class CategoriaBean extends AbstractBean implements Serializable {

	/**
	 * Controlando a evolução dos objetos serialidos.... Ex.: Salva um objeto em
	 * um arquivo, meses depois em que adicionar um método e ou atributo na sua
	 * classe. Quando tentar deserializar o objeto naão é permitido mais.
	 * Mantendo o serialVersionUID este erro não ocorre. Assim é permitido
	 * deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = 1L;

	private Categoria categoria = new Categoria();
	private List<EntidadeDominio> listaCategoria = new ArrayList<EntidadeDominio>();

	private int totalCategoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<EntidadeDominio> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<EntidadeDominio> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public int getTotalCategoria() {
		return totalCategoria;
	}

	public void setTotalCategoria(int totalCategoria) {
		this.totalCategoria = totalCategoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Carrega um Lista de Categoria assim que a tela se inicia e coloca o valor
	 * de total de Categoria existente na variavel totalCategoria
	 */
	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");

		Resultado resultado = new Resultado();

		resultado = command.execute(categoria);

		if (resultado != null) {
			listaCategoria = resultado.getEntidades();
		}

		this.setTotalCategoria(listaCategoria.size());

	}


	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(categoria);

		if (resultado != null) {
			categoria = (Categoria) resultado.getEntidades().get(0);
		}

		categoria = new Categoria();

	}

	public void limparObjetos() {
		categoria = new Categoria();
	}

}
