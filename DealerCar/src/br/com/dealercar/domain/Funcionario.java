package br.com.dealercar.domain;

public class Funcionario extends Pessoa {
	private String cargo;
	private Double salario;

	public Funcionario(){
		
	}
	
	public Funcionario(String nome, String dataNasc, String sexo, String telefone, 
			String celular, String cargo, Double salario, String endereco, Cidade cidade) {
		
		this.setNome(nome);
		this.setDataNasc(dataNasc);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setCelular(celular);
		this.setEndereco(endereco);
		this.setCidade(cidade);
		this.setCargo(cargo);
		this.setSalario(salario);
		
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
