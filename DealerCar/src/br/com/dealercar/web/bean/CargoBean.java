package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.Cargo;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.web.command.ICommand;

/**
 * Controller responsável por gerenciar o View Cargo.xhtml
 * 
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBCargo")
@ViewScoped
public class CargoBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Cargo cargo = new Cargo();
	private List<EntidadeDominio> listaCargos = new ArrayList<EntidadeDominio>();
	private int totalCargo;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public int getTotalCargo() {
		return totalCargo;
	}

	public void setTotalCargo(int totalCargo) {
		this.totalCargo = totalCargo;
	}

	public List<EntidadeDominio> getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List<EntidadeDominio> listaCargos) {
		this.listaCargos = listaCargos;
	}

	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");

		Resultado resultado = new Resultado();

		resultado = command.execute(cargo);

		if (resultado != null) {
			listaCargos = resultado.getEntidades();
		}

		totalCargo = listaCargos.size();

	}

	@Override
	public void executar() {

		//recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);

		Resultado resultado = new Resultado();

		resultado = command.execute(cargo);

		if (resultado != null) {
			cargo = (Cargo) resultado.getEntidades().get(0);
		}

		cargo = new Cargo();

	}

	public void limparObjetos() {
		cargo = new Cargo();
	}

}
