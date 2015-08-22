package br.com.dealercar.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.domain.Cidade;

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
		listaCidades = cidade.listarTodos();
	}

	public void prepararNovo() {

		cidade = new Cidade();
	}

	public void novo() {
		cidade.cadastrar(cidade);

	}

	public void excluir() {
		cidade.excluir(cidade);
	}

	public void cadastrando() {
		this.cidade.setNome(this.cidade.getNome().toUpperCase());
		this.cidade.setUf(this.cidade.getUf().toUpperCase());

		cidade.cadastrar(cidade);
		cidade = new Cidade();
	}

}
