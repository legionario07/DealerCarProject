package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.AbstractPesquisaItensOpcionais;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.domain.itensopcionais.TipoSeguro;
import br.com.dealercar.enums.SeguroType;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class SeguroDAO extends AbstractPesquisaItensOpcionais<Seguro> {

	/**
	 * 
	 * @pseguroam Seguro Recebe um Seguro e cadastra no Banco de Dados
	 */
	public void cadastrar(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into seguros (descricao, valor, tipo_seguro) ");
		sql.append("values (?, ?, ?)");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, seguro.getDescricao());
			ps.setDouble(2, seguro.getValor());
			ps.setInt(3, seguro.getTipoSeguro().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @pseguroam Seguro Recebe um Seguro e exclui-o do Banco de Dados
	 *            procurando-o pelo codigo
	 */
	public void excluir(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from seguros where codigo = ?");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, seguro.getCodigo());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @pseguroam Seguro Recebe um Seguro e edita-o no Banco de Dados
	 *            Localizando-o pelo seu Codigo
	 */
	public void editar(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("update seguros set descricao = ?, valor = ?, tipo_seguro = ? where codigo = ?");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, seguro.getDescricao());
			ps.setDouble(2, seguro.getValor());
			ps.setInt(3, seguro.getTipoSeguro().getId());
			ps.setInt(4, seguro.getCodigo());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Lista todos os ArCondionados do Banco de Dados e retorna um
	 *         objeto de Seguro
	 */
	public List<Seguro> listarTodos() {
		StringBuffer sql = new StringBuffer();
		sql.append("select seguros.codigo, seguros.descricao, seguros.valor, ");
		sql.append("seguros.tipo_seguro, tipo_seguro.id, tipo_seguro.nome, tipo_seguro.valor_acrescido ");
		sql.append("from seguros inner join tipo_seguro where seguros.tipo_seguro = tipo_seguro.id ");
		sql.append("order by seguros.descricao asc");

		List<Seguro> listaRetorno = new ArrayList<Seguro>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Seguro seguro = new Seguro();
				seguro.setCodigo(rSet.getInt("seguros.codigo"));
				seguro.setDescricao(rSet.getString("seguros.descricao"));

				TipoSeguro tipoSeguro = new TipoSeguro();

				tipoSeguro.setId(rSet.getInt("tipo_seguro.id"));
				tipoSeguro.setNome(SeguroType.valueOf(rSet.getString("tipo_seguro.nome")));
				tipoSeguro.setValorAcrescido(rSet.getDouble("tipo_seguro.valor_acrescido"));

				seguro.setTipoSeguro(tipoSeguro);
				seguro.setValor(rSet.getDouble("seguros.valor"));

				listaRetorno.add(seguro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

	/**
	 * 
	 * @pseguroam Seguro Recebe um Objeto Seguro e localiza no Banco de Dados
	 *            pelo Codigo
	 * @return Retorna um objeto de Seguro
	 */
	@Override
	public Seguro pesquisarPorCodigo(Seguro seguro) {

		StringBuffer sql = new StringBuffer();
		sql.append("select seguros.codigo, seguros.descricao, seguros.valor, ");
		sql.append("seguros.tipo_seguro, tipo_seguro.id, tipo_seguro.nome, tipo_seguro.valor_acrescido ");
		sql.append("from seguros inner join tipo_seguro on seguros.tipo_seguro = tipo_seguro.id ");
		sql.append("where seguros.codigo = ?");

		Seguro seguroRetorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, seguro.getCodigo());
			
			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				seguroRetorno = new Seguro();
				seguroRetorno.setCodigo(rSet.getInt("seguros.codigo"));
				seguroRetorno.setDescricao(rSet.getString("seguros.descricao"));
				seguroRetorno.setValor(rSet.getDouble("seguros.valor"));

				TipoSeguro tipoSeguro = new TipoSeguro();

				tipoSeguro.setId(rSet.getInt("tipo_seguro.id"));
				tipoSeguro.setNome(SeguroType.valueOf(rSet.getString("tipo_seguro.nome")));
				tipoSeguro.setValorAcrescido(rSet.getDouble("tipo_seguro.valor_acrescido"));

				seguroRetorno.setTipoSeguro(tipoSeguro);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return seguroRetorno;
	}

	@Override
	public Seguro pesquisarPorID(Seguro entidade) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
