package br.com.dealercar.domain;

import java.util.List;

public class Categoria {

	private int id;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(Double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public Categoria validaCategoria(String categoria, List<Categoria> lista) {
		Categoria rCategoria = new Categoria(categoria);
		
		for(Categoria c : lista) {
			if(rCategoria.getNome().equals(c.getNome())){
				rCategoria.setId(c.getId());
				rCategoria.setDescricao(c.getDescricao());
				rCategoria.setValorDiaria(c.getValorDiaria());
			}
		}
		
		return rCategoria;
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
