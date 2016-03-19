package br.com.dealercar.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.builder.GraficoPizzaBuilder;
import br.com.dealercar.dao.RetiradaDAO;
import br.com.dealercar.domain.Retirada;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBRetiradaGrafico")
@ViewScoped
public class GraficoRetiradaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieRetiradaPersonalizado;
	private Retirada retirada = new Retirada();
	private String tipoDeDadosGraficos;
	private List<Retirada> lista = new ArrayList<Retirada>();
	private List<String> listaString = new ArrayList<String>();

	public PieChartModel getPieRetiradaPersonalizado() {
		return pieRetiradaPersonalizado;
	}

	public void setPieRetiradaPersonalizado(PieChartModel pieRetiradaPersonalizado) {
		this.pieRetiradaPersonalizado = pieRetiradaPersonalizado;
	}

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public List<Retirada> getLista() {
		return lista;
	}

	public void setLista(List<Retirada> lista) {
		this.lista = lista;
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

		if (DataUtil.compararDatas(retirada.getDataRetirada(), retirada.getDataDevolucao()) != 1) {
			// colocando formato string para armazenar no banco de dados

			lista = null;
			JSFUtil.adicionarMensagemErro("A data para Final deve ser Maior que a data Inicio.");
			return;
		}

		if (DataUtil.compararDatas(retirada.getDataDevolucao(), Calendar.getInstance().getTime()) != 1) {

			lista = null;
			JSFUtil.adicionarMensagemErro("A data Final deve ser inferior ao dia Atual");

			return;
		}

		pieRetiradaPersonalizado = new PieChartModel();

		lista = new RetiradaDAO().pesquisarPorIntervaloData(retirada);
		// Verificando qual foi o dado relacionado ao grafico que o usuario
		// deseja verificar
		switch (tipoDeDadosGraficos) {
		case "Categoria":

			for (Retirada r : lista) {
				listaString.add(r.getCarro().getCategoria().getNome());
			}

			break;
		case "Modelo":

			for (Retirada r : lista) {
				listaString.add(r.getCarro().getModelo().getNome());
			}

			break;

		case "Carro":

			for (Retirada r : lista) {
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
			pieRetiradaPersonalizado = null;
			retirada = new Retirada();
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

		pieRetiradaPersonalizado = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaPersonalizado.setTitle(tipoDeDadosGraficos + " - De " + sdf.format(retirada.getDataRetirada())
				+ " à " + sdf.format(retirada.getDataDevolucao()));
		pieRetiradaPersonalizado.setLegendPosition("w");
		pieRetiradaPersonalizado.setShowDataLabels(true);

	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparGrafico() {

		pieRetiradaPersonalizado = null;
		lista.clear();
		listaString.clear();
		retirada = new Retirada();
	}

}
