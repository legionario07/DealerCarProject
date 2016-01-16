package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

@ManagedBean(name = "MBOpcionais")
@ViewScoped
public class OpcionaisBean implements IBean, Serializable{

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

	private List<Opcional> opcionais = new ArrayList<Opcional>();
	private List<Itens> listaItens = new ArrayList<Itens>();
	
	private List<ArCondicionado> listaArCondicionados = new ArrayList<ArCondicionado>();
	private List<BebeConforto> listaBebeConfortos = new ArrayList<BebeConforto>();
	private List<CadeirinhaBebe> listaCadeirinhaBebes = new ArrayList<CadeirinhaBebe>();
	private List<Gps> listaGps = new ArrayList<Gps>();
	private List<RadioPlayer> listaRadioPlayers = new ArrayList<RadioPlayer>();
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();

	public ArCondicionado getArCondicionado() {
		return arCondicionado;
	}

	public void setArCondicionado(ArCondicionado arCondicionado) {
		this.arCondicionado = arCondicionado;
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
		
		
		listaArCondicionados   = new ArCondicionadoDAO().listarTodos();
		listaBebeConfortos     = new BebeConfortoDAO().listarTodos();
		listaCadeirinhaBebes   = new CadeirinhaBebeDAO().listarTodos();
		listaGps               = new GpsDAO().listarTodos();
		listaRadioPlayers      = new RadioPlayerDAO().listarTodos();
		listaSeguros           = new SeguroDAO().listarApenasNomesDiferentes();
		
		opcionais.add((Opcional) listaBebeConfortos);
		opcionais.add((Opcional) listaCadeirinhaBebes);
		opcionais.add((Opcional) listaGps);
		opcionais.add((Opcional) listaRadioPlayers);
		
	}

}
