package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Revisao;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBRevisaoGrafico")
@ViewScoped
public class GraficoRevisaoBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieRevisaoPersonalizado;
	private Revisao revisao = new Revisao();
	private String tipoDeDadosGraficos;
	private String tipoDeAnalise;
	private List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	private List<String> listaString = new ArrayList<String>();
	private Date dataFinal;

	public PieChartModel getPieRevisaoPersonalizado() {
		return pieRevisaoPersonalizado;
	}

	public void setPieRevisaoPersonalizado(PieChartModel pieRevisaoPersonalizado) {
		this.pieRevisaoPersonalizado = pieRevisaoPersonalizado;
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

		if (DataUtil.compararDatas(revisao.getDataRevisao(), dataFinal) != 1) {
			// colocando formato string para armazenar no banco de dados

			lista = null;
			JSFUtil.adicionarMensagemErro("A data para Final deve ser Maior que a data Inicio.");
			return;
		}

		if (DataUtil.compararDatas(dataFinal, Calendar.getInstance().getTime()) != 1) {

			lista = null;
			JSFUtil.adicionarMensagemErro("A data Final deve ser inferior ao dia Atual");

			return;
		}

		pieRevisaoPersonalizado = new PieChartModel();
		
		Devolucao dev = new Devolucao();
		dev.setDataDevolucao(dataFinal);
		revisao.setDevolucao(dev);
		
		// Recebe os dados de acordo com o criterio buscado
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(revisao);
		if (resultado != null) {
			lista = resultado.getEntidades();
		} else {
			lista = null;
		}
		
		// Verificando qual foi o dado relacionado ao grafico que o usuario
		// deseja verificar
		switch (tipoDeDadosGraficos) {
		case "Categoria":

			for (EntidadeDominio r : lista) {
				listaString.add(((Revisao) r).getCarro().getCategoria().getNome());
			}

			break;
		case "Modelo":

			for (EntidadeDominio r : lista) {
				listaString.add(((Revisao) r).getCarro().getModelo().getNome());
			}

			break;

		case "Carro":

			for (EntidadeDominio r : lista) {
				listaString.add(((Revisao) r).getCarro().getPlaca());
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
			pieRevisaoPersonalizado = null;
			dataFinal = null;
			revisao = new Revisao();
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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		pieRevisaoPersonalizado = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRevisaoPersonalizado.setTitle(tipoDeDadosGraficos + " - De " + sdf.format(revisao.getDataRevisao())
				+ " à " + sdf.format(dataFinal));
		pieRevisaoPersonalizado.setLegendPosition("w");
		pieRevisaoPersonalizado.setShowDataLabels(true);

	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparGrafico() {

		pieRevisaoPersonalizado = null;
		dataFinal = null;
		tipoDeAnalise = null;
		lista.clear();
		listaString.clear();
		revisao = new Revisao();
		tipoDeDadosGraficos = null;
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
