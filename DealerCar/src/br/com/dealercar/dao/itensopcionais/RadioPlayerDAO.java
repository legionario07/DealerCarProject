package br.com.dealercar.dao.itensopcionais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.AbstractPesquisaItensOpcionais;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class RadioPlayerDAO extends AbstractPesquisaItensOpcionais<RadioPlayer>{

	/**
	 * 
	 * @param RadioPlayer Recebe um RadioPlayer como parametro e cadastra no Banco de Dados
	 */
	public void cadastrar(RadioPlayer radio) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into radio_player (descricao, valor, marca, numero_patrimonio, modelo) ");
		sql.append("values (?, ?, ?, ?, ?)");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, radio.getDescricao());
			ps.setDouble(2, radio.getValor());
			ps.setString(3, radio.getMarca());
			ps.setString(4, radio.getNumeroPatrimonio());
			ps.setString(5, radio.getModelo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
	}

	/**
	 * 
	 * @param RadioPlayer Recebe um RadioPlayer e exclui do Banco de Dados pelo seu Codigo
	 */
	public void excluir(RadioPlayer radio) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from radio_player where codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, radio.getCodigo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param RadioPlayer Recebe um RadioPlayer e edita de acordo com seu codigo
	 */
	public void editar(RadioPlayer radio) {
		StringBuffer sql = new StringBuffer();
		sql.append("update radio_player set descricao = ?, valor = ?, ");
		sql.append("marca = ?, numero_patrimonio = ?, modelo = ? where codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, radio.getDescricao());
			ps.setDouble(2, radio.getValor());
			ps.setString(3, radio.getMarca());
			ps.setString(4, radio.getNumeroPatrimonio());
			ps.setString(5, radio.getModelo());
			ps.setInt(6, radio.getCodigo());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return Lista os RadioPlayer cadastrados no Banco e exibe em forma de List<RadioPLayer>
	 */
	public List<RadioPlayer> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from radio_player");
		
		List<RadioPlayer> listaRetorno = new ArrayList<RadioPlayer>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				RadioPlayer radio = new RadioPlayer();
				radio.setCodigo(rSet.getInt("codigo"));
				radio.setDescricao(rSet.getString("descricao"));
				radio.setValor(rSet.getDouble("valor"));
				radio.setMarca(rSet.getString("marca"));
				radio.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				radio.setModelo(rSet.getString("modelo"));
				
				listaRetorno.add(radio);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaRetorno;
			
	}
	

	/**
	 * 
	 * @param RadioPlayer Recebe um Objeto RadioPlayer e localiza no Banco de Dados
	 * pelo Codigo
	 * @return Retorna um objeto de RadioPlayer
	 */
	public RadioPlayer pesquisarPorCodigo(RadioPlayer radioPlayer) {
	
		StringBuffer sql = new StringBuffer();
		sql.append("select * from radio_player where codigo = ?");
		
		RadioPlayer radioPlayerRetorno = null;
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setInt(1, radioPlayer.getCodigo());
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				
				radioPlayerRetorno = new RadioPlayer();
				radioPlayerRetorno.setCodigo(rSet.getInt("codigo"));
				radioPlayerRetorno.setDescricao(rSet.getString("descricao"));
				radioPlayerRetorno.setValor(rSet.getDouble("valor"));
				radioPlayerRetorno.setMarca(rSet.getString("marca"));
				radioPlayerRetorno.setNumeroPatrimonio(rSet.getString("numero_patrimonio"));
				radioPlayerRetorno.setModelo(rSet.getString("modelo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return radioPlayerRetorno;
	}

	@Override
	public RadioPlayer pesquisarPorID(RadioPlayer entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
