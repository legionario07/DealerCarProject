package br.com.dealercar.domain.automotivos;

import java.io.Serializable;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe que representa a categoria a qual um carro pertence
 * @author Paulinho
 *
 */
public class Categoria extends EntidadeDominio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String descricao;
	private Double valorDiaria;

	public Categoria() {

	}
	
	public Categoria(String nome) {
		this.setNome(nome);
	}

	public Categoria(int id) {
		this.setId(id);
	}
	
	public Categoria(String nome, Double valorDiaria) {
		this.setNome(nome);
		this.setValorDiaria(valorDiaria);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append("\n");
		retorno.append("Nome: ");
		retorno.append(this.getNome());
		retorno.append("\n");
		retorno.append("Descricao: ");
		retorno.append(this.getDescricao());
		retorno.append("\n");
		retorno.append("Valor Diária: ");
		retorno.append(this.getValorDiaria());
		retorno.append("\n\n");

		return retorno.toString();
	}

}
