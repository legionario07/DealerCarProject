package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.DaoUtil;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel pelo gerenciamento dos amortecedores no Banco
 * 
 * @author Paulinho
 *
 */
public class AmortecedorDAO extends AbstractPesquisaItensRevisao<Amortecedor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(Amortecedor amortecedor) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into amortecedores ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, amortecedor.getDescricao().toUpperCase());
			pstm.setString(++i, amortecedor.getMarca().toUpperCase());
			pstm.setString(++i, amortecedor.getTipo().toUpperCase());
			pstm.setInt(++i, amortecedor.getFormaDeVenda().getId());
			pstm.setDouble(++i, amortecedor.getValor());
			pstm.setInt(++i, amortecedor.getQuantidade());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Amortecedor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Amortecedor amortecedor) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from amortecedores ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, amortecedor.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Amortecedor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(Amortecedor amortecedor) {

		StringBuffer sql = new StringBuffer();
		sql.append("update amortecedores set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, amortecedor.getDescricao().toUpperCase());
			pstm.setString(++i, amortecedor.getMarca().toUpperCase());
			pstm.setString(++i, amortecedor.getTipo().toUpperCase());
			pstm.setInt(++i, amortecedor.getFormaDeVenda().getId());
			pstm.setDouble(++i, amortecedor.getValor());
			pstm.setInt(++i, amortecedor.getQuantidade());
			pstm.setInt(++i, amortecedor.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o Amortecedor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public Amortecedor pesquisarPorID(Amortecedor amortecedor) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from amortecedores ");
		sql.append("where id = ? ");

		Amortecedor amortecedorRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, amortecedor.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				amortecedorRetorno = new Amortecedor();
				amortecedorRetorno.setId(rSet.getInt("id"));
				amortecedorRetorno.setDescricao(rSet.getString("descricao"));
				amortecedorRetorno.setTipo(rSet.getString("tipo"));
				amortecedorRetorno.setMarca(rSet.getString("marca"));
				amortecedorRetorno.setValor(rSet.getDouble("valor"));
				amortecedorRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				amortecedorRetorno.setFormaDeVenda(formaDeVenda);

			}

			/**
			 * Se DaoUtil.isCallFromDao != -1 a connection será fechada no
			 * DAO de chamador
			 */
			if(DaoUtil.isCallFromDao() == -1) {
				rSet.close();
				pstm.close();
				con.close();
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o Amortecedor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return amortecedorRetorno;
	}

	@Override
	public List<Amortecedor> pesquisarPorMarca(Amortecedor amortecedor) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from amortecedores ");
		sql.append("where marca = ? ");

		List<Amortecedor> lista = new ArrayList<Amortecedor>();
		Amortecedor amortecedorRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, amortecedor.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				amortecedorRetorno = new Amortecedor();
				amortecedorRetorno.setId(rSet.getInt("id"));
				amortecedorRetorno.setDescricao(rSet.getString("descricao"));
				amortecedorRetorno.setTipo(rSet.getString("tipo"));
				amortecedorRetorno.setMarca(rSet.getString("marca"));
				amortecedorRetorno.setValor(rSet.getDouble("valor"));
				amortecedorRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				amortecedorRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(amortecedorRetorno);

			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Amortecedor> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from amortecedores ");
		sql.append("where id <> 99");

		List<Amortecedor> lista = new ArrayList<Amortecedor>();
		Amortecedor amortecedorRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				amortecedorRetorno = new Amortecedor();
				amortecedorRetorno.setId(rSet.getInt("id"));
				amortecedorRetorno.setDescricao(rSet.getString("descricao"));
				amortecedorRetorno.setTipo(rSet.getString("tipo"));
				amortecedorRetorno.setMarca(rSet.getString("marca"));
				amortecedorRetorno.setValor(rSet.getDouble("valor"));
				amortecedorRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				amortecedorRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(amortecedorRetorno);

			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

}
