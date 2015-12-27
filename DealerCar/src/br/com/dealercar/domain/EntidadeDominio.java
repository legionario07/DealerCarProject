package br.com.dealercar.domain;

import java.io.Serializable;

/**
 * 
 * @author Paulinho
 * 
 * Classe que as classe dominio do projeto herdam para poderem implementar as persistencias no sistema
 *
 */
public abstract class EntidadeDominio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
