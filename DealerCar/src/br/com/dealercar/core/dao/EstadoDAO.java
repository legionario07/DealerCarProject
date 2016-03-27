package br.com.dealercar.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.Estado;

/**
 * Persiste os Estados no BD
 * @author Paulinho
 *
 */
public class EstadoDAO implements IDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	
	@Override
	public void cadastrar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> listarTodos() {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from estados");
		
		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
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
	public EntidadeDominio pesquisarPorID(EntidadeDominio entidade) {
		
			if(!(entidade instanceof Estado))
				return null;
			
			Estado estado = new Estado();
			estado = (Estado) entidade;
		
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
