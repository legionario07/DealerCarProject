package br.com.dealercar.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.CidadeDAO;
import br.com.dealercar.domain.Cidade;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBcidade")
@ViewScoped
public class CidadeBean {

	List<Cidade> listaCidades;
	private Cidade cidade = new Cidade();
	private Cidade cidadeRetorno = new Cidade();

	public CidadeBean() {

	}

	public Cidade getCidadeRetorno() {
		return cidadeRetorno;
	}

	public void setCidadeRetorno(Cidade cidadeRetorno) {
		this.cidadeRetorno = cidadeRetorno;
	}

	public List<Cidade> getListaCidades() {
		return listaCidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}

	public void carregarCidades() {
		CidadeDAO cidDao = new CidadeDAO(); 
		listaCidades = cidDao.listarTodos();
	}

	public void prepararNovo() {

		cidade = new Cidade();
	}

	public void novo() {
		CidadeDAO cidDao = new CidadeDAO(); 
		cidDao.cadastrar(cidade);

	}

	public void excluir() {
		CidadeDAO cidDao = new CidadeDAO(); 
		cidDao.excluir(cidade);
		JSFUtil.adicionarMensagemSucesso("Cidade excluida com Sucesso.");
	}

	public void cadastrando() {
		CidadeDAO cidDao = new CidadeDAO(); 
		this.cidade.setNome(this.cidade.getNome().toUpperCase());
		this.cidade.setUf(this.cidade.getUf().toUpperCase());

		cidDao.cadastrar(cidade);
		JSFUtil.adicionarMensagemSucesso("Cidade cadastrada com Sucesso.");
		cidade = new Cidade();
	}

}
