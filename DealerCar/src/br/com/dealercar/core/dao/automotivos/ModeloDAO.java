package br.com.dealercar.core.dao.automotivos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.domain.automotivos.Modelo;

/**
 * REaliza a persistencia os Modelos de Carros no BD
 * @author Paulinho
 *
 */
public class ModeloDAO implements IDAO, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;

	
	/**
	 * 
	 * @param modelo Recebe um objeto de Modelo e cadastra no BD 
	 * @param fabricante
	 */
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Modelo))
			return;
		
		Modelo modelo = new Modelo();
		modelo = (Modelo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into modelos ");
		sql.append("(nome, id_fabricante) values (?, ?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, modelo.getNome().toUpperCase());
			pstm.setInt(2, modelo.getFabricante().getId());
			pstm.executeUpdate();
			
			
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
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Modelo))
			return;
		
		Modelo modelo = new Modelo();
		modelo = (Modelo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update modelos set nome = ?, ");
		sql.append("id_fabricante = ? where id = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, modelo.getNome().toUpperCase());
			pstm.setInt(2, modelo.getFabricante().getId());
			pstm.setLong(3, modelo.getId());
			pstm.executeUpdate();
			
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
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Modelo))
			return;
		
		Modelo modelo = new Modelo();
		modelo = (Modelo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from modelos where id = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, modelo.getId());
			pstm.executeUpdate();
			
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
	public Modelo pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Modelo))
			return null;
		
		Modelo modelo = new Modelo();
		modelo = (Modelo) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from modelos ");
		sql.append("where id = ?");
		
		Modelo modeloRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, modelo.getId());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				modeloRetorno = new Modelo();
				modeloRetorno.setId(rSet.getInt("modelos.id"));
				modeloRetorno.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante(rSet.getInt("id_fabricante"));
				fabricante = new FabricanteDAO().pesquisarPorID(fabricante);
				modeloRetorno.setFabricante(fabricante);
				
			}
			
			
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
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql =  new StringBuffer();
		sql.append("select * from modelos");
		
		List<EntidadeDominio> listaRetorno = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				Modelo modeloRetorno = new Modelo();
				modeloRetorno.setId(rSet.getInt("modelos.id"));
				modeloRetorno.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante(rSet.getInt("id_fabricante"));
				fabricante = new FabricanteDAO().pesquisarPorID(fabricante);
				modeloRetorno.setFabricante(fabricante);
				
				listaRetorno.add(modeloRetorno);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaRetorno;
	
	}
	
	/**
	 * 
	 * @return Localiza todos os Modelos cadastrados no BD e retorna em forma de List<Modelo> 
	 * que estao disponiveis
	 */
	public List<EntidadeDominio> listarModelosDisponiveis() {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from carros ");
		sql.append("inner join modelos on modelos.id = carros.id_modelo ");
		sql.append("where carros.situacao = ? group by modelos.nome");
		
		List<EntidadeDominio> listaRetorno = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, "Disponivel");
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				Modelo modeloRetorno = new Modelo();
				modeloRetorno.setId(rSet.getInt("modelos.id"));
				modeloRetorno.setNome(rSet.getString("modelos.nome"));
				
				Fabricante fabricante = new Fabricante(rSet.getInt("id_fabricante"));
				fabricante = new FabricanteDAO().pesquisarPorID(fabricante);
				modeloRetorno.setFabricante(fabricante);
				
				listaRetorno.add(modeloRetorno);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaRetorno;
	
	}
}
