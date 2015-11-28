package br.com.dealercar.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.dao.ClienteDAO;
import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.dao.ReservaDAO;
import br.com.dealercar.dao.RetiradaDAO;
import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.dao.itensopcionais.ArCondicionadoDAO;
import br.com.dealercar.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.dao.itensopcionais.GpsDAO;
import br.com.dealercar.dao.itensopcionais.OpcionalDAO;
import br.com.dealercar.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.dao.itensopcionais.TipoSeguroDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.domain.Cliente;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Reserva;
import br.com.dealercar.domain.Retirada;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.ImagemCarro;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;
import br.com.dealercar.strategy.valida.ValidaCarro;
import br.com.dealercar.strategy.valida.ValidaFuncionario;
import br.com.dealercar.strategy.valida.ValidaImagemCarro;
import br.com.dealercar.strategy.valida.ValidaItemOpcional;
import br.com.dealercar.strategy.valida.ValidaModelo;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe Controller responsavel pela View Retirada
 * 
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBRetirada")
@ViewScoped
public class RetiradaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Retirada retirada = new Retirada();
	private Reserva reserva = new Reserva();
	private Cliente cliente = new Cliente();
	private Cidade cidade = new Cidade();
	private Modelo modelo = new Modelo();
	private Funcionario funcionario = new Funcionario();
	private Itens item = new Itens();
	private Carro carro = new Carro();
	private String quilometragem;
	private ImagemCarro carroUrl = new ImagemCarro();
	private Seguro seguro = new Seguro();
	private Opcional opcional = new Opcional();
	private TipoSeguro tipoSeguro = new TipoSeguro();
	private Date dataDevolucao = null;
	private Date dataRetirada = null;

	private boolean selectItem = false;

	private List<Reserva> listaReservas = new ArrayList<Reserva>();

	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private List<Cidade> listaCidades = new ArrayList<Cidade>();
	private List<Modelo> listaModelosDisponiveis = new ArrayList<Modelo>();
	private List<Carro> listaPlacasDisponiveis = new ArrayList<Carro>();
	private List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	private List<Itens> itens = new ArrayList<Itens>();
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();
	private List<TipoSeguro> listaTipoSeguros = new ArrayList<TipoSeguro>();
	private List<ArCondicionado> listaArCondicionado = new ArrayList<ArCondicionado>();
	private List<BebeConforto> listaBebeConforto = new ArrayList<BebeConforto>();
	private List<CadeirinhaBebe> listaCadeirinhaBebe = new ArrayList<CadeirinhaBebe>();
	private List<Gps> listaGps = new ArrayList<Gps>();
	private List<RadioPlayer> listaRadioPlayer = new ArrayList<RadioPlayer>();

	private ArCondicionado arCondicionado = new ArCondicionado();
	private BebeConforto bebeConforto = new BebeConforto();
	private CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe();
	private Gps gps = new Gps();
	private RadioPlayer radioPlayer = new RadioPlayer();

	private boolean ehCadastrado;
	private boolean jaPesquisei;
	private int dataDevolucaoEhMaior;

	public Retirada getRetirada() {
		return retirada;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Carro getCarro() {
		return carro;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public boolean isSelectItem() {
		return selectItem;
	}

	public void setSelectItem(boolean selectItem) {
		this.selectItem = selectItem;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public ImagemCarro getCarroUrl() {
		return carroUrl;
	}


	public void setCarroUrl(ImagemCarro carroUrl) {
		this.carroUrl = carroUrl;
	}

	public ArCondicionado getArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(ArCondicionado arCondicionado) {
		this.arCondicionado = arCondicionado;
	}

	public Itens getItem() {
		return item;
	}

	public void setItem(Itens item) {
		this.item = item;
	}

	public List<Itens> getItens() {
		return itens;
	}

	public void setItens(List<Itens> itens) {
		this.itens = itens;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getQuilometragem() {
		return quilometragem;
	}

	public int getDataDevolucaoEhMaior() {
		return dataDevolucaoEhMaior;
	}

	public void setDataDevolucaoEhMaior(int dataDevolucaoEhMaior) {
		this.dataDevolucaoEhMaior = dataDevolucaoEhMaior;
	}

	public void setQuilometragem(String quilometragem) {
		this.quilometragem = quilometragem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Opcional getOpcional() {
		return opcional;
	}

	public void setOpcional(Opcional opcional) {
		this.opcional = opcional;
	}

	public BebeConforto getBebeConforto() {
		return bebeConforto;
	}

	public void setBebeConforto(BebeConforto bebeConforto) {
		this.bebeConforto = bebeConforto;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public CadeirinhaBebe getCadeirinhaBebe() {
		return cadeirinhaBebe;
	}

	public void setCadeirinhaBebe(CadeirinhaBebe cadeirinhaBebe) {
		this.cadeirinhaBebe = cadeirinhaBebe;
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

	public boolean isEhCadastrado() {
		return ehCadastrado;
	}

	public void setEhCadastrado(boolean ehCadastrado) {
		this.ehCadastrado = ehCadastrado;
	}

	public List<Carro> getListaPlacasDisponiveis() {
		return listaPlacasDisponiveis;
	}

	public void setListaPlacasDisponiveis(List<Carro> listaPlacasDisponiveis) {
		this.listaPlacasDisponiveis = listaPlacasDisponiveis;
	}

	public boolean isJaPesquisei() {
		return jaPesquisei;
	}

	public void setJaPesquisei(boolean jaPesquisei) {
		this.jaPesquisei = jaPesquisei;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
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

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public List<ArCondicionado> getListaArCondicionado() {
		return listaArCondicionado;
	}

	public void setListaArCondicionado(List<ArCondicionado> listaArCondicionado) {
		this.listaArCondicionado = listaArCondicionado;
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

	public void carregarListagem() {

		listaReservas = new ReservaDAO().listarTodos();
		listaClientes = new ClienteDAO().listarTodos();
		listaCidades = new CidadeDAO().listarTodos();
		listaModelosDisponiveis = new ModeloDAO().listarModelosDisponiveis();
		listaTipoSeguros = new TipoSeguroDAO().listarTodos();
		listaSeguros = new SeguroDAO().listarApenasNomesDiferentes();
		listaFuncionarios = new FuncionarioDAO().listarTodos();

	}

	public void carregarPlacas() {

		modelo = (Modelo) new ValidaModelo().validar(modelo);
		listaPlacasDisponiveis = new CarroDAO().pesquisarPorModelo(modelo);
		carroUrl.setDescricao(modelo.getNome());
		carroUrl = (ImagemCarro) new ValidaImagemCarro().validar(carroUrl);
	}

	public void carregarItensOpcionais() {

		listaArCondicionado = new ArCondicionadoDAO().listarTodos();
		listaBebeConforto = new BebeConfortoDAO().listarTodos();
		listaCadeirinhaBebe = new CadeirinhaBebeDAO().listarTodos();
		listaGps = new GpsDAO().listarTodos();
		listaRadioPlayer = new RadioPlayerDAO().listarTodos();

	}

	public void efetuarRetirada() {

		retirada = new Retirada();

		if (arCondicionado.getCodigo() > 0)
			arCondicionado = (ArCondicionado) new ValidaItemOpcional().validar(arCondicionado);

		opcional.setArCondicionado(arCondicionado);

		tipoSeguro = (TipoSeguro) new ValidaItemOpcional().validar(tipoSeguro);
		seguro.setTipoSeguro(tipoSeguro);
		seguro = (Seguro) new ValidaItemOpcional().validar(seguro);
		opcional.setSeguro(seguro);
		
		if (bebeConforto.getCodigo() > 0)
			bebeConforto = (BebeConforto) new ValidaItemOpcional().validar(bebeConforto);
		if (cadeirinhaBebe.getCodigo() > 0)
			cadeirinhaBebe = (CadeirinhaBebe) new ValidaItemOpcional().validar(cadeirinhaBebe);
		if (gps.getCodigo() > 0)
			gps = (Gps) new ValidaItemOpcional().validar(gps);
		if (radioPlayer.getCodigo() > 0)
			radioPlayer = (RadioPlayer) new ValidaItemOpcional().validar(radioPlayer);

		itens.add(bebeConforto);
		itens.add(cadeirinhaBebe);
		itens.add(gps);
		itens.add(radioPlayer);
		opcional.setItens(itens);

		carro = (Carro) new ValidaCarro().validar(carro);
		funcionario = (Funcionario) new ValidaFuncionario().validar(funcionario);

		new OpcionalDAO().cadastrar(opcional);
		opcional = new OpcionalDAO().pesquisarPorUltimoCadastrado();

		retirada.setCarro(carro);
		retirada.setCliente(cliente);
		retirada.setOpcional(opcional);
		retirada.setFuncionario(funcionario);
		retirada.setQuilometragem(quilometragem);
		retirada.setDataRetirada(setarDataDeCadastro());
		int i = compararDatas(); // aqui seta a data de devolução
		
		//se a data for menor que o dia de hoje não sera persistido no BD
		if(i!=1){
			return;
		}
		new RetiradaDAO().cadastrar(retirada);
		
		limparObjetos();
		
		JSFUtil.adicionarMensagemSucesso("Retirada Efetuada com Sucesso.");
		
	}

	/*
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		this.ehCadastrado = false;
		this.jaPesquisei = true;

		for (Cliente cli : listaClientes) {
			if (cliente.getCPF().toString().equals(cli.getCPF().toString())) {
				this.ehCadastrado = true;
				this.jaPesquisei = false;
				cliente = new ClienteDAO().pesquisarPorCPF(cli);
				reservas = pesquisarPorReserva();

				return;
			}
		}

		if (this.ehCadastrado == false) {
			cliente = new Cliente();
			JSFUtil.adicionarMensagemNaoLocalizado("Cliente Não Cadastrado.");
			return;
		}

	}

	public List<Reserva> pesquisarPorReserva() {

		reserva = new Reserva();

		reserva.setCliente(cliente);
		List<Reserva> lista = new ArrayList<Reserva>();
		lista = new ReservaDAO().pesquisarPorCPF(reserva);

		return lista;
	}

	/**
	 * Limpa o inputbox de Pesquisar
	 */
	public void limparPesquisa() {
		cliente = new Cliente();
		this.ehCadastrado = false;
	}

	/**
	 * Edita o Cliente desejado pelo Usuário apos realizado a pesquisa pelo CPF
	 * na tela
	 */
	public void editar() {

		// Verifica a cidade escolhida para ser adicionado ao Cliente que esta
		// sendo editado

		for (Cidade cid : listaCidades) {
			if (cid.getNome().equals(cliente.getCidade().getNome())) {
				cliente.setCidade(cid);
				break;
			}

		}

		new ClienteDAO().editar(cliente);

		JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso.");
	}

	/**
	 * 
	 * @return Uma Date com a data atual do Sistema
	 */
	public Date setarDataDeCadastro() {

		// Setando a Data inicio como a data do Cadastro
		Date data = Calendar.getInstance().getTime();
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		String dataInicio = stf.format(data);

		try {
			this.dataRetirada = stf.parse(dataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dataRetirada;
	}

	/**
	 * Se a data que o cliente deseja retirar o carro for menor que a data da
	 * Devolucao o sistema irá invalidar a retirada. A dataDevolucao deve ser
	 * maior que a data de Cadastro de Retirada, e nunca menor ou no mesmo dia.
	 * 
	 * @return uma inteiro com os seguintes valores: -1 = a data de cadastro é
	 *         maior que a data da Reserva 0 = ambas das datas são iguais 1 = a
	 *         data de reserva é maior que a data de Cadastro
	 * 
	 */
	public int compararDatas() {

		setarDataDeCadastro();
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		String strDataDevolucao = stf.format(dataDevolucao);
		try {
			this.dataDevolucao = stf.parse(strDataDevolucao);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		dataDevolucaoEhMaior = dataDevolucao.compareTo(dataRetirada);

		if (dataDevolucaoEhMaior == 1) {
			retirada.setDataDevolucao(this.dataDevolucao);
		} else {
			JSFUtil.adicionarMensagemErro("A data de Devolução deve ser maior que "+ stf.format(dataRetirada));
		
		}

		return dataDevolucaoEhMaior;
	}

	
	public void limparObjetos(){
		
		retirada = new Retirada();
		opcional = new Opcional();
		arCondicionado = new ArCondicionado();
		bebeConforto = new BebeConforto();
		cadeirinhaBebe = new CadeirinhaBebe();
		gps = new Gps();
		radioPlayer = new RadioPlayer();
		tipoSeguro = new TipoSeguro();
		seguro = new Seguro();
		carro = new Carro();
		funcionario = new Funcionario();
		itens.clear();
		dataDevolucao = new Date();
		dataDevolucaoEhMaior = 0;
		
		
	}

}
