package br.com.dealercar.core.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 * 
 * @author Paulinho
 *
 *         Classe responsavel por gerar os gráficos
 */
public class GraficoBarraBuilder {

	// HashMap que ira receber <Nome, qtde>
	private static HashMap<String, Integer> graficoHash = null;

	private static BarChartModel graficoRetorno = null;

	/**
	 * 
	 * @param listaStringDesordenada
	 * @param produtosEscolhidos
	 * @param produtos
	 * @param eixoX
	 * @param eixoY
	 * @return
	 */
	public static BarChartModel gerarGraficoBar(List<String> listaStringDesordenada,
			Map<String, Integer> produtosEscolhidos, List<String> produtos, String eixoX, String eixoY) {

		graficoHash = new HashMap<String, Integer>();
		graficoRetorno = new BarChartModel();

		// Criando uma collections com apenas os distintos
		Set<String> listaDistintos = new HashSet<String>(listaStringDesordenada);

		// criando uma lista que ira transforrmar a collection em uma lista de
		// String
		List<String> listaDistintaOrdenada = new ArrayList<String>();

		// tranforma a collection set em uma lista de String para ser ordenada
		for (String s : listaDistintos) {
			listaDistintaOrdenada.add(s);
		}

		// classifica a lista por ordem alfabetica
		Collections.sort(listaDistintaOrdenada);

		Collections.sort(listaStringDesordenada);
		for (int i = 0; i < listaDistintaOrdenada.size(); i++) {
			int count = Collections.frequency(listaStringDesordenada, listaDistintaOrdenada.get(i));
			graficoHash.put(listaDistintaOrdenada.get(i), count);
		}

		gerarChartSeries(produtosEscolhidos, produtos);

		graficoRetorno.setShowPointLabels(true);
		graficoRetorno.setZoom(true);
		graficoRetorno.setLegendPosition("ne");
		Axis xAxis = graficoRetorno.getAxis(AxisType.X);
		xAxis.setLabel(eixoX);

		Axis yAxis = graficoRetorno.getAxis(AxisType.Y);
		yAxis.setTickFormat("%i");
		yAxis.setLabel(eixoY);
		yAxis.setMin(0);
		// setando o valor maximo do eixo y
		if (produtos != null) {
			yAxis.setMax(Collections.max(graficoHash.values()) + 2);
		}

		return graficoRetorno;
	}

	private static void gerarChartSeries(Map<String, Integer> produtosEscolhidos, List<String> produtos) {

		Set<String> chaves = graficoHash.keySet();
		ChartSeries chartSeries = null;

		if (produtosEscolhidos != null) {
			Set<String> produtosString = produtosEscolhidos.keySet();
			for (String s : produtosString) {
				chartSeries = new ChartSeries();
				for (String chave : chaves) {
					chartSeries.set(chave, produtosEscolhidos.get(s));
				}
				chartSeries.setLabel(s);

				graficoRetorno.addSeries(chartSeries);
			}

		} else {
			for (String s : produtos) {

				chartSeries = new ChartSeries();
				for (String chave : chaves) {
					chartSeries.set(chave, graficoHash.get(chave));
				}

				chartSeries.setLabel(s);

				graficoRetorno.addSeries(chartSeries);
			}
		}
	}

}
