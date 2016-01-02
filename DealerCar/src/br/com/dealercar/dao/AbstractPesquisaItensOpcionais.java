package br.com.dealercar.dao;

import java.io.Serializable;

import br.com.dealercar.domain.EntidadeDominio;

public abstract class AbstractPesquisaItensOpcionais<T extends EntidadeDominio> implements IDAO<T>, Serializable{

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
	
	public abstract T pesquisarPorCodigo(T entidade);
	
}
