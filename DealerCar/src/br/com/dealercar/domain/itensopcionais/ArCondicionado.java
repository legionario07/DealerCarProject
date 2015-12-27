package br.com.dealercar.domain.itensopcionais;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe que representa um ARCondicionado disponivel
 * para escolha do cliente no momento da Locação
 * @author Paulinho
 *
 */
public class ArCondicionado extends EntidadeDominio {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String descricao;
	private double valor;

	public ArCondicionado() {

	}

	public ArCondicionado(String descricao, double valor) {
		this.setDescricao(descricao);
		this.setValor(valor);
	}

	public ArCondicionado(int codigo) {
		this.setCodigo(codigo);
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

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();

		if (this.codigo != 99) {
			retorno.append("Codigo: ");
			retorno.append(this.getCodigo());
			retorno.append("\nDescricao: ");
			retorno.append(this.getDescricao());
			retorno.append("\nValor: ");
			retorno.append(this.getValor());
		}

		return retorno.toString();
	}
}
