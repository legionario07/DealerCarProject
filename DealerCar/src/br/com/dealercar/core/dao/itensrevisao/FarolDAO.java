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
import br.com.dealercar.domain.produtosrevisao.Farol;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

/**
 * Classe responsavel pelo gerenciamento dos farois no Banco
 * 
 * @author Paulinho
 *
 */
public class FarolDAO extends AbstractPesquisaItensRevisao<Farol> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Farol))
			return;
		
		Farol farol = new Farol();
		farol = (Farol) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into farol ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, farol.getDescricao().toUpperCase());
			pstm.setString(++i, farol.getMarca().toUpperCase());
			pstm.setString(++i, farol.getTipo().toUpperCase());
			pstm.setInt(++i, farol.getFormaDeVenda().getId());
			pstm.setDouble(++i, farol.getValor());
			pstm.setInt(++i, farol.getQuantidade());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Farol))
			return;
		
		Farol farol = new Farol();
		farol = (Farol) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from farol ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, farol.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Farol))
			return;
		
		Farol farol = new Farol();
		farol = (Farol) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update farol set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, farol.getDescricao().toUpperCase());
			pstm.setString(++i, farol.getMarca().toUpperCase());
			pstm.setString(++i, farol.getTipo().toUpperCase());
			pstm.setInt(++i, farol.getFormaDeVenda().getId());
			pstm.setDouble(++i, farol.getValor());
			pstm.setInt(++i, farol.getQuantidade());
			pstm.setInt(++i, farol.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public Farol pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Farol))
			return null;
		
		Farol farol = new Farol();
		farol = (Farol) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from farol ");
		sql.append("where id = ? ");

		Farol farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, farol.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				farolRetorno = new Farol();
				farolRetorno.setId(rSet.getInt("id"));
				farolRetorno.setDescricao(rSet.getString("descricao"));
				farolRetorno.setTipo(rSet.getString("tipo"));
				farolRetorno.setMarca(rSet.getString("marca"));
				farolRetorno.setValor(rSet.getDouble("valor"));
				farolRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				farolRetorno.setFormaDeVenda(formaDeVenda);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o Farol no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return farolRetorno;
	}

	@Override
	public List<Farol> pesquisarPorMarca(Farol farol) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from farol ");
		sql.append("where marca = ? ");

		List<Farol> lista = new ArrayList<Farol>();
		Farol farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, farol.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Farol();
				farolRetorno.setId(rSet.getInt("id"));
				farolRetorno.setDescricao(rSet.getString("descricao"));
				farolRetorno.setTipo(rSet.getString("tipo"));
				farolRetorno.setMarca(rSet.getString("marca"));
				farolRetorno.setValor(rSet.getDouble("valor"));
				farolRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				farolRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(farolRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from farol ");
		sql.append("where id <> 99");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		Farol farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Farol();
				farolRetorno.setId(rSet.getInt("id"));
				farolRetorno.setDescricao(rSet.getString("descricao"));
				farolRetorno.setTipo(rSet.getString("tipo"));
				farolRetorno.setMarca(rSet.getString("marca"));
				farolRetorno.setValor(rSet.getDouble("valor"));
				farolRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				farolRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(farolRetorno);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public Farol pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from farol ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		Farol farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				farolRetorno = new Farol();
				farolRetorno.setId(rSet.getInt("id"));
				farolRetorno.setDescricao(rSet.getString("descricao"));
				farolRetorno.setTipo(rSet.getString("tipo"));
				farolRetorno.setMarca(rSet.getString("marca"));
				farolRetorno.setValor(rSet.getDouble("valor"));
				farolRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				farolRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return farolRetorno;
	}
}
