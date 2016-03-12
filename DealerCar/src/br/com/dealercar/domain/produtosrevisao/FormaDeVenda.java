package br.com.dealercar.domain.produtosrevisao;

import br.com.dealercar.domain.EntidadeDominio;

public class FormaDeVenda extends EntidadeDominio{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	@Override
	public String toString() {
		
		StringBuffer retorno = new StringBuffer();
		
		retorno.append("\nId: ");
		retorno.append(this.getId());
		retorno.append(" - Forma de Venda: ");
		retorno.append(this.getDescricao());
		
		return retorno.toString();
	}
}
