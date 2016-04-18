package br.com.dealercar.core.dao;

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
public interface IDAO {
	
	/**
	 * Cadastra uma entidade Dominio
	 * @param entidade
	 */
	public void cadastrar(EntidadeDominio entidade);

	/**
	 * Exclui uma entidade Dominio
	 * @param entidade
	 */
	public void excluir(EntidadeDominio entidade);

	/**
	 * Edita uma entidade Dominio
	 * @param entidade
	 */
	public void editar(EntidadeDominio entidade);

	/**
	 * Lista uma entidade Dominio
	 * @return List<EntidadeDominio>
	 */
	public List<EntidadeDominio> listarTodos();

	/**
	 * Pesquisar atrav�s do ID uma entidade Dominio
	 * @param entidade
	 * @return uma EntidadeDominio
	 */
	public EntidadeDominio pesquisarPorID(EntidadeDominio entidade);

}
