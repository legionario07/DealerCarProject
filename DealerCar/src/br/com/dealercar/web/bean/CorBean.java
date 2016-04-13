package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Cor;
import br.com.dealercar.web.command.ICommand;

/**
 * Controller responsável por gerenciar o View Cors.xhtml
 * 
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBCor")
@ViewScoped
public class CorBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Cor cor = new Cor();
	private List<EntidadeDominio> listaCores = new ArrayList<EntidadeDominio>();
	private int totalCor;

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public int getTotalCor() {
		return totalCor;
	}

	public void setTotalCor(int totalCor) {
		this.totalCor = totalCor;
	}

	public List<EntidadeDominio> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<EntidadeDominio> listaCores) {
		this.listaCores = listaCores;
	}

	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");

		Resultado resultado = new Resultado();

		resultado = command.execute(cor);

		if (resultado != null) {
			listaCores = resultado.getEntidades();
		}

		totalCor = listaCores.size();

	}

	@Override
	public void executar() {

		//recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(cor);

		if (resultado != null) {
			cor = (Cor) resultado.getEntidades().get(0);
		}

		cor = new Cor();

	}

	public void limparObjetos() {
		cor = new Cor();
	}

}
