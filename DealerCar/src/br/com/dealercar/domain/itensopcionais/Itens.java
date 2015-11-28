package br.com.dealercar.domain.itensopcionais;

import br.com.dealercar.domain.EntidadeDominio;

public class Itens extends EntidadeDominio {

	private int codigo;
	private String descricao;
	private double valor;
	private String marca;
	private String numeroPatrimonio;

	public Itens() {

	}

	public Itens(int codigo) {
		this.setCodigo(codigo);
	}

	public Itens(String descricao, double valor, String marca, String numeroPatrimonio) {
		this.setDescricao(descricao);
		this.setValor(valor);
		this.setMarca(marca);
		this.setNumeroPatrimonio(numeroPatrimonio);

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca.toUpperCase();
	}

	public String getNumeroPatrimonio() {
		return numeroPatrimonio.toUpperCase();
	}

	public void setNumeroPatrimonio(String numeroPatrimonio) {
		this.numeroPatrimonio = numeroPatrimonio.toUpperCase();
	}

	public String toString() {

		StringBuffer retorno = new StringBuffer();
		if (this.codigo != 99) {
			retorno.append("Codigo: ");
			retorno.append(this.getCodigo());
			retorno.append("\nDescricao: ");
			retorno.append(this.getDescricao());
			retorno.append("\nValor: ");
			retorno.append(this.getValor());
			retorno.append("\nMarca: ");
			retorno.append(this.getMarca());
			retorno.append("\nNúmero Patrimônio: ");
			retorno.append(this.getNumeroPatrimonio());
		}
		return retorno.toString();
	}

}
