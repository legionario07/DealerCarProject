package br.com.dealercar.core.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.dealercar.core.negocio.Revisao;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;

/**
 * 
 * @author Paulinho
 *
 *         Classe responsavel por gerar os gráficos
 */
public class GraficoBarraBuilder {


	/**
	 * Metodo que recebe uma lista de String, ordena e remove os duplicados
	 * 
	 * @param listaString
	 *            Desordenada e com duplicatas
	 * @return uma BarChartModel
	 */
	public static BarChartModel gerarGraficoBar(List<String> listaStringDesordenada, Revisao revisao,  ProdutoRevisao produto) {

		BarChartModel graficoRetorno = new BarChartModel();

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

		Set<String> chaves = graficoHash.keySet();
		ChartSeries chartSeries = new ChartSeries();
		
		for(String chave : chaves){
			chartSeries.set(chave, graficoHash.get(chave));
		}
		

		chartSeries.setLabel(produto.getClass().getSimpleName());

		// Grafico recebe os dados para ser exibido
		/*
		for (int j = 0; j < listaDistintaOrdenada.size(); j++) {
			for (int i = 0; i < graficoHash.size(); i++) {
				chartSeries.set(listaDistintaOrdenada.get(i), graficoHash.get(listaDistintaOrdenada.get(i)));
			}
		}*/
		graficoRetorno.addSeries(chartSeries);

		graficoRetorno.setShowPointLabels(true);
		graficoRetorno.setZoom(true);
		graficoRetorno.setLegendPosition("ne");
		Axis xAxis = graficoRetorno.getAxis(AxisType.X);
		xAxis.setLabel(revisao.getCarro().getModelo().getNome());
		
		Axis yAxis = graficoRetorno.getAxis(AxisType.Y);
		yAxis.setTickFormat("%i");
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		//setando o valor maximo do eixo y
		yAxis.setMax(Collections.max(graficoHash.values())+2);
		
		
		
		return graficoRetorno;
	}
	


}
