package br.com.dealercar.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dealercar.domain.Usuario;

@ManagedBean(name = "MBlogin")
@ViewScoped
public class LoginBean {
	
	private Usuario usuario;
	

}
