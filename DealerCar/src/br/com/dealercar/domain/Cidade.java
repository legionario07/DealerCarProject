package br.com.dealercar.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.CidadeDAO;

public class Cidade {

	private int id;
	private String nome;
	private String uf;

	public Cidade() {

	}

	public Cidade(int idCidade) {
		this.setId(idCidade);
	}

	public Cidade(String nome, String uf) {
		this.nome = nome;
		this.uf = uf;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	// Método que cadastra uma Cidade no Banco de Dados
	public void cadastrar(Cidade cidade) {
		CidadeDAO cidDao = new CidadeDAO();
		cidDao.cadastrar(cidade);
	}

	// Método que edita o Cliente e sua respectiva cidade no Banco de Dados
	public void editar(Cidade cidade) {
		CidadeDAO cidDao = new CidadeDAO();
		cidDao.editar(cidade);
	}

	// Método que exlui uma Cidade no Banco de Dados
	public void excluir(Cidade cidade) {
		CidadeDAO cidDao = new CidadeDAO();
		cidDao.excluir(cidade);
	}

	// Método que retorna uma Lista de Todos as Cidades do Banco de Dados
	public List<Cidade> listarTodos() {
		List<Cidade> listaRetorno = new ArrayList<Cidade>();
		CidadeDAO cidDao = new CidadeDAO();
		listaRetorno = cidDao.listarTodos();

		return listaRetorno;
	}

	// Método que retorna uma Cidade de acordo com seu Id
	public Cidade pesquisarPorID(Cidade cidade) {
		Cidade cidadeRetorno = new Cidade();
		CidadeDAO cidDao = new CidadeDAO();
		cidadeRetorno = cidDao.pequisarPorId(cidade);

		return cidadeRetorno;
	}

	// Método que retorna uma Cidade pesquisando por seu nome
	public List<Cidade> pesquisarPorNome(Cidade cidade) {
		List<Cidade> listaRetorno = new ArrayList<Cidade>();
		CidadeDAO cidDao = new CidadeDAO();
		listaRetorno = cidDao.pesquisarPorNome(cidade);

		return listaRetorno;
	}

	@Override
	public String toString() {
		String retorno;
		retorno = "Id: " + this.getId() + "\n";
		retorno += "Nome: " + this.getNome() + "\n";
		retorno += "UF: " + this.getUf() + "\n\n";

		return retorno;
	}

}
