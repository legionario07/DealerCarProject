package br.com.dealercar.core.autenticacao;

import br.com.dealercar.domain.Cargo;
import br.com.dealercar.domain.Endereco;
import br.com.dealercar.domain.Pessoa;

/**
 * Classe que representa o Funcionario da locadora
 * @author Paulinho
 *
 */
public class Funcionario extends Pessoa {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cargo cargo;
	private Double salario;
	private Usuario usuario;

	public Funcionario(){
		this.setEndereco(new Endereco());
		this.setUsuario(new Usuario());
		this.setCargo(new Cargo());
	}
	/**
	 * 
	 * @param id
	 */
	public Funcionario(int id) {
		this.setId(id);
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setCargo(Cargo cargo) {
			this.cargo = cargo;
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
