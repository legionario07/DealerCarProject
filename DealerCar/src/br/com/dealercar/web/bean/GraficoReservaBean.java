package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.dao.ReservaDAO;
import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.core.util.DataMenu;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.domain.enums.SituacaoReserva;

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

	private List<EntidadeDominio> listaModelo = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	private List<String> listaString = new ArrayList<String>();
	private List<DataMenu> listaData = new ArrayList<DataMenu>();
	private List<String> listaStringClientes = new ArrayList<String>();
	private List<String> listaStringModelos = new ArrayList<String>();
	private List<String> listaStringSituacao = new ArrayList<String>();
	
	private boolean modeloIsSelecionado = false;
	private boolean situacaoIsSelecionado = false;
	private boolean cpfIsSelecionado = false;

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

	public List<EntidadeDominio> getListaModelo() {
		return listaModelo;
	}

	public void setListaModelo(List<EntidadeDominio> listaModelo) {
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

	public List<String> getListaClientes() {
		return listaStringClientes;
	}

	public void setListaClientes(List<String> listaClientes) {
		this.listaStringClientes = listaClientes;
	}

	public List<String> getListaStringModelos() {
		return listaStringModelos;
	}

	public void setListaStringModelos(List<String> listaStringModelos) {
		this.listaStringModelos = listaStringModelos;
	}

	public List<String> getListaSituacao() {
		return listaStringSituacao;
	}

	public void setListaSituacao(List<String> listaSituacao) {
		this.listaStringSituacao = listaSituacao;
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

	public boolean isModeloIsSelecionado() {
		return modeloIsSelecionado;
	}

	public void setModeloIsSelecionado(boolean modeloIsSelecionado) {
		this.modeloIsSelecionado = modeloIsSelecionado;
	}

	public boolean isSituacaoIsSelecionado() {
		return situacaoIsSelecionado;
	}

	public void setSituacaoIsSelecionado(boolean situacaoIsSelecionado) {
		this.situacaoIsSelecionado = situacaoIsSelecionado;
	}

	public boolean isCpfIsSelecionado() {
		return cpfIsSelecionado;
	}

	public void setCpfIsSelecionado(boolean cpfIsSelecionado) {
		this.cpfIsSelecionado = cpfIsSelecionado;
	}

	public void setAnoSelecionado(String anoSelecionado) {
		this.anoSelecionado = anoSelecionado;
	}

	public List<EntidadeDominio> getLista() {
		return lista;
	}

	public void setLista(List<EntidadeDominio> lista) {
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
	 * Gerando o gr�fico Personalizado mais locadas
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

			for (EntidadeDominio r : lista) {
				listaString.add(((Reserva) r).getCliente().getNome());
			}

			break;
		case "Modelo":

			for (EntidadeDominio r : lista) {
				listaString.add(((Reserva) r).getModelo().getNome());
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

		// N�o houve dados disponiveis para gerar o gr�fico
		if (lista.isEmpty()) {
			JSFUtil.adicionarMensagemErro("N�o houve h� Dados disponiveis para o Intervalo solicitado");
			limparGrafico();
			org.primefaces.context.RequestContext.getCurrentInstance()
					.update("pnlGrafico pnlComandos pnlTipoGrafico validadores");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		pieReservaPersonalizado = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieReservaPersonalizado.setTitle(tipoDeDadosGraficos + " - De " + sdf.format(reserva.getDataCadastroReserva())
				+ " � " + sdf.format(reserva.getDataFim()));
		pieReservaPersonalizado.setLegendPosition("w");
		pieReservaPersonalizado.setShowDataLabels(true);

	}

	/**
	 * Gera o grafico Avan�ado com os dados da View
	 */
	public void gerarGraficoAvancado() {

		lineReservaAvancado = new LineChartModel();

		String diaSelecionado = "01";
		reserva.setDataCadastroReserva(
				DataUtil.getPrimeiroDiaDoMesAtual(anoSelecionado, mesSelecionado, diaSelecionado));
		reserva.setDataFim(DataUtil.getUltimoDiaDoMesAtual(anoSelecionado, mesSelecionado, diaSelecionado));

		StringBuffer sqlCriterios = new StringBuffer();
		// List<String> listaDistintaModelo = new ArrayList<String>();
		// List<String> listaDistintaSituacao = new ArrayList<String>();
		// <String> listaDistintaClientes = new ArrayList<String>();

		if (!strModelo.equals("Todos")) {
			sqlCriterios.append("and reservas.id_modelo = ? ");
			reserva.getModelo().setId(Integer.parseInt(strModelo));
			modeloIsSelecionado = true;
		}
		if (!reserva.getCliente().getCPF().equals("")) {
			sqlCriterios.append("and clientes.cpf = ? ");
			cpfIsSelecionado = true;
		}
		if (!situacao.equals("Todos")) {
			sqlCriterios.append("and reservas.situacao = ? ");
			reserva.setSituacao(SituacaoReserva.valueOf(situacao));
			situacaoIsSelecionado = true;
		} else {
			reserva.setSituacao(SituacaoReserva.ATIVO);
			situacaoIsSelecionado = false;
		}

		lista = new ReservaDAO().pesquisarPorIntervaloECriterios(reserva, sqlCriterios.toString(), modeloIsSelecionado,
				cpfIsSelecionado, situacaoIsSelecionado);

		System.out.println(lista.size());
		

		for (EntidadeDominio r : lista) {
			listaStringModelos.add(((Reserva) r).getModelo().getNome());
		}

		
		if (modeloIsSelecionado) {
			for (EntidadeDominio r : lista) {
				listaStringModelos.add(((Reserva) r).getModelo().getNome());
			}
		}
		if (cpfIsSelecionado) {
			for (EntidadeDominio r : lista) {
				listaStringClientes.add(((Reserva) r).getCliente().getCPF());
			}
		}
		if (situacaoIsSelecionado) {
			for (EntidadeDominio r : lista) {
				listaStringSituacao.add(((Reserva) r).getSituacao().getDescricao());
			}
		}

		// limpando os atributos
		sqlCriterios = new StringBuffer();
	}

	/**
	 * Limpa o Grafico Personalizado da VIew
	 */
	public void limparGrafico() {

		pieReservaPersonalizado = null;
		lista.clear();
		listaString.clear();
		reserva = new Reserva();
	}

}
