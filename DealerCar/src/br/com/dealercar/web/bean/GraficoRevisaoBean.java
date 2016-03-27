package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.dao.RevisaoDAO;
import br.com.dealercar.core.negocio.Revisao;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;

@ManagedBean(name = "MBRevisaoGrafico")
@ViewScoped
public class GraficoRevisaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieRevisaoPersonalizado;
	private Revisao revisao = new Revisao();
	private String tipoDeDadosGraficos;
	private List<Revisao> lista = new ArrayList<Revisao>();
	private List<String> listaString = new ArrayList<String>();
	private Date dataFinal;

	public PieChartModel getPieRevisaoPersonalizado() {
		return pieRevisaoPersonalizado;
	}

	public void setPieRevisaoPersonalizado(PieChartModel pieRevisaoPersonalizado) {
		this.pieRevisaoPersonalizado = pieRevisaoPersonalizado;
	}

	public Revisao getRevisao() {
		return revisao;
	}

	public void setRevisao(Revisao revisao) {
		this.revisao = revisao;
	}

	public List<Revisao> getLista() {
		return lista;
	}

	public void setLista(List<Revisao> lista) {
		this.lista = lista;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public List<String> getListaString() {
		return listaString;
	}

	public void setListaString(List<String> listaString) {
		this.listaString = listaString;
	}

	public String getTipoDeDadosGraficos() {
		return tipoDeDadosGraficos;
	}

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}

	/**
	 * Gerando o gráfico Personalizado mais locadas
	 */
	public void gerarGraficoPersonalizado() {

		if (DataUtil.compararDatas(revisao.getDataRevisao(), dataFinal) != 1) {
			// colocando formato string para armazenar no banco de dados

			lista = null;
			JSFUtil.adicionarMensagemErro("A data para Final deve ser Maior que a data Inicio.");
			return;
		}

		if (DataUtil.compararDatas(dataFinal, Calendar.getInstance().getTime()) != 1) {

			lista = null;
			JSFUtil.adicionarMensagemErro("A data Final deve ser inferior ao dia Atual");

			return;
		}

		pieRevisaoPersonalizado = new PieChartModel();

		lista = new RevisaoDAO().pesquisarPorIntervaloData(revisao, dataFinal);
		// Verificando qual foi o dado relacionado ao grafico que o usuario
		// deseja verificar
		switch (tipoDeDadosGraficos) {
		case "Categoria":

			for (Revisao r : lista) {
				listaString.add(r.getCarro().getCategoria().getNome());
			}

			break;
		case "Modelo":

			for (Revisao r : lista) {
				listaString.add(r.getCarro().getModelo().getNome());
			}

			break;

		case "Carro":

			for (Revisao r : lista) {
				listaString.add(r.getCarro().getPlaca());
			}

			break;

		}

	}

	/**
	 * Chama o grafico para ser exibido na view
	 */
	public void exibirGraficoPersonalizado() {

		// Gerou erro no ActionListener
		if (lista == null) {
			pieRevisaoPersonalizado = null;
			dataFinal = null;
			revisao = new Revisao();
			org.primefaces.context.RequestContext.getCurrentInstance()
			.update("pnlGrafico pnlComandos pnlTipoGrafico validadores");
			return;
		}
		
		// Não houve dados disponiveis para gerar o gráfico
		if (lista.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparGrafico();
			org.primefaces.context.RequestContext.getCurrentInstance()
					.update("pnlGrafico pnlComandos pnlTipoGrafico validadores");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		pieRevisaoPersonalizado = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRevisaoPersonalizado.setTitle(tipoDeDadosGraficos + " - De " + sdf.format(revisao.getDataRevisao())
				+ " à " + sdf.format(dataFinal));
		pieRevisaoPersonalizado.setLegendPosition("w");
		pieRevisaoPersonalizado.setShowDataLabels(true);

	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparGrafico() {

		pieRevisaoPersonalizado = null;
		dataFinal = null;
		lista.clear();
		listaString.clear();
		revisao = new Revisao();
	}

}
