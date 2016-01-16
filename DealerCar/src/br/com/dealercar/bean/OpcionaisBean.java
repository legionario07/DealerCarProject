package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.dealercar.dao.itensopcionais.ArCondicionadoDAO;
import br.com.dealercar.dao.itensopcionais.BebeConfortoDAO;
import br.com.dealercar.dao.itensopcionais.CadeirinhaBebeDAO;
import br.com.dealercar.dao.itensopcionais.GpsDAO;
import br.com.dealercar.dao.itensopcionais.RadioPlayerDAO;
import br.com.dealercar.dao.itensopcionais.SeguroDAO;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBOpcionais")
@ViewScoped
public class OpcionaisBean implements IBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArCondicionado arCondicionado = new ArCondicionado();
	private BebeConforto bebeConforto = new BebeConforto();
	private CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe();
	private Gps gps = new Gps();
	private RadioPlayer radioPlayer = new RadioPlayer();
	private Seguro seguro = new Seguro();

	private Itens item = new Itens();

	private List<Opcional> opcionais = new ArrayList<Opcional>();
	private List<Itens> listaItens = new ArrayList<Itens>();

	private List<ArCondicionado> listaArCondicionados = new ArrayList<ArCondicionado>();
	private List<BebeConforto> listaBebeConfortos = new ArrayList<BebeConforto>();
	private List<CadeirinhaBebe> listaCadeirinhaBebes = new ArrayList<CadeirinhaBebe>();
	private List<Gps> listaGps = new ArrayList<Gps>();
	private List<RadioPlayer> listaRadioPlayers = new ArrayList<RadioPlayer>();
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();

	private int indice;

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

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
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

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public List<ArCondicionado> getListaArCondicionados() {
		return listaArCondicionados;
	}

	public void setListaArCondicionados(List<ArCondicionado> listaArCondicionados) {
		this.listaArCondicionados = listaArCondicionados;
	}

	public List<BebeConforto> getListaBebeConfortos() {
		return listaBebeConfortos;
	}

	public void setListaBebeConfortos(List<BebeConforto> listaBebeConfortos) {
		this.listaBebeConfortos = listaBebeConfortos;
	}

	public List<CadeirinhaBebe> getListaCadeirinhaBebes() {
		return listaCadeirinhaBebes;
	}

	public void setListaCadeirinhaBebes(List<CadeirinhaBebe> listaCadeirinhaBebes) {
		this.listaCadeirinhaBebes = listaCadeirinhaBebes;
	}

	public List<Gps> getListaGps() {
		return listaGps;
	}

	public void setListaGps(List<Gps> listaGps) {
		this.listaGps = listaGps;
	}

	public List<RadioPlayer> getListaRadioPlayers() {
		return listaRadioPlayers;
	}

	public void setListaRadioPlayers(List<RadioPlayer> listaRadioPlayers) {
		this.listaRadioPlayers = listaRadioPlayers;
	}

	public List<Itens> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<Itens> listaItens) {
		this.listaItens = listaItens;
	}

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	public List<Opcional> getOpcionais() {
		return opcionais;
	}

	public void setOpcionais(List<Opcional> opcionais) {
		this.opcionais = opcionais;
	}

	@Override
	public void carregarListagem() {

		listaArCondicionados = new ArCondicionadoDAO().listarTodos();
		listaBebeConfortos = new BebeConfortoDAO().listarTodos();
		listaCadeirinhaBebes = new CadeirinhaBebeDAO().listarTodos();
		listaGps = new GpsDAO().listarTodos();
		listaRadioPlayers = new RadioPlayerDAO().listarTodos();
		listaSeguros = new SeguroDAO().listarApenasNomesDiferentes();

		listaItens.addAll(listaBebeConfortos);
		listaItens.addAll(listaCadeirinhaBebes);
		listaItens.addAll(listaGps);
		listaItens.addAll(listaRadioPlayers);

	}

	/**
	 * Prepara para Cadastrar um novo Opcional
	 */
	public void prepararCadastrar(ActionEvent event) {
		
		indice = 0;
		
		
		if (event.getComponent().getId().equals("botaoBebeConforto")) {
			indice = 2;
		}

		if (event.getComponent().getId().equals("botaoCadeirinhaBebe")) {
			indice = 3;
		}

		if (event.getComponent().getId().equals("botaoGps")) {
			indice = 4;
		}

		if (event.getComponent().getId().equals("botaoRadioPlayer")) {
			indice = 5;
		}

	}

	
	/**
	 * Cadastra um novo Opcional
	 */
	public void cadastrar(ActionEvent event) {

		if (indice == 2) {
			bebeConforto.setCodigo(item.getCodigo());
			bebeConforto.setDescricao(item.getDescricao());
			bebeConforto.setValor(item.getValor());
			bebeConforto.setNumeroPatrimonio(item.getNumeroPatrimonio());
			bebeConforto.setMarca(item.getMarca());
			
			new BebeConfortoDAO().cadastrar(bebeConforto);
			
			bebeConforto = new BebeConforto();
			
			JSFUtil.adicionarMensagemSucesso("Item Opcional cadastrado com sucesso!");
		}

		if (indice == 3) {
			cadeirinhaBebe.setCodigo(item.getCodigo());
			cadeirinhaBebe.setDescricao(item.getDescricao());
			cadeirinhaBebe.setValor(item.getValor());
			cadeirinhaBebe.setNumeroPatrimonio(item.getNumeroPatrimonio());
			cadeirinhaBebe.setMarca(item.getMarca());
			
			new CadeirinhaBebeDAO().cadastrar(cadeirinhaBebe);
			
			cadeirinhaBebe = new CadeirinhaBebe();
			
			JSFUtil.adicionarMensagemSucesso("Item Opcional cadastrado com sucesso!");
		}

		if (indice == 4) {
			gps.setCodigo(item.getCodigo());
			gps.setDescricao(item.getDescricao());
			gps.setValor(item.getValor());
			gps.setNumeroPatrimonio(item.getNumeroPatrimonio());
			gps.setMarca(item.getMarca());
			
			new GpsDAO().cadastrar(gps);
			
			gps = new Gps();
			
			JSFUtil.adicionarMensagemSucesso("Item Opcional cadastrado com sucesso!");
		}

		if (indice == 5) {
			radioPlayer.setCodigo(item.getCodigo());
			radioPlayer.setDescricao(item.getDescricao());
			radioPlayer.setValor(item.getValor());
			radioPlayer.setNumeroPatrimonio(item.getNumeroPatrimonio());
			radioPlayer.setMarca(item.getMarca());
			
			new RadioPlayerDAO().cadastrar(radioPlayer);
			
			radioPlayer = new RadioPlayer();

			JSFUtil.adicionarMensagemSucesso("Item Opcional cadastrado com sucesso!");
		}

		indice = 0;

	}

	/**
	 * Edita o Item Opcional clicado na View
	 */
	public void editar() {

		if (item instanceof BebeConforto) {
			bebeConforto = (BebeConforto) item;
			new BebeConfortoDAO().editar(bebeConforto);
			JSFUtil.adicionarMensagemSucesso("Item Opcional editado com Sucesso!");

		}

		if (item instanceof CadeirinhaBebe) {
			cadeirinhaBebe = (CadeirinhaBebe) item;
			new CadeirinhaBebeDAO().editar(cadeirinhaBebe);
			JSFUtil.adicionarMensagemSucesso("Item Opcional editado com Sucesso!");

		}

		if (item instanceof Gps) {
			gps = (Gps) item;
			new GpsDAO().editar(gps);
			JSFUtil.adicionarMensagemSucesso("Item Opcional editado com Sucesso!");

		}

		if (item instanceof RadioPlayer) {
			radioPlayer = (RadioPlayer) item;
			new RadioPlayerDAO().editar(radioPlayer);
			JSFUtil.adicionarMensagemSucesso("Item Opcional editado com Sucesso!");

		}

		indice = 0;
	}

	/**
	 * Exclui o Item Opcional clicado na View
	 */
	public void excluir() {

		if (item instanceof BebeConforto) {
			bebeConforto = (BebeConforto) item;
			new BebeConfortoDAO().excluir(bebeConforto);
			JSFUtil.adicionarMensagemSucesso("Item Opcional excluido com sucesso!");

		}

		if (item instanceof CadeirinhaBebe) {
			cadeirinhaBebe = (CadeirinhaBebe) item;
			new CadeirinhaBebeDAO().excluir(cadeirinhaBebe);
			JSFUtil.adicionarMensagemSucesso("Item Opcional excluido com sucesso!");

		}

		if (item instanceof Gps) {
			gps = (Gps) item;
			new GpsDAO().excluir(gps);
			JSFUtil.adicionarMensagemSucesso("Item Opcional excluido com sucesso!");

		}

		if (item instanceof RadioPlayer) {
			radioPlayer = (RadioPlayer) item;
			new RadioPlayerDAO().excluir(radioPlayer);
			JSFUtil.adicionarMensagemSucesso("Item Opcional excluido com sucesso!");

		}
		
		indice = 0;

	}

	/**
	 * Verifica qual o opcional clicado
	 */
	public void verificarOpcional() {

		if (item instanceof BebeConforto) {
			indice = 2;
			bebeConforto.setCodigo(item.getCodigo());
			bebeConforto = new BebeConfortoDAO().pesquisarPorCodigo(bebeConforto);
		}

		if (item instanceof CadeirinhaBebe) {
			indice = 3;
			cadeirinhaBebe.setCodigo(item.getCodigo());
			cadeirinhaBebe = new CadeirinhaBebeDAO().pesquisarPorCodigo(cadeirinhaBebe);
		}

		if (item instanceof Gps) {
			indice = 4;
			gps.setCodigo(item.getCodigo());
			gps = new GpsDAO().pesquisarPorCodigo(gps);
		}

		if (item instanceof RadioPlayer) {
			indice = 5;
			radioPlayer.setCodigo(item.getCodigo());
			radioPlayer = new RadioPlayerDAO().pesquisarPorCodigo(radioPlayer);
		}

	}

}
