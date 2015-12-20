package br.com.dealercar.bean;

public abstract class AbstractBean implements IBean{
	
	private boolean ehCadastrado = false;
	private boolean jaPesquisei = false;

	public boolean isEhCadastrado() {
		return ehCadastrado;
	}
	public void setEhCadastrado(boolean ehCadastrado) {
		this.ehCadastrado = ehCadastrado;
	}
	public boolean isJaPesquisei() {
		return jaPesquisei;
	}
	public void setJaPesquisei(boolean jaPesquisei) {
		this.jaPesquisei = jaPesquisei;
	}

}
