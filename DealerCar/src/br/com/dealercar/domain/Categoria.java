package br.com.dealercar.domain;

public class Categoria {

	private int id;
	private String nome;
	private String descricao;
	private Double valorDiaria;

	public Categoria() {

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
		retorno.append("Valor Di�ria: ");
		retorno.append(this.getValorDiaria());
		retorno.append("\n\n");

		return retorno.toString();
	}

}
