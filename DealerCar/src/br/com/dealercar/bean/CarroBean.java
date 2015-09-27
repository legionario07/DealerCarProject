package br.com.dealercar.bean;

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
import br.com.dealercar.strategy.valida.IValidacaoStrategy;
import br.com.dealercar.strategy.valida.ValidaCarro;
import br.com.dealercar.strategy.valida.ValidaCategoria;
import br.com.dealercar.strategy.valida.ValidaCor;
import br.com.dealercar.strategy.valida.ValidaImagemCarro;
import br.com.dealercar.strategy.valida.ValidaModelo;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name="MBCarro")
@ViewScoped
public class CarroBean implements Serializable{

	/**
	 * Controlando a evolu��o dos objetos serialidos.... 
	 * Ex.: Salva um objeto em um arquivo, meses depois em que adicionar um m�todo e ou atributo na sua classe. 
	 * Quando tentar deserializar o objeto na�o � permitido mais. 
	 * Mantendo o serialVersionUID este erro n�o ocorre.
	 * Assim � permitido deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = -3692960075593703235L;
	
	private Carro carro = new Carro();
	private int totalCarros;
	private Modelo modelo = new Modelo();
	private Cor cor = new Cor();
	
	private CorDAO corDao = new CorDAO();
	private CarroDAO carDao = new CarroDAO();
	private ModeloDAO modDao = new ModeloDAO();
	private CategoriaDAO catDao = new CategoriaDAO();
	private ImagemCarroDAO imDao = new ImagemCarroDAO();
	
	private IValidacaoStrategy valida = null;
	
	private Categoria categoria = new Categoria();
	private ImagemCarro carroUrl = new ImagemCarro();
	private List<Carro> listaCarros = new ArrayList<Carro>();
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
	 * Carros, Modelos, Cores e Imagens do Banco de Dados assim que a tela � iniciada
	 * 																			
	 */
	public void carregarListagemCarros() {
		
		listaCarros = carDao.listarTodos();
		
		listaCategoria = catDao.listarTodos();
		
		listaModelos = modDao.listarTodos();
		
		listaCores = corDao.listarTodos();
		
		listaImagens = imDao.listarTodos();
		
		listaSituacao = Arrays.asList(SituacaoType.values());
		
		
		this.setTotalCarros(listaCarros.size());
		
	}
	
	/**
	 * M�todo que localiza um objeto de Carro pela placa digitada na tela pelo Usuario
	 */
	public void pesquisarPorPlaca() {
		this.ehCadastrado =  false;
		this.jaPesquisei = true;
		
		valida = new ValidaCarro();
		
		carro = (Carro) valida.validar(carro);
		
		if(carro != null) {

			this.ehCadastrado = true;
			this.jaPesquisei = false;
			return;

		}

		if (this.ehCadastrado == false) {
			carro = new Carro();
			JSFUtil.adicionarMensagemNaoLocalizado("Carro N�o Cadastrado.");
			return;
		}
	}
	
	/**
	 * Cadastra um novo Objeto do tipo Carro no BD com os dados digitados 
	 * pelo usuario na tela
	 */
	public void cadastrar() {

		carro.setModelo(modelo);
		
		
		
		listaModelos = modDao.listarTodos();
		
		carro.setCategoria(categoria);
		carro.setCor(cor);
		

		for(Modelo m : listaModelos) {
			if(m.getId()==carro.getModelo().getId()) {
				carroUrl.setDescricao(m.getNome());
			}
		}
		
		
		valida = new ValidaImagemCarro();
		carro.setCarroUrl((ImagemCarro)valida.validar(carroUrl));
		
		carDao.cadastrar(carro);
		
		JSFUtil.adicionarMensagemSucesso("Carro Cadastrado com Sucesso.");
		
		carro = new Carro();
		ehCadastrado = false;
		jaPesquisei = false;
	}
	
	/**
	 * Limpa a pesquisa anteriomente realizada pelo Usu�rio
	 */
	public void limparPesquisa() {
		carro = new Carro();
		this.ehCadastrado = false;
	}
	
	/**
	 * Exclui o Objeto Carro localizado pelo Usuario 
	 */
	public void excluir() {
		
		carDao.excluir(carro);
		
		JSFUtil.adicionarMensagemSucesso("Carro excluido com Sucesso.");
		carro = new Carro();
		this.jaPesquisei = false;
		this.ehCadastrado = false;
		
	}
	
	/**
	 * Edita o objeto carro localizado com os novos Dados digitados
	 */
	public void editar() {
		
		valida = new ValidaCategoria();
		carro.setCategoria((Categoria)valida.validar(categoria) );
		
		valida = new ValidaCor();
		carro.setCor((Cor) valida.validar(cor));
		
		valida = new ValidaModelo();
		carro.setModelo((Modelo) valida.validar(modelo));
		
		carroUrl.setDescricao(carro.getModelo().getNome());
		
		valida = new ValidaImagemCarro();
		carro.setCarroUrl((ImagemCarro)valida.validar(carroUrl));
		
		carDao.editar(carro);
		
		JSFUtil.adicionarMensagemSucesso("Carro Alterado com Sucesso.");
		
		carro = new Carro();
		ehCadastrado = false;
		jaPesquisei = false;
	}
	
}
