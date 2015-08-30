package br.com.dealercar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Cor;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

public class CoresDAO {

	public List<Cor> listarTodos() {
		String sql = "select * from cores";
		List<Cor> listaCores = new ArrayList<Cor>();
		
		Connection con = Conexao.getConnection();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rSet = ps.executeQuery();
			
			while(rSet.next()) {
				Cor cor = new Cor();
				cor.setId(rSet.getInt("id"));
				cor.setNome(rSet.getString("nome"));
				listaCores.add(cor);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		return listaCores;
	}
}
