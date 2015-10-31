package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Permissao;
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
		sql.append("insert into usuarios (login, senha, id_permissao, ativo) values ( ?, md5(?), ?, ?)");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			ps.setString(++i, usuario.getLogin());
			ps.setString(++i, usuario.getSenha());
			ps.setInt(++i, usuario.getPermissao().getId());
			ps.setString(++i, usuario.getAtivo());
			
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
		sql.append("update usuarios set login = ?, senha = md5( ? ), id_permissao = ?, ativo = ? where login = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int i = 0;
			
			ps.setString(++i, usuario.getLogin());
			ps.setString(++i, usuario.getSenha());
			ps.setInt(++i, usuario.getPermissao().getId());
			ps.setString(++i, usuario.getAtivo());
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
		sql.append("select usuarios.id, usuarios.login, usuarios.senha, usuarios.id_permissao, ");
		sql.append("usuarios.ativo, permissao.id, permissao.nivel from usuarios ");
		sql.append("inner join permissao on permissao.id = usuarios.id_permissao ");
		sql.append("where usuarios.id = ? ");
		
		Usuario usuarioRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, usuario.getId());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rSet.getInt("usuarios.id"));
				usuarioRetorno.setLogin(rSet.getString("usuarios.login"));
				usuarioRetorno.setSenha(rSet.getString("usuarios.senha"));
				usuarioRetorno.setAtivo(rSet.getString("usuarios.ativo"));
				
				Permissao permissao = new Permissao();
				permissao.setId(rSet.getInt("usuarios.id_permissao"));
				permissao.setNivel(rSet.getString("permissao.nivel"));
				
				usuarioRetorno.setPermissao(permissao);
				
				
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
		
		sql.append("select usuarios.id, usuarios.login, usuarios.senha, usuarios.id_permissao, ");
		sql.append("usuarios.ativo, permissao.id, permissao.nivel from usuarios ");
		sql.append("inner join permissao on permissao.id = usuarios.id_permissao ");
		sql.append("where usuarios.login = ? ");
		
		Usuario usuarioRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, usuario.getLogin());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rSet.getInt("usuarios.id"));
				usuarioRetorno.setLogin(rSet.getString("usuarios.login"));
				usuarioRetorno.setSenha(rSet.getString("usuarios.senha"));
				usuarioRetorno.setAtivo(rSet.getString("usuarios.ativo"));
				
				Permissao permissao = new Permissao();
				permissao.setId(rSet.getInt("usuarios.id_permissao"));
				permissao.setNivel(rSet.getString("permissao.nivel"));
				
				usuarioRetorno.setPermissao(permissao);
				
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

		StringBuffer sql = new StringBuffer();
		
		sql.append("select usuarios.id, usuarios.login, usuarios.senha, usuarios.id_permissao, ");
		sql.append("usuarios.ativo, permissao.id, permissao.nivel from usuarios ");
		sql.append("inner join permissao on permissao.id = usuarios.id_permissao ");
		sql.append("order by usuarios.id asc ");
		
		List<Usuario> usuarios = new ArrayList<Usuario>();

		Connection con = Conexao.getConnection();

		try {

			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Usuario usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rSet.getInt("usuarios.id"));
				usuarioRetorno.setLogin(rSet.getString("usuarios.login"));
				usuarioRetorno.setSenha(rSet.getString("usuarios.senha"));
				usuarioRetorno.setAtivo(rSet.getString("usuarios.ativo"));
				
				Permissao permissao = new Permissao();
				permissao.setId(rSet.getInt("usuarios.id_permissao"));
				permissao.setNivel(rSet.getString("permissao.nivel"));

				usuarioRetorno.setPermissao(permissao);
				
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


