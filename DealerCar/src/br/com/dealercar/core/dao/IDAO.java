package br.com.dealercar.core.dao;

import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

/**
 * 
 * @author Paulinho
 *Interface padrão com os métodos de  Persistência
 * 		cadastrar(EntidadeDominio)
 * 		excluir(EntidadeDomiio)
 * 		editar(EntidadeDominio)
 * 		Listar<EntidadeDominio> listarTodos()
 * 		EntidadDominio pesquisarPorID(EntidadeDominio)
 * @param <T> Classe entidadeDominio
 */
public interface IDAO {
	
	
	void cadastrar(EntidadeDominio entidade);

	void excluir(EntidadeDominio entidade);

	void editar(EntidadeDominio entidade);

	List<EntidadeDominio> listarTodos();

	EntidadeDominio pesquisarPorID(EntidadeDominio entidade);

}
