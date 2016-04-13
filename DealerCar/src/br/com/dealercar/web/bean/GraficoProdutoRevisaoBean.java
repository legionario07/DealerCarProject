package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.builder.GraficoBarraBuilder;
import br.com.dealercar.core.dao.RevisaoDAO;
import br.com.dealercar.core.negocio.Revisao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBProdutoRevisaoGrafico")
@ViewScoped
public class GraficoProdutoRevisaoBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel graficoBarras;
	private Revisao revisao = new Revisao();
	private String tipoDeDadosGraficos;
	private List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	private List<String> listaString = new ArrayList<String>();
	private List<String> produtos = new ArrayList<String>();
	private Map<String, Integer> produtosUtilizados = new HashMap<String, Integer>();
	private Date dataFinal;
	private List<EntidadeDominio> listaModelo = new ArrayList<EntidadeDominio>();
	private Map<String, String> criterio = new HashMap<String, String>();

	private Modelo modelo = new Modelo();

	public GraficoProdutoRevisaoBean() {

		produtos.add("Amortecedor");
		produtos.add("Correia Dentada");
		produtos.add("Embreagem");
		produtos.add("Farol");
		produtos.add("Filtro de Ar");
		produtos.add("Filtro de Oleo Motor");
		produtos.add("Fluido de Freio");
		produtos.add("Pastilha de Freio");
		produtos.add("Pneu");
		produtos.add("Velas Ignição");

		criterio.put("Amortecedor", "where produto_revisao.qtde_amortecedor > 0 and carros.id_modelo = ");
		criterio.put("Correia Dentada", "where produto_revisao.qtde_correia_dentada > 0 and carros.id_modelo = ");
		criterio.put("Embreagem", "where produto_revisao.qtde_embreagem > 0 and carros.id_modelo = ");
		criterio.put("Farol", "where produto_revisao.qtde_farol > 0 and carros.id_modelo = ");
		criterio.put("Filtro de Ar", "where produto_revisao.qtde_filtro_de_ar > 0 and carros.id_modelo = ");
		criterio.put("Filtro de Oleo Motor",
				"where produto_revisao.qtde_filtro_de_oleo_motor > 0 and carros.id_modelo = ");
		criterio.put("Fluido de Freio", "where produto_revisao.qtde_fluido_de_freio > 0 and carros.id_modelo = ");
		criterio.put("Pastilha de Freio", "where produto_revisao.qtde_pastilhas_freio > 0 and carros.id_modelo = ");
		criterio.put("Pneu", "where produto_revisao.qtde_pneus > 0 and carros.id_modelo = ");
		criterio.put("Velas Ignição", "where produto_revisao.qtde_velas_ignicao > 0 and carros.id_modelo = ");

	}

	public Revisao getRevisao() {
		return revisao;
	}

	public void setRevisao(Revisao revisao) {
		this.revisao = revisao;
	}

	public List<EntidadeDominio> getLista() {
		return lista;
	}

	public void setLista(List<EntidadeDominio> lista) {
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

	public List<String> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}

	public Map<String, String> getCriterio() {
		return criterio;
	}

	public void setCriterio(Map<String, String> criterio) {
		this.criterio = criterio;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<EntidadeDominio> getListaModelo() {
		return listaModelo;
	}

	public void setListaModelo(List<EntidadeDominio> listaModelo) {
		this.listaModelo = listaModelo;
	}

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}

	public void carregarListagem() {

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();
		resultado = command.execute(new Modelo());

		if (resultado != null) {
			listaModelo = resultado.getEntidades();
		}

	}

	public void gerarGraficoProduto() {

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();
		resultado = command.execute(revisao.getCarro());

		if (revisao.getCarro().getModelo().getId() != 0) {
			resultado = command.execute(revisao.getCarro().getModelo());
			if (resultado.getEntidades().get(0) != null) {
				revisao.getCarro().setModelo((Modelo) resultado.getEntidades().get(0));
			}
		}
		String inner = "inner join carros on carros.placa = revisao.placa ";
		
		for (String s : produtos) {
			lista = new RevisaoDAO()
					.pesquisarPorProdutoUtilizado(inner + criterio.get(s) + revisao.getCarro().getModelo().getId());
			if (lista.size() > 0) {
				produtosUtilizados.put(s, lista.size());
				for (EntidadeDominio r : lista) {
					listaString.add(((Revisao) r).getCarro().getPlaca());
				}
			}
		}

	}

	/**
	 * Chama o grafico para ser exibido na view
	 */
	public void exibirGraficoProduto() {

		// Gerou erro no ActionListener
		if (lista == null && listaString.size() == 0) {
			graficoBarras = null;
			dataFinal = null;
			revisao = new Revisao();
			JSFUtil.adicionarMensagemErro("Não há dados para ser Exibido");
			org.primefaces.context.RequestContext.getCurrentInstance().update("pnlGrafico pnlComandos pnlTipoGrafico");
			return;
		}

		// Não houve dados disponiveis para gerar o gráfico
		if (lista.isEmpty() && listaString.size() == 0) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparObjetos();
			org.primefaces.context.RequestContext.getCurrentInstance().update("pnlGrafico pnlComandos pnlTipoGrafico");
			return;
		}

		
		graficoBarras = GraficoBarraBuilder.gerarGraficoBar(listaString, produtosUtilizados, null,  "Carro", "Quantidade");
		
		produtosUtilizados = new HashMap<String, Integer>();

	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparObjetos() {

		graficoBarras = null;
		dataFinal = null;
		lista.clear();
		listaString.clear();
		revisao = new Revisao();
		produtosUtilizados = new HashMap<String, Integer>();
		
	}

	@Override
	public void executar() {
		// TODO Auto-generated method stub

	}


}
