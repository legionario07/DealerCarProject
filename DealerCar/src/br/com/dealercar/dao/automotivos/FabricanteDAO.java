package br.com.dealercar.dao.automotivos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class FabricanteDAO {

	/**
	 * 
	 * @param fabricante Recebe um objeto de Fabricante e cadastra no BD
	 */
	public void cadastrar(Fabricante fabricante) {
		String sql = "insert into fabricantes (nome) values(?)";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fabricante.getNome().toUpperCase());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param fabricante Recebe um objeto de Fabricante e edita seus dados no BD
	 * localizando por seu ID
	 */
	public void editar(Fabricante fabricante) {
		String sql = "update fabricantes set nome = ? where id = ?";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fabricante.getNome().toUpperCase());
			ps.setInt(2, fabricante.getId());
		
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param fabricante Recebe um objeto de Fabricante e exclui do BD
	 * localizando por seu ID
	 */
	public void excluir (Fabricante fabricante) {
		String sql = "delete from fabricantes where id=?";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, fabricante.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @return Retorna todos os Fabricantes cadastrados no Bd em forma de List<Fabricante>
	 */
	public List<Fabricante> listarTodos() {
		String sql = "select * from fabricantes";
		List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()){
				Fabricante fRetorno = new Fabricante();
				fRetorno.setId(rSet.getInt("id"));
				fRetorno.setNome(rSet.getString("nome"));
				
				listaFabricantes.add(fRetorno);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaFabricantes;
	}
	
	/**
	 * 
	 * @param fabricante Recebe um Objeto Fabricante e localiza por seu Id no BD
	 * @return Retorna um objeto de Fabricante
	 */
	public Fabricante pesquisarPorID(Fabricante fabricante) {
		
		String sql = "select * from fabricantes where id=?";
		Fabricante fRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, fabricante.getId());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				fRetorno = new Fabricante();
				fRetorno.setId(rSet.getInt("id"));
				fRetorno.setNome(rSet.getString("nome"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return fRetorno;
		
	}
	
}
