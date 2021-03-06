package br.com.dealercar.core.dao.itensrevisao;

import java.io.Serializable;
import java.util.List;

import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe Abstract Responsável por disponibilzar o método Pesquisar por Marca 
 * @author Paulinho
 *
 * @param <T>
 */
public abstract class AbstractPesquisaItensRevisao<T extends EntidadeDominio> implements IDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param entidade Recebe uma entidade Dominio e pesquisar pela Marca
	 * @return Retorna uma Lista com a entidade dominio procurada
	 */
	public abstract List<T> pesquisarPorMarca(T produtoRevisao);
	/**
	 * 
	 * @param entidade recebe uma String
	 * @return um ProdutoRevisao localizado pela Descricao, Marca e Tipo
	 */
	public abstract T pesquisarPorDescricaoMarcaTipo(String produtoRevisao);
}
