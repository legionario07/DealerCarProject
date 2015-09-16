package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class ArCondionadoDAO implements IDAO<ArCondicionado>{
	
	
	/**
	 * 
	 * @param arCondicionado Recebe um arCondicionado e cadastra no Banco de Dados
	 */
	public void cadastrar(ArCondicionado ar) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into arcondicionados (descricao, valor) ");
		sql.append("values (?, ?)");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, ar.getDescricao());
			ps.setDouble(2, ar.getValor());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param arCondicionado Recebe um arCondcionado e exclui-o do Banco de Dados
	 * procurando-o pelo codigo
	 */
	public void excluir(ArCondicionado ar) {
	
		StringBuffer sql = new StringBuffer();
		sql.append("delete from arcondicionados where codigo = ?");
	
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, ar.getCodigo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}
	
	/**
	 * 
	 * @param ArCondicionado Recebe um Arcondiconado e edita-o no Banco de Dados
	 * Localizando-o pelo seu Codigo 
	 */
	public void editar(ArCondicionado ar) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update arcondicionados set descricao = ?, valor = ? where codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, ar.getDescricao());
			ps.setDouble(2, ar.getValor());
			ps.setInt(3, ar.getCodigo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return Lista todos os ArCondionados do Banco de Dados
	 * e retorna um array de ArCondicionado
	 */
	public List<ArCondicionado> listarTodos() {
		StringBuffer sql =  new StringBuffer();
		sql.append("select * from arcondicionados");
		
		List<ArCondicionado> listaRetorno = new ArrayList<ArCondicionado>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				ArCondicionado ar = new ArCondicionado();
				ar.setCodigo(rSet.getInt("codigo"));
				ar.setDescricao(rSet.getString("descricao"));
				ar.setValor(rSet.getDouble("valor"));
				
				listaRetorno.add(ar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaRetorno;
	}
	
	/**
	 * 
	 * @param ArCondicionado Recebe um Objeto ArCondionado e localiza no Banco de Dados
	 * pelo Codigo
	 * @return Retorna um objeto de ArCondicionado
	 */
	public ArCondicionado pesquisarPorID(ArCondicionado ar) {
	
		StringBuffer sql = new StringBuffer();
		sql.append("select * from arcondicionados where codigo = ?");
		
		ArCondicionado arRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, ar.getCodigo());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				arRetorno = new ArCondicionado();
				arRetorno.setCodigo(rSet.getInt("codigo"));
				arRetorno.setDescricao(rSet.getString("descricao"));
				arRetorno.setValor(rSet.getDouble("valor"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return arRetorno;
	}
	
	
}
