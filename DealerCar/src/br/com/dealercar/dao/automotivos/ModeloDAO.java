package br.com.dealercar.dao.automotivos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class ModeloDAO implements IDAO<Modelo>{
	
	/**
	 * 
	 * @param modelo Recebe um objeto de Modelo e cadastra no BD 
	 * @param fabricante
	 */
	public void cadastrar(Modelo modelo) {
		String sql = "insert into modelos (nome, id_fabricante) values (?, ?)";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, modelo.getNome().toUpperCase());
			ps.setInt(2, modelo.getFabricante().getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param modelo Recebe um objeto de Modelo e edita no BD seus dados 
	 * @param fabricante
	 */
	public void editar(Modelo modelo) {
		String sql = "update modelos set nome = ?, id_fabricante = ? where id = ?";
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, modelo.getNome().toUpperCase());
			ps.setInt(2, modelo.getFabricante().getId());
			ps.setLong(3, modelo.getId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param modelo Recebe um objeto de Modelo e exclui do BD 
	 * localizando por seu ID
	 */
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
	
	/**
	 * 
	 * @param modelo Recebe um objeto de Modelo e localiza no
	 * BD de acordo com seu ID
	 * @return Retorna um objeto de Modelo
	 */
	public Modelo pesquisarPorID(Modelo modelo) {
		StringBuffer sql = new StringBuffer();
		sql.append("select  modelos.id, modelos.nome, modelos.id_fabricante, ");
		sql.append("fabricantes.id, fabricantes.nome ");
		sql.append("from modelos inner join fabricantes where modelos.id = ?");
		
		Modelo modeloRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
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
			
			rSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return modeloRetorno;
	}
	
	/**
	 * 
	 * @return Localiza todos os Modelos cadastrados no BD e retorna em forma de List<Modelo>
	 */
	public List<Modelo> listarTodos() {
		
		String sql = "select  modelos.id, modelos.nome, modelos.id_fabricante, fabricantes.nome"
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
				
				Fabricante fabricante = new Fabricante();
				fabricante.setId(rSet.getInt("modelos.id_fabricante"));
				fabricante.setNome(rSet.getString("fabricantes.nome"));
				
				modelo.setFabricante(fabricante);
				
				listaRetorno.add(modelo);
			}
			
			rSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaRetorno;
	
	}
}
