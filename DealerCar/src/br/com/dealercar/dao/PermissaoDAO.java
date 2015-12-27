package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.autenticacao.Permissao;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe que persiste as Permissões no BD
 * @author Paulinho
 *
 */
public class PermissaoDAO implements IDAO<Permissao>{

	/**
	 * Cadastra uma nova Permissão no Banco de Dados
	 */
	@Override
	public void cadastrar(Permissao permissao) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into permissao ");
		sql.append("(nivel) values (?)");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, permissao.getNivel().toUpperCase());
			pstm.executeUpdate();
			pstm.close();
			con.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

	@Override
	public void excluir(Permissao permissao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Permissao permissao) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Lista todas as permissões do Banco de Dados
	 * @return Uma lista de Permissao
	 */
	@Override
	public List<Permissao> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from permissao");
		
		List<Permissao> listaPermissao = new ArrayList<Permissao>();
		
		Connection con = Conexao.getConnection();
		
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
	public Permissao pesquisarPorID(Permissao permissao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from permissao");
		
		Permissao permissaoRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
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
