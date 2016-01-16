package br.com.dealercar.autenticacao;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dealercar.dao.FuncionarioDAO;
import br.com.dealercar.dao.UsuarioDAO;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Usuario;
import br.com.dealercar.util.JSFUtil;

@ManagedBean(name = "MBlogin")
@RequestScoped
/**
 * Controller responsavel por realizar a autenticação do Usuario
 * @author Paulinho
 *
 */
public class LoginBean {
	
	/**
	 * 
	 */
	private static Funcionario funcionario = new Funcionario();
	private Usuario usuario = new Usuario();
	private boolean estaLogado = false; 
	private FacesContext faces = FacesContext.getCurrentInstance();
	private ExternalContext exContext = faces.getExternalContext();
		
	public Usuario getUsuario() {
		return usuario;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public boolean isEstaLogado() {
		return estaLogado;
	}
	public void setLogado(boolean isLogado) {
		this.estaLogado = isLogado;
	}
	public void setFuncionario(Funcionario funcionario) {
		LoginBean.funcionario = funcionario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Verifica se o usuario eh valida e add o mesmo na sessão.
	 * @throws IOException
	 */
	public void autenticar(){
		usuario = new UsuarioDAO().autenticar(usuario);
		estaLogado = false;
		
		if(usuario != null){
			funcionario = new FuncionarioDAO().pesquisarPorUsuario(usuario);
			JSFUtil.adicionarMensagemSucesso(usuario.getLogin() + " Autenticado!");
			JSFUtil.adicionarMensagemSucesso("Bem vindo "+ funcionario.getNome());
			
			estaLogado = true;
			
			//add usuario na Session
			HttpSession sessao = (HttpSession) faces.getExternalContext().getSession(false);
			sessao.setAttribute("usuarioLogado", funcionario);
			
			
			
			//encaminha para tela de Reserva
			try {
				exContext.redirect("reserva.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
				JSFUtil.adicionarMensagemErro(e.getMessage());
			}
			
			
		}else{
			JSFUtil.adicionarMensagemErro("Login ou Usuario invalido!");
		}
	}
	
	/**
	 * Efetua logout removendo Usuario da Session
	 * @return
	 * @throws IOException 
	 */
	public void efetuarLogout() throws IOException{
		exContext.invalidateSession();
		JSFUtil.adicionarMensagemSucesso(funcionario.getNome() + " saiu com sucesso!");
		funcionario = new Funcionario();
		usuario = new Usuario();
		exContext.redirect("login.xhtml");
	}

}
