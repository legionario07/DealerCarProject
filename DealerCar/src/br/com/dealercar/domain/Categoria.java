package br.com.dealercar.domain;

public class Categoria {

	private int id;
	private String nome;
	private String descricao;
	private Double valorDiaria;

	public Categoria() {

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

	
	@Override
	public String toString() {
		String retorno;
		retorno = "Id: " + this.getId() + "\n";
		retorno += "Nome: " + this.getNome() + "\n";
		retorno += "Descricao: " + this.getDescricao() + "\n";
		retorno += "Valor Diária: " + this.getValorDiaria() + "\n\n";

		return retorno;
	}

}
