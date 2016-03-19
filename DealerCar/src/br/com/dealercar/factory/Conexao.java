package br.com.dealercar.factory;

import java.sql.Connection;
import java.sql.SQLException;

//import javax.sql.DataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 * 
 * @return Retorna um Objeto de Connection quando a conexão válida ou uma
 *         exception se a conexao for invalida
 */
public abstract class Conexao {

	private static Connection con = null;

/*	private static final String url = "jdbc:mysql://localhost:3306/dealercar";
	private static final String usuario = "root";
	private static final String senha = "root";*/

	/*
	 * @return Retorna um Objeto de Connection quando a conexão válida ou uma
	 * exception se a conexao for invalida
	 */

	/*
	 * public static Connection getConnection2() { try { if (conexao == null ||
	 * conexao.isClosed()) { try { Class.forName("com.mysql.jdbc.Driver");
	 * conexao = DriverManager.getConnection(url, usuario, senha);
	 * 
	 * } catch (SQLException | ClassNotFoundException e) { System.out.println(
	 * "Não foi possivel conectar com o Banco de Dados"); e.printStackTrace();
	 * JSFUtil.adicionarMensagemErro(
	 * "Não foi possivel conectar com o Banco de Dados");
	 * JSFUtil.adicionarMensagemErro(e.getMessage());
	 * 
	 * } } } catch (SQLException e) { e.printStackTrace();
	 * JSFUtil.adicionarMensagemErro(e.getMessage()); } return conexao;
	 * 
	 * }
	 */
	/**
	 * Fecha a conexao com o BD
	 * 
	 * /* public static void close(){
	 * 
	 * 
	 * try { if(!dataSource.isClosed() || conexao!=null){
	 * 
	 * conexao.close();
	 * 
	 * } } catch (SQLException e) { e.printStackTrace();
	 * JSFUtil.adicionarMensagemErro(e.getMessage()); }
	 * 
	 * }
	 * 
	 * 
	 * /**
	 * 
	 * @return Retorna um Objeto de Connection quando a conexão válida ou uma
	 *         exception se a conexao for invalida
	 */

	public static Connection getConnection() {

		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://localhost:3306/dealercar");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername("root");
		p.setPassword("root");
		
		
		p.setLogAbandoned(true);
		p.setInitialSize(10);
		p.setMaxActive(100);
		p.setMinIdle(10);
		
		DataSource dataSource = new DataSource();
		dataSource.setPoolProperties(p);
		try {
			if(con==null ||con.isClosed()){
				con =  dataSource.getConnection();
				return con;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;

	}

}
