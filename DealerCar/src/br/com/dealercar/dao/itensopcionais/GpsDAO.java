package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.AbstractPesquisaItensOpcionais;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class GpsDAO extends AbstractPesquisaItensOpcionais<Gps> {

	/**
	 * 
	 * @param Gps
	 *            Recebe um Gps como parametro e cadastra no Banco de Dados
	 */
	public void cadastrar(Gps gps) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into gps (descricao, valor, marca, numero_patrimonio, idioma) ");
		sql.append("values (?, ?, ?, ?, ?)");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, gps.getDescricao());
			ps.setDouble(2, gps.getValor());
			ps.setString(3, gps.getMarca());
			ps.setString(4, gps.getNumeroPatrimonio());
			ps.setString(5, gps.getIdioma());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param Gps
	 *            Recebe um Gps e exclui do Banco de Dados pelo seu Codigo
	 */
	public void excluir(Gps gps) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from gps where codigo = ?");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, gps.getCodigo());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param Gps
	 *            Recebe um Gps e edita de acordo com seu codigo
	 */
	public void editar(Gps gps) {
		StringBuffer sql = new StringBuffer();
		sql.append("update gps set descricao = ?, valor = ?, ");
		sql.append("marca = ?, numero_patrimonio = ?, idioma = ? where codigo = ?");

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, gps.getDescricao());
			ps.setDouble(2, gps.getValor());
			ps.setString(3, gps.getMarca());
			ps.setString(4, gps.getNumeroPatrimonio());
			ps.setString(5, gps.getIdioma());
			ps.setInt(6, gps.getCodigo());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Lista os Gps cadastrados no Banco e exibe em forma de List<Gps>
	 */
	public List<Gps> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from gps where codigo <> 99");

		List<Gps> listaRetorno = new ArrayList<Gps>();

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {
				Gps gps = new Gps();
				gps.setCodigo(rSet.getInt("codigo"));
				gps.setDescricao(rSet.getString("descricao"));
				gps.setValor(rSet.getDouble("valor"));
				gps.setMarca(rSet.getString("marca"));
				gps.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				gps.setIdioma(rSet.getString("idioma"));

				listaRetorno.add(gps);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;

	}

	/**
	 * 
	 * @param Gps
	 *            Recebe um Objeto Gps e localiza no Banco de Dados pelo Codigo
	 * @return Retorna um objeto de Gps
	 */
	public Gps pesquisarPorCodigo(Gps gps) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from gps where codigo = ?");

		Gps gpsRetorno = null;

		Connection con = Conexao.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, gps.getCodigo());

			ResultSet rSet = ps.executeQuery();

			while (rSet.next()) {

				gpsRetorno = new Gps();

				gpsRetorno.setCodigo(rSet.getInt("codigo"));
				gpsRetorno.setDescricao(rSet.getString("descricao"));
				gpsRetorno.setValor(rSet.getDouble("valor"));
				gpsRetorno.setMarca(rSet.getString("marca"));
				gpsRetorno.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				gpsRetorno.setIdioma(rSet.getString("idioma"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return gpsRetorno;
	}

	@Override
	public Gps pesquisarPorID(Gps entidade) {
		// TODO Auto-generated method stub
		return null;
	}
}
