package br.com.dealercar.core.dao.itensopcionais;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.dao.IDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;

/**
 * REaliza a persistencia dos Itens Opcionais Escolhidos pelo Usuario
 * @author Paulinho
 *
 */
public class OpcionalDAO implements IDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection con = null;

	public void cadastrar(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Opcional))
			return;
		
		Opcional opcional =  new Opcional();
		opcional = (Opcional) entidade;

		StringBuffer sql = new StringBuffer();

		sql.append("insert into itens_opcionais ");
		sql.append("(id_seguro, id_bebeconforto, id_caderinhasbebe, ");
		sql.append("id_gps, id_radioplayer) values (?, ?, ?, ?, ?)");

		boolean bebe = false;
		boolean cadeirinha = false;
		boolean gps = false;
		boolean radio = false;

		con = Conexao.getConnection();
		
		try{
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, opcional.getSeguro().getCodigo());

			//Verifica se foi escolhido os itens opcionais
			for (Itens itens : opcional.getItens()) {

				if (itens instanceof BebeConforto) {
					if (itens.getCodigo() > 0) {
						pstm.setInt(2, itens.getCodigo());
						bebe = true;
					}
				}
				if (itens instanceof CadeirinhaBebe) {
					if (itens.getCodigo() > 0) {
						pstm.setInt(3, itens.getCodigo());
						cadeirinha = true;
					}
				}
				if (itens instanceof Gps) {
					if (itens.getCodigo() > 0) {
						pstm.setInt(4, itens.getCodigo());
						gps = true;
					}
				}
				if (itens instanceof RadioPlayer) {
					if (itens.getCodigo() > 0) {
						pstm.setInt(5, itens.getCodigo());
						radio = true;
					}
				}

			}

			// verificando quais opcionais não foi incluido
			// incluindo o id 99 para o opcional que nao foi incluido (99 =
			// null)
			
			if (bebe == false) {
				pstm.setInt(2, 99);
			}
			if (cadeirinha == false) {
				pstm.setInt(3, 99);
			}
			if (gps == false) {
				pstm.setInt(4, 99);
			}
			if (radio == false) {
				pstm.setInt(5, 99);
			}

			pstm.executeUpdate();
			
		} catch (

		SQLException e)

		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {

		// não há exclusão no BD desse tipo de objeto
	}

	@Override
	public void editar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select id, id_seguro, id_bebeconforto, id_caderinhasbebe, ");
		sql.append("id_gps, id_radioplayer from itens_opcionais ");

		List<EntidadeDominio> listaOpcionais = new ArrayList<EntidadeDominio>();
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				Opcional opcional = new Opcional();

				opcional.setId(rSet.getInt("id"));

				Seguro seguro = new Seguro();
				seguro.setCodigo(rSet.getInt("id_seguro"));
				seguro = new SeguroDAO().pesquisarPorCodigo(seguro);
				opcional.setSeguro(seguro);

				BebeConforto bebe = new BebeConforto();
				bebe.setCodigo(rSet.getInt("id_bebeconforto"));
				bebe = new BebeConfortoDAO().pesquisarPorCodigo(bebe);

				CadeirinhaBebe cadeirinha = new CadeirinhaBebe();
				cadeirinha.setCodigo(rSet.getInt("id_caderinhasbebe"));
				cadeirinha = new CadeirinhaBebeDAO().pesquisarPorCodigo(cadeirinha);

				Gps gps = new Gps();
				gps.setCodigo(rSet.getInt("id_gps"));
				gps = new GpsDAO().pesquisarPorCodigo(gps);

				RadioPlayer radio = new RadioPlayer();
				radio.setCodigo(rSet.getInt("id_radioplayer"));
				radio = new RadioPlayerDAO().pesquisarPorCodigo(radio);

				List<Itens> itens = new ArrayList<Itens>();

				itens.add(bebe);
				itens.add(cadeirinha);
				itens.add(gps);
				itens.add(radio);

				opcional.setItens(itens);

				listaOpcionais.add(opcional);

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return listaOpcionais;

	}

	/**
	 * Pesquisa nas TAbelas  de OpcionalDAO cadastrados pelo seu ID
	 */
	@Override
	public Opcional pesquisarPorID(EntidadeDominio entidade) {

		StringBuffer sql = new StringBuffer();
		sql.append("select id, id_seguro, id_bebeconforto, id_caderinhasbebe, ");
		sql.append("id_gps, id_radioplayer from itens_opcionais where id = ?");

		Opcional opcional = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, entidade.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				opcional = new Opcional();

				opcional.setId(rSet.getInt("id"));

				Seguro seguro = new Seguro();
				seguro.setCodigo(rSet.getInt("id_seguro"));
				seguro = new SeguroDAO().pesquisarPorCodigo(seguro);
				opcional.setSeguro(seguro);

				BebeConforto bebe = new BebeConforto();
				bebe.setCodigo(rSet.getInt("id_bebeconforto"));
				bebe = new BebeConfortoDAO().pesquisarPorCodigo(bebe);

				CadeirinhaBebe cadeirinha = new CadeirinhaBebe();
				cadeirinha.setCodigo(rSet.getInt("id_caderinhasbebe"));
				cadeirinha = new CadeirinhaBebeDAO().pesquisarPorCodigo(cadeirinha);

				Gps gps = new Gps();
				gps.setCodigo(rSet.getInt("id_gps"));
				gps = new GpsDAO().pesquisarPorCodigo(gps);

				RadioPlayer radio = new RadioPlayer();
				radio.setCodigo(rSet.getInt("id_radioplayer"));
				radio = new RadioPlayerDAO().pesquisarPorCodigo(radio);

				List<Itens> itens = new ArrayList<Itens>();

				itens.add(bebe);
				itens.add(cadeirinha);
				itens.add(gps);
				itens.add(radio);

				opcional.setItens(itens);

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return opcional;
	}

	public Opcional pesquisarPorUltimoCadastrado() {

		StringBuffer sql = new StringBuffer();
		sql.append("select id, id_seguro, id_bebeconforto, id_caderinhasbebe, ");
		sql.append("id_gps, id_radioplayer from itens_opcionais");

		Opcional opcional = null;
		
		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {

				if (rSet.isLast()) {
					opcional = new Opcional();

					opcional.setId(rSet.getInt("id"));

					Seguro seguro = new Seguro();
					seguro.setCodigo(rSet.getInt("id_seguro"));
					seguro = new SeguroDAO().pesquisarPorCodigo(seguro);
					opcional.setSeguro(seguro);

					BebeConforto bebe = new BebeConforto();
					bebe.setCodigo(rSet.getInt("id_bebeconforto"));
					bebe = new BebeConfortoDAO().pesquisarPorCodigo(bebe);

					CadeirinhaBebe cadeirinha = new CadeirinhaBebe();
					cadeirinha.setCodigo(rSet.getInt("id_caderinhasbebe"));
					cadeirinha = new CadeirinhaBebeDAO().pesquisarPorCodigo(cadeirinha);

					Gps gps = new Gps();
					gps.setCodigo(rSet.getInt("id_gps"));
					gps = new GpsDAO().pesquisarPorCodigo(gps);

					RadioPlayer radio = new RadioPlayer();
					radio.setCodigo(rSet.getInt("id_radioplayer"));
					radio = new RadioPlayerDAO().pesquisarPorCodigo(radio);

					List<Itens> itens = new ArrayList<Itens>();

					itens.add(bebe);
					itens.add(cadeirinha);
					itens.add(gps);
					itens.add(radio);

					opcional.setItens(itens);
				}

			}
			

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return opcional;
	}

}