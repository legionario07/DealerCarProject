package br.com.dealercar.core.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.dealercar.core.util.GraficoUtil;

/**
 * 
 * @author Paulinho
 *
 *         Classe responsavel por gerar os gráficos
 */
public class GraficoLinhaBuilder {

	private static LineChartModel graficoRetorno = null;

	/**
	 * 
	 * @param dadosTotais - Um MAP<String, List<String, Integer>> com todos os dados 
	 * @param intervaloPesquisa - List<String>, geralmente meses para serem exibidos no eixoX
	 * @param listaDesordenada - Uma lista desordenada e repetida, que sera ordenada e virara 
	 * as Keys do dadosTotais
	 * @param eixoX - Nome do eixoX
	 * @param eixoY - Nome do eixoY
	 * @return
	 */
	public static LineChartModel gerarGraficoLine(Map<String, List<Map<String, Integer>>> dadosTotais,
			List<String> intervaloPesquisa, List<String> listaDesordenada, String eixoX, String eixoY) {

		graficoRetorno = new LineChartModel();
		LineChartSeries lineChartSeries = null;

		listaDesordenada = GraficoUtil.ordernarListaDistinta(listaDesordenada);

		// ira receber a maior quantidade entre todos o meses
		int maiorQuantidade = 0;

		// Cria as Linhas do Grafico
		for (int j = 0; j < listaDesordenada.size(); j++) {

			List<Map<String, Integer>> listaMaps = null;
			Map<String, Integer> map = null;

			lineChartSeries = new LineChartSeries();

			// coloca o nome da legenda
			lineChartSeries.setLabel(listaDesordenada.get(j));

			// verifica todos os meses
			for (int i = 0; i < intervaloPesquisa.size(); i++) {

				listaMaps = new ArrayList<Map<String, Integer>>();
				map = new HashMap<String, Integer>();

				listaMaps = dadosTotais.get(intervaloPesquisa.get(i));

				// para cada mes informado sera adicionado o valor encontrado
				for (int k = 0; k < listaMaps.size(); k++) {
					map = listaMaps.get(k);
					if (map.containsKey(listaDesordenada.get(j))) {
						if (map.containsKey(lineChartSeries.getLabel())) {
							lineChartSeries.set(intervaloPesquisa.get(i), map.get(listaDesordenada.get(j)));
						}

						// verifica o maior dado para formar o eixo y + 2
						if (maiorQuantidade < map.get(listaDesordenada.get(j)))
							maiorQuantidade = map.get(listaDesordenada.get(j));

						// s se não encontrou dado naquele mes recebera o valor
						// 0
					} else {
						lineChartSeries.set(intervaloPesquisa.get(i), 0);
					}
				}

			}

			graficoRetorno.addSeries(lineChartSeries);
		}

		graficoRetorno.getAxes().put(AxisType.X, new CategoryAxis(eixoX));
		Axis yAxis = graficoRetorno.getAxis(AxisType.Y);
		yAxis.setLabel(eixoY);
		yAxis.setMin(-1);
		yAxis.setMax(maiorQuantidade + 2);
		yAxis.setTickFormat("%i");
		graficoRetorno.setLegendPosition("e");
		graficoRetorno.setShowPointLabels(true);
		graficoRetorno.setAnimate(true);
		graficoRetorno.setZoom(true);

		return graficoRetorno;
	}

}
