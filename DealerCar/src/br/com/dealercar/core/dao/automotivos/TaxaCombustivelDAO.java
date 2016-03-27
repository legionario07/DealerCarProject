package br.com.dealercar.core.dao.automotivos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.taxasadicionais.TaxaCombustivel;

public class TaxaCombustivelDAO implements IDAO,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;

	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxaCombustivel))
			return;
		
		TaxaCombustivel taxaCombustivel = new TaxaCombustivel();
		taxaCombustivel = (TaxaCombustivel) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into taxa_combustivel (valor) values (?)");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setDouble(++i, taxaCombustivel.getValor());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxaCombustivel))
			return;
		
		TaxaCombustivel taxaCombustivel = new TaxaCombustivel();
		taxaCombustivel = (TaxaCombustivel) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update taxa_combustivel set valor = ? where id = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setDouble(++i, taxaCombustivel.getValor());
			pstm.setInt(++i, taxaCombustivel.getId());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxaCombustivel pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxaCombustivel))
			return null;
		
		TaxaCombustivel taxaCombustivel = new TaxaCombustivel();
		taxaCombustivel = (TaxaCombustivel) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from taxa_combustivel where id = ?");

		TaxaCombustivel taxaRetorno = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, taxaCombustivel.getId());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				taxaRetorno = new TaxaCombustivel();
				taxaRetorno.setId(rSet.getInt("id"));
				taxaRetorno.setValor(rSet.getDouble("valor"));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return taxaRetorno;

	}

}