package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.LineChartModel;

import br.com.dealercar.core.builder.GraficoLinhaBuilder;
import br.com.dealercar.core.dao.RetiradaDAO;
import br.com.dealercar.core.dao.RevisaoDAO;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.domain.conducao.Revisao;

@ManagedBean(name = "MBRetiradaGraficoAnual")
@ViewScoped
public class GraficoAnualLinhaBean extends AbstractGraficoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineChartModel lineModel;
	private Retirada retirada = new Retirada();
	private Revisao revisao = new Revisao();
	private String tipoDeDadosGraficos;
	private List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	private List<String> listaString = new ArrayList<String>();
	private Map<String, Integer> dados = new HashMap<String, Integer>();
	private List<String> seriesPesquisada = new ArrayList<String>();
	private Map<String, List<Map<String, Integer>>> dadosTotais = new HashMap<String, List<Map<String, Integer>>>();

	public GraficoAnualLinhaBean() {

	}

	public LineChartModel getPieRetiradaPersonalizado() {
		return lineModel;
	}

	public void setPieRetiradaPersonalizado(LineChartModel pieRetiradaPersonalizado) {
		this.lineModel = pieRetiradaPersonalizado;
	}

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public List<EntidadeDominio> getLista() {
		return lista;
	}

	public void setLista(List<EntidadeDominio> lista) {
		this.lista = lista;
	}

	public List<String> getSeriesPesquisada() {
		return seriesPesquisada;
	}

	public void setSeriesPesquisada(List<String> seriesPesquisada) {
		this.seriesPesquisada = seriesPesquisada;
	}

	public List<String> getListaString() {
		return listaString;
	}

	public void setListaString(List<String> listaString) {
		this.listaString = listaString;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public Map<String, Integer> getDados() {
		return dados;
	}

	public void setDados(Map<String, Integer> dados) {
		this.dados = dados;
	}

	public String getTipoDeDadosGraficos() {
		return tipoDeDadosGraficos;
	}

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}

	public Revisao getRevisao() {
		return revisao;
	}

	public void setRevisao(Revisao revisao) {
		this.revisao = revisao;
	}

	/**
	 * Gerando o gráfico Personalizado mais locadas
	 */
	public void gerarGraficoPersonalizado(String tipoDeDadosGraficos) {

		tipoDados = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tiposDados");
		
		//Verifica se foi escolhido o ano
		if(ano==null){
			JSFUtil.adicionarMensagemErro("Escolha o ano");
			return;
		}

		this.tipoDeDadosGraficos = tipoDeDadosGraficos;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicio = null;
		Date dataFinal = null;
		List<Map<String, Integer>> listaMaps = null;

		for (int i = 0; i < meses.size(); i++) {

			listaMaps = new ArrayList<Map<String, Integer>>();

			try {
				// Recebe a data ja armazenda e mais o ano escolhido na view
				dataInicio = sdf.parse(mesesInicio.get(meses.get(i)) + ano);
				dataFinal = sdf.parse(mesesFim.get(meses.get(i)) + ano);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// verifica qual o tipo de Dados solicitado
			switch (tipoDados) {

			case "REVISÃO":

				revisao.setDataRevisao(dataInicio);

				// recebe as retiradas relacionado ao mes pesquisado
				lista = new RevisaoDAO().pesquisarPorIntervaloData(revisao, dataFinal);

				for (EntidadeDominio r : lista) {
					revisao = new Revisao();
					revisao = (Revisao) r;
					listaString.add(retornarStringPesquisada());
					seriesPesquisada.add(retornarStringPesquisada());
				}

			case "RETIRADA":
				
				retirada.setDataRetirada(dataInicio);
				retirada.setDataDevolucao(dataFinal);
				// recebe as retiradas relacionado ao mes pesquisado
				lista = new RetiradaDAO().pesquisarPorIntervaloData(retirada);
				
				for (EntidadeDominio r : lista) {
					retirada = new Retirada();
					retirada = (Retirada) r;
					listaString.add(retornarStringPesquisada());
					seriesPesquisada.add(retornarStringPesquisada());
				}

			}


			for (String s : listaString) {
				if (!dados.containsKey(s)) {
					dados.put(s, 1);
				} else {
					dados.put(s, dados.get(s) + 1);
				}

			}
			

			listaMaps.add(dados);

			// Map recebe o mes e quantidade daquele mes
			dadosTotais.put(meses.get(i), listaMaps);
			// limpar a lista de String para receber novos dados
			listaString.clear();
			dados = new HashMap<String, Integer>();

		}

		lineModel = new LineChartModel();

	}

	public Map<String, List<Map<String, Integer>>> getDadosTotais() {
		return dadosTotais;
	}

	public void setDadosTotais(Map<String, List<Map<String, Integer>>> dadosTotais) {
		this.dadosTotais = dadosTotais;
	}

	/**
	 * Chama o grafico para ser exibido na view
	 */
	public void exibirGraficoPersonalizado() {

		boolean temValor = false;

		// verifica se algum mes retornou resultado
		for (int i = 0; i < meses.size(); i++) {
			if (dadosTotais.get(meses.get(i)).size() > 0) {
				temValor = true;
			}
		}
		// Não houve dados disponiveis para gerar o gráfico
		if (!temValor) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparGrafico();
			org.primefaces.context.RequestContext.getCurrentInstance().update("pnlGrafico pnlComandos pnlTipoGrafico"
					+ "pnlGraficoLinha pnlEscolha outDataPersonalizada outEscolha");
			return;
		}

		lineModel = GraficoLinhaBuilder.gerarGraficoLine(dadosTotais, meses, seriesPesquisada, tipoDados,
				"Quantidade");
		
		lineModel.setTitle("Grafico de Linhas Anual");

	}

	private String retornarStringPesquisada() {
		// Verificando qual foi o dado relacionado ao grafico que o usuario
		// deseja verificar
		
		switch (tipoDeDadosGraficos) {
		case "Categoria":
			if (tipoDados.equals("RETIRADA"))
				return retirada.getCarro().getCategoria().getNome();
			else if (tipoDados.equals("REVISÃO"))
				return revisao.getCarro().getCategoria().getNome();

		case "Modelo":
			if (tipoDados.equals("RETIRADA"))
				return retirada.getCarro().getModelo().getNome();
			else if (tipoDados.equals("REVISÃO"))
				return revisao.getCarro().getModelo().getNome();

		case "Carro":
			if (tipoDados.equals("RETIRADA"))
				return retirada.getCarro().getPlaca();
			else if (tipoDados.equals("REVISÃO"))
				return revisao.getCarro().getPlaca();

		}

		return null;
	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	@Override
	public void limparGrafico() {

		lineModel = null;
		lista.clear();
		listaString.clear();
		retirada = new Retirada();
		dados.clear();
		tipoDeDadosGraficos = null;
		ano = null;
		tipoDados = null;
		
	}

	@Override
	public void carregarListagem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void limparObjetos() {
		// TODO Auto-generated method stub

	}

}
