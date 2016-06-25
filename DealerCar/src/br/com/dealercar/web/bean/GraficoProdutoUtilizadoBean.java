package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

@ManagedBean(name = "MBProdutoUtilizadoGrafico")
@ViewScoped
public class GraficoProdutoUtilizadoBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BarChartModel graficoBarras;
	private Revisao revisao = new Revisao();
	private String tipoDeDadosGraficos;
	private List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	private List<String> listaString = new ArrayList<String>();
	private List<String> listaStringProdutos = new ArrayList<String>();
	private Map<String, Integer> produtosUtilizados = new HashMap<String, Integer>();
	private Map<String, String> criterio = new HashMap<String, String>();
	private Map<String, String> hashProdutos = new HashMap<String, String>();
	private String produtoEscolhido;

	private Modelo modelo = new Modelo();


	public GraficoProdutoUtilizadoBean() {

		listaStringProdutos.add("Amortecedor");
		listaStringProdutos.add("Correia Dentada");
		listaStringProdutos.add("Embreagem");
		listaStringProdutos.add("Farol");
		listaStringProdutos.add("Filtro de Ar");
		listaStringProdutos.add("Filtro de Oleo Motor");
		listaStringProdutos.add("Fluido de Freio");
		listaStringProdutos.add("Pastilha de Freio");
		listaStringProdutos.add("Pneu");
		listaStringProdutos.add("Velas Ignição");

		hashProdutos.put("Amortecedor", Amortecedor.class.getName());
		hashProdutos.put("Correia Dentada", CorreiaDentada.class.getName());
		hashProdutos.put("Embreagem", Embreagem.class.getName());
		hashProdutos.put("Farol", Farol.class.getName());
		hashProdutos.put("Filtro de Ar", FiltroDeAr.class.getName());
		hashProdutos.put("Filtro de Oleo Motor", FiltroDeOleoMotor.class.getName());
		hashProdutos.put("Fluido de Freio", FluidoDeFreio.class.getName());
		hashProdutos.put("Pastilha de Freio", PastilhaFreio.class.getName());
		hashProdutos.put("Pneu", Pneu.class.getName());
		hashProdutos.put("Velas Ignição", VelasIgnicao.class.getName());

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

	public List<String> getListaStringProdutos() {
		return listaStringProdutos;
	}

	public void setListaStringProdutos(List<String> listaStringProdutos) {
		this.listaStringProdutos = listaStringProdutos;
	}

	public String getTipoDeDadosGraficos() {
		return tipoDeDadosGraficos;
	}

	public String getProdutoEscolhido() {
		return produtoEscolhido;
	}

	public void setProdutoEscolhido(String produtoEscolhido) {
		this.produtoEscolhido = produtoEscolhido;
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

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}

	public void carregarListagem() {

		criterio.put("Amortecedor", "where produto_revisao.qtde_amortecedor > 0");
		criterio.put("Correia Dentada", "where produto_revisao.qtde_correia_dentada > 0");
		criterio.put("Embreagem", "where produto_revisao.qtde_embreagem > 0 ");
		criterio.put("Farol", "where produto_revisao.qtde_farol > 0");
		criterio.put("Filtro de Ar", "where produto_revisao.qtde_filtro_de_ar > 0");
		criterio.put("Filtro de Oleo Motor", "where produto_revisao.qtde_filtro_de_oleo_motor > 0");
		criterio.put("Fluido de Freio", "where produto_revisao.qtde_fluido_de_freio > 0");
		criterio.put("Pastilha de Freio", "where produto_revisao.qtde_pastilhas_freio > 0 ");
		criterio.put("Pneu", "where produto_revisao.qtde_pneus > 0 ");
		criterio.put("Velas Ignição", "where produto_revisao.qtde_velas_ignicao > 0");

	}

	public void gerarGraficoProduto() {

		// Recebe os dados de acordo com o criterio buscado
		ICommand command = mapCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();

		revisao.setDescricao(criterio.get(produtoEscolhido));
		
		resultado = command.execute(revisao);
		if (resultado != null) {
			lista = resultado.getEntidades();
		} else {
			lista = null;
		}

		for (EntidadeDominio r : lista) {
			listaString.add(((Revisao) r).getCarro().getModelo().getNome());
		}

		for (EntidadeDominio e : lista) {
			if (e instanceof Revisao) {
				Revisao r = (Revisao) e;
				for (ProdutoRevisao p : r.getListaProdutoRevisao()) {
					if (p.getClass().getName() == hashProdutos.get(produtoEscolhido)) {
						if (p.getQuantidade() > 0) {
							if (produtosUtilizados.containsKey(((Revisao) r).getCarro().getModelo().getNome())) {
								produtosUtilizados.put(((Revisao) r).getCarro().getModelo().getNome(),
										produtosUtilizados.get(((Revisao) r).getCarro().getModelo().getNome())
												+ p.getQuantidade());
							} else {
								produtosUtilizados.put(((Revisao) r).getCarro().getModelo().getNome(),
										p.getQuantidade());
							}
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
			produtoEscolhido = null;
			produtosUtilizados.clear();
			revisao = new Revisao();
			org.primefaces.context.RequestContext.getCurrentInstance().update("pnlGrafico pnlComandos pnlTipoGrafico");
			return;
		}

		// Não houve dados disponiveis para gerar o gráfico
		if (lista.isEmpty()||lista.size()==1) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparObjetos();
			return;
		}

		List<String> produtos = new ArrayList<String>();
		produtos.add(produtoEscolhido);

		// Criando uma collections com apenas os distintos
		Set<String> chaves = produtosUtilizados.keySet();
		listaString.clear();
		for (String s : chaves) {
			for (int i = 0; i < produtosUtilizados.get(s); i++) {
				listaString.add(s);
			}
		}

		graficoBarras = GraficoBarraBuilder.gerarGraficoBar(listaString, null, produtos, "Modelos",
				"Quantidade Produto");

	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparObjetos() {

		graficoBarras = null;
		produtoEscolhido = null;
		lista.clear();
		listaString.clear();
		revisao = new Revisao();
		produtosUtilizados.clear();

	}

	@Override
	public void executar() {
		// TODO Auto-generated method stub

	}

}
