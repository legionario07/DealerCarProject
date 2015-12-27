package br.com.dealercar.domain;

import java.io.Serializable;

/**
 * classe que representa o endereço do cliente ou dos funcionarios
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
	public Endereco(String rua, String numero,  String bairro) {
		
		this.setRua(rua);
		this.setNumero(numero);
		this.setBairro(bairro);
		
	}
	
	/**
	 * 
	 * @param rua
	 * @param numero
	 * @param complemento
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
		this.rua = rua.toUpperCase();
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		if(complemento != null) {
			this.complemento = complemento.toUpperCase();
		} else {
			this.complemento = complemento;
		}
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
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
		retorno.append("Endereço: ");
		retorno.append(this.getRua());
		retorno.append(" - Numero: ");
		retorno.append(this.getNumero());
		retorno.append(" - Complemento: ");
		retorno.append(this.getComplemento());
		retorno.append(" - Bairro: ");
		retorno.append(this.getBairro());
		return retorno.toString();
	}
	
	
	
}
