package br.com.dealercar.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.dao.UsuarioDAO;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Permissao;
import br.com.dealercar.domain.Usuario;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBlogin")
@ViewScoped
public class LoginBean {
	
	private Funcionario funcionario = new Funcionario();
	private Permissao permissao = new Permissao();
	private Usuario usuario = new Usuario();
	private boolean isLogado = false; 
		
	public Usuario getUsuario() {
		return usuario;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public boolean isLogado() {
		return isLogado;
	}
	public void setLogado(boolean isLogado) {
		this.isLogado = isLogado;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	public void testaLogin(){
		usuario = new UsuarioDAO().autenticar(usuario);
		isLogado = false;
		
		if(usuario != null){
			JSFUtil.adicionarMensagemSucesso("Cliente Autenticado!");
			isLogado = true;
		}else{
			JSFUtil.adicionarMensagemErro("Login ou Usuario invalido!");
		}
	}

}
