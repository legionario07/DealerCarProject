package br.com.dealercar.dao;

import java.sql.Connection;

import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.factory.Conexao;

public abstract class AbstractDAO<T extends EntidadeDominio> implements IDAO<T> {

	static Connection con = Conexao.getConnection();
		
}
