package br.com.dealercar.web.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBRelatorio")
@ViewScoped
public class RelatorioBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Retirada retirada = new Retirada();
	private Devolucao devolucao = new Devolucao();
	private String relatorioEscolhido;
	

	public RelatorioBean() {
		// verificando qual o relatorio escolhido pelo usuario
		relatorioEscolhido = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("relatorioEscolhido");
		
	}

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public Devolucao getDevolucao() {
		return devolucao;
	}

	public void setDevolucao(Devolucao devolucao) {
		this.devolucao = devolucao;
	}

	public String getRelatorioEscolhido() {
		return relatorioEscolhido;
	}

	public void setRelatorioEscolhido(String relatorioEscolhido) {
		this.relatorioEscolhido = relatorioEscolhido;
	}

	/**
	 * Chama o Exporter da Relatório
	 */
	public void chamarExporter() {

		if (relatorioEscolhido.equals("") || relatorioEscolhido.equals(null)) {
			// verificando qual o relatorio escolhido pelo usuario
			relatorioEscolhido = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("relatorioEscolhido");

		}
		
		if (relatorioEscolhido.equals("RETIRADA")) {
			List<Retirada> retiradas = new ArrayList<Retirada>();
			retiradas.add(retirada);

			File jasper = new File(FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/Relatorios/relatorioRetirada.jasper"));

			SessionUtil.remove("objetoRelatorio");
			SessionUtil.setParam("objetoRelatorio", this.retirada);
			SessionUtil.setParam("url", jasper);

		} else if (relatorioEscolhido.equals("DEVOLUÇÃO")) {
			List<Retirada> retiradas = new ArrayList<Retirada>();
			retiradas.add(retirada);

			File jasper = new File(FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/Relatorios/relatorioDevolucao.jasper"));

			SessionUtil.remove("objetoRelatorio");
			SessionUtil.setParam("objetoRelatorio", this.devolucao);
			SessionUtil.setParam("url", jasper);

		} 

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("imprimerelatorio.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void limparObjetos() {
		setEhCadastrado(false);
		setJaPesquisei(false);
		retirada = new Retirada();
		devolucao = new Devolucao();

	}

	@Override
	public void executar() {
		setEhCadastrado(false);
		setJaPesquisei(true);

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// Retorna um objeto completo de acordo com um ID
		ICommand command = mapCommands.get(operacao);
		Resultado resultado = new Resultado();
		if (relatorioEscolhido.equals("RETIRADA")) {
			resultado = command.execute(retirada);
			retirada = (Retirada) resultado.getEntidades().get(0);
		} else if (relatorioEscolhido.equals("DEVOLUÇÃO")) {
			resultado = command.execute(devolucao);
			devolucao = (Devolucao) resultado.getEntidades().get(0);
		}

		// Cliente foi encontrado
		if (resultado.getEntidades().get(0) != null) {
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		} else {
			JSFUtil.adicionarMensagemErro("ID não encontrado");
			retirada = new Retirada();
			devolucao = new Devolucao();
			return;
		}

	}

	@Override
	public void carregarListagem() {
		// TODO Auto-generated method stub
		
	}

}
