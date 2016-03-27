package br.com.dealercar.core.factory;

import java.sql.Connection;

public class TestaConexao {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Connection con = Conexao.getConnection();
	}

}
