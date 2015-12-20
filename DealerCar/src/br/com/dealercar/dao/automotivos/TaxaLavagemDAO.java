package br.com.dealercar.dao.automotivos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;
import br.com.dealercar.util.JSFUtil;

public class TaxaLavagemDAO implements IDAO<TaxaLavagem> {

	@Override
	public void cadastrar(TaxaLavagem taxaLavagem) {

		StringBuffer sql = new StringBuffer();

		sql.append("insert into taxa_lavagem (valor) values (?)");

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setDouble(++i, taxaLavagem.getValor());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(TaxaLavagem entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(TaxaLavagem taxaLavagem) {
		StringBuffer sql = new StringBuffer();

		sql.append("update taxa_lavagem set valor = ? where id = ?");

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setDouble(++i, taxaLavagem.getValor());
			pstm.setInt(++i, taxaLavagem.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public List<TaxaLavagem> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxaLavagem pesquisarPorID(TaxaLavagem taxaLavagem) {
		
		TaxaLavagem taxaRetorno = null;
		
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from taxa_lavagem where id = ?");

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, taxaLavagem.getId());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				taxaRetorno = new TaxaLavagem();
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