package br.com.dealercar.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.dealercar.core.aplicacao.Resultado;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.web.command.ICommand;

@ManagedBean(name = "MBOpcionais")
@ViewScoped
public class OpcionaisBean extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BebeConforto bebeConforto = new BebeConforto();
	private CadeirinhaBebe cadeirinhaBebe = new CadeirinhaBebe();
	private Gps gps = new Gps();
	private RadioPlayer radioPlayer = new RadioPlayer();

	private Itens item = new Itens();

	private List<EntidadeDominio> opcionais = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaItens = new ArrayList<EntidadeDominio>();

	private List<EntidadeDominio> listaBebeConfortos = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaCadeirinhaBebes = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaGps = new ArrayList<EntidadeDominio>();
	private List<EntidadeDominio> listaRadioPlayers = new ArrayList<EntidadeDominio>();

	private int indice;


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

	public List<EntidadeDominio> getListaBebeConfortos() {
		return listaBebeConfortos;
	}

	public void setListaBebeConfortos(List<EntidadeDominio> listaBebeConfortos) {
		this.listaBebeConfortos = listaBebeConfortos;
	}

	public List<EntidadeDominio> getListaCadeirinhaBebes() {
		return listaCadeirinhaBebes;
	}

	public void setListaCadeirinhaBebes(List<EntidadeDominio> listaCadeirinhaBebes) {
		this.listaCadeirinhaBebes = listaCadeirinhaBebes;
	}

	public List<EntidadeDominio> getListaGps() {
		return listaGps;
	}

	public void setListaGps(List<EntidadeDominio> listaGps) {
		this.listaGps = listaGps;
	}

	public List<EntidadeDominio> getListaRadioPlayers() {
		return listaRadioPlayers;
	}

	public void setListaRadioPlayers(List<EntidadeDominio> listaRadioPlayers) {
		this.listaRadioPlayers = listaRadioPlayers;
	}

	public List<EntidadeDominio> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<EntidadeDominio> listaItens) {
		this.listaItens = listaItens;
	}

	public List<EntidadeDominio> getOpcionais() {
		return opcionais;
	}

	public void setOpcionais(List<EntidadeDominio> opcionais) {
		this.opcionais = opcionais;
	}

	@Override
	public void carregarListagem() {


		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("LISTAR");
		Resultado resultado = new Resultado();

		resultado = command.execute(new BebeConforto());
		if (resultado != null) {
			listaBebeConfortos = resultado.getEntidades();
		}
		resultado = command.execute(new CadeirinhaBebe());
		if (resultado != null) {
			listaCadeirinhaBebes = resultado.getEntidades();
		}
		resultado = command.execute(new Gps());
		if (resultado != null) {
			listaGps = resultado.getEntidades();
		}
		resultado = command.execute(new RadioPlayer());
		if (resultado != null) {
			listaRadioPlayers = resultado.getEntidades();
		}
		
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
		} else	if (event.getComponent().getId().equals("botaoCadeirinhaBebe")) {
			indice = 3;
		} else if (event.getComponent().getId().equals("botaoGps")) {
			indice = 4;
		} else if (event.getComponent().getId().equals("botaoRadioPlayer")) {
			indice = 5;
		}

	}

	
	@Override
	public void executar() {

		// recebe a operacao a ser realizada
		String operacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("param");

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get(operacao);
		

		if (indice == 2 || item instanceof BebeConforto) {
			bebeConforto.setCodigo(item.getCodigo());
			bebeConforto.setDescricao(item.getDescricao());
			bebeConforto.setValor(item.getValor());
			bebeConforto.setNumeroPatrimonio(item.getNumeroPatrimonio());
			bebeConforto.setMarca(item.getMarca());
			
			command.execute(bebeConforto);
			
			bebeConforto = new BebeConforto();
			
		} else if (indice == 3 || item instanceof CadeirinhaBebe) {
			cadeirinhaBebe.setCodigo(item.getCodigo());
			cadeirinhaBebe.setDescricao(item.getDescricao());
			cadeirinhaBebe.setValor(item.getValor());
			cadeirinhaBebe.setNumeroPatrimonio(item.getNumeroPatrimonio());
			cadeirinhaBebe.setMarca(item.getMarca());
			
			command.execute(cadeirinhaBebe);
			
			cadeirinhaBebe = new CadeirinhaBebe();
			
		} else if (indice == 4 || item instanceof Gps) {
			gps.setCodigo(item.getCodigo());
			gps.setDescricao(item.getDescricao());
			gps.setValor(item.getValor());
			gps.setNumeroPatrimonio(item.getNumeroPatrimonio());
			gps.setMarca(item.getMarca());
			
			command.execute(gps);
			
			gps = new Gps();
			
		} else if (indice == 5 || item instanceof RadioPlayer) {
			radioPlayer.setCodigo(item.getCodigo());
			radioPlayer.setDescricao(item.getDescricao());
			radioPlayer.setValor(item.getValor());
			radioPlayer.setNumeroPatrimonio(item.getNumeroPatrimonio());
			radioPlayer.setMarca(item.getMarca());
			
			command.execute(radioPlayer);
			
			radioPlayer = new RadioPlayer();

		}

		item = new Itens();
		indice = 0;
		
	}
	


	/**
	 * Verifica qual o opcional clicado
	 */
	public void verificarOpcional() {

		// escolhe o Command corretamente de acordo com a operacao
		ICommand command = mapCommands.get("CONSULTAR");
		Resultado resultado = new Resultado();

		
		if (item instanceof BebeConforto) {
			indice = 2;
			bebeConforto.setCodigo(item.getCodigo());
			resultado = command.execute(bebeConforto);
			bebeConforto = (BebeConforto) resultado.getEntidades().get(0);
			
		} else if (item instanceof CadeirinhaBebe) {
			indice = 3;
			cadeirinhaBebe.setCodigo(item.getCodigo());
			resultado = command.execute(cadeirinhaBebe);
			cadeirinhaBebe = (CadeirinhaBebe) resultado.getEntidades().get(0);			
			
		} else if (item instanceof Gps) {
			indice = 4;
			gps.setCodigo(item.getCodigo());
			resultado = command.execute(gps);
			gps = (Gps) resultado.getEntidades().get(0);
			
		} else if (item instanceof RadioPlayer) {
			indice = 5;
			radioPlayer.setCodigo(item.getCodigo());
			resultado = command.execute(radioPlayer);
			radioPlayer = (RadioPlayer) resultado.getEntidades().get(0);
		}
		
		
	}
	
	public void limparObjetos(){
		item = new Itens();
		indice = 0;
		bebeConforto = new BebeConforto();
		cadeirinhaBebe = new CadeirinhaBebe();
		gps = new Gps();
		radioPlayer = new RadioPlayer();
	}

}
