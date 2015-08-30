package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CategoriaDAO;
import br.com.dealercar.domain.Categoria;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name="MBCategoria")
@ViewScoped
public class CategoriaBean implements Serializable {

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
	
	public void carregarListagemCategoria() {
		CategoriaDAO catDao = new CategoriaDAO();
		listaCategoria = catDao.listarTodos(); 
		
		
		this.setTotalCategoria(listaCategoria.size());
	}
	
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
	
	
	public void excluir() {
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.excluir(categoria);
		
		listaCategoria = catDao.listarTodos();
		
		JSFUtil.adicionarMensagemSucesso("Categoria Excluida com Sucesso.");
		
	}
	
	
	public void editar() {
		CategoriaDAO catDao = new CategoriaDAO();
		catDao.editar(categoria);
		listaCategoria = catDao.listarTodos();
		
		JSFUtil.adicionarMensagemSucesso("Categoria Alterada com Sucesso.");
		
	}
}
