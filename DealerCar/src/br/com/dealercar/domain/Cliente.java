package br.com.dealercar.domain;

import java.io.Serializable;
import java.util.Date;

public class Cliente extends Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeMae;
	private String RG;
	private String CPF;
	private String email;

	public Cliente() {

	}
	
	public Cliente (int id) {
		this.setId(id);
	}

	/**
	 * 
	 * @param nome
	 * @param dataNasc
	 * @param nomeMae
	 * @param sexo
	 * @param telefone
	 * @param celular
	 * @param RG
	 * @param CPF
	 * @param email
	 * @param endereco
	 * @param cidade
	 */
	public Cliente(String nome, Date dataNasc, String nomeMae, String sexo, String telefone, 
			String celular, String RG, String CPF, String email, Endereco endereco, Cidade cidade) {
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setNomeMae(nomeMae);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setCelular(celular);
		this.setRG(RG);
		this.setCPF(CPF);
		this.setEmail(email);
		this.setEndereco(endereco);
		this.getEndereco().setCidade(cidade);
	}

	/**
	 * 
	 * @param nome
	 * @param dataNasc
	 * @param sexo
	 * @param telefone
	 * @param celular
	 * @param RG
	 * @param CPF
	 * @param endereco
	 * @param cidade
	 */
	public Cliente(String nome, Date dataNasc,  String sexo, String telefone, String celular, 
			String RG, String CPF, Endereco endereco, Cidade cidade) {
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setCelular(celular);
		this.setRG(RG);
		this.setCPF(CPF);
		this.setEndereco(endereco);
		this.getEndereco().setCidade(cidade);
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		retorno.append(super.toString());
		retorno.append(" - Nome da M�e: ");
		retorno.append(this.getNomeMae());
		retorno.append(" - Rg: ");
		retorno.append(this.getRG());
		retorno.append(" - Cpf: ");
		retorno.append(this.getCPF());
		retorno.append(" - Email: ");
		retorno.append(this.getEmail());
		retorno.append("\n");

		return retorno.toString();
	}

}
