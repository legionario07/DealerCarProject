package br.com.dealercar.dao.automotivos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.automotivos.Fabricante;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel por realizar a Persistencia dos Fabricantes no BD
 * @author Paulinho
 *
 */
public class FabricanteDAO implements IDAO<Fabricante> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection con = Conexao.getConnection();
	
	/**
	 * 
	 * @param fabricante Recebe um objeto de Fabricante e cadastra no BD
	 */
	public void cadastrar(Fabricante fabricante) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into fabricantes (nome) values(?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, fabricante.getNome().toUpperCase());
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
	 * @param fabricante Recebe um objeto de Fabricante e edita seus dados no BD
	 * localizando por seu ID
	 */
	public void editar(Fabricante fabricante) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update fabricantes set nome = ? where id = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, fabricante.getNome().toUpperCase());
			pstm.setInt(2, fabricante.getId());
		
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
	 * @param fabricante Recebe um objeto de Fabricante e exclui do BD
	 * localizando por seu ID
	 */
	public void excluir (Fabricante fabricante) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from fabricantes where id=?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, fabricante.getId());
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
	 * @return Retorna todos os Fabricantes cadastrados no Bd em forma de List<Fabricante>
	 */
	public List<Fabricante> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from fabricantes");
		
		List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
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
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from fabricantes where id=?");
		
		Fabricante fRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, fabricante.getId());
			
			ResultSet rSet = pstm.executeQuery();
			
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
