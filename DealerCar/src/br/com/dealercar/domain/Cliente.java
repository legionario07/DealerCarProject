package br.com.dealercar.domain;

public class Cliente extends Pessoa {

	private String nomeMae;
	private String RG;
	private String CPF;
	private String email;

	public Cliente() {

	}

	public Cliente(String nome, String dataNasc, String nomeMae, String sexo, String telefone, String celular, String RG, String CPF,
			String email, String endereco, Cidade cidade) {
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
		this.setCidade(cidade);
	}

	public Cliente(String nome, String dataNasc,  String sexo, String telefone, String celular, String RG, String CPF,
			String endereco, Cidade cidade) {
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setCelular(celular);
		this.setRG(RG);
		this.setCPF(CPF);
		this.setEndereco(endereco);
		this.setCidade(cidade);
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
		String retorno;
		retorno = "Id: " + this.getId() + " - " + "Nome: " + this.getNome() + " - " + "Data de Nascimento: "
				+ this.getDataNasc() + " - " + "Nome da Mãe: " + this.getNomeMae() + " - "
				+ "Sexo: " + this.getSexo() + " - " + 
				"Telefone: " + this.getTelefone() + " - " + "Celular: " + this.getCelular() + " - " + "Rg: " + this.getRG() + " - "
				+ "Cpf: " + this.getCPF() + " - " + "Email: " + this.getEmail() + " - " + "Endereço: "
				+ this.getEndereco() + " - " + "Cidade: " + this.getCidade().getNome() + "\n";

		return retorno;
	}

}
