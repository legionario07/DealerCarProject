package br.com.dealercar.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.autenticacao.Permissao;
import br.com.dealercar.core.autenticacao.Usuario;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe que persiste as Permissões no BD
 * @author Paulinho
 *
 */
public class UsuarioDAO implements IDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	
	/**
	 * Cadastra uma nova Permissão no Banco de Dados
	 */
	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Usuario))
			return;
		
		Usuario usuario = new Usuario();
		usuario = (Usuario) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into usuarios ");
		sql.append("(login, senha) values (?, md5(?))");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		if(!(entidade instanceof Usuario))
			return;
		
		Usuario usuario = new Usuario();
		usuario = (Usuario) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update usuarios set ");
		sql.append("login = ?,  senha = md5(?) ");
		sql.append("where id = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, usuario.getLogin());
			pstm.setString(2, usuario.getSenha());
			pstm.setInt(1, usuario.getId());
			pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

	/**
	 * Lista todas as permissões do Banco de Dados
	 * @return Uma lista de Usuario
	 */
	@Override
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from usuarios");
		
		List<EntidadeDominio> listaPermissao = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			while(rSet.next()){
				Usuario usuario = new Usuario();
				usuario.setId(rSet.getInt("id"));
				usuario.setLogin(rSet.getString("login"));
				usuario.setSenha(rSet.getString("senha"));
				
				Permissao permissao = new Permissao(rSet.getInt("id_permissao"));
				permissao = new PermissaoDAO().pesquisarPorID(permissao);
				usuario.setPermissao(permissao);

				listaPermissao.add(usuario);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaPermissao;
	
	}

	
	/**
	 * Pesquisa uma permissão por seu numero de Identificação
	 */
	@Override
	public Usuario pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Usuario))
			return null;
		
		Usuario usuario = new Usuario();
		usuario = (Usuario) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from usuarios ");
		sql.append("where id = ?");
		
		Usuario usuarioRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, usuario.getId());
			ResultSet rSet = pstm.executeQuery();
			while(rSet.next()){
				
				usuarioRetorno = new Usuario();
				usuarioRetorno.setId(rSet.getInt("id"));
				usuarioRetorno.setLogin(rSet.getString("login"));
				usuarioRetorno.setSenha(rSet.getString("senha"));
				
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

}
