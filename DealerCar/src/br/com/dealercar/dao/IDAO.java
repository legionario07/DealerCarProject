package br.com.dealercar.dao;

import java.util.List;

import br.com.dealercar.domain.EntidadeDominio;

public interface IDAO<T extends EntidadeDominio> {
	void cadastrar(T entidade);
	void excluir(T entidade);
	void editar(T entidade);
	List<T> listarTodos();
	EntidadeDominio pesquisarPorID(T entidade);
	
	

}
