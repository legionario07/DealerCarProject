package br.com.dealercar.web.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dealercar.core.util.SessionUtil;

@ManagedBean(name = "MBRelatorioSintetico")
@ViewScoped
public class RelatorioSinteticosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String relatorioEscolhido;
	private Map<String, File> mapRelatorio = new HashMap<String, File>();

	public RelatorioSinteticosBean() {

		mapRelatorio.put("RETIRADA_SINTETICO", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemRetiradas.jasper")));
		mapRelatorio.put("DEVOLUÇÃO_SINTETICO", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemDevolucao.jasper")));
		mapRelatorio.put("RESERVA_SINTETICO", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemReservas.jasper")));
		mapRelatorio.put("RETIRADAS_FROM_RESERVAS", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemRetiradasFromReservas.jasper")));
		mapRelatorio.put("RETIRADA_MODELOS_MAIS_LOCADOS", new File(FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/Relatorios/listagemModelosMaisLocados.jasper")));
		mapRelatorio.put("CATEGORIAS_MAIS_LOCADAS", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemCategoriasMaisLocadas.jasper")));
		mapRelatorio.put("MODELOS_COM_MAIS_REVISOES", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemModelosComMaisRevisoes.jasper")));
		mapRelatorio.put("REVISOES_SINTETICO", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemRevisoes.jasper")));
		mapRelatorio.put("RETIRADAS_ATIVAS", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemRetiradasAtivas.jasper")));
		mapRelatorio.put("CLIENTES_SINTETICO", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemClientes.jasper")));
		mapRelatorio.put("CLIENTES_COM_MAIS_RETIRADAS", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemClientesComMaisLocacoes.jasper")));
		mapRelatorio.put("FUNCIONARIOS_SINTETICO", new File(FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/Relatorios/listagemFuncionarios.jasper")));

		

	}

	public String getRelatorioEscolhido() {
		return relatorioEscolhido;
	}

	public Map<String, File> getMapRelatorio() {
		return mapRelatorio;
	}

	public void setMapRelatorio(Map<String, File> mapRelatorio) {
		this.mapRelatorio = mapRelatorio;
	}

	public void setRelatorioEscolhido(String relatorioEscolhido) {
		this.relatorioEscolhido = relatorioEscolhido;
	}

	/**
	 * Chama o Exporter da Relatório
	 */
	public void chamarExporter() {

		relatorioEscolhido = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("relatorioEscolhido");

		File jasper = mapRelatorio.get(relatorioEscolhido);
		SessionUtil.remove("objetoRelatorio");
		SessionUtil.setParam("objetoRelatorio", relatorioEscolhido);
		SessionUtil.setParam("url", jasper);

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("imprimerelatorio.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
