package br.com.dealercar.core.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;

import br.com.dealercar.core.negocio.Reserva;

/**
 * 
 * @author Paulinho
 *
 *         Classe responsavel por gerar os gráficos
 */
public class GraficoLinhaBuilder {


	/**
	 * Metodo que recebe uma lista de String, ordena e remove os duplicados
	 * 
	 * @param listaString
	 *            Desordenada e com duplicatas
	 * @return uma LineChartModel
	 */
	public static LineChartModel gerarGraficoLine(List<String> listaStringDesordenada, Reserva reserva) {

		LineChartModel graficoRetorno = new LineChartModel();

		// Criando uma collections com apenas os distintos
		Set<String> reservasDistintas = new HashSet<String>(listaStringDesordenada);

		// criando uma lista que ira transforrmar a collection em uma lista de
		// String
		List<String> listaDistintaOrdenada = new ArrayList<String>();

		// tranforma a collection set em uma lista de String para ser ordenada
		for (String s : reservasDistintas) {
			listaDistintaOrdenada.add(s);
		}

		// classifica a lista por ordem alfabetica
		Collections.sort(listaDistintaOrdenada);

		// HashMap que ira receber <Nome, qtde>
		HashMap<String, Integer> graficoHash = new HashMap<String, Integer>();

		Collections.sort(listaStringDesordenada);
		for (int i = 0; i < listaDistintaOrdenada.size(); i++) {
			int count = Collections.frequency(listaStringDesordenada, listaDistintaOrdenada.get(i));
			graficoHash.put(listaDistintaOrdenada.get(i), count);
		}

		ChartSeries chartSeries = null;
	/*	for (int i = 0; i < listaDistintaOrdenada.size(); i++) {
			chartSeries = new ChartSeries();
			int count = Collections.frequency(listaStringDesordenada, listaDistintaOrdenada.get(i));

			// graficoRetorno.set(listaDistintaOrdenada.get(i),graficoHash.get(listaDistintaOrdenada.get(i)));
		}
		*/

		// Grafico recebe os dados para ser exibido
		for (int j = 0; j < listaDistintaOrdenada.size(); j++) {
			chartSeries = new ChartSeries();
			for (int i = 0; i < graficoHash.size(); i++) {
				chartSeries.set(listaDistintaOrdenada.get(i), graficoHash.get(listaDistintaOrdenada.get(i)));
			}
			graficoRetorno.addSeries(chartSeries);
		}

		
		graficoRetorno.setShowPointLabels(true);
		graficoRetorno.setZoom(true);
		graficoRetorno.setLegendPosition("ne");
		Axis yAxis = graficoRetorno.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		axis.setMin(reserva.getDataCadastroReserva());
		axis.setMax(reserva.getDataFim());
		axis.setTickFormat("%b %#d, %y");
		graficoRetorno.getAxes().put(AxisType.X, axis);
		
		
		
		return graficoRetorno;
	}

}
