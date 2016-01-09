package br.com.dealercar.bean;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.automotivos.CategoriaDAO;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBCategoria")
@ViewScoped
public class CategoriaBean implements Serializable {

	/**
	 * Controlando a evolu��o dos objetos serialidos.... Ex.: Salva um objeto em
	 * um arquivo, meses depois em que adicionar um m�todo e ou atributo na sua
	 * classe. Quando tentar deserializar o objeto na�o � permitido mais.
	 * Mantendo o serialVersionUID este erro n�o ocorre. Assim � permitido
	 * deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = 1L;

	private Categoria categoria = new Categoria();
	private CategoriaDAO catDao = new CategoriaDAO();
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();
	private int totalCategoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public int getTotalCategoria() {
		return totalCategoria;
	}

	public void setTotalCategoria(int totalCategoria) {
		this.totalCategoria = totalCategoria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Carrega um Lista de Categoria assim que a tela se inicia e coloca o valor
	 * de total de Categoria existente na variavel totalCategoria
	 */
	public void carregarListagemCategoria() {
		listaCategoria = catDao.listarTodos();

		this.setTotalCategoria(listaCategoria.size());
	}

	/**
	 * Cadastra um novo Objeto de Categoria verificando antes se ela n�o existe
	 * logo em seguida instancia uma nova Categoria e atualiza a lista de
	 * categorias existente
	 */
	public void cadastrar() {

		for (Categoria cat : listaCategoria) {
			if (categoria.getNome() == cat.getNome()) {
				JSFUtil.adicionarMensagemErro("Essa Categoria j� Existe.");
				return;
			}
		}

		catDao.cadastrar(categoria);

		// criando a nova pasta da categoria
		criarDiretorio();

		JSFUtil.adicionarMensagemSucesso("Categoria Cadastrada com Sucesso.");

		listaCategoria = catDao.listarTodos();
		categoria = new Categoria();
	}

	/**
	 * Exclui uma categoria desejado pelo usu�rio ao ser selecionada na tabela
	 * disponivel na tela. Ser� solicitado uma confirma��o
	 */
	public void excluir() {

		catDao.excluir(categoria);

		excluirDiretorio();

		listaCategoria = catDao.listarTodos();

		JSFUtil.adicionarMensagemSucesso("Categoria Excluida com Sucesso.");

	}

	/**
	 * Edita os dados da categoria selecionada pelo usuario na tela Ser� aberta
	 * uma nova caixa de dialogo para ser feitas as altera��es
	 */
	public void editar() {

		Categoria categoriaOld = new CategoriaDAO().pesquisarPorID(categoria);
		
		catDao.editar(categoria);

		
		// criando a nova pasta da categoria
		editarDiretorio(categoriaOld);

		listaCategoria = catDao.listarTodos();

		JSFUtil.adicionarMensagemSucesso("Categoria Alterada com Sucesso.");

	}

	private void criarDiretorio() {

		final String CAMINHO = "C:\\Users\\Paulinho\\git\\DealerCarProject\\DealerCar\\WebContent\\resources\\images\\";
		
		StringBuffer pasta = new StringBuffer();
		pasta.append(CAMINHO);
		pasta.append(categoria.getNome().toLowerCase());

		File diretorio = new File(pasta.toString());

		if (!diretorio.exists()) {
			diretorio.mkdir();

		}

	}

	private void excluirDiretorio() {

		final String CAMINHO = "C:\\Users\\Paulinho\\git\\DealerCarProject\\DealerCar\\WebContent\\resources\\images\\";
		
		StringBuffer pasta = new StringBuffer();
		pasta.append(CAMINHO);
		pasta.append(categoria.getNome().toLowerCase());

		File diretorio = new File(pasta.toString());

		if (diretorio.exists()) {
			diretorio.delete();

		}

	}

	private void editarDiretorio(Categoria categoriaOld) {
		final String CAMINHO = "C:\\Users\\Paulinho\\git\\DealerCarProject\\DealerCar\\WebContent\\resources\\images\\";
		
		StringBuffer pastaAntiga = new StringBuffer();
		pastaAntiga.append(CAMINHO);
		pastaAntiga.append(categoriaOld.getNome().toLowerCase());
		
		StringBuffer pastaNova = new StringBuffer();
		pastaNova.append(CAMINHO);
		pastaNova.append(categoria.getNome().toLowerCase());
		
		

		File diretorio = new File(pastaAntiga.toString());
		File diretorioNovo = new File(pastaNova.toString());

		if (diretorio.exists()) {
			diretorio.renameTo(diretorioNovo);

		}

	}

}
