package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Fabricante;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class FabricanteDAO {

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
