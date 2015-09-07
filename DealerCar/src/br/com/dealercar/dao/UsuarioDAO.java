package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.dealercar.domain.Usuario;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class UsuarioDAO {
	
	/**
	 * 
	 * @param usuario Recebe um Usuario como parametro e cadastra no BD
	 */
	public void cadastrar(Usuario usuario) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into usuarios (login, senha) values ( ?, md5(?))");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			ps.setString(++i, usuario.getLogin());
			ps.setString(++i, usuario.getSenha());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param usuario Recebe um objeto usuario e retorna um usuarios valido
	 * @return
	 */
	public Usuario pesquisarPorID(Usuario usuario) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from usuarios where id = ? ");
		
		Usuario usuarioRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, usuario.getId());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return usuarioRetorno;
	}
	
	/**
	 * 
	 * @param usuario recebe um objeto Usuario e retorna um Usuario localizado pelo Login
	 * @return
	 */
	public Usuario pesquisarPorLogin(Usuario usuario) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from usuarios where login = ? ");
		
		Usuario usuarioRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, usuario.getLogin());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return usuarioRetorno;
	}
}

