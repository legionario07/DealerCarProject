package br.com.dealercar.dao.itensrevisao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.produtosrevisao.FluidoDeFreio;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsavel pelo gerenciamento dos Fluido de freio no Banco
 * 
 * @author Paulinho
 *
 */
public class FluidoDeFreioDAO extends AbstractPesquisaItensRevisao<FluidoDeFreio> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(FluidoDeFreio fluidoDeFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into fluido_freio ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, fluidoDeFreio.getDescricao().toUpperCase());
			pstm.setString(++i, fluidoDeFreio.getMarca().toUpperCase());
			pstm.setString(++i, fluidoDeFreio.getTipo().toUpperCase());
			pstm.setInt(++i, fluidoDeFreio.getFormaDeVenda().getId());
			pstm.setDouble(++i, fluidoDeFreio.getValor());
			pstm.setInt(++i, fluidoDeFreio.getQuantidade());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar FluidoDeFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(FluidoDeFreio fluidoDeFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from fluido_freio ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, fluidoDeFreio.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir FluidoDeFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(FluidoDeFreio fluidoDeFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("update fluido_freio set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, fluidoDeFreio.getDescricao().toUpperCase());
			pstm.setString(++i, fluidoDeFreio.getMarca().toUpperCase());
			pstm.setString(++i, fluidoDeFreio.getTipo().toUpperCase());
			pstm.setInt(++i, fluidoDeFreio.getFormaDeVenda().getId());
			pstm.setDouble(++i, fluidoDeFreio.getValor());
			pstm.setInt(++i, fluidoDeFreio.getQuantidade());
			pstm.setInt(++i, fluidoDeFreio.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o FluidoDeFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public FluidoDeFreio pesquisarPorID(FluidoDeFreio fluidoDeFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from fluido_freio ");
		sql.append("where id = ? ");

		FluidoDeFreio fluidoDeFreioRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, fluidoDeFreio.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				fluidoDeFreioRetorno = new FluidoDeFreio();
				fluidoDeFreioRetorno.setId(rSet.getInt("id"));
				fluidoDeFreioRetorno.setDescricao(rSet.getString("descricao"));
				fluidoDeFreioRetorno.setTipo(rSet.getString("tipo"));
				fluidoDeFreioRetorno.setMarca(rSet.getString("marca"));
				fluidoDeFreioRetorno.setValor(rSet.getDouble("valor"));
				fluidoDeFreioRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				fluidoDeFreioRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o FluidoDeFreio no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return fluidoDeFreioRetorno;
	}

	@Override
	public List<FluidoDeFreio> pesquisarPorMarca(FluidoDeFreio fluidoDeFreio) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from fluido_freio ");
		sql.append("where marca = ? ");

		List<FluidoDeFreio> lista = new ArrayList<FluidoDeFreio>();
		FluidoDeFreio fluidoDeFreioRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, fluidoDeFreio.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				fluidoDeFreioRetorno = new FluidoDeFreio();
				fluidoDeFreioRetorno.setId(rSet.getInt("id"));
				fluidoDeFreioRetorno.setDescricao(rSet.getString("descricao"));
				fluidoDeFreioRetorno.setTipo(rSet.getString("tipo"));
				fluidoDeFreioRetorno.setMarca(rSet.getString("marca"));
				fluidoDeFreioRetorno.setValor(rSet.getDouble("valor"));
				fluidoDeFreioRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				fluidoDeFreioRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(fluidoDeFreioRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<FluidoDeFreio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from fluido_freio ");
		sql.append("where id <> 99");

		List<FluidoDeFreio> lista = new ArrayList<FluidoDeFreio>();
		FluidoDeFreio fluidoDeFreioRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				fluidoDeFreioRetorno = new FluidoDeFreio();
				fluidoDeFreioRetorno.setId(rSet.getInt("id"));
				fluidoDeFreioRetorno.setDescricao(rSet.getString("descricao"));
				fluidoDeFreioRetorno.setTipo(rSet.getString("tipo"));
				fluidoDeFreioRetorno.setMarca(rSet.getString("marca"));
				fluidoDeFreioRetorno.setValor(rSet.getDouble("valor"));
				fluidoDeFreioRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				fluidoDeFreioRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(fluidoDeFreioRetorno);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public FluidoDeFreio pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from fluido_freio ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		FluidoDeFreio fluidoDeFreioRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				fluidoDeFreioRetorno = new FluidoDeFreio();
				fluidoDeFreioRetorno.setId(rSet.getInt("id"));
				fluidoDeFreioRetorno.setDescricao(rSet.getString("descricao"));
				fluidoDeFreioRetorno.setTipo(rSet.getString("tipo"));
				fluidoDeFreioRetorno.setMarca(rSet.getString("marca"));
				fluidoDeFreioRetorno.setValor(rSet.getDouble("valor"));
				fluidoDeFreioRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				fluidoDeFreioRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return fluidoDeFreioRetorno;
	}

}
