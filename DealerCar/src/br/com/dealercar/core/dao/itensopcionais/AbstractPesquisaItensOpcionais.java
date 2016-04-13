package br.com.dealercar.core.dao.itensopcionais;

import java.io.Serializable;

import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.domain.EntidadeDominio;

public abstract class AbstractPesquisaItensOpcionais implements IDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param entidade Recebe uma classe que Extends EntidadeDominio e retornar uma busca
	 * ao banco de Dados pelo seu Codigo
	 * @return
	 */
	
	public abstract EntidadeDominio pesquisarPorCodigo(EntidadeDominio entidade);
	
}
