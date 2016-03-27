package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;

import br.com.dealercar.core.builder.GraficoBarraBuilder;
import br.com.dealercar.core.dao.RevisaoDAO;
import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.core.negocio.Revisao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;

@ManagedBean(name = "MBProdutoRevisaoGrafico")
@ViewScoped
public class GraficoProdutoRevisaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel graficoBarras;
	private Revisao revisao = new Revisao();
	private String tipoDeDadosGraficos;
	private List<Revisao> lista = new ArrayList<Revisao>();
	private List<String> listaString = new ArrayList<String>();
	private Date dataFinal;
	private List<Modelo> listaModelo = new ArrayList<Modelo>();
	
	private Modelo modelo = new Modelo();
	
	private Amortecedor amortecedor = new Amortecedor();


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

	public BarChartModel getGraficoBarras() {
		return graficoBarras;
	}

	public void setGraficoBarras(BarChartModel graficoBarras) {
		this.graficoBarras = graficoBarras;
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

	public Amortecedor getAmortecedor() {
		return amortecedor;
	}

	public void setAmortecedor(Amortecedor amortecedor) {
		this.amortecedor = amortecedor;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Modelo> getListaModelo() {
		return listaModelo;
	}

	public void setListaModelo(List<Modelo> listaModelo) {
		this.listaModelo = listaModelo;
	}

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}
	
	public void carregarListagem(){
		listaModelo = new ModeloDAO().listarTodos();
	}

	
	public void gerarGraficoProduto(){
		
		if(revisao.getCarro().getModelo().getId() != 0){
			revisao.getCarro().setModelo(new ModeloDAO().pesquisarPorID(revisao.getCarro().getModelo()));
		}
		
		lista = new RevisaoDAO().pesquisarPorModelo(revisao);
		
		for(Revisao r : lista){
			listaString.add(r.getCarro().getPlaca());
		}
		
		
		
	}
	
	/**
	 * Chama o grafico para ser exibido na view
	 */
	public void exibirGraficoProduto() {

		// Gerou erro no ActionListener
		if (lista == null) {
			graficoBarras = null;
			dataFinal = null;
			amortecedor = new Amortecedor();
			revisao = new Revisao();
			JSFUtil.adicionarMensagemErro("Não há dados para ser Exibido");
			org.primefaces.context.RequestContext.getCurrentInstance()
			.update("pnlGrafico pnlComandos pnlTipoGrafico");
			return;
		}
		
		// Não houve dados disponiveis para gerar o gráfico
		if (lista.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparGrafico();
			org.primefaces.context.RequestContext.getCurrentInstance()
					.update("pnlGrafico pnlComandos pnlTipoGrafico");
			return;
		}

		graficoBarras = GraficoBarraBuilder.gerarGraficoBar(listaString, revisao, amortecedor);

	}
	
	
	
	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparGrafico() {

		graficoBarras = null;
		dataFinal = null;
		lista.clear();
		listaString.clear();
		revisao = new Revisao();
		amortecedor = new Amortecedor();
	}

}
