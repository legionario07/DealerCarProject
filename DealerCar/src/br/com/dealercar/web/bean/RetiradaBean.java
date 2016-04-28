package br.com.dealercar.web.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.conducao.Reserva;
import br.com.dealercar.domain.conducao.Retirada;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;
import br.com.dealercar.web.command.ICommand;

/**
 * Classe Controller responsavel pela View Retirada
 * 
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBRetirada")
@SessionScoped
public class RetiradaBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Retirada retirada = new Retirada();

	private boolean selectAr = false;
	private boolean selectBebe = false;
	private boolean selectGps = false;
	private boolean selectCadeirinha = false;
	private boolean selectRadio = false;

	private int totalRetiradas;

	private List<EntidadeDominio> listaReservas = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaRetirada = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaClientes = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaModelosDisponiveis = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaPlacasDisponiveis = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaSeguros = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaTipoSeguros = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaBebeConforto = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCadeirinhaBebe = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaGps = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaRadioPlayer = new ArrayList<EntidadeDominio>();

	private BebeConforto bebeConforto = new BebeConforto();
	private CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe();
	private Gps gps = new Gps();
	private RadioPlayer radioPlayer = new RadioPlayer();

	private PieChartModel pieRetiradaModelos;
	private PieChartModel pieRetiradaCarrosLocados;
	private PieChartModel pieRetiradaCategoriasLocadas;

	private String tipoDeDadosGraficos;
	private Retirada retiradaImpressao;

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public PieChartModel getPieRetiradaModelos() {
		return pieRetiradaModelos;
	}

	public void setPieRetiradaModelos(PieChartModel pieRetiradaModelos) {
		this.pieRetiradaModelos = pieRetiradaModelos;
	}

	public PieChartModel getPieRetiradaCarrosLocados() {
		return pieRetiradaCarrosLocados;
	}

	public void setPieRetiradaCarrosLocados(PieChartModel pieRetiradaCarrosLocados) {
		this.pieRetiradaCarrosLocados = pieRetiradaCarrosLocados;
	}

	public PieChartModel getPieRetiradaCategoriasLocadas() {
		return pieRetiradaCategoriasLocadas;
	}

	public void setPieRetiradaCategoriasLocadas(PieChartModel pieRetiradaCategoriasLocadas) {
		this.pieRetiradaCategoriasLocadas = pieRetiradaCategoriasLocadas;
	}

	public int getTotalRetiradas() {
		return totalRetiradas;
	}

	public void setTotalRetiradas(int totalRetiradas) {
		this.totalRetiradas = totalRetiradas;
	}

	public String getTipoDeDadosGraficos() {
		return tipoDeDadosGraficos;
	}

	public void setTipoDeDadosGraficos(String tipoDeDadosGraficos) {
		this.tipoDeDadosGraficos = tipoDeDadosGraficos;
	}

	public boolean isSelectAr() {
		return selectAr;
	}

	public void setSelectAr(boolean selectAr) {
		this.selectAr = selectAr;
	}

	public boolean isSelectBebe() {
		return selectBebe;
	}

	public void setSelectBebe(boolean selectBebe) {
		this.selectBebe = selectBebe;
	}

	public boolean isSelectGps() {
		return selectGps;
	}


	public void setSelectGps(boolean selectGps) {
		this.selectGps = selectGps;
	}

	public boolean isSelectCadeirinha() {
		return selectCadeirinha;
	}

	public void setSelectCadeirinha(boolean selectCadeirinha) {
		this.selectCadeirinha = selectCadeirinha;
	}

	public boolean isSelectRadio() {
		return selectRadio;
	}

	public void setSelectRadio(boolean selectRadio) {
		this.selectRadio = selectRadio;
	}

	public BebeConforto getBebeConforto() {
		return bebeConforto;
	}

	public void setBebeConforto(BebeConforto bebeConforto) {
		this.bebeConforto = bebeConforto;
	}

	public CadeirinhaBebe getCadeirinhaBebe() {
		return cadeirinhaBebe;
	}

	public void setCadeirinhaBebe(CadeirinhaBebe cadeirinhaBebe) {
		this.cadeirinhaBebe = cadeirinhaBebe;
	}

	public Retirada getRetiradaImpressao() {
		return retiradaImpressao;
	}

	public void setRetiradaImpressao(Retirada retiradaImpressao) {
		this.retiradaImpressao = retiradaImpressao;
	}

	public Gps getGps() {
		return gps;
	}

	public void setGps(Gps gps) {
		this.gps = gps;
	}

	public RadioPlayer getRadioPlayer() {
		return radioPlayer;
	}

	public void setRadioPlayer(RadioPlayer radioPlayer) {
		this.radioPlayer = radioPlayer;
	}

	public List<EntidadeDominio> getListaPlacasDisponiveis() {
		return listaPlacasDisponiveis;
	}

	public void setListaPlacasDisponiveis(List<EntidadeDominio> listaPlacasDisponiveis) {
		this.listaPlacasDisponiveis = listaPlacasDisponiveis;
	}

	public List<EntidadeDominio> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<EntidadeDominio> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public List<EntidadeDominio> getListaRetirada() {
		return listaRetirada;
	}

	public void setListaRetirada(List<EntidadeDominio> listaRetirada) {
		this.listaRetirada = listaRetirada;
	}

	public List<EntidadeDominio> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<EntidadeDominio> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<EntidadeDominio> getListaModelosDisponiveis() {
		return listaModelosDisponiveis;
	}

	public void setListaModelosDisponiveis(List<EntidadeDominio> listaModelosDisponiveis) {
		this.listaModelosDisponiveis = listaModelosDisponiveis;
	}

	public List<EntidadeDominio> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<EntidadeDominio> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public List<EntidadeDominio> getListaTipoSeguros() {
		return listaTipoSeguros;
	}

	public void setListaTipoSeguros(List<EntidadeDominio> listaTipoSeguros) {
		this.listaTipoSeguros = listaTipoSeguros;
	}

	public List<EntidadeDominio> getListaBebeConforto() {
		return listaBebeConforto;
	}

	public void setListaBebeConforto(List<EntidadeDominio> listaBebeConforto) {
		this.listaBebeConforto = listaBebeConforto;
	}

	public List<EntidadeDominio> getListaCadeirinhaBebe() {
		return listaCadeirinhaBebe;
	}

	public void setListaCadeirinhaBebe(List<EntidadeDominio> listaCadeirinhaBebe) {
		this.listaCadeirinhaBebe = listaCadeirinhaBebe;
	}

	public List<EntidadeDominio> getListaGps() {
		return listaGps;
	}

	public void setListaGps(List<EntidadeDominio> listaGps) {
		this.listaGps = listaGps;
	}

	public List<EntidadeDominio> getListaRadioPlayer() {
		return listaRadioPlayer;
	}

	public void setListaRadioPlayer(List<EntidadeDominio> listaRadioPlayer) {
		this.listaRadioPlayer = listaRadioPlayer;
	}

	@Override
	public void carregarListagem() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get("LISTAR");

		Resultado resultado = new Resultado();

		resultado = command.execute(new Retirada());
		if (resultado != null) {
			listaRetirada = resultado.getEntidades();
		}

		resultado = command.execute(new Reserva());
		if (resultado != null) {
			listaReservas = resultado.getEntidades();
		}

		resultado = command.execute(new Modelo());
		if (resultado != null) {
			listaModelosDisponiveis = resultado.getEntidades();
		}

		resultado = command.execute(new Seguro());
		if (resultado != null) {
			listaSeguros = resultado.getEntidades();
		}

		command = mapCommands.get("LISTAR");

		resultado = command.execute(new Cliente());
		if (resultado != null) {
			listaClientes = resultado.getEntidades();
		}
		resultado = command.execute(new TipoSeguro());
		if (resultado != null) {
			listaTipoSeguros = resultado.getEntidades();
		}

		setTotalRetiradas(listaRetirada.size());

		/*
		 * verifica se ja ja tem uma reserva preenchida Se tiver significa que
		 * foi clicado para locar na View Reserva.xhtml
		 */
		if (this.retirada.getReserva().getId() > 0) {
			this.retirada.setCliente(this.retirada.getReserva().getCliente());
			pesquisarPorCPF();
		}

		// gerando os graficos
		gerarGrafico();

	}

	/**
	 * Método que carrega as placas de acordo com o modelo de veiculo
	 * selecionado
	 */
	public void carregarPlacas() {

		ICommand command = mapConducaoCommands.get("LISTAR");

		Resultado resultado = new Resultado();

		resultado = command.execute(this.retirada.getCarro());
		if (resultado != null) {
			listaPlacasDisponiveis = resultado.getEntidades();
		}

	}

	/**
	 * Carrega os Itens Opcionais na View
	 */
	public void carregarItensOpcionais() {

		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(new BebeConforto());
		if (resultado != null) {
			listaBebeConforto = resultado.getEntidades();
		}
		resultado = command.execute(new CadeirinhaBebe());
		if (resultado != null) {
			listaCadeirinhaBebe = resultado.getEntidades();
		}
		resultado = command.execute(new Gps());
		if (resultado != null) {
			listaGps = resultado.getEntidades();
		}
		resultado = command.execute(new RadioPlayer());
		if (resultado != null) {
			listaRadioPlayer = resultado.getEntidades();
		}

	}

	/**
	 * Efetua a retirada validando os itens selecionados na VIEW
	 */
	public void executar() {
		

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapConducaoCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();
		
		if (bebeConforto.getCodigo()==0)
			bebeConforto.setCodigo(99);

		if (cadeirinhaBebe.getCodigo()==0)
			cadeirinhaBebe.setCodigo(99);
		
		if (gps.getCodigo() == 0)
			gps.setCodigo(99);
		
		if (radioPlayer.getCodigo() == 0 )
			radioPlayer.setCodigo(99);
		
		this.retirada.getOpcional().getItens().add(bebeConforto);
		this.retirada.getOpcional().getItens().add(cadeirinhaBebe);
		this.retirada.getOpcional().getItens().add(gps);
		this.retirada.getOpcional().getItens().add(radioPlayer);
		
		resultado = command.execute(this.retirada.getOpcional().getSeguro());
		//pesquisando o seguro e o tipo de seguro escolhido
		this.retirada.getOpcional().setSeguro((Seguro) resultado.getEntidades().get(0));
		//localizando o ultimo Opcional Cadastrado e setando na Retirada
		
		command = mapConducaoCommands.get(operacao);
		resultado = command.execute(this.retirada.getOpcional());
		
		command = mapConducaoCommands.get("LISTAR");
		resultado = command.execute(new Opcional());
		
		this.retirada.setOpcional((Opcional) resultado.getEntidades().get(resultado.getEntidades().size()-1));
		
		//Recebendo o funcionario Logado
		Funcionario funcionario = new Funcionario();
		funcionario  = (Funcionario) SessionUtil.getParam("usuarioLogado");
		this.retirada.setFuncionario(funcionario);

		// Pegando a data atual da Retirada
		this.retirada.setDataRetirada(DataUtil.pegarDataAtualDoSistema());
		
		// setando retirada como ativa
		this.retirada.setEhAtivo(true);

		command = mapConducaoCommands.get(operacao);
		command.execute(this.retirada);

		command = mapConducaoCommands.get("LISTAR");
		resultado = command.execute(new Retirada());
		
		List<EntidadeDominio> retiradas = new ArrayList<EntidadeDominio>();
		if(resultado!= null) {
			retiradas = resultado.getEntidades();
			//pega a ultima retirada cadastrada no BD, limpa a Lista e armazena apenas a ultima
			this.retirada = (Retirada) retiradas.get(retiradas.size()-1);
			//retiradas.clear();
			//retiradas.add(retirada);
		}
		
		File jasper = new File(
				FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Relatorios/relatorioRetirada.jasper"));
		SessionUtil.remove("objetoRelatorio");
		SessionUtil.setParam("objetoRelatorio", this.retirada);
		SessionUtil.setParam("url", jasper);


		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("imprimerelatorio.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Verifica se o cliente tem alguma locação em seu CPF
	 */
	public void verificaPendenciaCliente() {

		Retirada retiradaRetorno = new Retirada();

		ICommand command = mapConducaoCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();

		//retorna uma lista com todos os clientes com locação no mommento
		resultado = command.execute(this.retirada.getCliente());
		if (resultado.getEntidades().get(0) != null) {
			retiradaRetorno = (Retirada) resultado.getEntidades().get(0);
		}

		//Verifica se o CPF digitado na view tem alguma locação
			if (this.retirada.getCliente().getCPF().equals(retiradaRetorno.getCliente().getCPF())) {
				JSFUtil.adicionarMensagemErro("Este cliente já tem uma Locação Ativa!");
				return;
			}

		// Se o cliente não tem pendencia abre o <p:Dialog>
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dlgEfetuarRetirada').show();");

	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Retorna um estado completo de acordo com um ID
		ICommand command = mapCommands.get("CONSULTAR");

		Resultado resultado = new Resultado();
		resultado = command.execute(this.retirada.getCliente());

		// Cliente foi encontrado
		if (resultado.getEntidades().get(0) != null) {
			this.retirada.setCliente((Cliente) resultado.getEntidades().get(0));
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		} else {
			this.retirada.setCliente(new Cliente());
			JSFUtil.adicionarMensagemErro("Este Cliente não esta Cadastrado");	
		}

	}

	/**
	 * Limpa o inputbox de Pesquisar
	 */
	public void limparPesquisa() {
		this.retirada.setCliente(new Cliente());
		setEhCadastrado(false);

		limparObjetos();

	}

	/**
	 * Limpa os Objetos
	 */
	public void limparObjetos() {

		retirada = new Retirada();

		bebeConforto = new BebeConforto();
		cadeirinhaBebe = new CadeirinhaBebe();
		gps = new Gps();
		radioPlayer = new RadioPlayer();

		selectAr = false;
		selectBebe = false;
		selectGps = false;
		selectCadeirinha = false;
		selectRadio = false;

		// Limpando a SessionScope
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MBRetirada");

	}

	/**
	 * Gerando o gráfico de Retirada
	 */
	private void gerarGrafico() {

		gerarGraficoModelos();
		gerarGraficoCarrosLocados();
		gerarGraficoCategoriasLocadas();
	}

	/**
	 * Gerando o gráfico dos Modelos mais Locados
	 */
	private void gerarGraficoModelos() {

		pieRetiradaModelos = new PieChartModel();

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (EntidadeDominio r : listaRetirada) {
			listaString.add(((Retirada) r).getCarro().getModelo().getNome());
		}

		pieRetiradaModelos = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaModelos.setTitle("Modelos Mais Locados");
		pieRetiradaModelos.setShowDataLabels(true);
		pieRetiradaModelos.setLegendPosition("w");
	}

	/**
	 * Gerando o gráfico dos carros mais locados
	 */
	private void gerarGraficoCarrosLocados() {

		pieRetiradaCarrosLocados = new PieChartModel();

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (EntidadeDominio r : listaRetirada) {
			listaString.add(((Retirada) r).getCarro().getPlaca());
		}

		pieRetiradaCarrosLocados = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaCarrosLocados.setTitle("Carros Mais Locados");
		pieRetiradaCarrosLocados.setShowDataLabels(true);
		pieRetiradaCarrosLocados.setLegendPosition("w");

	}

	/**
	 * Gerando o gráfico das Categorias mais locadas
	 */
	private void gerarGraficoCategoriasLocadas() {

		pieRetiradaCategoriasLocadas = new PieChartModel();

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (EntidadeDominio r : listaRetirada) {
			listaString.add(((Retirada) r).getCarro().getCategoria().getNome());
		}

		pieRetiradaCategoriasLocadas = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaCategoriasLocadas.setTitle("Categorias Mais Locadas");
		pieRetiradaCategoriasLocadas.setShowDataLabels(true);
		pieRetiradaCategoriasLocadas.setLegendPosition("w");

	}

}
