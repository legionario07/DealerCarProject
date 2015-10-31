package br.com.dealercar.domain;

/**
 * 
 * @author Paulinho
 * Classe que possui os usuários e senhas do sistema
 */
public class Usuario extends EntidadeDominio {
	
	private int id;
	private String login;
	private String senha;
	private String ativo;
	

	private Permissao permissao;
	
	
	public Usuario() {
	}
	
	public Usuario(int id) {
		this.setId(id);
	}
	
	public Usuario(String login, String senha, Permissao permissao, String ativo) {
		this.setLogin(login);
		this.setSenha(senha);
		this.setPermissao(permissao);
		this.setAtivo(ativo);
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
