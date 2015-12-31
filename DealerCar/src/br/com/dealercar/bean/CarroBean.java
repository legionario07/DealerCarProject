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
import br.com.dealercar.strategy.valida.ValidaCarro;
import br.com.dealercar.strategy.valida.ValidaCategoria;
import br.com.dealercar.strategy.valida.ValidaCor;
import br.com.dealercar.strategy.valida.ValidaImagemCarro;
import br.com.dealercar.strategy.valida.ValidaModelo;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBCarro")
@ViewScoped
public class CarroBean extends AbstractBean implements Serializable {

	/**
	 * Controlando a evolução dos objetos serialidos.... Ex.: Salva um objeto em
	 * um arquivo, meses depois em que adicionar um método e ou atributo na sua
	 * classe. Quando tentar deserializar o objeto naão é permitido mais.
	 * Mantendo o serialVersionUID este erro não ocorre. Assim é permitido
	 * deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = -3692960075593703235L;

	private Carro carro = new Carro();

	private CorDAO corDao = new CorDAO();
	private CarroDAO carDao = new CarroDAO();
	private ModeloDAO modDao = new ModeloDAO();
	private CategoriaDAO catDao = new CategoriaDAO();
	private ImagemCarroDAO imDao = new ImagemCarroDAO();

	private List<Carro> listaCarros = new ArrayList<Carro>();
	private List<Cor> listaCores = new ArrayList<Cor>();
	private List<Modelo> listaModelos = new ArrayList<Modelo>();
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();
	private List<ImagemCarro> listaImagens = new ArrayList<ImagemCarro>();
	private List<SituacaoType> listaSituacao = new ArrayList<SituacaoType>();

	private int totalCarros;

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

	public void setListaSituacao(List<SituacaoType> listaSituacao) {
		this.listaSituacao = listaSituacao;
	}

	/**
	 * Metodo que carrega as listagem de todas as Categoria, Carros, Modelos,
	 * Cores e Imagens do Banco de Dados assim que a tela é iniciada
	 * 
	 */
	@Override
	public void carregarListagem() {

		listaCarros = carDao.listarTodos();
		listaCategoria = catDao.listarTodos();
		listaModelos = modDao.listarTodos();
		listaCores = corDao.listarTodos();
		listaImagens = imDao.listarTodos();
		listaSituacao = Arrays.asList(SituacaoType.values());

		this.setTotalCarros(listaCarros.size());

	}

	/**
	 * Método que localiza um objeto de Carro pela placa digitada na tela pelo
	 * Usuario
	 */
	public void pesquisarPorPlaca() {

		setEhCadastrado(false);
		setJaPesquisei(true);

		// validando carro
		carro = (Carro) new ValidaCarro().validar(carro);

		// verificando se o carro foi encontrado
		if (carro != null) {

			setEhCadastrado(true);
			return;

		}

		// se nao encontrou entra nesse if
		if (isEhCadastrado() == false) {
			carro = new Carro();
			JSFUtil.adicionarMensagemNaoLocalizado("Carro Não Cadastrado.");
			return;
		}
	}

	/**
	 * Cadastra um novo Objeto do tipo Carro no BD com os dados digitados pelo
	 * usuario na tela
	 */
	public void cadastrar() {

		//valida os dados do carro
		consultarDadosCarroLocalizado();

		carDao.cadastrar(carro);

		JSFUtil.adicionarMensagemSucesso("Carro Cadastrado com Sucesso.");

		setEhCadastrado(false);
		setJaPesquisei(false);
	}

	/**
	 * Limpa a pesquisa anteriomente realizada pelo Usuário
	 */
	public void limparPesquisa() {
		carro = new Carro();
		setEhCadastrado(false);
		setJaPesquisei(false);
	}

	/**
	 * Exclui o Objeto Carro localizado pelo Usuario
	 */
	public void excluir() {

		carDao.excluir(carro);

		JSFUtil.adicionarMensagemSucesso("Carro excluido com Sucesso.");
		carro = new Carro();
		setJaPesquisei(false);
		setEhCadastrado(false);

	}

	/**
	 * Edita o objeto carro localizado com os novos Dados digitados
	 */
	public void editar() {

		//valida os dados do carro
		consultarDadosCarroLocalizado();
		
		carDao.editar(carro);

		JSFUtil.adicionarMensagemSucesso("Carro Alterado com Sucesso.");

		carro = new Carro();
		setEhCadastrado(false);
		setJaPesquisei(false);
	}

	/**
	 * 
	 * @param carro
	 *            recebe o carro localizado e Busca no Strategy Consulta dos
	 *            outros dados do Carro Localizado Como: Categoria, ImagemCarro,
	 *            Cor, Modelo
	 */
	public void consultarDadosCarroLocalizado() {

		// validando a categoria
		carro.setCategoria((Categoria) new ValidaCategoria().validar(carro.getCategoria()));

		// validando a cor
		carro.setCor((Cor) new ValidaCor().validar(carro.getCor()));

		// validando o modelo
		carro.setModelo((Modelo) new ValidaModelo().validar(carro.getModelo()));

		// setando a descrição pra encontrar a imagem
		carro.getCarroUrl().setDescricao(carro.getModelo().getNome());

		// validando a imagem do carro
		carro.setCarroUrl((ImagemCarro) new ValidaImagemCarro().validar(carro.getCarroUrl()));

	}

}
