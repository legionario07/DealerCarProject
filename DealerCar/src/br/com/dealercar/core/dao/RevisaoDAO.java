package br.com.dealercar.core.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.dealercar.core.autenticacao.Funcionario;
import br.com.dealercar.core.dao.automotivos.CarroDAO;
import br.com.dealercar.core.dao.itensrevisao.ProdutoRevisaoDAO;
import br.com.dealercar.core.factory.Conexao;
import br.com.dealercar.core.util.JSFUtil;
import br.com.dealercar.domain.EntidadeDominio;
import br.com.dealercar.domain.automotivos.Carro;
import br.com.dealercar.domain.conducao.Devolucao;
import br.com.dealercar.domain.conducao.Revisao;
import br.com.dealercar.domain.enums.PosicaoPneu;
import br.com.dealercar.domain.itensrevisao.Arrefecimento;
import br.com.dealercar.domain.itensrevisao.Bateria;
import br.com.dealercar.domain.itensrevisao.Componentes;
import br.com.dealercar.domain.itensrevisao.Embreagem;
import br.com.dealercar.domain.itensrevisao.Freio;
import br.com.dealercar.domain.itensrevisao.Lanterna;
import br.com.dealercar.domain.itensrevisao.Motor;
import br.com.dealercar.domain.itensrevisao.Pneu;
import br.com.dealercar.domain.itensrevisao.Suspensao;
import br.com.dealercar.domain.produtosrevisao.ProdutoRevisao;

/**
 * Classe responsável por persistir as Revisões no BD
 * 
 * @author Paulinho
 *
 */
