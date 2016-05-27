package br.com.dealercar.core.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.util.GraficoUtil;

/**
 * 
 * @author Paulinho
 *
 *         Classe responsavel por gerar os gráficos
 */
public class GraficoPizzaBuilder {

	/**
	 * Metodo que recebe uma lista de String, ordena e remove os duplicados
	 * 
	 * @param listaString
	 *            Desordenada e com duplicatas
	 * @return uma PieChartModel
	 */
	public static PieChartModel gerarGrafico(List<String> listaStringDesordenada) {

		PieChartModel graficoRetorno = new PieChartModel();

		// criando uma lista que ira transforrmar a collection em uma lista de
		// String
		List<String> listaDistintaOrdenada = new ArrayList<String>();
		
		listaDistintaOrdenada = GraficoUtil.ordernarListaDistinta(listaStringDesordenada);

		// HashMap que ira receber <Nome, qtde>
		HashMap<String, Integer> graficoHash = new HashMap<String, Integer>();

		Collections.sort(listaStringDesordenada);
		for (int i = 0; i < listaDistintaOrdenada.size(); i++) {
			int count = Collections.frequency(listaStringDesordenada, listaDistintaOrdenada.get(i));
			graficoHash.put(listaDistintaOrdenada.get(i), count);
		}

		// Grafico recebe os dados para ser exibido
		for (int i = 0; i < graficoHash.size(); i++) {
			graficoRetorno.set(listaDistintaOrdenada.get(i), graficoHash.get(listaDistintaOrdenada.get(i)));
		}

		return graficoRetorno;
	}


}
