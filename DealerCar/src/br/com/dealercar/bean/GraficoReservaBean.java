package br.com.dealercar.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.dao.ReservaDAO;
import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoReserva;
import br.com.dealercar.util.DataMenu;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.GraficoUtil;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBReservaGrafico")
@ViewScoped
public class GraficoReservaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PieChartModel pieReservaPersonalizado;
	private LineChartModel lineReservaAvancado;

	private Reserva reserva = new Reserva();
	private String tipoDeDadosGraficos;
	private String mesSelecionado;
	private String anoSelecionado;
	private String situacao;
	private String strModelo;

	private List<Modelo> listaModelo = new ArrayList<Modelo>();
	private List<Reserva> lista = new ArrayList<Reserva>();
	private List<String> listaString = new ArrayList<String>();
	private List<DataMenu> listaData = new ArrayList<DataMenu>();

	public String getStrModelo() {
		return strModelo;
	}

	public void setStrModelo(String strModelo) {
		this.strModelo = strModelo;
	}

	public PieChartModel getPieReservaPersonalizado() {
		return pieReservaPersonalizado;
	}

	public void setPieReservaPersonalizado(PieChartModel pieReservaPersonalizado) {
		this.pieReservaPersonalizado = pieReservaPersonalizado;
	}

	public LineChartModel getLineReservaAvancado() {
		return lineReservaAvancado;
	}

	public void setLineReservaAvancado(LineChartModel lineReservaAvancado) {
		this.lineReservaAvancado = lineReservaAvancado;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public List<Modelo> getListaModelo() {
		return listaModelo;
	}

	public void setListaModelo(List<Modelo> listaModelo) {
		this.listaModelo = listaModelo;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getMesSelecionado() {
		return mesSelecionado;
	}

	public void setMesSelecionado(String mesSelecionado) {
		this.mesSelecionado = mesSelecionado;
	}

	public String getAnoSelecionado() {
		return anoSelecionado;
	}

	public void setAnoSelecionado(String anoSelecionado) {
		this.anoSelecionado = anoSelecionado;
	}

	public List<Reserva> getLista() {
		return lista;
	}

	public void setLista(List<Reserva> lista) {
		this.lista = lista;
	}

	public List<DataMenu> getListaData() {
		return listaData;
	}

	public void setListaData(List<DataMenu> listaData) {
		this.listaData = listaData;
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

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}

	public void carregarListagem() {

		listaModelo = new ModeloDAO().listarTodos();
		int ano = Calendar.getInstance().get(Calendar.YEAR);
		reserva.setDataCadastroReserva(null);
		reserva.setDataFim(null);

		for (int i = 2015; i <= ano; i++) {
			DataMenu dataMenu = new DataMenu();
			dataMenu.setAno(i);
			listaData.add(dataMenu);
		}

	}

	/**
	 * Gerando o gráfico Personalizado mais locadas
	 */
	public void gerarGraficoPersonalizado() {

		try {
			if (reserva.getDataCadastroReserva().equals(null) || reserva.getDataFim().equals(null)) {
				JSFUtil.adicionarMensagemErro("Favor preencher a data Inicio e Data Final");
				return;
			}
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Favor preencher a data Inicio e Data Final");
			return;
		}

		if (DataUtil.compararDatas(reserva.getDataCadastroReserva(), reserva.getDataFim()) != 1) {
			// colocando formato string para armazenar no banco de dados

			lista = null;
			JSFUtil.adicionarMensagemErro("A data para Final deve ser Maior que a data Inicio.");
			return;
		}

		if (DataUtil.compararDatas(reserva.getDataFim(), Calendar.getInstance().getTime()) != 1) {

			lista = null;
			JSFUtil.adicionarMensagemErro("A data Final deve ser inferior ao dia Atual");

			return;
		}

		pieReservaPersonalizado = new PieChartModel();

		lista = new ReservaDAO().pesquisarPorIntervaloData(reserva);
		// Verificando qual foi o dado relacionado ao grafico que o usuario
		// deseja verificar
		switch (tipoDeDadosGraficos) {
		case "Cliente":

			for (Reserva r : lista) {
				listaString.add(r.getCliente().getNome());
			}

			break;
		case "Modelo":

			for (Reserva r : lista) {
				listaString.add(r.getModelo().getNome());
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
			pieReservaPersonalizado = null;
			reserva = new Reserva();
			org.primefaces.context.RequestContext.getCurrentInstance()
					.update("pnlGrafico pnlComandos pnlTipoGrafico validadores");
			return;
		}

		// Não houve dados disponiveis para gerar o gráfico
		if (lista.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparGrafico();
			org.primefaces.context.RequestContext.getCurrentInstance()
					.update("pnlGrafico pnlComandos pnlTipoGrafico validadores");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		pieReservaPersonalizado = GraficoUtil.gerarGrafico(listaString);
		pieReservaPersonalizado.setTitle(tipoDeDadosGraficos + " - De " + sdf.format(reserva.getDataCadastroReserva())
				+ " à " + sdf.format(reserva.getDataFim()));
		pieReservaPersonalizado.setLegendPosition("w");

	}

	public void gerarGraficoAvancado() {

		String diaSelecionado = "01";
		lineReservaAvancado = new LineChartModel();

		reserva.setDataCadastroReserva(
				DataUtil.getPrimeiroDiaDoMesAtual(anoSelecionado, mesSelecionado, diaSelecionado));
		reserva.setDataFim(DataUtil.getUltimoDiaDoMesAtual(anoSelecionado, mesSelecionado, diaSelecionado));

		boolean modeloIsSelecionado = false;
		boolean situacaoIsSelecionado = false;
		boolean cpfIsSelecionado = false;

		StringBuffer sqlCriterios = new StringBuffer();

		if (!strModelo.equals("Todos")) {
			sqlCriterios.append("and reservas.id_modelo = ? ");
			reserva.getModelo().setId(Integer.parseInt(strModelo));
			modeloIsSelecionado = true;
		}
		if (!situacao.equals("Todos")) {
			sqlCriterios.append("and reservas.situacao = ? ");
			reserva.setSituacao(SituacaoReserva.valueOf(situacao));
			situacaoIsSelecionado = true;
		}
		if (!reserva.getCliente().getCPF().equals("")) {
			sqlCriterios.append("and clientes.cpf = ? ");
			cpfIsSelecionado = true;
		}

		lista = new ReservaDAO().pesquisarPorIntervaloECriterios(reserva, sqlCriterios.toString(), modeloIsSelecionado,
				situacaoIsSelecionado, cpfIsSelecionado);

		for (Reserva r : lista) {
			System.out.println(r);
		}

		sqlCriterios = new StringBuffer();
	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparGrafico() {
		;

		pieReservaPersonalizado = null;
		lista.clear();
		listaString.clear();
		reserva = new Reserva();
	}

	public void limparGraficoAvancado() {

	}

}
