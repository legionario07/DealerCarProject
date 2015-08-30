package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Fabricante;
import br.com.dealercar.domain.Modelo;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class ModeloDAO {
	
	public void cadastrar(Modelo modelo, Fabricante fabricante) {
		String sql = "insert into modelos (nome, id_fabricante) values (?, ?)";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, modelo.getNome().toUpperCase());
			ps.setInt(2, fabricante.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void editar(Modelo modelo, Fabricante fabricante) {
		String sql = "update modelos set nome = ?, id_fabricante = ? where id = ?";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, modelo.getNome().toUpperCase());
			ps.setInt(2, fabricante.getId());
			ps.setLong(3, modelo.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	public void excluir(Modelo modelo) {
		String sql = "delete from modelos where id = ?";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, modelo.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	public Modelo pesquisarPorID(Modelo modelo) {
		String sql = "select  modelos.id, modelos.nome, modelos.id_fabricante, fabricantes.id, fabricantes.nome"
				+ " from modelos inner join fabricantes where modelos.id = ?";
		Modelo modeloRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, modelo.getId());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				modeloRetorno = new Modelo();
				modeloRetorno.setId(rSet.getInt("modelos.id"));
				modeloRetorno.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("fabricantes.id"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				
				modeloRetorno.setFabricante(fabricante);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return modeloRetorno;
	}
	
	public List<Modelo> listarTodos() {
		
		String sql = "select  modelos.id, modelos.nome, modelos.id_fabricante, fabricantes.id, fabricantes.nome"
				+ " from modelos inner join fabricantes where modelos.id_fabricante = fabricantes.id";
		
		List<Modelo> listaRetorno = new ArrayList<Modelo>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				Modelo modelo = new Modelo();
				modelo.setId(rSet.getInt("modelos.id"));
				modelo.setNome(rSet.getString("modelos.nome"));
				modelo.setId(rSet.getInt("modelos.id_fabricante"));
				
				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("fabricantes.id"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				
				modelo.setFabricante(fabricante);
				
				listaRetorno.add(modelo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaRetorno;
	
	}
}