public class RevisaoDAO implements IDAO, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	/**
	 * Cadastra uma revisao no BD
	 */
	@Override
	public synchronized void cadastrar(EntidadeDominio entidade) {

		if (!(entidade instanceof Revisao))
			return;

		Revisao revisao = new Revisao();
		revisao = (Revisao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into revisao ");
		sql.append("(id_funcionario, data_revisao, quilometragem, placa, id_devolucao, ");
		sql.append("id_produtos_utilizados, arreferecimento, bateria, embreagem, ");
		sql.append("freio, lanterna, motor, suspensao, ");
		sql.append("dianteiro_direito, dianteiro_esquerdo, traseiro_direito, traseiro_esquerdo, estepe, ");
		sql.append("descricao) ");
		sql.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, revisao.getFuncionario().getId());

			// colocando formato string para armazenar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataRevisao = stf.format(revisao.getDataRevisao());
			pstm.setString(++i, dataRevisao);

			pstm.setString(++i, String.valueOf(revisao.getQuilometragem()));
			pstm.setString(++i, revisao.getCarro().getPlaca());

			// verificando se a revisão é logo após uma devolução
			pstm.setInt(++i, revisao.getDevolucao().getId());

			pstm.setInt(++i, revisao.getListaProdutoRevisao().get(0).getId());

			for (int j = 0; j < revisao.getComponentes().size(); j++) {
				pstm.setString(++i, revisao.getComponentes().get(j).getSituacao());
			}

			pstm.setString(++i, revisao.getDescricao());

			pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	/**
	 * Lista todas as revisões cadastradas no BD
	 */
	@Override
	public List<EntidadeDominio> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao");

		List<EntidadeDominio> listaRetorno = new ArrayList<EntidadeDominio>();

		Revisao revisaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				revisaoRetorno = new Revisao();

				revisaoRetorno.setId(rSet.getInt("id"));
				revisaoRetorno.setDataRevisao(rSet.getDate("data_revisao"));
				revisaoRetorno.setQuilometragem(Long.parseLong(rSet.getString("quilometragem")));
				revisaoRetorno.setDescricao(rSet.getString("descricao"));

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				revisaoRetorno.setFuncionario(funcionario);

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);

				Devolucao devolucao = new Devolucao();
				if (rSet.getInt("id_devolucao") > 0) {
					devolucao.setId(rSet.getInt("id_devolucao"));
					devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);

				}
				revisaoRetorno.setDevolucao(devolucao);

				revisaoRetorno.setCarro(carro);

				List<Componentes> componentes = new ArrayList<Componentes>();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				componentes.add(arrefecimento);
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				componentes.add(bateria);
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				componentes.add(embreagem);
				Freio freio = new Freio(rSet.getString("freio"));
				componentes.add(freio);
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				componentes.add(lanterna);
				Motor motor = new Motor(rSet.getString("motor"));
				componentes.add(motor);
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));
				componentes.add(suspensao);

				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				componentes.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				componentes.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				componentes.add(estepe);

				revisaoRetorno.setComponentes(componentes);

				ProdutoRevisao produtoRevisao = new ProdutoRevisao();
				produtoRevisao.setId(rSet.getInt("id_produtos_utilizados"));

				List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();
				produtos = new ProdutoRevisaoDAO().pesquisarProdutoPorID(produtoRevisao);

				revisaoRetorno.setListaProdutoRevisao(produtos);

				listaRetorno.add(revisaoRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaRetorno;

	}

	/**
	 * Pesquisa no BD uma revisao por seu numero de identificação
	 * 
	 * @return uma Revisao ou null se nao encontrar
	 */
	@Override
	public Revisao pesquisarPorID(EntidadeDominio entidade) {

		if (!(entidade instanceof Revisao))
			return null;

		Revisao revisao = new Revisao();
		revisao = (Revisao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao ");
		sql.append("where id = ?");

		Revisao revisaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setInt(1, revisao.getId());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				revisaoRetorno = new Revisao();

				revisaoRetorno.setId(rSet.getInt("id"));
				revisaoRetorno.setDataRevisao(rSet.getDate("data_revisao"));
				revisaoRetorno.setQuilometragem(Long.parseLong(rSet.getString("quilometragem")));
				revisaoRetorno.setDescricao(rSet.getString("descricao"));

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				revisaoRetorno.setFuncionario(funcionario);

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);

				Devolucao devolucao = new Devolucao();
				if (rSet.getInt("id_devolucao") > 0) {
					devolucao.setId(rSet.getInt("id_devolucao"));
					devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);

				}
				revisaoRetorno.setDevolucao(devolucao);

				revisaoRetorno.setCarro(carro);

				List<Componentes> componentes = new ArrayList<Componentes>();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				componentes.add(arrefecimento);
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				componentes.add(bateria);
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				componentes.add(embreagem);
				Freio freio = new Freio(rSet.getString("freio"));
				componentes.add(freio);
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				componentes.add(lanterna);
				Motor motor = new Motor(rSet.getString("motor"));
				componentes.add(motor);
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));
				componentes.add(suspensao);

				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				componentes.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				componentes.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				componentes.add(estepe);

				revisaoRetorno.setComponentes(componentes);

				ProdutoRevisao produtoRevisao = new ProdutoRevisao();
				produtoRevisao.setId(rSet.getInt("id_produtos_utilizados"));

				List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();
				produtos = new ProdutoRevisaoDAO().pesquisarProdutoPorID(produtoRevisao);

				revisaoRetorno.setListaProdutoRevisao(produtos);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

		return revisaoRetorno;
	}

	/**
	 * Lista todas as revisões de um determinado veiculo
	 * 
	 * @param carro
	 * @return uma lista de Revisões
	 */
	public List<Revisao> pesquisarPorPlaca(Carro carro) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao ");
		sql.append("where placa = ?");

		List<Revisao> listaRetorno = new ArrayList<Revisao>();

		Revisao revisaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			pstm.setString(1, carro.getPlaca());
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				revisaoRetorno = new Revisao();

				revisaoRetorno.setId(rSet.getInt("id"));
				revisaoRetorno.setDataRevisao(rSet.getDate("data_revisao"));
				revisaoRetorno.setQuilometragem(Long.parseLong(rSet.getString("quilometragem")));
				revisaoRetorno.setDescricao(rSet.getString("descricao"));

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				revisaoRetorno.setFuncionario(funcionario);

				carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);

				Devolucao devolucao = new Devolucao();
				if (rSet.getInt("id_devolucao") > 0) {
					devolucao.setId(rSet.getInt("id_devolucao"));
					devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);

				}
				revisaoRetorno.setDevolucao(devolucao);

				revisaoRetorno.setCarro(carro);

				List<Componentes> componentes = new ArrayList<Componentes>();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				componentes.add(arrefecimento);
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				componentes.add(bateria);
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				componentes.add(embreagem);
				Freio freio = new Freio(rSet.getString("freio"));
				componentes.add(freio);
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				componentes.add(lanterna);
				Motor motor = new Motor(rSet.getString("motor"));
				componentes.add(motor);
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));
				componentes.add(suspensao);

				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				componentes.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				componentes.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				componentes.add(estepe);

				revisaoRetorno.setComponentes(componentes);

				ProdutoRevisao produtoRevisao = new ProdutoRevisao();
				produtoRevisao.setId(rSet.getInt("id_produtos_utilizados"));

				List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();
				produtos = new ProdutoRevisaoDAO().pesquisarProdutoPorID(produtoRevisao);

				revisaoRetorno.setListaProdutoRevisao(produtos);

				listaRetorno.add(revisaoRetorno);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listaRetorno;
	}

	/**
	 * Retorna todas as revisao cadastradas no Banco de Dados em um intervalo de
	 * data
	 * 
	 * @param uma
	 *            Revisao
	 * @return uma lista de Revisao
	 */
	public List<EntidadeDominio> pesquisarPorIntervaloData(EntidadeDominio entidade) {

		if (!(entidade instanceof Revisao))
			return null;

		Revisao revisao = new Revisao();
		revisao = (Revisao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao ");
		sql.append("where data_revisao between ? and ? ");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		Revisao revisaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			// colocando formato string para buscar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataRevisao = stf.format(revisao.getDataRevisao());
			String strDataFinal = stf.format(revisao.getDevolucao().getDataDevolucao());

			pstm.setString(++i, dataRevisao);
			pstm.setString(++i, strDataFinal);
			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				revisaoRetorno = new Revisao();

				revisaoRetorno.setId(rSet.getInt("id"));
				revisaoRetorno.setDataRevisao(rSet.getDate("data_revisao"));
				revisaoRetorno.setQuilometragem(Long.parseLong(rSet.getString("quilometragem")));
				revisaoRetorno.setDescricao(rSet.getString("descricao"));

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				revisaoRetorno.setFuncionario(funcionario);

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);

				Devolucao devolucao = new Devolucao();
				if (rSet.getInt("id_devolucao") > 0) {
					devolucao.setId(rSet.getInt("id_devolucao"));
					devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);

				}
				revisaoRetorno.setDevolucao(devolucao);

				revisaoRetorno.setCarro(carro);

				List<Componentes> componentes = new ArrayList<Componentes>();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				componentes.add(arrefecimento);
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				componentes.add(bateria);
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				componentes.add(embreagem);
				Freio freio = new Freio(rSet.getString("freio"));
				componentes.add(freio);
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				componentes.add(lanterna);
				Motor motor = new Motor(rSet.getString("motor"));
				componentes.add(motor);
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));
				componentes.add(suspensao);

				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				componentes.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				componentes.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				componentes.add(estepe);

				revisaoRetorno.setComponentes(componentes);

				ProdutoRevisao produtoRevisao = new ProdutoRevisao();
				produtoRevisao.setId(rSet.getInt("id_produtos_utilizados"));

				List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();
				produtos = new ProdutoRevisaoDAO().pesquisarProdutoPorID(produtoRevisao);

				revisaoRetorno.setListaProdutoRevisao(produtos);

				lista.add(revisaoRetorno);
			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;

	}

	public List<EntidadeDominio> pesquisarPorModelo(EntidadeDominio entidade) {

		if (!(entidade instanceof Revisao))
			return null;

		Revisao revisao = new Revisao();
		revisao = (Revisao) entidade;

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao ");
		sql.append("inner join carros on carros.placa = revisao.placa ");
		sql.append("where carros.id_modelo = ?");

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		Revisao revisaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			pstm.setInt(++i, revisao.getCarro().getModelo().getId());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				revisaoRetorno = new Revisao();

				revisaoRetorno.setId(rSet.getInt("id"));
				revisaoRetorno.setDataRevisao(rSet.getDate("data_revisao"));
				revisaoRetorno.setQuilometragem(Long.parseLong(rSet.getString("quilometragem")));
				revisaoRetorno.setDescricao(rSet.getString("descricao"));

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				revisaoRetorno.setFuncionario(funcionario);

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);

				Devolucao devolucao = new Devolucao();
				if (rSet.getInt("id_devolucao") > 0) {
					devolucao.setId(rSet.getInt("id_devolucao"));
					devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);

				}
				revisaoRetorno.setDevolucao(devolucao);

				revisaoRetorno.setCarro(carro);

				List<Componentes> componentes = new ArrayList<Componentes>();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				componentes.add(arrefecimento);
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				componentes.add(bateria);
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				componentes.add(embreagem);
				Freio freio = new Freio(rSet.getString("freio"));
				componentes.add(freio);
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				componentes.add(lanterna);
				Motor motor = new Motor(rSet.getString("motor"));
				componentes.add(motor);
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));
				componentes.add(suspensao);

				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				componentes.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				componentes.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				componentes.add(estepe);

				revisaoRetorno.setComponentes(componentes);

				ProdutoRevisao produtoRevisaoRetorno = new ProdutoRevisao();
				produtoRevisaoRetorno.setId(rSet.getInt("id_produtos_utilizados"));

				List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();
				produtos = new ProdutoRevisaoDAO().pesquisarProdutoPorID(produtoRevisaoRetorno);

				revisaoRetorno.setListaProdutoRevisao(produtos);

				lista.add(revisaoRetorno);
			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;

	}

	public List<EntidadeDominio> pesquisarPorProdutoUtilizado(Object entidade) {

		String criterio = null;
		Revisao rev = new Revisao();
		rev = (Revisao) entidade;
		criterio = rev.getDescricao();

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao ");
		sql.append("inner join produto_revisao on produto_revisao.id = revisao.id_produtos_utilizados ");
		sql.append(criterio);

		List<EntidadeDominio> lista = new ArrayList<EntidadeDominio>();
		Revisao revisaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());

			ResultSet rSet = pstm.executeQuery();

			while (rSet.next()) {
				revisaoRetorno = new Revisao();

				revisaoRetorno.setId(rSet.getInt("id"));
				revisaoRetorno.setDataRevisao(rSet.getDate("data_revisao"));
				revisaoRetorno.setQuilometragem(Long.parseLong(rSet.getString("quilometragem")));
				revisaoRetorno.setDescricao(rSet.getString("descricao"));

				Funcionario funcionario = new Funcionario(rSet.getInt("id_funcionario"));
				funcionario = new FuncionarioDAO().pesquisarPorID(funcionario);
				revisaoRetorno.setFuncionario(funcionario);

				Carro carro = new Carro(rSet.getString("placa"));
				carro = new CarroDAO().pesquisarPorPlaca(carro);

				Devolucao devolucao = new Devolucao();
				if (rSet.getInt("id_devolucao") > 0) {
					devolucao.setId(rSet.getInt("id_devolucao"));
					devolucao = new DevolucaoDAO().pesquisarPorID(devolucao);

				}
				revisaoRetorno.setDevolucao(devolucao);

				revisaoRetorno.setCarro(carro);

				List<Componentes> componentes = new ArrayList<Componentes>();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				componentes.add(arrefecimento);
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				componentes.add(bateria);
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				componentes.add(embreagem);
				Freio freio = new Freio(rSet.getString("freio"));
				componentes.add(freio);
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				componentes.add(lanterna);
				Motor motor = new Motor(rSet.getString("motor"));
				componentes.add(motor);
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));
				componentes.add(suspensao);

				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				componentes.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				componentes.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				componentes.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				componentes.add(estepe);

				revisaoRetorno.setComponentes(componentes);

				ProdutoRevisao produtoRevisaoRetorno = new ProdutoRevisao();
				produtoRevisaoRetorno.setId(rSet.getInt("id_produtos_utilizados"));

				List<ProdutoRevisao> produtos = new ArrayList<ProdutoRevisao>();
				produtos = new ProdutoRevisaoDAO().pesquisarProdutoPorID(produtoRevisaoRetorno);

				revisaoRetorno.setListaProdutoRevisao(produtos);

				lista.add(revisaoRetorno);
			}

			rSet.close();
			pstm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;

	}

}
