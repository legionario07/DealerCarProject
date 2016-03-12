package br.com.dealercar.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CorDAO;
import br.com.dealercar.domain.Cor;
import br.com.dealercar.util.JSFUtil;

/**
 * Controller responsável por gerenciar o View Cors.xhtml
 * @author Paulinho
 *
 */
@ManagedBean(name = "MBCor")
@ViewScoped
public class CorBean implements Serializable, IBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Cor cor = new Cor();
	private List<Cor> listaCores = new ArrayList<Cor>();
	private int totalCor;

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}


	public int getTotalCor() {
		return totalCor;
	}

	public void setTotalCor(int totalCor) {
		this.totalCor = totalCor;
	}


	public List<Cor> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<Cor> listaCores) {
		this.listaCores = listaCores;
	}

	@Override
	public void carregarListagem() {

		listaCores = new CorDAO().listarTodos();
		
		totalCor = listaCores.size();
		
	}
	
	public void cadastrar(){
		
		new CorDAO().cadastrar(cor);
		
		JSFUtil.adicionarMensagemSucesso("Cor Cadastrada com Sucesso.");
		
		cor = new Cor();
		
	}
	
	public void editar(){
		
		new CorDAO().editar(cor);
		
		JSFUtil.adicionarMensagemSucesso("Cor Editada com Sucesso.");
		
		cor = new Cor();
	}
	
	public void excluir(){
		
		new CorDAO().excluir(cor);
		
		JSFUtil.adicionarMensagemSucesso("Cor Excluida com Sucesso.");
		
		cor = new Cor();
		
	}
	
	public void limparObjetos(){
		cor = new Cor();
	}
	
}
