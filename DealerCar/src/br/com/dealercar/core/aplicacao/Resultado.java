package br.com.dealercar.core.aplicacao;

import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

public class Resultado extends EntidadeAplicacao {

	private String msg;
	private List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
	
	/**
	 * Método de recuperação do campo msg
	 *
	 * @return valor do campo msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * Valor de msg atribuído a msg
	 *
	 * @param msg Atributo da Classe
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Método de recuperação do campo entidades
	 *
	 * @return valor do campo entidades
	 */
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	/**
	 * Valor de entidades atribuído a entidades
	 *
	 * @param entidades Atributo da Classe
	 */
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	
}
