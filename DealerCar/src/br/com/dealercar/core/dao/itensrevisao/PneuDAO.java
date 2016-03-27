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
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;
import br.com.dealercar.domain.produtosrevisao.Pneu;

/**
 * Classe responsavel pelo gerenciamento dos farois no Banco
 * 
 * @author Paulinho
 *
 */
public class PneuDAO extends AbstractPesquisaItensRevisao<Pneu> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Pneu))
			return;
		
		Pneu pneu = new Pneu();
		pneu = (Pneu) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into pneus ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, pneu.getDescricao().toUpperCase());
			pstm.setString(++i, pneu.getMarca().toUpperCase());
			pstm.setString(++i, pneu.getTipo().toUpperCase());
			pstm.setInt(++i, pneu.getFormaDeVenda().getId());
			pstm.setDouble(++i, pneu.getValor());
			pstm.setInt(++i, pneu.getQuantidade());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Pneu))
			return;
		
		Pneu pneu = new Pneu();
		pneu = (Pneu) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from pneus ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, pneu.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Pneu))
			return;
		
		Pneu pneu = new Pneu();
		pneu = (Pneu) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update pneus set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, pneu.getDescricao().toUpperCase());
			pstm.setString(++i, pneu.getMarca().toUpperCase());
			pstm.setString(++i, pneu.getTipo().toUpperCase());
			pstm.setInt(++i, pneu.getFormaDeVenda().getId());
			pstm.setDouble(++i, pneu.getValor());
			pstm.setInt(++i, pneu.getQuantidade());
			pstm.setInt(++i, pneu.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public Pneu pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Pneu))
			return null;
		
		Pneu pneu = new Pneu();
		pneu = (Pneu) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from pneus ");
		sql.append("where id = ? ");

		Pneu farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, pneu.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				farolRetorno = new Pneu();
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
			JSFUtil.adicionarMensagemErro("Erro ao buscar o Pneu no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return farolRetorno;
	}

	@Override
	public List<Pneu> pesquisarPorMarca(Pneu pneu) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from pneus ");
		sql.append("where marca = ? ");

		List<Pneu> lista = new ArrayList<Pneu>();
		Pneu farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, pneu.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Pneu();
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
		sql.append("select * from pneus ");
		sql.append("where id <> 99");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		Pneu farolRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				farolRetorno = new Pneu();
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
	public Pneu pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from pneus ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		Pneu pneuRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				pneuRetorno = new Pneu();
				pneuRetorno.setId(rSet.getInt("id"));
				pneuRetorno.setDescricao(rSet.getString("descricao"));
				pneuRetorno.setTipo(rSet.getString("tipo"));
				pneuRetorno.setMarca(rSet.getString("marca"));
				pneuRetorno.setValor(rSet.getDouble("valor"));
				pneuRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				pneuRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return pneuRetorno;
	}
}
