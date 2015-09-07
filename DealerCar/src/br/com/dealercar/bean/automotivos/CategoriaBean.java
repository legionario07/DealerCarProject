package br.com.dealercar.bean.automotivos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.automotivos.CategoriaDAO;
import br.com.dealercar.domain.automotivos.Categoria;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name="MBCategoria")
@ViewScoped
public class CategoriaBean implements Serializable {

	/**
	 * Controlando a evolução dos objetos serialidos.... 
	 * Ex.: Salva um objeto em um arquivo, meses depois em que adicionar um método e ou atributo na sua classe. 
	 * Quando tentar deserializar o objeto naão é permitido mais. 
	 * Mantendo o serialVersionUID este erro não ocorre.
	 * Assim é permitido deserializar objetos que foram modificados.
	 */
	private static final long serialVersionUID = 1L;
	
	private Categoria categoria = new Categoria();
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
	 * Carrega um Lista de Categoria assim que a tela se inicia e
	 * coloca o valor de total de Categoria existente na variavel totalCategoria
	 */
	public void carregarListagemCategoria() {
		CategoriaDAO catDao = new CategoriaDAO();
		listaCategoria = catDao.listarTodos(); 
		
		this.setTotalCategoria(listaCategoria.size());
	}

	/**
	 * Cadastra um novo Objeto de Categoria verificando antes se ela não existe
	 * logo em seguida instancia uma nova Categoria e atualiza a lista de categorias existente
	 */
	public void cadastrar() {
		
		for(Categoria cat : listaCategoria) {
			if(categoria.getNome()==cat.getNome()){
				JSFUtil.adicionarMensagemErro("Essa Categoria já Existe.");
				return;
			}
		}
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.cadastrar(categoria);
		JSFUtil.adicionarMensagemSucesso("Categoria Cadastrada com Sucesso.");
		
		listaCategoria = catDao.listarTodos();
		categoria = new Categoria();
	}
	
	/**
	 * Exclui uma categoria desejado pelo usuário ao ser selecionada na tabela
	 * disponivel na tela. Será solicitado uma confirmação
	 */
	public void excluir() {
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.excluir(categoria);
		
		listaCategoria = catDao.listarTodos();
		
		JSFUtil.adicionarMensagemSucesso("Categoria Excluida com Sucesso.");
		
	}
	
	/**
	 * Edita os dados da categoria selecionada pelo usuario na tela
	 * Será aberta uma nova caixa de dialogo para ser feitas as alterações
	 */
	public void editar() {
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.editar(categoria);
		listaCategoria = catDao.listarTodos();
		
		JSFUtil.adicionarMensagemSucesso("Categoria Alterada com Sucesso.");
		
	}
}
