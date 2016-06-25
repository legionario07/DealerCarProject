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
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.conducao.Revisao;
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.Embreagem;
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.PastilhaFreio;
import br.com.dealercar.domain.produtosrevisao.Pneu;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;
import br.com.dealercar.domain.produtosrevisao.VelasIgnicao;
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
	private Map<String, String> hashProdutos = new HashMap<String, String>();
	private Date dataFinal;
	private List<EntidadeDominio> listaModelo = new ArrayList<EntidadeDominio>();
	private StringBuffer sqlCriterios = new StringBuffer();

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

		hashProdutos.put(Amortecedor.class.getName(), "Amortecedor");
		hashProdutos.put(CorreiaDentada.class.getName(), "Correia Dentada");
		hashProdutos.put(Embreagem.class.getName(), "Embreagem");
		hashProdutos.put(Farol.class.getName(), "Farol");
		hashProdutos.put(FiltroDeAr.class.getName(), "Filtro de Ar");
		hashProdutos.put(FiltroDeOleoMotor.class.getName(), "Filtro de Oleo Motor");
		hashProdutos.put(FluidoDeFreio.class.getName(), "Fluido de Freio");
		hashProdutos.put(PastilhaFreio.class.getName(), "Pastilha de Freio");
		hashProdutos.put(Pneu.class.getName(), "Pneu");
		hashProdutos.put(VelasIgnicao.class.getName(), "Velas Ignição");


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

		// carrega a SQL com os criterios antes chamar o DAO
		carregarSql();

		command = mapCommands.get("CONSULTAR");
		resultado = new Resultado();

		revisao.setDescricao(sqlCriterios.toString());
		
		resultado = command.execute(revisao);
		if (resultado != null) {
			lista = resultado.getEntidades();
		} else {
			lista = null;
		}
		

		for (EntidadeDominio e : lista) {
			if (e instanceof Revisao) {
				Revisao r = (Revisao) e;
				for (ProdutoRevisao p : r.getListaProdutoRevisao()){

					if (p.getQuantidade() > 0) {
						listaString.add(hashProdutos.get(p.getClass().getName()));
						if (produtosUtilizados.containsKey(hashProdutos.get(p.getClass().getName()))) {
							produtosUtilizados.put(hashProdutos.get(p.getClass().getName()),
									produtosUtilizados.get(hashProdutos.get(p.getClass().getName()))
											+ p.getQuantidade());
						} else {
							produtosUtilizados.put(hashProdutos.get(p.getClass().getName()), p.getQuantidade());
						}
					}
				}
			}
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
			revisao = new Revisao();
			JSFUtil.adicionarMensagemErro("Não há dados para ser Exibido");
			return;
		}

		// Não houve dados disponiveis para gerar o gráficos
		if (lista.isEmpty()||lista.size()==1) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparObjetos();
			return;
		}

		listaString.clear();
		listaString.add(revisao.getCarro().getModelo().getNome());

		graficoBarras = GraficoBarraBuilder.gerarGraficoBar(listaString, produtosUtilizados, null, "Modelo",
				"Quantidade");

		produtosUtilizados = new HashMap<String, Integer>();

	}

	/**
	 * Carrega a Sql com os criterios desejados
	 */
	private void carregarSql() {

		sqlCriterios.append("inner join carros on carros.placa = revisao.placa ");
		sqlCriterios.append("inner join modelos on carros.id_modelo = modelos.id ");
		sqlCriterios.append("where modelos.id = " + revisao.getCarro().getModelo().getId());
		sqlCriterios.append(" and (produto_revisao.qtde_amortecedor > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_correia_dentada > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_embreagem > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_farol > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_filtro_de_ar > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_filtro_de_oleo_motor > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_fluido_de_freio > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_pastilhas_freio > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_pneus > 0 or ");
		sqlCriterios.append("produto_revisao.qtde_velas_ignicao > 0) ");

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
		sqlCriterios = new StringBuffer();

	}

	@Override
	public void executar() {
		// TODO Auto-generated method stub

	}

}
