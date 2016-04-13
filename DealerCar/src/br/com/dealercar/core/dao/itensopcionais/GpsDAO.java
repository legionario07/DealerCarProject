package br.com.dealercar.core.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.Gps;

/**
 * REaliza a persistencia dos ITEM OPCIONAL GPS
 * @author Paulinho
 *
 */
public class GpsDAO extends AbstractPesquisaItensOpcionais {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;

	/**
	 * 
	 * @param Gps
	 *            Recebe um Gps como parametro e cadastra no Banco de Dados
	 */
	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Gps))
			return;
		
		Gps gps = new Gps();
		gps = (Gps) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into gps (descricao, valor, marca, numero_patrimonio, idioma) ");
		sql.append("values (?, ?, ?, ?, ?)");

		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, gps.getDescricao());
			pstm.setDouble(2, gps.getValor());
			pstm.setString(3, gps.getMarca());
			pstm.setString(4, gps.getNumeroPatrimonio());
			pstm.setString(5, gps.getIdioma());
			pstm.executeUpdate();
			
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
	public void excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Gps))
			return;
		
		Gps gps = new Gps();
		gps = (Gps) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from gps where codigo = ?");
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, gps.getCodigo());
			pstm.executeUpdate();
			
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
	public void editar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Gps))
			return;
		
		Gps gps = new Gps();
		gps = (Gps) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update gps set descricao = ?, valor = ?, ");
		sql.append("marca = ?, numero_patrimonio = ?, idioma = ? where codigo = ?");
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, gps.getDescricao());
			pstm.setDouble(2, gps.getValor());
			pstm.setString(3, gps.getMarca());
			pstm.setString(4, gps.getNumeroPatrimonio());
			pstm.setString(5, gps.getIdioma());
			pstm.setInt(6, gps.getCodigo());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Lista os Gps cadastrados no Banco e exibe em forma de List<Gps>
	 */
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from gps where codigo <> 99");

		List<EntidadeDominio> listaRetorno = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());

			ResultSet rSet = pstm.executeQuery();

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
	public Gps pesquisarPorCodigo(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Gps))
			return null;
		
		Gps gps = new Gps();
		gps = (Gps) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from gps where codigo = ?");

		Gps gpsRetorno = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, gps.getCodigo());

			ResultSet rSet = pstm.executeQuery();

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
	public Gps pesquisarPorID(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
}
