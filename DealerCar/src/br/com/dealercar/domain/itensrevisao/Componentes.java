package br.com.dealercar.domain.itensrevisao;

import java.io.Serializable;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe herdadas pelos itens de Revisão
 * @author Paulinho
 *
 */
public class Componentes extends EntidadeDominio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String situacao;
	
	public Componentes() {
		
	}
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
	
		StringBuffer retorno = new StringBuffer();
		retorno.append("\nItens Verificados: ");
		
		return retorno.toString();
	}
}
