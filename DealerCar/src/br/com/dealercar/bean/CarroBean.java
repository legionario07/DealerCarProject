package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CarroDAO;
import br.com.dealercar.dao.CategoriaDAO;
import br.com.dealercar.dao.CorDAO;
import br.com.dealercar.dao.ImagemCarroDAO;
import br.com.dealercar.dao.ModeloDAO;
import br.com.dealercar.domain.Carro;
import br.com.dealercar.domain.Categoria;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.domain.ImagemCarro;
import br.com.dealercar.domain.Modelo;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name="MBCarro")
@ViewScoped
public class CarroBean implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = -3692960075593703235L;
	private Carro carro = new Carro();
	private List<Carro> listaCarros = new ArrayList<Carro>();
	private int totalCarros;
	private List<Cor> listaCores = new ArrayList<Cor>();
	private List<Modelo> listaModelos = new ArrayList<Modelo>();
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();
	private List<ImagemCarro> listaImagens = new ArrayList<ImagemCarro>();
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
		
		this.setTotalCarros(listaCarros.size());
		
	}
	
	public void pesquisarPorPlaca() {
		this.ehCadastrado =  false;
		CarroDAO carroDao = new CarroDAO();
		
		for (Carro c : listaCarros) {
			if (carro.getPlaca().toUpperCase().equals(c.getPlaca())) {
				this.ehCadastrado=true;
				carro=carroDao.pesquisarPorPlaca(c);
				break;
			}
		}

		if (this.ehCadastrado == false) {
			carro = new Carro();
			JSFUtil.adicionarMensagemNaoLocalizado("Carro Não Cadastrado.");
			return;
		}
	}
	
	
	public void editar() {
		
		carro.setCategoria(new Categoria().validaCategoria(carro.getCategoria().getNome(), listaCategoria));
		carro.setCor(new Cor().validaCor(carro.getCor().getNome(), listaCores));
		carro.setModelo(new Modelo().validaModelo(carro.getModelo().getNome(), listaModelos));
		carro.setCarroUrl(new ImagemCarro().validaImagemCarro(carro.getModelo().getId(), listaModelos, listaImagens));
		
		System.out.println(carro.getCarroUrl().getCaminho());
		
		CarroDAO carroDao = new CarroDAO();
		carroDao.editar(carro);
		
		JSFUtil.adicionarMensagemSucesso("Carro Editado com Sucesso.");
	}
	
	public void limparPesquisa() {
		carro = new Carro();
		this.ehCadastrado = false;
	}
}
