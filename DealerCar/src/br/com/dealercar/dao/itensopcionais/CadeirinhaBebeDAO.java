package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.AbstractPesquisaItensOpcionais;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class CadeirinhaBebeDAO extends AbstractPesquisaItensOpcionais<CadeirinhaBebe> {


	/**
	 * 
	 * @param CadeirinhaBebe Recebe um CadeirinhaBebe como parametro e cadastra no Banco de Dados
	 */
	public void cadastrar(CadeirinhaBebe cadeirinha) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into cadeirinhas_bebe (descricao, valor, marca, numero_patrimonio, peso_bebe) ");
		sql.append("values (?, ?, ?, ?, ?)");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, cadeirinha.getDescricao());
			ps.setDouble(2, cadeirinha.getValor());
			ps.setString(3, cadeirinha.getMarca());
			ps.setString(4, cadeirinha.getNumeroPatrimonio());
			ps.setFloat(5, cadeirinha.getPesoBebe());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

	/**
	 * 
	 * @param CadeirinhaBebe Recebe um CadeirinhaBebe e exclui do Banco de Dados pelo seu Codigo
	 */
	public void excluir(CadeirinhaBebe cadeirinha) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from cadeirinhas_bebe where codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, cadeirinha.getCodigo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param CadeirinhaBebe Recebe um CadeirinhaBebe e edita de acordo com seu codigo
	 */
	public void editar(CadeirinhaBebe cadeirinha) {
		StringBuffer sql = new StringBuffer();
		sql.append("update cadeirinhas_bebe set descricao = ?, valor = ?, ");
		sql.append("marca = ?, numero_patrimonio = ?, peso_bebe = ? where codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, cadeirinha.getDescricao());
			ps.setDouble(2, cadeirinha.getValor());
			ps.setString(3, cadeirinha.getMarca());
			ps.setString(4, cadeirinha.getNumeroPatrimonio());
			ps.setFloat(5, cadeirinha.getPesoBebe());
			ps.setInt(6, cadeirinha.getCodigo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return Lista os CadeirinhaBebe cadastrados no Banco e exibe em forma de List<CadeirinhaBebe>
	 */
	public List<CadeirinhaBebe> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cadeirinhas_bebe");
		
		List<CadeirinhaBebe> listaRetorno = new ArrayList<CadeirinhaBebe>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				CadeirinhaBebe cadeirinha = new CadeirinhaBebe();
				cadeirinha.setCodigo(rSet.getInt("codigo"));
				cadeirinha.setDescricao(rSet.getString("descricao"));
				cadeirinha.setValor(rSet.getDouble("valor"));
				cadeirinha.setMarca(rSet.getString("marca"));
				cadeirinha.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				cadeirinha.setPesoBebe(rSet.getInt("peso_bebe"));
				
				listaRetorno.add(cadeirinha);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaRetorno;
			
	}
	


	/**
	 * 
	 * @param CadeirinhaBebe Recebe um Objeto CadeirinhaBebe e localiza no Banco de Dados
	 * pelo Codigo
	 * @return Retorna um objeto de CadeirinhaBebe
	 */
	@Override
	public CadeirinhaBebe pesquisarPorCodigo(CadeirinhaBebe cadeirinhaBebe) {
	
		StringBuffer sql = new StringBuffer();
		sql.append("select * from cadeirinhas_bebe where codigo = ?");
		
		CadeirinhaBebe cadeirinhaRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, cadeirinhaBebe.getCodigo());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				cadeirinhaRetorno = new CadeirinhaBebe();
				cadeirinhaRetorno.setCodigo(rSet.getInt("codigo"));
				cadeirinhaRetorno.setDescricao(rSet.getString("descricao"));
				cadeirinhaRetorno.setValor(rSet.getDouble("valor"));
				cadeirinhaRetorno.setMarca(rSet.getString("marca"));
				cadeirinhaRetorno.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				cadeirinhaRetorno.setPesoBebe(rSet.getInt("peso_bebe"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return cadeirinhaRetorno;
	}

	@Override
	public CadeirinhaBebe pesquisarPorID(CadeirinhaBebe entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
