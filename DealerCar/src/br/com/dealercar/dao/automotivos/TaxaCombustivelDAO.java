package br.com.dealercar.dao.automotivos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.taxasadicionais.TaxaCombustivel;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class TaxaCombustivelDAO implements IDAO<TaxaCombustivel> {

	@Override
	public void cadastrar(TaxaCombustivel taxaCombustivel) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into taxa_combustivel (valor) values (?)");
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setDouble(++i, taxaCombustivel.getValor());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(TaxaCombustivel entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(TaxaCombustivel taxaCombustivel) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update taxa_combustivel set valor = ? where id = ?");
		
		Connection con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setDouble(++i, taxaCombustivel.getValor());
			pstm.setInt(++i, taxaCombustivel.getId());
			pstm.executeUpdate();
			
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public List<TaxaCombustivel> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxaCombustivel pesquisarPorID(TaxaCombustivel taxaCombustivel) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from taxa_combustivel where id = ?");

		TaxaCombustivel taxaRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, taxaCombustivel.getId());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				taxaRetorno = new TaxaCombustivel();
				taxaRetorno.setId(rSet.getInt("id"));
				taxaRetorno.setValor(rSet.getDouble("valor"));
			}

			rSet.close();
			pstm.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return taxaRetorno;

	}

}
