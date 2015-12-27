package br.com.dealercar.autenticacao;

import java.io.Serializable;

import br.com.dealercar.domain.EntidadeDominio;

public class Permissao extends EntidadeDominio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nivel;

	public Permissao(){
		
	}
	public Permissao(int i){
		this.setId(i);
	}
	
	public Permissao(int i, String nivel){
		this.setId(i);
		this.setNivel(nivel);
	}
	
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {

		StringBuffer retorno = new StringBuffer();
			
		retorno.append("\nID: ");
		retorno.append(this.getNivel());
		retorno.append(" - Nível de Acesso: ");
		retorno.append(this.getNivel());
		
		return retorno.toString();
		
	}
	
}
