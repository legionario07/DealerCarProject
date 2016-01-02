package br.com.dealercar.dao.itensopcionais;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.dao.IDAO;
import br.com.dealercar.domain.itensopcionais.ArCondicionado;
import br.com.dealercar.domain.itensopcionais.BebeConforto;
import br.com.dealercar.domain.itensopcionais.CadeirinhaBebe;
import br.com.dealercar.domain.itensopcionais.Gps;
import br.com.dealercar.domain.itensopcionais.Itens;
import br.com.dealercar.domain.itensopcionais.Opcional;
import br.com.dealercar.domain.itensopcionais.RadioPlayer;
import br.com.dealercar.domain.itensopcionais.Seguro;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * REaliza a persistencia dos Itens Opcionais Escolhidos pelo Usuario
 * @author Paulinho
 *
 */
public class OpcionalDAO implements IDAO<Opcional>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Connection con = Conexao.getConnection();

	public void cadastrar(Opcional opcional) {

		StringBuffer sql = new StringBuffer();

		sql.append("insert into itens_opcionais ");
		sql.append("(id_seguro, id_arcondicionado, id_bebeconforto, id_caderinhasbebe, ");
		sql.append("id_gps, id_radioplayer) values (?, ?, ?, ?, ?, ?)");

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
						pstm.setInt(3, itens.getCodigo());
						bebe = true;
					}
				}
				if (itens instanceof CadeirinhaBebe) {
					if (itens.getCodigo() > 0) {
						pstm.setInt(4, itens.getCodigo());
						cadeirinha = true;
					}
				}
				if (itens instanceof Gps) {
					if (itens.getCodigo() > 0) {
						pstm.setInt(5, itens.getCodigo());
						gps = true;
					}
				}
				if (itens instanceof RadioPlayer) {
					if (itens.getCodigo() > 0) {
						pstm.setInt(6, itens.getCodigo());
						radio = true;
					}
				}

			}

			// verificando quais opcionais não foi incluido
			// incluindo o id 99 para o opcional que nao foi incluido (99 =
			// null)
			if (opcional.getArCondicionado().getCodigo() < 1) {
				pstm.setInt(2, 99);
			} else {
				pstm.setInt(2, opcional.getArCondicionado().getCodigo());
			}

			if (bebe == false) {
				pstm.setInt(3, 99);
			}
			if (cadeirinha == false) {
				pstm.setInt(4, 99);
			}
			if (gps == false) {
				pstm.setInt(5, 99);
			}
			if (radio == false) {
				pstm.setInt(6, 99);
			}

			pstm.executeUpdate();
			
			pstm.close();
			con.close();

		} catch (

		SQLException e)

		{
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Opcional entidade) {

		// não há exclusão no BD desse tipo de objeto
	}

	@Override
	public void editar(Opcional entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Opcional> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select id, id_seguro, id_arcondicionado, id_bebeconforto, id_caderinhasbebe, ");
		sql.append("id_gps, id_radioplayer from itens_opcionais ");

		List<Opcional> listaOpcionais = new ArrayList<Opcional>();
		
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

				ArCondicionado ar = new ArCondicionado();
				ar.setCodigo(rSet.getInt("id_arcondicionado"));
				ar = new ArCondicionadoDAO().pesquisarPorID(ar);
				opcional.setArCondicionado(ar);

				BebeConforto bebe = new BebeConforto();
				bebe.setCodigo(rSet.getInt("id_bebeconforto"));
				bebe = new BebeConfortoDAO().pesquisarPorCodigo(bebe);

				CadeirinhaBebe cadeirinha = new CadeirinhaBebe();
				cadeirinha.setCodigo(rSet.getInt("id_caderinhasbebe"));
				cadeirinha = new CadeirinhaBebeDAO().pesquisarPorID(cadeirinha);

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

	@Override
	public Opcional pesquisarPorID(Opcional entidade) {

		StringBuffer sql = new StringBuffer();
		sql.append("select id, id_seguro, id_arcondicionado, id_bebeconforto, id_caderinhasbebe, ");
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

				ArCondicionado ar = new ArCondicionado();
				ar.setCodigo(rSet.getInt("id_arcondicionado"));
				ar = new ArCondicionadoDAO().pesquisarPorCodigo(ar);
				opcional.setArCondicionado(ar);

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
		sql.append("select id, id_seguro, id_arcondicionado, id_bebeconforto, id_caderinhasbebe, ");
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

					ArCondicionado ar = new ArCondicionado();
					ar.setCodigo(rSet.getInt("id_arcondicionado"));
					ar = new ArCondicionadoDAO().pesquisarPorID(ar);
					opcional.setArCondicionado(ar);

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