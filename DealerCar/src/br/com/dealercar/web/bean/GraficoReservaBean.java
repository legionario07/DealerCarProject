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
import br.com.dealercar.core.util.DataMenu;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBReservaGrafico")
@ViewScoped
public class GraficoReservaBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PieChartModel pieReservaPersonalizado;

	private Reserva reserva = new Reserva();
	private String tipoDeDadosGraficos;

	private List<EntidadeDominio> listaModelo = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
	private List<String> listaString = new ArrayList<String>();
	private List<DataMenu> listaData = new ArrayList<DataMenu>();
	private List<String> listaStringSituacao = new ArrayList<String>();
	


	public PieChartModel getPieReservaPersonalizado() {
		return pieReservaPersonalizado;
	}

	public void setPieReservaPersonalizado(PieChartModel pieReservaPersonalizado) {
		this.pieReservaPersonalizado = pieReservaPersonalizado;
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

	public List<String> getListaSituacao() {
		return listaStringSituacao;
	}

	public void setListaSituacao(List<String> listaSituacao) {
		this.listaStringSituacao = listaSituacao;
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

		// Recebe os dados de acordo com o criterio buscado
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(new Modelo());
		if (resultado != null) {
			listaModelo = resultado.getEntidades();
		} else {
			listaModelo = null;
		}
		
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

		// Recebe os dados de acordo com o criterio buscado
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(reserva);
		if (resultado != null) {
			lista = resultado.getEntidades();
		} else {
			lista = null;
		}
		
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

		// Não houve dados disponiveis para gerar o gráfico
		if (lista.isEmpty()) {
			JSFUtil.adicionarMensagemErro("Não houve há Dados disponiveis para o Intervalo solicitado");
			limparGrafico();
			org.primefaces.context.RequestContext.getCurrentInstance()
					.update("pnlGrafico pnlComandos pnlTipoGrafico validadores");
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		pieReservaPersonalizado = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieReservaPersonalizado.setTitle(tipoDeDadosGraficos + " - De " + sdf.format(reserva.getDataCadastroReserva())
				+ " à " + sdf.format(reserva.getDataFim()));
		pieReservaPersonalizado.setLegendPosition("w");
		pieReservaPersonalizado.setShowDataLabels(true);

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

	@Override
	public void executar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limparObjetos() {
		// TODO Auto-generated method stub
		
	}

}
