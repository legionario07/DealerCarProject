package br.com.dealercar.domain;

import java.io.Serializable;

/**
 * classe que representa o endere�o do cliente ou dos funcionarios
 * 
 * @author Paulinho
 *
 */
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;

	private Cidade cidade;

	public Endereco() {

		this.cidade = new Cidade();
	}

	/**
	 * 
	 * @param rua
	 * @param numero
	 * @param bairro
	 */
	public Endereco(String rua, String numero, String complemento, String bairro) {

		this.setRua(rua);
		this.setNumero(numero);
		this.setComplemento(complemento);
		this.setBairro(bairro);

	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		if (rua.equals("")) {
			this.rua = rua;
		} else {
			this.rua = rua.trim().toUpperCase();
		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero.equals("")) {
			this.numero = numero;
		} else {
			this.numero = numero.trim();
		}
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.trim().toUpperCase();
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
		retorno.append("Endere�o: ");
		retorno.append(this.getRua());
		retorno.append(" - Numero: ");
		retorno.append(this.getNumero());
		retorno.append(" - Complemento: ");
		retorno.append(this.getComplemento());
		retorno.append(" - Bairro: ");
		retorno.append(this.getBairro());
		retorno.append("\nCidade:\n");
		retorno.append(this.getCidade());
		return retorno.toString();
	}

}
