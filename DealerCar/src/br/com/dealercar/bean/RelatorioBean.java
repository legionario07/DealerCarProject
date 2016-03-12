package br.com.dealercar.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.dao.RetiradaDAO;
import br.com.dealercar.domain.Retirada;
import br.com.dealercar.relatorios.GeraRelatorio;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBRelatorio")
@ViewScoped
public class RelatorioBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Retirada retirada = new Retirada();

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	@Override
	public void carregarListagem() {

	}

	/**
	 * Pesquisa no BD uma Retirada de acordo com o ID digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorID() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Validando a retirada
		retirada = new RetiradaDAO().pesquisarPorID(retirada);

		// veficando se a retirada foi encontrada
		if (retirada != null) {
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		}

		if (isEhCadastrado() == false) {
			retirada = new Retirada();
			JSFUtil.adicionarMensagemNaoLocalizado("Retirada não encontrada.");
			return;
		}

	}

	/**
	 * Chama o Exporter da Retirada
	 */
	public void chamarExporter() {

		List<Retirada> retiradas = new ArrayList<Retirada>();
		retiradas.add(retirada);

		File jasper = new File(
				FacesContext.getCurrentInstance().getExternalContext().getRealPath("/comprovanteRetirada.jasper"));

		GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper, retiradas);

	}

	public void limparPesquisa() {
		setEhCadastrado(false);
		setJaPesquisei(false);
		retirada = new Retirada();

	}

}
