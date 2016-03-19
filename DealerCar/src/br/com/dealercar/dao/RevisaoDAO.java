package br.com.dealercar.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.dealercar.dao.automotivos.CarroDAO;
import br.com.dealercar.dao.itensrevisao.ProdutoRevisaoDAO;
import br.com.dealercar.domain.Devolucao;
import br.com.dealercar.domain.Funcionario;
import br.com.dealercar.domain.Revisao;
import br.com.dealercar.domain.automotivos.Carro;
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
import br.com.dealercar.enums.PosicaoPneu;
import br.com.dealercar.factory.Conexao;
import br.com.dealercar.util.JSFUtil;

/**
 * Classe responsável por persistir as Revisões no BD
 * 
 * @author Paulinho
 *
 */
public class RevisaoDAO implements IDAO<Revisao>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Connection con = null;

	/**
	 * Cadastra uma revisao no BD
	 */
	@Override
	public void cadastrar(Revisao revisao) {

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

			pstm.setString(++i, revisao.getComponentes().getArrefecimento().getSituacao());
			pstm.setString(++i, revisao.getComponentes().getBateria().getSituacao());
			pstm.setString(++i, revisao.getComponentes().getEmbreagem().getSituacao());
			pstm.setString(++i, revisao.getComponentes().getFreio().getSituacao());
			pstm.setString(++i, revisao.getComponentes().getLanterna().getSituacao());
			pstm.setString(++i, revisao.getComponentes().getMotor().getSituacao());
			pstm.setString(++i, revisao.getComponentes().getSuspensao().getSituacao());
			pstm.setString(++i, revisao.getComponentes().getPneus().get(0).getSituacao());// Dianteiro
																							// Direito
			pstm.setString(++i, revisao.getComponentes().getPneus().get(1).getSituacao());// Dianteiro
																							// Esquerdo
			pstm.setString(++i, revisao.getComponentes().getPneus().get(2).getSituacao());// Traseiro
																							// Direito
			pstm.setString(++i, revisao.getComponentes().getPneus().get(3).getSituacao());// Traseiro
																							// Esquerdo
			pstm.setString(++i, revisao.getComponentes().getPneus().get(4).getSituacao());// Estepe
			pstm.setString(++i, revisao.getDescricao());

			pstm.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}

	}

	@Override
	public void excluir(Revisao revisao) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editar(Revisao revisao) {
		// TODO Auto-generated method stub

	}

	/**
	 * Lista todas as revisões cadastradas no BD
	 */
	@Override
	public List<Revisao> listarTodos() {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao");

		List<Revisao> listaRetorno = new ArrayList<Revisao>();

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

				Componentes componente = new Componentes();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				Freio freio = new Freio(rSet.getString("freio"));
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				Motor motor = new Motor(rSet.getString("motor"));
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));

				List<Pneu> pneus = new ArrayList<Pneu>();
				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				pneus.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				pneus.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				pneus.add(estepe);

				componente.setArrefecimento(arrefecimento);
				componente.setBateria(bateria);
				componente.setEmbreagem(embreagem);
				componente.setFreio(freio);
				componente.setLanterna(lanterna);
				componente.setMotor(motor);
				componente.setPneus(pneus);
				componente.setSuspensao(suspensao);

				revisaoRetorno.setComponentes(componente);
				
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
		}

		return listaRetorno;

	}

	/**
	 * Pesquisa no BD uma revisao por seu numero de identificação
	 * 
	 * @return uma Revisao ou null se nao encontrar
	 */
	@Override
	public Revisao pesquisarPorID(Revisao revisao) {

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

				Componentes componente = new Componentes();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				Freio freio = new Freio(rSet.getString("freio"));
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				Motor motor = new Motor(rSet.getString("motor"));
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));

				List<Pneu> pneus = new ArrayList<Pneu>();

				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				pneus.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				pneus.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				pneus.add(estepe);

				componente.setArrefecimento(arrefecimento);
				componente.setBateria(bateria);
				componente.setEmbreagem(embreagem);
				componente.setFreio(freio);
				componente.setLanterna(lanterna);
				componente.setMotor(motor);
				componente.setPneus(pneus);
				componente.setSuspensao(suspensao);

				revisaoRetorno.setComponentes(componente);
				
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

				Componentes componente = new Componentes();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				Freio freio = new Freio(rSet.getString("freio"));
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				Motor motor = new Motor(rSet.getString("motor"));
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));

				List<Pneu> pneus = new ArrayList<Pneu>();
				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				pneus.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				pneus.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				pneus.add(estepe);

				componente.setArrefecimento(arrefecimento);
				componente.setBateria(bateria);
				componente.setEmbreagem(embreagem);
				componente.setFreio(freio);
				componente.setLanterna(lanterna);
				componente.setMotor(motor);
				componente.setPneus(pneus);
				componente.setSuspensao(suspensao);

				revisaoRetorno.setComponentes(componente);
				
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
		}

		return listaRetorno;
	}
	
	/**
	 * Retorna todas as revisao cadastradas no Banco de Dados em um intervalo de data
	 * @param uma Revisao, e uma Data final
	 * @return uma lista de Revisao
	 */
	public List<Revisao> pesquisarPorIntervaloData(Revisao revisao, Date dataFinal) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from revisao ");
		sql.append("where data_revisao between ? and ? ");

		List<Revisao> lista = new ArrayList<Revisao>();
		Revisao revisaoRetorno = null;

		con = Conexao.getConnection();

		try {
			PreparedStatement pstm = con.prepareStatement(sql.toString());
			int i = 0;
			// colocando formato string para buscar no banco de dados
			SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
			String dataRevisao = stf.format(revisao.getDataRevisao());
			String strDataFinal = stf.format(dataFinal);

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

				Componentes componente = new Componentes();

				Arrefecimento arrefecimento = new Arrefecimento(rSet.getString("arreferecimento"));
				Bateria bateria = new Bateria(rSet.getString("bateria"));
				Embreagem embreagem = new Embreagem(rSet.getString("embreagem"));
				Freio freio = new Freio(rSet.getString("freio"));
				Lanterna lanterna = new Lanterna(rSet.getString("lanterna"));
				Motor motor = new Motor(rSet.getString("motor"));
				Suspensao suspensao = new Suspensao(rSet.getString("suspensao"));

				List<Pneu> pneus = new ArrayList<Pneu>();
				Pneu dianteiroDireito = new Pneu(rSet.getString("dianteiro_direito"));
				dianteiroDireito.setPosicaoPneu(PosicaoPneu.DIANTEIRO_DIREITO);
				pneus.add(dianteiroDireito);

				Pneu dianteiroEsquerdo = new Pneu(rSet.getString("dianteiro_esquerdo"));
				dianteiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(dianteiroEsquerdo);

				Pneu traseiroDireito = new Pneu(rSet.getString("traseiro_direito"));
				traseiroDireito.setPosicaoPneu(PosicaoPneu.TRASEIRO_DIREITO);
				pneus.add(traseiroDireito);

				Pneu traseiroEsquerdo = new Pneu(rSet.getString("traseiro_esquerdo"));
				traseiroEsquerdo.setPosicaoPneu(PosicaoPneu.DIANTEIRO_ESQUERDO);
				pneus.add(traseiroEsquerdo);

				Pneu estepe = new Pneu(rSet.getString("estepe"));
				estepe.setPosicaoPneu(PosicaoPneu.ESTEPE);
				pneus.add(estepe);

				componente.setArrefecimento(arrefecimento);
				componente.setBateria(bateria);
				componente.setEmbreagem(embreagem);
				componente.setFreio(freio);
				componente.setLanterna(lanterna);
				componente.setMotor(motor);
				componente.setPneus(pneus);
				componente.setSuspensao(suspensao);

				revisaoRetorno.setComponentes(componente);
				
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
		}

		return lista;

	}

}
