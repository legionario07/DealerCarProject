package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.AbstractPesquisaItensOpcionais;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * REaliza a persistencia dos ITEM OPCIONAL ArCondicionado
 * @author Paulinho
 *
 */
public class ArCondicionadoDAO extends AbstractPesquisaItensOpcionais<ArCondicionado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection con = Conexao.getConnection();

	/**
	 * 
	 * @param arCondicionado
	 *            Recebe um arCondicionado e cadastra no Banco de Dados
	 */
	public void cadastrar(ArCondicionado ar) {

		StringBuffer sql = new StringBuffer();
		sql.append("insert into arcondicionados (descricao, valor) ");
		sql.append("values (?, ?)");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, ar.getDescricao());
			pstm.setDouble(2, ar.getValor());
			pstm.executeUpdate();
			
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @param arCondicionado
	 *            Recebe um arCondcionado e exclui-o do Banco de Dados
	 *            procurando-o pelo codigo
	 */
	public void excluir(ArCondicionado ar) {

		StringBuffer sql = new StringBuffer();
		sql.append("delete from arcondicionados where codigo = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, ar.getCodigo());
			pstm.executeUpdate();
			
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	/**
	 * 
	 * @param ArCondicionado
	 *            Recebe um Arcondiconado e edita-o no Banco de Dados
	 *            Localizando-o pelo seu Codigo
	 */
	public void editar(ArCondicionado ar) {

		StringBuffer sql = new StringBuffer();
		sql.append("update arcondicionados set descricao = ?, ");
		sql.append("valor = ? where codigo = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, ar.getDescricao());
			pstm.setDouble(2, ar.getValor());
			pstm.setInt(3, ar.getCodigo());

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Lista todos os ArCondionados do Banco de Dados e retorna um array
	 *         de ArCondicionado
	 */
	public List<ArCondicionado> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from arcondicionados where codigo <> 99");

		List<ArCondicionado> listaRetorno = new ArrayList<ArCondicionado>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				ArCondicionado ar = new ArCondicionado();
				ar.setCodigo(rSet.getInt("codigo"));
				ar.setDescricao(rSet.getString("descricao"));
				ar.setValor(rSet.getDouble("valor"));

				listaRetorno.add(ar);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaRetorno;
	}

	/**
	 * 
	 * @param ArCondicionado
	 *            Recebe um Objeto ArCondionado e localiza no Banco de Dados
	 *            pelo Codigo
	 * @return Retorna um objeto de ArCondicionado
	 */
	@Override
	public ArCondicionado pesquisarPorCodigo(ArCondicionado ar) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from arcondicionados where codigo = ?");

		ArCondicionado arRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, ar.getCodigo());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				arRetorno = new ArCondicionado();

				arRetorno.setCodigo(rSet.getInt("codigo"));
				arRetorno.setDescricao(rSet.getString("descricao"));
				arRetorno.setValor(rSet.getDouble("valor"));

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return arRetorno;
	}

	@Override
	public ArCondicionado pesquisarPorID(ArCondicionado entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
