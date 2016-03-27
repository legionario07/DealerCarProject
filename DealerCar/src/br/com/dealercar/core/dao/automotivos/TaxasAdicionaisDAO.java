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
import br.com.dealercar.domain.taxasadicionais.TaxasAdicionais;

/**
 * Persiste os tipos de Taxas Adicionais no BD
 * @author Paulinho
 *
 */
public class TaxasAdicionaisDAO implements IDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;
	
	/**
	 * Cadastra as Taxas adicionais no Banco de DAdos
	 */
	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxasAdicionais))
			return;
		
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();
		taxaAdicional = (TaxasAdicionais) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into taxas_adicionais ");
		sql.append("(taxa, valor) values (?,?)");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, taxaAdicional.getDescricao());
			pstm.setDouble(++i, taxaAdicional.getValor());
			pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		
	}

	/**
	 * Exclui uma taxa Adicional do BD
	 */
	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxasAdicionais))
			return;
		
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();
		taxaAdicional = (TaxasAdicionais) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from taxas_adicionais ");
		sql.append("where taxa = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, taxaAdicional.getDescricao());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

	/**
	 * Edita uma taxa Adicional no Banco de Dados de acordo com seu id
	 * 
	 */
	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxasAdicionais))
			return;
		
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();
		taxaAdicional = (TaxasAdicionais) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update taxas_adicionais ");
		sql.append("set taxa = ?, valor = ? ");
		sql.append("where id = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, taxaAdicional.getDescricao());
			pstm.setDouble(++i, taxaAdicional.getValor());
			pstm.setInt(++i, taxaAdicional.getId());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
				
	}

	/**
	 * Lista todas as taxas adicionais do BD
	 */
	@Override
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from taxas_adicionais");
		
		List<EntidadeDominio> taxas = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				TaxasAdicionais taxa = new TaxasAdicionais();
				taxa.setId(rSet.getInt("id"));
				taxa.setDescricao(rSet.getString("taxa"));
				taxa.setValor(rSet.getDouble("valor"));
				
				taxas.add(taxa);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return taxas;
		
		
	}

	/**
	 * Retorna uma taxa adicional localizado por seu ID
	 */
	@Override
	public TaxasAdicionais pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof TaxasAdicionais))
			return null;
		
		TaxasAdicionais taxaAdicional = new TaxasAdicionais();
		taxaAdicional = (TaxasAdicionais) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from taxas_adicionais ");
		sql.append("where id = ?");

		TaxasAdicionais taxa = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, taxaAdicional.getId());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				taxa = new TaxasAdicionais();
				
				taxa.setId(rSet.getInt("id"));
				taxa.setDescricao(rSet.getString("taxa"));
				taxa.setValor(rSet.getDouble("valor"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return taxa;
		
	}
	
	
	
	/**
	 * Retorna uma taxa adicional localizado por seu nome
	 */
	public TaxasAdicionais pesquisarPorTaxa(String nome) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from taxas_adicionais ");
		sql.append("where taxa = ?");

		TaxasAdicionais taxa = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, nome);
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				taxa = new TaxasAdicionais();
				
				taxa.setId(rSet.getInt("id"));
				taxa.setDescricao(rSet.getString("taxa"));
				taxa.setValor(rSet.getDouble("valor"));
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return taxa;
		
	}

}
