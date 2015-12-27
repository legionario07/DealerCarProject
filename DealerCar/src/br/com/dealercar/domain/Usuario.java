package br.com.dealercar.domain;

import br.com.dealercar.autenticacao.Permissao;

/**
 * 
 * @author Paulinho
 * Classe que possui os usuários e senhas do sistema
 */
public class Usuario extends EntidadeDominio {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String senha;
	private String ativo;
	

	private Permissao permissao;
	
	public Usuario(){
		this.permissao = new Permissao();
		
	}
	
	public Usuario (int id){
		this.permissao = new Permissao(id);
	}
	
	public Usuario(int id, String nivel) {
		this.permissao = new Permissao(id, nivel);
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getAtivo() {
		return ativo;
	}
	
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
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
		retorno.append("\n");
		retorno.append(this.getPermissao().toString());
		retorno.append("\n");
		retorno.append("Ativo: ");
		retorno.append(this.getAtivo());
		
		return retorno.toString();
	}
	
}
