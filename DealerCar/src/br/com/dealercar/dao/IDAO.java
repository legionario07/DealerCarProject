package br.com.dealercar.dao;

import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;
/**
 * 
 * @author Paulinho
 *Interface padr�o com os m�todos de  Persist�ncia
 * 		cadastrar(EntidadeDominio)
 * 		excluir(EntidadeDomiio)
 * 		editar(EntidadeDominio)
 * 		Listar<EntidadeDominio> listarTodos()
 * 		EntidadDominio pesquisarPorID(EntidadeDominio)
 * @param <T> Classe entidadeDominio
 */
public interface IDAO<T extends EntidadeDominio> {
	void cadastrar(T entidade);

	void excluir(T entidade);

	void editar(T entidade);

	List<T> listarTodos();

	T pesquisarPorID(T entidade);

}
