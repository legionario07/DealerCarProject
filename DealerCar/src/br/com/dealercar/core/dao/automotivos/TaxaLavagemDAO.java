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
import br.com.dealercar.domain.taxasadicionais.TaxaLavagem;

public class TaxaLavagemDAO implements IDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;

	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxaLavagem))
			return;
		
		TaxaLavagem taxaLavagem = new TaxaLavagem();
		taxaLavagem = (TaxaLavagem) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into taxa_lavagem (valor) values (?)");
		
		con = Conexao.getConnection();

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
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		
		if(!(entidade instanceof TaxaLavagem))
			return;
		
		TaxaLavagem taxaLavagem = new TaxaLavagem();
		taxaLavagem = (TaxaLavagem) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update taxa_lavagem set valor = ? where id = ?");
		
		con = Conexao.getConnection();

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
	public List<EntidadeDominio> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaxaLavagem pesquisarPorID(EntidadeDominio entidade) {
		
		
		if(!(entidade instanceof TaxaLavagem))
			return null;
		
		TaxaLavagem taxaLavagem = new TaxaLavagem();
		taxaLavagem = (TaxaLavagem) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from taxa_lavagem where id = ?");

		TaxaLavagem taxaRetorno = null;
		
		con = Conexao.getConnection();
		
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
