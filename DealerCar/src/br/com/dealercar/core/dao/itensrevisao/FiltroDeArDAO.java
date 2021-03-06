package br.com.dealercar.core.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.produtosrevisao.FiltroDeAr;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

/**
 * Classe responsavel pelo gerenciamento dos filtros de Ar no Banco
 * 
 * @author Paulinho
 *
 */
public class FiltroDeArDAO extends AbstractPesquisaItensRevisao<FiltroDeAr> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeAr))
			return;
		
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr = (FiltroDeAr) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into filtro_de_ar ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, filtroDeAr.getDescricao().toUpperCase());
			pstm.setString(++i, filtroDeAr.getMarca().toUpperCase());
			pstm.setString(++i, filtroDeAr.getTipo().toUpperCase());
			pstm.setInt(++i, filtroDeAr.getFormaDeVenda().getId());
			pstm.setDouble(++i, filtroDeAr.getValor());
			pstm.setInt(++i, filtroDeAr.getQuantidade());

			pstm.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar FiltroDeAr no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeAr))
			return;
		
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr = (FiltroDeAr) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from filtro_de_ar ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, filtroDeAr.getId());
			pstm.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir FiltroDeAr no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeAr))
			return;
		
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr = (FiltroDeAr) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update filtro_de_ar set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, filtroDeAr.getDescricao().toUpperCase());
			pstm.setString(++i, filtroDeAr.getMarca().toUpperCase());
			pstm.setString(++i, filtroDeAr.getTipo().toUpperCase());
			pstm.setInt(++i, filtroDeAr.getFormaDeVenda().getId());
			pstm.setDouble(++i, filtroDeAr.getValor());
			pstm.setInt(++i, filtroDeAr.getQuantidade());
			pstm.setInt(++i, filtroDeAr.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o FiltroDeAr no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public FiltroDeAr pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeAr))
			return null;
		
		FiltroDeAr filtroDeAr = new FiltroDeAr();
		filtroDeAr = (FiltroDeAr) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_ar ");
		sql.append("where id = ? ");

		FiltroDeAr filtroDeArRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, filtroDeAr.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				filtroDeArRetorno = new FiltroDeAr();
				filtroDeArRetorno.setId(rSet.getInt("id"));
				filtroDeArRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeArRetorno.setTipo(rSet.getString("tipo"));
				filtroDeArRetorno.setMarca(rSet.getString("marca"));
				filtroDeArRetorno.setValor(rSet.getDouble("valor"));
				filtroDeArRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeArRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o FiltroDeAr no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return filtroDeArRetorno;
	}

	@Override
	public List<FiltroDeAr> pesquisarPorMarca(FiltroDeAr filtroDeAr) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_ar ");
		sql.append("where marca = ? ");

		List<FiltroDeAr> lista = new ArrayList<FiltroDeAr>();
		FiltroDeAr filtroDeArRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, filtroDeAr.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				filtroDeArRetorno = new FiltroDeAr();
				filtroDeArRetorno.setId(rSet.getInt("id"));
				filtroDeArRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeArRetorno.setTipo(rSet.getString("tipo"));
				filtroDeArRetorno.setMarca(rSet.getString("marca"));
				filtroDeArRetorno.setValor(rSet.getDouble("valor"));
				filtroDeArRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeArRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(filtroDeArRetorno);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_ar ");
		sql.append("where id <> 99");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		FiltroDeAr filtroDeArRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				filtroDeArRetorno = new FiltroDeAr();
				filtroDeArRetorno.setId(rSet.getInt("id"));
				filtroDeArRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeArRetorno.setTipo(rSet.getString("tipo"));
				filtroDeArRetorno.setMarca(rSet.getString("marca"));
				filtroDeArRetorno.setValor(rSet.getDouble("valor"));
				filtroDeArRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeArRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(filtroDeArRetorno);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public FiltroDeAr pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_ar ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		FiltroDeAr filtroDeArRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				filtroDeArRetorno = new FiltroDeAr();
				filtroDeArRetorno.setId(rSet.getInt("id"));
				filtroDeArRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeArRetorno.setTipo(rSet.getString("tipo"));
				filtroDeArRetorno.setMarca(rSet.getString("marca"));
				filtroDeArRetorno.setValor(rSet.getDouble("valor"));
				filtroDeArRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeArRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return filtroDeArRetorno;
	}
}
