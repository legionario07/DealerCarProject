package br.com.dealercar.domain;

import java.util.Date;

public class Funcionario extends Pessoa {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cargo;
	private Double salario;
	private Usuario usuario;

	public Funcionario(){
		
	}
	
	public Funcionario(int id) {
		this.setId(id);
	}
	
	/**
	 * 
	 * @param nome
	 * @param dataNasc
	 * @param sexo
	 * @param telefone
	 * @param endereco
	 * @param cidade
	 * @param cargo
	 * @param salario
	 * @param usuario
	 */
	public Funcionario(String nome, Date dataNasc, String sexo, String telefone, 
			 Endereco endereco, Cidade cidade, String cargo, Double salario, Usuario usuario) {
		
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
		this.getEndereco().setCidade(cidade);
		this.setCargo(cargo);
		this.setSalario(salario);
		this.setUsuario(usuario);
		
	}
	
	/**
	 * 
	 * @param nome
	 * @param dataNasc
	 * @param sexo
	 * @param telefone
	 * @param celular
	 * @param endereco
	 * @param cidade
	 * @param cargo
	 * @param salario
	 * @param usuario
	 */
	public Funcionario(String nome, Date dataNasc, String sexo, String telefone,
			Endereco endereco, Cidade cidade, String celular, String cargo, Double salario,  Usuario usuario) {
		
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setCelular(celular);
		this.setEndereco(endereco);
		this.getEndereco().setCidade(cidade);
		this.setCargo(cargo);
		this.setSalario(salario);
		this.setUsuario(usuario);
		
	}

	public String getCargo() {
		return cargo.toUpperCase();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setCargo(String cargo) {
		//verificando se o banco retorna null para o campo cargo
		if(cargo != null){
			this.cargo = cargo.toUpperCase();
		} else {
			this.cargo = cargo;
		}
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		
		StringBuffer retorno = new StringBuffer();
		retorno.append(super.toString());
		retorno.append(" - Cargo: ");
		retorno.append(this.getCargo());
		retorno.append(" - Salário: ");
		retorno.append(this.getSalario());
		retorno.append(" - Login: ");
		retorno.append(this.getUsuario().getLogin());
		retorno.append("\n");
		
		return retorno.toString();
	}

}
