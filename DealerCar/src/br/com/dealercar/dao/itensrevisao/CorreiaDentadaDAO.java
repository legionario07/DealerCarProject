package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.CorreiaDentada;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.DaoUtil;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel pelo gerenciamento dos correia_dentada no Banco
 * 
 * @author Paulinho
 *
 */
public class CorreiaDentadaDAO extends AbstractPesquisaItensRevisao<CorreiaDentada> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(CorreiaDentada correiaDentada) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into correia_dentada ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, correiaDentada.getDescricao().toUpperCase());
			pstm.setString(++i, correiaDentada.getMarca().toUpperCase());
			pstm.setString(++i, correiaDentada.getTipo().toUpperCase());
			pstm.setInt(++i, correiaDentada.getFormaDeVenda().getId());
			pstm.setDouble(++i, correiaDentada.getValor());
			pstm.setInt(++i, correiaDentada.getQuantidade());

			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Correia Dentada no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(CorreiaDentada correiaDentada) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from correia_dentada ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, correiaDentada.getId());
			pstm.executeUpdate();

			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Correia Dentada no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(CorreiaDentada correiaDentada) {

		StringBuffer sql = new StringBuffer();
		sql.append("update correia_dentada set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, correiaDentada.getDescricao().toUpperCase());
			pstm.setString(++i, correiaDentada.getMarca().toUpperCase());
			pstm.setString(++i, correiaDentada.getTipo().toUpperCase());
			pstm.setInt(++i, correiaDentada.getFormaDeVenda().getId());
			pstm.setDouble(++i, correiaDentada.getValor());
			pstm.setInt(++i, correiaDentada.getQuantidade());
			pstm.setInt(++i, correiaDentada.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o Correia Dentada no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public CorreiaDentada pesquisarPorID(CorreiaDentada correiaDentada) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from correia_dentada ");
		sql.append("where id = ? ");

		CorreiaDentada correiaDentadaRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, correiaDentada.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				correiaDentadaRetorno = new CorreiaDentada();
				correiaDentadaRetorno.setId(rSet.getInt("id"));
				correiaDentadaRetorno.setDescricao(rSet.getString("descricao"));
				correiaDentadaRetorno.setTipo(rSet.getString("tipo"));
				correiaDentadaRetorno.setMarca(rSet.getString("marca"));
				correiaDentadaRetorno.setValor(rSet.getDouble("valor"));
				correiaDentadaRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				correiaDentadaRetorno.setFormaDeVenda(formaDeVenda);

			}

			/**
			 * /** Se DaoUtil.isCallFromDao != -1 a connection será fechada no
			 * DAO de chamador
			 */
			if (DaoUtil.isCallFromDao() == -1) {
				rSet.close();
				pstm.close();
				con.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o Correia ODentada no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return correiaDentadaRetorno;
	}

	@Override
	public List<CorreiaDentada> pesquisarPorMarca(CorreiaDentada correiaDentada) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from correia_dentada ");
		sql.append("where marca = ? ");

		List<CorreiaDentada> lista = new ArrayList<CorreiaDentada>();
		CorreiaDentada correiaDentadaRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, correiaDentada.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				correiaDentadaRetorno = new CorreiaDentada();
				correiaDentadaRetorno.setId(rSet.getInt("id"));
				correiaDentadaRetorno.setDescricao(rSet.getString("descricao"));
				correiaDentadaRetorno.setTipo(rSet.getString("tipo"));
				correiaDentadaRetorno.setMarca(rSet.getString("marca"));
				correiaDentadaRetorno.setValor(rSet.getDouble("valor"));
				correiaDentadaRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				correiaDentadaRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(correiaDentadaRetorno);

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
	public List<CorreiaDentada> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from correia_dentada ");

		List<CorreiaDentada> lista = new ArrayList<CorreiaDentada>();
		CorreiaDentada correiaDentadaRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				correiaDentadaRetorno = new CorreiaDentada();
				correiaDentadaRetorno.setId(rSet.getInt("id"));
				correiaDentadaRetorno.setDescricao(rSet.getString("descricao"));
				correiaDentadaRetorno.setTipo(rSet.getString("tipo"));
				correiaDentadaRetorno.setMarca(rSet.getString("marca"));
				correiaDentadaRetorno.setValor(rSet.getDouble("valor"));
				correiaDentadaRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				correiaDentadaRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(correiaDentadaRetorno);

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
