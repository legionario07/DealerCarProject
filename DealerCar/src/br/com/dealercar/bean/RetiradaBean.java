package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
import br.com.dealercar.strategy.valida.ValidaImagemCarro;
import br.com.dealercar.strategy.valida.ValidaItemOpcional;
import br.com.dealercar.strategy.valida.ValidaModelo;
import br.com.dealercar.util.DataUtil;
import br.com.dealercar.util.JSFUtil;
import br.com.dealercar.viewhelper.SessionHelper;

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
	private Reserva reserva = new Reserva();
	private Cliente cliente = new Cliente();
	private Cidade cidade = new Cidade();
	private Modelo modelo = new Modelo();
	private Itens item = new Itens();
	private Carro carro = new Carro();
	private String quilometragem;
	private ImagemCarro carroUrl = new ImagemCarro();
	private Seguro seguro = new Seguro();
	private Opcional opcional = new Opcional();
	private TipoSeguro tipoSeguro = new TipoSeguro();
	private Date dataDevolucao = null;

	private List<Reserva> listaReservas = new ArrayList<Reserva>();

	private boolean selectAr = false;
	private boolean selectBebe = false;
	private boolean selectGps = false;
	private boolean selectCadeirinha = false;
	private boolean selectRadio = false;
	
	private int totalRetiradas;

	private List<Retirada> listaRetirada = new ArrayList<Retirada>();
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

	public Retirada getRetirada() {
		return retirada;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public void setRetirada(Retirada retirada) {
		this.retirada = retirada;
	}

	public int getTotalRetiradas() {
		return totalRetiradas;
	}

	public void setTotalRetiradas(int totalRetiradas) {
		this.totalRetiradas = totalRetiradas;
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

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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

	@Override
	public void carregarListagem() {

		listaRetirada = new RetiradaDAO().listarTodos();
		listaReservas = new ReservaDAO().listarTodos();
		listaClientes = new ClienteDAO().listarTodos();
		listaCidades = new CidadeDAO().listarTodos();
		listaModelosDisponiveis = new ModeloDAO().listarModelosDisponiveis();
		listaTipoSeguros = new TipoSeguroDAO().listarTodos();
		listaSeguros = new SeguroDAO().listarApenasNomesDiferentes();
		listaFuncionarios = new FuncionarioDAO().listarTodos();

		setTotalRetiradas(listaRetirada.size());
		
	}

	public void carregarPlacas() {

		modelo = (Modelo) new ValidaModelo().validar(modelo);
		listaPlacasDisponiveis = new CarroDAO().listarModelosDisponiveis(modelo);
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

		if (arCondicionado.getDescricao() != null)
			arCondicionado = (ArCondicionado) new ValidaItemOpcional().validar(arCondicionado);
		else{
			arCondicionado.setCodigo(99);
			arCondicionado = new ArCondicionadoDAO().pesquisarPorCodigo(arCondicionado);
		}

		opcional.setArCondicionado(arCondicionado);

		tipoSeguro = (TipoSeguro) new ValidaItemOpcional().validar(tipoSeguro);
		seguro.setTipoSeguro(tipoSeguro);
		seguro = (Seguro) new ValidaItemOpcional().validar(seguro);
		opcional.setSeguro(seguro);

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

		itens.add(bebeConforto);
		itens.add(cadeirinhaBebe);
		itens.add(gps);
		itens.add(radioPlayer);
		opcional.setItens(itens);

		carro = (Carro) new ValidaCarro().validar(carro);

		new OpcionalDAO().cadastrar(opcional);
		opcional = new OpcionalDAO().pesquisarPorUltimoCadastrado();

		retirada.setCarro(carro);
		retirada.setCliente(cliente);
		retirada.setOpcional(opcional);
		retirada.setQuilometragem(quilometragem);

		// aqui seta a data de retirada
		retirada.setDataRetirada(DataUtil.pegarDataAtualDoSistema());
		retirada.setDataDevolucao(dataDevolucao);
		int i = DataUtil.compararDatas(retirada.getDataRetirada(), retirada.getDataDevolucao());
		// se a data for menor que o dia de hoje não sera persistido no BD
		if (i == -1) {
			retirada.setDataDevolucao(null);
			JSFUtil.adicionarMensagemErro("A data de Devolução não pode ser menor que " + retirada.getDataRetirada());
			return;
		}

		// Recebendo o funcionario Logado
		Funcionario funcionario = (Funcionario) SessionHelper.getParam("usuarioLogado");

		retirada.setFuncionario(funcionario);

		new RetiradaDAO().cadastrar(retirada);
		
		//atualizando a lista de carros disponiveis
		listaModelosDisponiveis = new ModeloDAO().listarModelosDisponiveis();

		limparObjetos();

		JSFUtil.adicionarMensagemSucesso("Retirada Efetuada com Sucesso.");

	}

	/*
	 * Pesquisa no BD um cliente de acordo com o CPF digitado pleo Usuário na
	 * TEla
	 */
	public void pesquisarPorCPF() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		for (Cliente cli : listaClientes) {
			if (cliente.getCPF().toString().equals(cli.getCPF().toString())) {
				setEhCadastrado(true);
				setJaPesquisei(false);
				cliente = new ClienteDAO().pesquisarPorCPF(cli);
				reservas = pesquisarPorReserva();

				return;
			}
		}

		if (isEhCadastrado() == false) {
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
		setEhCadastrado(false);
	
		limparObjetos();
		
	}

	/**
	 * Edita o Cliente desejado pelo Usuário apos realizado a pesquisa pelo CPF
	 * na tela
	 */
	public void editar() {

		// Verifica a cidade escolhida para ser adicionado ao Cliente que esta
		// sendo editado

		for (Cidade cid : listaCidades) {
			if (cid.getNome().equals(cliente.getEndereco().getCidade().getNome())) {
				cliente.getEndereco().setCidade(cid);
				break;
			}

		}

		new ClienteDAO().editar(cliente);

		JSFUtil.adicionarMensagemSucesso("Cliente Editado com Sucesso.");
	}

	public void limparObjetos() {

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
		modelo = new Modelo();
		quilometragem = null;
		dataDevolucao = null;
		
		
		selectAr = false;
		selectBebe = false;
		selectGps = false;
		selectCadeirinha = false;
		selectRadio = false;
		
		itens.clear();

	}

}
