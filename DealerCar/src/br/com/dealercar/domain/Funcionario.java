package br.com.dealercar.domain;

public class Funcionario extends Pessoa {
	
	private String cargo;
	private Double salario;
	private Usuario usuario;

	public Funcionario(){
		
	}
	
	public Funcionario(int id) {
		this.setId(id);
	}
	
	public Funcionario(String nome, String dataNasc, String sexo, String telefone, 
			String cargo, Double salario,  Cidade cidade, Usuario usuario) {
		
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setCidade(cidade);
		this.setCargo(cargo);
		this.setSalario(salario);
		this.setUsuario(usuario);
		
	}
	
	public Funcionario(String nome, String dataNasc, String sexo, String telefone, 
			 String endereco, String cargo, Double salario, Cidade cidade, Usuario usuario) {
		
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
		this.setCidade(cidade);
		this.setCargo(cargo);
		this.setSalario(salario);
		this.setUsuario(usuario);
		
	}
	
	
	public Funcionario(String nome, String dataNasc, String sexo, String telefone, 
			String celular, String cargo, Double salario, String endereco, Cidade cidade, Usuario usuario) {
		
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setCelular(celular);
		this.setEndereco(endereco);
		this.setCidade(cidade);
		this.setCargo(cargo);
		this.setSalario(salario);
		this.setUsuario(usuario);
		
	}

	public String getCargo() {
		return cargo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo.toUpperCase();
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
