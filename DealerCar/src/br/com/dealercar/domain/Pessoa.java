package br.com.dealercar.domain;

import java.util.Date;

/**
 * Classe que representa uma pessoa, herdam dela funcionario e cliente
 * @author Paulinho
 *
 */
public abstract class Pessoa extends EntidadeDominio{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private Date dataNasc;
	private Endereco endereco;
	private String sexo;
	
	public Pessoa(){
	
	}
	

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public String toString() {
		
		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append(" - Nome: ");
		retorno.append(this.getNome());
		retorno.append(" - Data de Nascimento: ");
		retorno.append(this.getDataNasc());
		retorno.append(" - Sexo: ");
		retorno.append(this.getSexo());
		retorno.append(" - Endereço: ");
		retorno.append(this.getEndereco());
		retorno.append(" - Cidade: ");
		retorno.append(this.getEndereco().getCidade().getNome());

		return retorno.toString();
	}


}
