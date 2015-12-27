package br.com.dealercar.dao;

import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

public abstract class AbstractPesquisaDAO<T extends EntidadeDominio> implements IDAO<T>{
	
	/**
	 * 
	 * @param entidade Recebe uma classe que Extends EntidadeDominio e retornar uma busca
	 * ao banco de Dados pelo seu Nome
	 * @return
	 */
	abstract List<T> pesquisarPorNome(T entidade);

	
}