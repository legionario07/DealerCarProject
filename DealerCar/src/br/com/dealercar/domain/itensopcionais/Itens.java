package br.com.dealercar.domain.itensopcionais;

import br.com.dealercar.domain.EntidadeDominio;
/**
 * Classe que contem os atributos herdados pelos demais itens Opcionais
 * @author Paulinho
 *
 */
public class Itens extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		this.descricao = descricao.trim();
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
		this.marca = marca.trim().toUpperCase();
	}

	public String getNumeroPatrimonio() {
		return numeroPatrimonio;
	}

	public void setNumeroPatrimonio(String numeroPatrimonio) {
		this.numeroPatrimonio = numeroPatrimonio.trim().toUpperCase();
	}

	public String toString() {

		StringBuffer retorno = new StringBuffer();
		
		if (this.codigo != 99) {
			retorno.append("\nDescricao: ");
			retorno.append(this.getDescricao());
			retorno.append("\tValor: ");
			retorno.append(this.getValor());
		}
		
		return retorno.toString();
	}
	

}
