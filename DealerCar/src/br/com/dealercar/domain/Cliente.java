package br.com.dealercar.domain;

import java.io.Serializable;

/**
 * Classe que representa os clientes da Locadora
 * @author Paulinho
 *
 */
public class Cliente extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeMae;
	private String celular;
	private String telefone;
	private String RG;
	private String CPF;
	private String email;

	public Cliente() {
		this.setEndereco(new Endereco());

	}
	
	public Cliente (int id) {
		this.setId(id);
	}


	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		if(nomeMae.equals("")){
			this.nomeMae = nomeMae;
		}else{
			this.nomeMae = nomeMae.trim().toUpperCase();
		}
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		if(rG.equals("")){
			this.RG = rG;
		}else{
			this.RG = rG.trim().toUpperCase();
		}
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		if(CPF.equals("")){
			this.CPF = CPF;
		}else{
			this.CPF = CPF.trim().toUpperCase();
		}
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if(telefone.equals("")){
			this.telefone = telefone;
		}else{
			this.telefone = telefone.trim().toUpperCase();
		}
	}
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		if(celular.equals("")){
			this.celular = celular;
		}else{
			this.celular = celular.trim().toUpperCase();
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim();
	}


	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append(super.toString());
		retorno.append(" - Nome da Mãe: ");
		retorno.append(this.getNomeMae());
		retorno.append(" - Rg: ");
		retorno.append(this.getRG());
		retorno.append(" - Cpf: ");
		retorno.append(this.getCPF());
		retorno.append(" - Telefone: ");
		retorno.append(this.getTelefone());
		retorno.append(" - Celular: ");
		retorno.append(this.getCelular());
		retorno.append(" - Email: ");
		retorno.append(this.getEmail());
		retorno.append("\n");

		return retorno.toString();
	}

}
