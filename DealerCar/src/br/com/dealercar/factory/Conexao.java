package br.com.dealercar.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.dealercar.util.JSFUtil;

/**
 * 
 * @return Retorna um Objeto de Connection quando a conexão válida 
 * ou uma exception se a conexao for invalida
 */
public abstract class Conexao {

	private static final String url = "jdbc:mysql://localhost:3306/dealercar2";
	private static final String usuario = "root";
	private static final String senha = "root";
	
	private static Connection conexao = null;

	/**
	 * 
	 * @return Retorna um Objeto de Connection quando a conexão válida 
	 * ou uma exception se a conexao for invalida
	 */
	public static Connection getConnection() {
		try {
			if(conexao == null || conexao.isClosed()){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexao = DriverManager.getConnection(url, usuario, senha);
				
				System.out.println("Conectado com sucesso ao Banco de Dados!");
				
			} catch (SQLException  | ClassNotFoundException e) {
				System.out.println("Não foi possivel conectar com o Banco de Dados");
				e.printStackTrace();
				JSFUtil.adicionarMensagemErro("Não foi possivel conectar com o Banco de Dados");
				JSFUtil.adicionarMensagemErro(e.getMessage());
				
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		return conexao;
		
	}
	
	/**
	 * Fecha a conexao com o BD
	 */
	public static void close(){
		
		
		try {
			if(!conexao.isClosed() || conexao!=null){

				conexao.close();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

}
