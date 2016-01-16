package br.com.dealercar.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.domain.Estado;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Persiste os Estados no BD
 * @author Paulinho
 *
 */
public class EstadoDAO implements IDAO<Estado>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con = null;
	
	@Override
	public void cadastrar(Estado entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Estado entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Estado entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Estado> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from estados");
		
		List<Estado> lista = new ArrayList<Estado>();
		Estado estado = null;
		
		con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				estado = new Estado();
				
				estado.setId(rSet.getInt("id"));
				estado.setNome(rSet.getString("nome"));
				estado.setUf(rSet.getString("uf"));
				
				lista.add(estado);
				
			}
			
			rSet.close();
			pstm.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
		
		
		return lista;
	}

	/**
	 * 
	 * @param estado Recebe um estado e pesquisa no banco de dados por Id
	 * @return Retorna o Estado Localizada
	 */
	@Override
	public Estado pesquisarPorID(Estado estado) {
	
			StringBuffer sql = new StringBuffer();
			sql.append("select * from estados where id = ? ");
			
			Estado estadoRetorno = null;
			
			con = Conexao.getConnection();
			
			try {
				PreparedStatement pstm = con.prepareStatement(sql.toString());
				pstm.setInt(1, estado.getId());

				ResultSet rSet = pstm.executeQuery();

				while (rSet.next()) {
					estadoRetorno = new Estado();
					estadoRetorno.setId(rSet.getInt("id"));
					estadoRetorno.setNome(rSet.getString("nome"));
					estadoRetorno.setUf(rSet.getString("uf"));

				}
			

			} catch (SQLException e) {
				e.printStackTrace();
				JSFUtil.adicionarMensagemErro(e.getMessage());
			}

			return estadoRetorno;


	}

}
