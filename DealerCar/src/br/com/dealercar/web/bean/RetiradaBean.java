package br.com.dealercar.web.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.builder.GraficoPizzaBuilder;
import br.com.dealercar.core.dao.CidadeDAO;
import br.com.dealercar.core.dao.ClienteDAO;
import br.com.dealercar.core.dao.EstadoDAO;
import br.com.dealercar.core.dao.ReservaDAO;
import br.com.dealercar.core.dao.RetiradaDAO;
import br.com.dealercar.core.dao.automotivos.CarroDAO;
import br.com.dealercar.core.dao.automotivos.ModeloDAO;
import br.com.dealercar.core.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.core.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.core.dao.itensopcionais.GpsDAO;
import br.com.dealercar.core.dao.itensopcionais.OpcionalDAO;
import br.com.dealercar.core.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.core.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.core.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.core.negocio.Reserva;
import br.com.dealercar.core.negocio.Retirada;
import br.com.dealercar.core.negocio.strategy.ValidaCarro;
import br.com.dealercar.core.negocio.strategy.ValidaCidade;
import br.com.dealercar.core.negocio.strategy.ValidaCliente;
import br.com.dealercar.core.negocio.strategy.ValidaEstado;
import br.com.dealercar.core.negocio.strategy.ValidaItemOpcional;
import br.com.dealercar.core.negocio.strategy.ValidaModelo;
import br.com.dealercar.core.relatorios.GeraRelatorio;
import br.com.dealercar.core.util.DataUtil;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.core.util.SessionUtil;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Estado;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;

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

	private List<Estado> listaEstados = new ArrayList<Estado>();
	private List<Cidade> listaCidades = new ArrayList<Cidade>();
	private List<Reserva> listaReservas = new ArrayList<Reserva>();
	private List<Retirada> listaRetirada = new ArrayList<Retirada>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Modelo> listaModelosDisponiveis = new ArrayList<Modelo>();
	private List<Carro> listaPlacasDisponiveis = new ArrayList<Carro>();
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();
	private List<TipoSeguro> listaTipoSeguros = new ArrayList<TipoSeguro>();
	private List<BebeConforto> listaBebeConforto = new ArrayList<BebeConforto>();
	private List<CadeirinhaBebe> listaCadeirinhaBebe = new ArrayList<CadeirinhaBebe>();
	private List<Gps> listaGps = new ArrayList<Gps>();
	private List<RadioPlayer> listaRadioPlayer = new ArrayList<RadioPlayer>();

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

	public List<Carro> getListaPlacasDisponiveis() {
		return listaPlacasDisponiveis;
	}

	public void setListaPlacasDisponiveis(List<Carro> listaPlacasDisponiveis) {
		this.listaPlacasDisponiveis = listaPlacasDisponiveis;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public List<Retirada> getListaRetirada() {
		return listaRetirada;
	}

	public void setListaRetirada(List<Retirada> listaRetirada) {
		this.listaRetirada = listaRetirada;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Modelo> getListaModelosDisponiveis() {
		return listaModelosDisponiveis;
	}

	public void setListaModelosDisponiveis(List<Modelo> listaModelosDisponiveis) {
		this.listaModelosDisponiveis = listaModelosDisponiveis;
	}

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public List<TipoSeguro> getListaTipoSeguros() {
		return listaTipoSeguros;
	}

	public void setListaTipoSeguros(List<TipoSeguro> listaTipoSeguros) {
		this.listaTipoSeguros = listaTipoSeguros;
	}

	public List<BebeConforto> getListaBebeConforto() {
		return listaBebeConforto;
	}

	public void setListaBebeConforto(List<BebeConforto> listaBebeConforto) {
		this.listaBebeConforto = listaBebeConforto;
	}

	public List<CadeirinhaBebe> getListaCadeirinhaBebe() {
		return listaCadeirinhaBebe;
	}

	public void setListaCadeirinhaBebe(List<CadeirinhaBebe> listaCadeirinhaBebe) {
		this.listaCadeirinhaBebe = listaCadeirinhaBebe;
	}

	public List<Gps> getListaGps() {
		return listaGps;
	}

	public void setListaGps(List<Gps> listaGps) {
		this.listaGps = listaGps;
	}

	public List<RadioPlayer> getListaRadioPlayer() {
		return listaRadioPlayer;
	}

	public void setListaRadioPlayer(List<RadioPlayer> listaRadioPlayer) {
		this.listaRadioPlayer = listaRadioPlayer;
	}

	@Override
	public void carregarListagem() {

		listaRetirada = new RetiradaDAO().listarTodos();
		listaReservas = new ReservaDAO().listarTodos();
		listaClientes = new ClienteDAO().listarTodos();
		listaModelosDisponiveis = new ModeloDAO().listarModelosDisponiveis();
		listaTipoSeguros = new TipoSeguroDAO().listarTodos();
		listaSeguros = new SeguroDAO().listarApenasNomesDiferentes();
		listaEstados = new EstadoDAO().listarTodos();

		setTotalRetiradas(listaRetirada.size());

		/*
		 * verifica se ja ja tem uma reserva preenchida Se tiver significa que
		 * foi clicado para locar na View Reserva.xhtml
		 */
		if (retirada.getReserva().getId() > 0) {
			retirada.setCliente(retirada.getReserva().getCliente());
			pesquisarPorCPF();
		}

		// gerando os graficos
		gerarGrafico();

	}

	/**
	 * M�todo que carrega as placas de acordo com o modelo de veiculo
	 * selecionado
	 */
	public void carregarPlacas() {

		retirada.getCarro().setModelo((Modelo) new ValidaModelo().validar(retirada.getCarro().getModelo()));

		listaPlacasDisponiveis = new CarroDAO().listarModelosDisponiveis(retirada.getCarro().getModelo());

	}

	/**
	 * Carrega os Itens Opcionais na View
	 */
	public void carregarItensOpcionais() {

		listaBebeConforto = new BebeConfortoDAO().listarTodos();
		listaCadeirinhaBebe = new CadeirinhaBebeDAO().listarTodos();
		listaGps = new GpsDAO().listarTodos();
		listaRadioPlayer = new RadioPlayerDAO().listarTodos();
	}

	/**
	 * Efetua a retirada validando os itens selecionados na VIEW
	 */
	public void efetuarRetirada() {

		// valida o tipo de seguro escolhido na view
		retirada.getOpcional().getSeguro().setTipoSeguro(
				(TipoSeguro) new ValidaItemOpcional().validar(retirada.getOpcional().getSeguro().getTipoSeguro()));
		// valida o seguro escolhido na view
		retirada.getOpcional().setSeguro((Seguro) new ValidaItemOpcional().validar(retirada.getOpcional().getSeguro()));

		if (bebeConforto.getDescricao() != null)
			bebeConforto = (BebeConforto) new ValidaItemOpcional().validar(bebeConforto);
		else {
			bebeConforto.setCodigo(99);
			bebeConforto = new BebeConfortoDAO().pesquisarPorCodigo(bebeConforto);
		}

		if (cadeirinhaBebe.getDescricao() != null)
			cadeirinhaBebe = (CadeirinhaBebe) new ValidaItemOpcional().validar(cadeirinhaBebe);
		else {
			cadeirinhaBebe.setCodigo(99);
			cadeirinhaBebe = new CadeirinhaBebeDAO().pesquisarPorCodigo(cadeirinhaBebe);
		}
		if (gps.getDescricao() != null)
			gps = (Gps) new ValidaItemOpcional().validar(gps);
		else {
			gps.setCodigo(99);
			gps = new GpsDAO().pesquisarPorCodigo(gps);
		}
		if (radioPlayer.getDescricao() != null)
			radioPlayer = (RadioPlayer) new ValidaItemOpcional().validar(radioPlayer);
		else {
			radioPlayer.setCodigo(99);
			radioPlayer = new RadioPlayerDAO().pesquisarPorCodigo(radioPlayer);
		}

		retirada.getOpcional().getItens().add(bebeConforto);
		retirada.getOpcional().getItens().add(cadeirinhaBebe);
		retirada.getOpcional().getItens().add(gps);
		retirada.getOpcional().getItens().add(radioPlayer);

		// validando o carro
		retirada.setCarro((Carro) new ValidaCarro().validar(retirada.getCarro()));

		new OpcionalDAO().cadastrar(retirada.getOpcional());
		retirada.setOpcional(new OpcionalDAO().pesquisarPorUltimoCadastrado());

		// aqui seta a data de retirada
		retirada.setDataRetirada(DataUtil.pegarDataAtualDoSistema());
		int i = DataUtil.compararDatas(retirada.getDataRetirada(), retirada.getDataDevolucao());
		// se a data for menor que o dia de hoje n�o sera persistido no BD
		if (i == -1) {
			retirada.setDataDevolucao(null);
			JSFUtil.adicionarMensagemErro("A data de Devolu��o n�o pode ser menor que " + retirada.getDataRetirada());
			return;
		}

		/**
		 * Recebendo o funcionario Logado
		 */
		Funcionario funcionario = (Funcionario) SessionUtil.getParam("usuarioLogado");
		retirada.setFuncionario(funcionario);

		// setando retirada como ativa
		retirada.setEhAtivo(true);

		// atualizando a lista de carros disponiveis
		listaModelosDisponiveis = new ModeloDAO().listarModelosDisponiveis();

		new RetiradaDAO().cadastrar(retirada);
		JSFUtil.adicionarMensagemSucesso("Retirada Efetuada com Sucesso.");

		List<Retirada> retiradas = new ArrayList<Retirada>();
		retirada.setId(new RetiradaDAO().pesquisarPorUltimoID());
		retirada = new RetiradaDAO().pesquisarPorID(retirada);
		retiradas.add(retirada);
		
		
		File jasper = new File(
				FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorioRetirada.jasper"));

		GeraRelatorio.exportarListPDF(new HashMap<String, Object>(), jasper, retiradas);

		limparPesquisa();

	}

	/**
	 * Verifica se o cliente tem alguma loca��o em seu CPF
	 */
	public void verificaPendenciaCliente() {

		List<Retirada> listaClientesComLocacao = new ArrayList<Retirada>();
		listaClientesComLocacao = new RetiradaDAO().pesquisarPorCPF(retirada.getCliente());

		for (Retirada r : listaClientesComLocacao) {
			if (retirada.getCliente().getCPF().equals(r.getCliente().getCPF())) {
				JSFUtil.adicionarMensagemErro("Este cliente j� tem uma Loca��o Ativa!");
				return;
			}
		}

		// Se o cliente n�o tem pendencia abre o <p:Dialog>
		org.primefaces.context.RequestContext.getCurrentInstance().execute("PF('dlgEfetuarRetirada').show();");

	}

	/**
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usu�rio na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// Validando o cliente
		retirada.setCliente((Cliente) new ValidaCliente().validar(retirada.getCliente()));

		// veficando se o cliente foi encontrado
		if (retirada.getCliente() != null) {
			setEhCadastrado(true);
			setJaPesquisei(false);
			return;
		}

		if (isEhCadastrado() == false) {
			retirada.setCliente(new Cliente());
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente N�o Cadastrado.");
			return;
		}

	}

	/**
	 * Limpa o inputbox de Pesquisar
	 */
	public void limparPesquisa() {
		retirada.setCliente(new Cliente());
		setEhCadastrado(false);

		limparObjetos();

	}

	/**
	 * Edita o Cliente desejado pelo Usu�rio apos realizado a pesquisa pelo CPF
	 * na tela
	 */
	public void editar() {

		// Verifica a cidade escolhida para ser adicionado ao Cliente que esta
		// sendo editado
		retirada.getCliente().getEndereco()
				.setCidade((Cidade) new ValidaCidade().validar(retirada.getCliente().getEndereco().getCidade()));

		new ClienteDAO().editar(retirada.getCliente());

		JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso.");
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
	 * Carrega a lista de cidades de acordo com o Estado selecionado
	 */
	public void atualizarCidades() {

		retirada.getCliente().getEndereco().getCidade().setEstado(
				(Estado) new ValidaEstado().validar(retirada.getCliente().getEndereco().getCidade().getEstado()));

		listaCidades = new CidadeDAO()
				.pesquisarPorUFEstado(retirada.getCliente().getEndereco().getCidade().getEstado());

	}

	/**
	 * Gerando o gr�fico de Retirada
	 */
	private void gerarGrafico() {

		gerarGraficoModelos();
		gerarGraficoCarrosLocados();
		gerarGraficoCategoriasLocadas();
	}

	/**
	 * Gerando o gr�fico dos Modelos mais Locados
	 */
	private void gerarGraficoModelos() {

		pieRetiradaModelos = new PieChartModel();

		// lista que recebe todos os itens do BD
		List<Retirada> lista = new ArrayList<Retirada>();

		lista = new RetiradaDAO().listarTodos();
		// passando apenas os nomes para a lista de String

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (Retirada r : lista) {
			listaString.add(r.getCarro().getModelo().getNome());
		}

		pieRetiradaModelos = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaModelos.setTitle("Modelos Mais Locados");
		pieRetiradaModelos.setShowDataLabels(true);
		pieRetiradaModelos.setLegendPosition("w");

	}

	/**
	 * Gerando o gr�fico dos carros mais locados
	 */
	private void gerarGraficoCarrosLocados() {

		pieRetiradaCarrosLocados = new PieChartModel();

		// lista que recebe todos os itens do BD
		List<Retirada> lista = new ArrayList<Retirada>();

		lista = new RetiradaDAO().listarTodos();
		// passando apenas os nomes para a lista de String

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (Retirada r : lista) {
			listaString.add(r.getCarro().getPlaca());
		}

		pieRetiradaCarrosLocados = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaCarrosLocados.setTitle("Carros Mais Locados");
		pieRetiradaCarrosLocados.setShowDataLabels(true);
		pieRetiradaCarrosLocados.setLegendPosition("w");

	}

	/**
	 * Gerando o gr�fico das Categorias mais locadas
	 */
	private void gerarGraficoCategoriasLocadas() {

		pieRetiradaCategoriasLocadas = new PieChartModel();

		// lista que recebe todos os itens do BD
		List<Retirada> lista = new ArrayList<Retirada>();

		lista = new RetiradaDAO().listarTodos();
		// passando apenas os nomes para a lista de String

		List<String> listaString = new ArrayList<String>(); // Lista que ira
		for (Retirada r : lista) {
			listaString.add(r.getCarro().getCategoria().getNome());
		}

		pieRetiradaCategoriasLocadas = GraficoPizzaBuilder.gerarGrafico(listaString);
		pieRetiradaCategoriasLocadas.setTitle("Categorias Mais Locadas");
		pieRetiradaCategoriasLocadas.setShowDataLabels(true);
		pieRetiradaCategoriasLocadas.setLegendPosition("w");

	}

	/**
	 * Envia o usuario para a pagina de cadastrar um novo cliente e limpa a
	 * SessionScope
	 */
	public void cadastrarNovoCliente() {

		// Limpando a SessionScope
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MBRetirada");

		// Encaminha o usuario para a pagina de Cadastrar novo cliente
		FacesContext faces = FacesContext.getCurrentInstance();
		ExternalContext exContext = faces.getExternalContext();

		try {
			exContext.redirect("ncliente.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

}