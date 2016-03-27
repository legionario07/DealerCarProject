package br.com.dealercar.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.autenticacao.Permissao;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;

/**
 * Classe que persiste as Permissões no BD
 * @author Paulinho
 *
 */
public class PermissaoDAO implements IDAO, Serializable{

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
		
		if(!(entidade instanceof Permissao))
			return;
		
		Permissao permissao = new Permissao();
		permissao = (Permissao) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into permissao ");
		sql.append("(nivel) values (?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, permissao.getNivel().toUpperCase());
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * Lista todas as permissões do Banco de Dados
	 * @return Uma lista de Permissao
	 */
	@Override
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from permissao");
		
		List<EntidadeDominio> listaPermissao = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			while(rSet.next()){
				Permissao permissao = new Permissao();
				permissao.setId(rSet.getInt("id"));
				permissao.setNivel(rSet.getString("nivel"));
				
				listaPermissao.add(permissao);
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
	public Permissao pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Permissao))
			return null;
		
		Permissao permissao = new Permissao();
		permissao = (Permissao) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from permissao ");
		sql.append("where id = ?");
		
		Permissao permissaoRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, permissao.getId());
			ResultSet rSet = pstm.executeQuery();
			while(rSet.next()){
				permissaoRetorno = new Permissao();
				permissaoRetorno.setId(rSet.getInt("id"));
				permissaoRetorno.setNivel(rSet.getString("nivel"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return permissaoRetorno;
	}

}
