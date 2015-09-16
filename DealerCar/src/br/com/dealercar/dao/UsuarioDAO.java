package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Usuario;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class UsuarioDAO implements IDAO<Usuario>{
	
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
	 * @param usuario Recebe um Usuario como parametro e edita seus dados no BD
	 */
	public void editar(Usuario usuario) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update usuarios set login = ?, senha = md5( ? ) where login = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			ps.setString(++i, usuario.getLogin());
			ps.setString(++i, usuario.getSenha());
			ps.setString(++i, usuario.getLogin());
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
			
			rSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return usuarioRetorno;
	}
	
	/**
	 * 
	 * @param usuario recebe um objeto Usuario e excluir do Banco de Dados pelo Id
	 * @return
	 */
	
	public void excluir(Usuario usuario) {

		String sql = "delete from usuarios where id = ?";

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, usuario.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @param usuario recebe um objeto Usuario e pesquisa pelo login no Banco de Dados
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
			
			rSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return usuarioRetorno;
	}
	
	/**
	 * 
	 * @return Retorna um array de Usuario
	 */
	@Override
	public List<Usuario> listarTodos() {

		String sql = "select * from usuarios";
		List<Usuario> usuarios = new ArrayList<Usuario>();

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Usuario usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));

				usuarios.add(usuarioRetorno);

			}
			
			rSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return usuarios;

	}
}


