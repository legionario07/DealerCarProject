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
import br.com.dealercar.domain.produtosrevisao.Amortecedor;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

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
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Amortecedor))
			return;
		
		Amortecedor amortecedor = new Amortecedor();
		amortecedor = (Amortecedor) entidade;

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

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar Amortecedor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Amortecedor))
			return;
		
		Amortecedor amortecedor = new Amortecedor();
		amortecedor = (Amortecedor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from amortecedores ");
		sql.append("where id = ? ");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, amortecedor.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir Amortecedor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Amortecedor))
			return;
		
		Amortecedor amortecedor = new Amortecedor();
		amortecedor = (Amortecedor) entidade;

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
	public Amortecedor pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Amortecedor))
			return null;
		
		Amortecedor amortecedor = new Amortecedor();
		amortecedor = (Amortecedor) entidade;

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


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from amortecedores ");
		sql.append("where id <> 99");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
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


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	@Override
	public Amortecedor pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from amortecedores ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		Amortecedor amortecedorRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
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


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return amortecedorRetorno;
	}


}
