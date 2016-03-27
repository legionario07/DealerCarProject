package br.com.dealercar.core.dao;

import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

public abstract class AbstractPesquisaDAO implements IDAO {
	
	/**
	 * 
	 * @param entidade Recebe uma classe que Extends EntidadeDominio e retornar uma busca
	 * ao banco de Dados pelo seu Nome
	 * @return
	 */
	abstract List<EntidadeDominio> pesquisarPorNome(EntidadeDominio entidade);

	
}