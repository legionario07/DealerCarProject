package br.com.dealercar.domain;

import java.util.Date;

public abstract class Pessoa extends EntidadeDominio{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private Date dataNasc;
	private String celular;
	private String telefone;
	private Endereco endereco;
	private String sexo;
	
	public Pessoa(){
	
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
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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
		retorno.append(" - Telefone: ");
		retorno.append(this.getTelefone());
		retorno.append(" - Celular: ");
		retorno.append(this.getCelular());
		retorno.append(" - Endereço: ");
		retorno.append(this.getEndereco());
		retorno.append(" - Cidade: ");
		retorno.append(this.getEndereco().getCidade().getNome());

		return retorno.toString();
	}


}
