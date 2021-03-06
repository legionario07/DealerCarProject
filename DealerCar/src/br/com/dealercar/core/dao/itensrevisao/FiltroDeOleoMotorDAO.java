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
import br.com.dealercar.domain.produtosrevisao.FiltroDeOleoMotor;
import br.com.dealercar.domain.produtosrevisao.FormaDeVenda;

/**
 * Classe responsavel pelo gerenciamento dos filtro_de_oleo_motor no Banco
 * 
 * @author Paulinho
 *
 */
public class FiltroDeOleoMotorDAO extends AbstractPesquisaItensRevisao<FiltroDeOleoMotor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	@Override
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeOleoMotor))
			return;
		
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor = (FiltroDeOleoMotor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into filtro_de_oleo_motor ");
		sql.append("(descricao, marca, tipo, id_forma_de_venda, valor, quantidade) ");
		sql.append("values ( ? , ? , ? , ? , ? , ? ) ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, filtroDeOleoMotor.getDescricao().toUpperCase());
			pstm.setString(++i, filtroDeOleoMotor.getMarca().toUpperCase());
			pstm.setString(++i, filtroDeOleoMotor.getTipo().toUpperCase());
			pstm.setInt(++i, filtroDeOleoMotor.getFormaDeVenda().getId());
			pstm.setDouble(++i, filtroDeOleoMotor.getValor());
			pstm.setInt(++i, filtroDeOleoMotor.getQuantidade());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao cadastrar FiltroDeOleoMotor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeOleoMotor))
			return;
		
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor = (FiltroDeOleoMotor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from filtro_de_oleo_motor ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, filtroDeOleoMotor.getId());
			pstm.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao excluir FiltroDeOleoMotor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeOleoMotor))
			return;
		
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor = (FiltroDeOleoMotor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("update filtro_de_oleo_motor set ");
		sql.append("descricao = ?, marca = ?, tipo = ?, id_forma_de_venda = ?, ");
		sql.append("valor = ?, quantidade = ? ");
		sql.append("where id = ? ");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;

			pstm.setString(++i, filtroDeOleoMotor.getDescricao().toUpperCase());
			pstm.setString(++i, filtroDeOleoMotor.getMarca().toUpperCase());
			pstm.setString(++i, filtroDeOleoMotor.getTipo().toUpperCase());
			pstm.setInt(++i, filtroDeOleoMotor.getFormaDeVenda().getId());
			pstm.setDouble(++i, filtroDeOleoMotor.getValor());
			pstm.setInt(++i, filtroDeOleoMotor.getQuantidade());
			pstm.setInt(++i, filtroDeOleoMotor.getId());
			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao Editar o FiltroDeOleoMotor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public FiltroDeOleoMotor pesquisarPorID(EntidadeDominio entidade) {
		
		if(!(entidade instanceof FiltroDeOleoMotor))
			return null;
		
		FiltroDeOleoMotor filtroDeOleoMotor = new FiltroDeOleoMotor();
		filtroDeOleoMotor = (FiltroDeOleoMotor) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_oleo_motor ");
		sql.append("where id = ? ");

		FiltroDeOleoMotor filtroDeOleoMotorRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, filtroDeOleoMotor.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				filtroDeOleoMotorRetorno = new FiltroDeOleoMotor();
				filtroDeOleoMotorRetorno.setId(rSet.getInt("id"));
				filtroDeOleoMotorRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeOleoMotorRetorno.setTipo(rSet.getString("tipo"));
				filtroDeOleoMotorRetorno.setMarca(rSet.getString("marca"));
				filtroDeOleoMotorRetorno.setValor(rSet.getDouble("valor"));
				filtroDeOleoMotorRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeOleoMotorRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar o FiltroDeOleoMotor no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return filtroDeOleoMotorRetorno;
	}

	@Override
	public List<FiltroDeOleoMotor> pesquisarPorMarca(FiltroDeOleoMotor filtroDeOleoMotor) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_oleo_motor ");
		sql.append("where marca = ? ");

		List<FiltroDeOleoMotor> lista = new ArrayList<FiltroDeOleoMotor>();
		FiltroDeOleoMotor filtroDeOleoMotorRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, filtroDeOleoMotor.getMarca().toUpperCase());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				filtroDeOleoMotorRetorno = new FiltroDeOleoMotor();
				filtroDeOleoMotorRetorno.setId(rSet.getInt("id"));
				filtroDeOleoMotorRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeOleoMotorRetorno.setTipo(rSet.getString("tipo"));
				filtroDeOleoMotorRetorno.setMarca(rSet.getString("marca"));
				filtroDeOleoMotorRetorno.setValor(rSet.getDouble("valor"));
				filtroDeOleoMotorRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeOleoMotorRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(filtroDeOleoMotorRetorno);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_oleo_motor ");
		sql.append("where id <> 99");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		FiltroDeOleoMotor filtroDeOleoMotorRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				filtroDeOleoMotorRetorno = new FiltroDeOleoMotor();
				filtroDeOleoMotorRetorno.setId(rSet.getInt("id"));
				filtroDeOleoMotorRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeOleoMotorRetorno.setTipo(rSet.getString("tipo"));
				filtroDeOleoMotorRetorno.setMarca(rSet.getString("marca"));
				filtroDeOleoMotorRetorno.setValor(rSet.getDouble("valor"));
				filtroDeOleoMotorRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeOleoMotorRetorno.setFormaDeVenda(formaDeVenda);

				lista.add(filtroDeOleoMotorRetorno);

			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public FiltroDeOleoMotor pesquisarPorDescricaoMarcaTipo(String produtoRevisao) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from filtro_de_oleo_motor ");
		sql.append("where descricao = ? and ");
		sql.append("marca = ? and ");
		sql.append("tipo = ? ");

		String[] arrayString = produtoRevisao.split(" - ");
				
		FiltroDeOleoMotor filtroDeOleoMotorRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setString(++i, arrayString[0]);
			pstm.setString(++i, arrayString[1]);
			pstm.setString(++i, arrayString[2]);
			
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				filtroDeOleoMotorRetorno = new FiltroDeOleoMotor();
				filtroDeOleoMotorRetorno.setId(rSet.getInt("id"));
				filtroDeOleoMotorRetorno.setDescricao(rSet.getString("descricao"));
				filtroDeOleoMotorRetorno.setTipo(rSet.getString("tipo"));
				filtroDeOleoMotorRetorno.setMarca(rSet.getString("marca"));
				filtroDeOleoMotorRetorno.setValor(rSet.getDouble("valor"));
				filtroDeOleoMotorRetorno.setQuantidade(rSet.getInt("quantidade"));

				FormaDeVenda formaDeVenda = new FormaDeVenda();
				formaDeVenda.setId(rSet.getInt("id_forma_de_venda"));
				formaDeVenda = new FormaDeVendaDAO().pesquisarPorID(formaDeVenda);

				filtroDeOleoMotorRetorno.setFormaDeVenda(formaDeVenda);

			}


		} catch (SQLException e) {
			e.printStackTrace();
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao buscar Produto da Revisao no Banco de Dados.");
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return filtroDeOleoMotorRetorno;
	}
}
