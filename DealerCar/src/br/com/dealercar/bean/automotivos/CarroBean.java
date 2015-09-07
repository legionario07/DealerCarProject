package br.com.dealercar.bean.automotivos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CorDAO;
import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.dao.automotivos.CategoriaDAO;
import br.com.dealercar.dao.automotivos.ImagemCarroDAO;
import br.com.dealercar.dao.automotivos.ModeloDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.domain.automotivos.ImagemCarro;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.enums.SituacaoType;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name="MBCarro")
@ViewScoped
public class CarroBean implements Serializable{

	/**
	 * Controlando a evolução dos objetos serialidos.... 
	 * Ex.: Salva um objeto em um arquivo, meses depois em que adicionar um método e ou atributo na sua classe. 
	 * Quando tentar deserializar o objeto naão é permitido mais. 
	 * Mantendo o serialVersionUID este erro não ocorre.
	 * Assim é permitido deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = -3692960075593703235L;
	
	private Carro carro = new Carro();
	private List<Carro> listaCarros = new ArrayList<Carro>();
	private int totalCarros;
	private Modelo modelo = new Modelo();
	private Cor cor = new Cor();
	private Categoria categoria = new Categoria();
	private ImagemCarro carroUrl = new ImagemCarro();
	private List<Cor> listaCores = new ArrayList<Cor>();
	private List<Modelo> listaModelos = new ArrayList<Modelo>();
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();
	private List<ImagemCarro> listaImagens = new ArrayList<ImagemCarro>();
	private List<SituacaoType> listaSituacao = new ArrayList<SituacaoType>();
	private boolean ehCadastrado = false;
	private boolean jaPesquisei = false;
	
	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}
	
	public int getTotalCarros() {
		return totalCarros;
	}

	public void setTotalCarros(int totalCarros) {
		this.totalCarros = totalCarros;
	}

	public boolean isEhCadastrado() {
		return ehCadastrado;
	}

	public void setEhCadastrado(boolean ehCadastrado) {
		this.ehCadastrado = ehCadastrado;
	}

	public boolean isJaPesquisei() {
		return jaPesquisei;
	}

	public void setJaPesquisei(boolean jaPesquisei) {
		this.jaPesquisei = jaPesquisei;
	}

	public List<Cor> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<Cor> listaCores) {
		this.listaCores = listaCores;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<ImagemCarro> getListaImagens() {
		return listaImagens;
	}

	public void setListaImagens(List<ImagemCarro> listaImagens) {
		this.listaImagens = listaImagens;
	}

	public List<SituacaoType> getListaSituacao() {
		return listaSituacao;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public ImagemCarro getCarroUrl() {
		return carroUrl;
	}

	public void setCarroUrl(ImagemCarro carroUrl) {
		this.carroUrl = carroUrl;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public void setListaSituacao(List<SituacaoType> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}

	/**
	 * Metodo que carrega as listagem de todas as Categoria, 
	 * Carros, Modelos, Cores e Imagens do Banco de Dados assim que a tela é iniciada
	 * 																			
	 */
	public void carregarListagemCarros() {
		CarroDAO carDao = new CarroDAO();
		listaCarros = carDao.listarTodos();
		
		CategoriaDAO catDao = new CategoriaDAO();
		listaCategoria = catDao.listarTodos();
		
		ModeloDAO modDao = new ModeloDAO();
		listaModelos = modDao.listarTodos();
		
		CorDAO corDao = new CorDAO();
		listaCores = corDao.listarTodos();
		
		ImagemCarroDAO imDao = new ImagemCarroDAO();
		listaImagens = imDao.listarTodos();
		
		listaSituacao = Arrays.asList(SituacaoType.values());
		
		
		this.setTotalCarros(listaCarros.size());
		
	}
	
	/**
	 * Método que localiza um objeto de Carro pela placa digitada na tela pelo Usuario
	 */
	public void pesquisarPorPlaca() {
		this.ehCadastrado =  false;
		this.jaPesquisei = true;
		CarroDAO carroDao = new CarroDAO();
		
		for (Carro c : listaCarros) {
			if (carro.getPlaca().equals(c.getPlaca())) {
				this.ehCadastrado = true;
				this.jaPesquisei = false;
				carro=carroDao.pesquisarPorPlaca(c);
				return;
			}
		}

		if (this.ehCadastrado == false) {
			carro = new Carro();
			JSFUtil.adicionarMensagemNaoLocalizado("Carro Não Cadastrado.");
			return;
		}
	}
	
	/**
	 * Cadastra um novo Objeto do tipo Carro no BD com os dados digitados 
	 * pelo usuario na tela
	 */
	public void cadastrar() {

		ModeloDAO modeloDao = new ModeloDAO();
		
		carro.setModelo(modelo);
		
		listaModelos = modeloDao.listarTodos();
		
		carro.setCategoria(categoria);
		carro.setCor(cor);
		carro.setCarroUrl(carroUrl.validaImagemCarro(modelo.getId(), listaModelos, listaImagens));
		
		CarroDAO carroDao = new CarroDAO();
		carroDao.cadastrar(carro);
		
		JSFUtil.adicionarMensagemSucesso("Carro Cadastrado com Sucesso.");
		
		carro = new Carro();
		ehCadastrado = false;
		jaPesquisei = false;
	}
	
	/**
	 * Limpa a pesquisa anteriomente realizada pelo Usuário
	 */
	public void limparPesquisa() {
		carro = new Carro();
		this.ehCadastrado = false;
	}
	
	/**
	 * Exclui o Objeto Carro localizado pelo Usuario 
	 */
	public void excluir() {
		CarroDAO carroDao = new CarroDAO();
		
		carroDao.excluir(carro);
		JSFUtil.adicionarMensagemSucesso("Carro excluido com Sucesso.");
		carro = new Carro();
		this.jaPesquisei = false;
		this.ehCadastrado = false;
		
	}
	
	/**
	 * Edita o objeto carro localizado com os novos Dados digitados
	 */
	public void editar() {
		
		carro.setCategoria(new Categoria().validaCategoria(carro.getCategoria().getNome(), listaCategoria));
		carro.setCor(new Cor().validaCor(carro.getCor().getNome(), listaCores));
		carro.setModelo(new Modelo().validaModelo(carro.getModelo().getNome(), listaModelos));
		carro.setCarroUrl(new ImagemCarro().validaImagemCarro(carro.getModelo().getId(), listaModelos, listaImagens));
		
		CarroDAO carroDao = new CarroDAO();
		carroDao.editar(carro);
		
		JSFUtil.adicionarMensagemSucesso("Carro Alterado com Sucesso.");
		
		carro = new Carro();
		ehCadastrado = false;
		jaPesquisei = false;
	}
	
}
