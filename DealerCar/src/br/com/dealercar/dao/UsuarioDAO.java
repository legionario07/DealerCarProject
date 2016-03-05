package br.com.dealercar.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.autenticacao.Permissao;
import br.com.dealercar.domain.Usuario;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsável por persistir os Usuarios no BD
 * @author Paulinho
 *
 */
public class UsuarioDAO implements IDAO<Usuario>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection con = Conexao.getConnection();
	
	/**
	 * 
	 * @param usuario Recebe um Usuario como parametro e cadastra no BD
	 */
	public void cadastrar(Usuario usuario) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into usuarios (login, senha, id_permissao, ativo) values ( ?, md5(?), ?, ?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, usuario.getLogin());
			pstm.setString(++i, usuario.getSenha());
			pstm.setInt(++i, usuario.getPermissao().getId());
			pstm.setString(++i, usuario.getAtivo());
			
			pstm.executeUpdate();
			
			
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
		sql.append("update usuarios set login = ?, senha = md5(?), ");
		sql.append("id_permissao = ?, ativo = ? where id = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			
			pstm.setString(++i, usuario.getLogin());
			pstm.setString(++i, usuario.getSenha());
			pstm.setInt(++i, usuario.getPermissao().getId());
			pstm.setString(++i, usuario.getAtivo());
			pstm.setInt(++i, usuario.getId());
			
			pstm.executeUpdate();
			
			pstm.close();
			
			con.close();
			
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
		sql.append("select * from usuarios ");
		sql.append("where id = ? ");
		
		Usuario usuarioRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, usuario.getId());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				usuarioRetorno = new Usuario(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));
				usuarioRetorno.setAtivo(rSet.getString("ativo"));
				
				Permissao permissao = new Permissao(rSet.getInt("id_permissao"));
				permissao = new PermissaoDAO().pesquisarPorID(permissao);
				usuarioRetorno.setPermissao(permissao);
				
			}
			
			
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
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, usuario.getId());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

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
		
		sql.append("select * from usuarios ");
		sql.append("where login = ? ");
		
		Usuario usuarioRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, usuario.getLogin());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				usuarioRetorno = new Usuario(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));
				usuarioRetorno.setAtivo(rSet.getString("ativo"));
				
				Permissao permissao = new Permissao(rSet.getInt("id_permissao"));
				permissao = new PermissaoDAO().pesquisarPorID(permissao);
				usuarioRetorno.setPermissao(permissao);
				
			}
			
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
		
		sql.append("select * from usuarios ");
		sql.append("order by id asc ");
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		con = Conexao.getConnection();

		try {

			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
			
				Usuario usuarioRetorno = new Usuario(rSet.getInt("usuarios.id"));
				usuarioRetorno = new Usuario(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));
				usuarioRetorno.setAtivo(rSet.getString("ativo"));
				
				Permissao permissao = new Permissao(rSet.getInt("id_permissao"));
				permissao = new PermissaoDAO().pesquisarPorID(permissao);
				usuarioRetorno.setPermissao(permissao);
				
				usuarios.add(usuarioRetorno);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return usuarios;

	}
	
	/**
	 * 
	 * @param usuario recebe um objeto Usuario e pesquisa pelo login e senha no BD
	 * @return Retorna um Usuario ou Null se nao encontrar
	 */
	public Usuario autenticar(Usuario usuario) {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from usuarios ");
		sql.append("where login = ? and senha = md5(?) and ativo = 'SIM'");
		
		Usuario usuarioRetorno = null;

		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				usuarioRetorno = new Usuario(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));
				usuarioRetorno.setAtivo(rSet.getString("ativo"));
				
				Permissao permissao = new Permissao(rSet.getInt("id_permissao"));
				permissao = new PermissaoDAO().pesquisarPorID(permissao);
				usuarioRetorno.setPermissao(permissao);
				
			}
			
			rSet.close();
			pstm.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return usuarioRetorno;
	}
	
}


