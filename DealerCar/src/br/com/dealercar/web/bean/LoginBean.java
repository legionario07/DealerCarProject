package br.com.dealercar.web.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.FuncionarioDAO;
import br.com.dealercar.core.util.JSFUtil;

@ManagedBean(name = "MBlogin")
@RequestScoped
/**
 * Controller responsavel por realizar a autenticação do Usuario
 * 
 * @author Paulinho
 *
 */
public class LoginBean {

	/**
	 * 
	 */
	private static Funcionario funcionario = new Funcionario();
	private boolean estaLogado = false;
	private FacesContext faces = FacesContext.getCurrentInstance();
	private ExternalContext exContext = faces.getExternalContext();

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		LoginBean.funcionario = funcionario;
	}

	public boolean isEstaLogado() {
		return estaLogado;
	}

	public void setLogado(boolean isLogado) {
		this.estaLogado = isLogado;
	}



	/**
	 * Verifica se o usuario eh valida e add o mesmo na sessão.
	 * 
	 * @throws IOException
	 */
	public void autenticar() {
		funcionario = new FuncionarioDAO().autenticar(funcionario);
		estaLogado = false;

		if (funcionario != null) {
			JSFUtil.adicionarMensagemSucesso(funcionario.getUsuario().getLogin() + " Autenticado!");
			JSFUtil.adicionarMensagemSucesso("Bem vindo " + funcionario.getNome());

			estaLogado = true;

			// add usuario na Session
			HttpSession sessao = (HttpSession) faces.getExternalContext().getSession(false);
			sessao.setAttribute("usuarioLogado", funcionario);

			// encaminha para tela de Reserva
			try {
				if (funcionario.getUsuario().getPermissao().getId() == 3) {
					exContext.redirect("filarevisao.xhtml");
				} else {
					exContext.redirect("reserva.xhtml");
				}
			} catch (IOException e) {
				e.printStackTrace();
				JSFUtil.adicionarMensagemErro(e.getMessage());
			}

		} else {
			JSFUtil.adicionarMensagemErro("Login ou Usuario invalido!");
			funcionario = new Funcionario();
		}
	}

	/**
	 * Efetua logout removendo Usuario da Session
	 * 
	 * @return
	 * @throws IOException
	 */
	public void efetuarLogout() throws IOException {
		exContext.invalidateSession();
		JSFUtil.adicionarMensagemSucesso(funcionario.getNome() + " saiu com sucesso!");
		funcionario = new Funcionario();
		exContext.redirect("login.xhtml");
	}

}
