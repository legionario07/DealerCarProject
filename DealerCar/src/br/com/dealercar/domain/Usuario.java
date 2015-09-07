package br.com.dealercar.domain;

/**
 * 
 * @author Paulinho
 * Classe que possui os usuários e senhas do sistema
 */
public class Usuario {
	
	private int id;
	private String login;
	private String senha;
	
	public Usuario() {
	}
	
	public Usuario(int id) {
		this.setId(id);
	}
	
	public Usuario(String login, String senha) {
		this.setLogin(login);
		this.setSenha(senha);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login.toUpperCase();
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		
		StringBuffer retorno = new StringBuffer();
		retorno.append("Id: ");
		retorno.append(this.getId());
		retorno.append("\nLogin: ");
		retorno.append(this.getLogin());
		retorno.append("\nSenha: ");
		retorno.append(this.getSenha());
		retorno.append("\n\n");
		
		return retorno.toString();
	}
	
}
