package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBRetiradaGrafico")
@ViewScoped
public class GraficoRetiradaBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieRetiradaPersonalizado;
	private Retirada retirada = new Retirada();
	private String tipoDeDadosGraficos;
	private List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	private List<String> listaString = new ArrayList<String>();
	private String tipoDeAnalise;

	public PieChartModel getPieRetiradaPersonalizado() {
		return pieRetiradaPersonalizado;
	}

	public void setPieRetiradaPersonalizado(PieChartModel pieRetiradaPersonalizado) {
		this.pieRetiradaPersonalizado = pieRetiradaPersonalizado;
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

	public List<String> getListaString() {
		return listaString;
	}

	public void setListaString(List<String> listaString) {
		this.listaString = listaString;
	}

	public String getTipoDeAnalise() {
		return tipoDeAnalise;
	}

	public void setTipoDeAnalise(String tipoDeAnalise) {
		this.tipoDeAnalise = tipoDeAnalise;
	}

	public String getTipoDeDadosGraficos() {
		return tipoDeDadosGraficos;
	}

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}

	/**
	 * Gerando o gráfico Personalizado mais locadas
	 */
	public void gerarGraficoPersonalizado() {

		if (DataUtil.compararDatas(retirada.getDataRetirada(), retirada.getDataDevolucao()) != 1) {
			// colocando formato string para armazenar no banco de dados

			lista = null;
			JSFUtil.adicionarMensagemErro("A data para Final deve ser Maior que a data Inicio.");
			return;
		}

		if (DataUtil.compararDatas(retirada.getDataDevolucao(), Calendar.getInstance().getTime()) != 1) {

			lista = null;
			JSFUtil.adicionarMensagemErro("A data Final deve ser inferior ao dia Atual");

			return;
		}

		// Recebe os dados de acordo com o criterio buscado
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(retirada);
		if (resultado != null) {
			lista = resultado.getEntidades();
		} else {
			lista = null;
		}

		pieRetiradaPersonalizado = new PieChartModel();

		// Verificando qual foi o dado relacionado ao grafico que o usuario
		// deseja verificar
		switch (tipoDeDadosGraficos) {
		case "Categoria":

			for (EntidadeDominio r : lista) {
				listaString.add(((Retirada) r).getCarro().getCategoria().getNome());
			}

			break;
		case "Modelo":

			for (EntidadeDominio r : lista) {
				listaString.add(((Retirada) r).getCarro().getModelo().getNome());
			}

			break;

		case "Carro":

			for (EntidadeDominio r : lista) {
				listaString.add(((Retirada) r).getCarro().getPlaca());
			}

			break;

		}

	}

	/**
	 * Chama o grafico para ser exibido na view
	 */
	public void exibirGraficoPersonalizado() {

		// Gerou erro no ActionListener
		if (lista == null) {
			pieRetiradaPersonalizado = null;
			retirada = new Retirada();
			org.primefaces.context.RequestContext.getCurrentInstance().update("pnlGrafico pnlComandos pnlTipoGrafico");
			return;
		}

		// Não houve dados disponiveis para gerar o gráfico
		if (lista.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparGrafico();
			org.primefaces.context.RequestContext.getCurrentInstance().update("pnlGrafico pnlComandos pnlTipoGrafico");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		pieRetiradaPersonalizado = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaPersonalizado.setTitle(tipoDeDadosGraficos + " - De " + sdf.format(retirada.getDataRetirada())
				+ " à " + sdf.format(retirada.getDataDevolucao()));
		pieRetiradaPersonalizado.setLegendPosition("w");
		pieRetiradaPersonalizado.setShowDataLabels(true);

	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparGrafico() {

		pieRetiradaPersonalizado = null;
		tipoDeAnalise = null;
		lista.clear();
		listaString.clear();
		retirada = new Retirada();
		tipoDeDadosGraficos = null;

	}

	@Override
	public void carregarListagem() {
		
	}

	@Override
	public void executar() {
		
	}

	@Override
	public void limparObjetos() {
		
	}

}
